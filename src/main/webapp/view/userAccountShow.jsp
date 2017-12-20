<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>
	<div class="box-body">
		<p> ${USERNAME} : ${record.id} ${record.caption}  ${record.emailAddress}
		<c:if test="${!empty UPDATE_ALLOWED}">
			<a class="btn btn-primary" href="#" onClick="doAjaxGet('userAccount/edit?id=${record.id}');"> ${EDIT} </a>
		</c:if>
		</p>
	</div>
</div>

<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title"> ${USER_AUTHORIZATION} </h3>
		<a class="btn btn-primary" href="#" onClick="doAjaxGet('userAuthorization/new?parentKey=${record.id}');"> ${NEW} </a>
	</div>

	<div class="box-body">

    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${AUTHORITY_GROUP} </th>
        <th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="userAuthorization" items="${record.userAuthorizations}" varStatus="status">
        <tr>
          <td>${userAuthorization.id.authorityGroup}</td>
          <td><a class="btn btn-danger" href="#" onClick="doAjaxGet('userAuthorization/delete?id=${userAuthorization.id.username},${userAuthorization.id.authorityGroup}');"> ${DELETE} </a></td>
        </tr>
      </c:forEach>
    </table>
    </div>
    
    <div class="box-footer">
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>
</div>