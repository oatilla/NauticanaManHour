package com.nauticana.nams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.DomainValue;
import com.nauticana.nams.model.DomainValueId;
import com.nauticana.nams.repository.DomainNameRepository;
import com.nauticana.nams.utils.Utils;

@Service
public class DomainValueService extends AbstractService<DomainValue, DomainValueId> {

	@Override
	public String tableName() {
		return DomainValue.tableName;
	}

	@Override
	public String[] fieldNames() {
		return DomainValue.fieldNames;
	}

	@Autowired
	DomainNameRepository parentRep;

	@Override
	public DomainValue newEntity(String parentKey) {
		DomainValue entity = new DomainValue();
		if (!Utils.emptyStr(parentKey)) {
			DomainValueId id = new DomainValueId();
			id.setDomain(parentKey);
			entity.setDomainName(parentRep.findOne(parentKey));
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public DomainValueId StrToId(String id) {
		String[] s = id.split(",");
		return new DomainValueId(s[0], s[1]);
	}

	@Override
	public DomainValue newEntityWithId(String strId) {
		DomainValue entity = new DomainValue();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
