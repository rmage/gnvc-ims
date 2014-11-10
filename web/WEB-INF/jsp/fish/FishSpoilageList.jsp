<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Spoilage</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishSpoilageData.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Spoilage &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Batch Number</td>
                                    <td><input type="text" name="fv.batch_no"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishSpoilageData.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Fish Spoilage &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td>Batch Number</td>
                                <td>Date Shift</td>
                                <td>Time Shift</td>
                                <td>Cooked Weight</td>
                                <td>Raw Weight</td>
                                <td>Processed Weight</td>
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
            util.initListTable($('#list'), 'R_FishSR_Spoilage Report (xls)_1-2-3');
        </script>
        
    </body>
</html>
