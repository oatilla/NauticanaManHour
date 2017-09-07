package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;

@Service
public class ProjectTeamService extends AbstractService<ProjectTeam,ProjectTeamId> {


	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectTeam.fieldNames;
	}

	@Override
	public ProjectTeam newEntity() {
		return new ProjectTeam();
	}

	@Override
	public ProjectTeamId StrToId(String id) {
		String[] s = id.split(",");
		return new ProjectTeamId(Integer.parseInt(s[0]),Integer.parseInt(s[1]));
	}

}
