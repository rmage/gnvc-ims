<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Summary Report per Supplier</title>
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
                        <input type="hidden" name="item" value="FishSumPerSupp" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="<%=cDateH %>" />
                        <table class="collapse tblForm row-select">
                            <caption>Summary Report &therefore; per Supplier</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">As Of</td>
                                    <td><input id="date" type="text" name="date" value="<%=cDate %>" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate Summary Report" name="btnGenerate" />
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
            $('#date').datepicker({
                changeMonth: true, 
                changeYear: true, 
                dateFormat: "dd/mm/yy", 
                altField: "#params", 
                altFormat: "yy-mm-dd"
            });
        </script>

    </body>
</html>
