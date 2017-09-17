<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
    <p><a class="btn btn-primary" href="#" onClick="doAjaxGet('category/new');"> <i class="${NEW_ICON}"> ${NEW} </i> </a></p>
    
    <table id="dataTable1" class="table table-bordered table-hover">
	  <thead>
      <tr>
        <th> ${CATEGORY_ID}</th>
        <th> ${PARENT_ID}</th>
        <th> ${CAT_INDEX}</th>
        <th> ${CAPTION}</th>
        <th> ${DETAILS}</th>
        <th> ${UNIT}</th>
        <th> ${CAT_LEVEL}</th>
        <th> ${TREE_CODE}</th>
        <th> ${MAIN_FLAG}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.id}</td>
          <td>${record.parentId}</td>
          <td>${record.catIndex}</td>
          <td>${record.caption}</td>
          <td>${record.details}</td>
          <td>${record.unit}</td>
          <td>${record.catLevel}</td>
          <td>${record.treeCode}</td>
          <td>${record.mainFlag}</td>
			<td>
				<a class="btn btn-primary" href="#" onClick="doAjaxGet('category/edit?id=${record.id}');"> <i class="${EDIT_ICON}"> </i> ${EDIT} </a>
				<a class="btn btn-danger" href="#" onClick="doAjaxGet('category/delete?id=${record.id}');"> <i class="${DELETE_ICON}"> </i> ${DELETE} </a>
			</td>
        </tr>
      </c:forEach>             
    </table>
</div>
