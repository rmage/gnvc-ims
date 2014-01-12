<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Customer</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <%--
        Document   : CustomerView
        Created on : Jun 16, 2010, 8:03:00 PM
        Author     : ruli
    --%>
    <body>
        <%
        	com.app.wms.engine.db.dto.Customer dto = (com.app.wms.engine.db.dto.Customer) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->

                <div class="box">


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
                                <td class="style1">
                                    <%=dto.getSalutation()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Name</td>
                                <td class="style1">
                                    <%=dto.getName()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Name Tax</td>
                                <td class="style1">
                                    <%=dto.getNameTax() == null ? "" : dto.getNameTax()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Address</td>
                                <td class="style1">
                                    <%=dto.getAddress() == null ? "" : dto.getAddress()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Npwp</td>
                                <td class="style1">
                                    <%=dto.getNpwp() == null ? "" : dto.getNpwp()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Pkp Number</td>
                                <td class="style1">
                                    <%=dto.getPkpNumber() == null ? "" : dto.getPkpNumber()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Pkp Date</td>
                                <td class="style1">
                                    <%=com.app.wms.web.util.Utility.formatDate(dto.getPkpDate())%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Phone</td>
                                <td class="style1">
                                    <%= dto.getPhone() == null ? "" : dto.getPhone() %>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Email</td>
                                <td class="style1">
                                    <%= dto.getEmail() == null ? "" : dto.getEmail() %>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Is Active</td>
                                <td class="style1">
                                    <%=dto.getIsActive()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Is Default</td>
                                <td class="style1">
                                    <%= dto.getIsDefault()%>
                                </td>
                            </tr>
<%--
                            <tr>
                                <td class="style1">Created By</td>
                                <td class="style1">
                                    <%= dto.getCreatedBy()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Created Date</td>
                                <td class="style1">
                                    <%= dto.getCreatedDate()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Updated By</td>
                                <td class="style1">
                                    <%= dto.getUpdatedBy()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Updated Date</td>
                                <td class="style1">
                                    <%= dto.getUpdatedDate()%>
                                </td>
                            </tr>
--%>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="Customer.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="code" value="<%= dto.getCode()%>" />
                                    <input type="hidden" name="customerId" value="<%= dto.getCustomerId()%>" />
                                    <input type="submit" value="Edit"/>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                </form>

                            </td>
                        </tfoot>
                    </table>

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