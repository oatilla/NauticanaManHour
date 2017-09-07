package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.DomainName;

@Controller
@RequestMapping("/domainName")
public class DomainNameController extends AbstractController<DomainName, String> {

//	@Autowired
//	protected DomainNameService modelService;

	public DomainNameController() {
		super(DomainName.tableName, "domainNameList", "domainNameEdit");
	}

}
