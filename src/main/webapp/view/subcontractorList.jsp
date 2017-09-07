<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:out value="${PAGETITLE}" /></title>
</head>

<body>
  <div align="center">
    <h3><a href="new"><c:out value="${NEW}" /></a></h3>
    
    <table>
      <tr>
        <th><c:out value="${ORDER}" /></th>
        <th><c:out value="${SUBCONTRACTOR_ID}" /></th>
        <th><c:out value="${CAPTION}" /></th>
      </tr>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${record.id}</td>
          <td>${record.caption}</td>
          <td><a href="edit?id=${record.id}"> <c:out value="${EDIT}" /> </a> &nbsp; <a href="delete?id=${record.id}"> <c:out value="${DELETE}" /> </a></td>
        </tr>
      </c:forEach>             
    </table>
  </div>
</body>

</html>