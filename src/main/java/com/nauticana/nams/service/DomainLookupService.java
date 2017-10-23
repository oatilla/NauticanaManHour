package com.nauticana.nams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.DomainLookup;
import com.nauticana.nams.model.DomainLookupId;
import com.nauticana.nams.repository.DomainNameRepository;
import com.nauticana.nams.utils.Utils;

@Service
public class DomainLookupService extends AbstractService<DomainLookup, DomainLookupId> {

	@Override
	public String tableName() {
		return DomainLookup.tableName;
	}

	@Override
	public String[] fieldNames() {
		return DomainLookup.fieldNames;
	}

	@Autowired
	DomainNameRepository parentRep;

	@Override
	public DomainLookup newEntity(String parentKey) {
		DomainLookup entity = new DomainLookup();
		if (!Utils.emptyStr(parentKey)) {
			DomainLookupId id = new DomainLookupId();
			id.setDomain(parentKey);
			entity.setDomainName(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public DomainLookupId StrToId(String id) {
		String[] s = id.split(",");
		return new DomainLookupId(s[0], s[1]);
	}

	@Override
	public DomainLookup newEntityWithId(String strId) {
		DomainLookup entity = new DomainLookup();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}
}
