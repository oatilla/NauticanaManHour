<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
    <head>
		<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
 
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">


<!-- Style Sheets -->
  	<!-- Bootstrap 3.3.6 -->
  		<link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">  
	<!-- Font Awesome -->
		<link rel="stylesheet" href="../../s/font-awesome/css/font-awesome.min.css">
	<!-- Ionicons -->
		<link rel="stylesheet" href="../../s/Ionicons/css/ionicons.min.css">
	<!-- Bootstrap Data Tables --> 
 		<link rel="stylesheet" href="../../plugins/datatables/dataTables.bootstrap.css">
	<!-- bootstrap datepicker -->
  		<link rel="stylesheet" href="../../plugins/datepicker/datepicker3.css">
	<!-- Template Styles -->
	  	<link rel="stylesheet" href="../../s/AdminLTE.min.css">
		<!-- link rel="stylesheet" href="../../s/accordion-menu.css"> -->
		<link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">
			<!-- link href="../../s/eds1.css" rel="stylesheet" type="text/css"/> -->
<!-- Scripts -->
	<!-- jQuery 2.2.3 -->
		<script src="../../plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
		<script src="../../bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables -->
		<script src="../../plugins/datatables/jquery.dataTables.min.js"></script>
		<script src="../../plugins/datatables/dataTables.bootstrap.min.js"></script>
	<!-- bootstrap datepicker -->
		<script src="../../plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- Custom Scripts -->
	<script type="text/javascript" src="j/accordion-menu.js"></script>
	<script type="text/javascript" src="j/nav.js"></script>

<!-- AdminLTE App -->
	<script src="../../dist/js/app.min.js"></script>

    </head>
    <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper" style="height: auto;">
		<header id="header" class="main-header">
		
				<a href="/" class="logo">
			      <!-- mini logo for sidebar mini 50x50 pixels -->
			      <span class="logo-mini"><b>GH</b></span>
			      <!-- logo for regular state and mobile devices -->
			      <span class="logo-lg"><b>GAMA</b>Holding</span>
			    </a>
		
		    <!-- Header Navbar -->
			    <nav class="navbar navbar-static-top" role="navigation">
				      <!-- Sidebar toggle button-->
				      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				        <span class="sr-only">Toggle navigation</span>
				      </a>
				      <!-- Navbar Right Menu -->
				      <div class="navbar-custom-menu">
				      <ul class="nav navbar-nav">
				      
				      <li>			<a class="btn btn-success" onClick="document.location='/userAccount/login';"> <i class="fa fa-sign-in"></i> </a>
				      </li>
				      <li>			<a class="btn btn-danger" onClick="document.location='/userAccount/logoff';"> <i class="fa fa-power-off"></i> </a>
	</li>
				      </ul>
			          </div>
			    </nav>
		</header>
		
		<aside class="main-sidebar">
		    <!-- sidebar: style can be found in sidebar.less -->
		    	<section class="sidebar">
						${menu}
				</section>
		</aside>
		
		<div class="content-wrapper" style="min-height: 916px;">
		<section class="content-header"></section>
		
			<section class = "content">
				<div class="box">
					<div class="box-body">
						<!-- div class="dataTables_wrapper form-inline dt-bootstrap"> -->
						<div id="content" class="row">
							
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>
</html>
