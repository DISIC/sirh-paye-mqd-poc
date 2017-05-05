package com.sirh.mqd.reporting.persistence.entities;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 *
 * Entité d'echange elasticsearch
 *
 */
@Document(indexName = "logstash-perf*", type = "performance", shards = 1, replicas = 0)
public class WSPerformanceEntity extends GenericLogstachEntity {

	private String composant;

	private String type;

	private String service;

	private int duree;

	public String getComposant() {
		return composant;
	}

	public void setComposant(final String composant) {
		this.composant = composant;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getService() {
		return service;
	}

	public void setService(final String service) {
		this.service = service;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(final int duree) {
		this.duree = duree;
	}

}
