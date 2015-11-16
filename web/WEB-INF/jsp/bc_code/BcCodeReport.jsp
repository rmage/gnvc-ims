<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String fDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Report &therefore; IMS</title>
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
                        <input type="hidden" id="item" name="item" />
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
                                        <select id="moduleCategory" name="moduleCategory">
                                            <option value="empty">-- Select Report --</option>
                                            <option value="nfRR" data-bc="BC 2.3">iCore Template - BC 2.3</option>
                                            <option value="nfRRfRR" data-bc="BC 4.0">iCore Template - BC 4.0</option>
                                            <option value="rmDR" data-bc="BC 4.1">iCore Template - BC 4.1</option>
<!--                                            <option value="nfRR" data-bc="BC 2.3">Non Fish - BC 2.3</option>
                                            <option value="nfRR" data-bc="BC 4.0">Non Fish - BC 4.0</option>
                                            <option value="fishRR" data-bc="BC 2.3">Fish - BC 2.3</option>
                                            <option value="fgEDS" data-bc="BC 3.0">Finished Goods - BC 3.0</option>
                                            <option value="fgPTS" data-bc="0">Production - Finished Goods</option>-->
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
                                <tr>
                                    <td>Starting BC Number</td>
                                    <td>
                                        <input id="bcNumberStart" name="bcNumberStart" type="text" size="6" maxlength="6" onkeypress="return util.isNumberOnly(event);">
                                        <small>(used in BC 4.0 and BC 4.1)</small>
                                    </td>
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
            
            $('#moduleCategory').bind('change', function(){
                if ($(this).val() === 'nfRR' && $(this).find('option:selected').attr('data-bc') === 'BC 2.3' ) {
                    $('#item').val('reportBC23');
                } else if ($(this).val() === 'nfRRfRR' && $(this).find('option:selected').data('bc') === 'BC 4.0') {
                    $('#item').val('reportBC40');
                } else if($(this).val() === 'rmDR' && $(this).find('option:selected').data('bc') === 'BC 4.1'){
                    $('#item').val('reportBC41');
                }/* else if ($(this).val() === 'fishRR' && $(this).find('option:selected').data('bc') === 'BC 2.3'){
                    $('#item').val('reportBC23');
                }  else if($(this).val() === 'fgEDS' && $(this).find('option:selected').data('bc') === 'BC 3.0'){
                    $('#item').val('reportBC30');
                } else if($(this).val() === 'fgPTS'){
                    $('#item').val('reportFinishGood');
                } else if($(this).val() === 'rawTsMU'){
                    $('#item').val('reportFinishGood');
                } else {
                    alert('Pilih Yang Lain');
                }*/
            });

            $("#btnGenerate").bind("focus", function() {
                $('#params').val($("#moduleCategory").val() + ":" + $("#moduleCategory option:selected").attr('data-bc') + ":" + $("#dateFrom").val() + ":" + $("#dateTo").val() + ':' + $('#bcNumberStart').val());
            });
        </script>

    </body>
</html>