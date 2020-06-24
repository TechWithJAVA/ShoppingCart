package com.yash.shopping.response;

import java.util.Optional;

import com.yash.shopping.model.Bufcart;

public class CartResponse {
	private String status;
	private String message;
	private Optional<Bufcart> oblist;

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

	
	public Optional<Bufcart> getOblist() {
		return oblist;
	}

	public void setOblist(Optional<Bufcart> oblist) {
		this.oblist = oblist;
	}

}