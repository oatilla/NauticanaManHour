package com.nauticana.manhour.service;

import java.util.List;

import com.nauticana.manhour.exception.RecordNotFound;

public interface IAbstractService <M,MId> {

	M findStrId(String id);
	
	M findById(MId id);

	M create(M entity);
	
	void save(M entity);

	void removeStrId(String id) throws RecordNotFound;
	
	void remove(MId id) throws RecordNotFound;

	List<M> findAll();
	
	String[]  getFieldNames();
	
	M newEntity();

}
