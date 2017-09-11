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
	        <td> ${record.id.projectId} ${record.project.caption} <form:input type="hidden" path="id.projectId"  /></td>
		</tr>
		<tr>
        	<th> ${CATEGORY_ID} </th>
			<td> ${record.id.CategoryId} ${record.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
		</tr>
		<tr>
			<th> ${UNIT} </th>
			<td><form:input path="unit" /></td>
		</tr>
		<tr>
			<th> ${METRIC} </th>
			<td><form:input path="metric" /></td>
		</tr>
		<tr>
			<th> ${WORKFORCE} </th>
			<td><form:input path="workforce" /></td>
		</tr>
		<tr>
			<th> ${PUP_METRIC} </th>
			<td><form:input path="pupMetric" /></td>
		</tr>
		<tr>
			<th> ${PUP_WORKFORCE} </th>
			<td><form:input path="pupWorkforce" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="${SAVE}"> </td>
		</tr>
	</table>
	</form:form>
</div>