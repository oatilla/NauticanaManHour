package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Subcontractor;

@Controller
@RequestMapping("/subcontractor")
public class SubcontractorController extends AbstractController<Subcontractor, Integer> {

//	@Autowired
//	protected SubcontractorService modelService;

	public SubcontractorController() {
		super(Subcontractor.tableName, "subcontractorList", "subcontractorEdit");
	}
}
