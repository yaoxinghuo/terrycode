package com.terry.weddingphoto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.datanucleus.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Blob;

/**
 * @author Terry E-mail: yaoxinghuo at 126 dot com
 * @version create: 2010-3-15 下午01:08:46
 */
@Entity
public class Thumb implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -834012085269978947L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Enumerated
	private Blob data;

	@OneToOne
	private Photo photo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date cdate;

	@Enumerated
	private String pid;// PhotoId

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}
}
