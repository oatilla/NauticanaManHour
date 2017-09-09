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
    <h3><a href="new"> ${NEW} </a></h3>
    
    <table>
      <tr>
        <th> ${CATEGORY_ID}</th>
        <th> ${PARENT_ID}</th>
        <th> ${CAT_INDEX}</th>
        <th> ${CAPTION}</th>
        <th> ${DETAILS}</th>
        <th> ${UNIT}</th>
        <th> ${CAT_LEVEL}</th>
        <th> ${TREE_CODE}</th>
        <th> ${MAIN_FLAG}</th>
		<th> &nbsp; </th>
      </tr>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.id}</td>
          <td>${record.parentId}</td>
          <td>${record.catIndex}</td>
          <td>${record.caption}</td>
          <td>${record.details}</td>
          <td>${record.unit}</td>
          <td>${record.catLevel}</td>
          <td>${record.treeCode}</td>
          <td>${record.mainFlag}</td>
          <td><a href="edit?id=${record.id}"> ${EDIT} </a> &nbsp; <a href="delete?id=${record.id}"> ${DELETE} </a></td>
        </tr>
      </c:forEach>             
    </table>
  </div>
</body>

</html>