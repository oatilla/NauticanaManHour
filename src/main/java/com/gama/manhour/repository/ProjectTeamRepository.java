package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.ProjectTeam;
import com.gama.manhour.model.ProjectTeamId;

public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, ProjectTeamId> {

}
