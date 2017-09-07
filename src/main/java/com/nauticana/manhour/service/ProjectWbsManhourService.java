package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;

@Service
public class ProjectWbsManhourService extends AbstractService<ProjectWbsManhour,ProjectWbsManhourId> {

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbsManhour.fieldNames;
	}

	@Override
	public ProjectWbsManhour newEntity() {
		return new ProjectWbsManhour();
	}

	@Override
	public ProjectWbsManhourId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsManhourId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),null);
	}

}
