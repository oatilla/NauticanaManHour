package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.TableAuthorizationId;

@Controller
@RequestMapping("/tableAuthorization")
public class TableAuthorizationController extends AbstractController<TableAuthorization, TableAuthorizationId>{

//	@Autowired
//	protected TableAuthorizationService modelService;

	public TableAuthorizationController() {
		super(TableAuthorization.tableName, "tableAuthorizationList", "tableAuthorizationEdit");
	}

}
