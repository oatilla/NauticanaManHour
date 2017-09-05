package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.Language;

@Service
public class LanguageService  extends AbstractService<Language,String> {
	
	@Override
	public Language findStrId(String id) {
		return findById(id);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public String[] getFieldNames() {
		return Language.fieldNames;
	}

	@Override
	public Language newEntity() {
		return new Language();
	}
}
