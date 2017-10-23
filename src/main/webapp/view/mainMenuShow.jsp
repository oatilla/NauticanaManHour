<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p> ${MAIN_MENU} : ${record.id} ${record.caption}
		<c:if test="${!empty UPDATE_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('mainMenu/edit?id=${record.id}');"> ${EDIT} </a>
		</c:if>
		</p>
	</div>
</div>
  	
<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${SCREEN_PAGE} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('screenPage/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>
	
	<div class="box-body">

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${PAGENAME} </th>
			<th> ${CAPTION} </th>
			<th> ${ICON} </th>
			<th> ${URL} </th>
			<th> ${DISPLAY_ORDER} </th>
			<th> &nbsp; </th>
		</tr>
		</thead>
		
		<c:forEach var="screenPage" items="${record.screenPages}" varStatus="status">
		<tr>
			<td> <a href="#" onClick="doAjaxGet('/screenPage/show?id=${screenPage.id}');"> ${screenPage.id} </a> </td>
			<td>${screenPage.caption}</td>
			<td>${screenPage.icon}</td>
			<td>${screenPage.url}</td>
			<td>${screenPage.displayOrder}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('screenPage/edit?id=${screenPage.id}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a> &nbsp;
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('screenPage/delete?id=${screenPage.id}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a> &nbsp;
			</td>
		</tr>
		</c:forEach>             
	</table>
	</div>
</div>