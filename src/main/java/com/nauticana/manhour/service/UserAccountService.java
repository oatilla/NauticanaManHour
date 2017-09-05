package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.UserAccount;

@Service
public class UserAccountService extends AbstractService<UserAccount, String> {

	@Override
	public UserAccount findStrId(String id) {
		return findById(id);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public String[] getFieldNames() {
		return UserAccount.fieldNames;
	}

	@Override
	public UserAccount newEntity() {
		return new UserAccount();
	}

}

