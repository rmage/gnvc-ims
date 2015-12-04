<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Frozen Fish &therefore; in Cold Storage</title>
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
                        <input type="hidden" name="item" value="FFrozenFishInColdStorage" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />
                        
                        <input type="hidden"  id="date" name="date" value="<%=sdf.format(cDate)%>">

                        <input type="hidden" id="userId" name="userId" value="${ims.userId}" />
                        <table class="collapse tblForm row-select">
                            <caption>Frozen Fish &therefore; in Cold Storage</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">As Of</td>
                                    <td><input id="datePicker" type="text" name="datePicker" value="<%=sdfPicker.format(cDate)%>" /></td>
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
            $('#datePicker').datepicker({dateFormat: 'dd/mm/yy', altFormat: 'yy-mm-dd', altField: '#date', changeMonth: true, changeYear: true});
            
            $('#generator').bind('submit', function() {
                $('#params').val($('#date').val() + ':%%');
            });
        </script>

    </body>
</html>
