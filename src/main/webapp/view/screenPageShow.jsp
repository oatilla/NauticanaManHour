<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p> ${PAGENAME} : ${record.id} ${record.caption} ${record.url}
			<c:if test="${!empty UPDATE_ALLOWED}">
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('screenPage/edit?id=${record.id}');"> ${EDIT} </a>
			</c:if>
		</p>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">

	</div>
	
	<div class="box-body">

    </div>
</div>