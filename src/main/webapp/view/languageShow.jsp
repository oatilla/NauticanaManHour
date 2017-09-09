<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> ${PAGETITLE} </title>
</head>

<body>
  <div align="center">
    <h3><a href="edit?id=${record.id}"> ${EDIT} </a></h3>
    
	<p> ${LANGCODE} : ${record.id} ${record.caption} </p>

    <h3> ${CAPTION_TRANSLATION} </h3>

	<a href="/captionTranslation/new?parentKey=${langcode}"> ${NEW} </a>

    <table>
      <tr>
        <th>${CAPTION}</th>
        <th>${LABEL_UPPER}</th>
        <th>${LABEL_LOWER}</th>
		<th> &nbsp; </th>
      </tr>

      <c:forEach var="captionTranslation" items="${record.captionTranslations}" varStatus="status">
        <tr>
          <td>${captionTranslation.id.caption}</td>
          <td>${captionTranslation.labelupper}</td>
          <td>${captionTranslation.labellower}</td>
          <td>
          	<a href="/captionTranslation/edit?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}"> ${EDIT}" </a> &nbsp;
          	<a href="/captionTranslation/delete?id=${captionTranslation.id.caption},${captionTranslation.id.langcode}"> ${DELETE} </a>
          </td>
        </tr>
      </c:forEach>
    </table>
    
  </div>

</body>

</html>