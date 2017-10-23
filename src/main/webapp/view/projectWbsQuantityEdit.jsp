<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="projectWbs/show?id=${record.id.projectId},${record.id.categoryId}"/>

	<div class="box-body">
		<table>
			<tr>
				<th> ${PROJECT_ID} </th>
				<td> ${record.id.projectId} ${record.projectWbs.project.caption} <form:input type="hidden" path="id.projectId"  /></td>
			</tr>
			<tr>
        		<th> ${CATEGORY_ID} </th>
				<td> ${record.id.categoryId} ${record.projectWbs.category.treeCode} ${record.projectWbs.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
			</tr>
		</table>
		
		<div class="form-group">
			<label  class="col-sm-2 control-label" for="id.begda">${BEGDA}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="id.begda" type="date"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="endda">${ENDDA}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="endda" type="date"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="quantity">${QUANTITY}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="quantity"/> ${record.projectWbs.unit}
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="isSubcontractor">${IS_SUBCONTRACTOR}</label>
			<div class="col-sm-10"> 
				<form:select class="form-control" path="isSubcontractor" items="${YESNO_LIST}"/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost('${postlink}');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>