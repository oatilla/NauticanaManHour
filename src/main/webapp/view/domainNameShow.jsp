<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p> ${DOMAIN} : ${record.id} ${record.caption}
		<c:if test="${!empty UPDATE_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainName/edit?id=${record.id}');"> ${EDIT} </a>
		</c:if>	
		<c:if test="${!empty RELOAD_VALUES}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('language/reloadValues?id=${record.id}');"> ${RELOAD_VALUES} </a>
		</c:if>
		</p>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${DOMAIN_VALUE} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('domainValue/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>

	<div class="box-body">

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
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${DOMAIN_LOOKUP} </h3>
	</div>
	
	<div class="box-body">

    <table id="dataTable2" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${TABLENAME}</th>
        <th>${FIELDNAME}</th>
      </tr>
      </thead>

      <c:forEach var="domainLookup" items="${record.domainLookups}" varStatus="status">
        <tr>
          <td>${domainLookup.id.tableName}</td>
          <td>${domainLookup.fieldName}</td>
        </tr>
      </c:forEach>
    </table>
    </div>
</div>