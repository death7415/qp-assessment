package com.qpassessment.qpassessment.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qpassessment.qpassessment.dao.EGroceryJpaRepository;
import com.qpassessment.qpassessment.dao.entities.EGrocery;
import com.qpassessment.qpassessment.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EGroceryJpaRepository eGroceryJpaRepository;

	@Override
	public List<EGrocery> getAllGroceries() {
		return eGroceryJpaRepository.findAll();
	}

	@Override
	@Transactional
	public Map<String, Integer> bookGroceries(Map<String, Integer> order) {
	    try {
	        List<Long> itemIds = order.keySet().stream()
	                .map(Long::valueOf)
	                .collect(Collectors.toList());

	        List<EGrocery> groceries = eGroceryJpaRepository.findAllById(itemIds);

	        for (var grocery : groceries) {
	            String itemId = grocery.getId().toString();
	            Integer orderedQuantity = order.get(itemId);

	            if (grocery.getInventory() >= 0) {
	                if (grocery.getInventory() >= orderedQuantity) {
	                    grocery.setInventory(grocery.getInventory() - orderedQuantity);
	                } else {
	                    order.put(itemId, grocery.getInventory());
	                    grocery.setInventory(0);
	                }
	            } else {
	                order.put(itemId, grocery.getInventory());
	            }
	        }

	        eGroceryJpaRepository.saveAll(groceries);

	    } catch (EntityNotFoundException ex) {
	        throw new RuntimeException("No Such Item Found !!!");
	    }

	    return order;
	}

}
