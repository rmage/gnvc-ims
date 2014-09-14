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
        <title>IMS &therefore; Transfer &therefore; Create</title>
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
                    <form action="#" id="fTransfer" name="fTransfer" method="post">
                        <input type="hidden" id="tsDate" name="tsDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">TS Number</td>
                                    <td>
                                        <input type="text" id="tsCode" name="tsCode" required="required">
                                        Type 
                                        <select id="tsType" name="tsType" required>
                                            <option value="">-</option>
                                            <option value="B">Bad Stocks</option>
                                            <option value="O">Others</option>
                                            <option value="R">Re-Production</option>
                                        </select>
                                    </td>
                                    <td>TS Date</td>
                                    <td><input type="text" id="tsDatePicker" name="tsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td>
                                        <select id="tsFrom" name="tsFrom" required>
                                            <option value="">-</option>
                                            <c:forEach items="${ims.ds}" var="x">
                                                <option value="${x.departmentCode}">${x.departmentCode} | ${x.departmentName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>To</td>
                                    <td>
                                        <select id="tsTo" name="tsTo" required>
                                            <option value="">-</option>
                                            <c:forEach items="${ims.ds}" var="x">
                                                <option value="${x.departmentCode}">${x.departmentCode} | ${x.departmentName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="tsRemarks" name="tsRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGTransfer.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Transfer &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="8">
                                    <input type="text" id="ptsCode" name="ptsCode">
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add Item">
                                </th>
                            </tr>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Item Name</td>
                                <td>Pallet Number</td>
                                <td>Current Quantity</td>
                                <td>Out Quantity</td>
                                <td>Remaining Quantity</td>
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
            $("#tsDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#tsDate",
                changeYear: true,
                changeMonth: true
            });
            // BIND | Search PTS button
            $("#btnAdd").bind("click", function() {
                // VALIDATE | No same pts number in list
                var ptsCode = $('#ptsCode').val();
                if ($('tbody#detail tr td:nth-child(5):contains("' + ptsCode + '")').length > 0) {
                    alert('Pallet Transfer Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | Before pick transfer type
                if ($('#tsType').val() === '') {
                    alert('Please select Transfer Slip Type first!');
                    $('#tsType').focus();
                    return false;
                }

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    if ($('#tsType').val() !== 'R') {
                        $.ajax({
                            url: "?", type: "post",
                            data: {action: "getPalletTransfer", key: ptsCode},
                            dataType: "json",
                            success: function(json) {
                                for (var i = 0; i < json.length; i++) {
                                    var x = json[i][1].split('^');
                                    $('tbody#detail').append('<tr data-ppcs="' + x[1] + '" data-item="' + json[i][3] + '"><td>' + ($('tbody#detail tr').length + 1) +
                                            '</td><td>' + x[0] + '</td><td>' + json[i][2] + '</td><td>' + json[i][4] + '</td><td>' + json[i][5] +
                                            '</td><td>' + json[i][6] + '</td><td><input type="text" class="quantity-bad" value="0.00" size="6"></td><td>' + json[i][6] + '</td><td>' +
                                            '<input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td></tr>');
                                }
                            },
                            complete: function() {
                                $('#load').remove();
                                $('#ptsCode').val("");
                            }
                        });
                    } else {
                        $.ajax({
                            url: "?", type: "post",
                            data: {action: "getBadPalletTransfer", key: ptsCode},
                            dataType: "json",
                            success: function(json) {
                                for (var i = 0; i < json.length; i++) {
                                    var x = json[i][1].split('^');
                                    $('tbody#detail').append('<tr data-ppcs="' + x[1] + '" data-item="' + json[i][3] + '"><td>' + ($('tbody#detail tr').length + 1) +
                                            '</td><td>' + x[0] + '</td><td>' + json[i][2] + '</td><td>' + json[i][4] + '</td><td>' + json[i][5] +
                                            '</td><td>' + json[i][6] + '</td><td><input type="text" class="quantity-bad" value="0.00" size="6"></td><td>' + json[i][6] + '</td><td>' +
                                            '<input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td></tr>');
                                }
                            },
                            complete: function() {
                                $('#load').remove();
                                $('#ptsCode').val("");
                            }
                        });
                    }
                }
            });

            // BIND | Save function
            $("#fTransfer").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }
                
                // VALIDATE | 0 (zero) value in bad quantity
                if ($('tbody#detail tr input.quantity-bad').filter(function() { return parseFloat($(this).val()) <= 0; }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "";

                var header = $('#tsCode').val() + '^' + $('#tsType').val() + '^' + $('#tsDate').val() + '^' +
                        $('#tsFrom').val() + '^' + $('#tsTo').val() + '^' + $('#tsRemarks').val() + '^';

                $('tbody#detail tr').each(function() {
                    data = data + header + $(this).find('td:eq(4)').html() + '^' + $(this).data('item') + '^' + $(this).find('.quantity-bad').val() + '^@';
                });

//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });

            // LIVE | onBlur bad quantity
            $('.quantity-bad').live('blur', function() {
                var $r = $(this).parent().parent();
                var m = $r.data('ppcs');
                var gQty = csToTin($r.find('td:eq(5)').html(), m) - csToTin($r.find('input.quantity-bad').val(), m);

                $r.find('td:eq(7)').html(tinToCs(gQty, m).toFixed(2));
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

