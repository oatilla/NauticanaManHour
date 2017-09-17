<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
function submitTable() {
    var csv = [];
    var rows = document.querySelectorAll("table tr");
    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");
        for (var j = 0; j < cols.length; j++) 
            row.push(cols[j].innerText);
        csv.push(row.join(","));        
    }
    document.f.data.value=csv.join("\n");
    document.f.submit();
}
</script>

${DATATABLE1}
<div align="center">
	<h3> ${PAGETITLE} </h3>

	<table class="table table-bordered table-hover table-editable">
	<c:forEach var="row" items="${records}" varStatus="rowStatus">
		<tr>
		<c:forEach var="col" items="${row}" varStatus="colStatus">
			<c:choose>
				<c:when test="${rowStatus.index < 2 && !empty col}">
					<th colspan=2> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index < 2 && empty col}">
				</c:when>
				<c:when test="${rowStatus.index == 2}">
					<th> ${col} </th>
				</c:when>
				<c:when test="${rowStatus.index > 2 && colStatus.index < 2}">
					<th> ${col} </th>
				</c:when>
				<c:otherwise>
					<td contenteditable="true"> ${col} </td>
				</c:otherwise>
			</c:choose>
          </c:forEach>             
        </tr>
      </c:forEach>             
    </table>

	<form name=f method="post">
	<input type=hidden name=projectId value="${projectId}">
	<input type=hidden name=teamId value="${teamId}">
	<input type=hidden name=date value="${date}">
	<input type=hidden name=data value="">
	<button type="submit" class="btn btn-primary" onClick="doAjaxPost('projectWbsManhour/show');"> <i class="${SAVE_ICON}"></i> ${SAVE} </button>
	<button type="button" class="btn btn-warning" onClick="doAjaxGet('${prevpage}');"> <i class="${CANCEL_ICON}"></i> ${CANCEL} </button> 
	</form>
</div>