<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods &therefore; Actual Inventory Report</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            #rptPreview > tr:nth-child(2n+1) {
                background-color: rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="GenerateReport.htm" id="generator" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="FGActualInventory" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />

                        <input type="hidden" id="asOf" name="asOf" value="<%=fDate%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods &therefore; Actual Inventory Report</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Pack Size</td>
                                    <td>
                                        <select id="packSize" name="packSize">
                                            <c:forEach items="${packSize}" var="x">
                                                <option value="${x}">${x}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>As Of</td>
                                    <td><input type="text" id="asOfPicker" name="asOfPicker" value="<%=cDate%>" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="button" id="btnPreview" name="btnPreview" value="Preview Data">
                                        <input type="submit" value="Generate Report" name="btnGenerate">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <div class="container-list" style="overflow-x: scroll;">
                        <table class="collapse tblForm row-select" style="width: 2000px;">
                            <caption>Actual Inventory Report &therefore; Preview Data</caption>
                            <thead>
                                <tr>
                                    <td class="center" rowspan="2" style="width: 25px;">No</td>
                                    <td class="center" colspan="7">Pallet</td>
                                    <td class="center" colspan="5">Production Date</td>
                                    <td rowspan="2">No Code</td>
                                    <td colspan="5">Multi Codes</td>
                                    <td rowspan="2">Labeled</td>
                                    <td colspan="3">Cut Out</td>
                                    <td rowspan="2">Brand</td>
                                    <td>EOE /</td>
                                    <td rowspan="2">Row</td>
                                    <td rowspan="2">Count TAG</td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td>Item</td>
                                    <td>Oil</td>
                                    <td>Pack Style</td>
                                    <td>Pack Size</td>
                                    <td>Can Code</td>
                                    <td>Quantity</td>
                                    <td class="pDateYear">Year</td>
                                    <td class="pDateYear">Year</td>
                                    <td class="pDateYear">Year</td>
                                    <td class="pDateYear">Year</td>
                                    <td class="pDateYear">Year</td>
                                    <td class="mCodeYear">Year</td>
                                    <td class="mCodeYear">Year</td>
                                    <td class="mCodeYear">Year</td>
                                    <td class="mCodeYear">Year</td>
                                    <td class="mCodeYear">Year</td>
                                    <td>NW</td>
                                    <td>DW</td>
                                    <td>Flakes</td>
                                    <td>Regular</td>
                                </tr>
                            </thead>
                            <tbody id="rptPreview"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script>
            // BINDING | date picker to as of
            $('#asOfPicker').datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#asOf",
                changeMonth: true,
                changeYear: true
            });

            // BINDING | generate report button
            $('#generator').bind('submit', function() {
                $('#params').val($("#packSize").val() + ':' + $("#asOf").val());
            });

            // BINDING | preview data button
            $('#btnPreview').bind('click', function() {
                $('tbody#rptPreview').html('');
                $('.pDateYear,.mCodeYear').each(function(i) {
                    $(this).html('Year');
                });

                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: "D3.htm", type: "post",
                    data: {action: "getFGActualInventoryPreview", param1: $("#packSize").val(), param2: $("#asOf").val()},
                    dataType: "json",
                    success: function(json) {
                        var year = parseInt($('#asOf').val().substr(0, 4));
                        $('.pDateYear').each(function(i) {
                            $(this).html(year - i);
                        });
                        $('.mCodeYear').each(function(i) {
                            $(this).html(year - i);
                        });
                        for (var i = 0; i < json.length; i++) {
                            $('tbody#rptPreview').append('<tr>' +
                                    '<td class="center">' + ($('tbody#rptPreview tr').length + 1) + '</td>' +
                                    '<td class="center">' + json[i][1] + '</td>' +
                                    '<td class="left">' + json[i][2] + '</td>' +
                                    '<td class="center">' + json[i][3] + '</td>' +
                                    '<td class="center">' + json[i][4] + '</td>' +
                                    '<td class="center">' + json[i][5] + '</td>' +
                                    '<td class="center">' + json[i][6] + '</td>' +
                                    '<td class="right">' + json[i][7] + '</td>' +
                                    '<td class="right">' + json[i][8] + '</td>' +
                                    '<td class="right">' + json[i][9] + '</td>' +
                                    '<td class="right">' + json[i][10] + '</td>' +
                                    '<td class="right">' + json[i][11] + '</td>' +
                                    '<td class="right">' + json[i][12] + '</td>' +
                                    '<td class="right">' + json[i][13] + '</td>' +
                                    '<td class="right">' + json[i][14] + '</td>' +
                                    '<td class="right">' + json[i][15] + '</td>' +
                                    '<td class="right">' + json[i][16] + '</td>' +
                                    '<td class="right">' + json[i][17] + '</td>' +
                                    '<td class="right">' + json[i][18] + '</td>' +
                                    '<td class="right">' + json[i][19] + '</td>' +
                                    '<td class="right">' + json[i][20] + '</td>' +
                                    '<td class="right">' + json[i][21] + '</td>' +
                                    '<td class="right">' + json[i][22] + '</td>' +
                                    '<td class="center">' + json[i][23] + '</td>' +
                                    '<td class="center">' + json[i][24] + '</td>' +
                                    '<td class="center">' + json[i][25] + '</td>' +
                                    '<td class="center">' + json[i][26] + '</td>' +
                                    '</tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });
        </script>

    </body>
</html>
