<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Pallet Transfer &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete { max-height: 250px; overflow-y: auto; /* prevent horizontal scrollbar */overflow-x: hidden; }
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
                    <form action="#" id="fPts" name="fPts" method="post">
                        <input type="hidden" id="ptsId" name="ptsId" value="${model.ptss[0].pts_id}">
                        <input type="hidden" id="ptsDate" name="ptsDate">
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Transfer &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PTS Number</td>
                                    <td>
                                        <input type="text" id="ptsCode" name="ptsCode" value="${model.ptss[0].pts_code}" readonly>
                                        Status: 
                                        <select id="ptsStatus" name="ptsStatus" required>
                                            <option value="">-</option>
                                            <option value="N">RELEASE</option>
                                            <option value="Y">HOLD</option>
                                        </select>
                                    </td>
                                    <td style="width: 200px;">PTS Date</td>
                                    <td><input type="text" id="ptsDatePicker" name="ptsDatePicker" size="10" required readonly></td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td>
                                        <input type="text" id="packStyle" name="packStyle" value="${model.ptss[0].pack_style_code} / ${model.ptss[0].can_size}" readonly>
                                    </td>
                                    <td>For Brand</td>
                                    <td><input type="text" id="forBrand" name="forBrand" size="30" value="${model.ptss[0].pts_for_brand}"></td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" value="${model.ptss[0].pts_can_code}" required></td>
                                    <td>Reff</td>
                                    <td><input type="text" id="borReference" name="borReference" value="${model.ptss[0].bor_reference}" data-valid="true" onblur="input.checkDataValid(this);"></td>
                                </tr>
                                <tr>
                                    <td>Item</td>
                                    <td>
                                        <input id="itemCode" name="itemCode" type="text" value="${model.ptss[0].item_code}" data-valid="true" onblur="input.checkDataValid(this);" readonly>
                                    </td>
                                    <td>Location</td>
                                    <td>
                                        <select id="ptsLocation" name="ptsLocation">
                                            <c:forEach items="${model.ls}" var="x">
                                                <option value="${x.loca_id}">${x.loca_code} | ${x.loca_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave">
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletTransfer.htm');">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>

                        <table class="collapse tblForm row-select" id="list">
                            <caption>Pallet Transfer &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td rowspan="2" style="text-align: center">NS / DS</td>
                                    <td colspan="4" style="text-align: center">Product</td>
                                    <td colspan="4" style="text-align: center">Cut Out Evaluation</td>
                                    <td colspan="2" style="text-align: center">QA Disposition</td>
                                    <td rowspan="2" style="text-align: center">Action</td>
                                </tr>
                                <tr>
                                    <td>Date</td>
                                    <td>Production Batch</td>
                                    <td>Basket</td>
                                    <td>Quantity</td>
                                    <td>%Flk</td>
                                    <td>NW</td>
                                    <td>DW</td>
                                    <td>PW</td>
                                    <td>Release To</td>
                                    <td>Remarks</td>
                                </tr>
                            </thead>
                            <tbody id="detail">
                                <c:forEach items="${model.ptss}" var="x" varStatus="vs">
                                    <tr data-id="${x.ptsd_id}">
                                        <td>
                                            <select class="i0" data-value="${x.pts_shift}">
                                                <option value="DS">Day Shift</option>
                                                <option value="2ND">Second Shift</option>
                                                <option value="NS">Night Shift</option>
                                            </select>
                                        </td>
                                        <td><input type="hidden" id="date${vs.index + 1}" class="i1" value="${x.pts_pdate}"><input type="text" id="datePicker${vs.index + 1}" class="i1Picker" size="8" required></td>
                                        <td><input type="text" class="i2" size="11" value="${x.pts_pprodbatch}"></td>
                                        <td><input type="text" class="i3" size="11" value="${x.pts_pbasket}"></td>
                                        <td><input type="text" class="i4" size="5" value="${x.pts_pqty}"></td>
                                        <td><input type="text" class="i5" size="11" value="${x.pts_coeflk}"></td>
                                        <td><input type="text" class="i6" size="11" value="${x.pts_coenw}"></td>
                                        <td><input type="text" class="i7" size="11" value="${x.pts_coedw}"></td>
                                        <td><input type="text" class="i8" size="11" value="${x.pts_coepw}"></td>
                                        <td><input type="text" class="i9" size="11" value="${x.pts_qadreleaseto}"></td>
                                        <td><input type="text" class="i10" size="11" value="${x.pts_qadremarks}"></td>
                                        <td><input type="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="" style="font-size: smaller;"></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="12" style="text-align: right;">Quantity (Cs): <input type="text" id="ptsQty" name="ptsQty" value="${model.ptss[0].pts_total_qty}" readonly></td>
                                </tr>
                            </tfoot>
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

            //  BIND | Date Picker to pts date
            $('#ptsDate').val('${model.ptss[0].pts_date}');
            $("#ptsDatePicker").val(gnvs.util.toViewDate('${model.ptss[0].pts_date}'));//.datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#ptsDate", changeYear: true, changeMonth: true});

            //  BIND | Blur on detail quantity to calculate Cs value
            $(".i4").live("blur", function () {
                calculateCs();
            });
            function calculateCs() {
                var cs = 0.00;
                $(".i4:visible").each(function () {
                    if ($(this).val() >= 0) {
                        cs = cs + parseFloat($(this).val());
                    }
                });
                $("#ptsQty").val((cs).toFixed(2));
            }

            //  AUTOCOMPLETE | Item
//            $("#itemCode").autocomplete({
//                source: "?action=getItem",
//                minLength: 1,
//                select: function (event, ui) {
//                    $(this).val(ui.item[1]);
//                    $(this).attr('data-valid', true);
//                    return false;
//                }
//            }).data('autocomplete')._renderItem = function (ul, item) {
//                return $('<li>')
//                        .data("item", item)
//                        .append('<a><b>Item Code: ' + item[1] + ' | Can Size: ' + item[2] + '</b></a></li>')
//                        .appendTo(ul);
//            };

            //  AUTOCOMPLETE | Reff to bor dtl
            $("#borReference").autocomplete({
                source: "?action=getBor",
                minLength: 1,
                select: function (event, ui) {
                    $(this).val(ui.item[1]);
                    $(this).attr('data-valid', true);
                    return false;
                }
            }).data('autocomplete')._renderItem = function (ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a>' + item[1] + ' <b>(' + item[2] + ' | ' + item[3] + ')</b><br />Buyer: ' + item[4] + '</a></li>')
                        .appendTo(ul);
            };

            //  DETAIL | Add / remove detail input form
            $(".detailAdd").live("click", function () {
                addNewDetail(1);
            });
            $(".detailDelete").live("click", function () {
                if (confirm("Delete selected row?")) {
                    var $tr = $(this).parent().parent();

                    if ($tr.data('id') > 0) {
                        $tr.attr('data-status', 'D').hide();
                    } else {
                        $tr.remove();
                    }
                    calculateCs();
                }
            });

            //  FORM | Submit action
            $("#fPts").bind("submit", function () {
                var data = '', header = $('#ptsId').val() + ':s:' + $('#ptsCode').val() + ':s:' + $('#packStyle').val() + ':s:' + $('#borReference').val() + ':s:' + $('#ptsLocation').val() + ':s:' + $('#itemCode').val() + ':s:' + $('#ptsDate').val() + ':s:' + $('#canCode').val() + ':s:' + $('#forBrand').val() + ':s:' + $('#ptsQty').val() + ':s:' + $('#ptsStatus').val() + ':s:';

                $('#detail tr[data-status]').each(function () {
                    var input = $(this).data('status') + ':s:' + $(this).data('id') + ':s:';
                    for (var i = 0; i < 11; i++) {
                        input = input + $(this).find('.i' + i).val() + ':s:';
                    }
                    data = data + header + input + ':se:';
                });

                if (confirm('Update Pallet Transfer #' + $('#ptsCode').val() + ' ?')) {
                    if (data === '') {
                        data = header + 'X:s:-1:s::se:';
                    }
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function (json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });

            //  FUNCTION | Add detail input form
            var counter = 0, idx = -1;
            function addNewDetail(mode) {
                var detail = '<tr data-id="' + idx + '" data-status="C"><td><select class="i0"><option value="DS">Day Shift</option><option value="2ND">Second Shift</option><option value="NS">Night Shift</option></select></td>'
                        + '<td><input type="hidden" id="date' + counter + '" class="i1" /><input type="text" id="datePicker' + counter + '" class="i1Picker" size="8" required></td><td><input type="text" class="i2" size="11" /></td>'
                        + '<td><input type="text" class="i3" size="11" /></td><td><input type="text" class="i4" size="5" value="0" /></td><td><input type="text" class="i5" size="11" /></td>'
                        + '<td><input type="text" class="i6" size="11" /></td><td><input type="text" class="i7" size="11" /></td><td><input type="text" class="i8" size="11" /></td>'
                        + '<td><input type="text" class="i9" size="11" /></td><td><input type="text" class="i10" size="11" /></td><td><input type="button" class="detailAdd ui-button ui-widget ui-state-default ui-corner-all" value="Add" style="font-size: smaller;" /></td></tr>';
                $("#detail").append(detail);
                $("#datePicker" + counter).datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#date" + counter, changeYear: true, changeMonth: true});
                if (mode === 1) {
                    $(".detailAdd:first").removeClass("detailAdd").addClass("detailDelete").val("Remove");
                }

                counter = counter + 1;
                idx = idx - 1;
            }

            // UPDATE | data value
            $('#ptsStatus').val('${model.ptss[0].is_hold}').find('option:not(:selected)').remove();
            $('#packStyle').val('${model.ptss[0].pack_id}');
            $('#ptsLocation').val('${model.ptss[0].loca_id}');

            var trLength = $('#detail tr').length;
            $('#detail tr').each(function (i) {
                var $shift = $(this).find('.i0');
                $shift.val($shift.data('value'));

                var $datePicker = $(this).find('.i1Picker');
                if ($datePicker.prev().val() !== '') {
                    $datePicker.val(gnvs.util.toViewDate($datePicker.prev().val()))
                            .datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#date" + (i + 1), changeYear: true, changeMonth: true});
                }

                var $button = $(this).find('.ui-button');
                if ((i + 1) !== trLength) {
                    $button.addClass('detailDelete').val('Remove');
                } else {
                    $button.addClass('detailAdd').val('Add');
                }
            });

            // UPDATE | function
            $('#detail tr').find('input:not(:button), select').live('click', function () {
                var $tr = $(this).parent().parent();

                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            });

        </script>
    </body>
</html>

