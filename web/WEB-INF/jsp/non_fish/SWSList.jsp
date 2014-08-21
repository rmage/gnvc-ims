<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Stores Withdrawal</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="search" action="ReceiveReport.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Stores Withdrawal &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">SWS Number</td>
                                    <td><input type="text" name="sws_code" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Sws.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table id="list" class="collapse tblForm row-select">
                        <caption>Stores Withdrawal &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td column="sws_code">SWS Number</td>
                                <td column="sws_date">SWS Date</td>
                                <td column="department_code">Department Code</td>
                                <td>Department Name</td>
                                <td>Info</td>
                                <td>Creator</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script>
            util.initSearchForm($('#search'));

            //  PRINTOUT | Change to NFSwsP for this department
            var cDepartment = "<%=((LoginUser) request.getSession().getAttribute("user")).getDepartmentCode()%>";
            var aDepartment = ["0000"];
            if ($.inArray(cDepartment, aDepartment) === -1) {
                util.initListTable($('#list'), 'R_NFSws_Stores Withdrawal Slip (xls)');
            } else {
                util.initListTable($('#list'), 'R_NFSwsP_Stores Withdrawal Slip (xls)');
            }

            /* jQuery | Binding event */
            $('.d').live('click', function() {
                fyaQPanel($(this).html());
            });

            function fyaQPanel(p) {
                if (p === 0) {
                    $('#fyaQPanel').fadeOut(300, function() {
                        $('#fyaQPanel').html(null);
                    });
                } else {
                    $('#fyaQPanel').fadeIn(300, function() {
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'Sws.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>SWS Number : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>Item Code</th><th>Item Name</th><th>Type</th><th>Stock on Hand</th>'
                                        + '<th>Quantity</th><th>Uom</th></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for (var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemCode + '</td><td>' + json[i].itemName +
                                            '</td><td>' + json[i].type + '</td><td>' + numberWithCommas(json[i].soh) +
                                            '</td><td>' + numberWithCommas(json[i].qty) + '</td><td>' + json[i].uom + '</td></tr>');
                                }
                            },
                            complete: function() {
                                if ($('#fyaQPanel').html() === '')
                                    fyaQPanel(0);
                            }
                        });
                    });
                }
            }

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>
