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
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamPersonId id = new ProjectTeamPersonId();
			ProjectTeamId parentId = new ProjectTeamId(parentKey);
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			entity.setProjectTeam(parentRep.findOne(parentId));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectTeamPersonId StrToId(String id) {
		return new ProjectTeamPersonId(id);
	}

	@Override
	public ProjectTeamPerson newEntityWithId(String strId) {
		ProjectTeamPerson entity = new ProjectTeamPerson();
		entity.setId(StrToId(strId));
		return entity;
	}
}
