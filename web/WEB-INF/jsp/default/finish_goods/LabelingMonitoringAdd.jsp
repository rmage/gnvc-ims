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
            #list thead td {
                width: 60px;
            }
            #detail td:nth-child(n+5) input:nth-child(2n+1) {
                width: 25px;
            }
            #detail td:nth-child(n+5) input:nth-child(2n) {
                width: 15px;
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
                                <td colspan="3">Bad Stocks</td>
                                <td rowspan="2">Remaining</td>
                                <td colspan="3">Others</td>
                                <td rowspan="2">Remarks</td>
                            </tr>
                            <tr>
                                <td>Code</td>
                                <td>Date</td>
                                <td>Dented</td>
                                <td>Rusty</td>
                                <td>Flipper</td>
                                <td>Bulger</td>
                                <td>False Seam</td>
                                <td>No Code</td>
                                <td>Blurred Code</td>
                                <td>Lacquor Mentah</td>
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
                        if (json.length > 0) {
                            // DATA | Set header information
                            $(".ofal-info").each(function(i) {
                                $(this).val(json[0][i + 1]);
                            });

                            // DATA | Set item per cartons
                            $("#itemPerCs").val(json[0][7]);

                            $("#detail").html("");
                            for (var i = 0; i < json.length; i++) {
                                var ppc = parseInt($("#itemPerCs").val()) - 1,
                                        qtyServed = json[i][11].split('.');
                                $("#detail").append('<tr data-id="' + json[i][12] + '">' +
                                        '<td>' + json[i][8] + '</td>' +
                                        '<td>' + json[i][9] + '</td>' +
                                        '<td>' + json[i][10] + '</td>' +
                                        '<td><input type="text" value="" size="10" /></td>' +
                                        '<td><input type="text" class="qtyServedCs" value="' + (parseInt(qtyServed[0])) + '" data-max="' + qtyServed[0] + '" size="6" readonly><input type="text" id="qtyServedTin" name="qtyServedTin" value="' + parseInt(qtyServed[1]) + '" data-max="' + qtyServed[1] + '" readonly></td>' +
                                        '<td><input type="text" class="lmQtyCs" value="0"><input type="text" class="lmQtyTin" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td id="remainings">0.00</td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>' +
                                        '<td><input type="text" value="" style="width: 100px;"></td>' +
                                        '</tr>');
                                $('#detail tr:last-child() td:eq(5) input:eq(0)').trigger('blur');
                            }
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

            // VALIDATE | Quantity maximum and minimum value
            $('#detail td:gt(4):lt(6) input:nth-child(2),#detail td:gt(7):lt(10) input:nth-child(2)')
                    .live("blur", function() {
                        if ($(this).val() < 0 ||
                                parseInt($(this).val()) > $(this).data('max')) {
                            $(this).val($(this).data('max'));
                        }
                    });

            // VALIDATE | Remaining quantity
            $('#detail td:gt(4):lt(6) input').live("blur", function() {
                var $td = $(this).parent().parent().find('td:eq(4)'),
                        ppc = parseInt($("#itemPerCs").val()),
                        qtyCs = parseInt($td.find('input:nth-child(2n+1)').val()) * ppc,
                        qtyTin = parseInt($td.find('input:nth-child(2n)').val()),
                        qtyTotal = qtyCs + qtyTin,
                        qtyLess = 0;

                $(this).parent().parent().find('td:gt(4):lt(6)').each(function(i) {
                    qtyLess = qtyLess + (parseInt($(this).find('input:eq(0)').val()) * ppc) + parseInt($(this).find('input:eq(1)').val());
                });

                if (qtyTotal - qtyLess < 0) {
                    $(this).val(0).trigger('blur');
                    return false;
                }

                $(this).parent().parent().find('td:eq(11)').html(tinToCs(qtyTotal - qtyLess, ppc).toFixed(2));
            });
            $('#detail td:gt(11):lt(3) input').live("blur", function() {
                var re = $(this).parent().parent().find('td:eq(11)').html().split('.'),
                        ppc = parseInt($("#itemPerCs").val()),
                        qtyCs = parseInt(re[0]) * ppc,
                        qtyTin = parseInt(re[1]),
                        qtyTotal = qtyCs + qtyTin,
                        qtyLess = 0;
                
                $(this).parent().parent().find('td:gt(11):lt(3)').each(function(i) {
                    qtyLess = qtyLess + (parseInt($(this).find('input:eq(0)').val()) * ppc) + parseInt($(this).find('input:eq(1)').val());
                });
                
                if (qtyTotal - qtyLess < 0) {
                    $(this).val(0).trigger('blur');
                }
            });

            // BIND | Save function
            $("#fLabeling").bind("submit", function() {
                var data = "",
                        lmQty = 0.00,
                        m = parseFloat($("#itemPerCs").val());

                // DATA | Prepare total quantity data
                $(".lmQtyCs").each(function() {
                    lmQty = lmQty + (parseInt($(this).val()) * parseInt(m)) + parseInt($(this).next().val());
                });


                $("#detail tr").each(function() {
                    var $i = $(this).find('input');
                    data = data + $("#lmCode").val() + "^" + $("#ofalCode").val() + "^" + $("#lmDate").val() + "^" + tinToCs(lmQty, m) + "^" +
                            $(this).data("id") + "^" +
                            $i[0].value + "^" +
                            (parseFloat($i[1].value) + (parseInt($i[2].value) / 100)) + "^" +
                            (parseFloat($i[3].value) + (parseInt($i[4].value) / 100)) + "^" +
                            (parseFloat($i[5].value) + (parseInt($i[6].value) / 100)) + "^" +
                            (parseFloat($i[7].value) + (parseInt($i[8].value) / 100)) + "^" +
                            (parseFloat($i[9].value) + (parseInt($i[10].value) / 100)) + "^" +
                            (parseFloat($i[11].value) + (parseInt($i[12].value) / 100)) + "^" +
                            (parseFloat($i[13].value) + (parseInt($i[14].value) / 100)) + "^" +
                            $(this).find("td:eq(11)").html() + "^" +
                            (parseFloat($i[15].value) + (parseInt($i[16].value) / 100)) + "^" +
                            (parseFloat($i[17].value) + (parseInt($i[18].value) / 100)) + "^" +
                            (parseFloat($i[19].value) + (parseInt($i[20].value) / 100)) + "^" +
                            $i[21].value + "^@";
                });
//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            // FUNCTION | Tin to Cs
            function tinToCs(v, m) {
                var cs = Math.floor(v / m);
                return cs + ((v % m) / 100);
            }

        </script>
    </body>
</html>

