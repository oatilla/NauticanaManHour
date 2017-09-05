package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.ProjectWbsManhour;
import com.gama.manhour.model.ProjectWbsManhourId;

public interface ProjectWbsManhourRepository extends JpaRepository<ProjectWbsManhour, ProjectWbsManhourId> {

}
