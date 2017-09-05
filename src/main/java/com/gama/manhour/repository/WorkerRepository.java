package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

}
