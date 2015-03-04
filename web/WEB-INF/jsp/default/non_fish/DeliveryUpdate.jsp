<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Delivery &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
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
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="drForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Delivery &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>DR Number</td>
                                    <td>: <input id="drCode" type="text" value="${model.dr[0].dr_code}" readonly></td>
                                    <td>DR Date</td>
                                    <td>: <input id="drDate" size="10" type="text" required="true"></td>
                                    <td>Remarks</td>
                                    <td style="width: 400px;"><input type="text" id="drRemarks" name="drRemarks" size="50" value="${model.dr[0].dr_remarks}"></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td>: <input id="drFrom" size="40" type="text" value="${model.dr[0].dr_from}" required="true"></td>
                                    <td>Location</td>
                                    <td>: <input id="drFromLoc" type="text" value="${model.dr[0].dr_fromloc}"></td>
                                    <td>OR No.</td>
                                    <td> <input id="orCode" type="text" value="${model.dr[0].or_code}"></td>
                                </tr>

                                <tr>
                                    <td>To</td>
                                    <td>: <input id="drTo" size="40" type="text" value="${model.dr[0].supplier_name}" data-code="${model.dr[0].supplier_code}" required></td>
                                    <td>Location</td>
                                    <td>: <input id="drToLoc" type="text" value="${model.dr[0].dr_toloc}"></td>
                                    <td>DM No.</td>
                                    <td> <input id="dmCode" type="text" value="${model.dr[0].dm_code}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input id="save" type="submit" value="Update">
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('Delivery.htm?type=${model.dr[0].dr_type}');">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Delivery &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <th colspan="6">
                                        Please Type : 
                                        <select id="mode" name="mode">
                                            <option value="code">By Code</option>
                                            <option value="name">By Name</option>
                                        </select>
                                        <input id="itemName" size="40" type="text">
                                    </th>
                                </tr>
                                <tr>
                                    <td style="width: 100px;">Action</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Stock</td>
                                    <td>Quantity</td>
                                    <td>Uom</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.dr}" var="x">
                                    <tr data-id="${x.id}">
                                        <td><input class="btnDel ui-button ui-widget ui-state-default ui-corner-all" style="font-size: smaller;" type="button" value="Remove This"></td>
                                        <td>${x.product_code}</td>
                                        <td>${x.product_name}</td>
                                        <td>${x.qty_soh}</td>
                                        <td><input class="detailQty" type="text" value="${x.dr_qty}"></td>
                                        <td>${x.dr_uom}</td>
                                    </tr>
                                </c:forEach>
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
            $('#drDate').datepicker({dateFormat: "dd/mm/yy"}).datepicker("setDate", gnvs.util.toViewDate('${model.dr[0].dr_date}'));

            $("#mode").bind("change", function() {
                $('#itemName').autocomplete("option", "source", "?action=getProduct&mode=" + $(this).val());
            });

            var id = -1;
            $('#itemName').autocomplete({
                delay: 1000,
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(event, ui) {
                    if ($('#main td:nth-child(2):contains("' + ui.item.itemCode + '")').length === 0) {
                        $('#main').append('<tr data-id="' + id + '" data-status="C"><td><input class="btnDel ui-button ui-widget ui-state-default ui-corner-all" style="font-size: smaller;" type="button" value="Remove This"></td>' +
                                '<td>' + ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.soh + '</td><td><input type="text" required></td>' +
                                '<td>' + ui.item.uom + '</td></tr>');
                        $('#main > tr:last-child').find('input').focus();
                        id = id - 1;
                    } else {
                        alert('Item already exists!');
                    }
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName + ' (' + item.type + ')' +
                                '</b><br> Stock : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };

            $('#drTo').autocomplete({
                source: '?action=getSupplier',
                minLength: 2,
                select: function(event, ui) {
                    $('#drTo').val(ui.item.name);
                    $('#drTo').data('code', ui.item.code);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.code + ' : ' + item.name +
                                '</b><br> Address : ' + item.address + '</a></li>')
                        .appendTo(ul);
            };

            $('#drForm').bind('submit', function() {
                var data = '';
                var header = $('#drCode').val() + ':s:' + gnvs.util.toDBDate($('#drDate').val()) + ':s:' + $('#drFrom').val() + ':s:' + $('#drFromLoc').val() + ':s:' + $('#drToLoc').val() + ':s:' + $('#drRemarks').val() + ':s:NF:s:' + $('#drTo').data('code') + ':s:' + $('#orCode').val() + ':s:' + $('#dmCode').val() + ':s:';

                $('#main tr[data-status]').each(function() {
                    if (!isNaN(parseFloat($(this).find('input:eq(1)').val())) && parseFloat($(this).find('input:eq(1)').val()) > 0) {
                        data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('input:eq(1)').val() + ':s:' + $(this).find('td:eq(5)').html() + ':s:' + $(this).find('td:eq(1)').html() + ':s::se:';
                    }
                });

                if (data !== '' && confirm('Update Delivery #' + $('#drCode').val() + ' ?')) {
                    if (data === '') {
                        data = data + header + 'X:s:-1:s::se:';
                    }
                    
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });

            $('.btnDel').live('click', function() {
                var $p = $(this).parent().parent();

                if (parseFloat($p.data('id')) > 0) {
                    $p.attr('data-status', 'D').hide();
                } else {
                    $p.remove();
                }
            });
            
            // Update function
            $('.detailQty').live('keyup', function() {
                var $p = $(this).parent().parent();
                
                if (parseFloat($p.data('id')) > 0) {
                    $p.attr('data-status', 'U');
                } else {
                    $p.removeAttr('data-status');
                }
            });

        </script>
    </body>
</html>
