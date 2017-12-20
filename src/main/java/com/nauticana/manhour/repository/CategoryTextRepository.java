package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.CategoryText;
import com.nauticana.manhour.model.CategoryTextId;

public interface CategoryTextRepository extends JpaRepository<CategoryText, CategoryTextId> {

}
