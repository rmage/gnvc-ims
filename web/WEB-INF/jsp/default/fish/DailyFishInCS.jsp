<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Daily Inventory of Frozen Fish in Cold Storage</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <%            String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="GenerateReport.htm" id="search" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="FishDailyInCS" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />
                        <input type="hidden" id="date" name="date" value="<%=cDateH%>">
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card &therefore; Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">As Of</td>
                                    <td><input id="datePicker" type="text" name="datePicker" value="<%=cDate%>" /></td>
                                </tr>
                                <tr>
                                    <td style="width: 200px;">Storage</td>
                                    <td>
                                        <select id="storage" name="storage">
                                            <option value="%SPFI%">SPFI Storage</option>
                                            <option value="%EXTERNAL%">External Storage</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate Stock Card" id="btnGenerate" name="btnGenerate" />
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
            $('#datePicker').datepicker({
                changeMonth: true, 
                changeYear: true, 
                dateFormat: "dd/mm/yy", 
                altField: "#date", 
                altFormat: "yy-mm-dd"
            });
            
            $("#btnGenerate").bind("focus", function() {
                $('#params').val($("#date").val() + ":" + $("#storage").val());
            });
        </script>

    </body>
</html>
