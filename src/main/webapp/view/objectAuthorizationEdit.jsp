<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="authorityGroup/show?id=${record.id.authorityGroup}"/>

	<div class="box-body">

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.action"> ${AUTHORITY_GROUP} </label>
			<div class="col-sm-10">
				${record.id.authorityGroup} <form:input type="hidden" path="id.authorityGroup"  />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.action"> ${OBJECT_TYPE} </label>
			<div class="col-sm-10">
				<c:choose>
					<c:when test="${empty record.id.keyValue}">
						<td><form:select class="form-control" path="id.objectType" items="${authorityObjectList}" onChange="doAjaxGet('objectAuthorization/new?authorityGroup=${record.id.authorityGroup}&objectType=' + document.getElementById('id.objectType').value);" /> </td>
					</c:when>
					<c:otherwise>
						<td><form:input class="form-control" path="id.objectType" type="hidden" /> ${record.id.objectType} </td>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="id.action"> ${ACTION} </label>
			<div class="col-sm-10">
				<form:select class="form-control" path="id.action" items="${actionList}"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.keyValue">${KEY_VALUE}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="id.keyValue"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>
