package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.DomainLookup;
import com.nauticana.manhour.model.DomainLookupId;
import com.nauticana.manhour.repository.DomainNameRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class DomainLookupService extends AbstractService<DomainLookup, DomainLookupId> {

	@Override
	public String[] getFieldNames() {
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
}
