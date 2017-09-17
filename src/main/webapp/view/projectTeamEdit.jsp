<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record" id="f">
    <input type=hidden name=nextpage value="/project/show?id=${record.id.projectId}">
	<table class="table table-condensed">
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
        <td colspan="2" align="center">
			<a href="#" onclick="doAjaxPost('projectTeam/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
		<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
		</tr>
	</table>
	</form:form>
</div>
