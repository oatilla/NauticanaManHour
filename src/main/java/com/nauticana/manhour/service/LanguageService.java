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
	public Language newEntity() {
		return new Language();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}
}
