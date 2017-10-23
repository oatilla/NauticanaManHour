<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="language/show?id=${record.id.langcode}"/>

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.langcode"> ${LANGCODE} </label>
			<div class="col-sm-10">  
			    ${record.id.langcode}
				<form:input type="hidden" path="id.langcode"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="id.caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="labelupper">${LABELUPPER}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="labelupper"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="labellower">${LABELLOWER}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="labellower"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
