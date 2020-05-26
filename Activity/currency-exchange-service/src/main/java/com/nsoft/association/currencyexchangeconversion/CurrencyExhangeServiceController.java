package com.nsoft.association.currencyexchangeconversion;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nsoft.association.exception.ResourceNotFound;

@RestController
public class CurrencyExhangeServiceController {
	
	@Autowired
	private ExchangeValueRepositories repository;
	
	@Autowired
	private CurrencyService currencyservice;
	
	@Autowired
	private Environment env;
	//Get Currency Factor 
	
	 private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	@HystrixCommand(fallbackMethod = "myFallbackMethod")
	public ExchangeCurrency retrieveExchangeCurrency(@PathVariable String from , @PathVariable String to)
	
	{
		LOG.info("inside Exchange Service Retrice method");
			
		//Getting value from H2 database using JPA
		ExchangeCurrency exchangecurrncy= repository.findByFromAndTo(from, to);
		
		if (exchangecurrncy==null)
		{
			throw new ResourceNotFound("from or to "+from+ "" +to);
		}
		exchangecurrncy.setPort(env.getProperty("local.server.port"));
		return exchangecurrncy;
		
	}
	
	
	
public ExchangeCurrency myFallbackMethod(@PathVariable String from , @PathVariable String to)
	
	{
		LOG.info("inside Exchange Service myFallbackMethod method");
			
		//Getting value from H2 database using JPA
		ExchangeCurrency exchangecurrncy= new ExchangeCurrency((long) 0,"FallBack","Fallback",BigDecimal.ZERO,to);
		exchangecurrncy.setPort(env.getProperty("local.server.port"));
		return exchangecurrncy;
		
	}
	//Get all
	@GetMapping("/currency-exchange/all")
	public List<ExchangeCurrency> retrieveExchangeCurrency()
	
	{
		LOG.info("inside Exchange Service Get All method");
			
		//Getting value from H2 database using JPA
		List<ExchangeCurrency> exchangecurrncy= repository.findAll();
		
		if (exchangecurrncy==null)
		{
			throw new ResourceNotFound("no records");
		}
		return exchangecurrncy;
		
	}
	
	//Add currency factor
	
	@PostMapping("/currency-exchange/addConversionfector")
    public ResponseEntity <ExchangeCurrency> addConversion(@RequestBody ExchangeCurrency exchangecur) {
      
        
        return ResponseEntity.status(200).body(this.currencyservice.addConversion(exchangecur));
    }
	
	// update Currency factor 
	@PutMapping("/currency-exchange/updateConversionfector/")

    public ResponseEntity <ExchangeCurrency> updateConversionfactor(  @RequestBody ExchangeCurrency exchangecur) {
        
        return ResponseEntity.ok().body(this.currencyservice.updateConversionfactor(exchangecur));
    }
	
	
	//delete currency factor
	
	 @DeleteMapping("/currency-exchange/deleteConversionfector/{id}")
	    public HttpStatus deleteConversionfactor(@PathVariable long id) {
	        this.currencyservice.deleteConversionfactor(id);
	        return HttpStatus.OK;
	    }

}
