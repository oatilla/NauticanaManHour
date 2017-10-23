<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
${DATATABLE2}

<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p>${AUTHORITY_GROUP} : ${record.id} ${record.caption}
		<c:if test="${!empty UPDATE_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('authorityGroup/edit?id=${record.id}');"> ${EDIT} </a>
		</c:if>
		</p>
	</div>
</div>

<div class="nav-tabs-custom">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab1" data-toggle="tab">${OBJECT_AUTHORIZATION}</a></li>
		<li><a href="#tab2" data-toggle="tab">${USER_AUTHORIZATION}</a></li>
	</ul>

	<div class="tab-content">
		<div class="tab-pane active" id="tab1">
		
<div class="box box-info">
	<div class="box-header with-border">
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('objectAuthorization/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>
	
	<div class="box-body">

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${OBJECT_TYPE}</th>
        <th>${ACTION}</th>
        <th>${KEY_VALUE}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="objectAuthorization" items="${record.objectAuthorizations}" varStatus="status">
        <tr>
          <td>${objectAuthorization.id.objectType}</td>
          <td>${objectAuthorization.id.action}</td>
          <td>${objectAuthorization.id.keyValue}</td>
          <td>
			<a class="btn btn-danger" href="#" onClick="doAjaxGet('objectAuthorization/delete?id=${objectAuthorization.id.authorityGroup},${objectAuthorization.id.objectType},${objectAuthorization.id.action},${objectAuthorization.id.keyValue}');"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>

	</div>
</div>

		</div>  

		<div class="tab-pane" id="tab2">
    
<div class="box box-info">
	<div class="box-header with-border">
		&nbsp;
	</div>
	
	<div class="box-body">

    <table id="dataTable2" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th>${USERNAME}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.username}</td>
          <td><a class="btn btn-danger" href="#" onClick="doAjaxGet('userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}');"> <i class="${DELETE_ICON}"></i> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>

	</div>
</div>

</div>
</div>
</div>
