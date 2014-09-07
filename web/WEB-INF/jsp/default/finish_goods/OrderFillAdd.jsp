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
        <title>IMS &therefore; Order Fill Authority to Label &therefore; Create</title>
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
                    <form action="#" id="fOfal" name="fOfal" method="post">
                        <input type="hidden" id="ofalDate" name="ofalDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Order Fill Authority to Label &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Bor Code</td>
                                    <td><input type="text" class="bor-info" id="borId" name="borId" required="required" data-id="" /></td>
                                    <td style="width: 200px;">OFAL Code</td>
                                    <td><input type="text" id="ofalCode" name="ofalCode" size="10" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>OFAL Date</td>
                                    <td><input type="text" class="" id="ofalDatePicker" name="ofalDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td><b>Label Declaration</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Reference Number</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="lNw" value="0" /> g</td>
                                </tr>
                                <tr>
                                    <td>Destination Port</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="lDw" value="0" /> g</td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>BBE</td>
                                    <td><input type="text" id="lBbe" value="0" /> g</td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td><b>Actual Specification</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" readonly="readonly"</td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="aNw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Max Code</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="aDw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Shipment Schedule</td>
                                    <td><input type="text" id="shipment" name="shipment"</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGOrderFill.htm');" />
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
                                    <input type="text" id="ptsCode" name="ptsCode" />
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add" />
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
                        <tfoot></tfoot>
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
            $("#btnAdd").bind("click", function() {
                if ($("#detail tr td:nth-child(2):contains('" + $("#ptsCode").val() + "')").size() > 0) {
                    return false;
                }
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                // PTS | Get data
                $.ajax({
                    url: "?", type: "post",
                    data: {action: "getPts", key: $("#ptsCode").val()},
                    dataType: "json",
                    success: function(json) {
                        if (json.length > 0) {
                            $("#detail").append('<tr><td>' + ($("#detail tr").size() + 1) + '</td><td>' + json[0][1] +
                                    '</td><td>' + json[0][2] + '</td><td>' + json[0][3] + '</td><td>' + json[0][4] +
                                    '</td><td>' + json[0][5] + '</td><td><input type="text" class="quantity" value="' + parseFloat(json[0][6]).toFixed(2) + '" size="6" data-max="' + parseFloat(json[0][6]).toFixed(2) + '" data-code="' + json[0][1] + '" /></td><td>' + parseFloat(json[0][7]).toFixed(2) +
                                    '</td><td>' + parseFloat(json[0][8]).toFixed(2) + '</td><td>' + parseFloat(json[0][9]).toFixed(2) + '</td><td>' + json[0][10] +
                                    '</td><td><input type="text" class="remarks" /></td><td><input type="button" value="Remove" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" onclick="removeRow(this)"></td></tr>');
                            $("#ptsCode").val("");
                            setCanCode();
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

            // BIND | Quantity maximu and minimum value
            $(".quantity").live("blur", function() {
                if (parseFloat($(this).val()) < 0.01 || parseFloat($(this).val()) > parseFloat($(this).data("max")) || !/^\d+(\.\d{1,2})?$/.test($(this).val())) {
                    $(this).val($(this).data("max"));
                }
            });

            // BIND | Reset form if bor change
            $("#borId").bind("keyup", function() {
                if ($(this).data("id") !== "") {
                    $(".bor-info,#aNw,#aDw").each(function(i) {
                        $(this).val("");
                    });
                    $(this).data("id", "");
                }
            });

            // AUTOCOMPLETE | Get bor info
            $("#borId").autocomplete({
                source: "?action=getBor",
                minLength: 6,
                delay: 1000,
                select: function(event, ui) {
                    $(this).data("id", ui.item[1]);
                    $(".bor-info,#aNw,#aDw").each(function(i) {
                        $(this).val(ui.item[i + 3]);
                    });
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item[3] + ' | ' + item[2] + '</b><br />Buyer: ' + item[4] + '</a></li>')
                        .appendTo(ul);
            };

            $("#fOfal").bind("submit", function() {
                //  VALIDATION
                //  BOR | not selected from list
                if ($("#borId").val() !== "") {
                    if ($("#borId").data("id") === "" || $("#borId").data("id") === undefined) {
                        alert("Please select Bor Reff from LIST!");
                        return false;
                    }
                }

                var data = "";

                var $x = $(".quantity");
                var $y = $(".remarks");

                for (var i = 0; i < $x.size(); i++) {
                    data = data + $("#ofalCode").val() + "," + $("#borId").data("id") + "," + $("#ofalDate").val() + "," + $("#canCode").val() + "," +
                            $("#lNw").val() + "," + $("#lDw").val() + "," + $("#lBbe").val() + "," + $("#shipment").val() + "," + $x[i].getAttribute("data-code") + "," +
                            $x[i].value + "," + $y[i].value + ",@";
                }
//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            // FUNCTION | Remove row
            function removeRow(e) {
                $(e).parent().parent().remove();
                $("#detail tr td:nth-child(1)").each(function(i) {
                    $(this).html(i + 1);
                });
                setCanCode();
            }

            // FUNCTION | SEt can code
            function setCanCode() {
                $("#canCode").val("");
                $("#detail tr td:nth-child(3)").each(function() {
                    if ($("#canCode").val().indexOf($(this).html()) === -1) {
                        $("#canCode").val($("#canCode").val() + $(this).html() + ";");
                    }
                });
            }

        </script>
    </body>
</html>

