package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.repository.ProjectRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectWbsService extends AbstractService<ProjectWbs,ProjectWbsId> {

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbs.fieldNames;
	}

	@Autowired
	ProjectRepository parentRep;

	@Override
	public ProjectWbs newEntity(String parentKey) {
		ProjectWbs entity = new ProjectWbs();
		if (!Utils.emptyStr(parentKey)) {
			ProjectWbsId id = new ProjectWbsId();
			id.setProjectId(Integer.parseInt(parentKey));
			entity.setId(id);
			entity.setProject(parentRep.findOne(id.getProjectId()));
		}
		return entity;
	}

	@Override
	public ProjectWbsId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsId(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
	}

	@Override
	public ProjectWbs newEntityWithId(String strId) {
		ProjectWbs entity = new ProjectWbs();
		entity.setId(StrToId(strId));
		return entity;
	}

}
