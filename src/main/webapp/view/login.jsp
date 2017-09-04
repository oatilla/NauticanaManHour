<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>

<body>
    <h1>Login</h1>
    
  	<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
    
    <table>
      <tr>
        <th><form:label path="username">Username</form:label></th>
        <td><form:input id="username" name="username" path="username" /></td>
      </tr>
      <tr>
        <th><form:label path="password">Password</form:label></th>
        <td><form:password id="password" name="password" path="password" /></td>
      </tr>
      <tr>
        <th><form:label path="language">Language</form:label></th>
        <td> <form:select path="language" items="${languageList}"/> </td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="Logon"></td>
      </tr>
    </table>
    </form:form>
</body>

</html>