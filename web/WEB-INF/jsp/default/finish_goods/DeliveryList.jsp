<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delivery &therefore; IMS</title>
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
                    <form action="FGDelivery.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Delivery &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Delivery Number</td>
                                    <td><input type="text" name="dr_code" /></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('FGDelivery.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Delivery &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 60px">Action</td>
                                <td column="dr_code">Delivery Code</td>
                                <td column="dr_date">Delivery Date</td>
                                <td>From</td>
                                <td>To</td>
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
            util.initListTable($('#list'), 'u:d:R_Delivery Report (xls)');
        </script>
    </body>
</html>
