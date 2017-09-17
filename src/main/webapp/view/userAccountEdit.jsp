<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record" id="f">
    <table class="table table-condensed">
      <tr>
        <th> ${USERNAME} </th>
        <td><form:input path="id" /></td>
      </tr>
      <tr>
        <th> ${CAPTION} </th>
        <td><form:input path="caption" /></td>
      </tr>
      <tr>
        <th> ${PASSWORD} </th>
        <td><form:password path="password" /></td>
      </tr>
      <tr>
        <th> ${STATUS} </th>
        <td><form:select path="status" items="${USER_STATUS_LIST}" id="status"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
			<a href="#" onclick="doAjaxPost('userAccount/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
			<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
      </tr>
    </table>
    </form:form>
</div>
