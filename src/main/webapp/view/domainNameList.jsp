<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">

    <p><a href="domainName/new"><c:out value="${NEW}" /></a></p>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th><c:out value="${DOMAIN}" /></th>
        <th><c:out value="${CAPTION}" /></th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td> <a href="domainName/show?id=${record.id}"> ${record.id} </a> </td>
          <td>${record.caption}</td>
          <td>
          	<a href="domainName/edit?id=${record.id}"> ${EDIT} </a> &nbsp;
          	<a href="domainName/delete?id=${record.id}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>             
    </table>
</div>