package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ScreenPage;

@Service
public class ScreenPageService extends AbstractService<ScreenPage, String> {

	@Override
	public String[] getFieldNames() {
		return ScreenPage.fieldNames;
	}

	@Override
	public ScreenPage newEntity() {
		return new ScreenPage();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

}
