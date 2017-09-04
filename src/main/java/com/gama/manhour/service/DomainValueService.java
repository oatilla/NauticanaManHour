package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.DomainValue;
import com.gama.manhour.model.DomainValueId;
import com.gama.manhour.model.UserAccount;
import com.gama.manhour.repository.DomainValueRepository;

@Service
public class DomainValueService implements IAbstractService<DomainValue> {

	@Autowired
	private DomainValueRepository r;
	
	public DomainValue findById(DomainValueId id) {
		return r.findOne(id);
	}
	
	public void remove(DomainValueId id) throws RecordNotFound {
		DomainValue l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}
	
	@Override
	public DomainValue findStrId(String id) {
		String[] s = id.split(",");
		return findById(new DomainValueId(s[1], s[0]));
	}

	@Override
	public DomainValue create(DomainValue entity) {
		return r.save(entity);
	}

	@Override
	public void save(DomainValue entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		String[] s = id.split(",");
		remove(new DomainValueId(s[1], s[0]));
	}

	@Override
	public List<DomainValue> findAll() {
		return r.findAll();
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
