package com.yash.shopping.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{


	private static final long serialVersionUID = 8340230236053795959L;

	private Long mobile;

	private String username;

	private String name;

	private String lastname;

	private String email;

	private String password;

	private Date createddate;

	private Date updateddate;

	private int active;

	private String usertype;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long mobile, String username, String name, String lastname, String email, String password,
			Date createddate, Date updateddate, int active, String usertype) {
		super();
		this.mobile = mobile;
		this.username = username;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.createddate = createddate;
		this.updateddate = updateddate;
		this.active = active;
		this.usertype = usertype;
	}

	public Long getMobile() {
		return mobile;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "User [mobile=" + mobile + ", username=" + username + ", name=" + name + ", lastname=" + lastname
				+ ", email=" + email + ", password=" + password + ", createddate=" + createddate + ", updateddate="
				+ updateddate + ", active=" + active + ", usertype=" + usertype + "]";
	}

}
