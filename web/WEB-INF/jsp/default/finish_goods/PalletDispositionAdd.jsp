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
        <title>IMS &therefore; Pallet Disposition &therefore; Create</title>
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
                    <form action="#" id="fPalletDisposition" name="fPalletDisposition" method="post">
                        <input type="hidden" id="qaDate" name="qaDate" value="<%=sdf.format(cDate)%>" />
                        <input type="hidden" id="evaDate" name="evaDate" value="<%=sdf.format(cDate)%>" />
                        <input type="hidden" id="disDate" name="disDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Disposition &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">QA Number</td>
                                    <td><input type="text" id="qaCode" name="qaCode" required="required"></td>
                                    <td>QA Date</td>
                                    <td><input type="text" id="qaDatePicker" name="qaDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Evaluated Date</td>
                                    <td><input type="text" id="evaDatePicker" name="evaDatePicker" size="10" value="<%=sdfPicker.format(cDate)%>"></td>
                                    <td>Disposition Date</td>
                                    <td><input type="text" id="disDatePicker" name="disDatePicker" size="10" value="<%=sdfPicker.format(cDate)%>"></td>
                                </tr>
                                <tr>
                                    <td>Reason</td>
                                    <td><input type="text" id="qaReason" name="qaReason" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="qaRemarks" name="qaRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletDisposition.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Pallet Disposition &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="7">
                                    <input type="text" id="ptsCode" name="ptsCode">
                                    <input type="button" id="btnSelect" name="btnSelect" value="Select Pallet">
                                </th>
                            </tr>
                            <tr>
                                <td>Can Code</td>
                                <td>Production Date</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Item Name</td>
                                <td>Quantity</td>
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

            // BIND | date picker
            $("#qaDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#qaDate",
                changeYear: true,
                changeMonth: true
            });
            $("#evaDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#evaDate",
                changeYear: true,
                changeMonth: true
            });
            $("#disDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#disDate",
                changeYear: true,
                changeMonth: true
            });

            // BIND | search pts button
            $("#btnSelect").bind("click", function() {
                var ptsCode = $('#ptsCode').val();
                $('tbody#detail').html('');
                
                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: ptsCode},
                        dataType: "json",
                        success: function(json) {
                            for (var i = 0; i < json.length; i++) {
                                $('tbody#detail').append('<tr>' + 
                                        '<td>' + json[i][1] + '</td>' +
                                        '<td>' + json[i][2] + '</td>' +
                                        '<td>' + json[i][3] + '</td>' +
                                        '<td>' + json[i][4] + '</td>' +
                                        '<td>' + json[i][5] + '</td>' +
                                        '<td>' + json[i][6] + '</td>' +
                                        '</tr>');
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                        }
                    });
                }
            });

            // BIND | Save function
            $("#fPalletDisposition").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                var data = $('#qaCode').val() + '^' + $('#qaDate').val() + '^' + $('#ptsCode').val() + '^' + $('#evaDate').val() + '^' + 
                        $('#disDate').val() + '^' + $('#qaReason').val() + '^' + $('#qaRemarks').val() + '^@';

//                console.log(data);
                if (data !== "") {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }

                return false;
            });
            
            // BIND | focus will remove existing selected pallet number
            $('#ptsCode').bind('focus', function(){
                $('tbody#detail').html('');
            });

        </script>
    </body>
</html>

