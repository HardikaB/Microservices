package com.nsoft.association.currencyexchangeconversion;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exchange_currency")
public class ExchangeCurrency {
	
	@Id
	
	private Long id;
	
	@Column(name="currency_from")
	private String from;
	
	@Column(name="currency_to")
	private String to;
	@Column(name="conversion")
	private BigDecimal conversionMultiple;
	
	private String port;
	
	public ExchangeCurrency()
	{
		
	}
	public ExchangeCurrency(Long id, String from, String to, BigDecimal conversionMultiple, String port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.port = port;
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

	
	


}
