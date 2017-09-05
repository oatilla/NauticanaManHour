package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;

@Service
public class DomainValueService extends AbstractService<DomainValue, DomainValueId> {

	@Override
	public DomainValue findStrId(String id) {
		String[] s = id.split(",");
		return findById(new DomainValueId(s[0], s[1]));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new DomainValueId(s[0], s[1]));
	}

	@Override
	public String[] getFieldNames() {
		return DomainValue.fieldNames;
	}

	@Override
	public DomainValue newEntity() {
		return new DomainValue();
	}

}
