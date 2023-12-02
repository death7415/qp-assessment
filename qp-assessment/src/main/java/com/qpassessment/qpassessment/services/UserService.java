package com.qpassessment.qpassessment.services;

import java.util.List;
import java.util.Map;

import com.qpassessment.qpassessment.dao.entities.EGrocery;

public interface UserService {
	List<EGrocery> getAllGroceries();
	public Map<String, Integer> bookGroceries(Map<String, Integer> order);
}
