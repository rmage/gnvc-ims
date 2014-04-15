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
                                    <td width="20%">
                                        UOM Code
                                    </td>
                                    <td>
                                        <input type="text" name="uom_code" value=""/>
                                    </td>
                                    <td>
                                        UOM Name
                                    </td>
                                    <td>
                                        <input type="text" name="uom_name" value="" />
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr>

                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="4">
                                <span>
                                    <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                </span>
                                <label>
                                    <input type="button" name="button" id="btnAdd" value="Add" />
                                </label>
                            </td>
                            <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list"> 
                        <caption>Unit of Measurement - Search Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
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
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'Uom.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
                // adjust width for tables //
                $('.tblForm tbody').each(function() {
                    if ($(this).attr('id') != 'main') {
                        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                        $(this).find('.checkbox').addClass('span-2');
                    }
                });
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');
        </script>
    </body>
</html>