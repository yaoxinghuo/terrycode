package com.microblog.data.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6533125981356551408L;

	private String id;
	private String email;
	private String avatar;

	private Date udate;

	private String nickname;

	private boolean sex;

	private String region;

	private Date birthday;

	private Date rdate;// 注册时间

	private float tzone;// 时区

	private String occupation;

	private String school;

	private String tag;

	private int imnat;

	private String strBirthday;

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getImnat() {
		return imnat;
	}

	public void setImnat(int imnat) {
		this.imnat = imnat;
	}

	private String company;

	private String title;

	private String aplace;

	private String msn;

	private String qq;

	private String gtalk;

	private String yahoo;

	private String skype;

	private String pmessage;

	private String robotid;

	public String getPmessage() {
		return pmessage;
	}

	public void setPmessage(String pmessage) {
		this.pmessage = pmessage;
	}

	private List<Message> messages = new ArrayList<Message>();
	private List<Friend> friends = new ArrayList<Friend>();

	private Passport passport;

	private List<Group> groups = new ArrayList<Group>();
	private List<Subscribe> subscribes = new ArrayList<Subscribe>();
	private List<Head> heads = new ArrayList<Head>();

	public String getAplace() {
		return aplace;
	}

	public String getAvatar() {
		return avatar;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Message.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "account_id")
	public List<Message> getMessages() {
		return messages;
	}

	public String getCompany() {
		return company;
	}

	@Column(unique = true)
	public String getEmail() {
		return email;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Friend.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "account_id")
	public List<Friend> getFriends() {
		return friends;
	}

	public String getGtalk() {
		return gtalk;
	}

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Column(unique = true)
	public String getMsn() {
		return msn;
	}

	public String getNickname() {
		return nickname;
	}

	public String getQq() {
		return qq;
	}

	public String getRegion() {
		return region;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getRdate() {
		return rdate;
	}

	public String getTitle() {
		return title;
	}

	public String getYahoo() {
		return yahoo;
	}

	public boolean isSex() {
		return sex;
	}

	public void setAplace(String aplace) {
		this.aplace = aplace;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public void setGtalk(String gtalk) {
		this.gtalk = gtalk;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setYahoo(String yahoo) {
		this.yahoo = yahoo;
	}

	public void setStrBirthday(String strBirthday) {
		this.strBirthday = strBirthday;
	}

	@Transient
	public String getStrBirthday() {
		return strBirthday;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, mappedBy = "account")
	public Passport getPassport() {
		return passport;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Group.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "account_id")
	@OrderBy(value = "type")
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void setTzone(float tzone) {
		this.tzone = tzone;
	}

	public float getTzone() {
		return tzone;
	}

	public void setSubscribes(List<Subscribe> subscribes) {
		this.subscribes = subscribes;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Subscribe.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "account_id")
	public List<Subscribe> getSubscribes() {
		return subscribes;
	}

	public void setHeads(List<Head> heads) {
		this.heads = heads;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Head.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "account_id")
	public List<Head> getHeads() {
		return heads;
	}

	public void setUdate(Date udate) {
		this.udate = udate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getUdate() {
		return udate;
	}

	public void setRobotid(String robotid) {
		this.robotid = robotid;
	}

	public String getRobotid() {
		return robotid;
	}

}
