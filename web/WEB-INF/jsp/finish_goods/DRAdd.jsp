<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Delivery Receipt</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                    <form action="Delivery.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                        <input name="type" type="hidden" value="${model.type}" />
                    </form>
                    <form action="#" id="drForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>DR Number</td>
                                    <td>: <input id="drCode" type="text" required="true" /></td>
                                    <td>DR Date</td>
                                    <td>: <input id="drDate" size="10" type="text" required="true" /></td>
                                    <td>Remarks</td>
                                    <td style="width: 400px;"><textarea id="drRemarks"></textarea></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td>: <input id="drFrom" size="40" type="text" value="IM - PT. SPFI" required="true" /></td>
                                    <td>Location</td>
                                    <td>: <input id="drFromLoc" type="text" /></td>
                                    <td>OR No.</td>
                                    <td> <input id="orCode" type="text" /></td>
                                </tr>
                                
                                <tr>
                                    <td>To</td>
                                    <td>: <input id="drTo" size="40" type="text" required="true" /></td>
                                    <td>Location</td>
                                    <td>: <input id="drToLoc" type="text" /></td>
                                    <td>DM No.</td>
                                    <td> <input id="dmCode" type="text" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Delivery.htm?type=${model.type}');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <th colspan="6">Please Type Item Name : <input id="itemName" size="40" type="text" /></th>
                                </tr>
                                <tr>
                                    <td style="width: 100px;">Action</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Stock</td>
                                    <td>Quantity</td>
                                    <td>Uom</td>
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
            $('#drDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            
            $('#itemName').autocomplete({
                delay: 1000,
                source: '?action=getProduct&type=<%= request.getParameter("type") %>',
                minLength: 2,
                select: function( event, ui ) {
                    $('#main').append('<tr><td><input class="btnDel ui-button ui-widget ui-state-default ui-corner-all" style="font-size: smaller;" type="button" value="Remove This" /></td>' +
                        '<td>' + ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.soh + '</td><td><input type="text" value="0" required="true" /></td>' +
                        '<td>' + ui.item.uom + '</td></tr>');
                    $('#main > tr:last-child').find('input').focus();
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.itemCode + ' : ' + item.itemName + ' (' + item.type + ')' +
                    '</b><br /> Stock : ' + item.soh + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('#drTo').autocomplete({ 
                source: '?action=<%= request.getParameter("type").equals("NF") ? "getSupplier" : "getDistributor" %>',
                minLength: 2,
                select: function( event, ui ) {
                    $('#drTo').val(ui.item.name);
                    $('#drTo').data('code', ui.item.code);
                    return false;
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.code + ' : ' + item.name +
                    '</b><br /> Address : ' + item.address + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('#drForm').bind('submit', function() {
                if($('#main tr').length) {
                    $('#poster').append('<input name="master" type="hidden" value="' + $('#drCode').val() + ':' + $('#drDate').val() + ':' +
                        $('#drFrom').val() + ':' + $('#drFromLoc').val() + ':' + $('#drToLoc').val() + ':' + $('#drRemarks').val() + ':' +
                        $('#drTo').data('code') + ':' + $('#orCode').val() + ':' + $('#dmCode').val() + '" />');
                    
                    var i = 0;
                    $('#main tr').each(function() {
                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('input[type="text"]').val() + ':' + 
                            $(this).find('td:eq(5)').html() + ':' + $(this).find('td:eq(1)').html() + ':' + i + '" />'); i++;
                    });
                    
                    $('#poster').submit();
                }
                
                return false;
            });

            $('.btnDel').live('click', function() { $(this).parent().parent().remove(); });

        </script>
    </body>
</html>
