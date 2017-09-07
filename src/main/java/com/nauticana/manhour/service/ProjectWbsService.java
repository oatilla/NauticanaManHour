package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;

@Service
public class ProjectWbsService extends AbstractService<ProjectWbs,ProjectWbsId> {

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbs.fieldNames;
	}

	@Override
	public ProjectWbs newEntity() {
		return new ProjectWbs();
	}

	@Override
	public ProjectWbsId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsId(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
	}

}
