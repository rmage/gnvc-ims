<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create &therefore; Custom Code &therefore; IMS</title>
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
                    <form action="HsCode.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="hsCodeForm" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Custom Code &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Code</td>
                                    <td><input type="text" id="hscode_code" name="hscode_code" size="50" required></td>
                                </tr>
                                <tr>
                                    <td>Name</td>
                                    <td><input type="text" id="hscode_name" name="hscode_name" size="50" required></td>
                                </tr>
                                <tr>
                                    <td>Unit of Measurement</td>
                                    <td>
                                        <select id="hscode_uom" name="hscode_uom" required>
                                            <c:forEach items="${model.uomList}" var="x">
                                                <option value="${x.uomCode}">${x.uomCode}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Type</td>
                                    <td>
                                        <select id="hscode_type" name="hscode_type" required>
                                            <c:forEach items="${model.productType}" var="x">
                                                <option value="${x}">${x}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('HsCode.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
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
            $('#hsCodeForm').bind('submit', function() {
                var data = $('#hscode_code').val() + ':s:' + $('#hscode_name').val() + ':s:' + $('#hscode_uom').val() + ':s:' + $('#hscode_type').val() + ':s::se:';

//                $('#main tr').each(function() {
//                    data = data + $(this).find('td:eq(1)').html() + ':s:' + $(this).find('td:eq(4)').html().replace(/,/g, '') + ':s:' + $(this).find('input[type="text"]').val() + ':s:' + $(this).find('td:eq(6)').html() + ':s::se:';
//                });

                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });

                return false;
            });

            /* FYA | November 26, 2014 | stores withdrawal number request */
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
