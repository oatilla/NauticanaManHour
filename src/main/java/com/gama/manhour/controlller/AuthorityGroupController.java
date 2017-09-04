package com.gama.manhour.controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gama.manhour.model.AuthorityGroup;
import com.gama.manhour.service.AuthorityGroupService;


@Controller
@RequestMapping("/authorityGroup")
public class AuthorityGroupController extends AbstractController<AuthorityGroup> {

	@Autowired
	private AuthorityGroupService authorityGroupService;

	public AuthorityGroupController() {
		super("AUTHORITY_GROUPS", "authorityGroupList", "authorityGroupEdit");
		this.modelService = authorityGroupService;
	}

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list1(HttpServletRequest request) throws IOException {
//		return list(request);
//	}

}
