package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Language;

@Controller
@RequestMapping("/language")
public class LanguageController extends AbstractController<Language, String> {

//	@Autowired
//	protected LanguageService modelService;

	public LanguageController() {
		super(Language.tableName, "languageList", "languageEdit");
	}

}
