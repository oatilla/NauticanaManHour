package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;

@Controller
@RequestMapping("/domainValue")
public class DomainValueController extends AbstractController<DomainValue, DomainValueId>{

//	@Autowired
//	protected DomainValueService modelService;

	public DomainValueController() {
		super(DomainValue.tableName, "domainValueList", "domainValueEdit");
	}

}
