package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.DomainName;
import com.gama.manhour.model.UserAccount;
import com.gama.manhour.repository.DomainNameRepository;

@Service
public class DomainNameService implements IAbstractService<DomainName> {

	@Autowired
	private DomainNameRepository r;
	
	public DomainName findById(String id) {
		return r.findOne(id);
	}
	
	public void remove(String id) throws RecordNotFound {
		DomainName l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}
	
	@Override
	public DomainName findStrId(String id) {
		return findById(id);
	}

	@Override
	public DomainName create(DomainName entity) {
		return r.save(entity);
	}

	@Override
	public void save(DomainName entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
	}

	@Override
	public List<DomainName> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		return DomainName.fieldNames;
	}

	@Override
	public DomainName newEntity() {
		return new DomainName();
	}

}
