package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.AuthorityGroup;
import com.gama.manhour.repository.AuthorityGroupRepository;


@Service
public class AuthorityGroupService implements IAbstractService<AuthorityGroup> {

	@Autowired
	private AuthorityGroupRepository r;
	
	public AuthorityGroup findById(String id) {
		return r.findOne(id);
	}
	
	public void remove(String id) throws RecordNotFound {
		AuthorityGroup l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}

	@Override
	@Transactional
	public AuthorityGroup findStrId(String id) {
		return findById(id);
	}

	@Override
	@Transactional
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public String[] getFieldNames() {
		return AuthorityGroup.fieldNames;
	}

	@Override
	@Transactional
	public AuthorityGroup newEntity() {
		return new AuthorityGroup();
	}

	@Override
	public AuthorityGroup create(AuthorityGroup entity) {
		return r.save(entity);
	}

	@Override
	public void save(AuthorityGroup entity) {
		r.save(entity);
	}

	@Override
	public List<AuthorityGroup> findAll() {
		return r.findAll();
	}

}
