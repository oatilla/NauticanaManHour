package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.TableAuthorizationId;

@Service
public class TableAuthorizationService extends AbstractService<TableAuthorization,TableAuthorizationId> {

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new TableAuthorizationId(s[0],s[1]));
		
	}
	@Override
	public String[] getFieldNames() {
		return TableAuthorization.fieldNames;
	}

	@Override
	public TableAuthorization newEntity() {
		return new TableAuthorization();
	}

	@Override
	public TableAuthorization findStrId(String id) {
		String[] s = id.split(",");
		return findById(new TableAuthorizationId(s[0],s[1]));
	}

}