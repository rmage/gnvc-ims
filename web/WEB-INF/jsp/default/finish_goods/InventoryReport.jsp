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
                                            <option>307</option>
                                            <option>603</option>
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
                                        <input type="submit" value="Generate Report" name="btnGenerate" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script>
            $('#asOfPicker').datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#asOf",
                changeMonth: true,
                changeYear: true
            });

            $('#generator').bind('submit', function() {
                $('#params').val($("#packSize").val() + ':' + $("#asOf").val());
            });
        </script>

    </body>
</html>
