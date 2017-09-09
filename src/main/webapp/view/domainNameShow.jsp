<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>
</head>

<body>
  <div align="center">
    <h3><a href="edit?id=${record.id}"> ${EDIT} </a></h3>
    
	<p> ${LANGCODE} : ${record.id} ${record.caption} </p>

    <h3> ${DOMAIN_VALUE} </h3>

	<a href="/domainValue/new?parentKey=${record.id}"> ${NEW} </a>

    <table>
      <tr>
        <th>${REFVALUE}</th>
        <th>${CAPTION}</th>
		<th> &nbsp; </th>
      </tr>

      <c:forEach var="domainValue" items="${record.domainValues}" varStatus="status">
        <tr>
          <td>${domainValue.id.refvalue}</td>
          <td>${domainValue.caption}</td>
          <td>
          	<a href="/domainValue/edit?id=${domainValue.id.domain},${domainValue.id.refvalue}"> ${EDIT} </a> &nbsp;
          	<a href="/domainValue/delete?id=${domainValue.id.domain},${domainValue.id.refvalue}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
  </div>

</body>

</html>