<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />
<spring:message code = "label_bulletin_board" var ="labelSiteTitle"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${labelSiteTitle}</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <link href="${resPath}/style.css" rel="stylesheet" type="text/css" />
</head>