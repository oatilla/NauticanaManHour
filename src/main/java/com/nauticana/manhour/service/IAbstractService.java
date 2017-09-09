package com.nauticana.manhour.service;

import java.util.List;

import com.nauticana.manhour.exception.RecordNotFound;

public interface IAbstractService <M,MId> {

	M findById(MId id);

	M create(M entity);
	
	void save(M entity);

	void remove(MId id) throws RecordNotFound;

	List<M> findAll();
	
	String[]  getFieldNames();
	
	M newEntity(String parentKey);

	MId StrToId(String id);
}
