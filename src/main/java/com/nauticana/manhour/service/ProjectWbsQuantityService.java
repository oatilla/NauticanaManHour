package com.nauticana.manhour.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;
import com.nauticana.manhour.repository.ProjectTeamRepository;
import com.nauticana.manhour.repository.ProjectWbsRepository;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.Utils;

@Service
public class ProjectWbsQuantityService extends AbstractService<ProjectWbsQuantity,ProjectWbsQuantityId> {

	@Override
	public String tableName() {
		return ProjectWbsQuantity.tableName;
	}

	@Override
	public String[] fieldNames() {
		return ProjectWbsQuantity.fieldNames;
	}

	@Autowired
	ProjectWbsRepository pwRep;

	@Autowired
	ProjectTeamRepository ptRep;

	@Override
	public ProjectWbsQuantity newEntity(String parentKey) {
		String[] s = parentKey.split(",");
		int projectId = Integer.parseInt(s[0]);
		int categoryId = Integer.parseInt(s[1]);
		int teamId = Integer.parseInt(s[2]);
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		if (!Utils.emptyStr(parentKey)) {
			ProjectWbsId pwId = new ProjectWbsId(projectId,categoryId);
			ProjectTeamId ptId = new ProjectTeamId(projectId,teamId);
			ProjectWbsQuantityId id = new ProjectWbsQuantityId();
			id.setProjectId(projectId);
			id.setCategoryId(categoryId);
			id.setTeamId(teamId);
			entity.setId(id);
			entity.setProjectWbs(pwRep.findOne(pwId));
			entity.setProjectTeam(ptRep.findOne(ptId));
		}
		entity.setStatus("INITIAL");
		return entity;
	}

	@Override
	public ProjectWbsQuantityId StrToId(String id) {
		String[] s = id.split(",");
		try {
			return new ProjectWbsQuantityId(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2]), Labels.dmyDF.parse(s[3]));
		} catch (NumberFormatException | ParseException e) {
			return null;
		}
	}

	@Override
	public ProjectWbsQuantity newEntityWithId(String strId) {
		ProjectWbsQuantity entity = new ProjectWbsQuantity();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}
	
	public void approve(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findOne(pwId);
		for (ProjectWbsQuantity pwq : pw.getProjectWbsQuantities()) {
			if (pwq.getId().getTeamId() == teamId &&
				pwq.getStatus().equals(Labels.INITIAL) &&
				(begda == null || 
				 (endda == null && pwq.getId().getBegda().getTime() == begda.getTime()) ||
				 (pwq.getId().getBegda().getTime() >= begda.getTime() && pwq.getEndda().getTime() <= endda.getTime())
				))	{
				pwq.setStatus(Labels.APPROVED);
				save(pwq);
			}
		}
	}

	public void withdrawApprove(int projectId, int categoryId, int teamId, Date begda, Date endda) throws Exception {
		ProjectWbsId pwId = new ProjectWbsId(projectId, categoryId);
		ProjectWbs pw = pwRep.findOne(pwId);
		for (ProjectWbsQuantity pwq : pw.getProjectWbsQuantities()) {
			if (pwq.getId().getTeamId() == teamId &&
					pwq.getStatus().equals(Labels.APPROVED) &&
					(begda == null || 
					 (endda == null && pwq.getId().getBegda().getTime() == begda.getTime()) ||
					 (pwq.getId().getBegda().getTime() >= begda.getTime() && pwq.getEndda().getTime() <= endda.getTime())
					))	{
					pwq.setStatus(Labels.INITIAL);
					save(pwq);
				}
		}
	}

}
