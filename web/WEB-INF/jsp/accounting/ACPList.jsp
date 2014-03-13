<%@page import="java.util.Date"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting :: Price Listing</title>
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
                    <table class="collapse tblForm row-select">
                        <caption>Item Pricing</caption>
                        <tbody>
                            <tr>
                                <td style="width: 200px;">Item Category</td>
                                <td>
                                    <select id="itemType">
                                        <option value="">-- Select Item Category --</option>
                                        <c:forEach items="${model.pc}" var="x">
                                            <option value="${x.categoryCode}">${x.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <input id="btnSearch" name="btnSearch" type="button" value="Search Item" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <table class="collapse tblForm row-select">
                        <caption>Item List</caption>
                        <thead>
                            <tr>
                                <td>Item Code</td>
                                <td>Item Name</td>
                                <td>Item Type</td>
                                <td>Last RR</td>
                                <td>Previous Price</td>
                                <td>Adjusted Price</td>
                                <td>Status</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
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
            
            var sNone = '<span style="font-weight: bold;">-</span>';
            var sFocus = '<span style="color: blue; font-weight: bold;">Updating</span>';
            var sSave = '<span style="color: red; font-weight: bold;">Saving</span>';
            var sDone = '<span style="color: green; font-weight: bold;">Done</span>';
            
            $('#main input').live('focus', function() {
                $(this).parent().next().html(sFocus);
            });
            
            $('#main input').live('blur', function() {
                $o = $(this);
                if($o.data('u')) {
                    $o.data('u', false);
                    
                    var regexp = /^\d+(\.\d{1,2})?$/;
                    if(!regexp.test($o.val())) {
                        return;
                    } $o.parent().next().html(sSave);
                    
                    $.ajax({
                        url: 'ProductPrice.htm',
                        data: {
                            action: 'update', 
                            itemCode: $o.parent().parent().find('td:eq(0)').html(), 
                            currency: $o.prev().val(), 
                            price: $o.val()},
                        dataType: 'json',
                        success: function(json) {
                            if(json.s) {
                                $o.val(null);
                                $o.parent().prev().html(json.currency + ' ' + numberWithCommas(json.old));
                                $o.parent().prev().prev().html(json.rr);
                                $o.parent().next().html(sDone);
                            }
                        }
                    });
                } else {
                    $(this).parent().next().html(sNone);
                }
            });
            
            $('#main input').live('keyup', function() {
                $(this).data('u', true);
            });
           
            $('#btnSearch').bind('click', function() {
                if(!$('#itemType').val())
                    return;
                
                var $o = $(this);
                $o.attr('disabled', true);
                $('#itemType').attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                
                $('#main').html(null);
                $.ajax({
                    url: 'ProductPrice.htm',
                    data: {action: 'getProductList', key: $('#itemType').val()},
                    dataType: 'json',
                    success: function(json) {
                        for(var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + json[i].code + '</td><td>' + json[i].name + '</td><td>' + json[i].type + 
                                '</td><td>' + json[i].rr + '</td><td>' + json[i].currency + ' ' + numberWithCommas(json[i].old) + 
                                '</td><td><select><option>IDR</option></select> <input type="text" value="" /></td><td></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        $('#itemType').attr('disabled', false);
                    }
                });
                
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
