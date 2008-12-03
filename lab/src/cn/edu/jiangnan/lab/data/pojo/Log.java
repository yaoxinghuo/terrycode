package cn.edu.jiangnan.lab.data.pojo;

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
@Table(name = "log")
public class Log implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Date start;
	private Date end;
	private String equip_id;
	private String admin_id;
	private String user_id;
	private String equip_name;
	private String equip_no;
	private String admin_name;
	private String user_name;
	private String teacher;
	private String content;
	private String remark;
	private String admin_remark;
	private boolean confirm;
	private Date input;
	private Date user_input;
	private boolean charge;
	private int action;// 0不通过，1通过批准，2删除 ，3修改实验费用
	
	private String sample_name;
	private int sample_mount;
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

	public int getExp_time() {
		return exp_time;
	}

	public void setExp_time(int exp_time) {
		this.exp_time = exp_time;
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

	private int exp_time;//实验时间min
	private float compute_fee;
	private float actual_fee;

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
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

	public String getEquip_id() {
		return equip_id;
	}

	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEquip_name() {
		return equip_name;
	}

	public void setEquip_name(String equip_name) {
		this.equip_name = equip_name;
	}

	public String getEquip_no() {
		return equip_no;
	}

	public void setEquip_no(String equip_no) {
		this.equip_no = equip_no;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getInput() {
		return input;
	}

	public void setInput(Date input) {
		this.input = input;
	}

	public String getAdmin_remark() {
		return admin_remark;
	}

	public void setAdmin_remark(String admin_remark) {
		this.admin_remark = admin_remark;
	}

	public Date getUser_input() {
		return user_input;
	}

	public void setUser_input(Date user_input) {
		this.user_input = user_input;
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

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

}
