<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Vendor</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>

        <%
        	com.app.wms.engine.db.dto.Vendor dto = (com.app.wms.engine.db.dto.Vendor) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->

                <div class="box">


                    <table class="collapse tblForm row-select">
                        <caption>Vendor - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                                <td class="style1">Vendor Code</td>
                                <td class="style1">
                                    <%= dto.getVendorCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Vendor Name</td>
                                <td class="style1">
                                    <%= dto.getName()%>
                                </td>
                            </tr>

                            <tr>
                                <td class="style1">Address</td>
                                <td class="style1">
                                    <%= dto.getAddress()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Phone</td>
                                <td class="style1">
                                    <%= dto.getPhone()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Email</td>
                                <td class="style1">
                                    <%= dto.getEmail()%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">City</td>
                                <td class="style1">
                                    <%= dto.getCityCode()%>
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
                                    <%= dto.getName()%>
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
                                <form action="Vendor.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="vendorCode" value="<%= dto.getVendorCode()%>" />
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
                    location.href = 'Vendor.htm';
                    return false;
                });
            });
        </script>



    </body>





</html>