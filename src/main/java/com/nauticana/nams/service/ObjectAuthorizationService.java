package com.nauticana.nams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.ObjectAuthorization;
import com.nauticana.nams.model.ObjectAuthorizationId;
import com.nauticana.nams.repository.AuthorityGroupRepository;
import com.nauticana.nams.utils.Utils;


@Service
public class ObjectAuthorizationService extends AbstractService<ObjectAuthorization,ObjectAuthorizationId> {

	@Override
	public String tableName() {
		return ObjectAuthorization.tableName;
	}
	
	@Override
	public String[] fieldNames() {
		return ObjectAuthorization.fieldNames;
	}

	@Autowired
	AuthorityGroupRepository parentRep;

	@Override
	public ObjectAuthorization newEntity(String parentKey) {
		ObjectAuthorization entity = new ObjectAuthorization();
		if (!Utils.emptyStr(parentKey)) {
			ObjectAuthorizationId id = new ObjectAuthorizationId();
			id.setAuthorityGroup(parentKey);
			entity.setAuthorityGroup(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public ObjectAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new ObjectAuthorizationId(s[0], s[1], s[2], s[3]);
	}

	@Override
	public ObjectAuthorization newEntityWithId(String strId) {
		ObjectAuthorization entity = new ObjectAuthorization();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
