<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Receiving</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishReceivingReport.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Air Blast Freezing &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">ABF Number</td>
                                    <td><input type="text" name="abf_no" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('AirBlastFreezing.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Air Blast Freezing &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="bf_no">Number</td>
                                <td column="bf_date">Date</td>
                                <td>WS Number</td>
                                <td>Supplier</td>
                                <td>Batch Number</td>
                                <td>Regu</td>
                                <td>Time Shift</td>
                                <td>Time Start</td>
                                <td>Time Finish</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_FishABF_Air Blast Freezing Report (xls)');
        </script>
        
    </body>
</html>
