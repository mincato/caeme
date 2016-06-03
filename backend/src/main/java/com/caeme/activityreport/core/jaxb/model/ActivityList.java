package com.caeme.activityreport.core.jaxb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listado_reporte")
@XmlAccessorType(XmlAccessType.FIELD)
public class ActivityList {

	@XmlElement(name = "reporte")
	private List<Reporte> reportes;

	public List<Reporte> getReportes() {
		return reportes;
	}

	public void setReportes(List<Reporte> reportes) {
		this.reportes = reportes;
	}

}
