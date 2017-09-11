<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <input type=hidden name=nextpage value="project/show?id=${record.id.projectId}">
	<table>
		<tr>
			<th> ${PROJECT_ID} </th>
			<td>${record.id.projectId} ${record.project.caption} <form:input type="hidden" path="id.projectId"  /></td>
		</tr>
		<tr>
			<th> ${TEAM_ID} </th>
			<td> ${record.id.teamId} <form:input type="hidden" path="id.teamId" /></td>
		</tr>
		<tr>
			<th> ${CAPTION} </th>
			<td><form:input path="caption" /></td>
		</tr>
		<tr>
			<th> ${BEGDATE} </th>
			<td><form:input path="begDate" /></td>
		</tr>
		<tr>
			<th> ${ENDDATE} </th>
			<td><form:input path="endDate" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"> <input type="submit" value="${SAVE}"></td>
		</tr>
	</table>
	</form:form>
</div>