package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;

public interface ProjectTeamPersonRepository extends JpaRepository<ProjectTeamPerson, ProjectTeamPersonId> {

}
