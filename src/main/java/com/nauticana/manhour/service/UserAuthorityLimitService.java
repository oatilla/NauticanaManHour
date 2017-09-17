package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.UserAuthorityLimit;
import com.nauticana.manhour.model.UserAuthorityLimitId;
import com.nauticana.manhour.model.UserAuthorizationId;
import com.nauticana.manhour.repository.UserAuthorizationRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class UserAuthorityLimitService extends AbstractService<UserAuthorityLimit, UserAuthorityLimitId> {

	@Override
	public String[] getFieldNames() {
		return UserAuthorityLimit.fieldNames;
	}

	@Autowired
	UserAuthorizationRepository parentRep;

	@Override
	public UserAuthorityLimit newEntity(String parentKey) {
		UserAuthorityLimit entity = new UserAuthorityLimit();
		if (!Utils.emptyStr(parentKey)) {
			UserAuthorizationId parentId = new UserAuthorizationId(parentKey);
			UserAuthorityLimitId id = new UserAuthorityLimitId();
			id.setUsername(parentId.getUsername());
			id.setAuthorityGroup(parentId.getAuthorityGroup());
			entity.setId(id);
			entity.setUserAuthorization(parentRep.findOne(parentId));
		}
		return entity;
	}

	@Override
	public UserAuthorityLimitId StrToId(String id) {
		String[] s = id.split(",");
		return new UserAuthorityLimitId(s[0],s[1],s[2]);
	}

	@Override
	public UserAuthorityLimit newEntityWithId(String strId) {
		UserAuthorityLimit entity = new UserAuthorityLimit();
		entity.setId(StrToId(strId));
		return entity;
	}

}
