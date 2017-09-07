<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
<link href="../s/eds.css" rel="stylesheet" />
<link href="../s/tab.css" rel="stylesheet" />
<script type="text/javascript" language="JavaScript">
function callSelectionProjectWBS(id) {
  item0 = document.f.WBS_IDS;
  selectWindow = window.open("projectWBSSelect?id=" + id, "selectWindow", "toolbar=no,menubar=no,scrollbar=yes");
  selectWindow.item0 = item0;
}
</script>
</head>

<body>

<div align="center">
	<h3><a href="edit?id=${projectId}"><c:out value="${EDIT}" /></a></h3>
	<table>
		<tr>
			<th><c:out value="${PROJECT_ID}" /></th>
			<td><c:out value="${projectId}" /></td>
		</tr>
		<tr>
			<th><c:out value="${CAPTION}" /></th>
			<td><c:out value="${projectCaption}" /></td>
		</tr>
	</table>

	<div class="tabarea">
	
		<div id="tab1">
  		<a href="#tab1"> ${PROJECT_TEAM} </a>
			<div>
			<a href="/projectTeam/new?parentKey=${projectId}"><c:out value="${NEW}" /></a>
			<table>
				<tr>
					<th><c:out value="${ORDER}" /></th>
					<th><c:out value="${TEAM_ID}" /></th>
					<th><c:out value="${CAPTION}" /></th>
					<th><c:out value="${BEGIN}" /></th>
					<th><c:out value="${END}" /></th>
				</tr>
				<c:forEach var="record" items="${projectTeams}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${record.id.teamId}</td>
					<td>${record.caption}</td>
					<td>${record.begDate}</td>
					<td>${record.endDate}</td>
					<td>
						<a href="edit?id=${record.id.projectId},${record.id.teamId}"> <c:out value="${EDIT}" /> </a> &nbsp;
						<a href="delete?id=${record.id.projectId},${record.id.teamId}"> <c:out value="${DELETE}" /> </a> &nbsp;
						<a href="/projectTeamPerson/list?id=${record.id.projectId},${record.id.teamId}"> <c:out value="${PROJECT_TEAM_PERSON}" /> </a> &nbsp;
					</td>
				</tr>
				</c:forEach>             
			</table>
			</div>  
		</div>  
    
		<div id="tab2">
  		<a href="#tab2"> ${PROJECT_WBS} </a>
			<div>

			<form name=f method=post>
				<input type="button" onClick="if (callSelectionProjectWBS('${projectId}');) then submit();" value='<c:out value="${CHOOSE}" />'>
				<input type=hidden name=PROJECT_ID value="${projectId}">
				<input type=hidden name=WBS_IDS value="">
			</form>

			<table>
				<tr>
					<th><c:out value="${TREE_CODE}" /></th>
					<th><c:out value="${CAPTION}" /></th>
					<th><c:out value="${UNIT}" /></th>
					<th><c:out value="${METRIC}" /></th>
					<th><c:out value="${WORKFORCE}" /></th>
					<th><c:out value="${PUP_METRIC}" /></th>
					<th><c:out value="${PUP_WORKFORCE}" /></th>
				</tr>
				<c:forEach var="record" items="${projectWbses}" varStatus="status">
				<tr>
					<td>${record.category.treeCode}</td>
					<td>${record.category.caption}</td>
					<td>${record.unit}</td>
					<td>${record.metrif}</td>
					<td>${record.workforce}</td>
					<td>${record.pupMetric}</td>
					<td>${record.pupWorkforce}</td>
					<td>
						<a href="/projectWbs/edit?id=${record.id.projectId},${record.id.categoryId}"> <c:out value="${EDIT}" /> </a> &nbsp;
						<a href="/projectWbs/delete?id=${record.id.projectId},${record.id.categoryId}"> <c:out value="${DELETE}" /> </a> &nbsp;
						<a href="/projectWbsManhour/list?id=${record.id.projectId},${record.id.categoryId}"> <c:out value="${PROJECT_WBS_MANHOUR}" /> </a> &nbsp;
						<a href="/projectWbsQuantity/list?id=${record.id.projectId},${record.id.categoryId}"> <c:out value="${PROJECT_WBS_MANHOUR}" /> </a> &nbsp;
					</td>
				</tr>
				</c:forEach>             
			</table>
			</div>  
		</div>  
	</div>  
</div>

</body>

</html>