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
        <title>IMS &therefore; Reclassification &therefore; Create</title>
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
                    <form action="#" id="fReclassification" name="fReclassification" method="post">
                        <input type="hidden" id="reccDate" name="reccDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Reclassification &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Reclassification Number</td>
                                    <td><input type="text" id="reccCode" name="tsCode" required="required"></td>
                                    <td>Reclassification Date</td>
                                    <td><input type="text" id="reccDatePicker" name="tsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="reccRemarks" name="tsRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGReclassification.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Reclassification &therefore; Detail</caption>
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
                                <td>Pallet Number</td>
                                <td>From Item</td>
                                <td>To Item</td>
                                <td>Quantity</td>
                                <td>Reclassification Quantity</td>
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
            $("#reccDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#reccDate",
                changeYear: true,
                changeMonth: true
            });

//            // BIND | Search OFAL button
            $("#btnAdd").bind("click", function() {
                // VALIDATE | No same pts number in list
                var ptsCode = $('#ptsCode').val();
                if ($('tbody#detail tr td:nth-child(5):contains("' + ptsCode + '")').length > 0) {
                    alert('Pallet Reclassification Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | Before pick transfer type
                if ($('#tsType').val() === '') {
                    alert('Please select Reclassification Slip Type first!');
                    $('#tsType').focus();
                    return false;
                }

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: ptsCode},
                        dataType: "json",
                        success: function(json) {
                            if (json.length > 0) {
                                var x = json[0][1].split('^');
                                $('tbody#detail').append('<tr data-ppcs="' + x[1] + '" data-item="' + json[0][3] + '"><td>' + ($('tbody#detail tr').length + 1) +
                                        '</td><td>' + x[0] + '</td><td>' + json[0][2] + '</td><td>' + ptsCode + '</td><td>' + json[0][4] + '</td><td><select>' + $("<div/>").html(json[0][5]).text() + '</select>' + '</td><td>' + json[0][6] +
                                        '</td><td><input type="text" value="' + json[0][6] + '" data-max="' + json[0][6] + '"></td><td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td></tr>').text();
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                            $('#ptsCode').val("");
                        }
                    });
                }
            });

            // BIND | Save function
            $("#fReclassification").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }
                
                // VALIDATE | 0 (zero) value in bad quantity
                if ($('tbody#detail tr input').filter(function() { return parseFloat($(this).val()) <= 0; }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "";

                var header = $('#reccCode').val() + '^' + $('#reccDate').val() + '^' + $('#reccRemarks').val() + '^';

                $('tbody#detail tr').each(function() {
                    data = data + header + $(this).find('td:eq(3)').html() + '^' + $(this).data('item') + '^' + 
                            $(this).find('select').val() + '^' + $(this).find('input').val() + '^@';
                });

                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
//                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });
            
            // LIVE | validate maximum quantity
            $('tbody#detail tr input').live('blur', function() {
                if(parseFloat($(this).val()) > parseFloat($(this).data('max'))) {
                    $(this).val($(this).data('max'));
                }
            });

        </script>
    </body>
</html>

