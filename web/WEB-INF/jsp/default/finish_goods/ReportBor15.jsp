<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report &therefore; Booked Order &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                    <form action="ReportBor.htm" id="generate" method="post">
                        <input type="hidden" id="dateFrom" name="dateFrom">
                        <input type="hidden" id="dateTo" name="dateTo">
                        <table class="collapse tblForm row-select">
                            <caption>Booked Order Report &therefore; Report</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Bor Date From</td>
                                    <td><input type="text" id="dateFromPicker" name="dateFromPicker" required></td>
                                </tr>
                                <tr>
                                    <td>Bor Date To</td>
                                    <td><input type="text" id="dateToPicker" name="dateToPicker" required></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="reset" value="Reset" name="btnReset">
                                        <input type="submit" value="Generate" name="btnGenerate">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            $('#dateFromPicker').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd/mm/yy",
                altField: '#dateFrom',
                altFormat: 'yy-mm-dd'});
            $('#dateToPicker').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd/mm/yy",
                altField: '#dateTo',
                altFormat: 'yy-mm-dd'});

            $('#generate').bind('submit', function() {
                window.location.href = 'GenerateReport.htm?action=index&item=FGBor15Report&type=xls&params=' + $('#dateFrom').val() + ':' + $('#dateTo').val();
                return false;
            });
        </script>
    </body>
</html>
