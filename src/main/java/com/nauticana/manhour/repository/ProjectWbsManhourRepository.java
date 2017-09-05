package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;

public interface ProjectWbsManhourRepository extends JpaRepository<ProjectWbsManhour, ProjectWbsManhourId> {

}
