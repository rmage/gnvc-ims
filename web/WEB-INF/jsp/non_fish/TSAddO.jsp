<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create &therefore; Transfer Slip &therefore; Others &therefore; IMS</title>
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
                    <form action="TransferSlip.htm" id="tsForm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <input type="hidden" name="type"value="<%= request.getParameter("type") %>" />
                        <input type="hidden" name="module" value="<%= request.getParameter("module") %>" />
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip &therefore; Others &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>TS Number</td>
                                    <td><input id="tsCode" name="tsCode" pattern="[0-9]{1,}" type="text" required></td>
                                    <td>TS Date</td>
                                    <td><input id="tsDate" name="tsDate" size="10" type="text" required></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td><input id="tsTo" name="tsTo" size="40" type="text" required></td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="tsInfo" name="tsInfo" size="50"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>For Production ?</td>
                                    <td>
                                        <select id="is_production" name="is_production">
                                            <option value="">No</option>
                                            <option value="PRODUCTION">Yes</option>
                                        </select>
                                        <small>(if select "yes" this Transfer Slip will be counted on iCore Template - Consumption report)</small>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('TransferSlip.htm?module=<%= request.getParameter("module") %>');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip &therefore; Others &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select Item</td>
                                    <td colspan="6">
                                        <select id="mode" name="mode">
                                            <option value="code">By Code</option>
                                            <option value="name">By Name</option>
                                        </select>
                                        <input id="itemCode" size="50" type="text" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Action</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
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
            
            $("#mode").bind("change", function() {
                $('#itemCode').autocomplete("option", "source", "?action=getProduct&mode=" + $(this).val());
            });
            
            $('#itemCode').autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function( event, ui ) {
                    $('#main').append('<tr><td><input class="delete ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Delete" style="font-size: smaller;"></td><td>' + 
                        '<input name="item" type="hidden" value="' + ui.item.itemCode + '" />' + ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.type + '</td><td>' + numberWithCommas(ui.item.soh) + 
                        '</td><td><input name="qty" pattern="[0-9]{1,}" size="10" type="text" style="font-size: smaller;" required /></td><td>' + ui.item.uom + '</td></tr>');
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
            
            // VALIDATE | Minimum 1 (one) quantity
            $("#tsForm").bind("submit", function() {
                var data = '';
                var header = $('#tsCode').val() + ':s:' + gnvs.util.toDBDate($('#tsDate').val()) + ':s:' + $('#tsInfo').val() + ':s:' + $('#tsTo').val() + ':s:' + 'NF:s:OTHERS:s:';

                $('#main tr').each(function() {
                    if (!isNaN(parseFloat($(this).find('input:eq(2)').val())) && parseFloat($(this).find('input:eq(2)').val()) > 0) {
                        data = data + header + $(this).find('input:eq(1)').val() + ':s:' + $(this).find('input:eq(2)').val() + ':s:' + $('#is_production').val() + ':s::se:';
                    }
                });

                if (data !== '' && confirm('Save Transfer #' + $('#tsCode').val() + ' ?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSaveO', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;   
            });
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
