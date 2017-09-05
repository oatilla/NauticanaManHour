package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;

public interface ProjectWbsRepository extends JpaRepository<ProjectWbs, ProjectWbsId> {

}
