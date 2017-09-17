<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">



	<h3> ${PAGETITLE} </h3>
	<form:form id="f" name="f" method="post" modelAttribute="record">
	<table>
		<tr>
			<th> ${PROJECT_ID} </th>
			<td> ${record.id} <form:hidden path="id" /></td>
		</tr>
		<tr>
			<th> ${CAPTION} </th>
			<td><form:input path="caption" /></td>
		</tr>
		<tr>
			<th> ${COUNTRY} </th>
			<td><form:select path="country" items="${COUNTRY_LIST}"/></td>
		</tr>
		<tr>
			<th> ${START_TIME} </th>
			<td><form:input path="startDate" /></td>
		</tr>
		<tr>
			<th> ${DURATION} </th>
			<td><form:input path="duration" /></td>
		</tr>
		<tr>
        <td colspan="2" align="center">
			<button  class="btn btn-primary" onClick="doAjaxPost('project/list');"> <i class="${SAVE_ICON}"></i> ${SAVE} </button> 
			<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
		</tr>
	</table>
	</form:form>
</div>
