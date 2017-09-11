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
	</table>
</div>

<div align="center">
	<p> ${PROJECT_WBS_QUANTITY} Bu form WBS aktivitelerini içerecek, henüz yazılmadı </p>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
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
		</thead>
		
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
			 <a href="#" onclick="doAjaxPost('projectWbs/edit?id=${record.id.projectId},${record.categoryId}');"> ${EDIT} </a>
			 <a href="#" onclick="doAjaxPost('projectWbs/delete?id=${record.id.projectId},${record.categoryId}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>

	<p> ${PROJECT_WBS_MANHOUR} </p>

</div>