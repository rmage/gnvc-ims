<%@page import="com.app.wms.engine.db.dto.Supplier"%>
<%@page import="com.app.wms.hbm.bean.Tallyman"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Supplier</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%            
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Supplier dto = (Supplier) m.get("dto");
            String mode = (String) m.get("mode");
            String supplierCode = (String) dto.getSupplierCode();
            String supplierName = (String) dto.getSupplierName();
            String supplierAddress = (String) dto.getSupplierAddress();
            String telephone = (String) dto.getTelephone();
            String fax = (String) dto.getFax();
            String email = (String) dto.getEmail();
            String contactPerson = (String) dto.getContactPerson();
            String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Supplier.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%=id%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Supplier - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Supplier Code</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="supplierCode" value="<%=supplierCode%>" maxlength="10" size="12" readonly />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Name</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="supplierName"  value="<%=supplierName%>" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Address</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="supplierAddress"  value="<%=supplierAddress%>" maxlength="55" size="55" pattern="^\S+[^'\x22]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Telephone</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="telephone"  value="<%=telephone%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Fax</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="fax"  value="<%=fax%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Supplier Email</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="email"  value="<%=email%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Contact Person</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="contactPerson"  value="<%=contactPerson%>" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Is Active</td>
                                    <td>
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
                                        </label>
                                        <label>
                                            <input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
                                        </label>
                                    </td>
                               	</tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span>
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                    </label>
                                </span>
                                <input type="button" name="button" id="btnBack" value="Back" />
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
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Supplier.htm';
                    return false;
                });


            });
        </script>
    </body>
</html>