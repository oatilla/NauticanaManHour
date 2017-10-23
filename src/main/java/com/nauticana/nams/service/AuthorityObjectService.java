package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.AuthorityObject;


@Service
public class AuthorityObjectService extends AbstractService<AuthorityObject, String> {

	@Override
	public String tableName() {
		return AuthorityObject.tableName;
	}
	@Override
	public String[] fieldNames() {
		return AuthorityObject.fieldNames;
	}

	@Override
	@Transactional
	public AuthorityObject newEntity(String parentKey) {
		return new AuthorityObject();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public AuthorityObject newEntityWithId(String strId) {
		AuthorityObject entity = new AuthorityObject();
		entity.setId(strId);
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<AuthorityObject> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = -1;
		for(AuthorityObject x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(AuthorityObject x : list) {
//			map.put(x.getId(), x.getId());
//		}
//		return map;
	}

}
