<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>

<script type="text/javascript" language="JavaScript">
function callSelectionProjectTeamPerson(projectId, teamId) {
  item0 = document.worker.WORKER_ID;
  item1 = document.worker.CAPTION;
  selectWindow = window.open("projectTeamSelect?projectId=" + projectId + "&teamId=" + teamId, "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
  selectWindow.item1 = item1;
}
</script>
</head>

<body>

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
</div>

<div align="center">
	<p> ${PROJECT_TEAM_PERSON} </p>
	<a href="/projectTeamPerson/new?parentKey=${record.id.projectId},${record.id.teamId}"> ${NEW} </a>
	
	<a href="/projectTeamPerson/selectPersonnel?parentKey=${record.id.projectId},${record.id.teamId}"> ${NEW} ${PERSONNEL} </a>
	<a href="/projectTeamPerson/selectSubcontractor?parentKey=${record.id.projectId},${record.id.teamId}"> ${NEW} ${SUBCONTRACTOR} </a>

	<table>
		<tr>
			<th> ${TEAM_LEAD} </th>
			<th> ${PERSONNEL} </th>
			<th> ${SUBCONTRACTOR} </th>
	 		<th> &nbsp; </th>
		</tr>
		<c:forEach var="projectTeamPerson" items="${record.projectTeamPersonnel}" varStatus="status">
		<tr>
			<td>${projectTeamPerson.teamLead}</td>
			<c:choose>
				<c:when test="${empty projectTeamPerson.worker.personId}">
					<td> &nbsp; </td>
					<td>${projectTeamPerson.worker.caption}</td>
				</c:when>
				<c:otherwise>
					<td>${projectTeamPerson.worker.caption}</td>
					<td> &nbsp; </td>
				</c:otherwise>
			</c:choose>
			<td>
				<a href="edit?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId},${projectTeamPerson.id.workerId}"> ${EDIT} </a> &nbsp;
				<a href="delete?id=${projectTeamPerson.id.projectId},${projectTeamPerson.id.teamId},${projectTeamPerson.id.workerId}"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>
	</table>
  </div>

</body>
</html>
