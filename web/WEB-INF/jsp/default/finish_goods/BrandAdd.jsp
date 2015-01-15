<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Brand &therefore; Create</title>
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
                    <form action="FGBrand.htm" id="form" method="post">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="mode" value="insert">
                        <input type="hidden" id="data" name="data" value="">
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Brand</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Buyer Name<span style="float: right">*</span></td>
                                    <td>
                                        <select id="buyerId" name="buyerId" required>
                                            <option value="">-- select buyer --</option>
                                            <c:forEach items="${ims.bs}" var="x">
                                                <option value="${x.buyer_id}">${x.buyer_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Brand Name<span style="float: right">*</span></td>
                                    <td><input type="text" id="brandName" maxlength="100" name="brandName" size="50" required></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGBrand.htm');" />
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
            $('#form').bind('submit', function() {
                $('#data').val($('#buyerId').val() + '`' + $('#brandName').val() + '`^');
            });
        </script>
    </body>
</html>
