package com.nauticana.manhour.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.AuthorityGroup;


@Controller
@RequestMapping("/authorityGroup")
public class AuthorityGroupController extends AbstractController<AuthorityGroup, String> {

	public AuthorityGroupController() {
		super("AUTHORITY_GROUPS", "authorityGroupList", "authorityGroupEdit");
	}

}
