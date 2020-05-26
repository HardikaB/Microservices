package com.nsoft.association.currencyexchangeconversion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepositories extends JpaRepository<ExchangeCurrency, Long> {
	
	
	ExchangeCurrency findByFromAndTo(String from , String to);
}
