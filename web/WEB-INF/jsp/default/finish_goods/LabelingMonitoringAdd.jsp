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
        <title>Create &therefore; Labeling Monitoring &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <link href="resources/default/css/style-table.css" rel="stylesheet" type="text/css">
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #list thead td { width: 60px; }
            #detail td:nth-child(n+5) input:nth-child(2n+1) { width: 50px; }
            #detail td:nth-child(n+5) input:nth-child(2n) { width: 15px; }
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
                                    <td style="width: 200px;">LM Number</td>
                                    <td><input type="text" id="lmCode" name="lmCode" required="required" /></td>
                                    <td style="width: 200px;">LM Date</td>
                                    <td><input type="text" id="lmDatePicker" name="lmDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required /></td>
                                </tr>
                                <tr>
                                    <td>OFAL Number</td>
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
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGLabelingMonitoring.htm');" />
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
            $("#lmDatePicker").datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#lmDate", changeYear: true, changeMonth: true});

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
//                            $("#itemPerCs").val(json[0][7]);

                            $("#detail").html("");
                            $("#ofalCode").data('id', json[0][11]);
//                            var sAppend = [], cAppend = 0;
                            for (var i = 0; i < json.length; i++) {
                                $('#detail').append('<tr data-ofald="' + json[i][12] + '">' +
                                        '<td>' + json[i][7] + '</td>' +
                                        '<td>' + json[i][8] + '</td>' +
                                        '<td>' + json[i][9] + '</td>' +
                                        '<td><input class="lmBatch" type="text" value="" size="10" /></td>' +
                                        '<td class="bg-gray-1"><input type="text" class="qtyServed" value="' + json[i][10] + '" size="6" readonly></td>' +
                                        '<td class="bg-green-1"><input type="text" class="lmQty" value="0" size="6"></td>' +
                                        '<td class="bg-yellow-1"><input type="text" class="lmDented" value="0" size="6"></td>' +
                                        '<td class="bg-red-1"><input type="text" class="lmRusty" value="0" size="6"></td>' +
                                        '<td class="bg-gray-1"><input type="text" class="lmFlipper" value="0" size="6"></td>' +
                                        '<td class="bg-green-1"><input type="text" class="lmBulger" value="0" size="6"></td>' +
                                        '<td class="bg-yellow-1"><input type="text" class="lmSeam" value="0" size="6"></td>' +
                                        '<td id="remainings">0.00</td>' +
                                        '<td class="bg-gray-1"><input type="text" class="lmNoCode" value="0" size="6"></td>' +
                                        '<td class="bg-green-1"><input type="text" class="lmBlurred" value="0" size="6"></td>' +
                                        '<td class="bg-yellow-1"><input type="text" class="lmLacquor" value="0" size="6"></td>' +
                                        '<td><input class="lmRemark" type="text" style="width: 100px;"></td>' +
                                        '</tr>');
                                /*
                                 var ppc = parseInt($("#itemPerCs").val()) - 1,
                                 qtyServed = json[i][11].split('.');
                                 sAppend[cAppend++] = '<tr data-ofald="' + json[i][12] + '">';
                                 sAppend[cAppend++] = '<td>' + json[i][8] + '</td>';
                                 sAppend[cAppend++] = '<td>' + json[i][9] + '</td>';
                                 sAppend[cAppend++] = '<td>' + json[i][10] + '</td>';
                                 sAppend[cAppend++] = '<td><input type="text" value="" size="10" /></td>';
                                 sAppend[cAppend++] = '<td class="bg-gray-1"><input type="text" class="qtyServedCs" value="' + (parseInt(qtyServed[0])) + '" data-max="' + qtyServed[0] + '" size="6" readonly><input type="text" id="qtyServedTin" name="qtyServedTin" value="' + parseInt(qtyServed[1]) + '" data-max="' + qtyServed[1] + '" readonly></td>';
                                 sAppend[cAppend++] = '<td class="bg-green-1"><input type="text" class="lmQtyCs" value="0"><input type="text" class="lmQtyTin" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-yellow-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-red-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-gray-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-green-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-yellow-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td id="remainings">0.00</td>';
                                 sAppend[cAppend++] = '<td class="bg-gray-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-green-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td class="bg-yellow-1"><input type="text" value="0"><input type="text" value="0" data-max="' + ppc + '"></td>';
                                 sAppend[cAppend++] = '<td><input type="text" value="" style="width: 100px;"></td>';
                                 sAppend[cAppend++] = '</tr>';
                                 */
                            }
                            //$("#detail").append(sAppend.join(''));
//                            bindEvents();
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });

//            function bindEvents() {
//                $('#detail tr').each(function() {
//                    // VALIDATE | Quantity maximum and minimum value
//                    $(this).find('td:gt(4):lt(6) input:nth-child(2),td:gt(7):lt(10) input:nth-child(2)')
//                            .bind("blur", function() {
//                                if ($(this).val() < 0 || parseInt($(this).val()) > $(this).data('max')) {
//                                    alert('[Error] Maximum number exceeded!');
//                                    $(this).val(0);
//                                }
//                            });
//
//                    // VALIDATE | Remaining quantity
//                    $(this).find('td:gt(4):lt(6) input').live("blur", function() {
//                        var $td = $(this).parent().parent().find('td:eq(4)'),
//                                ppc = parseInt($("#itemPerCs").val()),
//                                qtyCs = parseInt($td.find('input:nth-child(2n+1)').val()) * ppc,
//                                qtyTin = parseInt($td.find('input:nth-child(2n)').val()),
//                                qtyTotal = qtyCs + qtyTin,
//                                qtyLess = 0;
//
//                        $(this).parent().parent().find('td:gt(4):lt(6)').each(function(i) {
//                            qtyLess = qtyLess + (parseInt($(this).find('input:eq(0)').val()) * ppc) + parseInt($(this).find('input:eq(1)').val());
//                        });
//
//                        if (qtyTotal - qtyLess < 0) {
//                            alert('[Error] Maximum number exceeded!');
//                            $(this).val(0).trigger('blur');
//                            return false;
//                        }
//
//                        $(this).parent().parent().find('td:eq(11)').html(tinToCs(qtyTotal - qtyLess, ppc).toFixed(2));
//                    }).trigger('blur');
//                    $(this).find('td:gt(11):lt(3) input').live("blur", function() {
//                        var re = $(this).parent().parent().find('td:eq(11)').html().split('.'),
//                                ppc = parseInt($("#itemPerCs").val()),
//                                qtyCs = parseInt(re[0]) * ppc,
//                                qtyTin = parseInt(re[1]),
//                                qtyTotal = qtyCs + qtyTin,
//                                qtyLess = 0;
//
//                        $(this).parent().parent().find('td:gt(11):lt(3)').each(function(i) {
//                            qtyLess = qtyLess + (parseInt($(this).find('input:eq(0)').val()) * ppc) + parseInt($(this).find('input:eq(1)').val());
//                        });
//
//                        if (qtyTotal - qtyLess < 0) {
//                            alert('[Error] Maximum number exceeded!');
//                            $(this).val(0).trigger('blur');
//                        }
//                    });
//                });
//            }

            // BIND | Save function
            $("#fLabeling").bind("submit", function() {
                var data = '', header = $('#lmCode').val() + ':s:' + $('#ofalCode').data('id') + ':s:' + $('#lmDate').val() + ':s:' + eachSum('lmQty') + ':s:' + eachSum('lmFlipper') + ':s:0:s:' + eachSum('lmBulger') + ':s:0:s:' + eachSum('lmSeam') + ':s:0:s:';

                $('#detail tr').each(function() {
                    data = data + header + $(this).data('ofald') + ':s:' + $(this).find('.lmBatch').val() + ':s:' + $(this).find('.qtyServed').val() + ':s:' + $(this).find('.lmQty').val() + ':s:' + $(this).find('.lmDented').val() + ':s:' + $(this).find('.lmRusty').val() + ':s:' + $(this).find('.lmFlipper').val() + ':s:' + $(this).find('.lmBulger').val() + ':s:' + $(this).find('.lmSeam').val() + ':s:' + $(this).find('#remainings').html() + ':s:' + $(this).find('.lmNoCode').val() + ':s:' + $(this).find('.lmBlurred').val() + ':s:' + $(this).find('.lmLacquor').val() + ':s:' + $(this).find('.lmRemark').val() + ':s::se:';
                });

                if (data !== '' && confirm('Save Labelling Monitoring #' + $('#lmCode').val() + ' ?')) {
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

            // UPDATE | function
            function eachSum(className) {
                var sum = 0;
                $('.' + className).each(function() {
                    sum = sum + parseFloat($(this).val());
                });
                return sum;
            }

            $('#detail input[type="text"]').live('blur', function() {
                var $tr = $(this).parent().parent(), remaining;

                remaining = parseFloat($tr.find('.qtyServed').val()) - parseFloat($tr.find('.lmQty').val()) - parseFloat($tr.find('.lmDented').val()) - parseFloat($tr.find('.lmRusty').val()) - parseFloat($tr.find('.lmFlipper').val()) - parseFloat($tr.find('.lmBulger').val()) - parseFloat($tr.find('.lmSeam').val());
                console.log(remaining);
                $tr.find('#remainings').html(parseFloat(remaining).toFixed(2));
            });

        </script>
    </body>
</html>

