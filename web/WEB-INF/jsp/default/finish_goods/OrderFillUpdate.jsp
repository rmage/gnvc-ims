<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Order Fill Authority to Label &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <link href="resources/default/css/style-table.css" rel="stylesheet" type="text/css">
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete { max-height: 250px; overflow-y: auto; /* prevent horizontal scrollbar */overflow-x: hidden; }
            .ui-datepicker { display: none; }
        </style>
    </head>
    <body onload="updateTotal();">
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="fOfal" name="fOfal" method="post">
                        <input id="ofalId" name="ofalId" type="hidden" value="${model.ofals[0].ofal_id}">
                        <input type="hidden" id="ofalDate" name="ofalDate" value="${model.ofals[0].ofal_date}">
                        <input type="hidden" id="itemCode" name="itemCode" value="${model.ofals[0].item_code}">
                        <table class="collapse tblForm row-select">
                            <caption>Order Fill Authority to Label &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Reference Number</td>
                                    <td><input id="borReference" id="borReference" type="text" class="bor-info" size="30" tabindex="1" value="${model.ofals[0].bor_reference}" readonly></td>
                                    <td style="width: 200px;">OFAL Number</td>
                                    <td><input type="text" id="ofalCode" name="ofalCode" size="10" tabindex="2" value="${model.ofals[0].ofal_code}" required></td>
                                </tr>
                                <tr>
                                    <td>Bor Number</td>
                                    <td><input type="text" class="bor-info" value="${model.ofals[0].bor_number}" readonly></td>
                                    <td>OFAL Date</td>
                                    <td><input type="text" class="" id="ofalDatePicker" name="ofalDatePicker" value="" size="10" tabindex="3" required></td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="bor-info" size="50" value="${model.ofals[0].buyer_name}" readonly></td>
                                    <td><b>Label Declaration</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td><input type="text" class="bor-info" value="${model.ofals[0].brand_name}" readonly></td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="lNw" tabindex="4" value="${model.ofals[0].ofal_lnw}"></td>
                                </tr>
                                <tr>
                                    <td>Destination Port</td>
                                    <td><input type="text" class="bor-info" value="${model.ofals[0].bor_destination}" readonly></td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="lDw" tabindex="5" value="${model.ofals[0].ofal_ldw}"></td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td><input type="text" class="bor-info" value="${model.ofals[0].bor_case}" readonly></td>
                                    <td>BBE</td>
                                    <td><input type="text" id="lBbe" tabindex="6" value="${model.ofals[0].ofal_lbbe}"></td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td><input type="text" class="bor-info" value="${model.ofals[0].pack_size}" readonly></td>
                                    <td><b>Actual Specification</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" size="50" tabindex="7" value="${model.ofals[0].ofal_cancode}"></td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="aNw" value="${model.ofals[0].bor_anw}" readonly></td>
                                </tr>
                                <tr>
                                    <td>Max Code</td>
                                    <td><input type="text" id="vMaxCode" name="vMaxCode" class="bor-info" size="10" value="${model.ofals[0].bor_maxcancode}" readonly></td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="aDw" value="${model.ofals[0].bor_adw}" readonly></td>
                                </tr>
                                <tr>
                                    <td>Shipment Schedule</td>
                                    <td><input type="text" id="shipment" name="shipment" tabindex="8" value="${model.ofals[0].ofal_shipment}"></td>
                                    <td>Flakes</td>
                                    <td><input type="text" id="aFlk" name="aFlk" value="${model.ofals[0].bor_flakes}" readonly></td>
                                </tr>
                                <tr>
                                    <td>Special Instruction</td>
                                    <td colspan="3"><input type="text" id="remarks" name="remarks" size="50" tabindex="9" value="${model.ofals[0].ofal_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGOrderFill.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Order Fill Authority to Label &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th style="width: 75px">PTS Number</th>
                                <th colspan="12">
                                    <input type="text" id="ptsCode" name="ptsCode" tabindex="10" />
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add" tabindex="11" />
                                </th>
                            </tr>
                            <tr>
                                <td rowspan="2">Number</td>
                                <td rowspan="2">Pallet Number</td>
                                <td rowspan="2">Can Code</td>
                                <td colspan="2">Production</td>
                                <td rowspan="2">Shift</td>
                                <td rowspan="2">Quantity</td>
                                <td colspan="3">Cut Out</td>
                                <td rowspan="2">Location</td>
                                <td rowspan="2">Remarks</td>
                                <td rowspan="2">Action</td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td>Batch</td>
                                <td>Net Weight</td>
                                <td>Drained Weight</td>
                                <td>Flakes</td>

                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.ofals}" var="x" varStatus="vs">
                                <tr data-id="${x.ofald_id}" data-pts="${x.pts_id}">
                                    <td>${vss.index + 1}</td>
                                    <td>${x.pts_code}</td>
                                    <td>${x.pts_can_code}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td class="bg-gray-1"><input type="text" class="qtyCs" value="${x.ofal_qty}" data-max="${x.sc_cqty}"></td>
                                    <td class="bg-green-1">${x.pts_coenw}</td>
                                    <td class="bg-yellow-1">${x.pts_coedw}</td>
                                    <td class="bg-red-1">${x.pts_coeflk}</td>
                                    <td>${x.loca_name}</td>
                                    <td><input type="text" class="remarks" value="${x.ofald_remarks}"></td>
                                    <td><input type="button" value="Remove" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" onclick="if (confirm('Continue to remove this data?')) { removeRow(this); }"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="13" class="right">
                                    Total Cs
                                    <input type="text" id="totalCs" name="totalCs" value="0" size="10" readonly>
                                </td>
                            </tr>
                        </tfoot>
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
            $("#ofalDatePicker").datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#ofalDate", changeYear: true, changeMonth: true})
                    .val(gnvs.util.toViewDate($('#ofalDate').val()));

            // BIND | Add PTS button
            var idx = -1;
            $("#btnAdd").bind("click", function() {
                // VALIDATE | no same pts number in list
                var ptsCode = $("#ptsCode").val();
                if ($("#detail tr td:nth-child(2):containsCI('" + ptsCode + "')").size() > 0) {
                    alert('[Error] Pallet Transfer Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | before pick bor id
                if ($('#itemCode').val() === '') {
                    alert('[Error] Please select approriate Booked Order Report first!');
                    return false;
                }

                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    // PTS | Get data
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPts", pts: ptsCode, item: $('#itemCode').val()},
                        dataType: "json",
                        success: function(json) {
                            if (json.length > 0) {
                                $("#detail").append('<tr data-id="' + idx + '" data-pts="' + json[0][0] + '" data-status="C">' +
                                        '<td>' + ($("#detail tr").size() + 1) + '</td>' +
                                        '<td>' + json[0][1] + '</td>' +
                                        '<td>' + json[0][2] + '</td>' +
                                        '<td>' + json[0][3] + '</td>' +
                                        '<td>' + json[0][4] + '</td>' +
                                        '<td>' + json[0][5] + '</td>' +
                                        '<td class="bg-gray-1"><input type="text" class="qtyCs" value="' + json[0][6] + '" data-max="' + json[0][6] + '"></td>' +
                                        '<td class="bg-green-1">' + json[0][7] + '</td>' +
                                        '<td class="bg-yellow-1">' + json[0][8] + '</td>' +
                                        '<td class="bg-red-1">' + json[0][9] + '</td>' +
                                        '<td>' + json[0][10] + '</td>' +
                                        '<td><input type="text" class="remarks" /></td>' +
                                        '<td><input type="button" value="Remove" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" onclick="removeRow(this);"></td>' +
                                        '</tr>');
                                $("#ptsCode").val("");
//                                setCanCode();
                                updateTotal();
                                $('#ptsCode').focus();
                                
                                idx = idx - 1;
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                        }
                    });
                }
            });
            // BIND | Quantity maximum and minimum value
            $(".qtyCs").live("blur", function() {
                if ($(this).val() > $(this).data('max')) {
                    alert('[Error] Maximum number exceeded!');
                    $(this).val($(this).data('max'));
                }

                updateTotal();
            });
            
            $("#fOfal").bind("submit", function() {
                // VALIDATE | minimum one row on detail
                if ($('#detail tr:visible').length === 0) {
                    alert('Minimum one Pallet Transfer on Order Fill detail');
                    return false;
                }
                
                var data = '', header = $('#ofalId').val() + ':s:' + $('#ofalCode').val() + ':s:' + $('#borReference').val() + ':s:' + $('#ofalDate').val() + ':s:' + $('#canCode').val() + ':s:' + $('#lNw').val() + ':s:' + $('#lDw').val() + ':s:' + $('#lBbe').val() + ':s:' + $('#aNw').val() + ':s:' + $('#aDw').val() + ':s:' + $('#aFlk').val() + ':s:' + $('#shipment').val() + ':s:' + $('#remarks').val() + ':s:';

                $('#detail tr[data-status]').each(function() {
                    if ($(this).find('input:eq(0)').val() > 0) {
                        data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).data('pts') + ':s:' + $(this).find('input:eq(0)').val() + ':s:' + $(this).find('input:eq(1)').val() + ':s::se:';
                    }
                });

                if (confirm('Update Order Fill Authority to Label #' + $('#ofalCode').val() + ' ?')) {
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
            // FUNCTION | remove row
            function removeRow(e) {
                var $tr = $(e).parent().parent();
                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'D').hide();
                } else {
                    $tr.remove();
                }
                
                gnvs.util.reNumbering($('#detail'), 1);
//                $("#detail tr td:nth-child(1)").each(function(i) {
//                    $(this).html(i + 1);
//                });
                updateTotal();
//                setCanCode();
            }

            // FUNCTION | set can code
//            function setCanCode() {
//                $("#canCode").val("");
//                $("#detail tr td:nth-child(3)").each(function() {
//                    if ((parseInt($('#vMaxCOde').val()) > ($('#canCode').val().split(';').length - 1))) {
//                        if ($("#canCode").val().indexOf($(this).html()) === -1) {
//                            $("#canCode").val($("#canCode").val() + $(this).html() + ";");
//                        }
//                    } else {
//                        alert('[Error] Maximum CAN CODE reached!');
//                        $(this).parent().remove();
//                    }
//                });
//            }

            // FUNCTION | to set total
            function updateTotal() {
                var cs = 0;
                $('#detail tr:visible td:nth-child(7)').each(function() {
                    var $i = $(this).find('input:eq(0)');
                    cs = cs + parseFloat($i.val());
                });
                $('#totalCs').val(cs);
            }
            
            // UPDATE | function
            $('#detail tr input[type="text"]').live('keyup', function() {
                var $tr = $(this).parent().parent();
                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            });

        </script>
    </body>
</html>

