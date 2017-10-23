package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, String> {

	@Override
	public String tableName() {
		return UserAccount.tableName;
	}

	@Override
	public String[] fieldNames() {
		return UserAccount.fieldNames;
	}

	@Override
	public UserAccount newEntity(String parentKey) {
		return new UserAccount();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public UserAccount newEntityWithId(String strId) {
		UserAccount entity = new UserAccount();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<UserAccount> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = "";
		items[i][1] = " - ";
		for(UserAccount x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(UserAccount x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;
	}

}

