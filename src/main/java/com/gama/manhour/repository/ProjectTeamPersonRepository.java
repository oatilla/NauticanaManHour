package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.ProjectTeamPerson;
import com.gama.manhour.model.ProjectTeamPersonId;

public interface ProjectTeamPersonRepository extends JpaRepository<ProjectTeamPerson, ProjectTeamPersonId> {

}
