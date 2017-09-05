package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.PageAuthorization;
import com.gama.manhour.model.PageAuthorizationId;
import com.gama.manhour.repository.PageAuthorizationRepository;


@Service
public abstract class PageAuthorizationService implements IAbstractService<PageAuthorization> {

	
	@Autowired
	private PageAuthorizationRepository r;
	
	

	public PageAuthorization findStrId(PageAuthorizationId id) {
		return r.findOne(id);
	}

	@Override
	public PageAuthorization create(PageAuthorization entity) {
		return r.save(entity);
	}

	@Override
	public void save(PageAuthorization entity) {
		r.save(entity);
	}

	public void removeStrId(PageAuthorizationId id) throws RecordNotFound {
		r.delete(id);
		
	}

	@Override
	public List<PageAuthorization> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		return PageAuthorization.fieldNames;
	}

	@Override
	public PageAuthorization newEntity() {
		return new PageAuthorization();
	}
	
	
	
	

}
