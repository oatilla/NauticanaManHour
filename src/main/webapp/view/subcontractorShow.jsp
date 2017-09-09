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
  
  	<p> ${SUBCONTRACTOR_ID} ${record.id} ${record.caption}</p>
  	
  	<h3> ${WORKER} </h3>
  
	<a href="/worker/new?parentKey=${record.id}"> ${NEW} </a>
	<table>
		<tr>
			<th> ${CAPTION} </th>
		</tr>
		<c:forEach var="worker" items="${record.workers}" varStatus="status">
		<tr>
			<td>${worker.caption}</td>
			<td>
				<a href="/worker/edit?id=${worker.id}"> ${EDIT} </a> &nbsp;
				<a href="/worker/delete?id=${worker.id}"> ${DELETE} </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>  

</body>
</html>
