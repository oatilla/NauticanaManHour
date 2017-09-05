package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.CaptionTranslationId;

@Service
public class CaptionTranslationService extends AbstractService<CaptionTranslation,CaptionTranslationId> {

	@Override
	public CaptionTranslation findStrId(String id) {
		String[] s = id.split(",");
		return findById(new CaptionTranslationId(s[0], s[1]));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new CaptionTranslationId(s[0], s[1]));
	}

	@Override
	public String[] getFieldNames() {
		return CaptionTranslation.fieldNames;
	}

	@Override
	public CaptionTranslation newEntity() {
		return new CaptionTranslation();
	}

}