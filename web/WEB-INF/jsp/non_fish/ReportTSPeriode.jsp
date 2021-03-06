<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Generate TS Registered per Periode</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" method="post" id="genForm">
                        <table class="collapse tblForm row-select">
                            <caption>Generate TS Registered per Periode</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td style="width: 150px;">Periode</td>
                                    <td>
                                        <input class="date" size="10" type="text" required="true" /> - <input class="date" size="10" type="text" required="true" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Report Format</td>
                                    <td>
                                        <select id="type" name="type" required="true">
                                            <option value="xls">XLS</option>
                                            <option value="pdf">PDF</option>
                                            <option value="csv">CSV</option>
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
            var monthName = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
            $('#genForm').bind('submit', function() {
                var date0 = $('.date:eq(0)').val().split("/");
                var date1 = $('.date:eq(1)').val().split("/");
                
                window.location.replace('GenerateReport.htm?action=index&item=TSPeriode&type=' + $('#type').val() + '&params=' + 
                    date0[2] + ':' + date0[1] + ':' + (date0[0] + ' - ' + date1[0] + ' ' + monthName[(date0[1] - 1)] + ' ' + date0[2]));
                return false;
            });
            
            $('.date').datepicker({ changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy" });
        </script>
    </body>
</html>
