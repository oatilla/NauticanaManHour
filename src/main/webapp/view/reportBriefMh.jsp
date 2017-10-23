<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="box box-primary">
	<form name="f" id="f" method="post">
	<input type=hidden name=projectId value="${projectId}">

	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} <BR> ${project.caption} </h3>
	</div>


	<div class="box-body">
    
    <table id="dataTable1" class="table table-bordered table-hover table-editable">
      <thead>
      <tr>
        <th rowspan=2 align="center">${TREE_CODE}</th>
        <th rowspan=2 align="center">${CAT_CAPTION}</th>
        <th rowspan=2 align="center">PHYSICAL_PROGRESS</th>
        <th colspan=3 align="center">${MANHOUR}</th>
		<th rowspan=2 align="center">EARNED_MH</th>
        <th rowspan=2 align="center">PERFORMANS</th>
        <th rowspan=2 align="center">PERFORMANS_MULTIP</th>
		<th rowspan=2 align="center">ESTIMATED_MH</th>
        <th rowspan=2 align="center">REMAINING_MH</th>
        <th rowspan=2 align="center">DEVIATION</th>
       
      </tr>
      <tr>
        <th>PROPOSAL_MANHOUR</th>
        <th>PROJECT_MANHOUR</th>
        <th>REALIZED_MANHOUR</th>
       </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
        	<td>${record.treeCode}</td>
        	<td>${record.catCaption}</td>
		 	<td><fmt:formatNumber value="${record.progress}" maxFractionDigits="2"/></td>
    		<td>${record.workforce}</td>
        	<td>${record.pupWorkforce}</td>
     		<td>${record.sumManhour}</td>
      		<td>${record.earnedManhour}</td>
    		<td><fmt:formatNumber value="${record.performans}" maxFractionDigits="2"/></td>
          	<td><fmt:formatNumber value="${record.performansMultip}" maxFractionDigits="2"/></td>
           	<td><fmt:formatNumber value="${record.estimatedCompMH}" maxFractionDigits="2"/></td>
         	<td><fmt:formatNumber value="${record.remainingMH}" maxFractionDigits="2"/></td>
           	<td><fmt:formatNumber value="${record.deviation}" maxFractionDigits="2"/></td>
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
