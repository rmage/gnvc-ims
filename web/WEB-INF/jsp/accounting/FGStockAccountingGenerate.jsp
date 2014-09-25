<%-- 
    Document   : FGStockAccounting
    Created on : Sep 23, 2014, 9:38:22 AM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <input type="hidden" name="" value="" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card Accounting &therefore; Finished Goods</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Pack Style</td>
                                    <td>
                                        ${model.fgPs.packStyle} - ${model.fgPs.packSize}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td>
                                        ${model.asOfDate}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">Currency Rate</td>
                                    <td>
                                        <b> ${model.fromTo} : <fmt:formatNumber type="number" maxFractionDigits="8" value="${model.cr.rateValue}" /></b>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" style="border: 1px solid black">
                        <caption>Stock Card FG Accounting Detail</caption>
                        <tbody >
                            <tr>
                                <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Item Name</th>
                                <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Quantity</th>
                                <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">#fcls</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">Cost in USD</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">Cost in IDR</th>
                            </tr>
                            <tr>
                                <th style="text-align: center; border: 1px solid #0078ae">VC / Case</th>
                                <th style="text-align: center; border: 1px solid #0078ae">FC / Case</th>
                                <th style="text-align: center; border: 1px solid #0078ae">TC / Case</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Variable Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Fixed Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Total Cost</th>
                            </tr>
                            <c:forEach items="${model.fgStockCardAccountingList}" var="fgStockCardAccounting" varStatus="i">
                                <tr>
                                    <td>
                                        ${fgStockCardAccounting.itemName}
                                    </td>
                                    <td style="text-align: center">
                                        ${fgStockCardAccounting.endQuantity}
                                    </td>
                                    <td>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.varCost}" /></b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.fixCost}" /> </b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.totalCost}" /> </b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.amountVarCost}" /> </b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.amountFixCost}" /> </b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${fgStockCardAccounting.amountTotalCost}" /> </b>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: center">
                                    <b>TOTAL</b>
                                </td>
                                <td style="text-align: center">
                                    ${model.totalQty}
                                </td>
                                <td>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalVarUSD}" /></b>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalFixUSD}" /></b>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalTotalUSD}" /></b>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalVarIDR}" /></b>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalFixIDR}" /></b>
                                </td>
                                <td style="text-align: center">
                                    <b><fmt:formatNumber type="number" maxFractionDigits="2" value="${model.totalTotalIDR}" /></b>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
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
