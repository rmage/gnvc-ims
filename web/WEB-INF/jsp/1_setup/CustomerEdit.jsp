<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Customer</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <%--
        Document   : CustomerEdit
        Created on : Jun 16, 2010, 8:03:00 PM
        Author     : ruli
    --%>

    <body>

        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        		    com.app.wms.engine.db.dto.Customer dto = (com.app.wms.engine.db.dto.Customer) m.get("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">

                <div class="box">
                    <form action="Customer.htm" >
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="customerId" value="<%=dto.getCustomerId()%>"/>
                        <input type="hidden" name="code" value="<%=dto.getCode()%>"/>

                        <table class="collapse tblForm row-select">
                            <caption>Customer - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Code</td>
                                    <td class="style1">
                                        <%=dto.getCode()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Salutation</td>
                                    <input type="hidden" name="getsalutation" value="<%=dto.getSalutation()%>" />
                                    <td>
                                        <select name="salutation">
                                            <option value="Mr" <%if (dto.getSalutation().equalsIgnoreCase("Mr")) {
							    out.print("selected");
							}%> >Mr.</option>
                                            <option value="Miss" <%if (dto.getSalutation().equalsIgnoreCase("Miss")) {
							    out.print("selected");
							}%> >Miss.</option>
                                        </select>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" value="<%=dto.getName()%>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Name Tax</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="nameTax" value="<%=dto.getNameTax() == null ? "" : dto.getNameTax()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Address</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="address" value="<%=dto.getAddress() == null ? "" : dto.getAddress()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Npwp</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="npwp" value="<%=dto.getNpwp() == null ? "" : dto.getNpwp()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Pkp Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="pkpNumber" value="<%=dto.getPkpNumber() == null ? "" : dto.getPkpNumber()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Pkp Date</td>
                                    <td class="style1">
                                        <label>
                                            <input class="datetime" type="text" name="pkpDate" value="<%=com.app.wms.web.util.Utility.formatDate(dto.getPkpDate())%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Phone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="phone" value="<%= dto.getPhone() == null ? "" : dto.getPhone()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="style1">Email</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="email" value="<%= dto.getEmail() == null ? "" : dto.getEmail()%>" size="30" />
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

                                <tr>
                                    <td class="style1">Is Default</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="isDefault" value="Y" <% if (dto.getIsDefault().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
					</label>
					<label>
					    <input type="radio" name="isDefault" value="N" <% if (dto.getIsDefault().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Customer.htm';
                });


            });
        </script>
    </body>
</html>
