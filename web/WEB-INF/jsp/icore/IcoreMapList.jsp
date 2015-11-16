<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item Code Mapping &therefore; IMS</title>
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
                    <table class="collapse tblForm row-select">
                        <caption>Item Filter</caption>
                        <tbody>
                            <tr>
                                <td style="width: 200px;">Item Category</td>
                                <td>
                                    <select id="itemType">
                                        <option value="">-- Select Item Category --</option>
                                        <c:forEach items="${model.pc}" var="x">
                                            <option value="${x.category_code}">${x.category_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>                                
                            </tr>
                        <td>
                            <input id="fishProduct" name="fishProduct" type="text" hidden="true"/>
                        </td>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="2">
                                    <input id="btnSearch" type="button" value="Search Item" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <table class="collapse tblForm row-select">
                        <caption>Item List</caption>
                        <thead>
                            <tr>
                                <td>Item Code/ID</td>
                                <td>Item Name</td>
                                <td>Item Type</td>
                                <td>Uom</td>
                                <td>Custom Code</td>
                                <td>Custom Name</td>
                                <td style="width: 100px;">Status</td>
                                <td>Is Wastes?</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            $('#itemType').bind('change', function() {
                if ($(this).val() === 'FISH') {
                    $('#fishProduct').val($('#itemType').val());
                } else if ($(this).val() === 'RENDERING ITEM') {
                    $('#fishProduct').val($('#itemType').val());
                } else if ($(this).val() === 'FINISHED GOODS') {
                    $('#fishProduct').val($('#itemType').val());
                } else {
                    $('#fishProduct').val('PRODUCT');
                }
            });


            var sNone = '<span style="font-weight: bold;">-</span>';
            var sFocus = '<span style="color: blue; font-weight: bold;">Updating</span>';
            var sSave = '<span style="color: red; font-weight: bold;">Saving</span>';
            var sDone = '<span style="color: green; font-weight: bold;">Done</span>';

            $('#main input').live('focus', function() {
                $(this).parent().next().next().html(sFocus);
            });

            var isFocus = 0;
            $('#main input').live('click', function() {
                isFocus = 1;
            });
            $('#main input').live('keyup', function() {
                $(this).data('u', true);
            });

            $('#main input').live('blur', function() {
                $o = $(this);
                setTimeout(function() {
                    if ($o.data('u') && isFocus === 0) {
                        $o.data('u', false);

                        var regexp = /^\d+(\.\d{1,})?$/;
                        if (!regexp.test($o.val())) {
                            return;
                        }
                        $o.parent().next().next().html(sSave);

                        $.ajax({
                            url: 'IcoreMap.htm',
                            data: {action: 'insertNUpdate', code: $o.parent().prev().prev().prev().prev().html(), hscode: $o.val(), scPro: $('#fishProduct').val()},
                            dataType: 'json',
                            success: function(json) {
                                if (json.s) {
                                    $o.val(json.hscode);
                                    $o.parent().next().next().html(sDone);
                                }
                            }
                        });
                    } else {
                        $o.parent().next().next().html(sNone);
                    }
                }, 600);
            });

            $('.item').live("keydown.autocomplete", function() {
                $(this).autocomplete({
                    source: '?action=getHsCodeList',
                    minLength: 2,
                    select: function(event, ui) {
                        $(this).val(ui.item.code);
                        $(this).parent().next().html(ui.item.name);

                        isFocus = 0;

                        return false;
                    }
                }).data('autocomplete')._renderItem = function(ul, item) {
                    return $('<li>')
                            .data("item", item)
                            .append('<a><b>' + item.code + ' : ' + item.name + '</b></a></li>')
                            .appendTo(ul);
                };
            });


            $('#btnSearch').bind('click', function() {
                if (!$('#itemType').val())
                    return;

                var $o = $(this);
                $o.attr('disabled', true);
                $('#itemType').attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                $('#main').html(null);
                $.ajax({
                    url: 'IcoreMap.htm',
                    data: {action: 'getProductList', key: $('#itemType').val(), soc: $('#fishProduct').val()},
                    dataType: 'json',
                    success: function(json) {
                        for (var i = 0; i < json.rows.length; i++) {
                            $('#main').append('<tr><td>' + json.rows[i].code + '</td><td>' + json.rows[i].name + '</td><td>' + json.rows[i].type + '</td><td>' + json.rows[i].uom
                                    + '</td><td><input class="item" name="codeList" type="text" value="' + json.rows[i].hscode + '"/></td><td>' + json.rows[i].hsname + '</td><td>' + sNone + '</td>'
                                    + '<td class="center"><input type="checkbox" class="is_wastes"></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        $('#itemType').attr('disabled', false);
                    }
                });

                return false;
            });

        </script>
    </body>
</html>
