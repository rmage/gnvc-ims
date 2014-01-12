<%-- 
    Document   : rptJournalService
    Created on : Mar 2, 2012, 11:51:08 AM
    Author     : Icarust Burst
--%>

<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Journal Service</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>

        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>
            <%
            	String datePattern = com.app.wms.web.helper.DateHelper.DATE_PATTERN_DASH;
            %>
            <h1><h:outputText value="rptJournalService Form"/></h1>
            <div id="content" style="display: none" class="span-19 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Generate Journal Service</caption>
                        <form:form method="post" action="rptJournalService.htm" commandName="dto">
                            <tbody class="tbl-nohover">
                            <input type="hidden" name="templateName" value="rptJournalService" />
                            <input type="hidden" name="parametersKey" value="P_START_DATE,P_END_DATE"/>
                            
<%--                              <c:if test="${model.isALL}">
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
                            </c:if> --%>
                                
                          
                            <%--<fmt:formatDate value="${confirmDate}" pattern="<%=datePattern%>" var="fconfirmDate" scope="request"/>
                            <c:if test="${confirmDate==null || empty confirmDate}">
                                <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fconfirmDate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="confirm-date-label">HandOver Confirm Date</label></td>
                                <td class="style1"><input id="confirm-date" class="date-pick" type="text" name="confirmDate" value="${fconfirmDate}" /></td>
                            </tr>--%>

                            <fmt:formatDate value="${startDate}" pattern="<%=datePattern%>" var="fstartdate" scope="request"/>
                            <c:if test="${startDate==null || empty startDate}">
                            <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fstartdate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="start_date_label">Start Date</label></td>
                                <td class="style1"><input id="start-date" class="date-pick" type="text" name="startDate" value="${fstartdate}" /></td>
                            </tr>

                            <fmt:formatDate value="${endDate}" pattern="<%=datePattern%>" var="fenddate" scope="request"/>
                            <c:if test="${endDate==null || empty endDate}">
                                <fmt:formatDate value="<%= new Date()%>" pattern="<%=datePattern%>" var="fenddate" scope="request"/>
                            </c:if>
                            <tr><td class="style1"><label for="end-date-label">End Date</label></td>
                                <td class="style1"><input id="end-date" class="date-pick" type="text" name="endDate" value="${fenddate}" /></td>
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
            <%--var datePattern = 'dd-mm-yy';
            $(document).ready(
            $(function() {
                $('#confirm-date').datepicker({dateFormat:datePattern});
            })
        );--%>
            
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
