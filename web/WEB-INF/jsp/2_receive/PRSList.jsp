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
                                        <input type="text" name="prsnumber" />
                                    </td>
                                    <td width="20%">
                                        PRS Date
                                    </td>
                                    <td>
                                        <input id="prsdate" type="text" name="prsdate" />
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
                                <td style="width: 15px;">No</td>
                                <td style="width: 75px;">Action</td>
                                <td column="prsnumber">PRS No</td>
                                <td column="prsdate">PRS Date</td>  
                                <td>Charge to Department</td> 
                                <td>Date Needed</td>
                                <td>Creator</td>
                                <td>Approval</td>
                                <td>Status</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                    <div id="ab_dialog" style="display: none">
                        <span id="ab_dialog_doc"></span>: Are you sure?
                    </div>

                    <script>
//                        $('.mark').click(function() {
//                            var code = $(this).attr('code');
//                            $('#ab_dialog_doc').html($(this).attr('code'));
//                            $('#ab_dialog').dialog({
//                                buttons: {
//                                    "Close": function() {
//                                        $(this).dialog("close");
//                                    },
//                                    "Ok": function() {
//                                        window.location.href = 'PurchaseRequisition.htm?action=approvedPRS&prsNo=' + code;
//                                    }
//                                },
//                                modal: true,
//                                width: 500,
//                                height: 150,
//                                'title': 'PRS Approved'
//                            });
//                        });
                    </script>

                    <script>
//                        $('.markb').click(function() {
//                            var code = $(this).attr('code');
//                            $('#ab_dialog_doc').html($(this).attr('code'));
//                            $('#ab_dialog').dialog({
//                                buttons: {
//                                    "Close": function() {
//                                        $(this).dialog("close");
//                                    },
//                                    "Ok": function() {
//                                        window.location.href = 'PurchaseRequisition.htm?action=cancelPRS&prsNo=' + code;
//                                    }
//                                },
//                                modal: true,
//                                width: 500,
//                                height: 150,
//                                'title': 'PRS Cancel'
//                            });
//                        });
                    </script>

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script type="text/javascript">
//            $('.d').live('click', function(){
//                fyaQPanel($(this).html());
//            });
//            function fyaQPanel(p) {
//                if(p === 0) {
//                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
//                } else {
//                    $('#fyaQPanel').fadeIn(300, function(){
//                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
//                        $.ajax({
//                            url: 'ReceiveReport.htm',
//                            data: {action: 'ajaxDocument', key: p},
//                            dataType: 'json',
//                            success: function(json) {
//                                $('#fyaQPanel').html(null);
//                                $('#fyaQPanel').append('<h6>RR Number : ' + p + '</h6><hr />');
//                                $('#fyaQPanel').append('<table><thead><tr><th>Item Code</th><th>Item Name</th><th>Department</th><th>Quantity Good</th>'
//                                    + '<th>Quantity Bad</th><th>UoM</th></thead><tbody id="fyaQPanelBody"></tbody></table>');
//                                for(var i = 0; i < json.length; i++) {
//                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemCode + '</td><td>' + json[i].itemName 
//                                        + '</td><td>' + json[i].department + '</td><td>' + json[i].qtyGood
//                                        + '</td><td>' + json[i].qtyBad + '</td><td>' + json[i].uom + '</td></tr>');
//                                }
//                            },
//                            complete: function() {
//                                if($('#fyaQPanel').html() === '')
//                                    fyaQPanel(0);
//                            }
//                        });
//                    }); 
//                }
//            }
//            
//            $(function() {
            $('#btnAdd').click(function() {
                location.href = 'PurchaseRequisition.htm?action=create';
            });
//
//                $('#btnEdit').click(function() {
//                    location.href = '';
//                });
//
//                $('#btnPrintAll').click(function() {
//                    window.location = '<c:out value="${urlPrintAll}"/>';
//                });
//
//                $('.tab').hide();
//                $('#btnEdit').click(function() {
//                    $('.tab').show();
//                });
//
//                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
//
//
//                $('#estimationDeliveryDate').datepicker({
//                    dateFormat: "dd/mm/yy"
//                });
//
//            });
            $('#prsdate').datepicker({dateFormat: "dd/mm/yy"});
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d:R_PRCPrs_Print Purchase Requisition Slip (xls)');
            util.tableListAction(800, "Purchase Requisition &therefore; Detail &therefore; ");

        </script>

    </body>
</html>