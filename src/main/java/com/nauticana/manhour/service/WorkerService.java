package com.nauticana.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ExternalPerson;
import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.repository.SubcontractorRepository;
import com.nauticana.manhour.repository.WorkerRepository;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.utils.Utils;

@Service
public class WorkerService extends AbstractService<Worker, Integer> {

	@Override
	public String tableName() {
		return Worker.tableName;
	}

	@Override
	public String[] fieldNames() {
		return Worker.fieldNames;
	}

	@Autowired
	SubcontractorRepository parentRep;
	
	@Override
	public Worker newEntity(String parentKey) {
		Worker entity = new Worker();
		if (!Utils.emptyStr(parentKey)) {
			entity.setSubcontractor(parentRep.findOne(StrToId(parentKey)));
		}
		return entity;
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}
	
	public Worker findByPersonId(int personId) {
		return ((WorkerRepository) r).findByPersonId(personId);
	}
	
	public List<Worker> findBySubcontractor(Subcontractor subcontractor) {
		return ((WorkerRepository) r).findBySubcontractor(subcontractor);
	}

	@Override
	public Worker newEntityWithId(String strId) {
		Worker entity = new Worker();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}
	
	public Worker getLocalPerson(ExternalPerson lp) {
		if (lp == null) return null;
		Worker entity = findByPersonId(lp.getPersonId());
		if (entity == null) {
			entity = new Worker();
			entity.setCaption(lp.getFirstName() + " " + lp.getLastName());
			entity.setPersonId(lp.getPersonId());
			entity.setCitizenShip(lp.getNationalityId());
			if (!Utils.emptyStr(lp.getSubcontractor())) {
				List<Subcontractor> subcontractor = parentRep.findByExtSubcontractor(lp.getSubcontractor());
				if (!subcontractor.isEmpty())
					entity.setSubcontractor(subcontractor.get(0));
			}
			entity = ((WorkerRepository) r).save(entity);
		}
		return entity;
	}

}
