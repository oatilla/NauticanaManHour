package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.DomainName;

@Service
public class DomainNameService extends AbstractService<DomainName,String> {
		
	@Override
	public String[] getFieldNames() {
		return DomainName.fieldNames;
	}

	@Override
	public DomainName newEntity(String parentKey) {
		return new DomainName();
	}
	
	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public DomainName newEntityWithId(String strId) {
		DomainName entity = new DomainName();
		entity.setId(StrToId(strId));
		return entity;
	}
}
