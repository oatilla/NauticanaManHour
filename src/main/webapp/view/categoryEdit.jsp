<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
	<h3> ${PAGETITLE} </h3>
    <form:form name="f" method="post" modelAttribute="record">
    <table class="table table-condensed">
        <tr>
        <th><label class="control-label" for="id">${CATEGORY_ID}</label></th>
        <td><form:input type="text" class="form-control" id="id" path="id" /></td>
      </tr>
       <tr>
        <th>${PARENT_ID}</th>
        <td><form:input path="parentId" /></td>
      </tr>
      <tr>
        <th>${CAT_INDEX}</th>
        <td><form:input path="catIndex" /></td>
      </tr>
      <tr>
        <th>${CAPTION}</th>
        <td><form:input path="caption" id="caption" /></td>
      </tr>
      <tr>
        <th>${DETAILS}</th>
        <td><form:input path="details" /></td>
      </tr>
      <tr>
        <th>${UNIT}</th>
        <td><form:input path="unit" /></td>
      </tr>
      <tr>
        <th>${CAT_LEVEL}</th>
        <td><form:input path="catLevel" /></td>
      </tr>
      <tr>
        <th>${TREE_CODE}</th>
        <td><form:input path="treeCode" /></td>
      </tr>
      <tr>
        <th>${MAIN_FLAG}</th>
        <td><form:input path="mainFlag" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
			<a href="#" onclick="doAjaxPost('category/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
			<a href="#" onclick="doAjaxPost('${prevpage}'); " class="btn btn-primary pull-right btn-flat" >${CANCEL} </a>
	    </td>
      </tr>
    </table>
    </form:form>
</div>
