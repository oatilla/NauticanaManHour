<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">

	<div class="box-body">
		<!-- 
			<div class="form-group">
				<label class="col-sm-2 control-label" for="id"> ${PROJECT_ID} </label>
				<div class="col-sm-10">
					<label class="control-label">${id}</label>
					<form:input type="hidden" path="id"/>
				</div>
			</div>
 		-->
 		<form:input type="hidden" path="id"/>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control required" path="caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="status"> ${STATUS} </label>
			<div class="col-sm-10">
				 ${record.status}
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="customer"> ${CUSTOMER} </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="customer"/>
			</div>
		</div>

			<div class="form-group">
				<label  class="col-sm-2 control-label required" for="country"> ${COUNTRY} </label>
				<div class="col-sm-10"> 
					<form:select name="country" path="country"  class="form-control select2" items="${COUNTRY_LIST}"/>
				</div>
			</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="location"> ${LOCATION} </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="location"/>
			</div>
		</div>
<%-- 		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractDate"> ${CONTRACT_DATE}  </label>
			<div class="col-sm-10"> 
				<form:input type="date" class="form-control" path="contractDate"/>
			</div>
		</div> --%>

		 
		 <div class="form-group">
                <label  class="col-sm-2 control-label" for="contractDate"> ${CONTRACT_DATE}  </label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <form:input type="text" class="form-control pull-right" path="contractDate"/>
                </div>
                <!-- /.input group -->
          </div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="areaHandover"> ${AREA_HANDOVER}  </label>
			 <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input  type="text" class="form-control" path="areaHandover"/>
			</div>
		</div>
		<%-- 24.11.2017 tarihli kullanıcı testi sonucunda alanların kapatılması talep edildi -->
		<%-- <div class="form-group"> 
			<label  class="col-sm-2 control-label" for="duration"> ${DURATION}  </label>
			<div class="col-sm-10"> 
				<form:input type="number" class="form-control" path="duration"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="revizedDuration"> ${REVIZED_DURATION}  </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="revizedDuration"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="revizedCompletion"> ${REVIZED_COMPLETION}  </label>
			<div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="revizedCompletion"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="expectedCompletion"> ${EXPECTED_COMPLETION}  </label>
			<div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="expectedCompletion"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="endOfWarranty"> ${END_OF_WARRANTY}  </label>
			<div class="input-group date col-md-6">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
				<form:input class="form-control" path="endOfWarranty"/>
			</div>
		</div> --%>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractedAmount"> ${CONTRACTED_AMOUNT}  </label>
			<div class="col-sm-10"> 
				<form:input type="text" class="currency form-control" path="contractedAmount"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="contractExchange"> ${CONTRACT_EXCHANGE}  </label>
			<div class="col-sm-10"> 
				<form:select name="contractExchange" path="contractExchange"  class="form-control" items="${EXCHANGE_LIST}"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="expectedCost"> ${EXPECTED_COST}  </label>
			<div class="col-sm-10"> 
				<form:input  class="currency form-control" path="expectedCost"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="advancePercent"> ${ADVANCE_PERCENT} % </label>
			<div class="col-sm-10"> 
				<form:input class="form-control percentage" path="advancePercent"/>
			</div>
		</div>
		<%-- <div class="form-group">
			<label  class="col-sm-2 control-label" for="letterOfAdvance"> ${LETTER_OF_ADVANCE} % </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="letterOfAdvance"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="letterOfWarranty"> ${LETTER_OF_WARRANTY}  </label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="letterOfWarranty"/>
			</div>
		</div> --%>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="organizationId">${ORGANIZATION_ID}</label>
			<div class="col-sm-10">
				<form:select path="organizationId" class="form-control select2" items="${organizationList}" />
			</div>
		</div>
		
		
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>

</div>

<!-- script type="text/javascript" src="/jquery/jquery.maskMoney.min.js"></script> -->

<script>
   $('document').ready(function() {

	//mask money
//	    $('.currency').maskMoney({precision:0, thousands:' '}).maskMoney( "mask" );

	//Initialize Select2 Elements
	    $(".select2").select2();

	//Date picker
	    $('.date').datepicker({
	      autoclose: true,
	      format: 'dd/mm/yyyy'
	    });
	    
	});
</script>
 <script>
	console.log(navigator.userAgent);
	$(".currency").inputmask( {
		alias: 'decimal',		
		groupSeparator: ' ',
	    autoGroup: true, 
		digits: 2, 
		digitsOptional: true,
		rightAlign: false
	});
	$(".percentage").inputmask( {
		alias: 'numeric',		
	    autoGroup: false, 
	    integerDigits:3,
		digits: 2, 
		min: 0,
		max:100,
		digitsOptional: true,
		rightAlign: false
	});
</script>

