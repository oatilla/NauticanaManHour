package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.PageAuthorizationId;

@Controller
@RequestMapping("/pageAuthorization")
public class PageAuthorizationController extends AbstractController<PageAuthorization, PageAuthorizationId>{

//	@Autowired
//	protected PageAuthorizationService modelService;

	public PageAuthorizationController() {
		super(PageAuthorization.tableName, "pageAuthorizationList", "pageAuthorizationEdit");
	}

}
