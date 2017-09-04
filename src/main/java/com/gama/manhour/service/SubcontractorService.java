package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.model.Subcontractor;
import com.gama.manhour.repository.SubcontractorRepository;

@Service
public class SubcontractorService {

	@Autowired
	private SubcontractorRepository rep;
	
	public Subcontractor getById(int id) {
		return rep.findOne(id);
	}
	
	public List<Subcontractor> list() {
		return rep.findAll();
	}
}
