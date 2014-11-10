<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Brine Freezing</title>
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
                            <caption>Brine Freezing &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">BF Number</td>
                                    <td><input type="text" name="bf_no" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('BrineFreezing.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Brine Freezing &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="bf_no">Number</td>
                                <td column="bf_date">Date</td>
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
            util.initListTable($('#list'), 'R_FishBF_Brine Freezing Report (xls)');
        </script>
        
    </body>
</html>
