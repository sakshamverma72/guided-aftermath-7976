package com.masai.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>, JpaRepository<Customer, Integer> {
	public Optional<Customer> findByEmail(String email);
}
