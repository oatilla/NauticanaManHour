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
			<td> ${record.id.CategoryId} ${record.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
		</tr>
	</table>

	<p> ${PROJECT_WBS_QUANTITY} </p>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th><c:out value="${TREE_CODE}" /></th>
			<th><c:out value="${CAPTION}" /></th>
			<th><c:out value="${UNIT}" /></th>
			<th><c:out value="${METRIC}" /></th>
			<th><c:out value="${QUANTITY}" /></th>
			<th><c:out value="${PUP_METRIC}" /></th>
			<th><c:out value="${PUP_QUANTITY}" /></th>
	 		<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.category.treeCode}</td>
			<td> <a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/show?id=${record.id.projectId},${record.id.categoryId}');"> ${record.category.caption} </a> </td>
			<td>${record.unit}</td>
			<td>${record.metric}</td>
			<td>${record.quantity}</td>
			<td>${record.pupMetric}</td>
			<td>${record.pupQuantity}</td>
			<td>
			 <a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${record.id.projectId},${record.id.categoryId}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
			 <a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbs/delete?id=${record.id.projectId},${record.id.categoryId}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>

	<p> ${PROJECT_WBS_MANHOUR} </p>

</div>