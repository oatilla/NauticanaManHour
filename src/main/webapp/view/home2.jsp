<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
    <head>
		<META name="robots" content="noindex,nofollow">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home</title>

		<link rel="stylesheet" href="s/eds.css">
		<link rel="stylesheet" href="s/accordion-menu.css">
		<link rel="stylesheet" href="s/bootstrap.min.css">
		<link rel="stylesheet" href="s/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="s/Ionicons/css/ionicons.min.css">
		<link rel="stylesheet" href="s/dataTables.bootstrap.min.css">
		
		<script type="text/javascript" src="j/accordion-menu.js"></script>
 		<script type="text/javascript" src="j/nav.js"></script>
		<script src="j/jquery.min.js"></script>
		<script src="j/bootstrap.min.js"></script>


    </head>
    <body>
		<header id="header">
			<div>
				<button type="button" class="btn btn-primary" onClick="showMenu();"> <i class="${MENU_ICON}"></i> </button>
				${userCaption}
				<button type="button" class="btn btn-success" onClick="document.location='/userAccount/login';"> <i class="${LOGIN_ICON}"></i> </button>
				<button type="button" class="btn btn-danger" onClick="document.location='/userAccount/logoff';"> <i class="${LOGOFF_ICON}"></i> </button>
			</div>
		</header>
		<div id="content">

		</div>
		<div id="accordion">
		${menu}
		</div>

<script type="text/javascript">
function doAjaxPost(target) {

	$("#f").submit(function(e) {

	    $.ajax({
	           type: "POST",
	           url: target,
	           data: $("#f").serialize(), // serializes the form's elements.
	           success: function( response ) {
	   	        $("#content").load( response );
	   	        
	   	     }
  	     
	         });

	    e.preventDefault(); // avoid to execute the actual submit of the form.
	});
	}




// 	  $.ajax({
// 	     type: "POST",
// 	     url: target,
// 	     data: $("#f").serialize(),
// 	     success: function( data ) {
// 	        $("#content").html( data );
// 	     }
// 	  });
// 	}



</script>

    </body>
</html>
