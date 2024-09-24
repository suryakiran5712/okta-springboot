package com.cg.api.gateway.controller;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.api.gateway.model.TokenModel;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
@Validated
public class Controller {

	
    @Value("${backend.service.url}")
    private String backendServiceUrl;
    
    @Value("${backend.service.client-id}")
    private String backendServiceClientId;
    
    @Value("${backend.service.client-secret}")
    private String backendServiceClientSecret;
    
    @Value("${backend.service.token-issuer}")
    private String backendServiceToken;
	
	 @GetMapping("/**")
	    public ResponseEntity<?> forwardRequest(HttpServletRequest request) {
	        String url = backendServiceUrl + request.getRequestURI();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(getToken());
	        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
	        return response;
	    }
	 public String getToken()
	 {
		 String token = "";
		 RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "application/x-www-form-urlencoded");
	        headers.setBasicAuth(backendServiceClientId, backendServiceClientSecret) ; 
	        String requestBody = "grant_type=client_credentials&scope=okta_integration";
	        RequestEntity<String> requestEntity;
			try {
				requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, new URI(backendServiceToken));
				ResponseEntity<TokenModel> response = restTemplate.exchange(requestEntity, TokenModel.class);
		        if (response.getStatusCode() == HttpStatus.OK) {
		        	token=response.getBody().getAccess_token();
		        } else {
		            System.out.println("Failed with status code: " + response.getStatusCode());
		        }
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		 return token;
	 }
}
