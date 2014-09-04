<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Transfer Slip &therefore; per Period</title>
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
                        <input type="hidden" name="item" value="NFTsRegisterPerPeriod" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />

                        <input type="hidden" id="dateFrom" name="dateFrom" value="<%=fDate%>" />
                        <input type="hidden" id="dateTo" name="dateTo" value="<%=fDate%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip &therefore; per Period</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Date From</td>
                                    <td><input type="text" id="dateFromPicker" name="dateFromPicker" value="<%=cDate%>" /></td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;">Date To</td>
                                    <td><input type="text" id="dateToPicker" name="dateToPicker" value="<%=cDate%>" /></td>
                                </tr>
                                <tr>
                                    <td>TS Type</td>
                                    <td>
                                        <select id="tsType" name="tsType">
                                            <option value="-">All Type</option>
                                            <option value="NORMAL">Normal</option>
                                            <option value="OTHERS">Others</option>
                                        </select>
                                    </td>
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

            $('#generator').bind('submit', function() {
                $('#params').val($("#dateFrom").val() + ':' + $("#dateTo").val() + ":" + $("#tsType").val());
            });
        </script>

    </body>
</html>
