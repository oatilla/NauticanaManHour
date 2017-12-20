<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">  
<link rel="stylesheet" href="../font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="../dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="../dist/css/skins/skin-blue.min.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <b> ${MANHOUR_APPLICATION} </b>
  </div>

  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>
    <p> <b> ${message}</b> </p>

    <form:form id="loginForm" method="post" modelAttribute="loginBean" >
      <div class="form-group has-feedback">
        <form:input type="username" placeholder="UserName" id="username" name="username" path="username" class="form-control"/>
       <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <form:input id="password" name="password" path="password" type="password" class="form-control" placeholder="Password" />
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <form:select id="language" name="language" path="language" items="${languageList}" type="language" class="form-control" placeholder="Language" />
      </div>
      <div class="row">
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat" value="Logon">Sign In</button>
        </div>
      </div>
    </form:form>
  </div>
</div>

</body>
</html>
