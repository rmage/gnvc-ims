<%@page import="com.app.wms.engine.db.dto.Uom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Unit of Measurement List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Uom.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search UOM</caption>
                            <tbody>
                                <tr>
                                    <td>UOM Code</td>
                                    <td><input type="text" name="uom_code" value=""/></td>
                                    <td>UOM Name</td>
                                    <td><input type="text" name="uom_name" value="" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <span>
                                            <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        </span>
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" onclick="window.location.replace('Uom.htm?action=create')" />
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list"> 
                        <caption>Unit of Measurement - Search Result</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 75px">Action</td>
                                <td column="uom_code">UoM Code</td>
                                <td column="uom_name">UoM Name</td>
                                <td>Remarks</td>
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
        </script>
    </body>
</html>