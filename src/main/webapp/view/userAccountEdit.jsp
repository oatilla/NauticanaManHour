<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>
</head>
<body>

  <div align="center">
    <form:form method="post" modelAttribute="record">
    <table>
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
        <td><form:select path="status" items="${statusList}"/></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="${SAVE}"></td>
      </tr>
    </table>
    </form:form>
  </div>

</body>
</html>