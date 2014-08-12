<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &therefore; Product</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Product.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Product &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td>Product Code</td>
                                    <td><input type="text" name="product_code" /></td>
                                    <td>Product Name</td>
                                    <td><input type="text" name="product_name" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    <input type="button" name="button" id="btnAdd" value="Add" onclick="window.location.replace('Product.htm?action=create')" />
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Product &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 75px">Action</td>
                                <td column="product_code">Product Code</td>
                                <td column="product_name">Product Name</td>
                                <td>Category</td>
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
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');
            
            <%
                if (((LoginUser) request.getSession().getAttribute("user")).getRoleCode().contains("7050")) {
                    out.println("util.tableListAction(1200, 'Product &therefore; In Last 20 Purchase Order &therefore; ');");
                }
            %>
            
        </script>
    </body>
</html>