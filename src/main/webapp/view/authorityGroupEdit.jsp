<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


   <div align="center">
    <form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
     <div class="box-body">
     <h1> ${PAGETITLE}</h1>
      <div class="form-group has-feedback row">
      	  <label class="col-sm-2 control-label" for="authGroup">${AUTHORITY_GROUP}</label>
	      <div class="col-sm-10">  
	        <form:input placeholder=""  id="id" name="authGroup" class="form-control" path="id"/>
	      </div>
      </div>
      <div class="form-group has-feedback row">
  		    <label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
        	<div class="col-sm-10"> 
       			 <form:input placeholder="" id="caption" name="caption" class="form-control" path="caption"/>
       		</div>
      </div>
      </div>
       <div class="box-footer row">
			<a href="#" onclick="doAjaxPost('authorityGroup/edit'); " class="btn btn-primary pull-right btn-flat" >${SAVE}</a>
	    </div>
    </form:form>

</div>
<!--
// Attach a submit handler to the form
$( "#authGroupForm" ).submit(function( event ) {
 
  // Stop form from submitting normally
  event.preventDefault();
 
  // Get some values from elements on the page:
  var $form = $( this ),
  authGroup_val = $form.find( "input[name='id']" ).val(),
  caption_val = $form.find( "input[name='caption']" ).val(),
    url = "authorityGroup/edit";
 
  // Send the data using post
  var posting = $.post( url, { id: authGroup_val, caption: caption_val } );
 
  // Put the results in a div
  posting.done(function( data ) {
//   var content = $( data ).find( "#record" );
 //   $( "#subView" ).empty().append( content );
    $( "#subView" ).html( data );
  });
});
-->
</script>

