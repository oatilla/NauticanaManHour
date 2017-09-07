package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;

@Controller
@RequestMapping("/projectWbsQuantity")
public class ProjectWbsQuantityController extends AbstractController<ProjectWbsQuantity, ProjectWbsQuantityId> {

//	@Autowired
//	protected ProjectWbsQuantityService modelService;

	public ProjectWbsQuantityController() {
		super(ProjectWbsQuantity.tableName, "projectWbsQuantityList", "projectWbsQuantityEdit");
	}

}
