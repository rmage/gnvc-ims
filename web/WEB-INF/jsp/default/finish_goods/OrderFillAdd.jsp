<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create &therefore; Order Fill Authority to Label &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <link href="resources/default/css/style-table.css" rel="stylesheet" type="text/css">
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete { max-height: 250px; overflow-y: auto; /* prevent horizontal scrollbar */overflow-x: hidden; }
            .ui-datepicker { display: none; }
        </style>
    </head>
    <body onload="input.resetForm(this);">
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="fOfal" name="fOfal" method="post">
                        <input type="hidden" id="ofalDate" name="ofalDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Order Fill Authority to Label &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Reference Number</td>
                                    <td><input id="borReference" id="borReference" type="text" class="bor-info" size="30" tabindex="1" onblur="input.checkDataValid(this);" required></td>
                                    <td style="width: 200px;">OFAL Number</td>
                                    <td><input type="text" id="ofalCode" name="ofalCode" size="10" tabindex="2" required="required"></td>
                                </tr>
                                <tr>
                                    <td>Bor Number</td>
                                    <td><input type="text" class="bor-info" readonly></td>
                                    <td>OFAL Date</td>
                                    <td><input type="text" class="" id="ofalDatePicker" name="ofalDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" tabindex="3" required="required"></td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="bor-info" size="50" readonly></td>
                                    <td><b>Label Declaration</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td><input type="text" class="bor-info" readonly></td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="lNw" tabindex="4"></td>
                                </tr>
                                <tr>
                                    <td>Destination Port</td>
                                    <td><input type="text" class="bor-info" readonly></td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="lDw" tabindex="5"></td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td><input type="text" class="bor-info" readonly></td>
                                    <td>BBE</td>
                                    <td><input type="text" id="lBbe" tabindex="6"></td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td><input type="text" class="bor-info" readonly></td>
                                    <td><b>Actual Specification</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" size="50" tabindex="7"></td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="aNw" readonly></td>
                                </tr>
                                <tr>
                                    <td>Max Code</td>
                                    <td><input type="text" id="vMaxCOde" name="vMaxCOde" class="bor-info" size="10" readonly></td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="aDw" readonly></td>
                                </tr>
                                <tr>
                                    <td>Shipment Schedule</td>
                                    <td><input type="text" id="shipment" name="shipment" tabindex="8"></td>
                                    <td>Flakes</td>
                                    <td><input type="text" id="aFlk" name="aFlk" readonly></td>
                                </tr>
                                <tr>
                                    <td>Special Instruction</td>
                                    <td><input type="text" id="remarks" name="remarks" size="50" tabindex="9"></td>
                                    <td><b style="color: red;">Item Code</b></td>
                                    <td><input id="itemCode" name="itemCode" type="text" readonly></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
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
                        <tbody id="detail"></tbody>
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
            $("#ofalDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#ofalDate",
                changeYear: true,
                changeMonth: true
            });
            // BIND | Add PTS button
            $("#btnAdd").bind("click", function () {
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
                        success: function (json) {
                            if (json.length > 0) {
                                $("#detail").append('<tr data-id="' + json[0][0] + '">' +
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
                            } else {
                                alert('[Error] PTS Not found!');
                            }
                        },
                        complete: function () {
                            $('#load').remove();
                        }
                    });
                }
            });
            // BIND | Quantity maximum and minimum value
            $(".qtyCs").live("blur", function () {
                if ($(this).val() > $(this).data('max')) {
                    alert('[Error] Maximum number exceeded!');
                    $(this).val($(this).data('max'));
                }

                updateTotal();
            });
            // BIND | Reset form if bor change
            $("#borReference").bind("keyup", function () {
                if ($(this).data("reference")) {
                    $(".bor-info,#aNw,#aDw,#aFlk").each(function (i) {
                        $(this).val("");
                    });
                    $(this).data("reference", '');
                    $('#itemCode').val('');
                    $("#detail").html('');
                    updateTotal();
                }
            });
            // AUTOCOMPLETE | Get bor info
            $("#borReference").autocomplete({
                source: "?action=getBor",
                minLength: 1,
                select: function (event, ui) {
                    $(this).data("reference", ui.item[1]);
                    $(this).attr('data-valid', true);
                    $(".bor-info,#aNw,#aDw,#aFlk").each(function (i) {
                        $(this).val(ui.item[i + 3]);
                    });
                    $('#itemCode').val(ui.item[101]);

                    return false;
                }
            }).data('autocomplete')._renderItem = function (ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>BOR</b>: ' + item[4] + ' | ' + item[2] + '<br><b>Buyer/Brand</b>: ' + item[5] + '/' + item[6] + '<br><b>Item</b>: ' + item[101] + '</a></li>')
                        .appendTo(ul);
            };
            $("#fOfal").bind("submit", function () {
                var data = '', header = $('#ofalCode').val() + ':s:' + $('#borReference').val() + ':s:' + $('#ofalDate').val() + ':s:' + $('#canCode').val() + ':s:' + $('#lNw').val() + ':s:' + $('#lDw').val() + ':s:' + $('#lBbe').val() + ':s:' + $('#aNw').val() + ':s:' + $('#aDw').val() + ':s:' + $('#aFlk').val() + ':s:' + $('#shipment').val() + ':s:' + $('#remarks').val() + ':s:';

                $('#detail tr').each(function () {
                    if ($(this).find('input:eq(0)').val() > 0) {
                        data = data + header + $(this).data('id') + ':s:' + $(this).find('input:eq(0)').val() + ':s:' + $(this).find('input:eq(1)').val() + ':s::se:';
                    }
                });
                
                if (data !== '' && confirm('Save Order Fill Authority to Label #' + $('#ofalCode').val() + ' ?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function (json) {
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
                $(e).parent().parent().remove();
                $("#detail tr td:nth-child(1)").each(function (i) {
                    $(this).html(i + 1);
                });
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
                $('#detail tr td:nth-child(7)').each(function () {
                    var $i = $(this).find('input:eq(0)');
                    cs = cs + parseFloat($i.val());
                });
                $('#totalCs').val(cs.toFixed(2));
            }

        </script>
    </body>
</html>

