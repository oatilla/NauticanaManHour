package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.DomainLookup;
import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;

@Controller
@RequestMapping("/domainLookup")
public class DomainLookupController extends AbstractController<DomainValue, DomainValueId>{

//	@Autowired
//	protected DomainValueService modelService;

	public DomainLookupController() {
		super(DomainLookup.tableName, "domainLookupList", "domainLookupEdit");
	}

}
