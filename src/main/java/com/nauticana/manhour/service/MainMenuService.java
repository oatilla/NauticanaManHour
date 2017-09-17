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
	public MainMenu newEntity(String parentKey) {
		return new MainMenu();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public MainMenu newEntityWithId(String strId) {
		MainMenu entity = new MainMenu();
		entity.setId(StrToId(strId));
		return entity;
	}

}
