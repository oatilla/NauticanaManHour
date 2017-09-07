package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;

@Service
public class ProjectWbsQuantityService extends AbstractService<ProjectWbsQuantity,ProjectWbsQuantityId> {

	@Override
	public String[] getFieldNames() {
		return ProjectWbsQuantity.fieldNames;
	}

	@Override
	public ProjectWbsQuantity newEntity() {
		return new ProjectWbsQuantity();
	}

	@Override
	public ProjectWbsQuantityId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Short.parseShort(s[2]),s[3].charAt(0),Short.parseShort(s[2]));
	}

}
