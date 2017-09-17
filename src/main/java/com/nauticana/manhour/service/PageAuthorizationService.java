package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.PageAuthorizationId;
import com.nauticana.manhour.repository.AuthorityGroupRepository;
import com.nauticana.manhour.utils.Utils;


@Service
public class PageAuthorizationService extends AbstractService<PageAuthorization,PageAuthorizationId> {

	
	@Override
	public String[] getFieldNames() {
		return PageAuthorization.fieldNames;
	}

	@Autowired
	AuthorityGroupRepository parentRep;

	@Override
	public PageAuthorization newEntity(String parentKey) {
		PageAuthorization entity = new PageAuthorization();
		if (!Utils.emptyStr(parentKey)) {
			PageAuthorizationId id = new PageAuthorizationId();
			id.setAuthorityGroup(parentKey);
			entity.setAuthorityGroup(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public PageAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new PageAuthorizationId(s[0], s[1]);
	}

	@Override
	public PageAuthorization newEntityWithId(String strId) {
		PageAuthorization entity = new PageAuthorization();
		entity.setId(StrToId(strId));
		return entity;
	}

}
