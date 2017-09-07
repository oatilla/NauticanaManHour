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
			<th><c:out value="${AUTHORITY_GROUP}" /></th>
			<td><c:out value="${authorityGroup}" /></td>
		</tr>
		<tr>
			<th><c:out value="${CAPTION}" /></th>
			<td><c:out value="${authorityGroupCaption}" /></td>
		</tr>
	</table>

    <h3><c:out value="${TABLE_AUTHORIZATION}" /></h3>

	<a href="/tableAuthorizations/new?AUTHORITY_GROUP=${auhtorityGroup}"><c:out value="${NEW}" /></a>

    <table>
      <tr>
        <th><c:out value="${ORDER}" /></th>
        <th><c:out value="${TABLENAME}" /></th>
        <th><c:out value="${ALLOW_SELECT}" /></th>
        <th><c:out value="${ALLOW_INSERT}" /></th>
        <th><c:out value="${ALLOW_UPDATE}" /></th>
        <th><c:out value="${ALLOW_DELETE}" /></th>
      </tr>

      <c:forEach var="record" items="${tableAuthorizations}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${record.id.tablename}</td>
          <td>${record.allowSelect}</td>
          <td>${record.allowInsert}</td>
          <td>${record.allowUpdate}</td>
          <td>${record.allowDelete}</td>
          <td><a href="/tableAuthorizations/edit?id=${record.id.auhtorityGroup},${record.id.tablename}"> <c:out value="${EDIT}" /> </a> &nbsp; <a href="/tableAuthorizations/delete?id=${record.id.auhtorityGroup},${record.id.tablename}"> <c:out value="${DELETE}" /> </a></td>
        </tr>
      </c:forEach>
    </table>
    
    <h3><c:out value="${USER_AUTHORIZATION}" /></h3>

	<a href="/userAuthorizations/new?AUTHORITY_GROUP=${auhtorityGroup}"><c:out value="${NEW}" /></a>

    <table>
      <tr>
        <th><c:out value="${ORDER}" /></th>
        <th><c:out value="${USERNAME}" /></th>
      </tr>

      <c:forEach var="record" items="${userAuthorizations}" varStatus="status">
        <tr>
          <td>${status.index + 1}</td>
          <td>${record.id.username}</td>
          <td><a href="/userAuthorizations/delete?id=${record.id.username},${record.id.auhtorityGroup}"> <c:out value="${DELETE}" /> </a></td>
        </tr>
      </c:forEach>
    </table>
    
  </div>
</body>

</html>