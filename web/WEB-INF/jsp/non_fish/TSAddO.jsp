<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Transfer Slip Others</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
                    <form action="TransferSlip.htm" id="tsForm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <input type="hidden" name="type"value="<%= request.getParameter("type") %>" />
                        <input type="hidden" name="module" value="<%= request.getParameter("module") %>" />
                        <table class="collapse tblForm row-select">
                            <caption>Header Others</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>TS Number</td>
                                    <td><input name="tsCode" pattern="[0-9]{1,}" type="text" required="true" /></td>
                                    <td>TS Date</td>
                                    <td><input id="tsDate" name="tsDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td><input id="tsTo" name="tsTo" size="40" type="text" /></td>
                                    <td>Remarks</td>
                                    <td><textarea name="tsInfo"></textarea></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('TransferSlip.htm?module=<%= request.getParameter("module") %>');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail Others</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select Item</td>
                                    <td colspan="6"><input id="itemCode" size="50" type="text" /></td>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Item Name</td>
                                    <td>Item Code</td>
                                    <td>Type</td>
                                    <td>Stock on Hand</td>
                                    <td>Quantity Out</td>
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
            $('#tsDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            $('.delete').live('click', function() { $(this).parent().parent().remove(); });
            
            $('#tsTo').autocomplete({
                source: '?action=getDepartment',
                minLength: 2,
                select: function( event, ui ) { $('#tsTo').val(ui.item.department); return false; }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.department + '</b></a></li>' )
                    .appendTo( ul );
            };
            
            $('#itemCode').autocomplete({
                source: '?action=getProduct&module=<%= request.getParameter("module") %>',
                minLength: 2,
                select: function( event, ui ) {
                    $('#main').append('<tr><td><input class="delete ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Delete" style="font-size: smaller;"></td><td>' + 
                        '<input name="item" type="hidden" value="' + ui.item.itemCode + '" />' + ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.type + '</td><td>' + numberWithCommas(ui.item.soh) + 
                        '</td><td><input name="qty" pattern="[0-9]{1,}" size="3" type="text" style="font-size: smaller;" required="true" /></td><td>' + ui.item.uom + '</td></tr>');
                    $('#main tr:last input[type="text"]').focus();
                    $('#itemCode').val(null);
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.itemCode + ' : ' + item.itemName + 
                    '</b><br /> Stock on Hand : ' + item.soh + '</a></li>' )
                    .appendTo( ul );
            };
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
