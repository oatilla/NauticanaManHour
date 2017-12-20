<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
:<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
	<table class="table table-bordered table-hover">
		<tr>
			<th> ${PROJECT_ID} </th>
	        <td> ${record.id.projectId} ${record.project.caption} </td>
		</tr>
		<tr>
        	<th> ${CATEGORY_ID} </th>
			<td> ${record.id.categoryId} ${record.category.caption} </td>
		</tr>
		<tr>
        	<th> ${CBS_CODE} </th>
			<td> ${record.cbs.id} ${record.cbs.caption} </td>
		</tr>
	</table>
	</div>
</div>


<div class="nav-tabs-custom">

	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">${PROJECT_WBS_QUANTITY}</a></li>
		<li><a href="#tab2" data-toggle="tab">${PROJECT_WBS_MANHOUR}</a></li>
	</ul>


<div class="tab-content">

<div class="tab-pane active" id="tab1">

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PROJECT_WBS_QUANTITY} </h3>
	</div>
	
	<div class="box-body">

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th>${BEGDA}</th>
			<th>${ENDDA}</th>
			<th>${TEAM_ID}</th>
			<th>${QUANTITY}</th>
			<th>${STATUS}</th>
	 		<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="qty" items="${record.projectWbsQuantities}" varStatus="status">
		<tr>
			<td><fmt:formatDate value="${qty.id.begda}" pattern="yyyy.MM.dd"/></td>
			<td><fmt:formatDate value="${qty.endda}" pattern="yyyy.MM.dd"/></td>
			<td>${qty.projectTeam.caption}</td>
			<td>${qty.quantity}</td>
			<td>${qty.status}</td>
			<td>
				<c:if test="${qty.status == 'INITIAL'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbsQuantity/edit?id=${qty.id.projectId},${qty.id.categoryId},${qty.id.teamId},<fmt:formatDate value="${qty.id.begda}" pattern="dd-MM-yyyy"/>');"> ${EDIT} </a>
					<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbsQuantity/delete?id=${qty.id.projectId},${qty.id.categoryId},${qty.id.teamId},<fmt:formatDate value="${qty.id.begda}" pattern="dd-MM-yyyy"/>');"> ${DELETE} </a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>

	</div>
</div>

</div>

<div class="tab-pane" id="tab2">

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PROJECT_WBS_MANHOUR} </h3>
	</div>
	
	<div class="box-body">

    <table id="dataTable2" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th>${ACTIVITY_DATE}</th>
			<th>${TEAM_ID}</th>
			<th>${WORKER_ID}</th>
			<th>${MANHOUR}</th>
			<th>${STATUS}</th>
	 		<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="mh" items="${record.projectWbsManhours}" varStatus="status">
		<tr>
			<td><fmt:formatDate value="${mh.id.activityDate}" pattern="yyyy.MM.dd"/></td>
			<td>${mh.projectTeamPerson.projectTeam.caption} </td>
			<td>${mh.projectTeamPerson.worker.caption}</td>
			<td>${mh.manhour}</td>
			<td>${mh.status}</td>
			<td>
				<c:if test="${mh.status == 'INITIAL'}">
					<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbsManhour/delete?id=${mh.id.projectId},${mh.id.categoryId},${mh.id.teamId},${mh.id.workerId},<fmt:formatDate value="${mh.id.activityDate}" pattern="yyyy-MM-dd"/>');"> ${DELETE} </a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
	</table>

	</div>
</div>

</div>

</div>

<div class="box-footer">
	<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
</div>

</div>

${DATATABLE1}
${DATATABLE2}

<script type="text/javascript">
$(function() {
	$("a[data-toggle='tab']").on('shown.bs.tab', function (e) {
		   $($.fn.dataTable.tables(true)).DataTable()
		      .columns.adjust()
		      .responsive.recalc()
		      .scroller.measure()
		      .fixedColumns().relayout();
	});
});
</script>
