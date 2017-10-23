package com.nauticana.nams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.ScreenPage;
import com.nauticana.nams.repository.MainMenuRepository;
import com.nauticana.nams.utils.Utils;

@Service
public class ScreenPageService extends AbstractService<ScreenPage, String> {

	@Override
	public String tableName() {
		return ScreenPage.tableName;
	}

	@Override
	public String[] fieldNames() {
		return ScreenPage.fieldNames;
	}

	@Autowired
	MainMenuRepository parentRep;
	
	@Override
	public ScreenPage newEntity(String parentKey) {
		ScreenPage entity = new ScreenPage();
		if (!Utils.emptyStr(parentKey)) {
			entity.setMenu(parentRep.findOne(parentKey));
		}
		return entity;
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public ScreenPage newEntityWithId(String strId) {
		ScreenPage entity = new ScreenPage();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<ScreenPage> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = "";
		items[i][1] = " - ";
		for(ScreenPage x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(ScreenPage x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;
	}

}
