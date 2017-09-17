package com.nauticana.manhour.service;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;
import com.nauticana.manhour.repository.ProjectWbsRepository;
import com.nauticana.manhour.utils.Labels;
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
		if (!Utils.emptyStr(parentKey)) {
			ProjectWbsId parentId = new ProjectWbsId(parentKey);
			ProjectWbsQuantityId id = new ProjectWbsQuantityId();
			id.setProjectId(parentId.getProjectId());
			id.setCategoryId(parentId.getCategoryId());
			entity.setId(id);
			entity.setProjectWbs(parentRep.findOne(parentId));
		}
		return entity;
	}

	@Override
	public ProjectWbsQuantityId StrToId(String id) {
		String[] s = id.split(",");
		try {
			return new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]), Labels.dmyDF.parse(s[2]));
		} catch (NumberFormatException | ParseException e) {
			return null;
		}
	}

	@Override
	public ProjectWbsQuantity newEntityWithId(String strId) {
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		entity.setId(StrToId(strId));
		return entity;
	}

}
