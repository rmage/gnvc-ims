<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Purchase Order</title>
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
                    <form action="Purchase.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PO Number</td>
                                    <td><input type="text" name="po_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Purchase.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="po_code">PO Number</td>
                                <td column="po_date">Po Date</td>
                                <td>Supplier Code</td>
                                <td>Supplier Name</td>
                                <td>Supplier Phone</td>
                                <td>Grand Total</td>
                                <td>Approval</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
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
            $('.d').live('click', function() {
                fyaQPanel($(this).html());
            });

            function fyaQPanel(p) {
                if (p === 0) {
                    $('#fyaQPanel').fadeOut(300, function() {
                        $('#fyaQPanel').html(null);
                    });
                } else {
                    $('#fyaQPanel').fadeIn(300, function() {
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'Purchase.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>PO Number : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>Item Name</th><th>Item Code</th><th>Department</th>' +
                                        '<th>Quantity</th><th>Unit</th><th>Unit/Price</th><th>Amount</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for (var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemName + '</td><td>' + json[i].itemCode + '</td><td>' +
                                            json[i].department + '</td><td>' + numberWithCommas(json[i].qty) + '</td><td>' + json[i].uom + '</td><td>' +
                                            numberWithCommas(json[i].price) + '</td><td>' + numberWithCommas(json[i].amount) + '</td></tr>');
                                }
                            },
                            complete: function() {
                                if ($('#fyaQPanel').html() === '')
                                    fyaQPanel(0);
                            }
                        });
                    });
                }
            }

            $('.box > table > tbody tr').each(function() {
                var $o = $(this).find('td:eq(7)');
                $o.html(numberWithCommas($o.html()));
            });

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_PRCPo_Print Purchase (xls)_3-1*R_CanvassingHistory_Canvassing History (xls)');
        </script>
    </body>
</html>
