<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <style>
    .hide{
      display:none;
        }
    .someclass{
    color: red;
	}
  
    </style>

<script type="text/javascript">
$(document).ready(function() {

$('input[type="text"]').keyup(function(){

var filter = jQuery(this).val();
  $(".menu ul > li").removeClass("hide");
  $(".menu ul > li").removeClass("show");
  
  $(".menu ul > li").each(function () {
     if ($(this).text().search(new RegExp(filter, "i")) < 0 && !$(this).hasClass('show')) {
	  	$(this).addClass('hide');
	    $(this).find('label').removeAttr("style");
//	    $(this).find('input:checkbox[name="Arrow"]').removeAttr("checked");
     } else {
	  	$(this).addClass('show');
	    $(this).find('input:checkbox[name="Arrow"]').attr("checked","checked");
	    $(this).find('label').attr("style", "color:red");   
     }
   });       
});

});
</script>
${DATATABLE1}
<div class="box box-primary menu">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('category/new');"> ${NEW} </a>
		<form name="f" id="f">
			${CAPTION_FILTER}
			<input type="text" />
			${PROJECT_FILTER}
			<select name=projectId id=projectId onChange="doAjaxGet('category/list?projectId=' + document.f.projectId.value);">
			    <option value="-1"> - </option>
			    <c:forEach var="project" items="${projects}" varStatus="status">
					<c:choose>
						<c:when test="${projectId == project.id}">
							<option value="${project.id}" selected> ${project.caption} </option>
						</c:when>
						<c:otherwise>
							<option value="${project.id}"> ${project.caption} </option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		
		</form>
	</div>

	<div class="box-body" class="menu">

${wbshtml}
    
    </div>
</div>
