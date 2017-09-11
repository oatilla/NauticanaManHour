<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <input type=hidden name=nextpage value="domainName/show?id=${record.id.domain}">
	<table>
		<tr>
			<th> ${DOMAIN} </th>
			<td>${record.id.domain} <form:input type="hidden" path="id.domain"  /></td>
		</tr>
		<tr>
			<th> ${REFVALUE} </th>
			<td> <form:input path="id.refvalue" /></td>
		</tr>
		<tr>
			<th> ${CAPTION} </th>
			<td><form:input path="caption" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"> <input type="submit" value="${SAVE}"></td>
		</tr>
	</table>
	</form:form>
</div>