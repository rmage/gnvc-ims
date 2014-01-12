<%-- 
    Document   : Error
    Created on : Jun 29, 2010, 5:30:50 PM
    Author     : login
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <%@include file="./metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="./headerError.jsp" %>

            <div id="content" style="display: none" class="span-19 last">
                <%--
                <c:if test="${sessionScope['user']==null}">
                    <c:redirect url="index.htm"/>
                </c:if>
                --%>
                <div class="box">
                    <h2>Sorry, we encountered a problem</h2>
                    <br><h3> Please report to IMS SPFI Group Support, <a href="http://appgroup.co.id:8080/appWeb/index.htm"> click here </a></h3>
                    <c:if test="${th!=null}">
                        <c:out value="${th.message}" />
                    </c:if>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
    </body>
</html>
