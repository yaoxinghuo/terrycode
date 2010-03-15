package com.terry.weddingphoto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
 * @version create: 2010-3-15 上午10:12:16
 */
@Entity
public class Photo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3719075717115159161L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	@Enumerated
	private String filename;
	@Enumerated
	private long size;
	@Temporal(TemporalType.TIMESTAMP)
	private Date cdate;
	@Enumerated
	private String remark;
	@Enumerated
	private int width;
	@Enumerated
	private int height;
	@Enumerated
	private boolean comment;// 是否允许评论
	@Enumerated
	private Blob data;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	private Thumb thumb;

	public Thumb getThumb() {
		return thumb;
	}

	public void setThumb(Thumb thumb) {
		this.thumb = thumb;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public Blob getData() {
		return data;
	}

	public void setComment(boolean comment) {
		this.comment = comment;
	}

	public boolean isComment() {
		return comment;
	}
}
