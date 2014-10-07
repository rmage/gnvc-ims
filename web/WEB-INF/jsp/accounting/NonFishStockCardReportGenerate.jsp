<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Non - Fish Stock Accounting</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="" id="search" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="NonFishStockCard" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" name="params" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card Report per Category &therefore; Non-Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Item Category</td>
                                    <td>
                                        <b>${model.productCat}</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">Currency Type</td>
                                    <td>
                                        <b>${model.cri.currencyType}</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td><b>${model.dateAsOf}</b></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                </tr>
                            </tfoot>
                        </table>
                    </form>

                    <table class="collapse tblForm row-select" style="border: 1px solid black">
                        <caption>Transaction Report (Purchase and Issuance)</caption>
                        <tbody>
                            <tr>
                                <th style="text-align: center; border: 1px solid #0078ae">Item Code</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Item Description</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Quantity</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount to Date</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Beginning Amount</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Transaction Amount</th>
                            </tr>
                            <c:forEach items="${model.nonFishStockCardReportList}" var="nonFishStockCardReport" varStatus="i">
                                <tr>
                                    <td>
                                        ${nonFishStockCardReport.product.productCode}
                                    </td>
                                    <td>
                                        ${nonFishStockCardReport.product.productName}
                                    </td>
                                    <td>
                                        ${nonFishStockCardReport.product.uom}
                                    </td>
                                    <td>
                                        ${nonFishStockCardReport.quantity}
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber pattern="#,##0" value="${nonFishStockCardReport.unitCost}" /></b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber pattern="#,##0" value="${nonFishStockCardReport.amountToDate}" /></b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber pattern="#,##0" value="${nonFishStockCardReport.beginningAmount}" /></b>
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber pattern="#,##0" value="${nonFishStockCardReport.transactionAmount}" /></b>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td style="text-align: center;  border: 1px solid #0078ae" colspan="3">
                                    <b>GRAND TOTAL</b>
                                </td>
                                <td style="text-align: center;  border: 1px solid #0078ae" >
                                    <b>${model.totalQuantity}</b>
                                </td>
                                <td></td>
                                <td style="text-align: center;  border: 1px solid #0078ae" >
                                    <b><fmt:formatNumber pattern="#,##0" value="${model.totalAmountToDate}" /></b>
                                </td>
                                <td></td>
                                <td style="text-align: center;  border: 1px solid #0078ae">
                                    <b><fmt:formatNumber pattern="#,##0" value="${model.totalTransactionAmount}" /></b>
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
            $(function() {

                $('#btnGenerate').click(function() {
                    var e = document.getElementById("ddlProductCategory");
                    var cat = e.options[e.selectedIndex].value;
                    var asOf = document.getElementById("asOfDate").value;

                    location.href = 'NonFishStockCardReport.htm?action=create&productcat=' + cat + '&asOf=' + asOf;
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            $('#asOfDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "yy-mm-dd", altField: "#params", altFormat: "yy-mm-dd"});

        </script>

    </body>
</html>
