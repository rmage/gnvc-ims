<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Rendering &therefore; Summary Report</title>
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
                    <form action="GenerateReport.htm" id="generator" method="get">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="RendSummaryReport" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />

                        <input type="hidden" id="dateFrom" name="dateFrom" value="<%=fDate%>" />
                        <input type="hidden" id="dateTo" name="dateTo" value="<%=fDate%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Rendering &therefore; Summary Report</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Date From</td>
                                    <td><input type="text" id="dateFromPicker" name="dateFromPicker" value="<%=cDate%>" required></td>
                                </tr>
                                <tr>
                                    <td>Date To</td>
                                    <td><input type="text" id="dateToPicker" name="dateToPicker" value="<%=cDate%>" required></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate Report" name="btnGenerate">
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
            // BINDING | date picker to as of
            $('#dateFromPicker').datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#dateFrom",
                changeMonth: true,
                changeYear: true
            });
            
            $('#dateToPicker').datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#dateTo",
                changeMonth: true,
                changeYear: true
            });

            // BINDING | generate report button
            $('#generator').bind('submit', function() {
                $('#params').val($("#dateFrom").val() + ':' + $("#dateTo").val());
            });

        </script>

    </body>
</html>
