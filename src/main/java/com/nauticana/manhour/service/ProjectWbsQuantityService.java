package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;
import com.nauticana.manhour.repository.ProjectWbsRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectWbsQuantityService extends AbstractService<ProjectWbsQuantity,ProjectWbsQuantityId> {

	@Override
	public String[] getFieldNames() {
		return ProjectWbsQuantity.fieldNames;
	}

	@Autowired
	ProjectWbsRepository parentRep;

	@Override
	public ProjectWbsQuantity newEntity(String parentKey) {
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		if (!Utils.emptyStr(parentKey)) entity.setProjectWbs(parentRep.findOne(new ProjectWbsId(parentKey)));
		return entity;
	}

	@Override
	public ProjectWbsQuantityId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Short.parseShort(s[2]),s[3].charAt(0),Short.parseShort(s[2]));
	}

}
