<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(function() {
		// for bootstrap 3 use 'shown.bs.tab', for bootstrap 2 use 'shown' in the next line
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			// save the latest tab; use cookies if you like 'em better:
			localStorage.setItem('lastTab', $(this).attr('href'));
		});

		// go to the latest tab, if it exists:
		var lastTab = localStorage.getItem('lastTab');
		if (lastTab) {
			$('[href="' + lastTab + '"]').tab('show');
		}
	});

	$(document).ready(function() {
	    $('#dataTable2').DataTable( {
	        scrollY:        '50vh',
	        paging:         false
	    } );
	} );
	
</script>


${DATATABLE1} ${DATATABLE2}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title">${PAGETITLE}</h3>
	</div>
	<div class="box-body">
		<p>
			${PROJECT_ID} : ${record.id} ${record.caption} ${record.country}
			${record.contractDate} ${record.duration} <a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');"> ${EDIT} </a>
			<c:choose>
				<c:when test="${record.status == 'APPROVE_WBS'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/approveFinal?id=${record.id}');"> ${APPROVE_FINAL} </a>
				</c:when>
				<c:when test="${record.status == 'APPROVE_FINAL'}">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/withdrawApprove?id=${record.id}');"> ${APPROVE_WITHDRAW} </a>
				</c:when>
			</c:choose>
		</p>
	</div>
</div>


<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">${PROJECT_WBS}</a></li>
		<li><a href="#tab2" data-toggle="tab">${PROJECT_TEAM}</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">

			<div class="box box-info">
				<div class="box-header with-border">
					<c:choose>
						<c:when test="${record.status == 'INITIAL'}">
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/select?id=${record.id}');"> ${CHOOSE} </a>
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/approveWbs?id=${record.id}');"> ${APPROVE_WBS} </a>
						</c:when>
						<c:when test="${record.status == 'APPROVE_WBS'}">
							<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/withdrawApprove?id=${record.id}');"> ${APPROVE_WITHDRAW} </a>
						</c:when>
					</c:choose>
				</div>

				<div class="box-body">

					<table id="dataTable2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th rowspan=2 colspan=2>${CAPTION}</th>
								<th rowspan=2 colspan=2>${CUSTOMER_WBS_CAPTION}</th>
								<th rowspan=2>${CBS_CODE}</th>
								<th rowspan=2>${UNIT}</th>
								<th colspan=3>${TENDER}</th>
								<th colspan=3>${PUP}</th>
								<th colspan=3>${PLANNED}</th>
								<th>&nbsp;</th>
							</tr>
							<tr>
								<th>${METRIC}</th>
								<th>${QUANTITY}</th>
								<th>${WORKFORCE}</th>
								<th>${METRIC}</th>
								<th>${QUANTITY}</th>
								<th>${WORKFORCE}</th>
								<th>${METRIC}</th>
								<th>${QUANTITY}</th>
								<th>${WORKFORCE}</th>
								<th>&nbsp;</th>
							</tr>
						</thead>
						<c:set var="tenderWf" value="0"></c:set>
						<c:set var="pupWf" value="0"></c:set>
						<c:set var="plannedWf" value="0"></c:set>

						<c:forEach var="projectWbs" items="${record.projectWbses}"
							varStatus="status">
							<tr>
								<td>${projectWbs.category.treeCode}</td>
								<td>${projectWbs.category.caption}</td>
								<td>${projectWbs.customerWbsCode}</td>
								<td>${projectWbs.customerWbsCaption}</td>
								<td>${projectWbs.cbs.id}${projectWbs.cbs.caption}</td>
								<td>${projectWbs.unit}</td>
								<td>${projectWbs.metric}</td>
								<td>${projectWbs.quantity}</td>
								<td  class="workforce">${projectWbs.workforce}</td>
								<td>${projectWbs.pupMetric}</td>
								<td>${projectWbs.pupQuantity}</td>
								<td class="workforce">${projectWbs.pupWorkforce}</td>
								<td>${projectWbs.plannedMetric}</td>
								<td>${projectWbs.plannedQuantity}</td>
								<td class="workforce">${projectWbs.plannedWorkforce}</td>
								<c:set var="tenderWf" value="${tenderWf + projectWbs.workforce}"></c:set>
								<c:set var="pupWf" value="${pupWf + projectWbs.pupWorkforce}"></c:set>
								<c:set var="plannedWf" value="${plannedWf + projectWbs.plannedWorkforce}"></c:set>
								<td><c:choose>
										<c:when test="${record.status == 'INITIAL'}">
											<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${EDIT} </a>
											<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectWbs/delete?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${DELETE} </a>
										</c:when>
										<c:otherwise>
											<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectWbs/edit?id=${projectWbs.id.projectId},${projectWbs.id.categoryId}');"> ${EDIT} </a>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
						<tfoot>
								<tr>
									<th colspan=8 >Total</th>
									<th >${tenderWf}</th>
									<th colspan=2> &nbsp;</th>
									<th > ${pupWf} </th>
									<th colspan=2> &nbsp;</th>
									<th > ${plannedWf} </th>
									<th> &nbsp;</th>
								</tr>
						</tfoot>
					</table>
				</div>
			</div>

		</div>

		<div class="tab-pane" id="tab2">

			<div class="box box-info">
				<div class="box-header with-border">
					<a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/new?parentKey=${record.id}');"> ${NEW} </a>
				</div>

				<div class="box-body">
					<table id="dataTable1" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>${TEAM_ID}</th>
								<th>${CAPTION}</th>
								<th>&nbsp;</th>
							</tr>
						</thead>

						<c:forEach var="projectTeam" items="${record.projectTeams}"
							varStatus="status">
							<tr>
								<td>${projectTeam.id.teamId}</td>
								<td><a href="#" onClick="doAjaxGet('projectTeam/show?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${projectTeam.caption} </a></td>
								<td><a class="btn btn-primary" href="#" onClick="doAjaxGet('projectTeam/edit?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${EDIT} </a>
									<a class="btn btn-danger" href="#" onClick="doAjaxGet('projectTeam/delete?id=${projectTeam.id.projectId},${projectTeam.id.teamId}');"> ${DELETE} </a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</div>
	</div>
</div>