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
        <th><c:out value="${AUTHORITY_GROUP}" /></th>
        <td><form:input path="authorityGroup" /></td>
      </tr>
      <tr>
        <th><c:out value="${CAPTION}" /></th>
        <td><form:input path="caption" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value='<c:out value="${SAVE}" />'></td>
      </tr>
    </table>
    </form:form>
  </div>
    
</body>
</html>
