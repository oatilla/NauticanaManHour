<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3> ${PAGETITLE} </h3>

<div align="center">
    <form:form method="post" modelAttribute="record">
    <table>
      <tr>
        <th>${SUBCONTRACTOR_ID}</th>
        <td>${record.id} <form:hidden path="id" /></td>
      </tr>
      <tr>
        <th>${CAPTION}</th>
        <td><form:input path="caption" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center"><input type="submit" value="${SAVE}"> </td>
      </tr>
    </table>
    </form:form>
</div>