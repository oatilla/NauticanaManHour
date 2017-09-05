package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;

@Service
public class ProjectWbsQuantityService extends AbstractService<ProjectWbsQuantity,ProjectWbsQuantityId> {

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Short.parseShort(s[2]),s[3].charAt(0),Short.parseShort(s[2])));
		
	}
	@Override
	public String[] getFieldNames() {
		return ProjectWbsQuantity.fieldNames;
	}

	@Override
	public ProjectWbsQuantity newEntity() {
		return new ProjectWbsQuantity();
	}

	@Override
	public ProjectWbsQuantity findStrId(String id) {
		String[] s = id.split(",");
		return findById(new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Short.parseShort(s[2]),s[3].charAt(0),Short.parseShort(s[2])));


	}

}
