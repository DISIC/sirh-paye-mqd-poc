package com.sirh.mqd.commons.storage.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entité des référentiels
 *
 * @author alexandre
 */
@Document(collection = "referentiels")
public class ReferentielEntity {

	@Id
	private String id;

	@Indexed
	private String name;

	private Date lastUpdate;

	public ReferentielEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
