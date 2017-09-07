package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;

@Controller
@RequestMapping("/projectWbsManhour")
public class ProjectWbsManhourController extends AbstractController<ProjectWbsManhour, ProjectWbsManhourId>{

//	@Autowired
//	protected ProjectWbsManhourService modelService;

	public ProjectWbsManhourController() {
		super(ProjectWbsManhour.tableName, "projectWbsManhourList", "projectWbsManhourEdit");
	}

}
