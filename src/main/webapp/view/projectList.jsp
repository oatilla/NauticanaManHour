<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3> ${PAGETITLE} </h3>

<p><a href="#" onclick="doAjaxPost('project/new');"> ${NEW} </a></p>

<div align="center">
	   		 <table class="table table-bordered table-hover">
			      
			     <thead> 
			      <tr>
			        <th>${PROJECT_ID}</th>
			        <th>${CAPTION}</th>
			 		<th> &nbsp; </th>
			      </tr>
				</thead>
		      <c:forEach var="record" items="${records}" varStatus="status">
		        <tr>
		          <td>${record.id}</td>
		          <td><a href="#" onclick="doAjaxPost('show?id=${record.id}');"> ${record.caption} </a></td>
		          <td>
					<a href="#" onclick="doAjaxPost('project/edit?id=${record.id}');"> ${EDIT} </a>
					<a href="#" onclick="doAjaxPost('project/delete?id=${record.id}');"> ${DELETE} </a>
					<a href="#" onclick="doAjaxPost('projectWbs/list?projectId=${record.id}');"> ${PROJECT_WBS} </a>
					<a href="#" onclick="doAjaxPost('projectTeam/list?projectId=${record.id}');"> ${PROJECT_TEAM} </a>
				  </td>
		        </tr>
		      </c:forEach>
    </table>
  </div>

<script>
  $(function () {
    $('#dataTable1').DataTable({
      "paging": true,
      "lengthChange": false,
      "searching": false,
      "ordering": true,
      "info": true,
      "autoWidth": false
    });
  });
</script>
