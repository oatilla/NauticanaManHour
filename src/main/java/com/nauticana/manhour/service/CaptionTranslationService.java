package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.CaptionTranslation;
import com.nauticana.manhour.model.CaptionTranslationId;

@Service
public class CaptionTranslationService extends AbstractService<CaptionTranslation,CaptionTranslationId> {

	@Override
	public String[] getFieldNames() {
		return CaptionTranslation.fieldNames;
	}

	@Override
	public CaptionTranslation newEntity() {
		return new CaptionTranslation();
	}

	@Override
	public CaptionTranslationId StrToId(String id) {
		String[] s = id.split(",");
		return new CaptionTranslationId(s[0], s[1]);
	}

}