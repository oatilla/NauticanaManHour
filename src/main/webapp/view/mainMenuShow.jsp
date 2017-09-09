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
  
  	<p> ${MENU} ${record.id} </p>
  	
  	<h3> ${SCREEN_PAGE} </h3>
  
	<a href="/screenPage/new?parentKey=${record.id}"> ${NEW} </a>
	<table>
		<tr>
			<th> ${PAGENAME} </th>
			<th> ${CAPTION} </th>
			<th> ${ICON} </th>
			<th> ${URL} </th>
			<th> ${DISPLAY_ORDER} </th>
		</tr>
		<c:forEach var="screenPage" items="${record.screenPages}" varStatus="status">
		<tr>
			<td> <a href="/screenPage/show?id=${screenPage.id}"> ${screenPage.id} </a> </td>
			<td>${screenPage.caption}</td>
			<td>${screenPage.icon}</td>
			<td>${screenPage.url}</td>
			<td>${screenPage.displayOrder}</td>
			<td>
				<a href="/screenPage/edit?id=${screenPage.id}"> ${EDIT} </a> &nbsp;
				<a href="/screenPage/delete?id=${screenPage.id}"> ${DELETE} </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>  

</body>
</html>
