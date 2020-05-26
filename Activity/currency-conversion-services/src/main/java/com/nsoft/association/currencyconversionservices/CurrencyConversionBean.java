package com.nsoft.association.currencyconversionservices;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	
	private Long id;
	private String from;	
	private String to;
	private BigDecimal conversionMultiple;
	private String port;
	private BigDecimal quantity;
	private BigDecimal totalCalculatedamout;
	
	
	
	
	
   public CurrencyConversionBean() {
		
	}

public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple, String port, BigDecimal quantity,
			BigDecimal totalCalculatedamout) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.port = port;
		this.quantity = quantity;
		this.totalCalculatedamout = totalCalculatedamout;
	}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getFrom() {
	return from;
}

public void setFrom(String from) {
	this.from = from;
}

public String getTo() {
	return to;
}

public void setTo(String to) {
	this.to = to;
}

public BigDecimal getConversionMultiple() {
	return conversionMultiple;
}

public void setConversionMultiple(BigDecimal conversionMultiple) {
	this.conversionMultiple = conversionMultiple;
}

public String getPort() {
	return port;
}

public void setPort(String port) {
	this.port = port;
}

public BigDecimal getQuantity() {
	return quantity;
}

public void setQuantity(BigDecimal quantity) {
	this.quantity = quantity;
}

public BigDecimal getTotalCalculatedamout() {
	return totalCalculatedamout;
}

public void setTotalCalculatedamout(BigDecimal totalCalculatedamout) {
	this.totalCalculatedamout = totalCalculatedamout;
}
	
	
	
	
}
