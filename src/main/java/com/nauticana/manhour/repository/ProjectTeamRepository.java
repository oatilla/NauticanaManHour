package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;

public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, ProjectTeamId> {

}
