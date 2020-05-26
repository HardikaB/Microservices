package com.nsoft.association.currencyconversionservices;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyCalculationCotroller {
	 
	@Autowired
      CurrencyConversionProxy proxy;
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	   public CurrencyConversionBean convertCurrency(@PathVariable String from, 
			   @PathVariable String to,
			   @PathVariable  BigDecimal quantity){
	   
		      LOG.info("Inside Currency Conversion");
		      CurrencyConversionBean response = new CurrencyConversionBean();
		     // for(int i=0;i<100;i++)
		       response=proxy.retrieveExchangeCurrency(from, to);
		      

		     
		      
		      
	   return new  CurrencyConversionBean(response.getId(), response.getFrom(), response.getTo(),
			   response.getConversionMultiple(),response.getPort(),quantity,quantity.multiply(response.getConversionMultiple()) );
	  }
}
