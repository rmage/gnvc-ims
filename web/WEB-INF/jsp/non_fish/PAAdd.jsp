<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Price Assignment</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
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
                    <form action="PriceAssignment.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="search" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Supplier</td>
                                    <td>
                                        <input id="sSupplier" size="50" type="text" required="true" />
                                        <input id="sSupplierCode" type="hidden" required="true" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input id="save" type="button" value="Save" />
                                        <input type="submit" value="Search" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('PriceAssignment.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Assignment</caption>
                            <thead>
                                <tr>
                                    <td>PRS Number</td>
                                    <td>Assign Date</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Quantity</td>
                                    <td>Supplier Code</td>
                                    <td>Supplier Name</td>
                                    <td>Unit Price</td>
                                    <td>Term of Payments</td>
                                    <td>Term of Delivery</td>
                                    <td>Warranty Period</td>
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
                var b = false;
                $('#main tr').each(function() {
                    if($(this).find('input[name="price"]').val() !== '') {
                        b = true;
                        $('#poster').append($(this).find('input, select'));
                    }
                });
                
                if(b) $('#poster').submit();
            });
            
            $('#search').bind('submit', function(e) {
                var $o = $(this).find('input[type="submit"]');
                $o.attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                
                /* get detail item */
                $.ajax({
                    url: 'PriceAssignment.htm',
                    data: {action: 'getSupplier', key: $('#sSupplierCode').val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for(var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + json[i].prsNo + '</td><td>' + json[i].assignDate + '</td><td>' + json[i].itemCode + '</td><td>' + json[i].itemName + '</td><td>' + json[i].quantity + '</td><td>' + json[i].supplierCode + 
                                '<input id="selected" name="selected" type="hidden" value="off" />' +
                                '</td><td>' + json[i].supplierName + '</td><td><input name="selected" title="Selected Supplier" type="checkbox" /> <input name="price" size="10" type="text" /></td><td><select name="top"><option>Cash</option><option>Credit</option></select> ' + 
                                '<input name="topDesc" type="text" /></td><td><input name="tod" type="text" /></td><td><input class="wp" name="wp" size="8" type="text" /><input type="hidden" name="prsNumber" value="' + json[i].prsNo + 
                                '" /><input type="hidden" name="itemCode" value="' + json[i].itemCode + '" /><input type="hidden" name="supplierCode" value="' + json[i].supplierCode +'" /></td></tr>');
                        
                            setDatePicker('wp');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        
                        if($('#main').html().trim() === '')
                            $('#search')[0].reset();
                    }
                });
                
                return false;
            });
            
            $('input[type="checkbox"]').live('click', function() {
                if($(this)[0].checked) {
                    $(this).parent().parent().find('#selected').remove();
                } else {
                    $(this).parent().append('<input id="selected" name="selected" type="hidden" value="off" />');
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
            
            function setDatePicker(s) { $('.' + s).datepicker({ dateFormat: "dd/mm/yy" }); }
            
        </script>
    </body>
</html>