package com.ecom.modal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ecom.types.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbl_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false, length = 150)
	private String name;
	
	@Column(name="email", nullable = false, length = 320)
	private String email;
	
	@Column(name="username", nullable = false,unique = true, length = 150)
	private String username;
	
	@JsonIgnore
	@Column(name="password", nullable = false, length = 150)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role",nullable = false,length = 20)
	private Roles role;
	
	@Column(name="account_non_expired", nullable = false, length = 150,columnDefinition = "boolean default true")
	private boolean accountNonExpired=true;
	
	@Column(name="account_non_locked", nullable = false, length = 150,columnDefinition = "boolean default true")
	private boolean accountNonLocked=true;
	
	@Column(name="credentials_non_expire", nullable = false, length = 150,columnDefinition = "boolean default true")
	private boolean credentialsNonExpire=true;
	
	@Column(name="enabled", nullable = false, length = 150,columnDefinition = "boolean default true")
	private boolean enabled=true;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpire() {
		return credentialsNonExpire;
	}

	public void setCredentialsNonExpire(boolean credentialsNonExpire) {
		this.credentialsNonExpire = credentialsNonExpire;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
