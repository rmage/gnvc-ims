<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Order Fill / Authority to Label (OFAL)</title>
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
                    <form action="Ofal.htm" id="poster" method="post">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="ofalForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Bor Number</td>
                                    <td>
                                        <input id="borCode" type="text" required="true" />
                                        <input name="borCode" type="hidden" />
                                    </td>
                                    <td>Label declaration:</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td>: <span id="borBuyer"></span></td>
                                    <td>- Net Weight</td>
                                    <td><input id="ofalNw" type="text" required="true" /> g</td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td>: <span id="borBrand"></span></td>
                                    <td>- Drained / Weight</td>
                                    <td><input id="ofalDw" type="text" required="true" /> g</td>
                                </tr>
                                <tr>
                                    <td>Reference No.</td>
                                    <td>: <span id="borReff"></span></td>
                                    <td>- BBE</td>
                                    <td>:</td>
                                </tr>
                                <tr>
                                    <td>Destination</td>
                                    <td>: <span id="borDest"></span></td>
                                    <td>Actual Specification :</td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td>: <span id="borQty">0</span> Cs</td>
                                    <td>- Net Weight</td>
                                    <td>: <span id="borNw">0</span> g</td>
                                </tr>
                                <tr>
                                    <td>P. Style / Size</td>
                                    <td>: <span id="borPack"></span> / <span id="borCan"></span></td>
                                    <td>- Drained Weight</td>
                                    <td>: <span id="borDw">0</span> g</td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td>: <span id="canCode"></span></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Max. Code</td>
                                    <td>: <span id="borMax"></span></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                
                                <tr>
                                    <td>Shipment Schedule</td>
                                    <td>: <input id="ofalShipment" size="50" type="text" required="true" /></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Ofal.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="8">Type PTS Number (to add another un-reference PTS): <input id="pts" size="6" type="text" /></td>
                                </tr>
                                <tr>
                                    <td>Action</td>
                                    <td>PTS Number</td>
                                    <td>Bor Number</td>
                                    <td>PTS Date</td>
                                    <td>Can Code</td>
                                    <td>Quantity</td>
                                    <td>Location</td>
                                    <td>Remarks</td>
                                </tr>
                            </thead>
                            <tbody id="main"></tbody>
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
            $('#pts').autocomplete({
                source: '?action=getPtsUnref',
                minLength: 1,
                delay: 2000,
                select: function( event, ui ) {
                    $('#main').append('<tr><td><input class="check" type="checkbox" /></td><td>' + ui.item.ptsCode + 
                        '</td><td>' + ui.item.borCode  + '</td><td>' + ui.item.ptsDate + '</td><td>' + ui.item.canCode + 
                        '</td><td><input type="text" value="' + ui.item.qty + '" /></td><td>' + ui.item.location + '</td><td>' + 
                        ui.item.remarks + '</td></tr>');
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.ptsCode + '</b><br /> Date : ' + item.ptsDate + 
                    '<br /> Remarks : ' + item.remarks + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('#borCode').bind('keyup', function() { $('input[name="borCode"]').val(null); });
            
            $('#borCode').autocomplete({
                source: '?action=getBorDetail',
                minLength: 8,
                delay: 1000,
                select: function( event, ui ) {
                    $('#main').html('');
                    $('#borBuyer').html(ui.item.buyer);
                    $('#borBrand').html(ui.item.brand);
                    $('#borReff').html(ui.item.po);
                    $('#borDest').html(ui.item.destination);
                    $('#borQty').html(ui.item.qty);
                    $('#borNw').html(parseInt(ui.item.nw)); $('#ofalNw').val(parseInt(ui.item.nw));
                    $('#borPack').html(ui.item.packstyle);
                    $('#borCan').html(ui.item.cansize);
                    $('#borDw').html(ui.item.dw); $('#ofalDw').val(parseFloat(ui.item.dw));
                    $('#borMax').html(ui.item.max + 'CAN CODE');
                    $('input[name="borCode"]').val($('#borCode').val() + '_' + ui.item.idx);
                    
                    $.ajax({
                        url: 'Ofal.htm',
                        data: {action: 'getPts', borCode: $('#borCode').val(), brandName: ui.item.brand},
                        dataType: 'json',
                        success: function(json) {
                            for(var i = 0; i < json.length; i++) {
                                $('#main').append('<tr><td><input class="check" type="checkbox" /></td><td>' + json[i].ptsCode + 
                                    '</td><td>' + json[i].borCode  + '</td><td>' + json[i].ptsDate + '</td><td>' + json[i].canCode + 
                                    '</td><td><input type="text" value="' + json[i].qty + '" /></td><td>' + json[i].location + '</td><td>' + json[i].remarks + '</td></tr>');
                            }
                        }
                    });
                    
                    return false;
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.buyer + '</b><br /> Brand : ' + item.brand + 
                    '<br /> Reference : ' + item.po + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('.check').live('click', function() {
                if($(this)[0].checked) {
                    $(this).parent().parent().addClass("bold");
                } else {
                    $(this).parent().parent().removeClass("bold");
                }
            });
            
            $('#ofalForm').bind('submit', function() {
                if(!$('input[name="borCode"]').val()) return false;
                if(!$('.check:checked').length) return false;
                
                $('#poster').append('<input name="master" type="hidden" value="' + $('#ofalNw').val() + ':' + $('#ofalDw').val() +
                    ':' + $('input[name="borCode"]').val() + ':' + $('#ofalShipment').val() + '" />');
                var detail = '';
                $('.check:checked').each(function(i) {
                    if(i !== 0)
                        detail += ':';
                    detail += $(this).parent().next().html() + '-' + $(this).parent().parent().find('input[type="text"]').val();
                });
                $('#poster').append('<input name="detail" type="hidden" value="' + detail + '" />');
                $('#poster').submit();
                
                return false;
            });

        </script>
    </body>
</html>
