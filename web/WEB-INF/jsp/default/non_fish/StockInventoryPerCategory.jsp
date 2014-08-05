<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Stock Inventory &therefore; per Category</title>
        <%@include file="../../metaheader.jsp" %>
    </head>
    <body>
        <%String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());%>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="GenerateReport.htm" id="generator" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="NFSIPerCat" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" id="params" name="params" value="" />

                        <input type="hidden" id="userId" name="userId" value="${ims.userId}" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Inventory &therefore; per Category</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">As Of</td>
                                    <td><input id="date" type="text" name="date" value="<%=cDate%>" /></td>
                                </tr>
                                <tr>
                                    <td>Item Category</td>
                                    <td>
                                        <select id="category" name="category">
                                            <c:forEach items="${ims.pcs}" var="x">
                                                <option value="${x.categoryCode}">${x.categoryName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Generate Report" name="btnGenerate" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script>
            $('#date').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#generator').bind('submit', function() {
                var p = $('#date').val().split('/');
                $('#params').val(p[2] + ':' + p[1] + ':' + p[0] + ':' + $('#category').val() + ':' + $('#userId').val());
            });
        </script>

    </body>
</html>
