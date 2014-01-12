<%-- 
    Document   : rptAgentList
    Created on : Jun 21, 2010, 10:06:16 AM
    Author     : zyrex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <title>Report Agent List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <%
            	String datePattern = com.app.wms.web.helper.DateHelper.DATE_PATTERN_DASH;
            %>
            <h1><h:outputText value="rptAgentList Form"/></h1>

            <div id="content" style="display: none" class="span-19 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Generate Report Agent List</caption>
                        <form:form method="post" action="rptAgentList.htm" commandName="dto">
                            <tbody class="tbl-nohover">
                                <input type="hidden" name="templateName" value="rptAgenList" />
                                <input type="hidden" name="parametersKey" value="P_LOCATION,P_DEALER_CODE,P_DATE,P_USERNAME"/>
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

                                <form:hidden path="username" />
                                <form:hidden path="bgType" />

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
                $('#date').datepicker({dateFormat:datePattern});
            })
        );
        </script>
    </body>
</html>

