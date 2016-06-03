package com.caeme.activityreport.core.jaxb.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.caeme.activityreport.core.jaxb.model.ActivityList;
import com.caeme.activityreport.core.jaxb.model.Reporte;
import com.caeme.activityreport.core.model.Activity;
import com.caeme.activityreport.core.model.Area;
import com.caeme.activityreport.core.model.CaemeParticipant;
import com.caeme.activityreport.core.model.Participant;
import com.caeme.activityreport.core.model.ParticipationType;
import com.caeme.activityreport.core.model.ParticipationTypeEnum;
import com.caeme.activityreport.core.model.User;
import com.caeme.activityreport.core.service.ActivityService;
import com.caeme.activityreport.core.service.ActivityServiceImpl;
import com.caeme.activityreport.core.service.AreaService;
import com.caeme.activityreport.core.service.UserService;

public class Main {

	public static void main(String[] args) throws Exception {
		try {

			File file = new File("C:\\caeme\\dbReporteActividad.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ActivityList.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ActivityList activityList = (ActivityList) jaxbUnmarshaller.unmarshal(file);

			ApplicationContext context = new ClassPathXmlApplicationContext("spring/services.xml");
			ActivityService activityService = (ActivityService) context.getBean("activityServiceImpl");
			AreaService areaService = (AreaService) context.getBean("areaServiceImpl");
			UserService userService = (UserService) context.getBean("userServiceImpl");

			Map<String, Area> areas = new HashMap<>();
			Map<String, User> users = new HashMap<>();
			List<Activity> activities = createActivities(activityList, areas, users);
			for (User user : users.values()) {
				user.setId(userService.saveUser(user));
			}
			for (Area area : areas.values()) {
				area.setId(areaService.saveArea(area));
			}
			for (Activity activity : activities) {
				activityService.saveActivity(activity);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static List<Activity> createActivities(ActivityList activityList, Map<String, Area> areas,
			Map<String, User> users) throws Exception {
		List<Activity> activities = new ArrayList<>();
		List<Reporte> reportes = activityList.getReportes();
		for (Reporte reporte : reportes) {
			Activity activity = new Activity();
			createArea(areas, reporte);
			activity.setArea(areas.get(reporte.getArea()));
			if (StringUtils.isNotEmpty(reporte.getFechaDesde())) {
				activity.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(reporte.getFechaDesde()));
			}
			if (StringUtils.isNotEmpty(reporte.getFechaHasta())) {
				activity.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(reporte.getFechaHasta()));
			}
			activity.setTitle(reporte.getTitulo());
			activity.setDescription(reporte.getDescripcion());
			activity.setObservation(reporte.getObservacion());
			activity.setLocation(reporte.getUbicacion());
			activity.setCaemeParticipants(buildCaemeParticipants(reporte, users));
			activity.setOtherParticipants(buildOtherParticipants(reporte));
			if (!users.containsKey(reporte.getCompletadoPor().trim())) {
				User user = new User();
				user.setName(reporte.getCompletadoPor().trim());
				users.put(reporte.getCompletadoPor().trim(), user);
			}
			activity.setCreatedBy(users.get(reporte.getCompletadoPor().trim()));

			activities.add(activity);
		}
		return activities;
	}

	private static List<Participant> buildOtherParticipants(Reporte reporte) {
		List<Participant> participants = new ArrayList<>();
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro1())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro1(),
					reporte.getParticipacionOtro1(), reporte.getObservacionOtro1());
			participants.add(participant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro2())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro2(),
					reporte.getParticipacionOtro2(), reporte.getObservacionOtro2());
			participants.add(participant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro3())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro3(),
					reporte.getParticipacionOtro3(), reporte.getObservacionOtro3());
			participants.add(participant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro4())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro4(),
					reporte.getParticipacionOtro4(), reporte.getObservacionOtro4());
			participants.add(participant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro5())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro5(),
					reporte.getParticipacionOtro5(), reporte.getObservacionOtro5());
			participants.add(participant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteOtro6())) {
			Participant participant = buildOtherParticipant(reporte.getParticipanteOtro6(),
					reporte.getParticipacionOtro6(), reporte.getObservacionOtro6());
			participants.add(participant);
		}


		return participants;
	}

	private static Participant buildOtherParticipant(String participanteOtro, String participacionOtro,
			String observacionOtro) {
		Participant participant = new Participant();
		participant.setName(participanteOtro);
		participant.setObservation(observacionOtro);
		ParticipationType participationType = new ParticipationType(
				ParticipationTypeEnum.getValue(participacionOtro), null);
		participant.setParticipationType(participationType);
		return participant;
	}


	private static List<CaemeParticipant> buildCaemeParticipants(Reporte reporte, Map<String, User> users) {
		List<CaemeParticipant> caemeParticipants = new ArrayList<>();
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme1())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme1(),
					reporte.getParticipacionCaeme1(), reporte.getObservacionCaeme1(), users);
			caemeParticipant.setOrganizer(true);
			caemeParticipants.add(caemeParticipant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme2())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme2(),
					reporte.getParticipacionCaeme2(), reporte.getObservacionCaeme2(), users);
			caemeParticipants.add(caemeParticipant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme3())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme3(),
					reporte.getParticipacionCaeme3(), reporte.getObservacionCaeme3(), users);
			caemeParticipants.add(caemeParticipant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme4())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme4(),
					reporte.getParticipacionCaeme4(), reporte.getObservacionCaeme4(), users);
			caemeParticipants.add(caemeParticipant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme5())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme5(),
					reporte.getParticipacionCaeme5(), reporte.getObservacionCaeme1(), users);
			caemeParticipants.add(caemeParticipant);
		}
		if (StringUtils.isNotEmpty(reporte.getParticipanteCaeme6())) {
			CaemeParticipant caemeParticipant = buildCaemeParticipant(reporte.getParticipanteCaeme6(),
					reporte.getParticipacionCaeme6(), reporte.getObservacionCaeme6(), users);
			caemeParticipants.add(caemeParticipant);
		}
		return caemeParticipants;
	}

	private static CaemeParticipant buildCaemeParticipant(String participanteCaeme, String participacionCaeme,
			String observacionCaeme, Map<String, User> users) {
		CaemeParticipant caemeParticipant = new CaemeParticipant();
		if (!users.containsKey(participanteCaeme.trim())) {
			User user = new User();
			user.setName(participanteCaeme.trim());
			users.put(participanteCaeme.trim(), user);
		}
		caemeParticipant.setUser(users.get(participanteCaeme.trim()));
		caemeParticipant.setObservation(observacionCaeme);
		ParticipationType participationType = new ParticipationType(
				ParticipationTypeEnum.getValue(participacionCaeme), null);
		caemeParticipant.setParticipationType(participationType);
		return caemeParticipant;
	}

	private static void createArea(Map<String, Area> areas, Reporte reporte) {
		if (!areas.containsKey(reporte.getArea().trim())) {
			Area area = new Area();
			area.setName(reporte.getArea().trim());
			areas.put(reporte.getArea().trim(), area);
		}
	}

}
