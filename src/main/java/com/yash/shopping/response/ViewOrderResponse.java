package com.yash.shopping.response;

import java.util.ArrayList;
import java.util.List;

import com.yash.shopping.model.Order;

public class ViewOrderResponse {

	private String status;
	private String message;
	private List<Order> orderlist = new ArrayList<>();

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

	public List<Order> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}

	@Override
	public String toString() {
		return "ViewOrderResponse [status=" + status + ", message=" + message + ", orderlist=" + orderlist + "]";
	}
	
	
}
