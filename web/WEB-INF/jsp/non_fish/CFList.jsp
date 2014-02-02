<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Canvassing Form</title>
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
                    <form action="GenerateReport.htm" method="post">
                        <input name="action" type="hidden" value="index" />
                        <input name="item" type="hidden" value="PCanvassingForm" />
                        <input name="type" type="hidden" value="xls" />
                        <table class="collapse tblForm row-select">
                            <caption>Pick Supplier</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Supplier</td>
                                    <td>
                                        <select name="params" required="true">
                                            <option value="">-- Pick Supplier --</option>
                                            <c:forEach items="${model.supplier}" var="x">
                                                <option value="${x.supplierCode}">${x.supplierCode} :: ${x.supplierName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2"><input type="submit" value="Generate" /></td>
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
            
        </script>
    </body>
</html>
