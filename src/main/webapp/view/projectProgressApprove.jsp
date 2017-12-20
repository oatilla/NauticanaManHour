<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<form name="f" id="f" method="post">
		<table class="table table-bordered table-hover">
			<tr>
				<th> ${PROJECT}	</th>
				<td>
					<select name=projectId id=projectId onChange="doAjaxGet('project/approveProgress?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
					<option value=""> </option>
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${project.id == projectId}">
							<option value="${project.id}" selected> ${project.caption} </option>
							</c:when>
							<c:otherwise>
							<option value="${project.id}"> ${project.caption} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th> ${PERIOD}</th>
				<td><input type="text" name="year" id="year" value="${year}" onChange="doAjaxGet('project/approveProgress?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
					<select id="month" name="month" onChange="doAjaxGet('project/approveProgress?projectId=' + document.f.projectId.value + '&year=' + document.f.year.value + '&month=' + document.f.month.value);">
					<c:forEach var="m" items="${MONTHS_LIST}">
						<c:choose>
							<c:when test="${month == m.key}">
							<option value="${m.key}" selected> ${m.value} </option>
							<c:set var="MONTH_NAME" value="${m.value}"></c:set>
							</c:when>
							<c:otherwise>
							<option value="${m.key}"> ${m.value} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<th> &nbsp; </th>
				<td>
					<c:if test="${!empty minInitialDate}">
						<a href="#"  class="btn btn-primary" onclick="doAjaxPost('project/approveProgress');"> ${APPROVE} </a>
					</c:if>
					<c:if test="${!empty minApprovedDate}">
						<a href="#"  class="btn btn-primary" onclick="doAjaxPost('project/approveProgress?withdraw=YES');"> ${APPROVE_WITHDRAW} </a>
					</c:if>

					<a href="/" class="btn btn-warning"> ${CANCEL} </a>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th colspan=2 align="center">${CAT_CAPTION}</th>
        <th colspan=2 align="center">${CUSTOMER_WBS_CAPTION}</th>
        <th rowspan=2 align="center">${UNIT}</th>
        <th colspan=3 align="center">${MONTH_NAME} ${year} ${REALISED} </th>
        <th colspan=3 align="center">${TOTAL} ${REALISED}</th>
        <th colspan=3 align="center">${METRIC}</th>
      </tr>
      <tr>
        <th>${CODE}</th>
        <th>${CAPTION}</th>
        <th>${CODE}</th>
        <th>${CAPTION}</th>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
        <th>${TENDER}</th>
        <th>${PUP}</th>
        <th>${PLANNED}</th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.treeCode}</td>
          <td>${record.catCaption}</td>
          <td>${record.customerWbsCode}</td>
          <td>${record.customerWbsCaption}</td>
          <td>${record.unit}</td>
          <td> <c:if test="${record.termWorkforce != 0}"> ${record.termWorkforce} </c:if> </td>
          <td> <c:if test="${record.termQuantity != 0}">  ${record.termQuantity} </c:if> </td>
          <td> <c:if test="${record.termMetric != 0}">    <fmt:formatNumber value="${record.termMetric}" maxFractionDigits="2"/> </c:if> </td>
          <td> <c:if test="${record.sumWorkforce != 0}">  ${record.sumWorkforce} </c:if> </td>
          <td> <c:if test="${record.sumQuantity != 0}">   ${record.sumQuantity} </c:if> </td>
          <td> <c:if test="${record.sumMetric != 0}">     <fmt:formatNumber value="${record.sumMetric}" maxFractionDigits="2"/> </c:if> </td>
          <td> <c:if test="${record.metric != 0}">        ${record.metric} </c:if> </td>
          <td> <c:if test="${record.pupMetric != 0}">     ${record.pupMetric} </c:if> </td>
          <td> <c:if test="${record.plannedMetric != 0}"> ${record.plannedMetric} </c:if> </td>
        </tr>
      </c:forEach>
    </table>
    </div>
</div>

<script> 
$(document).ready(function () { $('#dataTable1').DataTable({
		 	'paging':false, 
		 	'scrollY':'50vh',
			'scrollX': true,
		 	'width': '100%',
		 	'lengthChange':false, 
		 	'searching':false, 
		 	'ordering':false, 
		 	'info':true, 
		 	'autoWidth': true ,
		 	'responsive': true,
		 	'scrollCollapse': false,
		 	dom: 'Bfrtip',
		    buttons: [
		        'copyHtml5',
		        'excelHtml5',
		        {
		            "extend": "pdfHtml5",
		            "orientation": "landscape",
		            "text" : "PDF"		                    
		        },
		        'print'
		    ]
			
		}).columns.adjust().draw();
}) 
</script>