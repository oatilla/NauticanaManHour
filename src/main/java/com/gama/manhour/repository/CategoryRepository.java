package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
