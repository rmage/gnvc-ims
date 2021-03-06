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
        <title>Create &therefore; Pallet Rejection &therefore; IMS</title>
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
                    <form action="#" id="fPalletRejection" name="fPalletRejection" method="post">
                        <input type="hidden" id="rejeDate" name="rejeDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Rejection &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Rejection Number</td>
                                    <td><input type="text" id="rejeCode" name="rejeCode" required="required"></td>
                                    <td>Rejection Date</td>
                                    <td><input type="text" id="rejeDatePicker" name="rejeDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Rejection Issue</td>
                                    <td><input type="text" id="rejeIssue" name="rejeIssue"></td>
                                    <td>Rejection Route</td>
                                    <td><input type="text" id="rejeRoute" name="rejeRoute" size="50"></td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td><input type="text" id="rejeCNumber" name="rejeCNumber"></td>
                                    <td>Date</td>
                                    <td><input type="text" id="rejeCDate" name="rejeCDate"></td>
                                </tr>
                                <tr>
                                    <td>Type / Reason</td>
                                    <td>
                                        <select id="rejeCType" name="rejeCType">
                                            <option>CONDEMNATION</option>
                                            <option>RENDERING</option>
                                            <option>REPROCESS</option>
                                        </select>
                                        <input type="text" id="rejeCReason" name="rejeCReason" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" />
                                    </td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="rejeCRemarks" name="rejeCRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletRejection.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>

                        <table class="collapse tblForm row-select" id="list">
                            <caption>Pallet Rejection &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <th>Pallet Number</th>
                                    <th colspan="7">
                                        <input type="text" id="ptsCode" name="ptsCode" required>
                                        <input type="button" id="btnSelect" name="btnSelect" value="Select Pallet">
                                    </th>
                                </tr>
                                <tr>
                                    <td style="width: 100px">Pack Style</td>
                                    <td>Pack Size</td>
                                    <td>Item Code</td>
                                    <td>Production Date</td>
                                    <td>Quantity</td>
                                </tr>
                            </thead>
                            <tbody id="detail"></tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">Total Quantity: <span id="ptsTotalQty">0</span></td>
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

            // BIND | date picker
            $("#rejeDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#rejeDate",
                changeYear: true,
                changeMonth: true
            });

//            // BIND | search pts button
            $("#btnSelect").bind("click", function() {
                var ptsCode = $('#ptsCode').val();
                $('tbody#detail').html('');

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: ptsCode},
                        dataType: "json",
                        success: function(json) {
                            if (json.length > 0) {
                                $('#ptsTotalQty').html(json[0][7]);
                                $('#ptsCode').data('id', json[0][1]);

                                for (var i = 0; i < json.length; i++) {
                                    $('tbody#detail').append('<tr>' +
                                            '<td>' + json[i][2] + '</td>' +
                                            '<td>' + json[i][3] + '</td>' +
                                            '<td>' + json[i][4] + '</td>' +
                                            '<td>' + json[i][5] + '</td>' +
                                            '<td>' + json[i][6] + '</td>' +
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

            // BIND | Save function
            $("#fPalletRejection").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                var data = $('#rejeCode').val() + ':s:' + $('#rejeDate').val() + ':s:' + $('#rejeIssue').val() + ':s:' +
                        $('#rejeRoute').val() + ':s:' + $('#rejeCNumber').val() + ':s:' + $('#rejeCDate').val() + ':s:' +
                        $('#ptsCode').data('id') + ':s:' + $('#rejeCType').val() + ':s:' + $('#rejeCReason').val() + ':s:' +
                        $('#rejeCRemarks').val() + ':s::se:';

                if (data !== '' && confirm('Save Pallet Rejection #' + $('#rejeCode').val() + ' ?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });

            // BIND | focus will remove existing selected pallet number
            $('#ptsCode').bind('focus', function() {
                $('tbody#detail').html('');
                $('#ptsTotalQty').html('');
            });

        </script>
    </body>
</html>

