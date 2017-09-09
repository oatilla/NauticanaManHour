<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>
</head>

<body>

<div align="center">
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
			<th> ${METRIC} </th>
			<td> ${record.metric} ${record.unit}</td>
		</tr>
		<tr>
			<th> ${WORKFORCE}</th>
			<td> ${record.workforce} ${record.unit}</td>
		</tr>
	</table>
</div>

<div align="center">
	<p> <c:out value="${PROJECT_WBS_QUANTITY}" /> </p>
	<a href="new?parentKey=${record.id.projectId},${record.id.categoryId}"> ${NEW} </a>
	<table>
		<tr>
			<th> ${YEAR} </th>
			<th> ${TERM_TYPE} </th>
			<th> ${TERM_ID} </th>
			<th> ${QUANTITY} </th>
			<th> ${IS_SUBCONTRACTOR} </th>
			<th> &nbsp; </th>
		</tr>
		<c:forEach var="quantity" items="${quantities}" varStatus="status">
		<tr>
			<td>${quantity.year}</td>
			<td>${quantity.term_type}</td>
			<td>${quantity.termId}</td>
			<td>${quantity.quantity}</td>
			<td>${quantity.isSubcontractor}</td>
			<td>
				<a href="edit?id=${quantity.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}"> ${EDIT} </a> &nbsp;
				<a href="delete?id=${record.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}"> ${DELETE} </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
  </div>

</body>
</html>
