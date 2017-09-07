package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.model.UserAuthorizationId;

@Controller
@RequestMapping("/userAuthorization")
public class UserAuthorizationController extends AbstractController<UserAuthorization, UserAuthorizationId>{

//	@Autowired
//	protected UserAuthorizationService modelService;

	public UserAuthorizationController() {
		super(UserAuthorization.tableName, "userAuthorizationList", "userAuthorizationEdit");
	}

}
