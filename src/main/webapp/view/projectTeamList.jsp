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
			<td><c:out value="${projectId}" /></td>
		</tr>
		<tr>
			<th><c:out value="${CAPTION}" /></th>
			<td><c:out value="${caption}" /></td>
		</tr>
	</table>
</div>

<div align="center">
	<p> <c:out value="${PROJECT_TEAM}" /> </p>
	<a href="new?PROJECT_ID=<c:out value="${projectId}" />"><c:out value="${NEW}" /></a>
	<table>
		<tr>
			<th><c:out value="${ORDER}" /></th>
			<th><c:out value="${TEAM_ID}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${BEGIN}" /></th>
			<th><c:out value="${END}" /></th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${status.index + 1}</td>
			<td>${record.teamId}</td>
			<td>${record.caption}</td>
			<td>${record.begDate}</td>
			<td>${record.endDate}</td>
			<td>
				<a href="edit?projectId=${record.projectId}&id=${record.teamId}"> <c:out value="${EDIT}" /> </a> &nbsp;
				<a href="delete?projectId=${record.projectId}&id=${record.teamId}"> <c:out value="${DELETE}" /> </a> &nbsp;
				<a href="person/list?projectId=${record.projectId}&id=${record.teamId}"> <c:out value="${PROJECT_TEAM_PERSON}" /> </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
  </div>

</body>
</html>
