<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record" id="f">
    <input type=hidden name=nextpage value="/authorityGroup/show?id=${record.id.authorityGroup}">
	<table class="table table-condensed">
		<tr>
			<th> ${AUTHORITY_GROUP} </th>
	        <td> ${record.id.authorityGroup} <form:input type="hidden" path="id.authorityGroup"  /></td>
		</tr>
		<tr>
			<th> ${TABLENAME} </th>
			<td> <form:input path="id.tablename"  /></td>
		</tr>
		<tr>
			<th> ${ALLOW_SELECT} </th>
			<td> <form:input path="allowSelect" /></td>
		</tr>
		<tr>
			<th> ${ALLOW_INSERT} </th>
			<td> <form:input path="allowInsert" /></td>
		</tr>
		<tr>
			<th> ${ALLOW_UPDATE} </th>
			<td> <form:input path="allowUpdate" /></td>
		</tr>
		<tr>
			<th> ${ALLOW_DELETE} </th>
			<td> <form:input path="allowDelete" /></td>
		</tr>
		<tr>
        <td colspan="2" align="center">
			<a href="#" onclick="doAjaxPost('tableAuthorization/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
		<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
		</tr>
	</table>
	</form:form>
</div>
