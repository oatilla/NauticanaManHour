package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.Language;
import com.gama.manhour.model.UserAccount;
import com.gama.manhour.repository.LanguageRepository;

@Service
public class LanguageService  implements IAbstractService<Language> {
	
	@Autowired
	private LanguageRepository r;
	
	public Language findById(String id) {
		return r.findOne(id);
	}

	@Override
	public Language findStrId(String id) {
		return findById(id);
	}

	public void remove(String id) throws RecordNotFound {
		Language l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
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

	@Override
	public Language create(Language entity) {
		return r.save(entity);
	}

	@Override
	public void save(Language entity) {
		r.save(entity);
	}

	@Override
	public List<Language> findAll() {
		return r.findAll();
	}
}
