package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.UserAuthorization;
import com.gama.manhour.model.UserAuthorizationId;
import com.gama.manhour.repository.UserAuthorizationRepository;

@Service
public class UserAuthorizationService implements IAbstractService<UserAuthorization> {

	
	@Autowired
	public UserAuthorizationRepository r;
	
	
	
	public UserAuthorization findById(UserAuthorizationId id) {
		return r.findOne(id);
	}

	@Override
	public UserAuthorization create(UserAuthorization entity) {
		return r.save(entity);
	}

	@Override
	public void save(UserAuthorization entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<UserAuthorization> findAll() {
		return r.findAll();
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
		return null;
	}

}
