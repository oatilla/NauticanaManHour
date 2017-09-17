package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.model.ProjectTeamTemplateId;
import com.nauticana.manhour.repository.ProjectTeamRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectTeamTemplateService extends AbstractService<ProjectTeamTemplate,ProjectTeamTemplateId> {

	@Override
	public String[] getFieldNames() {
		return ProjectTeamTemplate.fieldNames;
	}

	@Autowired
	ProjectTeamRepository parentRep;

	@Override
	public ProjectTeamTemplate newEntity(String parentKey) {
		ProjectTeamTemplate entity = new ProjectTeamTemplate();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamTemplateId id = new ProjectTeamTemplateId();
			ProjectTeamId parentId = new ProjectTeamId(parentKey);
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			entity.setProjectTeam(parentRep.findOne(parentId));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectTeamTemplateId StrToId(String id) {
		return new ProjectTeamTemplateId(id);
	}

	@Override
	public ProjectTeamTemplate newEntityWithId(String strId) {
		ProjectTeamTemplate entity = new ProjectTeamTemplate();
		entity.setId(StrToId(strId));
		return entity;
	}
}
