package com.microblog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "url")
public class Url implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6466000564699850189L;
	
	private String id;
	private Date lastvisit;
	private String url;
	private String hash;

	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastvisit() {
		return lastvisit;
	}

	public void setLastvisit(Date lastvisit) {
		this.lastvisit = lastvisit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(unique = true)
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
}
