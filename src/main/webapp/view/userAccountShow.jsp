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
    
	<table>
		<tr>
			<th> ${USERNAME} </th>
			<td> ${record.id} ${record.caption} </td>
		</tr>
	</table>

    <h3><c:out value="${USER_AUTHORIZATION}" /></h3>

	<a href="/userAuthorization/new?parentKey=${record.id}"> ${NEW} </a>

    <table>
      <tr>
        <th> ${AUTHORITY_GROUP} </th>
        <th> &nbsp; </th>
      </tr>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.authorityGroup}</td>
          <td><a href="/userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}"> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>

  </div>
</body>

</html>