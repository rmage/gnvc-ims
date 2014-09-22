<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Finished Goods Item &therefore; Update</title>
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
                        <input type="hidden" name="mode" value="edit" />
                        <input type="hidden" name="key" value="${ims.i.item_id}" />
                        <table class="collapse tblForm row-select">
                            <caption>Finished Goods Item</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 175px;">Item Code <span style="float: right">*</span></td>
                                    <td><input type="text" id="itemCode" name="itemCode" value="${ims.i.item_code}" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Item Name <span style="float: right">*</span></td>
                                    <td><input type="text" id="itemName" name="itemName" value="${ims.i.item_name}" required="required" /></td>
                                </tr>
                                <tr>
                                    <td>Pack Style <span style="float: right">*</span></td>
                                    <td>
                                        <select id="itemPackStyle" name="itemPackStyle" required="required">
                                            <option value="">-- Pick One --</option>
                                            <c:set var="labelPackSize" value="-1" />
                                            <c:forEach items="${ims.ps}" var="x">
                                                <c:if test="${labelPackSize != x.pack_size}">
                                                    <c:set var="labelPackSize" value="${x.pack_size}" />
                                                    <optgroup label="Pack Size: ${x.pack_size}" />
                                                </c:if>
                                                    <option value="${x.pack_id}" <c:if test="${x.pack_id == ims.i.pack_id}">selected="selected"</c:if>>${x.pack_style} (${x.pack_size})</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Oil</td>
                                    <td><input type="text" id="itemOil" name="itemOil" size="10" value="${ims.i.item_oil}"></td>
                                </tr>
                                <tr>
                                    <td>Lid</td>
                                    <td><input type="text" id="itemLid" name="itemLid" size="5" value="${ims.i.item_lid}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnUpdate" />
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
            $("#packSize").val("${ims.item_pack_size}");
        </script>
    </body>
</html>
