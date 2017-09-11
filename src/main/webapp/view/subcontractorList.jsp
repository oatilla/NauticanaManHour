<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    <h3><a href="#" onclick="doAjaxPost('subcontractor/new');"> ${NEW} </a></h3>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${SUBCONTRACTOR_ID} </th>
        <th> ${CAPTION} </th>
  		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> ${record.id} </td>
          <td> <a href="#" onclick="doAjaxPost('subcontractor/show?id=${record.id}');"> ${record.caption} </a> </td>
          <td>
          	<a href="#" onclick="doAjaxPost('subcontractor/edit?id=${record.id}');"> ${EDIT} </a>
          	<a href="#" onclick="doAjaxPost('delete?id=${record.id}');"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>             
    </table>
</div>