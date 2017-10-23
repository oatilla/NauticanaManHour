package com.nauticana.manhour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.Subcontractor;

public interface SubcontractorRepository extends JpaRepository<Subcontractor, Integer> {

	List<Subcontractor> findByExtSubcontractor(String extSubcontractor);
}
