<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
	<p> ${date}</p>
    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
			<tr>
				<th> ${PROJECT} </th>
				<th> ${TEAM} </th>
				<th> ${BEG_DATE} </th>
				<th> ${END_DATE} </th>
				<th> &nbsp; </th>
			</tr>
		</thead>
		<c:forEach var="project" items="${projects}">
		
			<c:forEach var="projectTeam" items="${project.projectTeams}">
				<tr>
					<td> ${project.caption} </td>
					<td> ${projectTeam.caption} </td>
					<td> ${projectTeam.begDate} </td>
					<td> ${projectTeam.endDate} </td>
					<td>
						<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsManhour/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId},${date}');"> <i class="${PROJECT_MANHOUR_ICON}" > </i> </a>
					</td>
				</tr>
			</c:forEach>             
		</c:forEach>
	</table>
</div>