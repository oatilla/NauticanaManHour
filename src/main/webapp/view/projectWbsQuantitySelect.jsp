<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>
    <table id="dataTable1" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>${PRJ_CAPTION}</th>
        <th>${TREE_CODE}</th>
        <th>${CAT_CAPTION}</th>
        <th>${UNIT}</th>
        <th>${METRIC}</th>
        <th>${QUANTITY}</th>
        <th>${SUM_QUANTITY}</th>
        <th>${SUM_MANHOUR}</th>
        <th>${SUM_OVERTIME}</th>
        <th>${BEGDA}</th>
        <th>${ENDDA}</th>
        <th>${LAST_QUANTITY}</th>
		<th> &nbsp; </th>
      </tr>
      </thead>

      <c:forEach var="record" items="${records}" varStatus="status">
        <tr>
          <td>${record.prjCaption}</td>
          <td>${record.treeCode}</td>
          <td>${record.catCaption}</td>
          <td>${record.unit}</td>
          <td>${record.metric}</td>
          <td>${record.quantity}</td>
          <td>${record.sumQuantity}</td>
          <td>${record.sumManhour}</td>
          <td>${record.sumOvertime}</td>
          <td>${record.begda}</td>
          <td>${record.endda}</td>
          <td>${record.lastQuantity}</td>
          <td>
			<a href="#" onClick="doAjaxGet('projectWbsQuantity/new?id=${record.id.projectId},${record.id.categoryId},${record.nextTermYear},${record.termType},${record.nextTermId}');" class="btn btn-primary"> <i class="${NEW_ICON}"></i> ${NEW} </a>
		  </td>
        </tr>
      </c:forEach>             
    </table>
</div>