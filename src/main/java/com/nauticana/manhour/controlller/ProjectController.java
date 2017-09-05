package com.nauticana.manhour.controlller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.service.ProjectService;


@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, Integer> {

	@Autowired
	private ProjectService projectService;

	public ProjectController() {
		super("PROJECTS", "projectList", "projectEdit");
		this.modelService = projectService;
	}

}
