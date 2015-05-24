<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Delivery &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker {
                display: none;
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
                    <form action="#" id="fDelivery" name="fDelivery" method="post">
                        <input type="hidden" id="drDate" name="drDate">
                        <table class="collapse tblForm row-select">
                            <caption>Delivery &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Delivery Number</td>
                                    <td><input type="text" id="drCode" name="drCode" value="${model.ds[0].dr_code}" readonly></td>
                                    <td>Delivery Date</td>
                                    <td><input type="text" id="drDatePicker" name="drDatePicker" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td><input type="text" id="drFrom" name="drFrom" size="50" value="${model.ds[0].dr_from}"></td>
                                    <td>To</td>
                                    <td><input type="text" id="drTo" name="drTo" size="50" value="${model.ds[0].dr_to}"></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="drRemarks" name="drRemarks" size="50" value="${model.ds[0].dr_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGDelivery.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Delivery &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="7">
                                    <input type="text" id="ptsCode" name="ptsCode">
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add Item">
                                </th>
                            </tr>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Pallet Number</td>
                                <td>Item Name</td>
                                <td>Quantity</td>
                                <td>Delivery Quantity</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.ds}" var="x" varStatus="vs">
                                <tr data-item="${x.item_code}" data-id="${x.dr_id}">
                                    <td>${vs.index + 1}</td>
                                    <td>${x.pack_style}</td>
                                    <td>${x.pack_size}</td>
                                    <td data-id="${x.pts_id}">${x.pts_code}</td>
                                    <td>${x.item_name}</td>
                                    <td>${x.sc_cqty}</td>
                                    <td><input type="text" class="qty" value="${x.dr_qty}" size="6"></td>
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
            $("#drDatePicker").val(gnvs.util.toViewDate('${model.ds[0].dr_date}'))
                    .datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#drDate", changeYear: true, changeMonth: true});
            $('#drDate').val(gnvs.util.toDBDate($('#drDatePicker').val()));

            // BIND | Search PTS button
            var idx = -1;
            $("#btnAdd").bind("click", function() {
                var ptsCode = $('#ptsCode').val();
                if ($('tbody#detail tr td:nth-child(4):containsCI("' + ptsCode + '")').length > 0) {
                    alert('Pallet Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: ptsCode},
                        dataType: "json",
                        success: function(json) {
                            for (var i = 0; i < json.length; i++) {
                                $('tbody#detail').append('<tr data-item="' + json[i][3] + '" data-status="C" data-id="' + idx + '">' +
                                        '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                        '<td>' + json[i][1] + '</td>' +
                                        '<td>' + json[i][2] + '</td>' +
                                        '<td data-id="' + json[i][6] + '">' + json[i][7] + '</td>' +
                                        '<td>' + json[i][4] + '</td>' +
                                        '<td>' + json[i][5] + '</td>' +
                                        '<td><input type="text" class="qty" value="0" size="6"></td>' +
                                        '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="actionDelete(this);"></td>' +
                                        '</tr>');
                                idx = idx - 1;
                            }

                            if (json.length > 0) {
                                $('#ptsCode').val("");
                            }
                        },
                        complete: function() {
                            $('img#load').remove();
                            $('#ptsCode').focus();
                        }
                    });
                }
            });

            // BIND | Save function
            $("#fDelivery").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                // VALIDATE | 0 (zero) value in bad quantity
                if ($('tbody#detail input.cs')
                        .filter(function() {
                            return parseFloat($(this).val()) <= 0;
                        }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "";

                var header = $('#drCode').val() + ':s:' + $('#drDate').val() + ':s:' + $('#drFrom').val() + ':s:' + $('#drTo').val() + ':s:' + $('#drRemarks').val() + ':s:';

                $('tbody#detail tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(3)').data('id') + ':s:' + $(this).data('item') + ':s:' + $(this).find('.qty').val() + ':s::se:';
                });

                if (confirm('Update Delivery #' + $('#drCode').val() + ' ?')) {
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

            // LIVE | validate maximum quantity
//            $('tbody#detail .cs,tbody#detail .tin').live('blur', function() {
//                var ppc = $(this).parent().parent().data('ppc');
//
//                if ($(this).val() < 0 || parseInt($(this).val()) >= parseInt($(this).data('max'))) {
//                    if ($(this).hasClass('tin')) {
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
//            });

            // UPDATE | function
            $('#detail tr input[type="text"]').live('keyup', function() {
                var $tr = $(this).parent().parent();

                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            });

            function actionDelete(el) {
                var $tr = $(el).parent().parent();
                if ($tr.data('id') < 0) {
                    $tr.remove();
                } else {
                    $tr.attr('data-status', 'D').hide();
                }

                gnvs.util.reNumbering($('#detail'), 1);
            }

        </script>
    </body>
</html>

