package com.terry.costnote.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.datanucleus.jpa.annotations.Extension;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create：Jul 12, 2009 7:50:01 AM
 */

@Entity
public class Schedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4577147027319519223L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Enumerated
	private String name;
	@Enumerated
	private String message;
	@Enumerated
	@Temporal(TemporalType.TIMESTAMP)
	private Date cdate;
	@Enumerated
	@Temporal(TemporalType.TIMESTAMP)
	private Date adate;
	@Enumerated
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated
	private boolean type;// true executed
	
	@Enumerated
	private String sid;//id return from fetion

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getAdate() {
		return adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public boolean isType() {
		return type;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSid() {
		return sid;
	}

}
