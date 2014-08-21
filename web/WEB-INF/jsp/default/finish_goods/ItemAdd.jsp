<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Item &therefore; Create</title>
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
                    <form action="FGItem.htm" id="search" method="post">
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="mode" value="insert" />
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Item</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Item Code <span style="float: right">*</span></td>
                                    <td><input type="text" id="itemCode" name="itemCode" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Item Name <span style="float: right">*</span></td>
                                    <td><input type="text" id="itemName" name="itemName" size="50" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Pack Style <span style="float: right">*</span></td>
                                    <td>
                                        <select id="itemPackStyle" name="itemPackStyle" required="required">
                                            <option value="">-- Pick One --</option>
                                            <c:set var="labelPackSize" value="-1" />
                                            <c:forEach items="${ims}" var="x">
                                                <c:if test="${labelPackSize != x.pack_size}">
                                                    <c:set var="labelPackSize" value="${x.pack_size}" />
                                                    <optgroup label="Pack Size: ${x.pack_size}" />
                                                </c:if>
                                                <option value="${x.pack_id}">${x.pack_style} (${x.pack_size})</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGItem.htm');" />
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
