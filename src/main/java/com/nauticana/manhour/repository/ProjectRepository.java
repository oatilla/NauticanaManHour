package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
