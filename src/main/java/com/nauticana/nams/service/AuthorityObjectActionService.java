package com.nauticana.nams.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.AuthorityObjectAction;
import com.nauticana.nams.model.AuthorityObjectActionId;
import com.nauticana.nams.repository.AuthorityObjectRepository;
import com.nauticana.nams.utils.Utils;


@Service
public class AuthorityObjectActionService extends AbstractService<AuthorityObjectAction,AuthorityObjectActionId> {

	
	@Override
	public String tableName() {
		return AuthorityObjectAction.tableName;
	}

	@Override
	public String[] fieldNames() {
		return AuthorityObjectAction.fieldNames;
	}

	@Autowired
	AuthorityObjectRepository parentRep;

	@Override
	public AuthorityObjectAction newEntity(String parentKey) {
		AuthorityObjectAction entity = new AuthorityObjectAction();
		if (!Utils.emptyStr(parentKey)) {
			AuthorityObjectActionId id = new AuthorityObjectActionId();
			id.setObjectType(parentKey);
			entity.setAuthorityObject(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public AuthorityObjectActionId StrToId(String id) {
		String[] s = id.split(",");
		return new AuthorityObjectActionId(s[0], s[1]);
	}

	@Override
	public AuthorityObjectAction newEntityWithId(String strId) {
		AuthorityObjectAction entity = new AuthorityObjectAction();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

	public Map<String, String> findAllStr(String authorityObject) {
		Map<String, String> list = new HashMap<String, String>();
		list.put(null, " - ");
		for (AuthorityObjectAction aoa : parentRep.getOne(authorityObject).getAuthorityObjectActions()) {
			list.put(aoa.getId().getAction(), aoa.getId().getAction());
		}
		return list;
	}

}
