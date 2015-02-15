<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Canvassing Assignment - IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
                    <form action="CanvassingAssignment.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Canvasser</caption>
                            <thead>
                                <tr>
                                    <td>PRS Number</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Quantity</td>
                                    <td>Canvasser</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="canvasser">
                                <c:forEach items="${model.acs}" var="x">
                                    <tr data-id="${x.id}">
                                        <td>${x.prsnumber}</td>
                                        <td>${x.product_code}</td>
                                        <td>${x.product_name}</td>
                                        <td>${x.qty}</td>
                                        <td>
                                            <select data-value="${x.user_id}" id="userId" name="userId">
                                                <c:forEach items="${model.cs}" var="y">
                                                    <option value="${y.userId}">${y.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input type="submit" value="Update" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('CanvassingAssignment.htm');" />
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
            /* jQuery | Binding event */
            //  GNVS | bulk select from first select
            $('select[name="userId"]:eq(0)').live('change', function() {
                $('#canvasser select').each(function(i) {
                    if (i === 0) {
                        $('#canvasser').data('bulk', $(this).val());
                    } else {
                        $(this).val($('#canvasser').data('bulk'));
                    }
                });
            });

            // AUTO | assign select value based on @data
            $('select[data-value]').each(function() {
                $(this).val($(this).data('value'));
            });

            // 2015 Update | by FYA
            $('form').bind('submit', function() {
                var data = '';
                $('#canvasser tr').each(function() {
                    data = data + $(this).data('id') + ':s:' + $(this).find('select').val() + ':s::se:';
                });
                
                if (data !== '') {
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
        </script>
    </body>
</html>
