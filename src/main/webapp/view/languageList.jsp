<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div>

    <a href="language/new"> ${NEW} </a>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${LANGCODE}</th>
        <th>${CAPTION}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.id}</td>
          <td> <a href="language/show?id=${record.id}"> ${record.caption} </a> </td>
          <td>
          	<a href="language/edit?id=${record.id}"> ${EDIT} </a> &nbsp;
          	<a href="language/delete?id=${record.id}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>             
    </table>
</div>
