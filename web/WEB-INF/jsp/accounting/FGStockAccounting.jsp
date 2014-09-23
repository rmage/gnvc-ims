<%-- 
    Document   : FGStockAccounting
    Created on : Sep 23, 2014, 9:38:22 AM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Stock Accounting</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FGStockAccounting.htm" id="search" method="post">
                        <input type="hidden" name="action" value="generate" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card Accounting &therefore; Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Pack Style</td>
                                    <td>
                                        <select name="dropdownPackStyle" id="dropdownPackStyle" required="true" >
                                            <c:if test="${model.fgPackStyles!=null}">
                                                <c:forEach items="${model.fgPackStyles}" var="fgPackStyle" varStatus="i">
                                                    <option value=<c:out value="${i.index}"/> >
                                                        <c:out value="${fgPackStyle.packSize} - ${fgPackStyle.packStyle}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td><input id="asOfDate" type="date" name="asOfDate" value="" readonly="true" required="true"/></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate FG Accounting" name="btnGenerate" id="btnGenerate"/>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script>
            $('#asOfDate').datepicker({
                dateFormat: "dd/mm/yy"
            });
        </script>
    </body>
</html>
