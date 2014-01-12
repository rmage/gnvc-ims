<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Report Cash HandOver</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>
            <%
            	String datePattern = com.app.wms.web.helper.DateHelper.DATE_PATTERN_DASH;
            %>
            <h1><h:outputText value="rptCashHandOver Form"/></h1>
            <div id="content" style="display: none" class="span-19 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Generate Report Cash HandOver</caption>
                        <form:form method="post" action="rptCashHandOver.htm" commandName="dto">
                            <tbody class="tbl-nohover">
                            <input type="hidden" name="templateName" value="rptCashHandOver" />
                            <input type="hidden" name="parametersKey" value="P_DEALER_CODE,P_CONFIRM_DATE"/>

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

                            <fmt:formatDate value="${confirmDate}" pattern="<%=datePattern%>" var="fconfirmDate" scope="request"/>
                            <c:if test="${confirmDate==null || empty confirmDate}">
                                <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fconfirmDate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="confirm-date-label">HandOver Confirm Date</label></td>
                                <td class="style1"><input id="confirm-date" class="date-pick" type="text" name="confirmDate" value="${fconfirmDate}" /></td>
                            </tr>

                            
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
                $('#confirm-date').datepicker({dateFormat:datePattern});
            })
        );
        </script>
    </body>
</html>


