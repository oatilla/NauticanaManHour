<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
    <input type="hidden" name="nextpage" value="userAccount/show?id=${record.id.username}">

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.username"> ${USERNAME} </label>
			<div class="col-sm-10">
				${record.id.username} <form:input type="hidden" path="id.username"  />
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.authorityGroup">${AUTHORITY_GROUP}</label>
			<div class="col-sm-10"> 
				<form:select name="id.authorityGroup" path="id.authorityGroup"  class="form-control select2" items="${authorityGroupList}"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('userAccount/show?id=${record.id.username}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
