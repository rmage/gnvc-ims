<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &therefore; Generate &therefore; PTS Registered Per Period</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" method="post" id="genForm">
                        <input type="hidden" id="dateFrom" name="dateFrom" value="<%=cDateH%>" />
                        <input type="hidden" id="dateTo" name="dateTo" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Generate &therefore; PTS Registered Per Period</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 200px">Date From</td>
                                    <td><input type="text" id="dateFromSource" name="dateTo" size="10" value="<%=cDate%>" required="required" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" /></td>
                                </tr>
                                <tr>
                                    <td>Date To</td>
                                    <td><input type="text" id="dateToSource" name="dateTo" size="10" value="<%=cDate%>" required="required" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[012])/[0-9]{4}" /></td>
                                </tr>
                                <tr>
                                    <td>By</td>
                                    <td>
                                        <select id="ptsBy" name="ptsBy">
                                            <option value="D">Document Date</option>
                                            <option value="C">System Date</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tfoot class="ui-widget-header">
                                <tr><td>
                                        <label>
                                            <input id="generate" type="submit" value="Generate Report" />
                                        </label>
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
            $('#dateFromSource').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#dateFrom", altFormat: "yy-mm-dd"});
            $('#dateToSource').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#dateTo", altFormat: "yy-mm-dd"});
            $('#genForm').bind('submit', function() {
                window.location.replace('GenerateReport.htm?action=index&item=FGPtsPerPeriod&type=xls&params=' +
                        $('#dateFrom').val() + ':' + $('#dateTo').val() + ':' + $('#ptsBy').val());
                return false;
            });
        </script>
    </body>
</html>
