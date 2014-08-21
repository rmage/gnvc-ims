<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Location &therefore; Update</title>
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
                    <form action="FGLocation.htm" id="search" method="post">
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="mode" value="edit" />
                        <input type="hidden" name="key" value="${ims.loca_id}" />
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Location</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Location Code<span style="float: right">*</span></td>
                                    <td><input type="text" id="locationCode" name="locationCode" value="${ims.loca_code}" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Location Name<span style="float: right">*</span></td>
                                    <td><input type="text" id="locationName" name="locationName" value="${ims.loca_name}" required="required" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnUpdate" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGLocation.htm');" />
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
            
        </script>
    </body>
</html>
