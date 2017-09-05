package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.DomainName;

@Service
public class DomainNameService extends AbstractService<DomainName,String> {
		
	@Override
	public DomainName findStrId(String id) {
		return findById(id);
	}
	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(id);
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
