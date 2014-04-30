<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Pallet Transfer Slip</title>
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
                    <form action="Pts.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="ptsForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>PTS Number</td>
                                    <td>
                                        <input id="ptsCode" pattern="[0-9]{1,}" type="text" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                    <td>PTS Date</td>
                                    <td>
                                        <input id="ptsDate" size="10" type="text" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>For Brand</td>
                                    <td>
                                        <input id="brandName" size="50" type="text" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                    <td>Can Code</td>
                                    <td>
                                        <input id="canCode" type="text" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                    <td>Reff</td>
                                    <td><input id="reff" type="text" /></td>
                                </tr>
                                <tr>
                                    <td>Item Code</td>
                                    <td id="itemCode"></td>
                                    <td>Item Name</td>
                                    <td id="itemName"></td>
                                    <td>Pack Style/Size</td>
                                    <td id="pack"></td>
                                </tr>
                                <tr>
                                    <td colspan="2" rowspan="2"><big><b>CUT OUT EVALUATION</b></big></td>
                                    <td>%Flk</td>
                                    <td><input id="coeFlk" type="text" value="0" required="true" /></td>
                                    <td>NW</td>
                                    <td><input id="coeNw" type="text" value="0" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>DW</td>
                                    <td><input id="coeDw" type="text" value="0" required="true" /></td>
                                    <td>PW</td>
                                    <td><input id="coePw" type="text" value="0" /></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><big><b>QA DISPOSITION</b></big></td>
                                    <td>Release to</td>
                                    <td><input id="qadReleaseTo" type="text" /></td>
                                    <td>Remarks</td>
                                    <td><input id="qadRemarks" type="text" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Pts.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="6" style="text-align: right;"><input id="btnAdd" type="button" value="Add Detail Product" /></td>
                                </tr>
                                <tr>
                                    <td>NS/DS</td>
                                    <td>Date</td>
                                    <td>Production Batch</td>
                                    <td>Basket</td>
                                    <td>Quantity</td>
                                    <td>Action</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
                            <tfoot>
                                <tr>
                                    <td><big><b>Total</b></big></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><big><b id="quantity">0.00</b></big></td>
                                    <td>
                                        <input id="cs" size="3" type="text" > Cs
                                        <span style="float: right;">Location : <input id="location" size="5" type="text" /></span>
                                    </td>
                                </tr>
                            </tfoot>
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
            $('#ptsDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            
            $('#brandName').bind('focus', function() {
                $(this).val(null); $('#itemCode').html(null); $('#itemName').html(null); $('#pack').html(null);
            });
            
            $('#brandName').autocomplete({
                source: '?action=getBrandName',
                minLength: 2,
                select: function( event, ui ) {
                    $('#brandName').val(ui.item.brand);
                    $('#itemCode').html(ui.item.itemCode);
                    $('#itemName').html(ui.item.itemName);
                    $('#pack').html(ui.item.style + ' / ' + ui.item.size);
                    $('#brandName').autocomplete("destroy");
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.itemCode + ' : ' + item.itemName + 
                    '</b><br /> For Brand : ' + item.brand + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('#ptsForm').bind('submit', function() {
                if($('#main tr').length) {
                    $('#poster').append('<input name="master" type="hidden" value="' + $('#ptsCode').val() + ':' + $('#ptsDate').val() + 
                        ':' + $('#canCode').val() + ':' + $('#cs').val() + ':' + $('#location').val() + ':' + $('#itemCode').html() + ':' + $('#reff').val() + 
                        ':' + $('#coeFlk').val() + ':' + $('#coeNw').val() + ':' + $('#coeDw').val() + ':' + $('#coePw').val() + 
                        ':' + $('#qadReleaseTo').val() + ':' + $('#qadRemarks').val() + ':' + $('#brandName').val() + '" />');
                    
                    var i = 0;
                    $('#main tr').each(function() {
                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('select').val() + 
                            ':' + $(this).find('input:eq(0)').val() + ':' + $(this).find('input:eq(1)').val() + 
                            ':' + $(this).find('input:eq(2)').val() + ':' + $(this).find('input:eq(3)').val() + 
                            ':' + i + '" />'); i++;
                    });
                    
                    $('#poster').submit();
                }
                
                return false;
            });
            
            $('#btnAdd').bind('click', function() {
                if(!$('#ptsForm')[0].checkValidity())
                    return;
                
                $('#main').append('<tr><td><select><option value="DS">Day Shift</option><option value="2nd">Second Shift</option><option value="NS">Night Shift</option></select></td>' +
                    '<td><input class="datepicker" size="10" type="text" required="true" /></td><td><input type="text" required="true" /></td><td><input type="text" required="true" /></td>' +
                    '<td><input class="qty" pattern="[0-9]+([\.|,][0-9]+)?" step="0.01" type="text" value="0.00" required="true" /></td><td>' +
                    '<input class="btnDel ui-button ui-widget ui-state-default ui-corner-all" style="font-size: smaller;" type="button" value="Remove This" /></td></tr>'); setDatePicker();
                $('#main tr:last > td:eq(0) > select').focus();
            });
            
            $('.qty').live('blur', function() {
                $(this).val(parseFloat($(this).val()).toFixed(2));
            });
            
            $('.qty').live('keyup', function() {
                var i = 0;
                $('#main input.qty').each(function() {
                    if($(this)[0].checkValidity())
                        i += parseFloat($(this).val());
                });
                $('#quantity').html(parseFloat(i).toFixed(2));
            });
            
            $('.btnDel').live('click', function() {
                $(this).parent().parent().remove();
                if($('.qty').length)
                    $('.qty:eq(0)').trigger('keyup');
                else
                    $('#quantity').html('0.00');
            });
            
            function setDatePicker() {
                $('#main input.datepicker').each(function() {
                    if(!$(this).val())
                        $(this).datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
                });
            }

        </script>
    </body>
</html>
