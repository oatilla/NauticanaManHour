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
    <h3><a href="edit?id=${authorityGroup}"><c:out value="${EDIT}" /></a></h3>
    
	<table>
		<tr>
			<th>${AUTHORITY_GROUP}</th>
			<td>${record.id}</td>
		</tr>
		<tr>
			<th>${CAPTION}</th>
			<td>${record.caption}</td>
		</tr>
	</table>

    <h3>${TABLE_AUTHORIZATION}</h3>

	<a href="/tableAuthorization/new?parentKey=${record.id}"> ${NEW} </a>

    <table>
      <tr>
        <th>${TABLENAME}</th>
        <th>${ALLOW_SELECT}</th>
        <th>${ALLOW_INSERT}</th>
        <th>${ALLOW_UPDATE}</th>
        <th>${ALLOW_DELETE}</th>
		<th> &nbsp; </th>
      </tr>

      <c:forEach var="tableAuthorization" items="${record.tableAuthorizations}" varStatus="status">
        <tr>
          <td>${tableAuthorization.id.tablename}</td>
          <td>${tableAuthorization.allowSelect}</td>
          <td>${tableAuthorization.allowInsert}</td>
          <td>${tableAuthorization.allowUpdate}</td>
          <td>${tableAuthorization.allowDelete}</td>
          <td>
          	<a href="/tableAuthorization/edit?id=${tableAuthorization.id.authorityGroup},${tableAuthorization.id.tablename}"> ${EDIT} </a> &nbsp;
          	<a href="/tableAuthorization/delete?id=${tableAuthorization.id.authorityGroup},${tableAuthorization.id.tablename}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
    <h3>${USER_AUTHORIZATION}</h3>

    <table>
      <tr>
        <th>${USERNAME}</th>
      </tr>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.username}</td>
          <td><a href="/userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}"> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>
    
    <h3>${PAGE_AUTHORIZATION}</h3>

    <table>
      <tr>
        <th>${PAGENAME}</th>
      </tr>

      <c:forEach var="pageAuthorization" items="${record.pageAuthorizations}" varStatus="status">
        <tr>
          <td>${pageAuthorization.id.pagename}</td>
          <td><a href="/pageAuthorization/delete?id=${pageAuthorization.id.authorityGroup},${pageAuthorization.id.pagename}"> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>

  </div>
</body>

</html>