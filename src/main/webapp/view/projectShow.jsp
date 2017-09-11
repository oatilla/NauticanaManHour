<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}

<h3> ${PAGETITLE} </h3>

<div align="center">
	<p><a href="#" onclick="doAjaxPost('project/edit?id=${record.id}');"> ${EDIT} </a></p>
	<p> ${PROJECT_ID} : ${record.id} ${record.caption}</p>

	<div class="tabarea">
	
		<div id="tab1">
	  		<a href="#tab1"> ${PROJECT_TEAM} </a>
			<div>
				<a href="#" onclick="doAjaxPost('projectTeam/new?parentKey=${record.id}');"> ${NEW} </a>

			    <table id="dataTable1" class="table table-bordered table-hover">
	  				<thead>
					<tr>
						<th> ${TEAM_ID} </th>
						<th> ${CAPTION} </th>
						<th> ${BEG_DATE} </th>
						<th> ${END_DATE} </th>
						<th> &nbsp; </th>
					</tr>
					</thead>
					
					<c:forEach var="projectTeam" items="${record.projectTeams}" varStatus="status">
					<tr>
						<td>${projectTeam.id.teamId}</td>
						<td> <a href="#" onclick="doAjaxPost('projectTeam/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${projectTeam.caption} </a> </td>
						<td>${projectTeam.begDate}</td>
						<td>${projectTeam.endDate}</td>
						<td>
							<a href="#" onclick="doAjaxPost('projectTeam/edit?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${EDIT} </a>
							<a href="#" onclick="doAjaxPost('projectTeam/delete?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${DELETE} </a>
						</td>
					</tr>
					</c:forEach>             
				</table>
			</div>  
		</div>  
    
		<div id="tab2">
  		<a href="#tab2"> ${PROJECT_WBS} </a>
			<div>

			<a href="projectWbs/select?id=${record.id}"> ${CHOOSE} </a>

			    <table id="dataTable2" class="table table-bordered table-hover">
				<thead>
				<tr>
					<th> ${TREE_CODE} </th>
					<th> ${CAPTION} </th>
					<th> ${UNIT} </th>
					<th> ${METRIC} </th>
					<th> ${WORKFORCE} </th>
					<th> ${PUP_METRIC} </th>
					<th> ${PUP_WORKFORCE} </th>
					<th> &nbsp; </th>
				</tr>
				</thead>
				
				<c:forEach var="projectWbs" items="${record.projectWbses}" varStatus="status">
				<tr>
					<td>${projectWbs.category.treeCode}</td>
					<td>${projectWbs.category.caption}</td>
					<td>${projectWbs.unit}</td>
					<td>${projectWbs.metric}</td>
					<td>${projectWbs.workforce}</td>
					<td>${projectWbs.pupMetric}</td>
					<td>${projectWbs.pupWorkforce}</td>
					<td>
						<a href="projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${EDIT} </a> &nbsp;
						<a href="projectWbs/delete?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${DELETE} </a> &nbsp;
						<a href="projectWbsManhour/list?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${PROJECT_WBS_MANHOUR} </a> &nbsp;
						<a href="projectWbsQuantity/list?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${PROJECT_WBS_MANHOUR} </a> &nbsp;
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
</div>