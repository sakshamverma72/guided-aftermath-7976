package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer> {

}
