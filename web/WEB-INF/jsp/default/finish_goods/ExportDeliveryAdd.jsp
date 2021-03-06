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
        <title>Create &therefore; Export Delivery &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
                    <form action="#" id="fExport" name="fExport" method="post">
                        <input type="hidden" id="edsDate" name="edsDate" value="<%=sdf.format(cDate)%>" />
                        <input type="hidden" id="itemPerCs" name="" value="" />
                        <table class="collapse tblForm row-select">
                            <caption>Export Delivery &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">EDS Number</td>
                                    <td><input type="text" id="edsCode" name="edsCode" required="required" /></td>
                                    <td style="width: 200px;">EDS Date</td>
                                    <td><input type="text" id="edsDatePicker" name="edsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required /></td>
                                </tr>
                                <tr>
                                    <td>Container Number</td>
                                    <td><input type="text" id="edsVan" name="edsVan" size="50" /></td>
                                    <td>Seal Number</td>
                                    <td><input type="text" id="edsPelayaranSeal" name="edsPelayaranSeal" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>Vessel</td>
                                    <td><input type="text" id="edsVessel" name="edsVessel" size="50" /></td>
                                    <td>Plat Number</td>
                                    <td><input type="text" id="edsPlatNo" name="edsPlatNo" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>Time</td>
                                    <td>
                                        In: <input type="text" id="edsTimeIn" name="edsTimeIn" size="5" />
                                        Out: <input type="text" id="edsTimeOut" name="edsTimeOut" size="5" />
                                    </td>
                                    <td>Name of Driver</td>
                                    <td><input type="text" id="edsDriverName" name="edsDriverName" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>CI Number</td>
                                    <td><input type="text" id="edsCi" name="edsCi" /></td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="edsRemarks" name="edsRemarks" size="50" /></td>
                                </tr>
                                <tr>
                                    <td>LM Number</td>
                                    <td colspan="3">
                                        <input type="text" id="lmrCode" name="lmrCode" class="lmr-info" required />
                                        <input type="button" id="btnSearch" name="btnSearch" value="Search" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="lmr-info" size="50" readonly /></td>
                                    <td>Brand</td>
                                    <td><input type="text" class="lmr-info" size="50" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Reference</td>
                                    <td><input type="text" class="lmr-info" size="50" readonly /></td>
                                    <td>Destination</td>
                                    <td><input type="text" class="lmr-info" size="50" readonly /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGExportDelivery.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Export Delivery &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td>Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Can Code</td>
                                <td>Number of Cases</td>

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
            $("#edsDatePicker").datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#edsDate", changeYear: true, changeMonth: true, onSelect: function(dateText, inst) { gnvs.validator.backDate($(this), dateText, inst); }});

            // BIND | Remove all previous data if keyup in OFAL Code
            $("#lmrCode").bind("keyup", function() {
                if ($(".lmr-info:eq(1)").val() !== '') {
                    $(".lmr-info").val("");
                    $("#list > #detail").html("");
                }
            });

            // BIND | Search OFAL button
            $("#btnSearch").bind("click", function() {
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: "?", type: "post",
                    data: {action: "getLabelingMonitoring", key: $("#lmrCode").val()},
                    dataType: "json",
                    success: function(json) {
                        // DATA | Set header infor
                        if (json.length > 0) {
                            $("#detail").html('');
                            $(".lmr-info").each(function(i) {
                                $(this).val(json[0][i + 2]);
                            });

                            var sAppend = [], cAppend = 0;
                            for (var i = 0; i < json.length; i++) {
                                sAppend[cAppend++] = '<tr><td>';
                                sAppend[cAppend++] = json[i][7];
                                sAppend[cAppend++] = '</td><td>';
                                sAppend[cAppend++] = json[i][8];
                                sAppend[cAppend++] = '</td><td>';
                                sAppend[cAppend++] = json[i][9];
                                sAppend[cAppend++] = '</td><td>';
                                sAppend[cAppend++] = json[i][10];
                                sAppend[cAppend++] = '</td><td>';
                                sAppend[cAppend++] = json[i][11];
                                sAppend[cAppend++] = '</td></tr>';
                            }
                            $("#detail").append(sAppend.join(''));
                            $("#lmrCode").data('id', json[0][1]);
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

            // BIND | Save function
            $("#fExport").bind("submit", function() {
                var data = '', header = $('#edsCode').val() + ':s:' + $('#lmrCode').data('id') + ':s:' + $('#edsDate').val() + ':s:' + $('#edsVan').val() + ':s:' + $('#edsPelayaranSeal').val() + ':s:' + $('#edsVessel').val() + ':s:' + $('#edsPlatNo').val() + ':s:' + $('#edsTimeIn').val() + ':s:' + $('#edsTimeOut').val() + ':s:' + $('#edsDriverName').val() + ':s:' + $('#edsCi').val() + ':s:' + $('#edsRemarks').val() + ':s:';

                if ($('#detail tr').length > 0) {
                    data = header + ':se:';
                }

                if (data !== '' && confirm('Save Export Delivery #' + $('#edsCode').val() + ' ?')) {
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
//                // VALIDATE | LMR has been selected
//                if ($(".lmr-info:eq(1)").val() === '') {
//                    return false;
//                }
//
//                var data = "";
//
//                data = data + $('#edsCode').val() + ':s:' + $('#lmrCode').val() + ':s:' + $('#edsDate').val() + ':s:' + $('#edsVan').val() + ':s:' +
//                        $('#edsPelayaranSeal').val() + ':s:' + $('#edsVessel').val() + ':s:' + $('#edsPlatNo').val() + ':s:' + $('#edsTimeIn').val() + ':s:' +
//                        $('#edsTimeOut').val() + ':s:' + $('#edsDriverName').val() + ':s:' + $('#edsCi').val() + ':s:' + $('#edsRemarks').val() + ':s:';
//
////                console.log(data);
//                if (data !== "") {
//                    if (confirm("Continue to save this document?")) {
//                        window.location.replace("?action=save&data=" + data);
//                    }
//                }
//
//                return false;
            });

        </script>
    </body>
</html>

