package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.masai.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>, PagingAndSortingRepository<Category, Integer> {

}
