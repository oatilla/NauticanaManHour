package com.nauticana.manhour.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Category;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;
import com.nauticana.manhour.repository.ProjectRepository;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.Utils;

@Service
public class ProjectTeamService extends AbstractService<ProjectTeam,ProjectTeamId> {


	@Override
	public String[] getFieldNames() {
		return ProjectTeam.fieldNames;
	}

	@Autowired
	ProjectRepository parentRep;

	@Autowired
	UtilService urepRep;

	@Override
	public ProjectTeam newEntity(String parentKey) {
		ProjectTeam entity = new ProjectTeam();
		if (!Utils.emptyStr(parentKey)) {
			ProjectTeamId id = new ProjectTeamId();
			id.setProjectId(Integer.parseInt(parentKey));
			id.setTeamId(urepRep.nextTeamId(id.getProjectId()));
			entity.setId(id);
			entity.setProject(parentRep.findOne(id.getProjectId()));
		}
		return entity;
	}

	@Override
	public ProjectTeamId StrToId(String id) {
		return new ProjectTeamId(id);
	}

	public String[][] getWbsManhourByDate(int projectId, int teamId, Date date, ProjectWbsManhourService pwms) {
		ProjectTeam team = r.findOne(new ProjectTeamId(projectId, teamId));
		ArrayList<ProjectTeamPerson> p = new ArrayList<ProjectTeamPerson>();
		ArrayList<Category> c = new ArrayList<Category>();
		for (ProjectTeamPerson x : team.getProjectTeamPersonnel()) p.add(x);
		for (ProjectWbs x : team.getProject().getProjectWbses()) c.add(x.getCategory());
		
		String[][] s = new String[p.size()+3][c.size()*2+2];

		s[0][0] = Labels.dmyDF.format(date);
		s[1][0] = team.getCaption();
		for (int i = 0; i < c.size(); i++) {
			s[0][i*2+2] = c.get(i).getId()+"";
			s[1][i*2+2] = c.get(i).getCaption() + "<br>" + c.get(i).getTreeCode();
			s[2][i*2+2] = "MH";
			s[2][i*2+3] = "OT";
		}
		
//		int[] totals = new int[c.size()*2+2];
//		for (int i = 0; i < totals.length; i++) {
//			totals[i] = 0;
//		}
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, 0, date);
		for (int i = 0; i < p.size(); i++) {
			id.setWorkerId(p.get(i).getId().getWorkerId());
			s[i+3][0] = p.get(i).getWorker().getId()+"";
			s[i+3][1] = p.get(i).getWorker().getCaption();
			for (int j = 0; j < c.size(); j++) {
				id.setCategoryId(c.get(j).getId());
				ProjectWbsManhour data = pwms.findById(id);
				if (data != null) {
					s[i + 3][j*2 + 2] = data.getManhour()+"";
					s[i + 3][j*2 + 3] = data.getOvertime()+"";
//					totals[j*2+2] = totals[j*2+2] + data.getManhour();
//					totals[j*2+3] = totals[j*2+3] + data.getManhour();
				} else {
					s[i + 3][j*2 + 2] = "";
					s[i + 3][j*2 + 3] = "";
				}
			}
		}
//		s[p.size()+3][0] = Labels.TOTAL;
//		for (int i = 2; i < totals.length; i++) {
//			s[p.size()+3][i] = totals[i] + "";
//		}
		return s;
	}

//	String catIds = "";
//  catIds = catIds+","+c.get(i).getId();
//	s[1][c.size()+1] = Labels.OVERTIME;
//	s[1][c.size()+2] = Labels.FOREIGN;
//	s[1][c.size()+3] = Labels.LOCALMH;
//	s[1][c.size()+4] = Labels.TRMH;
//			int overtime=0;
//			int foreign=0;
//			int localmh=0;
//			int trmh=0;
//			s[i + 3][j + 2] = "<a href=\"/projectWbsManhour/edit?id=" + id.toString() + "\">" + data.getManhour() + "</a>";
//			overtime = overtime + data.getOvertime();
//			foreign = foreign + data.getForeignMh();
//			localmh = localmh + data.getLocalMh();
//			trmh = trmh + data.getTrMh();
//			s[i + 3][j + 1] = "<a href=\"/projectWbsManhour/new?id=" + id.toString() + "\">" + language.getText(Labels.EDIT) + "</a>";
//			totals[c.size()+1] = totals[c.size()+1] + overtime;
//			totals[c.size()+2] = totals[c.size()+2] + foreign;
//			totals[c.size()+3] = totals[c.size()+3] + localmh;
//			totals[c.size()+4] = totals[c.size()+4] + trmh;
//			s[i + 2][c.size() + 2] = overtime+"";
//			s[i + 2][c.size() + 3] = foreign+"";
//			s[i + 2][c.size() + 4] = localmh+"";
//			s[i + 2][c.size() + 5] = trmh+"";
//			s[i + 2][c.size() + 6] = "";
	
	public void setWbsManhourByDate(int projectId, int teamId, Date date, ProjectWbsManhourService pwms, String[][] list) {
		ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, 0, teamId, 0, date);
		for (int i = 2; i < list.length; i++) {
			int workerId = Integer.parseInt(list[i][0]);
			id.setWorkerId(workerId);
			for (int j = 3; j < list.length; j++) {
				int categoryId = Integer.parseInt(list[i][j]);
				id.setCategoryId(categoryId);
				ProjectWbsManhour data = pwms.findById(id);
				data.setManhour(Short.parseShort(list[i][j]));
				pwms.save(data);
			}
		}
	}

	@Override
	public ProjectTeam newEntityWithId(String strId) {
		ProjectTeam entity = new ProjectTeam();
		entity.setId(StrToId(strId));
		return entity;
	}


}
