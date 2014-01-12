<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Vendor</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
                            java.util.List<com.app.wms.engine.db.dto.City> listCity = (java.util.List<com.app.wms.engine.db.dto.City>) m.get("listCity");
                            com.app.wms.engine.db.dto.Vendor dto = (com.app.wms.engine.db.dto.Vendor) m.get("dto");
                            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">

                <div class="box">
                    <form action="Vendor.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <%--<input type="hidden" name="isActive" value="Y"/>--%>
                        <table class="collapse tblForm row-select">
                            <caption>Branch - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Vendor Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="vendorCode" value="<%=dto.getVendorCode()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Vendor Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" value="<%=dto.getName()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Address</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="address" value="<%=dto.getAddress()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Phone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="phone" value="<%=dto.getPhone()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Email</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email" value="<%=dto.getEmail()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">City</td>
                                    <td class="style1">
                                        <label>
                                            <select name="cityCode">
                                                <%
                                                	for (com.app.wms.engine.db.dto.City item : listCity) {
                                                %>
                                                <option value="<%=item.getCityCode()%>"><%=item.getName()%></option>
                                                <%
                                                            }
                                                %>
                                            </select>
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Vendor.htm';
                    return false;
                });

               
            });
        </script>
    </body>
</html>