<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record">
    <table>
      <tr>
        <th>${AUTHORITY_GROUP}</th>
        <td><form:input path="id" /></td>
      </tr>
      <tr>
        <th>${CAPTION}</th>
        <td><form:input path="caption" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
			<button class="btn btn-primary" onClick="doAjaxPost('authorityGroup/edit');"> <i class="${SAVE_ICON}"></i> ${SAVE} </button> 
			<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
        </td>
      </tr>
    </table>
    </form:form>
</div>
