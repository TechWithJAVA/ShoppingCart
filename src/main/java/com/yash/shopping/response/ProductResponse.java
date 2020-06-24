package com.yash.shopping.response;

import java.util.List;

import com.yash.shopping.model.Product;



public class ProductResponse {

	private String status;
	private String message;
	private Iterable<Product> oblist;
	public ProductResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductResponse(String status, String message, List<Product> oblist) {
		super();
		this.status = status;
		this.message = message;
		this.oblist = oblist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Iterable<Product> getOblist() {
		return oblist;
	}
	public void setOblist(Iterable<Product> oblist) {
		this.oblist = oblist;
	}
	@Override
	public String toString() {
		return "ProductResponse [status=" + status + ", message=" + message + ", oblist=" + oblist + "]";
	}
	
	
}
