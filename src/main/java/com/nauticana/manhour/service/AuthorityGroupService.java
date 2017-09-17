package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.AuthorityGroup;


@Service
public class AuthorityGroupService extends AbstractService<AuthorityGroup, String> {

	@Override
	public String[] getFieldNames() {
		return AuthorityGroup.fieldNames;
	}

	@Override
	@Transactional
	public AuthorityGroup newEntity(String parentKey) {
		return new AuthorityGroup();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public AuthorityGroup newEntityWithId(String strId) {
		AuthorityGroup entity = new AuthorityGroup();
		entity.setId(strId);
		return entity;
	}

}
