<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Price Assignment</title>
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
                    <form action="PriceAssignment.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PRS Number</td>
                                    <td><input type="text" name="prsnumber" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('PriceAssignment.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="prsnumber">Prs Number</td>
                                <td column="supplier_code">Supplier Code</td>
                                <td>Supplier Name</td>
                                <td>Assign Date</td>
                                <td>Item Code</td>
                                <td>Item Name</td>
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
            /* jQuery | Binding event */
            $('.d').live('click', function(){
                fyaQPanel($(this));
            });

            function fyaQPanel(p) {
                if(p === 0) {
                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
                } else {
                    var $t = p.parent().parent();
                    $('#fyaQPanel').fadeIn(300, function(){
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'PriceAssignment.htm',
                            data: {action: 'ajaxDocument', key1: p.html(), key2: $t.find('td:eq(6)').html(), key3: $t.find('td:eq(3)').html()},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>PRS Number : ' + p.html() + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>PRS Number</th><th>Assign Date</th><th>Item Code</th><th>Item Name</th>'
                                    + '<th>Supplier Code</th><th>Supplier Name</th><th>Unit Price</th><th>ToP</th><th>ToP Desc.</th><th>ToD</th>'
                                    + '<th>Warranty</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].prsNo + '</td><td>' + json[i].assignDate 
                                        + '</td><td>' + json[i].itemCode + '</td><td>' + json[i].itemName 
                                        + '</td><td>' + json[i].supplierCode + '</td><td>' + json[i].supplierName 
                                        + '</td><td>' + numberWithCommas(json[i].unitPrice) + '</td><td>' + json[i].top 
                                        + '</td><td>' + json[i].topDesc + '</td><td>' + json[i].tod + '</td><td>' + (json[i].warranty === 'null' ? '' : json[i].warranty) + '</td></tr>');
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
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
            util.initSearchForm($('#search'));
            util.initListTable($('#list'),'');
        </script>
    </body>
</html>
