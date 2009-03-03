package com.microblog.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mereply")
public class Reply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2829326426970447237L;

	private String id;
	private String avatar;
	private String image;//這個發布有圖片的，圖片的位置

	private String nickname;
	private String pmessage;
	private int purview;
	public String getPmessage() {
		return pmessage;
	}

	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}

	private String content;
	private int type;//1回复 2愤 3自己的备注
	private Date inputdate;
	private Message message;
	
	private String raccountid;//回复发表者的ID
	private String maccountid;//日记发表者的ID

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInputdate() {
		return inputdate;
	}

	public void setInputdate(Date inputdate) {
		this.inputdate = inputdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getRaccountid() {
		return raccountid;
	}

	public void setRaccountid(String raccountid) {
		this.raccountid = raccountid;
	}

	public String getMaccountid() {
		return maccountid;
	}

	public void setMaccountid(String maccountid) {
		this.maccountid = maccountid;
	}

	public void setPurview(int purview) {
		this.purview = purview;
	}

	public int getPurview() {
		return purview;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}
}
