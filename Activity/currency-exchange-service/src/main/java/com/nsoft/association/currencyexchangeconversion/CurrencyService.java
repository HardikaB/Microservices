package com.nsoft.association.currencyexchangeconversion;

public interface CurrencyService {
	
	
	ExchangeCurrency addConversion(ExchangeCurrency addexchangecur);
	ExchangeCurrency updateConversionfactor(ExchangeCurrency updatexchangecur);
	void deleteConversionfactor(long id);

}
