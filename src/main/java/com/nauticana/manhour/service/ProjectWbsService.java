package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;

@Service
public class ProjectWbsService extends AbstractService<ProjectWbs,ProjectWbsId> {

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new ProjectWbsId(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
	

	}

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
	public ProjectWbs findStrId(String id) {
		String[] s = id.split(",");
		return findById(new ProjectWbsId(Integer.parseInt(s[0]),Integer.parseInt(s[1])));

	}

}
