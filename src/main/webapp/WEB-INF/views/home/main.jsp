<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />

<spring:message code = "label_button_load" var = "labelLoadMore" />

<!DOCTYPE >
<html>

<jsp:include page="../fragments/head.jsp"/>

<body>

    <jsp:include page="../fragments/header.jsp"/>

    <div id="templatemo_main_wrapper">
        <div id="templatemo_content_wrapper">

            <div id="templatemo_content"></div>

            <jsp:include page="../fragments/categories.jsp"/>

            <jsp:include page="../fragments/banners.jsp"/>

            <div class="cleaner"></div>

        </div>
    </div>

    <button class="btn_load">${labelLoadMore}</button>

    <jsp:include page="../fragments/footer.jsp"/>

    <script>
        var url = "./advertisements/advertisements_ajax";
        var contextPath = "${contextPath}";
    </script>

    <script src="${resPath}/assets/getData.js"></script>

</body>

</html>
