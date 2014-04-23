<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - User List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <% //            com.app.web.engine.search.UserSearch criteria = new com.app.web.engine.search.UserSearch();
//            if (request.getSession().getAttribute("UserSearch") != null) {
//                criteria = (com.app.web.engine.search.UserSearch) request.getSession().getAttribute("UserSearch");
//            }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="User.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search User</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        User Id
                                    </td>
                                    <td>
                                        <input type="text" name="user_id" value=""/>
                                    </td>
                                    <td width="20%">
                                        User Name
                                    </td>
                                    <td>
                                        <input type="text" name="username" value="" />
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
                        <caption>User - List</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="user_id">User Id</td>
                                <td column="username">User Name</td>
                                <td>Name</td>
                                <td>Role Code</td>
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
                    location.href = 'User.htm?action=create';
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