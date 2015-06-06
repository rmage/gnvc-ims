<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Product Category List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <%        int row = 0;
        java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
    %>   
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="ProductCategory.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Product Category</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">Category Code</td>
                                    <td><input type="text" name="category_code" value=""/></td>

                                    <td>Category Name</td>
                                    <td><input type="text" name="category_name" value="" /></td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr>

                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="10">
                                <span>
                                    <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                </span>
                                <label>
                                    <input type="button" name="button" id="btnAdd" value="Add" />
                                </label>
                            </td>
                            </tfoot>
                        </table>
                    </form>

                    <table class="collapse tblForm row-select" id="list">
                        <caption>Product Category - Search Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="category_code">Category Code</td>
                                <td column="category_name">Category Name</td>
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
                    location.href = 'ProductCategory.htm?action=create';
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