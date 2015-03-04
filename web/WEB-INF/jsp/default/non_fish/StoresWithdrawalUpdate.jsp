<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Stores Withdrawal &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
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
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="swsForm" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Stores Withdrawal &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>SWS Number</td>
                                    <td>
                                        <input id="swsCode" name="swsCode" pattern="[0-9]{1,}" type="text" value="${model.sws[0].sws_code}" required readonly>
                                        <select id="vZero" name="vZero">
                                            <option value="0">Normal</option>
                                            <option value="1">Confirmatory</option>
                                        </select>
                                    </td>
                                    <td>SWS Date</td>
                                    <td><input id="swsDate" name="swsDate" size="10" type="text" required></td>
                                </tr>
                                <tr>
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
                                                out.print("<option value=\"7039\">7039 - Variable Labeling</option>");
                                                out.print("</select>");
                                            } else {
                                                String[] x = ((HashMap) request.getAttribute("model")).get("department").toString().split(":");
                                                out.print("<input id=\"departmentCode\" size=\"4\" type=\"text\" value=\"" + x[0] + "\" readonly required> " + x[1]);
                                            }
                                        %>
                                    </td>
                                    <td>Info</td>
                                    <td><input type="text" id="swsInfo" name="swsInfo" size="50" value="${model.sws[0].sws_info}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Update" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('Sws.htm');" />
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
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.sws}" var="x">
                                    <tr data-id="${x.id}">
                                        <td><input class="delete ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Delete" style="font-size: smaller;"></td>
                                        <td>${x.product_code}</td>
                                        <td>${x.product_name}</td>
                                        <td>${x.product_category}</td>
                                        <td>${x.soh}</td>
                                        <td><input size="3" type="text" style="font-size: smaller;" required="true" value="${x.qty}"></td>
                                        <td>${x.uom}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
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
            // INIT | value
            $('#swsDate').val(gnvs.util.toViewDate('${model.sws[0].sws_date}'));
            $('#departmentCode').val('${model.sws[0].department_code}');
            if ('${model.sws[0].sws_info}'.indexOf('CONFIRMATORY') > -1) {
                $('#vZero').val(1);
            }

            /* BIND | element event */
            $('#swsDate').datepicker({dateFormat: "dd/mm/yy"});
            $('.delete').live('click', function() {
                var $tr = $(this).parent().parent();

                if ($tr.attr('data-status') === undefined) {
                    $tr.attr('data-status', 'D').hide();
                } else if ($tr.attr('data-status') === 'C') {
                    $tr.remove();
                }
            });
            $('#main tr input[type="text"]').bind('focus', function() {
                $(this).parent().parent().attr('data-status', 'U');
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
            
            var idCounter = 0;
            $('#item').autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(event, ui) {
                    if (parseFloat(ui.item.soh) > 0 || $('#vZero').val() === '1') {
                        $('#main').append('<tr data-id="' + idCounter + '" data-status="C"><td><input class="delete ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Delete" style="font-size: smaller;"></td><td>' +
                                ui.item.itemCode + '</td><td>' + ui.item.itemName + '</td><td>' + ui.item.type + '</td><td>' + numberWithCommas(ui.item.soh) +
                                '</td><td><input size="3" type="text" style="font-size: smaller;" required="true"></td><td>' + ui.item.uom + '</td></tr>');
                        $('#main tr:last input[type="text"]').focus();
                        $('#item').val(null);
                        idCounter = idCounter - 1;
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
                var data = '';
                var header = $('#swsCode').val() + ':s:' + gnvs.util.toDBDate($('#swsDate').val()) + ':s:' + $('#swsInfo').val() + ':s:' + $('#departmentCode').val() + ':s:';

                $('#main tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(1)').html() + ':s:' + parseFloat($(this).find('td:eq(4)').html()) + ':s:' + $(this).find('input[type="text"]').val() + ':s:' + $(this).find('td:eq(6)').html() + ':s::se:';
                });

                if (confirm('Update Stores Withdrawal #' + $('#swsCode').val() + ' ?')) {
                    if (data === '') {
                        data = header + 'X:s:-1:s::se:';
                    }

                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
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
