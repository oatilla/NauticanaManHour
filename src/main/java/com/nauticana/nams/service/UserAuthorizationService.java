package com.nauticana.nams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.UserAuthorization;
import com.nauticana.nams.model.UserAuthorizationId;
import com.nauticana.nams.repository.UserAccountRepository;
import com.nauticana.nams.utils.Utils;

@Service
public class UserAuthorizationService extends AbstractService<UserAuthorization,UserAuthorizationId> {

	@Override
	public String tableName() {
		return UserAuthorization.tableName;
	}

	@Override
	public String[] fieldNames() {
		return UserAuthorization.fieldNames;
	}

	@Autowired
	UserAccountRepository parentRep;

	@Override
	public UserAuthorization newEntity(String parentKey) {
		UserAuthorization entity = new UserAuthorization();
		if (!Utils.emptyStr(parentKey)) {
			UserAuthorizationId id = new UserAuthorizationId();
			id.setUsername(parentKey);
			entity.setId(id);
			entity.setUserAccount(parentRep.findOne(parentKey));
		}
		return entity;
	}

	@Override
	public UserAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new UserAuthorizationId(s[0],s[1]);
	}

	@Override
	public UserAuthorization newEntityWithId(String strId) {
		UserAuthorization entity = new UserAuthorization();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
