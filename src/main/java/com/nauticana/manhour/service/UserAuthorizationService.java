package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.model.UserAuthorizationId;

@Service
public class UserAuthorizationService extends AbstractService<UserAuthorization,UserAuthorizationId> {

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new UserAuthorizationId(s[0],s[1]));
	}
	@Override
	public String[] getFieldNames() {
		return UserAuthorization.fieldNames;
	}

	@Override
	public UserAuthorization newEntity() {
		return new UserAuthorization();
	}

	@Override
	public UserAuthorization findStrId(String id) {
		String[] s = id.split(",");
		return findById(new UserAuthorizationId(s[0],s[1]));
	}

}
