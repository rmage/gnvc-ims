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
        <title>IMS &therefore; Pallet Transfer &therefore; Create</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            /*            #fyaQPanelBody tr:nth-child(2n+1) {
                            background-color: #EEEEEE;
                        }*/
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
                    <form action="#" id="fPts" name="fPts" method="post">
                        <input type="hidden" id="ptsDate" name="ptsDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Transfer &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PTS Number</td>
                                    <td><input type="text" id="ptsCode" name="ptsCode" required="required" /></td>
                                    <td style="width: 200px;">PTS Date</td>
                                    <td><input type="text" id="ptsDatePicker" name="ptsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Pack Style / Size</td>
                                    <td>
                                        <select id="packStyle" name="packStyle">
                                            <c:forEach items="${ims.pss}" var="x">
                                                <option value="${x.pack_id}" data-ppc="${x.pack_per_cs}">${x.pack_style} | ${x.pack_size}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>For Brand</td>
                                    <td><input type="text" id="forBrand" name="forBrand" /></td>
                                </tr>
                                <tr>
                                    <td>Can Code</td>
                                    <td><input type="text" id="canCode" name="canCode" required="required" /></td>
                                    <td>Reff</td>
                                    <td><input type="text" id="borId" name="borId" data-id="0" /></td>
                                </tr>
                                <tr>
                                    <td>Item</td>
                                    <td>
                                        <select id="itemId" name="itemId"></select>
                                    </td>
                                    <td>Location</td>
                                    <td>
                                        <select id="ptsLocation" name="ptsLocation">
                                            <c:forEach items="${ims.ls}" var="x">
                                                <option value="${x.loca_id}">${x.loca_code} | ${x.loca_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletTransfer.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Pallet Transfer &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td rowspan="2" style="text-align: center">NS / DS</td>
                                <td colspan="4" style="text-align: center">Product</td>
                                <td colspan="4" style="text-align: center">Cut Out Evaluation</td>
                                <td colspan="2" style="text-align: center">QA Disposition</td>
                                <td rowspan="2" style="text-align: center">Action</td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td>Production Batch</td>
                                <td>Basket</td>
                                <td>Quantity</td>
                                <td>%Flk</td>
                                <td>NW</td>
                                <td>DW</td>
                                <td>PW</td>
                                <td>Release To</td>
                                <td>Remarks</td>
                            </tr>
                        </thead>
                        <tbody id="detail"></tbody>
                        <tfoot>
                            <tr>
                                <td colspan="12" style="text-align: right;">Quantity (Cs): <input type="text" id="ptsQty" name="ptsQty" value="0.00" readonly="readonly" /></td>
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

            //  BIND | Date Picker to pts date
            $("#ptsDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#ptsDate",
                changeYear: true,
                changeMonth: true
            });

            // BIND | Change event on pack style to get item on list
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
                var tin = 0.00;
                var divider = $("#packStyle option:selected").data("ppc");
                $(".i4").each(function() {
                    if ($(this).val() > 0) {
                        var io = $(this).val().indexOf(".");
                        if (io < 0) {
                            cs = cs + parseFloat($(this).val());
                        } else {
                            cs = cs + parseFloat($(this).val().substring(0, io));
                            tin = tin + parseFloat($(this).val().substring(io + 1, $(this).val().length));
                            //+ Math.floor(( / divider)) + 
                            //(($(this).val().substring(io + 1, $(this).val().length) % divider) / 100);
                        }
                    }
                });
                $("#ptsQty").val((cs + Math.floor(tin / divider) + ((tin % divider) / 100)).toFixed(2));
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
                //console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            //  FUNCTION | Add detail input form
            var counter = 0;
            function addNewDetail(mode) {
                var detail = '<tr><td><select class="i0"><option value="NS">Night Shift</option><option value="DS">Day Shift</option><option value="2ND">Second Shift</option></select></td>'
                        + '<td><input type="hidden" id="date' + counter + '" class="i1" /><input type="text" id="datePicker' + counter + '" class="i1Picker" size="8" /></td><td><input type="text" class="i2" size="11" /></td>'
                        + '<td><input type="text" class="i3" size="11" /></td><td><input type="text" class="i4" size="11" /></td><td><input type="text" class="i5" size="11" /></td>'
                        + '<td><input type="text" class="i6" size="11" /></td><td><input type="text" class="i7" size="11" /></td><td><input type="text" class="i8" size="11" /></td>'
                        + '<td><input type="text" class="i9" size="11" /></td><td><input type="text" class="i10" size="11" /></td><td><input type="button" class="detailAdd ui-button ui-widget ui-state-default ui-corner-all" value="Add" style="font-size: smaller;" /></td></tr>';
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
        </script>
    </body>
</html>

