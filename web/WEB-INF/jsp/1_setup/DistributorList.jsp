<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Supplier List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Distributor.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Distributor</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Distributor Code
                                    </td>
                                    <td>
                                        <input type="text" name="distributor_code" value=""/>
                                    </td>
                                    <td>
                                        Distributor Name
                                    </td>
                                    <td>
                                        <input type="text" name="distributor_name" value="" />
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
                        <caption>Distributor - Search Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="distributor_code">Distributor Code</td>
                                <td column="distributor_name">Distributor Name</td>
                                <td>Distributor Address</td>
                                <td>Fax</td>
                                <td>Email</td>
                                <td>Telephone</td>
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
                    location.href = 'Distributor.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('#btnPrintAll').click(function() {
                    window.location = '<c:out value="${urlPrintAll}"/>';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');


                $('#estimationDeliveryDate').datepicker({
                    dateFormat: "dd/mm/yy"
                });

            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'),'u:d');
        </script>

    </body>

</html>