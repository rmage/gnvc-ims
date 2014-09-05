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
                        <caption>Document Summary Report Per Category</caption>
                        <tbody>
                            <tr>
                                <td style="width: 20%;">Document Type</td>
                                <td>
                                    <b>${model.docType}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">Item Category</td>
                                <td>
                                    <b>${model.productCat}</b>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 20%;">Date</td>
                                <td>
                                    <b>${model.dateAsOf}</b>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                            </tr>
                        </tfoot>
                    </table>

                    <table class="collapse tblForm row-select" style="border: 1px solid black">
                        <caption>Document Summary Detail</caption>
                        <tbody>
                            <tr>
                                <th style="text-align: center; border: 1px solid #0078ae; width: 15%">Item Code</th>
                                <th style="text-align: center; border: 1px solid #0078ae; width: 25%">Item Name</th>
                                <th style="text-align: center; border: 1px solid #0078ae; width: 25%">Item Unit</th>
                                <th style="text-align: center; border: 1px solid #0078ae; width: 35%">Amount</th>
                            </tr>
                            <c:forEach items="${model.nonFishDocumentSummaryList}" var="nfd" varStatus="i">
                                <tr>
                                    <td>
                                        ${nfd.product.productCode}
                                    </td>
                                    <td>
                                        ${nfd.product.productName}
                                    </td>
                                    <td>
                                        ${nfd.product.uom}
                                    </td>
                                    <td style="text-align: center">
                                        <fmt:formatNumber pattern="#,##0" value="${nfd.amountIDR}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr style="border: 1px solid #000000">
                                <td colspan="3" style="border: 1px solid #000000">
                                    TOTAL
                                </td>
                                <td style="text-align: center; border: 1px solid #000000">
                                    <fmt:formatNumber pattern="#,##0" value="${model.total}" />
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

        </script>

    </body>
</html>
