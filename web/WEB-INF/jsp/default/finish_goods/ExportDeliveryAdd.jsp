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
        <title>IMS &therefore; Export Delivery &therefore; Create</title>
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
                    <form action="#" id="fExport" name="fExport" method="post">
                        <input type="hidden" id="edsDate" name="edsDate" value="<%=sdf.format(cDate)%>" />
                        <input type="hidden" id="itemPerCs" name="" value="" />
                        <table class="collapse tblForm row-select">
                            <caption>Export Delivery &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">EDS Code</td>
                                    <td><input type="text" id="edsCode" name="edsCode" required="required" /></td>
                                    <td style="width: 200px;">EDS Date</td>
                                    <td><input type="text" id="edsDatePicker" name="edsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required /></td>
                                </tr>
                                <tr>
                                    <td>VAN</td>
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
                                    <td>LMR Code</td>
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
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGExportDelivery.htm');" />
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
            $("#edsDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#edsDate",
                changeYear: true,
                changeMonth: true
            });

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
                            $(".lmr-info").each(function(i) {
                                $(this).val(json[0][i + 1]);
                            });

                            for (var i = 0; i < json.length; i++) {
                                $("#detail").append("<tr><td>" + json[i][6] + "</td><td>" + json[i][7] + "</td><td>" + json[i][8] +
                                        "</td><td>" + json[i][9] + "</td><td>" + json[i][10] + "</td></tr>");
                            }
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

            // BIND | Save function
            $("#fExport").bind("submit", function() {
                // VALIDATE | LMR has been selected
                if ($(".lmr-info:eq(1)").val() === '') {
                    return false;
                }

                var data = "";

                data = data + $("#edsCode").val() + "^" + $("#lmrCode").val() + "^" + $("#edsDate").val() + "^" + $("#edsVan").val() + "^" +
                        $("#edsPelayaranSeal").val() + "^" + $("#edsVessel").val() + "^" + $("#edsPlatNo").val() + "^" + $("#edsTimeIn").val() + "^" +
                        $("#edsTimeOut").val() + "^" + $("#edsDriverName").val() + "^" + $("#edsCi").val() + "^" + $("#edsRemarks").val() + "^@";

//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

        </script>
    </body>
</html>

