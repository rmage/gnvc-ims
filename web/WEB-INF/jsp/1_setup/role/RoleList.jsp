<%@page import="com.app.wms.engine.db.dto.UserRole"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - User Role List</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <% //            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");

        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search User</div -->
                <div class="box">
                    <form action="UserRole.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search User Role</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Role Code
                                    </td>
                                    <td>
                                        <input type="text" name="role_code" value=""/>
                                    </td>
                                    <td width="20%">
                                        Role Name
                                    </td>
                                    <td>
                                        <input type="text" name="role_name" value=""/>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span>
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" />
                                        </label>

                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>User Role - List</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="role_code">Role Code</td>
                                <td column="role_name">Role Name</td>
                                <td>Department</td>
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
                    location.href = 'UserRole.htm?action=create';
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
                //$('.tblForm tbody').each(function() {
                //    if($(this).attr('id') != 'main') {
                //        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                //        $(this).find('.checkbox').addClass('span-2');
                //    }
                //});
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');
        </script>
        <!-- ini end if script untuk button -->
        <!-- smp sini di cut -->
    </body>
</html>