<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Transfer &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                    <form action="#" id="fTransfer" name="fTransfer" method="post">
                        <input type="hidden" id="tsDate" name="tsDate">
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">TS Number</td>
                                    <td>
                                        <input type="text" id="tsCode" name="tsCode" value="${model.tss[0].ts_code}" readonly>
                                        Type
                                        <select id="tsType" name="tsType" required>
                                            <c:if test="${model.tss[0].ts_type == 'B'}"><option value="B">Bad Stocks</option></c:if>
                                            <c:if test="${model.tss[0].ts_type == 'O'}"><option value="O">Others</option></c:if>
                                            <c:if test="${model.tss[0].ts_type == 'R'}"><option value="R">Reprocess</option></c:if>
                                            </select>
                                        </td>
                                        <td>TS Date</td>
                                        <td><input type="text" id="tsDatePicker" name="tsDatePicker" size="10" required></td>
                                    </tr>
                                    <tr>
                                        <td>From</td>
                                        <td><input type="text" id="tsFrom" name="tsFrom" size="50" value="${model.tss[0].ts_from}" required></td>
                                    <td>To</td>
                                    <td>
                                        <input type="text" id="tsTo" name="tsTo" size="50" value="${model.tss[0].ts_to}" required>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="tsRemarks" name="tsRemarks" size="50" value="${model.tss[0].ts_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave">
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGTransfer.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Transfer &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="8">
                                    <input type="text" id="ptsCode" name="ptsCode">
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add Item">
                                </th>
                            </tr>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Item Name</td>
                                <td>Pallet Number</td>
                                <td>Current Quantity</td>
                                <td>Out Quantity</td>
                                <td>Remaining Quantity</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.tss}" var="x" varStatus="vs">
                                <tr data-item="${x.item_code}" data-id="${x.ts_id}">
                                    <td>${vs.index + 1}</td>
                                    <td>${x.pack_style}</td>
                                    <td>${x.pack_size}</td>
                                    <td>${x.item_name}</td>
                                    <td data-id="${x.pts_id}">${x.pts_code}</td>
                                    <td>${x.sc_cqty}</td>
                                    <td><input type="text" class="qty" value="${x.ts_quantity}" size="6"></td>
                                    <td>${x.sc_cqty}</td>
                                    <td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="actionDelete(this);"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>

            // BIND | Date Picker to ofal date
            $("#tsDatePicker").val(gnvs.util.toViewDate('${model.tss[0].ts_date}'));//.datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#tsDate", changeYear: true, changeMonth: true});
            $('#tsDate').val(gnvs.util.toDBDate($('#tsDatePicker').val()));

            // BIND | Search PTS button
            var idx = -1;
            $("#btnAdd").bind("click", function() {
                // VALIDATE | No same pts number in list
                var ptsCode = $('#ptsCode').val();
                if ($('#detail tr td:nth-child(5):containsCI("' + ptsCode + '")').length > 0) {
                    alert('Pallet Transfer Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | Before pick transfer type
                if ($('#tsType').val() === '') {
                    alert('Please select Transfer Slip Type first!');
                    $('#tsType').focus();
                    return false;
                }

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    if ($('#tsType').val() !== 'R') {
                        $.ajax({
                            url: "?", type: "post",
                            data: {action: "getPalletTransfer", key: ptsCode},
                            dataType: "json",
                            success: function(json) {
                                for (var i = 0; i < json.length; i++) {
                                    $('tbody#detail').append('<tr data-item="' + json[i][3] + '" data-status="C" data-id="' + idx + '">' +
                                            '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                            '<td>' + json[i][1] + '</td><td>' + json[i][2] + '</td>' +
                                            '<td>' + json[i][4] + '</td><td data-id="' + json[i][5] + '">' + json[i][6] + '</td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input type="text" class="qty" value="0" size="6"></td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td></tr>');
                                    idx = idx - 1;
                                }

                                if (json.length > 0) {
                                    $('#ptsCode').val("");
                                }
                            },
                            complete: function() {
                                $('#load').remove();
                                $('#ptsCode').focus();
                            }
                        });
                    } else {
                        $.ajax({
                            url: "?", type: "post",
                            data: {action: "getBadPalletTransfer", key: ptsCode},
                            dataType: "json",
                            success: function(json) {
                                for (var i = 0; i < json.length; i++) {
                                    $('tbody#detail').append('<tr data-item="' + json[i][3] + '" data-status="C" data-id="' + idx + '">' +
                                            '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                            '<td>' + json[i][1] + '</td><td>' + json[i][2] + '</td>' +
                                            '<td>' + json[i][4] + '</td><td data-id="' + json[i][5] + '">' + json[i][6] + '</td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input type="text" class="qty" value="0" size="6"></td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td></tr>');
                                    idx = idx - 1;
                                }

                                if (json.length > 0) {
                                    $('#ptsCode').val("");
                                }
                            },
                            complete: function() {
                                $('#load').remove();
                                $('#ptsCode').focus();
                            }
                        });
                    }
                }
            });

            // BIND | Save function
            $("#fTransfer").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                // VALIDATE | 0 (zero) value in bad quantity
                if ($('tbody#detail tr input.qty')
                        .filter(function() {
                            return parseFloat($(this).val()) <= 0;
                        }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "", header = $('#tsCode').val() + ':s:' + $('#tsType').val() + ':s:' + $('#tsDate').val() + ':s:' + $('#tsFrom').val() + ':s:' + $('#tsTo').val() + ':s:' + $('#tsRemarks').val() + ':s:';

                $('tbody#detail tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(4)').data('id') + ':s:' + $(this).data('item') + ':s:' + $(this).find('.qty').val() + ':s::se:';
                });

                if (confirm('Update Transfer #' + $('#tsCode').val() + ' ?')) {
                    if (data === '') {
                        data = header + 'X:s:-1:s::se:';
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

//            // LIVE | onBlur out quantity
//            $('.qtyOutCs,.qtyOutTin').live('blur', function() {
//                var ppc = $(this).parent().parent().data('ppc'),
//                        $td = $(this).parent(),
//                        qty = parseInt($td.find('input:eq(0)').data('max')) * ppc + parseInt($td.find('input:eq(1)').data('max'));
//
//                if ($(this).val() < 0 || parseInt($(this).val()) >= parseInt($(this).data('max'))) {
//                    if ($(this).hasClass('qtyOutTin')) {
//                        if (parseInt($(this).prev().val()) === $(this).prev().data('max')) {
//                            $(this).val($(this).data('max'));
//                        } else if (parseInt($(this).val()) > (ppc - 1)) {
//                            $(this).val(ppc - 1);
//                        }
//                    } else {
//                        $(this).val($(this).data('max'));
//                        $(this).next().trigger('blur');
//                        return false;
//                    }
//                }
//
//                var qtyLess = parseInt($td.find('input:eq(0)').val()) * ppc + parseInt($td.find('input:eq(1)').val());
//                $td.next().html(tinToCs(qty - qtyLess, ppc).toFixed(2));
//            });
//
//            // FUNCTION | Cs to Tin conversation
//
//            function tinToCs(v, m) {
//                var cs = Math.floor(v / m);
//                return cs + ((v % m) / 100);
//            }

            // Update | function
            function actionDelete(el) {
                var $tr = $(el).parent().parent();
                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'D').hide();
                } else {
                    $tr.remove();
                }
                gnvs.util.reNumbering($('#detail'), 1);
            }

            $('#detail tr input[type="text"]').live('keyup', function() {
                var $tr = $(this).parent().parent();
                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            });

        </script>
    </body>
</html>

