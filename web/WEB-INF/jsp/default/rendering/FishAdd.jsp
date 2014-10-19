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
                    <form id="fRend" action="#" method="post">
                        <input type="hidden" id="date" name="date">
                        <table class="collapse tblForm row-select">
                            <caption>Rendering Fish &therefore; Detail</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Date</td>
                                    <td>
                                        <input type="text" id="datePicker" name="datePicker" size="10" required>
                                        <input type="button" id="prepareData" value="Prepare">
                                    </td>
                                    <td style="width: 200px;">Second Pass</td>
                                    <td>
                                        Sack: <span class="bold" id="spSack">0</span>
                                        Kg: <span class="bold">0</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><textarea id="remarks" style="height: 80px; width: 1000px;"></textarea></td>
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
                                    <td colspan="8">Direct Rendering, kgs</td>
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
                                    <td rowspan="2">%</td>
                                    <td rowspan="2">Weight of Scrap</td>
                                    <td colspan="2">First Pass</td>
                                    <td colspan="2">High Protein</td>
                                    <td colspan="2">Second Pass</td>
                                    <td rowspan="2">%</td>
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
                            <tbody id="man">
                                <tr>
                                    <td class="bold">DS</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="0.1" style="width: 30px;" required></td>
                                    <td><input class="data" type="text" size="5"></td>
                                    <td><input class="data" type="text" size="5"></td>
                                </tr>
                                <tr>
                                    <td class="bold">2nd</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="0.1" style="width: 30px;" required></td>
                                    <td><input class="data" type="text" size="5"></td>
                                    <td><input class="data" type="text" size="5"></td>
                                </tr>
                                <tr>
                                    <td class="bold">NS</td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="1" style="width: 30px;" required></td>
                                    <td><input class="data" type="number" size="5" value="0" min="0" step="0.1" style="width: 30px;" required></td>
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
                            <tbody id="breakdown">
                                <tr>
                                    <td class="bold data">DS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">100.00</td>
                                </tr>
                                <tr>
                                    <td class="bold data">2nd</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">100.00</td>
                                </tr>
                                <tr>
                                    <td class="bold data">NS</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
                                    <td class="data">100.00</td>
                                    <td class="data"><input type="number" value="0" min="0" step="0.01" required></td>
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
                            <tbody id="stock">
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
                                    <td class="data">0</td>
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
            var $pLastTr = $('#process tr:last-child()');
            var $bLastTr = $('#breakdown tr:last-child()');

            // BIND | to raw fish
            $('#process tr td:nth-child(2) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to raw fish");
                // GET | total and show it in Total field
                var t = 0.00;
                var $i = $('#process tr td:nth-child(2) > input');
                $i.each(function() {
                    t = t + parseFloat($(this).val());
                });
                $pLastTr.find('td:eq(1)').html(t.toFixed(2));

                // CALCULATE | percentage
                var p = 0.00;
                $i.each(function() {
                    var x = (parseFloat($(this).val()) / t) * 100;
                    $(this).parent().next().html(isNaN(x) ? '0.00' : x.toFixed(2));
                    p = p + x;
                });
                $pLastTr.find('td:eq(2)').html(isNaN(p) ? '0' : p.toFixed(0));
            });

            // BIND | to tricanter weight of scrap
            $('#process tr td:nth-child(4) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to tricanter weight of scrap");
                // GET | total and show it in Total field
                var t = 0.00;
                var $i = $('#process tr td:nth-child(4) > input');
                $i.each(function() {
                    t = t + parseFloat($(this).val());
                });
                $pLastTr.find('td:eq(3)').html(t.toFixed(2));

                // CALCULATE | percentage
                $i.each(function() {
                    var x = (parseFloat($(this).val()) / parseFloat($(this).parent().prev().prev().find('input').val())) * 100;
                    $(this).parent().next().html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                var $td = $pLastTr.find('td:eq(4)');
                var x = (t / parseFloat($td.prev().prev().prev().html())) * 100;
                $td.html(isNaN(x) ? '0.00' : x.toFixed(2));
                updateTotalScraps();
            });

            // BIND | to tricanter fishmeal
            $('#process tr td:nth-child(6) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to tricanter fishmeal");
                // GET | total, sack and show it in field
                var t = 0.00, tk = 0.00, s = 0.00, x = 0.00, wos = 0.00;
                var $i = $('#process tr td:nth-child(6) > input');
                $i.each(function() {
                    t = t + parseFloat($(this).val());
                    s = (parseFloat($(this).val()) * 40);
                    tk = tk + s;
                    $(this).parent().next().html(s.toFixed(2));
                    wos = $(this).parent().prev().prev().find('input').val();
                    x = s / wos * 100;
                    $(this).parent().next().next().html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                $pLastTr.find('td:eq(5)').html(t.toFixed(2));
                $pLastTr.find('td:eq(6)').html(tk.toFixed(0));
                var $td = $pLastTr.find('td:eq(7)');
                x = tk / $td.parent().find('td:eq(3)').html() * 100;
                $td.html(isNaN(x) ? '0.00' : x.toFixed(2));
                updateTotalFM();
            });

            // BIND | to tricanter fish oil
            $('#process tr td:nth-child(9) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to tricanter fish oil");
                var t = 0.00, x = 0.00, wos = 0.00,
                        $i = $('#process tr td:nth-child(9) > input');
                $i.each(function() {
                    x = parseFloat($(this).val());
                    t = t + x;
                    wos = $(this).parent().parent().find('td:eq(3) input').val();
                    x = (x * 100 / wos);
                    $(this).parent().next().html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                $pLastTr.find('td:eq(8)').html(t.toFixed(2));
                x = t / $pLastTr.find('td:eq(3)').html() * 100;
                $pLastTr.find('td:eq(9)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                updateTotalFM();
            });

            // BIND | to direct rendering weight of scrap
            $('#process tr td:nth-child(11) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to direct rendering weight of scrap");
                var t = 0,
                        $i = $('#process tr td:nth-child(11) > input');

                $i.each(function() {
                    t = t + parseFloat($(this).val());
                });
                $pLastTr.find('td:eq(10)').html(t.toFixed(0));
                updateTotalScraps();
            });

            // BIND | to direct rendering sack from first pass
            $('#process tr td:nth-child(12) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to direct rendering sack from first pass");
                var t = 0, tk = 0, wos = 0, x = 0,
                        $i = $('#process tr td:nth-child(12) > input');

                $i.each(function() {
                    t = t + parseFloat($(this).val());
                    tk = tk + ($(this).val() * 40);
                    $(this).parent().next().html(($(this).val() * 40).toFixed(0));
                    wos = parseFloat($(this).parent().prev().children().val());
                    x = ((parseFloat($(this).val()) + parseFloat($(this).parent().next().next().children().val())) * 40) * 100 / wos;
                    $(this).parent().parent().find('td:eq(17)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                $pLastTr.find('td:eq(11)').html(t.toFixed(2));
                $pLastTr.find('td:eq(12)').html(tk.toFixed(0));
                x = ((tk + parseFloat($pLastTr.find('td:eq(14)').html())) * 100 / $pLastTr.find('td:eq(10)').html());
                $pLastTr.find('td:eq(17)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                updateTotalFM();
            });

            // BIND | to direct rendering sack from high protein
            $('#process tr td:nth-child(14) > input').bind('keyup change', function() {
                console.log("[EVENTS| BIND | to direct rendering sack from high protein");
                var t = 0, tk = 0, wos = 0, x = 0,
                        $i = $('#process tr td:nth-child(14) > input');

                $i.each(function() {
                    t = t + parseFloat($(this).val());
                    tk = tk + ($(this).val() * 40);
                    $(this).parent().next().html(($(this).val() * 40).toFixed(0));
                    wos = parseFloat($(this).parent().parent().find('td:eq(10) input').val());
                    x = ((parseFloat($(this).val()) + parseFloat($(this).parent().prev().prev().children().val())) * 40) * 100 / wos;
                    $(this).parent().parent().find('td:eq(17)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                $pLastTr.find('td:eq(13)').html(t.toFixed(2));
                $pLastTr.find('td:eq(14)').html(tk.toFixed(0));
                x = (tk + parseFloat($pLastTr.find('td:eq(12)').html())) * 100 / $pLastTr.find('td:eq(10)').html();
                $pLastTr.find('td:eq(17)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                updateTotalFM();
            });

            // BIND | direct rendering sack from second pass
            $('#process tr td:nth-child(16) > input').bind('keyup change', function() {
                var tSacks = 0, tKgs = 0;

                // UPDATE | kilograms
                $(this).parent().next().html(($(this).val() * 40).toFixed(2));

                $('#process tr:not(:last-child) td:nth-child(16)').each(function() {
                    if (parseFloat($(this).children().val()) !== 0 && parseFloat($(this).next().html()) === 0) {
                        $(this).next().html((parseFloat($(this).children().val()) * 40).toFixed(2));
                    }

                    tSacks = tSacks + parseFloat($(this).children().val());
                    tKgs = tKgs + parseFloat($(this).next().html());
                });

                // UPDATE | total sack and kilogram and summary second pass
                $pLastTr.find('td:eq(15)').html(tSacks.toFixed(2)).next().html(tKgs.toFixed(2));
                $('#spSack').html(tSacks.toFixed(2)).next().html(tKgs.toFixed(2));
            });

            /* -------------------------------------------------------------- */

            // BIND | to breakdown size
            $('#breakdown tr td:nth-child(2) > input').bind('keyup change', function() {
                var t = 0.00, c = 0, x = 0;
                $('#breakdown tr td:nth-child(2) > input').each(function() {
                    if (!$(this).prop('readonly')) {
                        $(this).parent().next().html((100.00 - $(this).val()).toFixed(2));
                        t = t + parseFloat($(this).val());
                        c = c + 1;
                    }

                    x = (t / c);
                    $bLastTr.find('td:eq(1)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                    x = (100 - (t / c));
                    $bLastTr.find('td:eq(2)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
            });

            // BIND | to breakdown fresh/frozen
            $('#breakdown tr td:nth-child(4) > input').bind('keyup change', function() {
                var t = 0.00, c = 0, x = 0;
                $('#breakdown tr td:nth-child(4) > input').each(function() {
                    if (!$(this).prop('readonly')) {
                        $(this).parent().next().html((100.00 - $(this).val()).toFixed(2));
                        t = t + parseFloat($(this).val());
                        c = c + 1;
                    }

                    x = (t / c);
                    $bLastTr.find('td:eq(3)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                    x = (100 - (t / c));
                    $bLastTr.find('td:eq(4)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
            });

            // BIND | to NS shift to set disable or enable field
            $('#breakdown input').bind('dblclick', function() {
                if ($(this).prop('readonly')) {
                    $(this).removeAttr('readonly')
                            .css('opacity', 1).trigger('keyup');
                } else {
                    $(this).attr('readonly', 'readonly').val(0).css('opacity', 0.3).trigger('keyup')
                            .parent().next().html(0);
                }
            });

            /* -------------------------------------------------------------- */

            function dataRecalculate() {
                console.log("[EVENTS| dataRecalculate");
                $('#process tr:eq(0) input:lt(8)').trigger('keyup');
                $('#breakdown tr:eq(0) input:lt(2)').trigger('keyup');
                $('#spSack').trigger('keyup');

                setTimeout(function() {
                    if ($('#stock tr:eq(0) td:eq(1)').html() === '0') {
                        $('#prepareData').trigger('click');
                    }
                }, 1000);
            }

            function updateTotalScraps() {
                console.log("[EVENTS| updateTotalScraps");
                $('#process tr').each(function(i) {
                    var x = 0;

                    // TRIGGER | fish meal sack to recalculate any changes
                    $(this).find('td:eq(5) > input,td:eq(11) > input,td:eq(13) > input').trigger('keyup');

                    if (i !== 4) {
                        x = parseFloat($(this).find('td:eq(3) > input').val()) + parseFloat($(this).find('td:eq(10) > input').val());
                    } else {
                        x = parseFloat($(this).find('td:eq(3)').html()) + parseFloat($(this).find('td:eq(10)').html());
                    }
                    $(this).find('td:eq(18)').html(x.toFixed(2));

                    if (i !== 4) {
                        x = x * 100 / $(this).find('td:eq(1) > input').val();
                    } else {
                        x = x * 100 / $(this).find('td:eq(1)').html();
                    }
                    $(this).find('td:eq(19)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });
                updateTotalFM();
            }

            function updateTotalFM() {
                console.log("[EVENTS| updateTotalFM");
                var x = 0, fo = 0, hp = 0;
                $('#process tr').each(function(i) {
                    if (i !== 4) {
                        x = parseFloat($(this).find('td:eq(5) > input').val()) + parseFloat($(this).find('td:eq(11) > input').val()) + parseFloat($(this).find('td:eq(13) > input').val());
                    } else {
                        x = parseFloat($(this).find('td:eq(5)').html()) + parseFloat($(this).find('td:eq(11)').html()) + parseFloat($(this).find('td:eq(13)').html());
                        fo = parseFloat($(this).find('td:eq(8)').html());
                    }
                    $(this).find('td:eq(20)').html(x.toFixed(2));

                    x = parseFloat($(this).find('td:eq(6)').html()) + parseFloat($(this).find('td:eq(12)').html()) + parseFloat($(this).find('td:eq(14)').html());
                    $(this).find('td:eq(21)').html(x.toFixed(0));

                    x = x * 100 / $(this).find('td:eq(18)').html();
                    $(this).find('td:eq(22)').html(isNaN(x) ? '0.00' : x.toFixed(2));
                });

                x = parseFloat($pLastTr.find('td:eq(11)').html()) + parseFloat($pLastTr.find('td:eq(5)').html());
                hp = $pLastTr.find('td:eq(13)').html();
                fo = $pLastTr.find('td:eq(8)').html();
                $('#stock tr:eq(1) > td:eq(1)').html(x)
                        .next().html(x * 40)
                        .next().html(hp)
                        .next().html(hp * 40)
                        .next().next().html((fo / 180).toFixed(2))
                        .next().html(fo);

                updateEnding();
            }

            function updateEnding() {
                var qty = new Array(0, 0, 0, 0, 0, 0),
                        $s = $('#stock');

                qty[0] = qty[0] + parseFloat($s.find('tr:eq(0) td:eq(1)').html()) + parseFloat($s.find('tr:eq(1) td:eq(1)').html());
                qty[1] = qty[1] + parseFloat($s.find('tr:eq(0) td:eq(2)').html()) + parseFloat($s.find('tr:eq(1) td:eq(2)').html());
                qty[2] = qty[2] + parseFloat($s.find('tr:eq(0) td:eq(3)').html()) + parseFloat($s.find('tr:eq(1) td:eq(3)').html());
                qty[3] = qty[3] + parseFloat($s.find('tr:eq(0) td:eq(4)').html()) + parseFloat($s.find('tr:eq(1) td:eq(4)').html());
                qty[4] = qty[4] + parseFloat($s.find('tr:eq(0) td:eq(6)').html()) + parseFloat($s.find('tr:eq(1) td:eq(6)').html());
                qty[5] = qty[5] + parseFloat($s.find('tr:eq(0) td:eq(7)').html()) + parseFloat($s.find('tr:eq(1) td:eq(7)').html());

                for (var i = 0; i < qty.length; i++) {
                    if (i < 4) {
                        $s.find('tr:eq(2) td:eq(' + (i + 1) + ')').html(qty[i].toFixed(2));
                    } else {
                        $s.find('tr:eq(2) td:eq(' + (i + 2) + ')').html(qty[i].toFixed(2));
                    }
                }

                qty[0] = qty[0] - parseFloat($s.find('tr:eq(3) td:eq(1)').html());
                qty[1] = qty[1] - parseFloat($s.find('tr:eq(3) td:eq(2)').html());
                qty[2] = qty[2] - parseFloat($s.find('tr:eq(3) td:eq(3)').html());
                qty[3] = qty[3] - parseFloat($s.find('tr:eq(3) td:eq(4)').html());
                qty[4] = qty[4] - parseFloat($s.find('tr:eq(3) td:eq(6)').html());
                qty[5] = qty[5] - parseFloat($s.find('tr:eq(3) td:eq(7)').html());

                for (var i = 0; i < qty.length; i++) {
                    if (i < 4) {
                        $s.find('tr:last-child td:eq(' + (i + 1) + ')').html(qty[i].toFixed(2));
                    } else {
                        $s.find('tr:last-child td:eq(' + (i + 2) + ')').html(qty[i].toFixed(2));
                    }
                }
            }

            // TRIGGER | binding events
            dataRecalculate();

            $('#prepareData').bind('click', function() {
                if ($('#date').val() !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: 'RenderingFish.htm', type: 'post',
                        data: {action: 'ajaxPrepareData', date: $('#date').val()},
                        dataType: 'json',
                        success: function(json) {
                            if (json.status > 0) {
                                $('#datePicker').datepicker('disable');
                                $('#prepareData').attr('disabled', 'disabled');

                                $('#stock tr:first-child td:eq(1)').html(json[1])
                                        .next().html(json[2])
                                        .next().html(json[3])
                                        .next().html(json[4])
                                        .next().next().html(json[5])
                                        .next().html(json[6]);

                                updateTotalFM();
                            } else {
                                alert('[Error] Please select another date!');
                            }
                        },
                        complete: function() {
                            $('img#load').remove();
                        }
                    });
                }
            });

            $('#fRend').bind('submit', function() {
                var data = '';
                if ($('#prepareData').attr('disabled') === 'disabled') {
                    // PREPARE | data for master fish meal and fish oil
                    data = $('#date').val() + '^' + $('#spSack').html() + '^' + $('#spSack').next().html() + '^@*';

                    // PREPARE | data for master remark
                    var remarks = $('#remarks').val().split(/\n/);
                    for (var i = 0; i < remarks.length; i++) {
                        data = data + remarks[i] + '^@';
                    }
                    data = data + '*';

                    // PREPARE | data for man
                    $('#man tr').each(function() {
                        data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1) > input').val() + '^' + $(this).find('td:eq(2) > input').val() + '^'
                                + $(this).find('td:eq(3) > input').val() + '^' + $(this).find('td:eq(4) > input').val() + '^@';
                    });
                    data = data + '*';

                    // PREPARE | data for breakdown
                    $('#breakdown tr').each(function(i) {
                        if (i !== 3) {
                            data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1) > input').val() + '^' + $(this).find('td:eq(2)').html() + '^'
                                    + $(this).find('td:eq(3) > input').val() + '^' + $(this).find('td:eq(4)').html() + '^@';
                        } else {
                            data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1)').html() + '^' + $(this).find('td:eq(2)').html() + '^'
                                    + $(this).find('td:eq(3)').html() + '^' + $(this).find('td:eq(4)').html() + '^@';
                        }
                    });
                    data = data + '*';

                    // PREPARE | data for stock
                    $('#stock tr').each(function(i) {
                        data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1)').html() + '^'
                                + $(this).find('td:eq(2)').html() + '^' + $(this).find('td:eq(3)').html() + '^' + $(this).find('td:eq(4)').html() + '^'
                                + $(this).find('td:eq(5)').html() + '^' + $(this).find('td:eq(6)').html() + '^' + $(this).find('td:eq(7)').html() + '^@';
                    });
                    data = data + '*';

                    // PREPARE | data for process
                    $('#process tr').each(function(i) {
                        if (i <= 3) {
                            data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1) > input').val() + '^'
                                    + $(this).find('td:eq(3) > input').val() + '^' + $(this).find('td:eq(5) > input').val() + '^' + $(this).find('td:eq(8) > input').val() + '^'
                                    + $(this).find('td:eq(10) > input').val() + '^' + $(this).find('td:eq(11) > input').val() + '^' + $(this).find('td:eq(13) > input').val() + '^'
                                    + $(this).find('td:eq(15) > input').val() + '^@';
                        } else {
                            data = data + $(this).find('td:eq(0)').html() + '^' + $(this).find('td:eq(1)').html() + '^'
                                    + $(this).find('td:eq(3)').html() + '^' + $(this).find('td:eq(5)').html() + '^' + $(this).find('td:eq(8)').html() + '^'
                                    + $(this).find('td:eq(10)').html() + '^' + $(this).find('td:eq(11)').html() + '^' + $(this).find('td:eq(13)').html() + '^'
                                    + $(this).find('td:eq(15)').html() + '^@';
                        }
                    });
                    data = data + '*#';
                }

                console.log(encodeURIComponent(data));
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + encodeURIComponent(data));
                    }
                }

                return false;
            });
        </script>
    </body>
</html>

