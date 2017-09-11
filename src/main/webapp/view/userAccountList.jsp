<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    <h3><a href="#" onclick="doAjaxPost('userAccount/new');"> ${NEW} </a></h3>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${USERNAME} </th>
        <th>${CAPTION} </th>
        <th>${STATUS} </th>
        <th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td><a href="#" onclick="doAjaxPost('userAccount/show?id=${record.id}');"> ${record.id} </a> </td>
          <td>${record.caption}</td>
          <td>${record.status}</td>
          <td>
          	<a href="#" onclick="doAjaxPost('userAccount/edit?id=${record.id}');"> ${EDIT} </a>
          	<a href="#" onclick="doAjaxPost('userAccount/delete?id=${record.id}');"> ${DELETE} </a></td>
        </tr>
      </c:forEach>             
    </table>
</div>