package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.PageAuthorizationId;


@Service
public class PageAuthorizationService extends AbstractService<PageAuthorization,PageAuthorizationId> {

	
	@Override
	public String[] getFieldNames() {
		return PageAuthorization.fieldNames;
	}

	@Override
	public PageAuthorization newEntity() {
		return new PageAuthorization();
	}

	@Override
	public PageAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new PageAuthorizationId(s[0], s[1]);
	}

}
