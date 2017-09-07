package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;

@Service
public class DomainValueService extends AbstractService<DomainValue, DomainValueId> {

	@Override
	public String[] getFieldNames() {
		return DomainValue.fieldNames;
	}

	@Override
	public DomainValue newEntity() {
		return new DomainValue();
	}

	@Override
	public DomainValueId StrToId(String id) {
		String[] s = id.split(",");
		return new DomainValueId(s[0], s[1]);
	}

}
