<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Stock Accounting</title>
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
                    <input type="hidden" name="action" value="index" />
                    <input type="hidden" name="item" value="NonFishStockCard" />
                    <input type="hidden" name="type" value="xls" />
                    <input type="hidden" name="params" value="<%=cDateH%>" />
                    <table class="collapse tblForm row-select">
                        <caption>Transaction Report (Purchase and Issuance)</caption>
                        <tbody>
                            <tr>
                                <td style="width: 20%;">Item Code</td>
                                <td>
                                    <b>${model.product.productCode}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">Item Name</td>
                                <td>
                                    <b>${model.product.productName}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">Unit</td>
                                <td>
                                    <b>${model.product.uom}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">Item Category</td>
                                <td>
                                    <b>${model.productCat}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">As Of</td>
                                <td>
                                    <b>${model.asOf}</b>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                            </tr>
                        </tfoot>
                    </table>

                    <table class="collapse tblForm row-select">
                        <caption>Stock On Hand &therefore; ${model.product.productName}</caption>
                        <tbody>
                            <tr>
                                <td style="width: 20%;">Date </td>
                                <td><b>${model.lastDateOfPreviousMonth}</b></td>
                            </tr>
                            <tr>
                                <td style="width: 20%;" rowspan="2">Beginning Balance </td>
                                <td> Quantity : <b> <fmt:formatNumber pattern="#,##0" value="${model.lastMonthQTY}" /> </b></td>
                            </tr>
                            <tr>
                                <td> Amount (IDR) : <b> <fmt:formatNumber pattern="#,##0" value="${model.lastMonthBalance}" /> </b></td>
                            </tr>
                        </tbody>

                    </table>
                    <table class="collapse tblForm row-select" style="border: 1px solid black">
                        <caption>Transaction Report (Purchase and Issuance)</caption>
                        <tbody>
                            <tr>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">DOCUMENT</th>
                                <th colspan="4" style="text-align: center; border: 1px solid #0078ae">UNIT COST</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">IN</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">OUT</th>
                                <th colspan="3" style="text-align: center; border: 1px solid #0078ae">ENDING BALANCE</th>
                            </tr>
                            <tr>
                                <th style="text-align: center; border: 1px solid #0078ae">Date</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Code</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Number</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Currency</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Rate</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount (IDR)</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount</th>
                                <th style="text-align: center; border: 1px solid #0078ae">QTY</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Unit Cost</th>
                                <th style="text-align: center; border: 1px solid #0078ae">Amount</th>
                            </tr>
                            <c:forEach items="${model.nFStockCardList}" var="nFStockCard" varStatus="i">
                                <tr>
                                    <td>
                                        ${nFStockCard.stockCardDate}
                                    </td>
                                    <td>
                                        ${nFStockCard.code}
                                    </td>
                                    <td>
                                        ${nFStockCard.number}
                                    </td>
                                    <td style="text-align: center">
                                        ${nFStockCard.currencyCode}
                                    </td>
                                    <td>
                                        <fmt:formatNumber pattern="#,##0" value="${nFStockCard.amount}" />
                                    </td>
                                    <td>
                                        <fmt:formatNumber pattern="#,##0" value="${nFStockCard.rateValue}" />
                                    </td>
                                    <td style="text-align: center">
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.amountIDR}" /></b>
                                    </td>
                                    <!--IN-->
                                    <td>
                                        ${nFStockCard.quantityIN}
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.unitCostIN}" /></b>
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.amountIN}" /></b>
                                    </td>
                                    <!--OUT-->
                                    <td>
                                        ${nFStockCard.quantityOUT}
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.unitCostOUT}" /></b>
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.amountOUT}" /></b>
                                    </td>
                                    <!--ENDING BALANCE-->
                                    <td>
                                        ${nFStockCard.quantityEND}
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.unitCostEND}" /></b>
                                    </td>
                                    <td>
                                        <b><fmt:formatNumber pattern="#,##0" value="${nFStockCard.amountEND}" /></b>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr style="border: 1px solid #000000">
                                <td colspan="7" style="border: 1px solid #000000">
                                    TOTAL
                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    ${model.totalQTYIN}
                                </td>
                                <td>

                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    <b><fmt:formatNumber pattern="#,##0" value="${model.totalAmountIN}" /></b>
                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    ${model.totalQTYOUT}
                                </td>
                                <td>

                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    <b><fmt:formatNumber pattern="#,##0" value="${model.totalAmountOUT}" /></b>
                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    ${model.totalQTYEND}
                                </td>
                                <td>

                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    <b><fmt:formatNumber pattern="#,##0" value="${model.totalAmountEND}" /></b>
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
                    var productCode = document.getElementById("productcode").value;
                    var asOf = document.getElementById("asOfDate").value;
                    location.href = 'NonFishStockAccounting.htm?action=create&productcode=' + productCode + '&asOf=' + asOf;
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            $('#asOfDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "yy-mm-dd", altField: "#params", altFormat: "yy-mm-dd"});

            //  AUTOCOMPLETE | Find product by code
            $('#productcode').autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(event, ui) {
                    $("#productcode").val(ui.item.itemCode);
                    $("#productname").val(ui.item.itemName);
                    $("#uomName").val(ui.item.uom);
                    $("#stockonhand").val(ui.item.soh);
                    $('#jumlah').focus();

                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName +
                                '</b><br /> Stock : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };

            //  AUTOCOMPLETE | Find product by name
            $('#productname').autocomplete({
                source: '?action=getProduct&mode=name',
                minLength: 2,
                select: function(event, ui) {
                    $("#productcode").val(ui.item.itemCode);
                    $("#productname").val(ui.item.itemName);
                    $("#uomName").val(ui.item.uom);
                    $("#stockonhand").val(ui.item.soh);
                    $('#jumlah').focus();

                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName +
                                '</b><br /> Stock : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };

        </script>

    </body>
</html>
