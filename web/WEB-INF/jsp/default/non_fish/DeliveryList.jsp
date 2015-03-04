<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delivery &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
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
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="search" action="Delivery.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Delivery &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">DR Number</td>
                                    <td><input type="text" name="dr_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Delivery.htm?action=create&type=NF');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table id="list" class="collapse tblForm row-select">
                        <caption>Delivery &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td>DR Number</td>
                                <td>DR Date</td>
                                <td>From</td>
                                <td>To</td>
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
            util.initListTable($('#list'), 'u:d:R_NFDr_Delivery Receipt (xls)');
            
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
                            url: 'Delivery.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>DR Number : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>Item Code</th><th>Item Name</th>' +
                                    '<th>Quantity</th><th>Uom</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemCode + '</td><td>' + json[i].itemName +
                                        '</td><td>' + json[i].qty + '</td><td>' + json[i].uom + '</td></tr>');
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
            
        </script>
    </body>
</html>
