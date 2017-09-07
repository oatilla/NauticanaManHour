package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;

@Controller
@RequestMapping("/projectTeam")
public class ProjectTeamController extends AbstractController<ProjectTeam, ProjectTeamId>{

//	@Autowired
//	protected ProjectTeamService modelService;

	public ProjectTeamController() {
		super(ProjectTeam.tableName, "projectTeamList", "projectTeamEdit");
	}

}
