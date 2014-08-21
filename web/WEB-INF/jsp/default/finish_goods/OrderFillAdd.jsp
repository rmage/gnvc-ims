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
                    <form action="#" id="fPts" name="fOfal" method="post">
                        <input type="hidden" id="ofalDate" name="ofalDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Order Fill Authority to Label &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Bor Code</td>
                                    <td><input type="text" class="bor-info" id="borId" name="borId" required="required" /></td>
                                    <td style="width: 200px;">OFAL Date</td>
                                    <td><input type="text" class="bor-info" id="ofalDatePicker" name="ofalDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td><b>Label Declaration</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Brand</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="lNw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Reference Number</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="lDw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Destination Port</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>BBE</td>
                                    <td><input type="text" id="lBbe" /> g</td>
                                </tr>
                                <tr>
                                    <td>Quantity</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td><b>Actual Specification</b></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td>Net Weight</td>
                                    <td><input type="text" id="aNw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" readonly="readonly"</td>
                                    <td>Drained Weight</td>
                                    <td><input type="text" id="aDw" /> g</td>
                                </tr>
                                <tr>
                                    <td>Max Code</td>
                                    <td><input type="text" class="bor-info" readonly="readonly"</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Shipment Schedule</td>
                                    <td><input type="text" id="shipment" name="shipment" readonly="readonly"</td>
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
                                <th colspan="10">
                                    <input type="text" id="ptsCode" name="ptsCode" />
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add" />
                                </th>
                            </tr>
                            <tr>
                                <td rowspan="2">Number</td>
                                <td rowspan="2">Pallet Number</td>
                                <td colspan="2">Production</td>
                                <td rowspan="2">Shift</td>
                                <td rowspan="2">Quantity</td>
                                <td colspan="3">Cut Out</td>
                                <td rowspan="2">Location</td>
                                <td rowspan="2">Remarks</td>
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
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                // PTS | Get data
                $.ajax({
                    url: "?", type: "post",
                    data: {action: "getPts", key: $("#ptsCode").val()},
                    dataType: "json",
                    success: function(json) {
                        
                    }
                });
                $('#load').remove();
            });

            <%--// BIND | Change event on pack style to get item on list
            $("#packStyle").bind("change", function() {
                $.ajax({
                    url: "?", type: "post",
                    data: {action: "getItem", key: $(this).val()},
                    dataType: "json",
                    success: function(json) {
                        $("#itemId").html("");
                        for (var i = 0; i < json.length; i++) {
                            $("#itemId").append('<option value="' + json[i][1] + '">' + json[i][3] + ' | ' + json[i][2] + '</option>');
                        }
                    }
                });
                calculateCs();
            }).trigger("change");

            //  BIND | Blur on detail quantity to calculate Cs value
            $(".i4").live("blur", function() {
                calculateCs();
            });
            function calculateCs() {
                var cs = 0.00;
                var divider = $("#packStyle option:selected").data("ppc");
                $(".i4").each(function() {
                    if ($(this).val() > 0) {
                        var io = $(this).val().indexOf(".");
                        if (io < 0) {
                            cs = cs + parseFloat($(this).val());
                        } else {
                            cs = cs + parseFloat($(this).val().substring(0, io)) + ($(this).val().substring(io + 1, $(this).val().length) / divider);
                        }
                    }
                });
                $("#ptsQty").val(cs.toFixed(2));
            }

            //  AUTOCOMPLETE | Reff to bor id
            $("#borId").autocomplete({
                source: "?action=getBor",
                minLength: 6,
                delay: 1000,
                select: function(event, ui) {
                    $(this).data("id", ui.item[1]);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item[2] + ' | ' + item[3] + '</b><br />Buyer: ' + item[4] + '</a></li>')
                        .appendTo(ul);
            };

            //  DETAIL | Add / remove detail input form
            $(".detailAdd").live("click", function() {
                addNewDetail(1);
            });
            $(".detailDelete").live("click", function() {
                if (confirm("Delete selected row?")) {
                    $(this).parent().parent().remove();
                    calculateCs();
                }
            });

            //  FORM | Submit action
            $("#fPts").bind("submit", function() {
                //  VALIDATION
                //  BOR | not selected from list
                if ($("#borId").val() !== "") {
                    if ($("#borId").data("id") === "" || $("#borId").data("id") === undefined) {
                        alert("Please select Bor Reff from LIST!");
                        return false;
                    }
                }

                var data = "";
                $(".i1").each(function() {
                    if ($(this).val() !== "" && $(this).val() !== undefined) {
                        var $r = $(this).parent().parent();
                        data = data + $("#ptsCode").val() + "," + $("#ptsDate").val() + "," + $("#packStyle").val() + ","
                                + $("#forBrand").val() + "," + $("#canCode").val() + "," + $("#borId").data("id") + ","
                                + $("#ptsLocation").val() + "," + $("#ptsQty").val() + "," + $r.find(".i0").val() + ","
                                + $r.find(".i1").val() + "," + $r.find(".i2").val() + "," + $r.find(".i3").val() + ","
                                + $r.find(".i4").val() + "," + $r.find(".i5").val() + "," + $r.find(".i6").val() + ","
                                + $r.find(".i7").val() + "," + $r.find(".i8").val() + "," + $r.find(".i9").val() + ","
                                + $r.find(".i10").val() + "," + $("#itemId").val() + ",";

                        data = data + "@";
                    }
                });
                console.log(data);
                if (data !== "") {
                    window.location.replace("?action=save&data=" + data);
                }

                return false;
            });

            //  FUNCTION | Add detail input form
            var counter = 0;
            function addNewDetail(mode) {
                var detail = '<tr><td><select class="i0"><option value="NS">Night Shift</option><option value="DS">Day Shift</option><option value="2ND">Second Shift</option></select></td>'
                        + '<td><input type="hidden" id="date' + counter + '" class="i1" /><input type="text" class="bor-info" id="datePicker' + counter + '" class="i1Picker" size="8" /></td><td><input type="text" class="bor-info" class="i2" size="11" /></td>'
                        + '<td><input type="text" class="bor-info" class="i3" size="11" /></td><td><input type="text" class="bor-info" class="i4" size="11" /></td><td><input type="text" class="bor-info" class="i5" size="11" /></td>'
                        + '<td><input type="text" class="bor-info" class="i6" size="11" /></td><td><input type="text" class="bor-info" class="i7" size="11" /></td><td><input type="text" class="bor-info" class="i8" size="11" /></td>'
                        + '<td><input type="text" class="bor-info" class="i9" size="11" /></td><td><input type="text" class="bor-info" class="i10" size="11" /></td><td><input type="button" class="detailAdd ui-button ui-widget ui-state-default ui-corner-all" value="Add" style="font-size: smaller;" /></td></tr>';
                $("#detail").append(detail);
                $("#datePicker" + counter).datepicker({
                    dateFormat: "dd/mm/yy",
                    altFormat: "yy-mm-dd",
                    altField: "#date" + counter,
                    changeYear: true,
                    changeMonth: true
                });
                if (mode === 1) {
                    $(".detailAdd:first").removeClass("detailAdd").addClass("detailDelete").val("Remove");
                }

                counter = counter + 1;
            }
            addNewDetail();
            --%>
        </script>
    </body>
</html>

