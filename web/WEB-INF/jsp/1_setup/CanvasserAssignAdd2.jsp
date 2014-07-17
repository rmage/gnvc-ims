<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Canvassing Assignment</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
                    <form action="CanvassingAssignment.htm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <input id="prsNo" name="prsNo" type="hidden" />
                        <table class="collapse tblForm row-select">
                            <caption>Assignment</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <%--<td style="width: 200px;">PRS No</td>
                                    <td>
                                        <select id="prsNo" name="prsNo" required="true">
                                            <option value="">-- Pilih PRS --</option>
                                            <c:forEach var="droplist" items="${requestScope.model.dropListPrs}">
                                                <option value="${droplist.prsnumber}">
                                                    ${droplist.departmentName} :: <fmt:formatDate pattern="yyyy-MM-dd" value="${droplist.prsdate}" /> :: ${droplist.prsnumber}
                                                </option> 
                                            </c:forEach>
                                        </select>
                                    </td>--%>
                                    <td>
                                        <table style="width: 1230px;">
                                            <thead>
                                                <tr>
                                                    <td style="width: 33%;">
                                                        Department
                                                        <input type="text" style="float: right" onkeyup="filterList(1, this.value)" />
                                                    </td>
                                                    <td style="width: 33%;">PRS Date</td>
                                                    <td style="width: 28%;">
                                                        PRS Number
                                                        <input type="text" size="10" style="float: right" onkeyup="filterList(3, this.value)" />
                                                    </td>
                                                    <td>Action</td>
                                                </tr>
                                            </thead>
                                        </table>
                                        <div style="height: 150px; overflow-y: auto;">
                                            <table style="width: 1230px;">
                                                <tbody id="prslist">
                                                    <c:forEach var="droplist" items="${requestScope.model.dropListPrs}">
                                                        <tr>
                                                            <td style="width: 33%;">${droplist.departmentName}</td>
                                                            <td style="width: 33%;"><fmt:formatDate pattern="dd/MM/yyyy" value="${droplist.prsdate}" /></td>
                                                            <td style="width: 28%;">${droplist.prsnumber}</td>
                                                            <td><img class="search" src="resources/images/search.png" title="Get PRS Detail" data-prscode="${droplist.prsnumber}" /></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td>
                                        <input type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('CanvassingAssignment.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
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

            //  search prs detail button
            $(".search").bind('click', function() {
                $('#prsNo').val($(this).data('prscode'));
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: 'CanvassingAssignment.htm',
                    data: {action: 'getPrs', key: $(this).data('prscode')},
                    dataType: 'json',
                    success: function(json) {
                        $('#canvasser').html('');
                        for (var i = 0; i < json.length; i++) {
                            $('#canvasser').append('<tr><td>' + json[i].prsNo + '</td><td>' + json[i].itemCode
                                    + '</td><td>' + json[i].itemName + '</td><td>' + json[i].quantity
                                    + '</td><td>' + json[i].canvasser + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                    }
                });
            });
            
            // filter unassign prs list
            var $trObj = $('#prslist tr');
            function filterList(i, keyword) {
                if(keyword === '') {
                    $trObj.filter(':hidden').show();
                } else {
                    $trObj.filter(':visible').hide();
                    $trObj.find('td:nth-child(' + i + '):contains("' + keyword + '")').parent().show();
                }
            }
        </script>
    </body>
</html>
