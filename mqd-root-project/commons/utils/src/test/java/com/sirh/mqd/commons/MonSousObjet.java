package com.sirh.mqd.commons;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MonSousObjet implements Serializable {

	private static final long serialVersionUID = -6053538341450204203L;

	private String monNom;

	private BigDecimal maDecimale;

	public String getMonNom() {
		return this.monNom;
	}

	public void setMonNom(final String monNom) {
		this.monNom = monNom;
	}

	public BigDecimal getMaDecimale() {
		return this.maDecimale;
	}

	public void setMaDecimale(final BigDecimal maDecimale) {
		this.maDecimale = maDecimale;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(1153, 1163, this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}

}