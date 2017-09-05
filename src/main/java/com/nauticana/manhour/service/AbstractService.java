package com.nauticana.manhour.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.exception.RecordNotFound;



public abstract class AbstractService<M, MId extends Serializable> implements IAbstractService<M, MId> {

	
	@Autowired
	protected JpaRepository<M, MId> r;

	@Override
	public M findById(MId id) {
		return r.findOne(id);
	}

	@Override
	public M create(M entity) {
		return r.save(entity);
	}

	@Override
	public void save(M entity) {
		r.save(entity);
	}

	@Override
	public void remove(MId id) throws RecordNotFound {
		r.delete(id);
	}

	@Override
	public List<M> findAll() {
		return r.findAll();
	}
	
}
