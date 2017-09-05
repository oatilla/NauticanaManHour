package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;

@Service
public class ProjectTeamPersonService extends AbstractService<ProjectTeamPerson,ProjectTeamPersonId> {

	@Override
	public String[] getFieldNames() {
		return ProjectTeamPerson.fieldNames;
	}

	@Override
	public ProjectTeamPerson newEntity() {
		return new ProjectTeamPerson();
	}

	@Override
	public ProjectTeamPerson findStrId(String id) {
		String[] s = id.split(",");
		return findById(new ProjectTeamPersonId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2])));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new ProjectTeamPersonId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2])));
		
	}
}
