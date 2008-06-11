package com.portal.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Developer: Terry DateTime : 2007-12-17 下午01:03:04
 */

@Entity
@Table(name = "row")
public class Row implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private int type;
	private String title;
	private String type1;
	private String type2;
	private String ex_type3;
	private int showNumber;
	private String rssAddress;
	private String rssLink;
	private boolean show_tip;
	private boolean show_from;
	private boolean show_time;
	private int vote;
	private boolean share;
	private int firstResult;//分页用的
	private int totalResult;//分页用的
	private Tab tab;

	@ManyToOne(fetch = FetchType.LAZY)
	public Tab getTab() {
		return tab;
	}

	public void setTab(Tab tab) {
		this.tab = tab;
	}

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getEx_type3() {
		return ex_type3;
	}

	public void setEx_type3(String ex_type3) {
		this.ex_type3 = ex_type3;
	}

	public int getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(int showNumber) {
		this.showNumber = showNumber;
	}

	public String getRssAddress() {
		return rssAddress;
	}

	public void setRssAddress(String rssAddress) {
		this.rssAddress = rssAddress;
	}

	public String getRssLink() {
		return rssLink;
	}

	public void setRssLink(String rssLink) {
		this.rssLink = rssLink;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Transient
	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	@Transient
	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public boolean isShow_tip() {
		return show_tip;
	}

	public void setShow_tip(boolean show_tip) {
		this.show_tip = show_tip;
	}

	public boolean isShow_from() {
		return show_from;
	}

	public void setShow_from(boolean show_from) {
		this.show_from = show_from;
	}

	public boolean isShow_time() {
		return show_time;
	}

	public void setShow_time(boolean show_time) {
		this.show_time = show_time;
	}

}
