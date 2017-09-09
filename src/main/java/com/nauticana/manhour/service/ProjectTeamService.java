package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.repository.ProjectRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectTeamService extends AbstractService<ProjectTeam,ProjectTeamId> {


	@Override
	public String[] getFieldNames() {
		return ProjectTeam.fieldNames;
	}

	@Autowired
	ProjectRepository parentRep;

	@Autowired
	UtilService urepRep;

	@Override
	public ProjectTeam newEntity(String parentKey) {
		ProjectTeam entity = new ProjectTeam();
		ProjectTeamId id = new ProjectTeamId();
		id.setProjectId(Integer.parseInt(parentKey));
		id.setTeamId(urepRep.nextTeamId(id.getProjectId()));
		entity.setId(id);
		if (!Utils.emptyStr(parentKey)) entity.setProject(parentRep.findOne(id.getProjectId()));
		return entity;
	}

	@Override
	public ProjectTeamId StrToId(String id) {
		return new ProjectTeamId(id);
	}

}
