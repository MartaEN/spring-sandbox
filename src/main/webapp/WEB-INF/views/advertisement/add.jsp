<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://www.springframework.org/tags" prefix = "spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${contextPath}/resources" var="resPath" />

<spring:message code = "label_ad_create" var = "labelCreate" />
<spring:message code = "label_category" var = "labelCategory" />
<spring:message code = "label_category_selection" var = "labelSelectCategory" />
<spring:message code = "label_ad_title" var = "labelAdTitle" />
<spring:message code = "label_company_info" var = "labelCompanyInfo" />
<spring:message code = "label_company_name" var = "labelCompanyName" />
<spring:message code = "label_button_publish" var = "labelPublish" />

<!DOCTYPE>
<html>

<jsp:include page="../fragments/head_ckeditor.jsp"/>

<body>

    <jsp:include page="../fragments/header.jsp" />

    <div id="templatemo_main_wrapper">
        <div id="templatemo_add_content_wrapper">

            <div id="templatemo_content">

                <c:if test="${not empty message}">
                    <span class="error">${message}</span>
                </c:if>

                <div class="post_section">
                   
                    <form:form modelAttribute="advertisement" class="add_advertisement_form" method="POST" action="${contextPath}/advertisements">
                       
                        <h2 class="message">${labelCreate}</h2>

                        <strong class="add_category">${labelCategory}</strong>
                        
                        <select id="categoryId" name="categoryId" class="cd-select">
          	                 <c:if test="${not empty categories}">
          	                     <option value="0" selected>${labelSelectCategory}</option>
          	                     <c:forEach items="${categories}" var="category">
                                     <option value="${category.id}">${category.name}</option>
                                 </c:forEach>
                            </c:if>
                        </select>
                        
                        <p><form:label path="title" class="add_title">${labelAdTitle}</form:label>
                        <form:input type="text" path="title" class="add_title_input" />
                        <p><form:errors path = "title" cssClass = "error" />
                        <p style="padding-top:50px;">

                        <form:errors path="content" cssClass="error" />
                        <p><form:textarea path="content" id="content" class="contentarea" />

                        <div class="company_add">
                            <span class="company_info_title">${labelCompanyInfo}</span>
                            <form:input path="company.name" type="text" placeholder="${labelCompanyName}" class="add_company_name" />
                        </div>

                        <input type="submit" class="button_sub" value="${labelPublish}" />
                    
                    </form:form>
                    
                </div>

                <div class="cleaner_h40">
                    <!-- a spacing between 2 posts -->
                </div>

            </div>

            <div class="cleaner_h40"></div>

        </div>

        <div class="cleaner"></div>
    </div>

    <jsp:include page="../fragments/footer.jsp" />

    <script type="text/javascript">
        $(document).ready(function() {
            CKEDITOR.replace('content');
            CKEDITOR.config.width = "100%";
            CKEDITOR.config.height = 600;
        });
    </script>

</body>
