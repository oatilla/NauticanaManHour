package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.CaptionTranslationId;
import com.nauticana.manhour.repository.LanguageRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class CaptionTranslationService extends AbstractService<CaptionTranslation,CaptionTranslationId> {

	@Override
	public String[] getFieldNames() {
		return CaptionTranslation.fieldNames;
	}

	@Autowired
	LanguageRepository parentRep;

	@Override
	public CaptionTranslation newEntity(String parentKey) {
		CaptionTranslation entity = new CaptionTranslation();
		if (!Utils.emptyStr(parentKey)) entity.setLanguage(parentRep.findOne(parentKey));
		return entity;
	}

	@Override
	public CaptionTranslationId StrToId(String id) {
		String[] s = id.split(",");
		return new CaptionTranslationId(s[0], s[1]);
	}

}