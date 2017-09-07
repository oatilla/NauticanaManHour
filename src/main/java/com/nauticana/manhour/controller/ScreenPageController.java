package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ScreenPage;

@Controller
@RequestMapping("/screenPage")
public class ScreenPageController extends AbstractController<ScreenPage, String> {

//	@Autowired
//	protected ScreenPageService modelService;

	public ScreenPageController() {
		super(ScreenPage.tableName, "screenPageList", "screenPageEdit");
	}

}
