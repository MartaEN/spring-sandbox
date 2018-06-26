<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />

<!DOCTYPE >
<html>

<jsp:include page="../fragments/head.jsp"/>

<body>

    <jsp:include page="../fragments/header.jsp"/>

    <div id="templatemo_main_wrapper">
        <div id="templatemo_content_wrapper">

            <div id="templatemo_content">
                <h2 class="category_name">${category.name}</h2>
            </div>

            <jsp:include page="../fragments/categories.jsp"/>

            <jsp:include page="../fragments/banners.jsp"/>

            <div class="cleaner"></div>

        </div>
    </div>

    <button class="btn_load">Загрузить еще</button>

    <jsp:include page="../fragments/footer.jsp"/>

    <script>
        var url = "./${category.id}/advertisements_ajax";
        var contextPath = "${contextPath}";
    </script>
    <script src="${resPath}/assets/getData.js"></script>

</body>

</html>