<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <input type=hidden name=nextpage value="language/show?id=${record.id.langcode}">
	<table>
		<tr>
			<th> ${LANGUAGE} </th>
			<td>${record.id.langcode} <form:input type="hidden" path="id.langcode"  /></td>
		</tr>
		<tr>
			<th> ${CAPTION} </th>
			<td> <form:input path="id.caption" /></td>
		</tr>
		<tr>
			<th> ${LABEL_UPPER} </th>
			<td><form:input path="labelupper" /></td>
		</tr>
		<tr>
			<th> ${LABEL_LOWER} </th>
			<td><form:input path="labellower" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"> <input type="submit" value="${SAVE}"></td>
		</tr>
	</table>
	</form:form>
</div>
