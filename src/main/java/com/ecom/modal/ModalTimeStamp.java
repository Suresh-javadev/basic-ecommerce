package com.ecom.modal;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ModalTimeStamp {

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(name="created_at")
	private Date createdDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	@Column(name="last_modified_at")
	
	private Date lastModifiedDate;
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
