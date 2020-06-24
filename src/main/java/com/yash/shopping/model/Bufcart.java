package com.yash.shopping.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bufcart implements Serializable {

	private static final long serialVersionUID = 8539629280174208086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bufcartId;

	@Column(nullable = true)
	private int orderId;

	private Long mobile;

	private String email;

	private Date dateAdded;

	private int quantity;

	private double price;

	private int productId;

	private String productname;

	public Bufcart() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Bufcart(int bufcartId, int orderId, Long mobile, String email, Date dateAdded, int quantity, double price,
			int productId, String productname) {
		super();
		this.bufcartId = bufcartId;
		this.orderId = orderId;
		this.mobile = mobile;
		this.email = email;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.productname = productname;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getBufcartId() {
		return bufcartId;
	}

	public void setBufcartId(int bufcartId) {
		this.bufcartId = bufcartId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Long getMobile() {
		return mobile;
	}



	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "Bufcart [bufcartId=" + bufcartId + ", orderId=" + orderId + ", mobile=" + mobile + ", email=" + email
				+ ", dateAdded=" + dateAdded + ", quantity=" + quantity + ", price=" + price + ", productId="
				+ productId + ", productname=" + productname + "]";
	}

}
