package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.ProjectWbs;
import com.gama.manhour.model.ProjectWbsId;

public interface ProjectWbsRepository extends JpaRepository<ProjectWbs, ProjectWbsId> {

}
