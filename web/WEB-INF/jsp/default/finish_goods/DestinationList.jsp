<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Destination</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FGDestination.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Destination &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Name</td>
                                    <td><input type="text" name="destination_name" /></td>
                                    <td style="width: 200px;">Description</td>
                                    <td><input type="text" name="destination_description" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGDestination.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Finished Goods Destination &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="destination_name">Name</td>
                                <td column="destination_description">Description</td>
                                <td>Created By</td>
                                <td>Created Date</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');
        </script>
    </body>
</html>
