<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Rendering Sales &therefore; Create</title>
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
                            <caption>Rendering Sales &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Number</td>
                                    <td>
                                        <input type="text" id="code" name="code" required>
                                    </td>
                                    <td style="width: 200px;">Date</td>
                                    <td>
                                        <input type="text" id="datePicker" name="datePicker" size="10" required>
                                        <input type="button" id="view" value="View Stock">
                                    </td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td><input type="text" id="from" name="from" size="50" required></td>
                                    <td>Location</td>
                                    <td><input type="text" id="locationFrom" name="locationFrom" required></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td><input type="text" id="to" name="to" size="50" required></td>
                                    <td>Location</td>
                                    <td><input type="text" id="locationTo" name="locationTo" required></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="remarks" name="remarks" size="50"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('RenderingSales.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Rendering Sales &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td class="center" rowspan="2" style="width: 225px;">Description</td>
                                    <td class="center" rowspan="2">Value</td>
                                    <td class="center" colspan="3">Current Stock (kgs)</td>
                                </tr>
                                <tr>
                                    <td class="center">Fish Meal - Standard</td>
                                    <td class="center">Fish Meal - High Protein</td>
                                    <td class="center">Fish Oil</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Sales Type</td>
                                    <td>
                                        <select id="salesType" name="salesType" required>
                                            <option value="">--</option>
                                            <option value="0" data-max="0" data-uom="Bags" disabled>Fish Meal - Standard</option>
                                            <option value="1" data-max="0" data-uom="Bags" disabled>Fish Meal - High Protein</option>
                                            <option value="5" data-max="0" data-uom="Drums" disabled>Fish Oil</option>
                                        </select>
                                        <input type="text" id="qty" name="qty" value="0" size="3"> <span>0</span> kg(s)
                                    </td>
                                    <td class="right top bigger bold" id="stock0" rowspan="5">0</td>
                                    <td class="right top bigger bold" id="stock1" rowspan="5">0</td>
                                    <td class="right top bigger bold" id="stock2" rowspan="5">0</td>
                                </tr>
                                <tr>
                                    <td>Container Number</td>
                                    <td><input type="text" id="tegu" name="tegu" size="50"></td>
                                </tr>
                                <tr>
                                    <td>Seal Number</td>
                                    <td><input type="text" id="seal" name="seal" size="50"></td>
                                </tr>
                                <tr>
                                    <td>Plat Number</td>
                                    <td><input type="text" id="platNo" name="platNo" size="50"></td>
                                </tr>
                                <tr>
                                    <td>Vessel Name</td>
                                    <td><input type="text" id="vessel" name="vessel" size="50"></td>
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

            // BIND | to get data from current date
            $('#view').bind('click', function() {
                if ($('#date').val() !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: 'RenderingSales.htm', type: 'post',
                        data: {action: 'ajaxViewStock', date: $('#date').val()},
                        dataType: 'json',
                        success: function(json) {
                            if (json.col1 !== undefined) {
                                $('#datePicker').datepicker('disable');
                                $('#view').attr('disabled', 'disabled');

                                for (var i = 0; i < 3; i++) {
                                    if (json['col' + (i + 1)] > 0) {
                                        $('#salesType option:eq(' + (i + 1) + ')').data('max', json['col' + (i + 1)])
                                                .removeAttr('disabled');
                                    }
                                    $('#stock' + i).html(parseFloat(json['col' + (i + 1)]).toLocaleString());
                                }
                            }
                        },
                        complete: function() {
                            $('img#load').remove();
                        }
                    });
                }
            });

            // BIND | to sales type select option
            $('#salesType').bind('change', function() {
                var v = $(this).next().val(), t = 0;
                if ($(this).val() === '') {
                    $(this).next().val(0).next().html(0);
                } else if ($(this).val() === '0' || $(this).val() === '1') {
                    t = v * 40;
                } else if ($(this).val() === '5') {
                    t = v * 180;
                }
                if (t <= $(this).find('option:selected').data('max')) {
                    $(this).next().next().html(t.toLocaleString());
                } else {
                    $(this).next().next().html(0);
                }
            });

            // BIND | to quantity
            $('#qty').bind('keyup', function() {
                var v = $(this).val(), t = 0;
                if ($(this).prev().val() === '') {
                    $(this).val(0).next().html(0);
                } else if ($(this).prev().val() === '0' || $(this).prev().val() === '1') {
                    t = v * 40;
                } else if ($(this).prev().val() === '5') {
                    t = v * 180;
                }
                if (t <= $(this).prev().find('option:selected').data('max')) {
                    $(this).next().html(t.toLocaleString());
                } else {
                    $(this).next().html(0);
                }
            });

            // BIND | to form submission
            $('#fRend').bind('submit', function() {
                var data = '';

                // CHECK | if already trigger the view stock
                if ($('#view').attr('disabled') === 'disabled' && $('#qty').next().html() !== '0') {
                    data = data + $('#code').val() + '^' + $('#date').val() + '^' + $('#from').val() + '^'
                            + $('#to').val() + '^' + $('#locationFrom').val() + '^' + $('#locationTo').val() + '^'
                            + $('#salesType').val() + '^' + $('#salesType option:selected').html() + '^' + $('#qty').val() + '^'
                            + $('#qty').next().html().replace(/,/g, '') + '^' + $('#salesType option:selected').data('uom') + '^' + $('#tegu').val() + '^'
                            + $('#seal').val() + '^' + $('#platNo').val() + '^' + $('#vessel').val() + '^'
                            + $('#remarks').val() + '^@';
                }

                console.log(data);
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

