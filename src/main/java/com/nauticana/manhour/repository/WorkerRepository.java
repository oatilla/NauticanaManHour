package com.nauticana.manhour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

	Worker findByPersonId(Integer personId);

	List<Worker> findBySubcontractor(Subcontractor subcontractor);
}
