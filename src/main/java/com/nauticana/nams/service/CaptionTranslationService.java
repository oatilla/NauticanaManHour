package com.nauticana.nams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.CaptionTranslation;
import com.nauticana.nams.model.CaptionTranslationId;
import com.nauticana.nams.repository.LanguageRepository;
import com.nauticana.nams.utils.Utils;

@Service
public class CaptionTranslationService extends AbstractService<CaptionTranslation,CaptionTranslationId> {

	@Override
	public String tableName() {
		return CaptionTranslation.tableName;
	}

	@Override
	public String[] fieldNames() {
		return CaptionTranslation.fieldNames;
	}

	@Autowired
	LanguageRepository parentRep;

	@Override
	public CaptionTranslation newEntity(String parentKey) {
		CaptionTranslation entity = new CaptionTranslation();
		if (!Utils.emptyStr(parentKey)) {
			CaptionTranslationId id = new CaptionTranslationId();
			id.setLangcode(parentKey);
			entity.setLanguage(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public CaptionTranslationId StrToId(String id) {
		String[] s = id.split(",");
		return new CaptionTranslationId(s[0], s[1]);
	}

	@Override
	public CaptionTranslation newEntityWithId(String strId) {
		CaptionTranslation entity = new CaptionTranslation();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}