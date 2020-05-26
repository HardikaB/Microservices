package com.nsoft.association.currencyconversionservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="netflix-zull-gateway-server")      // Invoke the client through Zull API gateway
@RibbonClient(name="currency-exchange-service")   // Ribbon is used for Client side load balancing
public interface CurrencyConversionProxy {
	
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrieveExchangeCurrency(@PathVariable("from") String from , @PathVariable("to") String to);
	
	
	
}
