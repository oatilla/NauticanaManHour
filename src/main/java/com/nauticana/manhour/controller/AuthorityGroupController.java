package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.AuthorityGroup;


@Controller
@RequestMapping("/authorityGroup")
public class AuthorityGroupController extends AbstractController<AuthorityGroup, String> {

//	@Autowired
//	protected AuthorityGroupService modelService;
	
	public AuthorityGroupController() {
		super(AuthorityGroup.tableName, "authorityGroupList", "authorityGroupEdit");
	}

}
