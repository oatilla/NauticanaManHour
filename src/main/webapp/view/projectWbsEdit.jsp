<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
  $(function () {
    //Initialize Select2 Elements
    $(".select2").select2();
  });
</script>
 
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="project/show?id=${record.id.projectId}"/>

	<div class="box-body">

		<table>
			<!--  <tr>
				<th> ${PROJECT_ID} </th>
				<td> ${record.id.projectId} ${record.project.caption} </td>
			</tr>
			-->
			<form:input type="hidden" path="id.projectId"  />
			<tr>
        		<th> ${CATEGORY_ID} </th>
				<td> ${record.id.categoryId} ${record.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
			</tr>
		</table>

		<c:choose>
		<c:when test="${record.project.status == 'INITIAL'}">

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customerWbsCode">${CUSTOMER_WBS_CODE}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="customerWbsCode" />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customerWbsCaption">${CUSTOMER_WBS_CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="customerWbsCaption"/>
			</div>
		</div>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="cbs">${CBS_CODE}</label>
			<div class="col-sm-10"> 
				<form:select class="form-control select2" path="cbs" items="${cbsList}"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="unit"> ${UNIT} </label>
			<div class="col-sm-10">
				<form:select class="form-control" name="unit" path="unit" items="${MEASUREMENT_UNIT_LIST}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="metric">${METRIC}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control decimal" path="metric"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="quantity">${QUANTITY}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control decimal"  path="quantity"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="pupMetric">${PUP_METRIC}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control decimal" path="pupMetric"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="pupQuantity">${PUP_QUANTITY}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control decimal" path="pupQuantity"/>
			</div>
		</div>
		
		</c:when>
		
		<c:otherwise>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customerWbsCode">${CUSTOMER_WBS_CODE}</label>
			<div class="col-sm-10"> ${record.customerWbsCode}
				<form:input type="hidden" path="customerWbsCode" />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customerWbsCaption">${CUSTOMER_WBS_CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input type="hidden" path="customerWbsCaption"/>
			</div>
		</div>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="cbs">${CBS_CODE}</label>
			<div class="col-sm-10"> ${record.cbs.caption}
				<form:input type="hidden" path="cbs"/>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="unit"> ${UNIT} </label>
			<div class="col-sm-10">${record.unit}
				<form:input type="hidden" path="unit"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="metric">${METRIC}</label>
			<div class="col-sm-10"> ${record.metric}
				<form:input type="hidden" path="metric"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="quantity">${QUANTITY}</label>
			<div class="col-sm-10"> ${record.quantity}
				<form:input type="hidden" path="quantity"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="pupMetric">${PUP_METRIC}</label>
			<div class="col-sm-10"> ${record.pupMetric}
				<form:input type="hidden" path="pupMetric"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="pupQuantity">${PUP_QUANTITY}</label>
			<div class="col-sm-10"> ${record.pupQuantity}
				<form:input type="hidden" path="pupQuantity"/>
			</div>
		</div>

		</c:otherwise>
		
		</c:choose>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="plannedMetric">${PLANNED_METRIC}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="plannedMetric"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="plannedQuantity">${PLANNED_QUANTITY}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="plannedQuantity"/>
			</div>
		</div>
		
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>

<!-- 
<script type="text/javascript" src="/jquery/jquery.maskMoney.min.js"></script>

<script>
 //mask money
   $('document').ready(function() {
	    $('.decimal').maskMoney({precision:2, thousands:' '}).maskMoney( "mask" );
	    $('.number').maskMoney({precision:0, thousands:' '}).maskMoney( "mask" );
	    
	    //$('.currency').maskMoney('mask');
	});
	


</script>
 -->
 <script>
	console.log(navigator.userAgent);
	$(".decimal").inputmask( {
		alias: 'decimal',		
		groupSeparator: ' ',
	    autoGroup: true, 
		digits: 2, 
		digitsOptional: true,
		rightAlign: false
	});
</script>
 
 
 