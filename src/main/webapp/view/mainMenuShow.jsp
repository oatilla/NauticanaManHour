<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}
<div align="center">
  
	<h3> ${PAGETITLE} </h3>
	<p>${MENU} : ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('mainMenu/edit?id=${record.id}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a></p>
  	
  	<h3> ${SCREEN_PAGE} </h3>
  
	<p><a class="btn btn-primary" href="#" onClick="doAjaxGet('screenPage/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"></i> ${NEW} </a></p>

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
			<td> <a href="/screenPage/show?id=${screenPage.id}"> ${screenPage.id} </a> </td>
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