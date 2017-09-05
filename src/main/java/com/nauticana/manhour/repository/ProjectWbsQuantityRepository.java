package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;

public interface ProjectWbsQuantityRepository extends JpaRepository<ProjectWbsQuantity, ProjectWbsQuantityId> {

}
