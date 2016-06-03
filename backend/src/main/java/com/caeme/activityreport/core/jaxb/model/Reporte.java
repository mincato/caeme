package com.caeme.activityreport.core.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reporte {

	@XmlElement(name = "Area")
	private String area;

	@XmlElement(name = "Completado_por")
	private String completadoPor;

	@XmlElement(name = "Titulo_actividad")
	private String titulo;

	@XmlElement(name = "Fecha_desde")
	private String fechaDesde;

	@XmlElement(name = "Fecha_hasta")
	private String fechaHasta;

	@XmlElement(name = "Descripcion")
	private String descripcion;

	@XmlElement(name = "Tipo_de_participacion")
	private String tipoDeParticipacion;

	@XmlElement(name = "Ubicacion")
	private String ubicacion;

	@XmlElement(name = "Observacion")
	private String observacion;

	@XmlElement(name = "Ano")
	private String ano;

	@XmlElement(name = "Mes")
	private String mes;

	@XmlElement(name = "Dia")
	private String dia;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_1")
	private String participanteCaeme1;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_2")
	private String participanteCaeme2;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_3")
	private String participanteCaeme3;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_4")
	private String participanteCaeme4;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_5")
	private String participanteCaeme5;

	@XmlElement(name = "Parcicipante_CAEMe_Nombre_6")
	private String participanteCaeme6;

	@XmlElement(name = "Tipo_participacion_CAEMe_1")
	private String participacionCaeme1;

	@XmlElement(name = "Tipo_participacion_CAEMe_2")
	private String participacionCaeme2;

	@XmlElement(name = "Tipo_participacion_CAEMe_3")
	private String participacionCaeme3;

	@XmlElement(name = "Tipo_participacion_CAEMe_4")
	private String participacionCaeme4;

	@XmlElement(name = "Tipo_participacion_CAEMe_5")
	private String participacionCaeme5;

	@XmlElement(name = "Tipo_participacion_CAEMe_6")
	private String participacionCaeme6;

	@XmlElement(name = "Observacion_CAEMe_1")
	private String observacionCaeme1;

	@XmlElement(name = "Observacion_CAEMe_2")
	private String observacionCaeme2;

	@XmlElement(name = "Observacion_CAEMe_3")
	private String observacionCaeme3;

	@XmlElement(name = "Observacion_CAEMe_4")
	private String observacionCaeme4;

	@XmlElement(name = "Observacion_CAEMe_5")
	private String observacionCaeme5;

	@XmlElement(name = "Observacion_CAEMe_6")
	private String observacionCaeme6;

	@XmlElement(name = "Otro_parcicipante_Nombre_1")
	private String participanteOtro1;

	@XmlElement(name = "Otro_parcicipante_Nombre_2")
	private String participanteOtro2;

	@XmlElement(name = "Otro_parcicipante_Nombre_3")
	private String participanteOtro3;

	@XmlElement(name = "Otro_parcicipante_Nombre_4")
	private String participanteOtro4;

	@XmlElement(name = "Otro_parcicipante_Nombre_5")
	private String participanteOtro5;

	@XmlElement(name = "Otro_parcicipante_Nombre_6")
	private String participanteOtro6;

	@XmlElement(name = "Otro_tipo_participacion_1")
	private String participacionOtro1;

	@XmlElement(name = "Otro_tipo_participacion_2")
	private String participacionOtro2;

	@XmlElement(name = "Otro_tipo_participacion_3")
	private String participacionOtro3;

	@XmlElement(name = "Otro_tipo_participacion_4")
	private String participacionOtro4;

	@XmlElement(name = "Otro_tipo_participacion_5")
	private String participacionOtro5;

	@XmlElement(name = "Otro_tipo_participacion_6")
	private String participacionOtro6;

	@XmlElement(name = "Observacion_Otro_1")
	private String observacionOtro1;

	@XmlElement(name = "Observacion_Otro_2")
	private String observacionOtro2;

	@XmlElement(name = "Observacion_Otro_3")
	private String observacionOtro3;

	@XmlElement(name = "Observacion_Otro_4")
	private String observacionOtro4;

	@XmlElement(name = "Observacion_Otro_5")
	private String observacionOtro5;

	@XmlElement(name = "Observacion_Otro_6")
	private String observacionOtro6;

	public String getArea() {
		return area;
	}

	public String getCompletadoPor() {
		return completadoPor;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTipoDeParticipacion() {
		return tipoDeParticipacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getObservacion() {
		return observacion;
	}

	public String getParticipanteCaeme1() {
		return participanteCaeme1;
	}

	public String getParticipanteCaeme2() {
		return participanteCaeme2;
	}

	public String getParticipanteCaeme3() {
		return participanteCaeme3;
	}

	public String getParticipanteCaeme4() {
		return participanteCaeme4;
	}

	public String getParticipanteCaeme5() {
		return participanteCaeme5;
	}

	public String getParticipanteCaeme6() {
		return participanteCaeme6;
	}

	public String getParticipacionCaeme1() {
		return participacionCaeme1;
	}

	public String getParticipacionCaeme2() {
		return participacionCaeme2;
	}

	public String getParticipacionCaeme3() {
		return participacionCaeme3;
	}

	public String getParticipacionCaeme4() {
		return participacionCaeme4;
	}

	public String getParticipacionCaeme5() {
		return participacionCaeme5;
	}

	public String getParticipacionCaeme6() {
		return participacionCaeme6;
	}

	public String getObservacionCaeme1() {
		return observacionCaeme1;
	}

	public String getObservacionCaeme2() {
		return observacionCaeme2;
	}

	public String getObservacionCaeme3() {
		return observacionCaeme3;
	}

	public String getObservacionCaeme4() {
		return observacionCaeme4;
	}

	public String getObservacionCaeme5() {
		return observacionCaeme5;
	}

	public String getObservacionCaeme6() {
		return observacionCaeme6;
	}

	public String getParticipanteOtro1() {
		return participanteOtro1;
	}

	public String getParticipanteOtro2() {
		return participanteOtro2;
	}

	public String getParticipanteOtro3() {
		return participanteOtro3;
	}

	public String getParticipanteOtro4() {
		return participanteOtro4;
	}

	public String getParticipanteOtro5() {
		return participanteOtro5;
	}

	public String getParticipanteOtro6() {
		return participanteOtro6;
	}

	public String getParticipacionOtro1() {
		return participacionOtro1;
	}

	public String getParticipacionOtro2() {
		return participacionOtro2;
	}

	public String getParticipacionOtro3() {
		return participacionOtro3;
	}

	public String getParticipacionOtro4() {
		return participacionOtro4;
	}

	public String getParticipacionOtro5() {
		return participacionOtro5;
	}

	public String getParticipacionOtro6() {
		return participacionOtro6;
	}

	public String getObservacionOtro1() {
		return observacionOtro1;
	}

	public String getObservacionOtro2() {
		return observacionOtro2;
	}

	public String getObservacionOtro3() {
		return observacionOtro3;
	}

	public String getObservacionOtro4() {
		return observacionOtro4;
	}

	public String getObservacionOtro5() {
		return observacionOtro5;
	}

	public String getObservacionOtro6() {
		return observacionOtro6;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAno() {
		return ano;
	}

	public String getMes() {
		return mes;
	}

	public String getDia() {
		return dia;
	}

}
