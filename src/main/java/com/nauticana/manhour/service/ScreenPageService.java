package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ScreenPage;
import com.nauticana.manhour.repository.MainMenuRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class ScreenPageService extends AbstractService<ScreenPage, String> {

	@Override
	public String[] getFieldNames() {
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

}
