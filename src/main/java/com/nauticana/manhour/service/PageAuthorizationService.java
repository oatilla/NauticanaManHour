package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.PageAuthorizationId;


@Service
public class PageAuthorizationService extends AbstractService<PageAuthorization,PageAuthorizationId> {

	
	@Override
	public String[] getFieldNames() {
		return PageAuthorization.fieldNames;
	}

	@Override
	public PageAuthorization newEntity() {
		return new PageAuthorization();
	}

	@Override
	public PageAuthorization findStrId(String id) {
		String[] s = id.split(",");
		return findById(new PageAuthorizationId(s[0], s[1]));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		String[] s = id.split(",");
		remove(new PageAuthorizationId(s[0], s[1]));
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
