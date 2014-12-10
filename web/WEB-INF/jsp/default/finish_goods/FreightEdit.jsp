<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Freight &therefore; Update</title>
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
                    <form action="FGFreight.htm" id="form" method="post">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="mode" value="edit">
                        <input type="hidden" name="key" value="${ims.freight_id}">
                        <input type="hidden" id="data" name="data" value="">
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Freight</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Code<span style="float: right">*</span></td>
                                    <td><input type="text" id="freightCode" maxlength="50" name="freightCode" size="20" value="${ims.freight_code}" ></td>
                                </tr>
                                <tr>
                                    <td>Description<span style="float: right">*</span></td>
                                    <td><input type="text" id="freightDescription" maxlength="100" name="freightDescription" size="50" value="${ims.freight_description}" required></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnUpdate" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGFreight.htm');" />
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
                $('#data').val($('#freightCode').val() + '`' + $('#freightDescription').val() + '`^');
            });
        </script>
    </body>
</html>
