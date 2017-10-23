package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.MainMenu;

@Service
public class MainMenuService extends AbstractService<MainMenu, String> {

	@Override
	public String tableName() {
		return MainMenu.tableName;
	}

	@Override
	public String[] fieldNames() {
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

	@Override
	public String[][] findAllStr() {
		List<MainMenu> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = "";
		items[i][1] = " - ";
		for(MainMenu x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(MainMenu x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;

	}

}
