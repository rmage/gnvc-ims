<%@page import="com.app.wms.engine.db.dto.Supplier"%>
<%@page import="com.app.wms.hbm.bean.Tallyman"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
            String telephone = (String)dto.getTelephone();
            String fax = (String)dto.getFax();
            String email = (String)dto.getEmail();
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
                                    <td class="style1">Supplier Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="supplierCode" value="<%=supplierCode%>" maxlength="10" size="12" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="supplierName"  value="<%=supplierName%>" maxlength="25" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Address</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="supplierAddress"  value="<%=supplierAddress%>" maxlength="55" size="55" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Telephone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="telephone"  value="<%=telephone%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Fax</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="fax"  value="<%=fax%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Email</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email"  value="<%=email%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Contact Person</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="contactPerson"  value="<%=contactPerson%>" maxlength="25" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Is Active</td>
                                    <td class="style1">
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
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>

                                    </span>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
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