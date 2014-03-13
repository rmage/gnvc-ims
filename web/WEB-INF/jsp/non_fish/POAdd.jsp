<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Purchase Order</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
            .ui-datepicker { display: none; }
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
                    <form action="Purchase.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="search" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>PO Number</td>
                                    <td style="width: 500px;"><input id="poCode" name="poCode" type="text" pattern="[0-9]{1,}" required="true" /></td>
                                    <td>PO Date</td>
                                    <td><input id="poDate" name="poDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>Supplier</td>
                                    <td>
                                        <input id="sSupplier" size="50" type="text" required="true" />
                                        <input id="sSupplierCode" type="hidden" required="true" />
                                    </td>
                                    <td>Discount</td>
                                    <td><input id="discount" name="discount" size="1" type="text" value="0" pattern="[0-9]{1,2}" required="true" /> %</td>
                                </tr>
                                <tr>
                                    <td>PPH</td>
                                    <td><input id="pph" name="pph" size="1" type="text" value="0" pattern="[0-9]{1,2}" required="true" /> %</td>
                                    <td>PPN</td>
                                    <td><input id="ppn" name="ppn" size="1" type="text" value="0" pattern="[0-9]{1,2}" required="true" /> %</td>
                                </tr>
                                <tr>
                                    <td>Currency</td>
                                    <td>
                                        <select id="currency" name="currency">
                                            <c:forEach items="${model.c}" var="x">
                                            <option>${x.currencyCode}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>Remarks</td>
                                    <td>
                                        <select id="confirmatory">
                                            <option value="0">Normal</option>
                                            <option value="1">Confirmatory</option>
                                        </select>
                                        <input id="remarks" name="remarks" size="50" type="text" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input id="save" type="button" value="Save" />
                                        <input type="submit" value="Search" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Purchase.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Pick Item to Generate Purchase Order</caption>
                            <thead>
                                <tr>
                                    <td>Action</td>
                                    <td>PRS Number</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Department</td>
                                    <td>Quantity</td>
                                    <td>Unit</td>
                                    <td>Unit/Price</td>
                                    <td>Amount</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
                        </table>
                    </form>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            
            /* BIND | element event */
            $('#save').bind('click', function() {
                var f = 0;
                $('#main tr').each(function(i) {
                    if($(this).find('input[type="checkbox"]')[0].checked) {
                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('td:eq(1)').html() + 
                            ':' + $(this).find('td:eq(2)').html() + ':' + $(this).find('td:eq(4)').html() + 
                            ':' + $(this).find('td:eq(8)').html().replace(/,/g, '') + ':' + i + '" />'); f = 1;
                    }
                });
                
                if(f === 1) {
                    $('#poster').append('<input name="master" type="hidden" value="' + $('#poCode').val() + 
                        ':' + $('#poDate').val() + ':' + $('#sSupplierCode').val() + ':' + $('#discount').val() + 
                        ':' + $('#pph').val() + ':' + $('#ppn').val()+ ':' + $('#currency').val() + ':' + $('#remarks').val() + '" />');
                    $('#poster').submit();
                }
            });
            
            $('#search').bind('submit', function(e) {
                var $o = $(this).find('input[type="submit"]');
                $o.attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                
                /* get detail item */
                $.ajax({
                    url: 'Purchase.htm',
                    data: {action: 'getItems', key: $('#sSupplierCode').val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for(var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td><input title="Pick this item" type="checkbox" /></td><td>' + json[i].prsNumber + 
                                '</td><td>' + json[i].itemCode + '</td><td>' + json[i].itemName + 
                                '</td><td>' + json[i].departmentCode + '</td><td>' + numberWithCommas(json[i].qty) + 
                                '</td><td>' + json[i].unit + '</td><td>' + numberWithCommas(json[i].price) +
                                '</td><td>' + numberWithCommas(json[i].amount) + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                    }
                });
                
                return false;
            });
            
            $('input[type="checkbox"]').live('change', function() {
                if($(this)[0].checked) {
                    $(this).parent().parent().addClass("bold");
                } else {
                    $(this).parent().parent().removeClass("bold");
                }
            });
            
            var supplierList = [
                <c:forEach items="${model.supplier}" var="x">
                    {
                        label: '${x.supplierName}',
                        code: '${x.supplierCode}'
                    },
                </c:forEach>
            ];
            $('#sSupplier').bind('focus', function() {
                $(this).val(null);
                $('#sSupplierCode').val(null);
                $('#main').html(null);
            });
            $('#sSupplier').autocomplete({ 
                source: supplierList,
                select: function( event, ui ) {
                    $('#sSupplier').val( ui.item.label );
                    $('#sSupplierCode').val( ui.item.code );
                    $('#sSupplier').blur();
                    return false;
                }
            });
            
            $('#poDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            
            $('#confirmatory').bind('change', function() {
                var $r = $('#remarks');
                if($(this).val() === '1') {
                    if($r.val().indexOf('CONFIRMATORY') < 0)
                        $r.val('CONFIRMATORY; ' + $r.val());
                } else {
                    $r.val($r.val().replace(/CONFIRMATORY; /gi, ''));
                }
            });
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
            
        </script>
    </body>
</html>
