package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.repository.ProjectTeamRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectTeamPersonService extends AbstractService<ProjectTeamPerson,ProjectTeamPersonId> {

	@Override
	public String[] getFieldNames() {
		return ProjectTeamPerson.fieldNames;
	}

	@Autowired
	ProjectTeamRepository parentRep;

	@Override
	public ProjectTeamPerson newEntity(String parentKey) {
		ProjectTeamPerson entity = new ProjectTeamPerson();
		if (!Utils.emptyStr(parentKey)) entity.setProjectTeam(parentRep.findOne(new ProjectTeamId(parentKey)));
		return entity;
	}

	@Override
	public ProjectTeamPersonId StrToId(String id) {
		return new ProjectTeamPersonId(id);
	}
}
