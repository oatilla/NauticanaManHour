package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.TableAuthorizationId;

@Service
public class TableAuthorizationService extends AbstractService<TableAuthorization,TableAuthorizationId> {

	@Override
	public String[] getFieldNames() {
		return TableAuthorization.fieldNames;
	}

	@Override
	public TableAuthorization newEntity() {
		return new TableAuthorization();
	}

	@Override
	public TableAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new TableAuthorizationId(s[0],s[1]);
	}

}