package com.qpassessment.qpassessment.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qpassessment.qpassessment.dao.entities.EGrocery;

@Repository
public interface EGroceryJpaRepository extends JpaRepository<EGrocery, Long> {

	Optional<EGrocery> findByName(String name);

}
