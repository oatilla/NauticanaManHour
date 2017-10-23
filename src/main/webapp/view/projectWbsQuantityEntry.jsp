<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="box box-primary">
	<form name="f" id="f">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<input type=hidden name=operation value="SEARCH">

		<select name=projectId id=projectId onChange="doAjaxGet('projectWbsQuantity/select?projectId=' + document.f.projectId.value + '&begda=' + document.f.begda.value + '&endda=' + document.f.endda.value);">
	    <c:forEach var="project" items="${projects}" varStatus="status">
		<c:choose>
			<c:when test="${projectId == project.id}">
				<option value="${project.id}" selected> ${project.caption} </option>
			</c:when>
			<c:otherwise>
				<option value="${project.id}"> ${project.caption} </option>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		</select>
	</div>

	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th rowspan=2 align="center">${TEAM_CAPTION}</th>
        <th rowspan=2 align="center">${CAT_CAPTION}</th>
        <th rowspan=2>${UNIT}</th>
        <th colspan=3 align="center">${CONTRACT}</th>
        <th colspan=3 align="center">${TOTAL}</th>
        <th colspan=3 align="center">${LAST}</th>
        <th colspan=3 align="center">${NEW} *default dates</th>
        
      </tr>
      <tr>
        <th>${METRIC}</th>
        <th>${QUANTITY}</th>
        <th>${WORKFORCE}</th>
        <th>${METRIC}</th>
        <th>${QUANTITY}</th>
        <th>${MANHOUR}</th>
        <th>${BEGDA}</th>
        <th>${ENDDA}</th>
        <th>${LAST_QUANTITY}</th>
		<th>${BEGDA}<input type="date" name="begda" id="begda" value="${begda}"> </th>
		<th>${ENDDA}<input type="date" name="endda" id="endda" value="${endda}"> </th>
		<th>${QUANTITY}</th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <th>${record.teamCaption} <input type="hidden" name="teamId${status.index}" id="teamId${status.index}" value="${record.id.teamId}"> </th>
          <th>${record.treeCode} ${record.catCaption} <input type="hidden" name="categoryId${status.index}" id="categoryId${status.index}" value="${record.id.categoryId}"> </th>
          <th>${record.unit}</th>
          <th>${record.metric}</th>
          <th>${record.quantity}</th>
          <th>${record.workforce}</th>
          <th><fmt:formatNumber value="${record.sumManhour/record.sumQuantity}" maxFractionDigits="2"/></th>
          <th>${record.sumQuantity}</th>
          <th>${record.sumManhour}</th>
		  <th><fmt:formatDate value="${record.begda}" pattern="dd-MM-yyyy"/></th>
          <th><fmt:formatDate value="${record.endda}" pattern="dd-MM-yyyy"/><input type="hidden" name="lastEndda${status.index}" id="lastEndda${status.index}" value="${record.endda}"></th>
          <th>${record.lastQuantity}</th>
          <td> <input type="date" name="begda${status.index}" id="begda${status.index}"> </td>
          <td> <input type="date" name="endda${status.index}" id="endda${status.index}"> </td>
          <td> <input type="text" name="quantity${status.index}" id="quantity${status.index}"> </td>
        </tr>
      </c:forEach>
    </table>
    </div>
	<div class="box-footer">
			<a href="#" class="btn btn-primary" onClick="doAjaxPost('${postlink}');"> ${SAVE} </a>
			<a href="#" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> ${CANCEL} </a> 
	</div>
	</form>
</div>
