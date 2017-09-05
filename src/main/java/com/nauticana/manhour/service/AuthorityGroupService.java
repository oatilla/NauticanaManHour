package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.AuthorityGroup;


@Service
public class AuthorityGroupService extends AbstractService<AuthorityGroup, String> {

	@Override
	@Transactional
	public AuthorityGroup findStrId(String id) {
		return findById(id);
	}

	@Override
	@Transactional
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public String[] getFieldNames() {
		return AuthorityGroup.fieldNames;
	}

	@Override
	@Transactional
	public AuthorityGroup newEntity() {
		return new AuthorityGroup();
	}

}
