package com.nsoft.association.currencyexchangeconversion;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nsoft.association.exception.ResourceNotFound;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {
	
	 @Autowired
	    private ExchangeValueRepositories exchangeepository;
	 
	 @Override
	    public ExchangeCurrency addConversion(ExchangeCurrency currency) {
		 System.out.println("Record to be inserted"+currency);
	        return exchangeepository.save(currency);
	    }
	 
	 @Override
	 public ExchangeCurrency updateConversionfactor(ExchangeCurrency currency)
	 {
		    Optional <ExchangeCurrency> exchangeId=this.exchangeepository.findById(currency.getId());
		 
		   if (exchangeId.isPresent())
		   {
			   ExchangeCurrency updateExchange = exchangeId.get();
			   
			   updateExchange.setFrom(currency.getFrom());
			   updateExchange.setTo(currency.getTo());
			   updateExchange.setConversionMultiple(currency.getConversionMultiple());
			   this.exchangeepository.save(updateExchange);
			   return updateExchange;
		   }
		   else
			 //  System.out.println("Record not found with id : " + currency.getId());
			   throw new ResourceNotFound("Record not found with id : " + currency.getId());
		 
	 }
	 
	 public void deleteConversionfactor(long id)
	 {
		 Optional <ExchangeCurrency> exchangeId=exchangeepository.findById(id);
		 
		 if (exchangeId.isPresent())
		   {
			 this.exchangeepository.deleteById(id);
		   }
		 else{
			 throw new ResourceNotFound("Record not found with id : " + id);}
		 
	 }
	 
	 

}
