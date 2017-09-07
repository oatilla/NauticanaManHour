<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
</head>
<body>

  <div align="center">
    <form:form method="post" modelAttribute="record">
    <table>
      <tr>
        <th><c:out value="${CATEGORY_ID}" /></th>
        <td><c:out value="${record.id}"/> <form:hidden path="id" /></td>
      </tr>
      <tr>
        <th><c:out value="${PARENT_ID}" /></th>
        <td><form:input path="parentId" /></td>
      </tr>
      <tr>
        <th><c:out value="${CAT_INDEX}" /></th>
        <td><form:input path="catIndex" /></td>
      </tr>
      <tr>
        <th><c:out value="${CAPTION}" /></th>
        <td><form:input path="caption" /></td>
      </tr>
      <tr>
        <th><c:out value="${DETAILS}" /></th>
        <td><form:input path="details" /></td>
      </tr>
      <tr>
        <th><c:out value="${UNIT}" /></th>
        <td><form:input path="unit" /></td>
      </tr>
      <tr>
        <th><c:out value="${CAT_LEVEL}" /></th>
        <td><form:input path="catLevel" /></td>
      </tr>
      <tr>
        <th><c:out value="${TREE_CODE}" /></th>
        <td><form:input path="treeCode" /></td>
      </tr>
      <tr>
        <th><c:out value="${MAIN_FLAG}" /></th>
        <td><form:input path="mainFlag" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value='<c:out value="${SAVE}" />'></td>
      </tr>
    </table>
    </form:form>
  </div>
    
</body>
</html>
