<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<spring:message code = "label_home_page" var = "labelHome" />
<spring:message code = "label_ad_create" var = "labelCreate" />
<spring:message code = "label_switch_language" var = "labelLocale" />

<div id="templatemo_header_wrapper">

    <div id="templatemo_header">
        <div id="site_title"></div>
        <div id="templatemo_rss">
            <a href="" target="_parent" style="display: block">SUBSCRIBE<br/><span>OUR FEED</span></a>
        </div>
    </div>

    <div id="templatemo_menu">
        <ul>
            <li><a href="${contextPath}">${labelHome}</a></li>
            <li><a href="${contextPath}/advertisements/add">${labelCreate}</a></li>
            <li><a href="${contextPath}/?lang=${labelLocale}">${labelLocale}</a></li>
        </ul>
    </div>

</div>
