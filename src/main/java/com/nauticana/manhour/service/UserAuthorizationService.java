package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.model.UserAuthorizationId;
import com.nauticana.manhour.repository.UserAccountRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class UserAuthorizationService extends AbstractService<UserAuthorization,UserAuthorizationId> {

	@Override
	public String[] getFieldNames() {
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

}
