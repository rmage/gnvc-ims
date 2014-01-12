<%-- 
    Document   : rptCoaMapping
    Created on : Sep 7, 2010, 7:07:55 PM
    Author     : zyrex
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <title>Report Coa</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <h1><h:outputText value="rptCoaMapping Form"/></h1>

            <div id="content" style="display: none" class="span-19 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Generate Report Coa</caption>
                        <form:form method="post" action="rptCoaMapping.htm" commandName="dto">
                            <tbody class="tbl-nohover">
                                <input type="hidden" name="templateName" value="rptCoaMapping" />

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
    </body>
</html>

            
