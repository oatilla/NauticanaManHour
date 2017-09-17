<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
${DATATABLE2}

<script type="text/javascript">
function callSelectionProjectTeamPerson(projectId, teamId) {
  item0 = document.worker.WORKER_ID;
  item1 = document.worker.CAPTION;
  selectWindow = window.open("projectTeamSelect?projectId=" + projectId + "&teamId=" + teamId, "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}
</script>

<div align="center">
	<h3> ${PAGETITLE} </h3>
	<table>
		<tr>
			<th> ${PROJECT_ID} </th>
	        <td> ${record.project.id} ${record.project.caption} </td>
		</tr>
		<tr>
        	<th> ${TEAM_ID} </th>
			<td> ${record.id.teamId} ${record.caption} </td>
		</tr>
	</table>

	<div class="tabarea">
	
		<div id="tab1">
	  		<a href="#tab1"> ${PROJECT_TEAM_PERSON} </a>
			<div>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectPersonnel?parentKey=${record.id.projectId},${record.id.teamId}&nextpage=/projectTeam/show?id=${record.id.projectId},${record.id.teamId}');"> <i class="${NEW_ICON}"></i> ${NEW} ${PERSONNEL} </a> 
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/selectWorker?parentKey=${record.id.projectId},${record.id.teamId}&nextpage=/projectTeam/show?id=${record.id.projectId},${record.id.teamId}');"> <i class="${NEW_ICON}"></i> ${NEW} ${SUBCONTRACTOR} </a>

			    <table id="dataTable1" class="table table-bordered table-hover">
					<thead>
					<tr>
						<th> ${TEAM_LEAD} </th>
						<th> ${PERSONNEL} </th>
						<th> ${SUBCONTRACTOR} </th>
	 					<th> &nbsp; </th>
					</tr>
					</thead>
		
				<c:forEach var="projectTeamPerson" items="${record.projectTeamPersonnel}" varStatus="status">
					<tr>
						<td>${projectTeamPerson.teamLead}</td>
				<c:choose>
					<c:when test="${empty projectTeamPerson.worker.personId}">
						<td> &nbsp; </td>
						<td>${projectTeamPerson.worker.caption}</td>
					</c:when>
					<c:otherwise>
						<td>${projectTeamPerson.worker.subcontractor.caption} / ${projectTeamPerson.worker.caption}</td>
						<td> &nbsp; </td>
					</c:otherwise>
				</c:choose>
						<td>
							<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeamPerson/delete?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId},${projectTeamPerson.id.workerId}&nextpage=/projectTeam/show?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
		
		<div id="tab2">
  		<a href="#tab2"> ${PROJECT_TEAM_TEMPLATE} </a>
			<div>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeamTemplate/select?id=${record.id.projectId,record.id.teamId}');"> <i class="${CHOOSE_ICON}"> </i> ${CHOOSE} </a>
			    <table id="dataTable2" class="table table-bordered table-hover">
				<thead>
				<tr>
					<th> ${TREE_CODE} </th>
					<th> ${CAPTION} </th>
					<th> &nbsp; </th>
				</tr>
				</thead>
				
				<c:forEach var="category" items="${record.projectTeamCategory}" varStatus="status">
				<tr>
					<td>${category.treeCode}</td>
					<td>${category.caption}</td>
					<td>
						<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeamTemplate/delete?id=${record.id.projectId},${record.id.teamId},${category.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
					</td>
				</tr>
				</c:forEach>
			</table>
			</div>
		</div>
	</div>
</div>