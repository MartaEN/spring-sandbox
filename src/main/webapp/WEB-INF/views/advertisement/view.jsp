<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />

<spring:message code = "label_date" var = "labelDate" />
<spring:message code = "label_company" var = "labelCompany" />
<spring:message code = "label_category" var = "labelCategory" />

<!DOCTYPE>
<html>

<jsp:include page="../fragments/head_ckeditor.jsp"/>

<body>

    <jsp:include page="../fragments/header.jsp"/>

    <div id="templatemo_main_wrapper">
        <div id="templatemo_add_content_wrapper">

            <div id="templatemo_content">

                <c:if test="${not empty advertisement}">

                    <div class='post_section view'>
                        <h2><a class='advertisement__title' href=''></a>${advertisement.title}</h2>
                        <strong>${labelDate}: </strong><span class='advertisement__date'>
                        <fmt:formatDate pattern="yyyy-MM-dd" value="${advertisement.publishedDate}" /></span> |
                        <strong>${labelCompany}: </strong> <span class='advertisement__company'>${advertisement.company.name}</span>
                        <div class="cleaner"></div>
                        <p>
                            <div class='advertisement__content view'>${advertisement.content}</div>
                            <div class='cleaner'></div>
                            <p>
                                <div class='category view'>${labelCategory}: <span>${advertisement.category.name}</span></div>
                    </div>

                </c:if>
            </div>
            <div class="cleaner"></div>
        </div>
    </div>

    <jsp:include page="../fragments/footer.jsp"/>

</body>
