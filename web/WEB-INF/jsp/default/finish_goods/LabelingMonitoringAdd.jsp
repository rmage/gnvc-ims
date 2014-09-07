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
        <title>IMS &therefore; Labeling Monitoring &therefore; Create</title>
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
                    <form action="#" id="fLabeling" name="fLabeling" method="post">
                        <input type="hidden" id="lmDate" name="lmDate" value="<%=sdf.format(cDate)%>" />
                        <input type="hidden" id="itemPerCs" name="" value="" />
                        <table class="collapse tblForm row-select">
                            <caption>Labeling Monitoring &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">LM Code</td>
                                    <td><input type="text" id="lmCode" name="lmCode" required="required" /></td>
                                    <td style="width: 200px;">LM Date</td>
                                    <td><input type="text" id="lmDatePicker" name="lmDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10"" required /></td>
                                </tr>
                                <tr>
                                    <td>OFAL Code</td>
                                    <td colspan="3">
                                        <input type="text" id="ofalCode" name="ofalCode" required />
                                        <input type="button" id="btnSearch" name="btnSearch" value="Search" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /></td>
                                    <td>Buyer</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /></td>
                                    <td>Reference</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /></td>
                                </tr>
                                <tr>
                                    <td>Destination</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /></td>
                                    <td>Quantity Order</td>
                                    <td><input type="text" class="ofal-info" size="50" readonly /> CS</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGLabelingMonitoring.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Labeling Monitoring &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td rowspan="2">Pallet Number</td>
                                <td colspan="2">Production</td>
                                <td rowspan="2">Batch</td>
                                <td rowspan="2">Quantity Served</td>
                                <td rowspan="2">Good Stock Labeled</td>
                                <td colspan="2">Reclassified due to</td>
                                <td rowspan="2">Remaining</td>
                                <td rowspan="2">Remarks</td>
                            </tr>
                            <tr>
                                <td>Code</td>
                                <td>Date</td>
                                <td>Dented</td>
                                <td>Rusty</td>
                            </tr>
                        </thead>
                        <tbody id="detail"></tbody>
                        <tfoot>
                            <tr>
                                <td>Flipper P# No</td>
                                <td colspan="9">
                                    <input type="text" class="lmr-data" /> /
                                    <input type="text" class="lmr-data" />
                                </td>
                            </tr>
                            <tr>
                                <td>Bulger</td>
                                <td colspan="9">
                                    <input type="text" class="lmr-data" /> /
                                    <input type="text" class="lmr-data" />
                                </td>
                            </tr>
                            <tr>
                                <td>False Seam</td>
                                <td colspan="9">
                                    <input type="text" class="lmr-data" /> /
                                    <input type="text" class="lmr-data" />
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
            $("#lmDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#lmDate",
                changeYear: true,
                changeMonth: true
            });

            // BIND | Remove all previous data if keyup in OFAL Code
            $("#ofalCode").bind("keyup", function() {
                $(".ofal-info").val("");
                $("#list > #detail").html("");
            });

            // BIND | Search OFAL button
            $("#btnSearch").bind("click", function() {
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: "?", type: "post",
                    data: {action: "getOfal", key: $("#ofalCode").val()},
                    dataType: "json",
                    success: function(json) {
                        // DATA | Set header infor
                        $(".ofal-info").each(function(i) {
                            $(this).val(json[0][i + 1]);
                        });

                        // DATA | Set item per cartons
                        $("#itemPerCs").val(json[0][7]);

                        for (var i = 0; i < json.length; i++) {
                            $("#detail").append('<tr data-id="' + json[i][12] + '"><td>' + json[i][8] + '</td><td>' + json[i][9] + '</td><td>' + json[i][10] + '</td><td><input type="text" value="" size="10" /></td><td><input type="text" class="qty-served" value="' + json[i][11] + '" data-max="' + json[i][11] + '" size="10" /></td>' +
                                    '<td><input type="text" class="lmQty" value="0" size="10" /></td><td><input type="text" value="0" size="10" /></td><td><input type="text" value="0" size="10" /></td><td id="remainings">0</td><td><input type="text" value="" /></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

            // BIND | Quantity maximum and minimum value
            $(".qty-served").live("blur", function() {
                if (parseFloat($(this).val()) < 0.01 || parseFloat($(this).val()) > parseFloat($(this).data("max")) || !/^\d+(\.\d{1,2})?$/.test($(this).val())) {
                    $(this).val($(this).data("max"));
                }
            });

            // BIND | Remaining quantity
            $("#detail td:nth-child(5) > input,#detail td:nth-child(6) > input,#detail td:nth-child(7) > input,#detail td:nth-child(8) > input").live("blur", function() {
                var m = parseFloat($("#itemPerCs").val()),
                        $tr = $(this).parent().parent();

                var h = csToTin($tr.find("td:eq(4) > input").val(), m),
                        i = csToTin($tr.find("td:eq(5) > input").val(), m),
                        j = csToTin($tr.find("td:eq(6) > input").val(), m),
                        k = csToTin($tr.find("td:eq(7) > input").val(), m);


                $tr.find("td:eq(8)").html(tinToCs(h - i - j - k, m).toFixed(2));
            });

            // BIND | Save function
            $("#fLabeling").bind("submit", function() {
                var data = "",
                        lmQty = 0.00,
                        m = parseFloat($("#itemPerCs").val()),
                        lmData = "";

                // DATA | Prepare total quantity data
                $(".lmQty").each(function() {
                    lmQty = lmQty + csToTin($(this).val(), m);
                });

                // DATA | Prepare lmData
                $(".lmr-data").each(function() {
                    lmData = lmData + $(this).val() + "^";
                });

                $("#detail tr").each(function() {
                    data = data + $("#lmCode").val() + "^" + $("#ofalCode").val() + "^" + $("#lmDate").val() + "^" + tinToCs(lmQty, m) + "^" +
                            lmData + $(this).data("id") + "^" + $(this).find("td:eq(3) > input").val() + "^" +
                            $(this).find("td:eq(4) > input").val() + "^" + $(this).find("td:eq(5) > input").val() + "^" + $(this).find("td:eq(6) > input").val() + "^" + $(this).find("td:eq(7) > input").val() + "^" +
                            $(this).find("td:eq(8)").html() + "^" + $(this).find("td:eq(9) > input").val() + "^@";
                });
//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            // FUNCTION | Cs to Tin conversation
            function csToTin(v, m) {
                var cs = 0.00;
                var tin = 0.00;
                var io = v.indexOf(".");

                if (io < 0) {
                    return (v * m);
                } else {
                    cs = parseFloat(v.substring(0, io));
                    tin = parseFloat(v.substring(io + 1, v.length));
                    return (cs * m) + tin;
                }
            }

            function tinToCs(v, m) {
                var cs = Math.floor(v / m);
                return cs + ((v % m) / 100);
            }

        </script>
    </body>
</html>

