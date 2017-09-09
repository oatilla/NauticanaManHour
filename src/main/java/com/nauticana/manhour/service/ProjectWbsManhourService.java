package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;
import com.nauticana.manhour.repository.ProjectTeamPersonRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectWbsManhourService extends AbstractService<ProjectWbsManhour,ProjectWbsManhourId> {

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbsManhour.fieldNames;
	}

	@Autowired
	ProjectTeamPersonRepository parentRep;

	@Override
	public ProjectWbsManhour newEntity(String parentKey) {
		ProjectWbsManhour entity = new ProjectWbsManhour();
		if (!Utils.emptyStr(parentKey)) entity.setProjectTeamPerson(parentRep.findOne(new ProjectTeamPersonId(parentKey)));
		return entity;
	}

	@Override
	public ProjectWbsManhourId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectWbsManhourId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),null);
	}

}
