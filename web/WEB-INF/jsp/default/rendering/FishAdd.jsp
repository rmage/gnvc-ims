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
        <title>IMS &therefore; Rendering Fish &therefore; Create</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            /*:-moz-ui-invalid:not(output) { box-shadow: none; }*/
            .ui-datepicker {
                display: none;
            }
            input[type="number"] {
                background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
                border-color: #999999;
                border-image: none;
                border-style: none none solid;
                border-width: medium medium 1px;
                font-size: 10px;
                padding: 5px;
                width: 70px;
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
                    <form action="#" method="post">
                        <input type="hidden" id="date" name="date">
                        <table class="collapse tblForm row-select">
                            <caption>Rendering Fish &therefore; Detail</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Date</td>
                                    <td><input type="text" id="datePicker" name="datePicker" size="10"></td>
                                    <td style="width: 200px;">Second Pass</td>
                                    <td>
                                        Sack: <input type="number" min="0" step="0.01" value="0" required>
                                        Kg: <span>0</span>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('RenderingFish.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Rendering Fish &therefore; Process</caption>
                            <thead>
                                <tr>
                                    <td rowspan="3">Shift</td>
                                    <td rowspan="3">Raw Fish</td>
                                    <td rowspan="3">%</td>
                                    <td colspan="7">Tricanter, kgs</td>
                                    <td colspan="6">Direct Rendering, kgs</td>
                                    <td rowspan="2">Total Scraps</td>
                                    <td rowspan="2">%</td>
                                    <td colspan="2" rowspan="2">Total Fishmeal</td>
                                    <td rowspan="2">%</td>
                                </tr>
                                <tr>
                                    <td rowspan="2">Weight of Scrap</td>
                                    <td rowspan="2">%</td>
                                    <td colspan="3">Fishmeal</td>
                                    <td>Fish Oil</td>
                                    <td rowspan="2">% Recovery</td>
                                    <td rowspan="2">Weight of Scrap</td>
                                    <td colspan="2">First Pass</td>
                                    <td colspan="2">Second Pass</td>
                                    <td rowspan="2">% Recovery</td>
                                </tr>
                                <tr>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td>%</td>
                                    <td>Kg</td>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td>Kg</td>
                                    <td></td>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td></td>
                                </tr>
                            </thead>
                            <tbody id="process">
                                <tr>
                                    <td class="data bold">DS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold">2nd</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold">NS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold">Buy</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold">Total</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select" style="float: left; width: 325px; margin-right: 10px;">
                            <caption>Rendering Fish &therefore; Man</caption>
                            <thead>
                                <tr>
                                    <td>Shift</td>
                                    <td>Manpower</td>
                                    <td>Quantity (BIN)</td>
                                    <td colspan="2">Operator</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="bold">DS</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="text" size="5"></td>
                                    <td><input class="data" type="text" size="5"></td>
                                </tr>
                                <tr>
                                    <td class="bold">2nd</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="text" size="5"></td>
                                    <td><input class="data" type="text" size="5"></td>
                                </tr>
                                <tr>
                                    <td class="bold">NS</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="text" size="5"></td>
                                    <td><input class="data" type="text" size="5"></td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select" style="float: left; width: 350px;">
                            <caption>Rendering Fish &therefore; Breakdown</caption>
                            <thead>
                                <tr>
                                    <td rowspan="2">Shift</td>
                                    <td colspan="2">Breakdown Size</td>
                                    <td rowspan="2">Fresh</td>
                                    <td rowspan="2">Frozen</td>
                                </tr>
                                <tr>
                                    <td>&lt;1.0kg</td>
                                    <td>&gt;1.0kg</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>&percnt;</td>
                                    <td>&percnt;</td>
                                    <td>&percnt;</td>
                                    <td>&percnt;</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="bold data">DS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                </tr>
                                <tr>
                                    <td class="bold data">2nd</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                </tr>
                                <tr>
                                    <td class="bold data">NS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="1" required></td>
                                    <td class="data">100.00</td>
                                </tr>
                                <tr>
                                    <td class="bold data">Total</td>
                                    <td class="data">0</td>
                                    <td class="data">100.00</td>
                                    <td class="data">100</td>
                                    <td class="data">100.00</td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select" style="float: right; width: 575px;">
                            <caption>Rendering Fish &therefore; Stock Inventory</caption>
                            <thead>
                                <tr>
                                    <td rowspan="3">Inventory</td>
                                    <td colspan="5">Fishmeal</td>
                                    <td colspan="2">Fish Oil</td>
                                </tr>
                                <tr>
                                    <td colspan="2">Standard</td>
                                    <td colspan="3">High Protein</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td>Sack</td>
                                    <td>Kg</td>
                                    <td>&percnt;</td>
                                    <td>Drum</td>
                                    <td>Kg</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="bold data">Beginning</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">-</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold data">Today</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">-</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold data">Available</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">-</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold data">Sales</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">-</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
                                <tr>
                                    <td class="bold data">Ending</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                    <td class="data">-</td>
                                    <td class="data">0</td>
                                    <td class="data">0</td>
                                </tr>
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

            // BIND | Date Picker to ofal date
            $("#datePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#date",
                changeYear: true,
                changeMonth: true
            });
            
            // FUNCTION | to support calculation
            // BIND | to raw fish
            $('#process tr td:nth-child(2) > input').bind('keyup', function() {
                // GET | total and show it in Total field
                var t = 0.00;
                var $i = $('#process tr td:nth-child(2) > input');
                $i.each(function() {
                    t = t + parseFloat($(this).val());
                });
                $('#process tr:last-child() td:eq(1)').html(t.toFixed(2));
                
                // CALCULATE | percentage
                var p = 0.00;
                $i.each(function() {
                    var x = (parseFloat($(this).val()) / t) * 100;
                    $(this).parent().next().html(x.toFixed(2));
                    p = p + x;
                });
                $('#process tr:last-child() td:eq(2)').html(p.toFixed(0));
            });
            
            // BIND | to tricanter weight of scrap
            $('#process tr td:nth-child(4) > input').bind('keyup', function() {
                // GET | total and show it in Total field
                var t = 0.00;
                var $i = $('#process tr td:nth-child(4) > input');
                $i.each(function() {
                    t = t + parseFloat($(this).val());
                });
                $('#process tr:last-child() td:eq(3)').html(t.toFixed(2));
                
                // CALCULATE | percentage
                $i.each(function() {
                    var x = (parseFloat($(this).val()) / parseFloat($(this).parent().prev().prev().find('input').val())) * 100;
                    $(this).parent().next().html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                var $td = $('#process tr:last-child() td:eq(4)');
                var x = (t / parseFloat($td.prev().prev().prev().html())) * 100;
                $td.html(isNaN(x) ? '0.00' : x.toFixed(2));
            });
            
            // TRIGGER | binding events
            $('#process tr:eq(0) input:eq(0),#process tr:eq(0) input:eq(1)').trigger('keyup');
//
//            // BIND | Search EDS button
//            $("#btnAdd").bind("click", function() {
//                var edsCode = $('#edsCode').val();
//
//                // VALIDATE | export delivery slip is empty
//                if (edsCode !== '') {
//                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
//                    $.ajax({
//                        url: "?", type: "post",
//                        data: {action: "getPalletTransfer", key: edsCode},
//                        dataType: "json",
//                        success: function(json) {
//                            $('tbody#detail').html('');
//                            if (json.length > 0) {
//                                // HEADER | value
//                                $('#edsCode').val(json[0][1]);
//                                $('#rrFrom').val(json[0][2]);
//
//                                for (var i = 0; i < json.length; i++) {
//                                    var qtyReturn = json[i][7].split('.');
//                                    $('tbody#detail').append('<tr data-item="' + json[i][101] + '" data-ppc="' + json[i][102] + '">' +
//                                            '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
//                                            '<td>' + json[i][3] + '</td>' +
//                                            '<td>' + json[i][4] + '</td>' +
//                                            '<td>' + json[i][5] + '</td>' +
//                                            '<td>' + json[i][6] + '</td>' +
//                                            '<td>' + json[i][7] + '</td>' +
//                                            '<td><input type="text" class="cs" value="0" size="6" data-max="' + parseInt(qtyReturn[0]) + '"><input type="text" class="tin" value="0" size="2" data-max="' + parseInt(qtyReturn[1]) + '"></td>' +
//                                            '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td>' +
//                                            '</tr>');
//                                }
//                            }
//                        },
//                        complete: function() {
//                            $('#load').remove();
//                        }
//                    });
//                }
//            });
//
//            // BIND | Save
//            $("#fReturnCargo").bind("submit", function() {
//                // VALIDATE | Pallet has been added
//                if ($("tbody#detail tr").length <= 0) {
//                    alert('[Error] No pallet number added? Try select EDS.');
//                    return false;
//                }
//
//                // VALIDATE | 0 (zero) value in quantity
//                if ($('tbody#detail input.cs')
//                        .filter(function() {
//                            return parseInt($(this).val()) <= 0 && parseInt($(this).next().val()) <= 0;
//                        }).length > 0) {
//                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
//                    return false;
//                }
//
//                var data = "";
//
//                var header = $('#rrCode').val() + '^' + $('#rrDate').val() + '^' + $('#edsCode').val() + '^' + $('#rrFrom').val() + '^' + $('#rrRemarks').val() + '^' + $('#isReplace').val() + '^';
//
//                $('tbody#detail tr').each(function() {
//                    data = data + header + $(this).find('td:eq(3)').html() + '^' + 
//                            $(this).data('item') + '^' + 
//                            (parseFloat($(this).find('.cs').val()) + (parseInt($(this).find('.tin').val()) / 100)) + '^@';
//                });
//
////                console.log(data);
//                if (data !== "") {
//                    if (confirm("Continue to save this document?")) {
//                        window.location.replace("?action=save&data=" + data);
//                    }
//                }
//
//                return false;
//            });
//
//            // LIVE | validate maximum quantity
//            $('tbody#detail tr input').live('blur', function() {
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

        </script>
    </body>
</html>

