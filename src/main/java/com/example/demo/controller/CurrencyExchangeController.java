package com.example.demo.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.ExchangeValue;
import com.example.demo.exception.RequestedResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.ExchangeValueRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment env;
	
	@Autowired
	ExchangeValueRepository exchangeValueRepository;
	
	@RequestMapping(method=RequestMethod.GET,path="/CurrencyExchange/from/{from}/to/{to}", produces="application/hal+json")
	@Operation(method="HTTP.GET",summary="Get the exchange value of currencies",description="This handle fetches the exchange value for the input params from the exchange_value table")
	@ApiResponses({
		@ApiResponse(responseCode="200",description="OK"),
		@ApiResponse(responseCode="201",description="Created"),
		@ApiResponse(responseCode="400",description="Bad Request"),
		@ApiResponse(responseCode="404",description="not found"),
		@ApiResponse(responseCode="500",description="internal server error")
	})
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		validateFromAndTo(from,to);
		ExchangeValue exchangeValue =  exchangeValueRepository.findByFromAndTo(from, to);
		if(exchangeValue!=null) {
			exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		}else {
			throw new RequestedResourceNotFoundException("Resource Not Found -> /CurrencyExchange/from/"+from+"/to/"+to);
		}
		return exchangeValue;
	}
	
	private void validateFromAndTo(String from, String to) {
		if(from.equalsIgnoreCase(to))
			throw new ValidationException("form and to cannot be equal");		
	}

	@RequestMapping(method=RequestMethod.POST,path="/CurrencyExchange", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Operation(method="HTTP.GET",summary="Post the exchange value of currencies",description="This handle saves the exchange value for the request to the exchange_value table")
	@ApiResponses({
		@ApiResponse(responseCode="200",description="OK"),
		@ApiResponse(responseCode="201",description="Created"),
		@ApiResponse(responseCode="400",description="Bad Request"),
		@ApiResponse(responseCode="404",description="not found"),
		@ApiResponse(responseCode="500",description="internal server error")
	})
	public ResponseEntity<Object> save(@RequestBody ExchangeValue exchangeValue) {
		ExchangeValue savedExchangeValue = exchangeValueRepository.saveAndFlush(exchangeValue);
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", savedExchangeValue.getFrom());
		uriVariables.put("to", savedExchangeValue.getTo());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/from/{from}/to/{to}").build(uriVariables);
		
		return ResponseEntity.created(location).build();
	}
}
