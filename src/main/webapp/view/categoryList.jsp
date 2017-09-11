<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}

<h3> ${PAGETITLE} </h3>

<div align="center">
    <h3><a href="category/new"> <i class="${NEW_ICON}"> ${NEW} </i> </a></h3>
    
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
          <td> <a href="#" onclick="doAjaxPost('category/show?id=${record.id}');"> ${record.id} </a></td>
          <td>${record.parentId}</td>
          <td>${record.catIndex}</td>
          <td>${record.caption}</td>
          <td>${record.details}</td>
          <td>${record.unit}</td>
          <td>${record.catLevel}</td>
          <td>${record.treeCode}</td>
          <td>${record.mainFlag}</td>
          <td>
          <a href="#"   onclick="doAjaxPost('category/edit?id=${record.id}');" class="btn btn-primary" > ${EDIT} <i class="${EDIT_ICON}"> </i> </a> &nbsp;
        	<a href="#" onclick="category/delete?id=${record.id}');" class="btn btn-danger"> ${DELETE} <i class="${DELETE_ICON}"> </i> </a></td>

          </td>
        </tr>
      </c:forEach>             
    </table>
</div>