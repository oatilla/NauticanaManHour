<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
	<table>
		<tr>
			<th> ${PROJECT_ID} </th>
	        <td> ${record.id.projectId} ${record.project.caption} <form:input type="hidden" path="id.projectId"  /></td>
		</tr>
		<tr>
        	<th> ${CATEGORY_ID} </th>
			<td> ${record.id.categoryId} ${record.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
		</tr>
		<tr>
			<th> ${METRIC} </th>
			<td> ${record.metric} ${record.unit}</td>
		</tr>
		<tr>
			<th> ${QUANTITY}</th>
			<td> ${record.quantity} ${record.unit}</td>
		</tr>
	</table>

	<h3> ${PROJECT_WBS_QUANTITY} </h3>
	
	<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsQuantity/new?parentKey=${record.id.projectId},${record.id.categoryId}');"> <i class="${NEW_ICON}"></i> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${BEGDA} </th>
			<th> ${ENDDA} </th>
			<th> ${QUANTITY} </th>
			<th> ${IS_SUBCONTRACTOR} </th>
			<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="quantity" items="${record.projectWbsQuantities}" varStatus="status">
		<tr>
			<td>${quantity.id.begda}</td>
			<td>${quantity.endda}</td>
			<td>${quantity.quantity}</td>
			<td>${quantity.isSubcontractor}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsQuantity/edit?id=${quantity.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbsQuantity/delete?id=${record.id.projectId},${record.id.category},${record.id.year},${record.id.termType},${record.id.termId}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
