package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.AuthorityGroup;


@Service
public class AuthorityGroupService extends AbstractService<AuthorityGroup, String> {

	@Override
	public String tableName() {
		return AuthorityGroup.tableName;
	}

	@Override
	public String[] fieldNames() {
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

	@Override
	public String[][] findAllStr() {
		List<AuthorityGroup> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = -1;
		for(AuthorityGroup x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId() + " " + x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(AuthorityGroup x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;
	}

}
