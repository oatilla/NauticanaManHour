package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.CaptionTranslation;
import com.gama.manhour.model.CaptionTranslationId;
import com.gama.manhour.repository.CaptionTranslationRepository;

@Service
public class CaptionTranslationService implements IAbstractService<CaptionTranslation> {
	
	@Autowired
	private CaptionTranslationRepository r;
	
	public CaptionTranslation findById(CaptionTranslationId id) {
		return r.findOne(id);
	}
	
	public void remove(CaptionTranslationId id) throws RecordNotFound {
		CaptionTranslation l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}
	
	@Override
	public CaptionTranslation findStrId(String id) {
		String[] s = id.split(",");
		return findById(new CaptionTranslationId(s[1], s[0]));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new CaptionTranslationId(s[1], s[0]));
	}

	@Override
	public String[] getFieldNames() {
		return CaptionTranslation.fieldNames;
	}

	@Override
	public CaptionTranslation newEntity() {
		return new CaptionTranslation();
	}

	@Override
	public CaptionTranslation create(CaptionTranslation entity) {
		return r.save(entity);
	}

	@Override
	public void save(CaptionTranslation entity) {
		r.save(entity);
	}

	@Override
	public List<CaptionTranslation> findAll() {
		return r.findAll();
	}

}
