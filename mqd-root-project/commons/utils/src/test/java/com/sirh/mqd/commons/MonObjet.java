package com.sirh.mqd.commons;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@XmlRootElement(name = "MonObjet")
public class MonObjet implements Serializable {

	private static final long serialVersionUID = -8929092897326354892L;

	private boolean monBoolean;

	private int monEntier;

	private String maChaine;

	private List<MonSousObjet> maListe;

	public boolean isMonBoolean() {
		return monBoolean;
	}

	public void setMonBoolean(final boolean monBoolean) {
		this.monBoolean = monBoolean;
	}

	public int getMonEntier() {
		return monEntier;
	}

	public void setMonEntier(final int monEntier) {
		this.monEntier = monEntier;
	}

	public String getMaChaine() {
		return maChaine;
	}

	public void setMaChaine(final String maChaine) {
		this.maChaine = maChaine;
	}

	@XmlElementWrapper(name = "maListe")
	@XmlElement(name = "MonSousObjet")
	public List<MonSousObjet> getMaListe() {
		return maListe;
	}

	public void setMaListe(final List<MonSousObjet> maListe) {
		this.maListe = maListe;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(1009, 1013, this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

}
