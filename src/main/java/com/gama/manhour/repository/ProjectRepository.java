package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
