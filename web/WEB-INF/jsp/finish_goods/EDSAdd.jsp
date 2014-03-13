<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Export Data Slip</title>
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
            #main span {
                display: block;
                margin-left: 100px;
                text-align: right;
                width: 75px;
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
                    <form action="Eds.htm" id="edsForm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>EDS Number</td>
                                    <td><input id="edsCode" name="edsCode" type="text" required="true" /></td>
                                    <td>EDS Date</td>
                                    <td><input id="edsDate" name="edsDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: top;">Bor Number</td>
                                    <td style="vertical-align: top;">
                                        <input id="borCode" type="text" required="true" />
                                        <input name="borCode" type="hidden" />
                                    </td>
                                    <td style="vertical-align: top;">
                                        CI Number <br /><br />
                                        Remarks
                                    </td>
                                    <td>
                                        <input id="ciCode" name="ciCode" type="text" /> <br />
                                        <textarea name="edsRemarks"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>VAN</td>
                                    <td><input id="edsVan" name="edsVan" size="30" type="text" required="true" /></td>
                                    <td>Pelayaran Seal</td>
                                    <td><input id="edsSeal" name="edsSeal" size="30" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>Vessel</td>
                                    <td><input id="edsVessel" name="edsVessel" size="50" type="text" required="true" /></td>
                                    <td>Plat No.</td>
                                    <td><input id="edsPlatNo" name="edsPlatNo" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>Time</td>
                                    <td>
                                        In <input id="edsTimeIn" name="edsTimeIn" size="4" type="text" required="true" />
                                        Out <input id="edsTimeOut" name="edsTimeOut" size="4" type="text" required="true" />
                                    </td>
                                    <td>Driver</td>
                                    <td><input id="edsDriver" name="edsDriver" size="30" type="text" required="true" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Eds.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <tbody id="main">
                                <tr>
                                    <td><h5>BUYER</h5></td>
                                    <td colspan="4" id="dBuyer"></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><h5>PARTICULARS</h5></td>
                                    <td><h5>Can Size</h5></td>
                                    <td><h5>Number of Cases</h5></td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: top; width: 125px;">Pack Style / Can Code</td>
                                    <td style="vertical-align: top; width: 25px;">:</td>
                                    <td id="dPackStyle" style="width: 300px;"></td>
                                    <td id="dCanSize" style="vertical-align: top;"></td>
                                    <td id="dCase" style="vertical-align: bottom;"></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td>:</td>
                                    <td id="dBrand"></td>
                                    <td><span style="width: 100%;"><b>Total</b></span></td>
                                    <td id="dCaseTotal"></td>
                                </tr>
                                <tr>
                                    <td>Reference</td>
                                    <td>:</td>
                                    <td id="dRef"></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Destination</td>
                                    <td>:</td>
                                    <td id="dDest"></td>
                                    <td></td>
                                    <td></td>
                                </tr>
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
            $('#edsDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            
            $('#borCode').bind('keyup', function() { $('input[name="borCode"]').val(null); });
            
            $('#borCode').autocomplete({
                source: '?action=getBorDetail',
                minLength: 8,
                delay: 1000,
                select: function( event, ui ) {
                    $("#dBuyer").html(ui.item.buyer);
                    $('#dCanSize').html(ui.item.cansize);
                    $('#dBrand').html(ui.item.brand);
                    $('#dRef').html('PO#' + ui.item.po);
                    $('#dDest').html(ui.item.destination);
                    
                    var tot = 0;
                    var pts1 = '';
                    var pts2 = '';
                    var json = ui.item.pts;
                    for(var x = 0 ; x < json.length; x++) {
                        tot += parseFloat(json[x].cases);
                        pts1 += '<span>' + json[x].cancode + '</span>';
                        pts2 += '<span>' + json[x].cases + '</span>';
                    }
                    $('#dPackStyle').html(ui.item.packstyle + pts1);
                    $('#dCase').html(pts2);
                    $('#dCaseTotal').html('<span><b>' + tot + '</b></b>');
                    
                    $('input[name="borCode"]').val($('#borCode').val() + '::' + ui.item.idx);
                    
                    return false;
                }
            }).data( 'autocomplete' )._renderItem = function( ul, item ) {
                return $( '<li>' )
                    .data( "item", item ) 
                    .append( '<a><b>' + item.buyer + '</b><br /> Brand : ' + item.brand + 
                    '<br /> Reference : PO#' + item.po + '</a></li>' )
                    .appendTo( ul );
            };
            
            $('#edsForm').bind('submit', function() {
                
                
                if(!$('input[name="borCode"]').val()) return false;
            });

        </script>
    </body>
</html>
