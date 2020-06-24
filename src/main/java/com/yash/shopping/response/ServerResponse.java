package com.yash.shopping.response;

public class ServerResponse {

	private String status;
	private String message;
	private Object object;
	private String userType;

	public ServerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServerResponse(String status, String message, Object object, String userType) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
		this.userType = userType;
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

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
