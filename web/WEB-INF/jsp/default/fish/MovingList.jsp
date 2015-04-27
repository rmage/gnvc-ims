<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Moving</title>
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
                            <caption>Fish Moving &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">FM Number</td>
                                    <td><input type="text" name="fm_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishMoving.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Fish Moving &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td column="fr_code">Number</td>
                                <td column="fr_date">Date</td>
                                <td>From CS</td>
                                <td>To CS</td>
                                <td>Remarks</td>
                                <td>Creator</td>
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
            util.initListTable($('#list'), 'u:d:R_FishFMov_Fish Moving Report (xls)');
        </script>
        
    </body>
</html>
