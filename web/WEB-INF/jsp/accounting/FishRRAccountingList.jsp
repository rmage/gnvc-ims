<%-- 
    Document   : FishRRAccountingList
    Created on : Sep 16, 2014, 12:29:10 PM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish RR Accounting</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishRRAccounting.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Receiving &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">RR Number</td>
                                    <td><input type="text" name="rr_no" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Fish RR Update Price - List</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action </td>
                                <td>RR Number</td>
                                <td>RR Date</td>
                                <td>Updated By</td>
                                <td>Updated Date</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>

            <script>

                util.initSearchForm($('#search'));
                util.initListTable($('#list'), 'u:l');
            </script>
    </body>
</html>
