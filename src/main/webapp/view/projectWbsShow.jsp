<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
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
        	<th> ${CBS_CODE} </th>
			<td> ${record.cbs.cbsCode} ${record.cbs.caption} <form:input type="hidden" path="cbs" /></td>
		</tr>
	</table>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PROJECT_WBS_QUANTITY} </h3>
	</div>
	
	<div class="box-body">

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
			 <a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${record.id.projectId},${record.id.categoryId}');"> ${EDIT} </a>
			 <a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbs/delete?id=${record.id.projectId},${record.id.categoryId}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>
	</table>

	</div>
</div>