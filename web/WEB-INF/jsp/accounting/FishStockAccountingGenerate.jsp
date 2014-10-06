<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Inventory</title>
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
                        <input type="hidden" name="item" value="FishStockCard" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" name="params" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card &therefore; Frozen Fish Stock In Cold Storage</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Supplier</td>
                                    <td>
                                        ${model.fishSupplier.code} - ${model.fishSupplier.name}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td>
                                        ${model.asOfDate}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">Curency Type</td>
                                    <td>
                                        ${model.currencyType}
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" style="border: 1px solid black">
                        <caption>Stock Card Fish Accounting Detail</caption>
                        <tbody >
                            <tr>
                                <th colspan="2" rowspan="2" style="text-align: center; border: 1px solid #0078ae">DESCRIPTION</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">BEGINNING BALANCE</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">RECEIPTS</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">WITHDRAWAL</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">ENDING BALANCE</th>
                            </tr>
                            <tr>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost (USD)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount (USD)</th>
                            </tr>
                            <c:forEach items="${model.fishStockCardAccountingList}" var="fishStockCardAccounting" varStatus="i">
                                <tr>
                                    <td colspan="2">
                                        ${fishStockCardAccounting.fish.code}
                                    </td>
                                    <td style="text-align: center;">
                                        ${fishStockCardAccounting.begQuantity}
                                    </td>
                                    <td style="text-align: center;">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.begUnitCost}" /> 
                                    </td>
                                    <td style="text-align: center; font-weight: bold">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.begAmount}" /> 
                                    </td>
                                    <td style="text-align: center;">
                                        ${fishStockCardAccounting.receiveQuantity}
                                    </td>
                                    <td style="text-align: center;">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.receiveUnitCost}" />
                                    </td>
                                    <td style="text-align: center; font-weight: bold">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.receiveAmount}" /> 
                                    </td>
                                    <td style="text-align: center;">
                                        ${fishStockCardAccounting.withdrawalQuantity}
                                    </td>
                                    <td style="text-align: center;">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.withdrawalUnitCost}" />
                                    </td>
                                    <td style="text-align: center; font-weight: bold">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.withdrawalAmount}" /> 
                                    </td>
                                    <td style="text-align: center;">
                                        ${fishStockCardAccounting.endQuantity}
                                    </td>
                                    <td style="text-align: center;">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.endUnitCost}" />
                                    </td>
                                    <td style="text-align: center; font-weight: bold">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${fishStockCardAccounting.endAmount}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2" style="text-align: left; font-weight: bold" >
                                    GRAND TOTAL
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    ${model.begTotalQty}
                                </td>
                                <td>
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${model.begTotalAmount}" />
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    ${model.inTotalQty}
                                </td>
                                <td>
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${model.inTotalAmount}" />
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    ${model.outTotalQty}
                                </td>
                                <td>
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${model.outTotalAmount}" />
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    ${model.endTotalQty}
                                </td>
                                <td>
                                </td>
                                <td style="text-align: center; font-weight: bold" >
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${model.endTotalAmount}" />
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

        <script type="text/javascript">
            $(function() {
                $('#btnGenerate').click(function() {
                    var elem = document.getElementById("dropdownFishCode");
                    elem.value = $('#dropdownFishCode').val();
                    var str = elem.value;
                    str = str.replace(/_/g, ' ');
                    location.href = 'FishStockAccounting.htm?action=create&fishcode=' + str;
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            function currCodeSelected(index) {
                var str = $("#groupCurrencyCode-" + index + " option:selected").index();
                document.getElementById('rateIDR-' + index).innerHTML = str;
            }

            function calulateAmountIDR(index) {

                var idrRate = $("#rateIDR-" + index).text();
                var idamount = '#amount-' + index;
                var amount = document.getElementById(idamount).value;

                alert(idamount);
                alert("IDRRATE " + idrRate * amount + " " + idamount);
                var total = idrRate * amount;
                document.getElementById('amountIDR-' + index).innerHTML = total;
            }

        </script>

    </body>
</html>
