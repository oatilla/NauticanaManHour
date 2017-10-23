package com.nauticana.manhour.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;
import com.nauticana.manhour.repository.ProjectTeamPersonRepository;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.Utils;

@Service
public class ProjectWbsManhourService extends AbstractService<ProjectWbsManhour,ProjectWbsManhourId> {

	@Override
	public String tableName() {
		return ProjectWbsManhour.tableName;
	}

	@Override
	public String[] fieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbsManhour.fieldNames;
	}

	@Autowired
	ProjectTeamPersonRepository parentRep;

	@Override
	public ProjectWbsManhour newEntity(String parentKey) {
		ProjectWbsManhour entity = new ProjectWbsManhour();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamPersonId parentId = new ProjectTeamPersonId(parentKey);
			ProjectWbsManhourId id = new ProjectWbsManhourId();
			id.setProjectId(parentId.getProjectId());
			id.setTeamId(parentId.getTeamId());
			id.setWorkerId(parentId.getWorkerId());
			entity.setProjectTeamPerson(parentRep.findOne(parentId));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ProjectWbsManhourId StrToId(String id) {
		String[] s = id.split(",");
		Date date;
		try {
			date = Labels.dmyDF.parse(s[4]);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return new ProjectWbsManhourId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),date);
	}

	@Override
	public ProjectWbsManhour newEntityWithId(String strId) {
		ProjectWbsManhour entity = new ProjectWbsManhour();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
