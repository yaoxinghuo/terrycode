package cn.edu.jiangnan.lab.data.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "equip", uniqueConstraints = { @UniqueConstraint(columnNames = { "no" }) })
public class Equip implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Book> books = new ArrayList<Book>();

	private String id;
	private String no;
	private String name;
	private String model;
	private String specification;// 规格
	private String country;
	private String company;
	private String year1;
	private String year2;
	private String price;
	private float fee;//每分钟收费多少钱
	private int sampletime;//每个样品需要多少时间（分钟）
	private String location;
	private String admin;// 负责人
	private String mobile;// 负责人联系方式
	private String remark;
	private String caution;
	private int type;
	private boolean status;
	private boolean pub;
	private boolean charge;
	private int counter;

	private String appt1;// 允许预约的最早时间
	private String appt2;// 允许预约的最晚时间
	private String appd;// 不允许预约周期[0,6]代表不允许周日0和周六6预约,
	//不过在Java的Calendar的Field里，DAY_OF_WEEK
	//周日是1 周一2……周六7
	private boolean checkd;// 是否检查同一台设备同一时间被多个人预约
	
	private String image;//照片保存名字8a96823b194ae61a01194b17e2890006.jpg

	public String getAppt1() {
		return appt1;
	}

	public void setAppt1(String appt1) {
		this.appt1 = appt1;
	}

	public String getAppt2() {
		return appt2;
	}

	public void setAppt2(String appt2) {
		this.appt2 = appt2;
	}

	public String getAppd() {
		return appd;
	}

	public void setAppd(String appd) {
		this.appd = appd;
	}

	public boolean isCheckd() {
		return checkd;
	}

	public void setCheckd(boolean checkd) {
		this.checkd = checkd;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	public boolean isCharge() {
		return charge;
	}

	public void setCharge(boolean charge) {
		this.charge = charge;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Book.class)
	@Cascade(value = { org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
			org.hibernate.annotations.CascadeType.ALL })
	@JoinColumn(name = "equip_id")
	public List<Book> getBooks() {
		return books;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public float getFee() {
		return fee;
	}

	public void setSampletime(int sampletime) {
		this.sampletime = sampletime;
	}

	public int getSampletime() {
		return sampletime;
	}

}
