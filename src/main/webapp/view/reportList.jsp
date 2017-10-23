<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
  $(function () {
    //Initialize Select2 Elements
    $(".select2").select2();

 	//Date picker
    $('#startDate').datepicker({
      autoclose: true
    });
  });
</script>
<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<div class="box-body">
    
		<form name="f" id="f">

		<table class="table table-bordered table-hover">

			<tr>
				<th> ${PROJECT}	</th>
				<td>
					<select name=projectId id=projectId onChange="doAjaxGet('report/list?projectId=' + document.f.projectId.value + '&begda=' + document.f.begda.value + '&endda=' + document.f.endda.value);">
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${project.id == projectId}">
							<option value="${project.id}" selected> ${project.caption} </option>
							</c:when>
							<c:otherwise>
							<option value="${project.id}"> ${project.caption} </option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</td>
			</tr>

			<tr>
				<th> ${SUBCONTRACTOR} </th>
				<td>
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${project.id == projectId}">
								<select name=subcontractorId id=subcontractorId>
								<option value=""> - </option>
								<c:forEach var="team" items="${project.projectTeams}">
									<c:forEach var="person" items="${team.projectTeamPersonnel}">
										<c:if test="${person.teamLead == 1}">
											<c:choose>
												<c:when test="${person.worker.subcontractor.id == subcontractorId}">
													<option value="${person.worker.subcontractor.id}" selected> ${person.worker.subcontractor.caption} </option>
												</c:when>
												<c:otherwise>
													<option value="${person.worker.subcontractor.id}"> ${person.worker.subcontractor.caption} </option>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</c:forEach>
								</select>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>

			<tr>
				<th> ${PROJECT_TEAM} </th>
				<td>
					<c:forEach var="project" items="${projects}">
						<c:choose>
							<c:when test="${project.id == projectId}">
								<select name=teamId id=teamId>
								<c:forEach var="team" items="${project.projectTeams}">
									<option value="${team.id.teamId}"> ${team.caption} </option>
								</c:forEach>
								</select>
							</c:when>
						</c:choose>
					</c:forEach>
				</td>
			</tr>

			<tr>
				<th> ${BEGDA} </th>
				<td> <input type="date" name="begda" id="begda" value="${begda}"> </td>
			</tr>

			<tr>
				<th> ${ENDDA} </th>
				<td> <input type="date" name="endda" id="endda" value="${endda}"> </td>
			</tr>

			<tr>
			<td colspan=2>
			<c:forEach var="report" items="${reports}" varStatus="status">
			<a href="#"  class="btn btn-primary" onclick="doAjaxPost('${report.value}');"> ${report.key} </a> &nbsp;
			</c:forEach>
			</td>
			</tr>
		</table>

		</form>
    </div>
</div>