<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
  	<p> ${SUBCONTRACTOR_ID} ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/edit?id=${record.id}"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a> </p>
  	
  	<h3> ${WORKER} </h3>
  
    <a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"> ${NEW} </i> </a>
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
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/edit?id=${worker.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('worker/delete?id=${worker.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>