<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
		<input type="hidden" name="nextpage"  id="nextpage" value="${nextpage}">
	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${CATEGORY_ID} </label>
			<div class="col-sm-10">  
				${record.id} <form:input type="hidden" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="parentId">${PARENT_ID}</label>
			<div class="col-sm-10"> 
				${record.parentId} <form:input type="hidden" path="parentId"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="catLevel">${CAT_LEVEL}</label>
			<div class="col-sm-10"> 
				${record.catLevel} <form:input type="hidden" path="catLevel"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="mainFlag">${MAIN_FLAG}</label>
			<div class="col-sm-10"> 
				${record.mainFlag} <form:input type="hidden" path="mainFlag"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="catIndex">${CAT_INDEX}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="catIndex"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption" />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="details">${DETAILS}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="details"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="unit">${UNIT}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="unit"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="cbsCode">${CBS_CODE}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="cbsCode"/>
			</div>
		</div>
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="projectId">${PROJECT_ID}</label>
			<div class="col-sm-10"> 
				${record.projectId} <form:input type="hidden" path="projectId"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	
	</div>

	</form:form>
</div>
