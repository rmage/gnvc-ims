<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Receiving</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
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
                    <form id="search" name="search" action="ReceiveReport.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Receiving &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">RR Number</td>
                                    <td><input type="text" name="rr_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('ReceiveReport.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table id="list" class="collapse tblForm row-select">
                        <caption>Receiving &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="rr_code">RR Number</td>
                                <td column="rr_date">RR Date</td>
                                <td>PO Code</td>
                                <td column="rr_from">From</td>
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
        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_NFRr_Receiving Report (xls)');
            
            /* jQuery | Binding event */
            $('.d').live('click', function(){
                fyaQPanel($(this).html());
            });

            function fyaQPanel(p) {
                if(p === 0) {
                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
                } else {
                    $('#fyaQPanel').fadeIn(300, function(){
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'ReceiveReport.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>RR Number : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>Item Code</th><th>Item Name</th><th>Department</th><th>Quantity Good</th>'
                                    + '<th>Quantity Bad</th><th>UoM</th></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemCode + '</td><td>' + json[i].itemName 
                                        + '</td><td>' + json[i].department + '</td><td>' + json[i].qtyGood
                                        + '</td><td>' + json[i].qtyBad + '</td><td>' + json[i].uom + '</td></tr>');
                                }
                            },
                            complete: function() {
                                if($('#fyaQPanel').html() === '')
                                    fyaQPanel(0);
                            }
                        });
                    }); 
                }
            }
            
//            function numberWithCommas(x) {
//                var parts = x.toString().split(".");
//                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
//                return parts.join(".");
//            }
        </script>
    </body>
</html>
