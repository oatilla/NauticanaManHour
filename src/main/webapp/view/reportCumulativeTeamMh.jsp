<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="box box-primary">
	<form name="f" id="f" method="post">
	<input type=hidden id=projectId name=projectId value="${projectId}">
	<input type=hidden id=teamId name=teamId value="${teamId}">
	<input type=hidden id=subcontractorId name=subcontractorId value="${subcontractorId}">
	<input type=hidden id=begda name=begda value="${begda}">
	<input type=hidden id=endda name=endda value="${endda}">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<p> ${project.caption} <BR> ${teamCaption} </p>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th rowspan=2 colspan=2 align="center">${CAT_CAPTION}</th>
        <th rowspan=2 colspan=2 align="center">${CBS_CAPTION}</th>
        <th rowspan=2 align="center">${UNIT}</th>
        <th colspan=3 align="center">${begda} - ${endda} ${REALISED} </th>
        <th colspan=3 align="center">${TOTAL} ${REALISED}</th>
        <th rowspan=2 align="center">${PUP_METRIC}</th>
        <th rowspan=2 align="center">${PLANNED_METRIC}</th>
      </tr>
      <tr>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
        <th>${WORKFORCE}</th>
        <th>${QUANTITY}</th>
        <th>${METRIC}</th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.treeCode}</td>
          <td>${record.catCaption}</td>
          <td>${record.cbsCode}</td>
          <td>${record.cbsCaption}</td>
          <td>${record.unit}</td>
          <td>${record.termWorkforce}</td>
          <td>${record.termQuantity}</td>
          <td><fmt:formatNumber value="${record.termMetric}" maxFractionDigits="2"/></td>
          <td>${record.sumWorkforce}</td>
          <td>${record.sumQuantity}</td>
          <td><fmt:formatNumber value="${record.sumMetric}" maxFractionDigits="2"/></td>
          <td>${record.pupMetric}</td>
          <td>${record.plannedMetric}</td>
        </tr>
      </c:forEach>
    </table>
    </div>
	<div class="box-footer">
			<input type=hidden name=data value="">
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
	</div>
	</form>
</div>
