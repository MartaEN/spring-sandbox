<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>
<%@ taglib prefix = "sec" uri = "http://www.springframework.org/security/tags" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<spring:message code = "label_home_page" var = "labelHome" />
<spring:message code = "label_ad_create" var = "labelCreate" />
<spring:message code = "label_switch_language" var = "labelLocale" />
<spring:message code = "label_button_reg" var = "labelReg" />
<spring:message code = "label_button_login" var = "labelLogin" />
<spring:message code = "label_button_logout" var = "labelLogout" />

<div id="templatemo_header_wrapper">

    <div id="templatemo_header">
        <div id="site_title"></div>
        <div id="templatemo_rss">
            <a href="" target="_parent" style="display: block">SUBSCRIBE<br/><span>OUR FEED</span></a>
        </div>
    </div>

    <div id="templatemo_menu">
        <ul id = "panel_reff">
            <li><a href="${contextPath}">${labelHome}</a></li>
            <sec:authorize access = "isAuthenticated()">
                <li><a href="${contextPath}/advertisements/add">${labelCreate}</a></li>
            </sec:authorize>
            <sec:authorize access = "isAnonymous()">
                <li><a href = "${contextPath}/registration" > ${labelReg} </a></li>
            </sec:authorize>
        </ul>
        <ul id = "panel_acc">
            <sec:authorize access = "isAnonymous()">
                <li><a href = "${contextPath}/login" > ${labelLogin} </a></li>
            </sec:authorize>
            <sec:authorize access = "isAuthenticated()">
                <li><a id="quit" href = "${contextPath}/logout" > ${labelLogout}</a>
                    <span id="username"> ${pageContext.request.userPrincipal.name} | </span>
                </li>
            </sec:authorize>
            <li><a href="${contextPath}/?lang=${labelLocale}">${labelLocale}</a></li>
        </ul>
    </div>

</div>
