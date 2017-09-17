package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.model.ProjectTeamTemplateId;

public interface ProjectTeamTemplateRepository extends JpaRepository<ProjectTeamTemplate, ProjectTeamTemplateId> {

}
