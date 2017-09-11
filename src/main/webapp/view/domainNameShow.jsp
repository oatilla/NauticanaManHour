<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    
	<p> ${DOMAIN} : ${record.id} ${record.caption} <a href="edit?id=${record.id}"> ${EDIT} </a> </p>

    <h3> ${DOMAIN_VALUE} </h3>

	<a href="domainValue/new?parentKey=${record.id}"> ${NEW} </a>

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${REFVALUE}</th>
        <th>${CAPTION}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="domainValue" items="${record.domainValues}" varStatus="status">
        <tr>
          <td>${domainValue.id.refvalue}</td>
          <td>${domainValue.caption}</td>
          <td>
          	<a href="domainValue/edit?id=${domainValue.id.domain},${domainValue.id.refvalue}"> ${EDIT} </a> &nbsp;
          	<a href="domainValue/delete?id=${domainValue.id.domain},${domainValue.id.refvalue}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
</div>