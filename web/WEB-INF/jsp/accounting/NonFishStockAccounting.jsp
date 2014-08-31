<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
                            <caption>Stock Card Accounting &therefore; Non-Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Item Code</td>
                                    <td>
                                        <input type="text" id="productcode" name="productcode" value="" size="30" required="true"/> 
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Items" id="imgSearchProductCode"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">Item Name</td>
                                    <td>
                                        <input type="text" id="productname" name="productname" value="" size="30" required="true"/> 
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Items" id="imgSearchProductName"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td><input id="asOfDate" type="date" name="date" value="<%=cDateH%>" readonly="true"/></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="button" value="Generate Report" name="btnGenerate" id="btnGenerate"/>
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
            $(function() {

                $('#btnGenerate').click(function() {
                    var productCode = document.getElementById("productcode").value;
                    var asOf = document.getElementById("asOfDate").value;
                    if (productCode == "") {
                        alert("Please select product code or product name");
                    } else {
                        location.href = 'NonFishStockAccounting.htm?action=create&productcode=' + productCode + '&asOf=' + asOf;
                    }
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
