package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.UserAccount;
import com.gama.manhour.repository.UserAccountRepository;

@Service
public class UserAccountService implements IAbstractService<UserAccount> {

	@Autowired
	private UserAccountRepository r;
	
	public UserAccount findById(String id) {
		return r.findOne(id);
	}
	
	public void remove(String id) throws RecordNotFound {
		UserAccount l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}

	@Override
	public UserAccount findStrId(String id) {
		return findById(id);
	}

	@Override
	public UserAccount create(UserAccount entity) {
		return r.save(entity);
	}

	@Override
	public void save(UserAccount entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public List<UserAccount> findAll() {
		return r.findAll();
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

