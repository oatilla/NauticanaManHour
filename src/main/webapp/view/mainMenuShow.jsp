<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
  
  	<p> ${MENU} ${record.id} </p>
  	
  	<h3> ${SCREEN_PAGE} </h3>
  
	<a href="#" onclick="doAjaxPost('screenPage/new?parentKey=${record.id}');"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
		<thead>
		<tr>
			<th> ${PAGENAME} </th>
			<th> ${CAPTION} </th>
			<th> ${ICON} </th>
			<th> ${URL} </th>
			<th> ${DISPLAY_ORDER} </th>
		</tr>
		</thead>
		
		<c:forEach var="screenPage" items="${record.screenPages}" varStatus="status">
		<tr>
			<td> <a href="#" onclick="doAjaxPost('screenPage/show?id=${screenPage.id}');"> ${screenPage.id} </a> </td>
			<td>${screenPage.caption}</td>
			<td>${screenPage.icon}</td>
			<td>${screenPage.url}</td>
			<td>${screenPage.displayOrder}</td>
			<td>
				<a href="#" onclick="doAjaxPost('screenPage/edit?id=${screenPage.id}');"> ${EDIT} </a>
				<a href="#" onclick="doAjaxPost('screenPage/delete?id=${screenPage.id}');"> ${DELETE} </a>
			</td>
		</tr>
		</c:forEach>             
	</table>
</div>  