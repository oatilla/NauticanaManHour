<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
    <h3><a href="#"   onclick="doAjaxPost('authorityGroup/new');"  > <c:out value="${NEW}" /> ${PAGETITLE} </a></h3>

    <div class="box">
		<div class="box-header">
	         <h3 class="box-title">Authority Groups</h3>
	    </div>
	 	<div class="box-body">
	   		 <table class="table table-bordered table-hover">
            <thead>
              <tr>
			      <th>${AUTORITY_GROUP}</th>
			      <th>${CAPTION}</th>
					<th> &nbsp; </th>
                </tr>
            </thead>

	<tbody>
      <c:forEach var="record" items="${records}" varStatus="status">
      <tr>
        <td> <a href="#" onclick="doAjaxPost('authorityGroup/show?id=${record.id}');"> ${record.id} </a> </td>
        <td>${record.caption}</td>
        <td>
        	<a href="#"   onclick="doAjaxPost('authorityGroup/edit?id=${record.id}');"> ${EDIT} </a> &nbsp;
        	<a href="#" onclick="doAjaxPost('authorityGroup/delete?id=${record.id}');"> ${DELETE} </a></td>
      </tr>
    </c:forEach> 
    </tbody>            
  </table>
		</div>
  	</div>
  
