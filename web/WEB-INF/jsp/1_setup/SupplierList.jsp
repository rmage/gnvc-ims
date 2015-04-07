<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Supplier List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Supplier.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Supplier</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">Supplier Code</td>
                                    <td><input type="text" name="supplier_code" value=""/></td>
                                    <td>Supplier Name</td>
                                    <td><input type="text" name="supplier_name" value="" /></td>
                                    <td style="width: 110px;"><input title="Get Master Supplier Excel Report" type="button" value="Generate Report" onclick="location.href = '/appWeb/GenerateReport.htm?action=index&item=MSupplier&type=xls';"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <span><input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" /></span>
                                        <label><input type="button" name="button" id="btnAdd" value="Add" /></label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Supplier - Search Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="supplier_code">Supplier Code</td>
                                <td column="supplier_name">Supplier Name</td>
                                <td>Is Active</td>
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
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'Supplier.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');
        </script>

    </body>

</html>