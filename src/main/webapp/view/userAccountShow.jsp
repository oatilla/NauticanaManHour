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
    <h3><a href="edit?id=${authorityGroup}"><c:out value="${EDIT}" /></a></h3>
    
	<table>
		<tr>
			<th><c:out value="${USERNAME}" /></th>
			<td><c:out value="${username}" /></td>
		</tr>
		<tr>
			<th><c:out value="${CAPTION}" /></th>
			<td><c:out value="${userAccountCaption}" /></td>
		</tr>
	</table>

    <h3><c:out value="${USER_AUTHORIZATION}" /></h3>

	<a href="/userAuthorizations/new?USERNAME=${username}"><c:out value="${NEW}" /></a>

    <table>
      <tr>
        <th><c:out value="${ORDER}" /></th>
        <th><c:out value="${AUTHORITY_GROUP}" /></th>
      </tr>

      <c:forEach var="record" items="${userAuthorizations}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${record.id.auhtorityGroup}</td>
          <td><a href="/userAuthorizations/delete?id=${record.id.username},${record.id.auhtorityGroup}"> <c:out value="${DELETE}" /> </a></td>
        </tr>
      </c:forEach>
    </table>
    
  </div>
</body>

</html>