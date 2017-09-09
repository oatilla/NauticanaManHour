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
		if (!Utils.emptyStr(parentKey)) entity.getId().setUsername(parentKey);
		return entity;
	}

	@Override
	public UserAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new UserAuthorizationId(s[0],s[1]);
	}

}
