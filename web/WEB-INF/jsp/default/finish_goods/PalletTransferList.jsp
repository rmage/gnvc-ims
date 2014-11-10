<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Pallet Transfer</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
/*            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }*/
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="PalletTransfer.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Transfer &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PTS Number</td>
                                    <td><input type="text" name="PTS_CODE" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGPalletTransfer.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Pallet Transfer &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td>PTS Number</td>
                                <td>PTS Date</td>
                                <td>For Brand</td>
                                <td>Pack Style</td>
                                <td>Can Code</td>
                                <td>Reff</td>
                                <td>Location</td>
                                <td>Quantity</td>
                                <td>Hold Status</td>
                                <td>Creator</td>
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
        <!--<div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>-->
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_FGPts_Print Pallet Transfer Slip (xls)');
            
//            var cs = 0;
//            var loc = '';
//            
//            $('.d').live('click', function(){
//                var $p = $(this).parent().parent();
//                cs = $p.data('cs');
//                loc = $p.data('location');
//                fyaQPanel($(this).html());
//            });
//            
//            function fyaQPanel(p) {
//                if(p === 0) {
//                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
//                } else {
//                    $('#fyaQPanel').fadeIn(300, function(){
//                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
//                        $.ajax({
//                            url: 'Pts.htm',
//                            data: {action: 'ajaxDocument', key: p},
//                            dataType: 'json',
//                            success: function(json) {
//                                $('#fyaQPanel').html(null);
//                                $('#fyaQPanel').append('<h6>PTS Number : ' + p + ' | Cs : ' + cs + ' | Location : ' + loc + '</h6><hr />');
//                                $('#fyaQPanel').append('<table><thead><tr><th>NS/DS</th><th>Date</th>' +
//                                    '<th>Prod. Batch</th><th>Basket</th><th>Quantity</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
//                                for(var i = 0; i < json.length; i++) {
//                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].shift + '</td><td>' + json[i].date +
//                                        '</td><td>' + json[i].prodbatch + '</td><td>' + json[i].basket + 
//                                        '</td><td>' + numberWithCommas(json[i].qty) + '</td></tr>');
//                                }
//                            },
//                            complete: function() {
//                                if($('#fyaQPanel').html() === '')
//                                    fyaQPanel(0);
//                            }
//                        });
//                    }); 
//                }
//            }
//            
//            function numberWithCommas(x) {
//                var parts = x.toString().split(".");
//                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
//                return parts.join(".");
//            }
           
        </script>
    </body>
</html>
