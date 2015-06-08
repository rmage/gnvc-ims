<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Transfer &therefore; IMS</title>
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
                    <form action="TransferSlip.htm" id="tsForm" method="post">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="type"value="NORMAL">
                        <input type="hidden" name="module" value="NF">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>TS Number</td>
                                    <td><input id="tsCode" name="tsCode" pattern="[0-9]{1,}" type="text" value="${model.ts[0].ts_code}" readonly></td>
                                    <td>TS Date</td>
                                    <td><input id="tsDate" name="tsDate" size="10" type="text" required></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="tsInfo" name="tsInfo" size="50" value="${model.ts[0].ts_info}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Update">
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('TransferSlip.htm?module=NF');">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select SWS</td>
                                    <td colspan="7">
                                        <input type="text" id="swsCode" name="swsCode" size="10" value="${model.ts[0].sws_code}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Item Name</td>
                                    <td>Item Code</td>
                                    <td>Type</td>
                                    <td>Quantity Request</td>
                                    <td>Current Stocks</td>
                                    <td>Quantity Out</td>
                                    <td>Uom</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.ts}" var="x" varStatus="xs">
                                    <tr data-id="${x.id == null ? -1 : x.id}">
                                        <td>${xs.index + 1}</td>
                                        <td>${x.product_name}</td>
                                        <td>${x.product_code}</td>
                                        <td>${x.product_category}</td>
                                        <td>${x.qty_req}</td>
                                        <td>${x.soh}</td>
                                        <td><input class="detailQty" name="qty" data-max="${x.soh}" type="text" value="${x.qty_out}"></td>
                                        <td>${x.uom_name}</td>
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
            // INIT | value
            $('#tsDate').val(gnvs.util.toViewDate('${model.ts[0].ts_date}'));

            /* BIND | element event */
            $('#tsDate').datepicker({
                dateFormat: "dd/mm/yy",
                changeMonth: true,
                changeYear: true
            });

            $('#select').bind('click', function() {
                if ($('#swsCode').val() === '') {
                    $('#main').html(null);
                    return;
                }

                $(this).attr('disabled', true);
                $(this).after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                $.ajax({
                    url: 'TransferSlip.htm',
                    data: {action: 'getSwsDetail', key: $("#swsCode").val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for (var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + (i + 1) + '</td><td>' + json[i][2] + '</td><td><input name="item" type="hidden" value="' + json[i][1] + '" />' + json[i][1] +
                                    '</td><td>' + json[i][3] + '</td><td>' + json[i][4] + '</td><td>' + json[i][100] + '</td><td><input class="detailQty" name="qty" pattern="[0-9]{1,}" data-max="' + json[i][4].replace(/,/g, "") +
                                    '" type="text" /></td><td>' + json[i][5] + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $('#select').attr('disabled', false);
                    }
                });
            });

            // VALIDATE | Minimum 1 (one) quantity
            $("#tsForm").bind("submit", function() {
                var data = '';
                var header = $('#tsCode').val() + ':s:' + gnvs.util.toDBDate($('#tsDate').val()) + ':s:' + $('#tsInfo').val() + ':s:NF:s:NORMAL:s:' + $('#swsCode').val() + ':s:';

                $('#main tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(2)').html() + ':s:' + $(this).find('input:eq(0)').val() + ':s::se:';
                });

                if (confirm('Update Transfer #' + $('#tsCode').val() + ' ?')) {
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

            // Update function
            $('.detailQty').live('keyup', function() {
                var $p = $(this).parent().parent();

                if ($p.data('id') === -1) {
                    if ($(this).val() === '' || isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) < 1) {
                        $p.removeAttr('data-status');
                    } else {
                        $p.attr('data-status', 'C');
                    }
                } else {
                    if ($(this).val() === '' || isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) < 1) {
                        $p.attr('data-status', 'D');
                    } else {
                        $p.attr('data-status', 'U');
                    }
                }
            });

        </script>
    </body>
</html>
