package com.gama.manhour.service;

import java.util.List;

import com.gama.manhour.exception.RecordNotFound;

public interface IAbstractService <M> {

	M findStrId(String id);

	M create(M entity);
	
	void save(M entity);

//	void update(M entity) throws RecordNotFound;

	void removeStrId(String id) throws RecordNotFound;

	List<M> findAll();
	
	String[]  getFieldNames();
	
	M newEntity();

}
