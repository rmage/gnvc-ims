<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Stores Withdrawal &therefore; Create</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
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
                    <form action="Sws.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="swsForm" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Stores Withdrawal &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>SWS Number</td>
                                    <td>
                                        <input id="swsCode" name="swsCode" pattern="[0-9]{1,}" type="text" required>
                                        <select id="vZero" name="vZero">
                                            <option value="0">Normal</option>
                                            <option value="1">Confirmatory</option>
                                        </select>
                                    </td>
                                    <td>SWS Date</td>
                                    <td><input id="swsDate" name="swsDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <%--<c:set var="x" value="${fn:split(model.department, ':')}" />--%>
                                    <td>Department</td>
                                    <td style="width: 500px;">
                                        <%                                            if (lu.getDepartmentCode().equals("7032")) {
                                                out.print("<select id=\"departmentCode\">");
                                                out.print("<option value=\"7031\">7031 - Variable Fish Procurement</option>");
                                                out.print("<option value=\"7032\">7032 - Fixed Fish Procurement</option>");
                                                out.print("<option value=\"7033C\">7033C - Skinning / Loining</option>");
                                                out.print("<option value=\"7033D\">7033D - Packing</option>");
                                                out.print("<option value=\"7033E\">7033E - Sanitation</option>");
                                                out.print("<option value=\"7033F\">7033F - Case Up</option>");
                                                out.print("<option value=\"7033G\">7033G - Retord</option>");
                                                out.print("<option value=\"7034\">7034 - Production</option>");
                                                out.print("<option value=\"7037\">7037 - Rendering</option>");
                                                out.print("</select>");
                                            } else if (lu.getDepartmentCode().equals("7042")) {
                                                out.print("<select id=\"departmentCode\">");
                                                out.print("<option value=\"7042\">7042 - Inventory Management</option>");
                                                out.print("<option value=\"7040\">7040 - Fixed Labeling</option>");
                                                out.print("<option value=\"7037\">7039 - Variable Labeling</option>");
                                                out.print("</select>");
                                            } else {
                                                String[] x = ((HashMap) request.getAttribute("model")).get("department").toString().split(":");
                                                out.print("<input id=\"departmentCode\" size=\"4\" type=\"text\" value=\"" + x[0] + "\" readonly required> " + x[1]);
                                            }
//                                            LoginUser lu = (LoginUser) request.getSession().getAttribute("user");
                                        %>
                                    </td>
                                    <td>Info</td>
                                    <td>
                                        <input type="text" id="swsInfo" name="swsInfo" size="50" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('Sws.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Stores Withdrawal &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select Item</td>
                                    <td colspan="6">
                                        <select id="mode" name="mode">
                                            <option value="code">By Code</option>
                                            <option value="name">By Name</option>
                                        </select>
                                        <input id="item" name="item" size="50" type="text" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>Action</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Type</td>
                                    <td>Stock On Hand</td>
                                    <td>Quantity</td>
                                    <td>Uom</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
                        </table>
                    </form>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            /* BIND | element event */
            $('#swsDate').datepicker({dateFormat: "dd/mm/yy"}).datepicker("setDate", new Date());
            $('.delete').live('click', function() {
                $(this).parent().parent().remove();
            });

            var $i = $('#swsInfo');
            $('#vZero').bind('change', function() {
                if ($(this).val() === '1') {
                    if ($i.val().indexOf('CONFIRMATORY; ') < 0) {
                        $i.val('CONFIRMATORY; ' + $i.val());
                    }
                } else {
                    if ($i.val().indexOf('CONFIRMATORY; ') >= 0) {
                        $i.val($i.val().replace('CONFIRMATORY; ', ''));
                    }
                    $('#main tr td:nth-child(5)').each(function() {
                        if (parseFloat($(this).html()) <= 0) {
                            $(this).parent().remove();
                        }
                    });
                }
            });

            $("#mode").bind("change", function() {
                $('#item').autocomplete("option", "source", "?action=getProduct&mode=" + $(this).val());
            });

            $('#item').autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(event, ui) {
                    if (parseFloat(ui.item.soh) > 0 || $('#vZero').val() === '1') {
                        $('#main').append('<tr><td><input class="delete ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Delete" style="font-size: smaller;"></td><td>' +
                                ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.type + '</td><td>' + numberWithCommas(ui.item.soh) +
                                '</td><td><input size="3" type="text" style="font-size: smaller;" required="true" /></td><td>' + ui.item.uom + '</td></tr>');
                        $('#main tr:last input[type="text"]').focus();
                        $('#item').val(null);
                    } else {
                        return false;
                    }
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName +
                                '</b><br /> Stock on Hand : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };

            $('#swsForm').bind('submit', function() {
                if ($('#main tr').length !== 0) {
                    if ($('#vZero').val() === '1' && $i.val().indexOf('CONFIRMATORY; ') < 0) {
                        $i.val('CONFIRMATORY; ' + $i.val());
                    }
                    if (confirm("Continue to save this document?")) {
                        $('#poster').append('<input name="master" type="hidden" value="' + $('#swsCode').val() + ':' + $('#swsDate').val() + ':' +
                                $('#swsInfo').val() + ':' + $('#departmentCode').val() + '" />');

                        var i = 0;
                        $('#main tr').each(function() {
                            $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('td:eq(1)').html() + ':' +
                                    parseFloat($(this).find('td:eq(4)').html()) + ':' + $(this).find('input[type="text"]').val() + ':' +
                                    $(this).find('td:eq(6)').html() + ':' + i + '" />');
                            i++;
                        });
                        $('#poster').submit();
                    }
                }

                return false;
            });

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
