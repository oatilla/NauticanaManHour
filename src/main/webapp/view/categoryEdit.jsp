<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form name="f" method="post" modelAttribute="record">
    <table>
      <tr>
        <th>${CATEGORY_ID}</th>
        <td>${record.id} <form:hidden path="id" /></td>
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
        <td><form:input path="caption" /></td>
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
        <button type="submit" class="btn btn-primary" onClick="document.f.submit();"> <i class="${SAVE_ICON}"> ${SAVE} </i> </button>
        <button type="button" class="btn btn-warning" onClick="history.back();"> <i class="${CANCEL_ICON}"> ${CANCEL} </i> </button>
      </tr>
    </table>
    </form:form>
</div>