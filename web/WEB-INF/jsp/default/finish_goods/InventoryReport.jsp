<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods &therefore; Inventory Report</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="GenerateReport.htm" id="generator" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="FGStockInventory" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />

                        <input type="hidden" id="asOf" name="asOf" value="<%=fDate%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods &therefore; Inventory Report</caption>
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
                                        <input type="button" value="Stock Status per PTS" id="btnSsppts" name="btnSsppts">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Inventory Report &therefore; Preview Data</caption>
                        <thead>
                            <tr>
                                <td class="center" style="width: 25px;">No</td>
                                <td class="center">Item Code</td>
                                <td class="center">Pack Style</td>
                                <td class="center">Can Type</td>
                                <td class="center">Lid Type</td>
                                <td class="center">NW/DW</td>
                                <td class="center">Beginning</td>
                                <td class="center">IN Pallet Transfer</td>
                                <td class="center">IN Pallet Hold</td>
                                <td class="center">IN Return Cargo</td>
                                <td class="center">IN Reclassification</td>
                                <td class="center">OUT Export Delivery</td>
                                <td class="center">OUT Delivery</td>
                                <td class="center">OUT Transfer Others</td>
                                <td class="center">OUT Transfer Bad Stocks</td>
                                <td class="center">OUT Reclassification</td>
                                <td class="center">Ending</td>
                            </tr>
                        </thead>
                        <tbody id="rptPreview"></tbody>
                    </table>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <iframe src="" id="iFrameDownloader" style="display: none;"></iframe>

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
            
            $('#btnSsppts').bind('click', function() {
                $('#iFrameDownloader')[0].src = 'GenerateReport.htm?action=index&item=FGStockStatusPerPts&type=xls&params=' + $("#asOf").val() + ':' + $("#packSize").val();
            });

            // BINDING | preview data button
            $('#btnPreview').bind('click', function() {
                $('tbody#rptPreview').html('');
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: "D3.htm", type: "post",
                    data: {action: "getFGStockInventoryPreview", param1: $("#packSize").val(), param2: $("#asOf").val()},
                    dataType: "json",
                    success: function(json) {
                        for (var i = 0; i < json.length; i++) {
                            $('tbody#rptPreview').append('<tr>' +
                                    '<td class="center">' + ($('tbody#rptPreview tr').length + 1) + '</td>' +
                                    '<td>' + json[i][13] + '</td>' +
                                    '<td>' + json[i][1] + '</td>' +
                                    '<td>' + json[i][14] + '</td>' +
                                    '<td>' + json[i][15] + '</td>' +
                                    '<td>' + json[i][16] + '</td>' +
                                    '<td class="right">' + json[i][2] + '</td>' +
                                    '<td class="right">' + json[i][3] + '</td>' +
                                    '<td class="right">' + json[i][4] + '</td>' +
                                    '<td class="right">' + json[i][5] + '</td>' +
                                    '<td class="right">' + json[i][6] + '</td>' +
                                    '<td class="right">' + json[i][7] + '</td>' +
                                    '<td class="right">' + json[i][8] + '</td>' +
                                    '<td class="right">' + json[i][9] + '</td>' +
                                    '<td class="right">' + json[i][10] + '</td>' +
                                    '<td class="right">' + json[i][11] + '</td>' +
                                    '<td class="right">' + json[i][12] + '</td>' +
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
