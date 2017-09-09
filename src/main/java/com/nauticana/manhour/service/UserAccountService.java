package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, String> {

	@Override
	public String[] getFieldNames() {
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

}

