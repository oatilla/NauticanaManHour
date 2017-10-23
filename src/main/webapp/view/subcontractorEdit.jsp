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

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${SUBCONTRACTOR_ID} </label>
			<div class="col-sm-10">  
				${record.id}
				<form:input type="hidden" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="extSubcontractor">${EXT_SUBCONTRACTOR}</label>
			<div class="col-sm-10">
				<form:select path="extSubcontractor" class="form-control select2" items="${extSubcontractorList}" />
			</div>
		</div>
		
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
