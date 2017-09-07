package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.model.UserAuthorizationId;

@Service
public class UserAuthorizationService extends AbstractService<UserAuthorization,UserAuthorizationId> {

	@Override
	public String[] getFieldNames() {
		return UserAuthorization.fieldNames;
	}

	@Override
	public UserAuthorization newEntity() {
		return new UserAuthorization();
	}

	@Override
	public UserAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new UserAuthorizationId(s[0],s[1]);
	}

}
