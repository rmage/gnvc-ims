<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Pallet Rejection</title>
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
                    <form action="FGPalletRejection.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Rejection &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Rejection Number</td>
                                    <td><input type="text" name="reje_code" /></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGPalletRejection.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Pallet Rejection &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="reje_code">Rejection Number</td>
                                <td column="reje_date">Rejection Date</td>
                                <td>PTS Number</td>
                                <td>Rejection Type</td>
                                <td>Reason</td>
                                <td>Remarks</td>
                                <td>Created By</td>
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
            util.initListTable($('#list'), 'R_Pallet Rejection Report (xls)');
        </script>
    </body>
</html>
