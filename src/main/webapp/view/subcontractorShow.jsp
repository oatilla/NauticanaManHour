<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
  
  	<p> ${SUBCONTRACTOR_ID} ${record.id} ${record.caption}</p>
  	
  	<h3> ${WORKER} </h3>
  
	<a href="#" onclick="doAjaxPost('worker/new?parentKey=${record.id}');"> ${NEW} </a>
    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${CAPTION} </th>
			<th> &nbsp; </th>
		</tr>
		</thead>
		<c:forEach var="worker" items="${record.workers}" varStatus="status">
		<tr>
			<td>${worker.caption}</td>
			<td>
				<a href="#" onclick="doAjaxPost('worker/edit?id=${worker.id}');"> ${EDIT} </a>
				<a href="#" onclick="doAjaxPost('worker/delete?id=${worker.id}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>