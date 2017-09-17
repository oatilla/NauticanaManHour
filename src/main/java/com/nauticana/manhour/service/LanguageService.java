package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Language;

@Service
public class LanguageService  extends AbstractService<Language,String> {
	
	@Override
	public String[] getFieldNames() {
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
}
