package com.nsoft.association.currencyconversionservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.nsoft.association.currencyconversionservices")  //Enable Feing
@EnableDiscoveryClient    // Register with Eureka server 
public class CurrencyConversionServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServicesApplication.class, args);
	}

}
