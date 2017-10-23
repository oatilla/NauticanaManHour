<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
    <input type="hidden" name="nextpage" value="mainMenu/show?id=${record.menu.id}">

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="menu.id"> ${MENU} </label>
			<div class="col-sm-10">
				${record.menu.id} <form:input type="hidden" path="menu.id"  />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id">${PAGENAME}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="icon">${ICON}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="icon"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="url">${URL}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="url"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="displayOrder">${DISPLAY_ORDER}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="displayOrder"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('mainMenu/show?id=${record.menu.id}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
