<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
    <head>
		META name="robots" content="noindex,nofollow">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home</title>
		<link href="s/eds.css" rel="stylesheet" type="text/css">
		<link href="s/accordion-menu.css" rel="stylesheet">
		
		<meta charset="utf-8">
  
		<script type="text/javascript" src="j/accordion-menu.js"></script>
		<script type="text/javascript" src="j/nav.js"></script>
    </head>
    <body>
		<header id="header">
			<div>
				<img src="i/menu.png" class="button" id="showMenuBtn" onClick="showMenu();">
				<img src="i/delete.png" class="button" id="shutMenuBtn" onClick="shutMenu();">
				<img src="i/key.png" class="button" id="logonBtn" onClick="document.location='/user/Login';">
				<img src="i/exit.png" class="button" id="logoffBtn" onClick="document.location='/user/Logoff;">
			</div>
		</header>
		<div id="content">
			<iframe name="frmupage" src="hello.html"></iframe>
		</div>
		<div id="accordion">
		${menu}
		</div>
		<!-- 
        <h1>Work Break Down</h1>
        <p><a href="user/list"> Users</a></p>
        <p><a href="authorityGroup/list"> Authority Groups</a></p>
        <p><a href="workBreakDown/list"> Work Break Down Structure </a></p>
         -->
        
         
    </body>
</html>
