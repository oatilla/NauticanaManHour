<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>
<link href="../s/eds.css" rel="stylesheet" />
<!--  <link href="../s/tab.css" rel="stylesheet" />  -->
</head>

<body>

<div align="center">
	<h3><a href="edit?id=${record.id}"> ${EDIT} </a></h3>
	<p> ${PROJECT_ID} : ${record.id} ${record.caption}</p>

	<div class="tabarea">
	
		<div id="tab1">
	  		<a href="#tab1"> ${PROJECT_TEAM} </a>
			<div>
				<a href="/projectTeam/new?parentKey=${record.id}"><c:out value="${NEW}" /></a>
				<table>
					<tr>
						<th> ${TEAM_ID} </th>
						<th> ${CAPTION} </th>
						<th> ${BEG_DATE} </th>
						<th> ${END_DATE} </th>
						<th> &nbsp; </th>
					</tr>
					<c:forEach var="projectTeam" items="${record.projectTeams}" varStatus="status">
					<tr>
						<td>${projectTeam.id.teamId}</td>
						<td> <a href="/projectTeam/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId}"> ${projectTeam.caption} </a> </td>
						<td>${projectTeam.begDate}</td>
						<td>${projectTeam.endDate}</td>
						<td>
							<a href="/projectTeam/edit?id=${projectTeam.id.projectId},${projectTeam.id.teamId}"> ${EDIT} </a> &nbsp;
							<a href="/projectTeam/delete?id=${projectTeam.id.projectId},${projectTeam.id.teamId}"> ${DELETE} </a> &nbsp;
						</td>
					</tr>
					</c:forEach>             
				</table>
			</div>  
		</div>  
    
		<div id="tab2">
  		<a href="#tab2"> ${PROJECT_WBS} </a>
			<div>

			<a href="/projectWbs/select?id=${record.id}"> ${CHOOSE} </a>

			<table>
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
						<a href="/projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${EDIT} </a> &nbsp;
						<a href="/projectWbs/delete?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${DELETE} </a> &nbsp;
						<a href="/projectWbsManhour/list?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${PROJECT_WBS_MANHOUR} </a> &nbsp;
						<a href="/projectWbsQuantity/list?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}"> ${PROJECT_WBS_MANHOUR} </a> &nbsp;
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