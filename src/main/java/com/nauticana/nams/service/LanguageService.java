package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.Language;

@Service
public class LanguageService  extends AbstractService<Language,String> {
	
	@Override
	public String tableName() {
		return Language.tableName;
	}

	@Override
	public String[] fieldNames() {
		return Language.fieldNames;
	}

	@Override
	public Language newEntity(String parentKey) {
		return new Language();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public Language newEntityWithId(String strId) {
		Language entity = new Language();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<Language> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = -1;
		for(Language x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		for(Language x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;
	}

	public String[][]  findAllFlag() {
		List<Language> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = null;
		items[i][1] = " - ";
		for(Language x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = "flag-icon " + x.getFlag();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		for(Language x : list) {
//			map.put(x.getId(), "flag-icon " + x.getFlag());
//		}
//		return map;
	}
}
