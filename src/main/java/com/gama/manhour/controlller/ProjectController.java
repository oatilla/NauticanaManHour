package com.gama.manhour.controlller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gama.manhour.model.Project;
import com.gama.manhour.service.ProjectService;


@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project> {

	@Autowired
	private ProjectService projectService;

	public ProjectController() {
		super("PROJECTS", "projectList", "projectEdit");
		this.modelService = projectService;
	}

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list1(HttpServletRequest request) throws IOException {
//		return list(request);
//	}

}
