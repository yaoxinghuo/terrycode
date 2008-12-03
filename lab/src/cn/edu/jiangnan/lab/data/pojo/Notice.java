package cn.edu.jiangnan.lab.data.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "notice")
public class Notice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1454315047958871650L;
	private String id;
	private String title;
	private String content;
	private Date input;
	private int type; // 1通知公告 2设备使用公告 3管理制度
	private Date date;
	private String sDate;
	private boolean red;
	private boolean bold;
	public boolean isRed() {
		return red;
	}

	public void setRed(boolean red) {
		this.red = red;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	private boolean pub;

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Transient
	public String getSDate() {
		return sDate;
	}

	public void setSDate(String date) {
		sDate = date;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic(fetch = FetchType.LAZY)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInput() {
		return input;
	}

	public void setInput(Date input) {
		this.input = input;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
