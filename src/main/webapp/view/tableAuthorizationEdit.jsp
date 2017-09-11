<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <input type=hidden name=nextpage value="authorityGroup/show?id=${record.id.authorityGroup}">
	<table>
		<tr>
			<th> ${AUTHORITY_GROUP} </th>
	        <td> ${record.id.authorityGroup} <form:input type="hidden" path="id.authorityGroup"  /></td>
		</tr>
		<tr>
			<th> ${TABLENAME} </th>
			<td>${record.id.tablename} <form:input path="id.tablename"  /></td>
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
			<td colspan="2" align="center"> <input type="submit" value="${SAVE}"></td>
		</tr>
	</table>
	</form:form>
</div>