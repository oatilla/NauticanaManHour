package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.ProjectWbsQuantity;
import com.gama.manhour.model.ProjectWbsQuantityId;

public interface ProjectWbsQuantityRepository extends JpaRepository<ProjectWbsQuantity, ProjectWbsQuantityId> {

}
