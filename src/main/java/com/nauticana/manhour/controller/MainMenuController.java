package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.MainMenu;

@Controller
@RequestMapping("/mainMenu")
public class MainMenuController extends AbstractController<MainMenu, String> {

//	@Autowired
//	protected MainMenuService modelService;

	public MainMenuController() {
		super(MainMenu.tableName, "mainMenuList", "mainMenuEdit");
	}

}
