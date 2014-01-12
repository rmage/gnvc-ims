<%-- 
    Document   : rptSalesRecapPerAlias
    Created on : Aug 21, 2010, 6:37:19 PM
    Author     : zyrex
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Report Sales Recap Per Alias</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>
            <%
            	String datePattern = com.app.wms.web.helper.DateHelper.DATE_PATTERN_DASH;
            %>
            <h1><h:outputText value="rptSalesRecapPerAlias Form"/></h1>
            <div id="content" style="display: none" class="span-19 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Generate Report Sales Recapitulation Per Alias</caption>
                        <form:form method="post" action="rptSalesRecapPerAlias.htm" commandName="dto">
                            <tbody class="tbl-nohover">
                            <input type="hidden" name="templateName" value="rptSalesRecapPerAlias_2" />
                            <input type="hidden" name="parametersKey" value="P_LOCATION,P_START_DATE,P_END_DATE,P_USERNAME"/>

                            <c:if test="${model.isALL}">
                                <tr>
                                    <td class="style1">Location</td>
                                    <td class="style1">
                                        <label>
                                            <form:select path="location">
                                                <form:option value="%%" label="All Location"/>
                                                <c:forEach items="${model.branchList}" var="branch" varStatus="prodCounter">
                                                    <form:option value="${branch.branchCode}" label="${branch.name}"/>
                                                </c:forEach>
                                            </form:select>
                                        </label>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!model.isALL}">
                                <input type="hidden" name="location" value="%%" />
                            </c:if>

                            <tr>
                                <td class="style1">Dealer Code</td>
                                <td class="style1">
                                    <label>
                                        <form:select path="dealerCode">
                                            <c:if test="${model.isALL}">
                                                <form:option value="%%" label="All BG"/>
                                                <c:if test="${model.lu.isWic eq 'Y'}">
                                                    <form:option value="%WIC%" label="All WIC"/>
                                                </c:if>
                                                <c:if test="${model.lu.isDsa eq 'Y'}">
                                                    <form:option value="%DSA%" label="All DSA"/>
                                                </c:if>
                                            </c:if>
                                            <c:forEach items="${model.bgList}" var="bg" varStatus="prodCounter">
                                                <form:option value="${bg.bgCode}" label="${bg.name}"/>
                                            </c:forEach>
                                        </form:select>
                                    </label>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Catalog Code</td>
                                <td class="style1">
                                    <label>
                                        <form:select path="catalogCode">
                                            <form:option value="%%" label="All Catalog"/>
                                            <form:option value="CS" label="Consigment"/>
                                            <form:option value="SERVICE" label="Services"/>
                                        </form:select>
                                    </label>
                                </td>
                            </tr>

                            <fmt:formatDate value="${startDate}" pattern="<%=datePattern%>" var="fstartdate" scope="request"/>
                            <c:if test="${startDate==null || empty startDate}">
                                <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fstartdate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="start-date-label">Start Date</label></td>
                                <td class="style1"><input id="start-date" class="date-pick" type="text" name="startDate" value="${fstartdate}" /></td>
                            </tr>

                            <fmt:formatDate value="${endDate}" pattern="<%=datePattern%>" var="fenddate" scope="request"/>
                            <c:if test="${endDate==null || empty endDate}">
                                <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fenddate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="end-date-label">End Date</label></td>
                                <td class="style1"><input id="end-date" class="date-pick" type="text" name="endDate" value="${fenddate}" /></td>
                            </tr>

                            <form:hidden path="username" />
                            <form:hidden path="bgType" />

                            <%--<tr>
                                <td class="style1">Username</td>
                                <td class="style1">
                                    <form:input path="username" readonly="true"/>
                                </td>
                            </tr>--%>
                            <tr>
                                <td class="style1">Output Format</td>
                                <td class="style1">
                                    <form:select path="outputFormat">
                                        <form:option value="pdf" label="PDF"/>
                                        <form:option value="xls" label="XLS"/>
                                        <form:option value="csv" label="CSV"/>
                                        <form:option value="rtf" label="RTF"/>
                                        <%--<form:option value="html" label="HTML"/>--%>
                                    </form:select>
                                </td>
                            </tr>

                            </tbody>
                            <tfoot>
                                <tr>
                                    <td></td>
                                    <td>
                                        <label>
                                            <input type="submit" name="action" id="btnSave" value="saveOnlineReport" />
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
                        </form:form>
                    </table>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var datePattern = 'dd-mm-yy';
            $(document).ready(
            $(function() {
                $('#start-date').datepicker({dateFormat:datePattern});
                $('#end-date').datepicker({dateFormat:datePattern});
            })
        );
        </script>
    </body>
</html>

