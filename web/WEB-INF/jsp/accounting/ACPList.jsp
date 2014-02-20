<%@page import="java.util.Date"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting :: Price Listing</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Item Pricing</caption>
                        <tbody>
                            <tr>
                                <td style="width: 200px;">Item Category</td>
                                <td>
                                    <select id="itemType">
                                        <option value="">-- select item category --</option>
                                        <c:forEach items="${model.pc}" var="x">
                                            <option value="${x.categoryCode}">${x.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <input type="button" value="Search Item" name="btnSearch" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <table class="collapse tblForm row-select">
                        <caption>Item List</caption>
                        <thead>
                            <tr>
                                <td>Item Code</td>
                                <td>Item Name</td>
                                <td>Item Type</td>
                                <td>Last RR</td>
                                <td>Last TS</td>
                                <td>Previous Price</td>
                                <td>Adjusted Price</td>
                                <td>Status</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                    </table>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script>
           
        </script>
    </body>
</html>
