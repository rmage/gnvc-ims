<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal &therefore; IMS</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishWds.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Withdrawal &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">WDS Number</td>
                                    <td><input type="text" name="fw.wds_no"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishWds.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Withdrawal &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td>WDS No</td>
                                <td>Batch No</td>
                                <td>Supplier Name</td>
                                <td>Requested By</td>
                                <td>Approved By</td>
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
            // set initial show
            variable.show = 20;
            
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d:R_FishWDS_Withdrawal Report (xls)');
        </script>
        
    </body>
</html>
