<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Purchase Requisition List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%            com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="PurchaseRequisition.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Purchase Requisition</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        PRS No
                                    </td>
                                    <td>
                                        <input type="text" name="prsnumber" value="${model.purchaseNo}"/>
                                    </td>
                                    <td width="20%">
                                        PRS Date
                                    </td>
                                    <td>
                                        <input type="text" name="prsdate" value="${model.estimationDeliveryDate}" id="estimationDeliveryDate" />
                                    </td>

                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="6">
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
                        <caption>PRS - List</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td>Action</td>
                                <td column="prsnumber">PRS No</td>
                                <td column="prsdate">PRS Date</td>  
                                <td>Charge to Department</td> 
                                <td>Date Needed</td>
                                <!--<td>Status Approved</td>-->
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot>
                            <td colspan="10">
                                <span>

                                </span>
                            </td>
                        </tfoot>
                    </table>
                    <div id="ab_dialog" style="display: none">
                        <span id="ab_dialog_doc"></span>: Are you sure?
                    </div>

                    <script>
                        $('.mark').click(function() {
                            var code = $(this).attr('code');
                            $('#ab_dialog_doc').html($(this).attr('code'));
                            $('#ab_dialog').dialog({
                                buttons: {
                                    "Close": function() {
                                        $(this).dialog("close");
                                    },
                                    "Ok": function() {
                                        window.location.href = 'PurchaseRequisition.htm?action=approvedPRS&prsNo=' + code;
                                    }
                                },
                                modal: true,
                                width: 500,
                                height: 150,
                                'title': 'PRS Approved'
                            });
                        });
                    </script>

                    <script>
                        $('.markb').click(function() {
                            var code = $(this).attr('code');
                            $('#ab_dialog_doc').html($(this).attr('code'));
                            $('#ab_dialog').dialog({
                                buttons: {
                                    "Close": function() {
                                        $(this).dialog("close");
                                    },
                                    "Ok": function() {
                                        window.location.href = 'PurchaseRequisition.htm?action=cancelPRS&prsNo=' + code;
                                    }
                                },
                                modal: true,
                                width: 500,
                                height: 150,
                                'title': 'PRS Cancel'
                            });
                        });
                    </script>

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
                    location.href = 'PurchaseRequisition.htm?action=create';
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
            util.initListTable($('#list'));
        </script>

    </body>
</html>