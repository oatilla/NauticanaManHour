<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

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
	<a href="#" onclick="doAjaxPost('projectWbsQuantity/new?parentKey=${record.id.projectId},${record.id.categoryId}');"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${YEAR} </th>
			<th> ${TERM_TYPE} </th>
			<th> ${TERM_ID} </th>
			<th> ${QUANTITY} </th>
			<th> ${IS_SUBCONTRACTOR} </th>
			<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="quantity" items="${quantities}" varStatus="status">
		<tr>
			<td>${quantity.year}</td>
			<td>${quantity.term_type}</td>
			<td>${quantity.termId}</td>
			<td>${quantity.quantity}</td>
			<td>${quantity.isSubcontractor}</td>
			<td>
				<a href="#" onclick="doAjaxPost('projectWbsQuantity/edit?id=${quantity.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}');"> ${EDIT} </a>
				<a href="#" onclick="doAjaxPost('projectWbsQuantity/delete?id=${record.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>