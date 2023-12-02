package com.qpassessment.qpassessment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.qpassessment.qpassessment.dao.EGroceryJpaRepository;
import com.qpassessment.qpassessment.dao.entities.EGrocery;
import com.qpassessment.qpassessment.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private EGroceryJpaRepository groceryJpaRepository;

	@Override
	public List<EGrocery> getAllGroceryItems() {
		return groceryJpaRepository.findAll();
	}

	@Override
	public EGrocery addGroceryItem(EGrocery grocery, int quantity) {
		Optional<EGrocery> existingItem = groceryJpaRepository.findByName(grocery.getName());

        if (existingItem.isPresent()) {
            EGrocery currentItem = existingItem.get();
            currentItem.setInventory(currentItem.getInventory() + quantity);
            return groceryJpaRepository.save(currentItem);
        } else {
            grocery.setInventory(quantity);
            return groceryJpaRepository.save(grocery);
        }
	}

	@Override
	public void updateGroceryItem(Long itemId, EGrocery grocery) {
		try {
			var existingGroceryItem = groceryJpaRepository.findById(itemId);
			if(existingGroceryItem.isPresent()) {
			grocery.setId(itemId);
			groceryJpaRepository.save(grocery);
			}else {
				throw new RuntimeException("No Such Item Found !!!");
			}
		} catch (DataAccessException e) {
			throw new RuntimeException("No Such Item Found !!!");
		}
	}

	@Override
	public void removeGroceryItem(Long itemId) {
		try {
			var existingGroceryItem = groceryJpaRepository.findById(itemId);
			if(existingGroceryItem.isPresent()) {
				groceryJpaRepository.deleteById(itemId);
			}else {
				throw new RuntimeException("No Such Item Found !!!");
			}
		} catch (DataAccessException e) {
			throw new RuntimeException("No Such Item Found !!!");
		}
	}

}
