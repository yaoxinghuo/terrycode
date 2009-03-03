package com.microblog.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "melimit")
public class Limit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -511473452999568799L;

	private String id;

	private int purview;
	private String mto;//如果Purview==-1而属于Rss订阅，那么这个栏位是Subscribe的subscribid

	private String forumid;

	private Message message;

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMto() {
		return mto;
	}

	public void setMto(String mto) {
		this.mto = mto;
	}

	@OneToOne
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void setPurview(int purview) {
		this.purview = purview;
	}

	public int getPurview() {
		return purview;
	}

	public void setForumid(String forumid) {
		this.forumid = forumid;
	}

	public String getForumid() {
		return forumid;
	}
}
