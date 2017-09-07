package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;

@Controller
@RequestMapping("/projectTeamPerson")
public class ProjectTeamPersonController extends AbstractController<ProjectTeamPerson, ProjectTeamPersonId>{

//	@Autowired
//	protected ProjectTeamPersonService modelService;

	public ProjectTeamPersonController() {
		super(ProjectTeamPerson.tableName, "projectTeamPersonList", "projectTeamPersonEdit");
	}

}
