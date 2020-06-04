package com.javatechie.jwt.api.controller;

import com.javatechie.jwt.api.entity.TokenObj;
import com.javatechie.jwt.api.entity.User;
import com.javatechie.jwt.api.util.JwtUtil;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public TokenObj generateToken(@RequestBody User user) throws Exception {
        try {
    		System.out.println("Calling IBM IAM Service------");

    		RestTemplate restTemplate = new RestTemplate();
    		HttpHeaders headers = new HttpHeaders();
    		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    		String access_token_url = "https://iam.cloud.ibm.com/identity/token";
    			

    		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    		requestBody.add("grant_type", "urn:ibm:params:oauth:grant-type:apikey");
    		requestBody.add("apikey", user.getApikey());

    		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

    		Object response1 = restTemplate.postForObject(access_token_url, httpEntity, Object.class);
    		if(response1 == null)
    			return null;
    	
    		Map<String, Object> map = (Map<String, Object>)response1;
    		map.forEach((k, v) -> System.out.println(k+": "+v));
    		
        	String accessToken = (String) map.get("access_token");
        	//Generate Token
        	String transactionToken=jwtUtil.generateToken(accessToken);
        	String serviceToken=jwtUtil.generateToken(accessToken);
        	//Encoded User Object
        	String userdetails1=user.getId()+"###"+user.getUserName()+"###"+user.getPassword()+"###"+user.getApikey();
        	System.out.println("Access Token Response ---------" +userdetails1);
        	String userToken = new String(Base64.encodeBase64String(userdetails1.getBytes()));
        	System.out.println("Access Token Response ---------" +userToken);
        	
    		TokenObj tokenobj =new TokenObj(userToken,transactionToken,serviceToken);
            
            return tokenobj;
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
    } 
}
