package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

}
