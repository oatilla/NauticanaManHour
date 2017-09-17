<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div align="center">
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

	<p> ${PROJECT_TEAM_PERSON} </p>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${TEAM_LEAD} </th>
			<th> ${PERSON} </th>
			<c:forEach var="category" items="${categories}" varStatus="status">
			<th>${category.treeCode} <br> ${category.caption}</th>
			</c:forEach>
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
				<a href="#" onClick="doAjaxGet('projectTeamPerson/delete?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId},${projectTeamPerson.id.workerId}&nextpage=/projectTeam/show?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
