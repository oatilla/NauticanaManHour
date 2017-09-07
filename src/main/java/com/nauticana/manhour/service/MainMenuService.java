package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.MainMenu;

@Service
public class MainMenuService extends AbstractService<MainMenu, String> {

	@Override
	public String[] getFieldNames() {
		return MainMenu.fieldNames;
	}

	@Override
	public MainMenu newEntity() {
		return new MainMenu();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

}
