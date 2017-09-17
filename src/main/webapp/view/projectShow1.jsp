<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}
<div align="center">
	<h3> ${PAGETITLE} </h3>
	<p> ${PROJECT_ID} : ${record.id} ${record.caption} ${record.country} ${record.startDate} ${record.duration} <a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a> </p>

	<div class="tabarea">
	
		<div id="tab1">
	  		<a href="#tab1"> ${PROJECT_TEAM} </a>
			<div>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"> </i> ${NEW} </a>

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
						<td> <a href="#" onClick="doAjaxGet('projectTeam/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${projectTeam.caption} </a> </td>
						<td>${projectTeam.begDate}</td>
						<td>${projectTeam.endDate}</td>
						<td>
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/edit?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a> &nbsp;
							<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeam/delete?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a> &nbsp;
						</td>
					</tr>
					</c:forEach>             
				</table>
			</div>  
		</div>  
    
		<div id="tab2">
  		<a href="#tab2"> ${PROJECT_WBS} </a>
			<div>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/select?id=${record.id}');"> <i class="${CHOOSE_ICON}"> </i> ${CHOOSE} </a>
			    <table id="dataTable2" class="table table-bordered table-hover">
				<thead>
				<tr>
					<th> ${TREE_CODE} </th>
					<th> ${CAPTION} </th>
					<th> ${UNIT} </th>
					<th> ${METRIC} </th>
					<th> ${QUANTITY} </th>
					<th> ${PUP_METRIC} </th>
					<th> ${PUP_QUANTITY} </th>
					<th> &nbsp; </th>
				</tr>
				</thead>
				
				<c:forEach var="projectWbs" items="${record.projectWbses}" varStatus="status">
				<tr>
					<td>${projectWbs.category.treeCode}</td>
					<td>${projectWbs.category.caption}</td>
					<td>${projectWbs.unit}</td>
					<td>${projectWbs.metric}</td>
					<td>${projectWbs.quantity}</td>
					<td>${projectWbs.pupMetric}</td>
					<td>${projectWbs.pupQuantity}</td>
					<td>
						<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
						<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbs/delete?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
</div>