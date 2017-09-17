<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record" id="f">
    <input type=hidden name=nextpage value="/projectWbsQuantity/list">
	<table class="table table-condensed">
		<tr>
			<th> ${PROJECT_ID} </th>
	        <td> ${record.id.projectId} ${record.projectWbs.project.caption} <form:input type="hidden" path="id.projectId"  /></td>
		</tr>
		<tr>
        	<th> ${CATEGORY_ID} </th>
			<td> ${record.id.categoryId} ${record.projectWbs.category.caption} <form:input type="hidden" path="id.categoryId" /></td>
		</tr>
		<tr>
			<th> ${BEGDA} </th>
			<td> ${record.id.begda} <form:input type="hidden" path="id.begda" /></td>
		</tr>
		<tr>
			<th> ${ENDDA} </th>
			<td> <form:input path="endda" /></td>
		</tr>
		<tr>
			<th> ${QUANTITY} </th>
			<td> <form:input path="quantity" /> ${record.projectWbs.unit} </td>
		</tr>
		<tr>
			<th> ${IS_SUBCONTRACTOR} </th>
			<td> <form:select path="isSubcontractor" items="${YESNO_LIST}"/></td>
		</tr>
		<tr>
	        <td colspan="2" align="center">
		<a href="#" onclick="doAjaxPost('projectWbsQuantity/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
			<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        	</td>
		</tr>
	</table>
	</form:form>
</div>
