<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
<script type="text/javascript" language="JavaScript">
function callSelectionProjectTeam(id) {
  item0 = document.f.WBS_IDS;
  selectWindow = window.open("projectTeamSelect?id=" + id, "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
}
</script>
</head>
<body>

<div align="center">
	<table>
		<tr>
			<th><c:out value="${PROJECT_ID}" /></th>
			<td><c:out value="${projectId}" /> <c:out value="${caption}" /></td>
		</tr>
		<tr>
			<th><c:out value="${TEAM_ID}" /></th>
			<td><c:out value="${teamId}" /> <c:out value="${teamCaption}" /></td>
		</tr>
		<tr>
			<th><c:out value="${DATE}" /></th>
			<td><c:out value="${begDate}" /> .. <c:out value="${endDate}" /></td>
		</tr>
	</table>
</div>

<div align="center">
	<p> <c:out value="${PROJECT_TEAM}" /> </p>
	<a href="new?PROJECT_ID=<c:out value="${projectId}" />&TEAM_ID=<c:out value="${teamId}" />"><c:out value="${NEW}" /></a>
	<table>
		<tr>
			<th rowspan=2><c:out value="${ORDER}" /></th>
			<th rowspan=2><c:out value="${TEAM_LEAD}" /></th>
			<th colspan=2><c:out value="${PERSONNEL}" /></th>
			<th colspan=2><c:out value="${SUBCONTRACTOR}" /></th>
		</tr>
		<tr>
			<th><c:out value="${PERSON_ID}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${SUBCONTRACTOR_ID}" /></th>
			<th><c:out value="${CAPTION}" /></th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${status.index + 1}</td>
			<td>${record.teamLead}</td>
			<c:choose>
				<c:when test="${empty record.personId}">
					<td> &nbsp; </td>
					<td> &nbsp; </td>
					<td>${record.subcontractorId}</td>
					<td>${record.caption}</td>
				</c:when>
				<c:otherwise>
					<td>${record.personId}</td>
					<td>${record.caption}</td>
					<td> &nbsp; </td>
					<td> &nbsp; </td>
				</c:otherwise>
			</c:choose>	
			<td>
				<a href="edit?id=${record.id.projectId},${record.id.teamId},${record.id.workerId}"> <c:out value="${EDIT}" /> </a> &nbsp;
				<a href="delete?id=${record.id.projectId},${record.id.teamId},${record.id.workerId}"> <c:out value="${DELETE}" /> </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
  </div>

</body>
</html>
