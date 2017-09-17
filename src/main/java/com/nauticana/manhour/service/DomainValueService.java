package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;
import com.nauticana.manhour.repository.DomainNameRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class DomainValueService extends AbstractService<DomainValue, DomainValueId> {

	@Override
	public String[] getFieldNames() {
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

}
