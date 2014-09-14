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
        <title>IMS &therefore; Return Cargo &therefore; Create</title>
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
                    <form action="#" id="fReturnCargo" name="fReturnCargo" method="post">
                        <input type="hidden" id="rrDate" name="rrDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Return Cargo &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Return Cargo Number</td>
                                    <td><input type="text" id="rrCode" name="rrCode" required="required"></td>
                                    <td>Return Cargo Date</td>
                                    <td><input type="text" id="rrDatePicker" name="rrDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>EDS Number</td>
                                    <td>
                                        <input type="text" id="edsCode" name="edsCode" size="10">
                                        <input type="button" id="btnAdd" name="btnAdd" value="Select EDS">
                                    </td>
                                    <td>From</td>
                                    <td><input type="text" id="rrFrom" name="rrFrom" size="50"></td>
                                </tr>
                                <tr>
                                    <td>Replace Items?</td>
                                    <td>
                                        <select id="isReplace" name="isReplace">
                                            <option value="N">No</option>
                                            <option value="Y">Yes</option>
                                        </select>
                                    </td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="rrRemarks" name="rrRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGReturnCargo.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Return Cargo &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Pallet Number</td>
                                <td>Item Name</td>
                                <td>Quantity</td>
                                <td>Returned Quantity</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                        <tbody id="detail"></tbody>
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
            $("#rrDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#rrDate",
                changeYear: true,
                changeMonth: true
            });

            // BIND | Search EDS button
            $("#btnAdd").bind("click", function() {
                var edsCode = $('#edsCode').val();

                // VALIDATE | export delivery slip is empty
                if (edsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: edsCode},
                        dataType: "json",
                        success: function(json) {
                            $('tbody#detail').html('');
                            if (json.length > 0) {
                                // HEADER | value
                                $('#edsCode').val(json[0][1]);
                                $('#rrFrom').val(json[0][2]);

                                for (var i = 0; i < json.length; i++) {
                                    $('tbody#detail').append('<tr data-item="' + json[i][101] + '">' +
                                            '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                            '<td>' + json[i][3] + '</td>' +
                                            '<td>' + json[i][4] + '</td>' +
                                            '<td>' + json[i][5] + '</td>' +
                                            '<td>' + json[i][6] + '</td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input type="text" value="' + json[i][7] + '" size="8" data-max="' + json[i][7] + '"></td>' +
                                            '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td>' +
                                            '</tr>');
                                }
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                        }
                    });
                }
            });

            // BIND | Save
            $("#fReturnCargo").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try select EDS.');
                    return false;
                }

                // VALIDATE | 0 (zero) value in quantity
                if ($('tbody#detail tr input').filter(function() {
                    return parseFloat($(this).val()) <= 0;
                }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "";

                var header = $('#rrCode').val() + '^' + $('#rrDate').val() + '^' + $('#edsCode').val() + '^' + $('#rrFrom').val() + '^' + $('#rrRemarks').val() + '^' + $('#isReplace').val() + '^';

                $('tbody#detail tr').each(function() {
                    data = data + header + $(this).find('td:eq(3)').html() + '^' + $(this).data('item') + '^' + $(this).find('input').val() + '^@';
                });

//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            // LIVE | validate maximum quantity
            $('tbody#detail tr input').live('blur', function() {
                if (parseFloat($(this).val()) > parseFloat($(this).data('max'))) {
                    $(this).val($(this).data('max'));
                }
            });

        </script>
    </body>
</html>

