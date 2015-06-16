<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; BC Code</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            .ui-datepicker { display: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="GenerateReport.htm" id="search" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="iCoreFishRR" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />
                        <input type="hidden" id="dateFrom" name="dateFrom" value="<%=fDate%>" />
                        <input type="hidden" id="dateTo" name="dateTo" value="<%=fDate%>" />
                        <table class="collapse tblForm row-select">
                            <caption>BC Code &therefore; Report</caption>
                            <tbody>                               
                                <tr>
                                    <td style="width: 100px;">Module</td>
                                    <td>
<!--                                        <input type="text" id="moduleCategory" name="moduleCategory" value="fishRR" readonly="true">-->
                                        <select id="moduleCategory" name="moduleCategory">
                                            <option value="fishRR">Fish - Receiving Report</option>
                                        </select>
                                    </td>                                
                                </tr>
                                <tr>
                                    <td style="width: 137px">Jenis Dokumen</td>
                                    <td>
                                        <select id="bcNo" name="bcNo" required>
                                        <option value="">Select No BC</option>
                                        <option value="BC 2.3">2.3</option>
                                        <option value="BC 3.0">3.0</option>
                                        <option value="BC 4.0">4.0</option>
                                        <option value="BC 4.1">4.1</option>
                                      </select>
                                    </td>
                                </tr>
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
                                        <input type="submit" value="Generate Report" id="btnGenerate" name="btnGenerate" />
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

            $("#btnGenerate").bind("focus", function() {
                $('#params').val($("#moduleCategory").val() + ":" + $("#bcNo").val() + ":" + $("#dateFrom").val() + ":" + $("#dateTo").val());
            });
        </script>

    </body>
</html>

