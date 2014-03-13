<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Fish Meal</title>
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
                    <form action="FishMeal.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="borForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
<!--                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>BOR Number</td>
                                    <td><input id="borCode" type="text" required="true" /></td>
                                    <td>Bor Date</td>
                                    <td><input id="borDate" size="10" type="text" required="true" /></td>
                                </tr>
                            </tbody>-->
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('FishMeal.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <tbody>
                                
                            </tbody>
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
//            $('#borDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
//            
//            $('#borForm').bind('submit', function() {
//                var f = false;
//                $('#main tr').each(function() {
//                    var v = ':';
//                    var b = false;
//                    $(this).find('td').each(function(i) {
//                        if(i !== 0) {
//                            v += $(this).find('input').val() + ':';
//                            b = true;
//                        }
//                    }); f = true;
//
//                    if(b)
//                        $('#poster').append('<input name="detail" type="hidden" value="' + v + '" />');
//                });
//                
//                if(f) {
//                    $('#poster').append('<input name="master" type="hidden" value="' + $('#borCode').val() + 
//                        ':' + $('#borDate').val() + '" />');
////                    $('#poster').submit();
//                }
//                
//                return false;
//            });
            
//            $('#brandName').bind('focus', function() {
//                $(this).val(null); $('#itemCode').html(null); $('#itemName').html(null); $('#pack').html(null);
//            });
//            
//            $('#brandName').autocomplete({
//                source: '?action=getBrandName',
//                minLength: 2,
//                select: function( event, ui ) {
//                    $('#brandName').val(ui.item.brand);
//                    $('#itemCode').html(ui.item.itemCode);
//                    $('#itemName').html(ui.item.itemName);
//                    $('#pack').html(ui.item.style + ' / ' + ui.item.size);
//                    
//                    return false;
//                }
//            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
//                return $( '<li>' )
//                    .data( "item", item ) 
//                    .append( '<a><b>' + item.itemCode + ' : ' + item.itemName + 
//                    '</b><br /> For Brand : ' + item.brand + '</a></li>' )
//                    .appendTo( ul );
//            };
//            
//            $('#ptsForm').bind('submit', function() {
//                if($('#main tr').length) {
//                    $('#poster').append('<input name="master" type="hidden" value="' + $('#ptsCode').val() + ':' + $('#ptsDate').val() + 
//                        ':' + $('#canCode').val() + ':' + $('#cs').val() + ':' + $('#location').val() + ':' + $('#itemCode').html() + ':' + $('#reff').val() + 
//                        ':' + $('#coeFlk').val() + ':' + $('#coeNw').val() + ':' + $('#coeDw').val() + ':' + $('#coePw').val() + 
//                        ':' + $('#qadReleaseTo').val() + ':' + $('#qadRemarks').val() + '" />');
//                    
//                    var i = 0;
//                    $('#main tr').each(function() {
//                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('select').val() + 
//                            ':' + $(this).find('input:eq(0)').val() + ':' + $(this).find('input:eq(1)').val() + 
//                            ':' + $(this).find('input:eq(2)').val() + ':' + $(this).find('input:eq(3)').val() + 
//                            ':' + i + '" />'); i++;
//                    });
//                    
//                    $('#poster').submit();
//                }
//                
//                return false;
//            });
//            
//            $('#btnAdd').bind('click', function() {
//                if(!$('#ptsForm')[0].checkValidity())
//                    return;
//                
//                $('#main').append('<tr><td><select><option value="DS">Day Shift</option><option value="2nd">Second Shift</option><option value="NS">Night Shift</option></select></td>' +
//                    '<td><input class="datepicker" size="10" type="text" required="true" /></td><td><input type="text" required="true" /></td><td><input type="text" required="true" /></td>' +
//                    '<td><input class="qty" pattern="[0-9]+([\.|,][0-9]+)?" step="0.01" type="text" value="0.00" required="true" /></td><td>' +
//                    '<input class="btnDel ui-button ui-widget ui-state-default ui-corner-all" style="font-size: smaller;" type="button" value="Remove This" /></td></tr>'); setDatePicker();
//                $('#main tr:last > td:eq(0) > select').focus();
//            });
//            
//            $('.qty').live('blur', function() {
//                $(this).val(parseFloat($(this).val()).toFixed(2));
//            });
//            
//            $('.qty').live('keyup', function() {
//                var i = 0;
//                $('#main input.qty').each(function() {
//                    if($(this)[0].checkValidity())
//                        i += parseFloat($(this).val());
//                });
//                $('#quantity').html(parseFloat(i).toFixed(2));
//            });
//            
//            $('.btnDel').live('click', function() {
//                $(this).parent().parent().remove();
//                if($('.qty').length)
//                    $('.qty:eq(0)').trigger('keyup');
//                else
//                    $('#quantity').html('0.00');
//            });
//            
//            function setDatePicker() {
//                $('#main input.datepicker').each(function() {
//                    if(!$(this).val())
//                        $(this).datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
//                });
//            }

        </script>
    </body>
</html>
