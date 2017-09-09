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
	</table>
</div>

<div align="center">
	<p> ${PROJECT_WBS_QUANTITY} Bu form WBS aktivitelerini içerecek, henüz yazılmadı </p>

	<table>
		<tr>
			<th><c:out value="${TREE_CODE}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${UNIT}" /></th>
			<th><c:out value="${METRIC}" /></th>
			<th><c:out value="${WORKFORCE}" /></th>
			<th><c:out value="${PUP_METRIC}" /></th>
			<th><c:out value="${PUP_WORKFORCE}" /></th>
	 		<th> &nbsp; </th>
		</tr>
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.category.treeCode}</td>
			<td>${record.category.caption}</td>
			<td>${record.unit}</td>
			<td>${record.metric}</td>
			<td>${record.workforce}</td>
			<td>${record.pupMetric}</td>
			<td>${record.pupWorkforce}</td>
			<td>
			 <a href="edit?id=${record.id.projectId},${record.categoryId}"> <c:out value="${EDIT}" /> </a> &nbsp;
			 <a href="delete?id=${record.id.projectId},${record.categoryId}"> <c:out value="${DELETE}" /> </a>
			</td>
		</tr>
		</c:forEach>             
	</table>

	<p> ${PROJECT_WBS_MANHOUR} </p>

</div>

</body>
</html>
