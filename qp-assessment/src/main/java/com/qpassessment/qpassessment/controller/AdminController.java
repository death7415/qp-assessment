package com.qpassessment.qpassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qpassessment.qpassessment.dao.entities.EGrocery;
import com.qpassessment.qpassessment.services.ServiceFactory;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ServiceFactory serviceFactory;
	
	@GetMapping("/v1/getAllGroceries")
	public List<EGrocery> getAllItems(){
		return serviceFactory.getAdminService().getAllGroceryItems();
	}
	
	@PostMapping("/v1/addItem")
	public ResponseEntity<EGrocery> addGrocery(@RequestBody EGrocery grocery, @RequestParam int quantity) {
		return new ResponseEntity<EGrocery> (serviceFactory.getAdminService().addGroceryItem(grocery, quantity), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/v1/removeGroceryItem/{itemId}")
    public ResponseEntity<String> removeGroceryItem(@PathVariable("itemId") Long itemId) {
		try {
			serviceFactory.getAdminService().removeGroceryItem(itemId);
			return new ResponseEntity<String>("Removed!!!", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }

    @PutMapping("/v1/updateGroceryItem/{id}")
    public ResponseEntity<String> updateGroceryItem(@PathVariable("id") Long id, @RequestBody EGrocery updatedItem) {
    	try {
    		serviceFactory.getAdminService().updateGroceryItem(id, updatedItem);
    		return new ResponseEntity<String>("Updated!!", HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
}
