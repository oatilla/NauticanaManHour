package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.TableAuthorizationId;
import com.nauticana.manhour.repository.AuthorityGroupRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class TableAuthorizationService extends AbstractService<TableAuthorization,TableAuthorizationId> {

	@Override
	public String[] getFieldNames() {
		return TableAuthorization.fieldNames;
	}

	@Autowired
	AuthorityGroupRepository parentRep;

	@Override
	public TableAuthorization newEntity(String parentKey) {
		TableAuthorization entity = new TableAuthorization();
		if (!Utils.emptyStr(parentKey)) entity.setAuthorityGroup(parentRep.findOne(parentKey));
		return entity;
	}

	@Override
	public TableAuthorizationId StrToId(String id) {
		String[] s = id.split(",");
		return new TableAuthorizationId(s[0],s[1]);
	}

}