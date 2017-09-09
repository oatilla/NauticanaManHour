<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
    <head>

		<title>Home</title>
		<link href="s/eds.css" rel="stylesheet" type="text/css">
		<link href="s/accordion-menu.css" rel="stylesheet">
		
		<meta charset="utf-8">
  
		<script type="text/javascript" src="j/accordion-menu.js"></script>
		<script type="text/javascript" src="j/nav.js"></script>
		
		
		
		  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="../../plugins/datatables/dataTables.bootstrap.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
		
		
		
		
    </head>
    <body>
		<!-- header id="header">
			<div>
				<img src="i/menu.png" class="button" id="showMenuBtn" onClick="showMenu();">
				<img src="i/delete.png" class="button" id="shutMenuBtn" onClick="shutMenu();">
				<img src="i/key.png" class="button" id="logonBtn" onClick="document.location='/user/Login';">
				<img src="i/exit.png" class="button" id="logoffBtn" onClick="document.location='/user/Logoff;">
			</div>
		</header>
		
		 -->
		 
		 
		<div id="content">
			<iframe name="frmupage" src="hello.html"></iframe>
		</div>
		<div >
			<section class="sidebar">
		${menu}
		    </section>
		</div>
		<!-- 
        <h1>Work Break Down</h1>
        <p><a href="user/list"> Users</a></p>
        <p><a href="authorityGroup/list"> Authority Groups</a></p>
        <p><a href="workBreakDown/list"> Work Break Down Structure </a></p>
         -->
        
         
<!-- jQuery 2.2.3 -->
<script src="../../plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="../../plugins/datatables/jquery.dataTables.min.js"></script>
<script src="../../plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="../../plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="../../plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="../../dist/js/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="../../dist/js/demo.js"></script>
<!-- page script -->
<script>
  $(function () {
    $("#example1").DataTable();
    $('#example2').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
    </body>
</html>
