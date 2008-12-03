package cn.edu.jiangnan.lab.data.pojo;

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
@Table(name = "book")
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Equip equip;
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Equip getEquip() {
		return equip;
	}

	public void setEquip(Equip equip) {
		this.equip = equip;
	}

	public String getSample_name() {
		return sample_name;
	}

	public void setSample_name(String sample_name) {
		this.sample_name = sample_name;
	}

	public int getSample_mount() {
		return sample_mount;
	}

	public void setSample_mount(int sample_mount) {
		this.sample_mount = sample_mount;
	}

	public float getCompute_fee() {
		return compute_fee;
	}

	public void setCompute_fee(float compute_fee) {
		this.compute_fee = compute_fee;
	}

	public float getActual_fee() {
		return actual_fee;
	}

	public void setActual_fee(float actual_fee) {
		this.actual_fee = actual_fee;
	}

	private String id;
	private Date start;
	private Date end;
	private String equip_id;
	private String user_id;
	private int type;
	private String user_name;
	private String teacher;
	private String sample_name;
	private int sample_mount;
	private int exp_time;//实验时间min
	private float compute_fee;
	private float actual_fee;
	private String content;
	private String remark;
	private int action;//0 没通过 1通过 2已删除
	private Date input;

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInput() {
		return input;
	}

	public void setInput(Date input) {
		this.input = input;
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Column(name = "equip_id", insertable = false, updatable = false)
	public String getEquip_id() {
		return equip_id;
	}

	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public void setExp_time(int exp_time) {
		this.exp_time = exp_time;
	}

	public int getExp_time() {
		return exp_time;
	}

}
