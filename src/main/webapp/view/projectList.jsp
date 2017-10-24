<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
//Confirm delete
$(document).ready(function(){
    $("a").click(function(){
		var data = $(this).attr('id');
    	var buttonId = data.split('_');
		if (buttonId[0] == 'delete'){
			$("#confirmDelete_"+ buttonId[1]).show();
	        $("#cancelDelete_"+ buttonId[1]).show();
	        $("#delete_"+ buttonId[1]).hide();
				}
		else if (buttonId[0] == 'cancelDelete'){
			$("#confirmDelete_"+ buttonId[1]).hide();
	    	$("#delete_"+ buttonId[1]).show();
	    	$("#cancelDelete_"+ buttonId[1]).hide();
		}
    })
});
</script>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
		<c:if test="${empty INSERT_ALLOWED}">
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/new');"> ${NEW} </a>
		</c:if>
	</div>
	
	<div class="box-body">
    
	<table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th>${PROJECT_ID}</th>
			<th>${CAPTION}</th>
			<th>${STATUS}</th>
			<th>${CUSTOMER}</th>
			<th>${COUNTRY}</th>
			<th>${LOCATION}</th>
 			<th> &nbsp; </th>
<!-- 
			<th>${CONTRACT_DATE}</th>
			<th>${AREA_HANDOVER}</th>
			<th>${DURATION}</th>
			<th>${REVIZED_DURATION}</th>
			<th>${REVIZED_COMPLETION}</th>
			<th>${EXPECTED_COMPLETION}</th>
			<th>${END_OF_WARRANTY}</th>
			<th>${CONTRACTED_AMOUNT}</th>
			<th>${CONTRACT_EXCHANGE}</th>
			<th>${EXPECTED_COST}</th>
			<th>${ADVANCE_PERCENT}</th>
			<th>${LETTER_OF_ADVANCE}</th>
			<th>${LETTER_OF_WARRANTY}</th>
			<th>${ORGANIZATION_ID}</th>
 -->
 		</tr>
		</thead>

      <c:forEach var="record" items="${records}" varStatus="status">
		<tr>
			<td>${record.id}</td>
			<td><a href="#" onClick="doAjaxGet('project/show?id=${record.id}');">${record.caption}</a></td>
			<td>${record.status}</td>
			<td>${record.customer}</td>
			<td>${record.country}</td>
			<td>${record.location}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('project/edit?id=${record.id}');"> ${EDIT} </a>
				<a class="btn btn-danger" href="#"  id="delete_${record.id}"> ${DELETE} </a>
				<a class="btn btn-danger" style="display: none;" href="#" onClick="doAjaxGet('project/delete?id=${record.id}');" id="confirmDelete_${record.id}"> <i class="${DELETE_ICON}"> </i> CONFIRM </a>
				<a class="btn btn-danger" style="display: none;" href="#"  id="cancelDelete_${record.id}"> ${CANCEL} </a>
				<a href="#" onClick="doAjaxGet('project/projectWbs/list?projectId=${record.id}');"> ${PROJECT_WBS} </a>
				<a href="#" onClick="doAjaxGet('project/projectTeam/list?projectId=${record.id}');"> ${PROJECT_TEAM} </a>
			</td>
<!-- 
			<td>${record.contractDate}</td>
			<td>${record.areaHandover}</td>
			<td>${record.duration}</td>
			<td>${record.revizedDuration}</td>
			<td>${record.revizedCompletion}</td>
			<td>${record.expectedCompletion}</td>
			<td>${record.endOfWarranty}</td>
			<td>${record.contractedAmount}</td>
			<td>${record.contractExchange}</td>
			<td>${record.expectedCost}</td>
			<td>${record.advancePercent}</td>
			<td>${record.letterOfAdvance}</td>
			<td>${record.letterOfWarranty}</td>
			<td>${record.organizationId}</td>
 -->
 		</tr>
      </c:forEach>
    </table>
    </div>
</div>