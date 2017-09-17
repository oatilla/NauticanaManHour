<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}

<div align="center">
  
    <h3> ${PAGETITLE} </h3>

	<p> ${DOMAIN} : ${record.id} ${record.caption} <a class="btn btn-primary" href="#" onClick="doAjaxGet('domainName/edit?id=${record.id}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a> </p>

    <h3> ${DOMAIN_VALUE} </h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainValue/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"></i> ${NEW} </a>

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
          	<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainValue/edit?id=${domainValue.id.domain},${domainValue.id.refvalue}');"> <i class="${EDIT_ICON}"></i> ${EDIT} </a>
          	<a class="btn btn-danger" href="#" onClick="doAjaxGet('domainValue/delete?id=${domainValue.id.domain},${domainValue.id.refvalue}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
    <h3> ${DOMAIN_LOOKUP} </h3>

	<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainLookup/new?parentKey=${record.id}');"> <i class="${NEW_ICON}"></i> ${NEW} </a>

    <table id="dataTable2" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${TABLENAME}</th>
        <th>${FIELDNAME}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="domainLookup" items="${record.domainLookups}" varStatus="status">
        <tr>
          <td>${domainLookup.id.tableName}</td>
          <td>${domainLookup.fieldName}</td>
          <td>
          	<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainLookup/edit?id=${domainLookup.id.domain},${domainLookup.id.tableName}');"> <i class="${EDIT_ICON}"></i>${EDIT} </a>
          	<a class="btn btn-danger" href="#" onClick="doAjaxGet('domainLookup/delete?id=${domainLookup.id.domain},${domainLookup.id.tableName}');"> <i class="${DELETE_ICON}"></i>${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
</div>