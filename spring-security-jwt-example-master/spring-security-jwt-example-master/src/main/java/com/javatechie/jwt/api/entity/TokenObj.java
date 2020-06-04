package com.javatechie.jwt.api.entity;

public class TokenObj {
	
	String userToken;
	String transactionToken;
	String serviceToken;
	public TokenObj(String userToken, String transactionToken, String serviceToken) {
		super();
		this.userToken = userToken;
		this.transactionToken = transactionToken;
		this.serviceToken = serviceToken;
	}
	public TokenObj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getTransactionToken() {
		return transactionToken;
	}
	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}
	public String getServiceToken() {
		return serviceToken;
	}
	public void setServiceToken(String serviceToken) {
		this.serviceToken = serviceToken;
	}
	

}
