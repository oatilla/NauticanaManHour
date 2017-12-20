<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="en_US" scope="session"/>
<!DOCTYPE HTML>
<html${LANGUAGE_DIRECTION}>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<title> ${PAGETITLE}</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">  
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="flag-icon/css/flag-icon.min.css">
<link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="plugins/select2/select2.min.css">
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
<link type="text/css" rel="stylesheet" href="s/tree.css" />
<link type="text/css" rel="stylesheet" href="s/nauticana.css" />
<link type="text/css" rel="stylesheet" href="s/jquery.dataTables.min.css" />
<link type="text/css" rel="stylesheet" href="s/buttons.dataTables.min.css" />

<script src="jquery/jquery-2.2.3.min.js"></script>
 <script type="text/javascript" src="/j/jquery.inputmask.bundle.min.js"> </script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="plugins/select2/select2.full.min.js"></script>
<script src="plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="dist/js/app.min.js"></script>

<script type="text/javascript" src="j/dataTables.buttons.min.multiheaders.js"></script>
<script type="text/javascript" src="j/jszip.min.js"></script>
<script type="text/javascript" src="j/pdfmake.min.js"></script>
<script type="text/javascript" src="j/vfs_fonts.js"></script>
<script type="text/javascript" src="j/buttons.html5.min.multiheaders.js"></script>
<script type="text/javascript" src="j/buttons.print.min.multiheaders.js"></script>

<script type="text/javascript" src="j/nauticana.js"></script>


</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper" style="height: auto;">
	<header id="header" class="main-header">
		<a href="/" class="logo">
			<span class="logo-mini"><img src="../flag-icon/gama_logo_mini.png"/></span>
			<span class="logo-lg"><img src="../flag-icon/gama_logo.png"/></span>
		</a>

		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>

			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li><div class="btn-group btn"> <a class="btn btn-info" data-toggle="dropdown">	<i class="${langClass}"></i></a>
							<ul class="dropdown-menu list-inline" role="menu">
							<c:forEach var="lang" items="${languageList}" varStatus="status">
								<li> <span class="${lang.value}" onClick="document.location='?langcode=${lang.key}';"></span> </li>
							</c:forEach>
							</ul>
						</div>
					</li>
					<li> <a class="btn btn-success" onClick="document.location='userAccount/login';"> <i class="fa fa-user-circle"></i> </a> </li>
					<li> <a class="btn btn-success" onClick="document.location='userAccount/setPassword';"> <i class="fa fa-key"></i> </a> </li>
					<li> <a class="btn btn-danger"  onClick="document.location='userAccount/logoff';"> <i class="fa fa-power-off"></i> </a> </li>
				</ul>
			</div>
		</nav>
	</header>

	<aside class="main-sidebar">
		<section class="sidebar">
${menu}
		</section>
	</aside>

	<div class="content-wrapper" style="min-height: 916px;">
		<section class="content-header">
		</section>

		<section class = "content">
			<div id="content"> </div>
		</section>
	</div>
</div>

</body>


<script type="text/javascript">
$('body').on('expanded.pushMenu collapsed.pushMenu', function() {
	    setTimeout(function(){
	    	$('.table').DataTable( {retrieve: true, visible: true, api: true} ).columns.adjust();
	    }, 350);
	});

</script>

</html>
