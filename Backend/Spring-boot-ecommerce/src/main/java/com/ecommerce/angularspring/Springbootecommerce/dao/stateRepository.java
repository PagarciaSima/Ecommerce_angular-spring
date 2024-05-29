package com.ecommerce.angularspring.Springbootecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.angularspring.Springbootecommerce.entity.State;

public interface stateRepository extends JpaRepository<State, Integer> {
	
	List<State> findByCountryCode(@Param("code") String code);
}
