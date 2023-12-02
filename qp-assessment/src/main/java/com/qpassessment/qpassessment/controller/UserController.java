package com.qpassessment.qpassessment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qpassessment.qpassessment.dao.entities.EGrocery;
import com.qpassessment.qpassessment.services.ServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	@GetMapping("/v1/getAllGroceries")
	public List<EGrocery> getAllGroceries(){
		return serviceFactory.getUserService().getAllGroceries();
	}
	
	@PostMapping("/v1/order")
	@Operation(summary = "Book groceries")
	public ResponseEntity<?> bookGroceries(@RequestBody @Valid @Schema( example = "{\"1\": 10, \"2\": 20}") Map<String, Integer> orders){
		try {
			var response = serviceFactory.getUserService().bookGroceries(orders);
			return new ResponseEntity<Map<String,Integer>>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
