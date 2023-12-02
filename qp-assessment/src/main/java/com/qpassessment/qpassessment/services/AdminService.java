package com.qpassessment.qpassessment.services;

import java.util.List;

import com.qpassessment.qpassessment.dao.entities.EGrocery;

public interface AdminService {
	 public List<EGrocery> getAllGroceryItems();
	 public EGrocery addGroceryItem(EGrocery grocery, int quantity);
	 public void updateGroceryItem(Long itemId, EGrocery grocery);
	 public void removeGroceryItem(Long itemId);
}
