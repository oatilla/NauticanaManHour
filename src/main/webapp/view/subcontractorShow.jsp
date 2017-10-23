<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
	  	<p> ${SUBCONTRACTOR_ID} ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('subcontractor/edit?id=${record.id}');"> ${EDIT} </a> </p>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${WORKER} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>

	<div class="box-body">
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
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('worker/edit?id=${worker.id}');"> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('worker/delete?id=${worker.id}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<div class="box-footer">
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>
	</div>
</div>