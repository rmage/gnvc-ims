<%@page import="com.app.wms.engine.db.dto.FishSupplier"%>
<%@page import="com.app.wms.engine.db.dto.FishStorage"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <head>
        <title>IMS - View Fish Supplier</title>
        <%@include file="../metaheader.jsp" %>
    </head>
</head>
<body>
    <%
    	FishSupplier dto = (FishSupplier) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <table class="collapse tblForm row-select">
                    <caption>Fish Supplier - View</caption>
                    <tbody class="tbl-nohover">
                    	<tr>
                        	<td class="style1"></td>
                            <td class="style1">Id</td>
                            <td class="style1">
                                <%= dto.getId()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Supplier Code</td>
                            <td class="style1">
                                <%= dto.getCode()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td width="style1" class="style1">Supplier Name</td>
                            <td width="style1" class="style1">
							<%= dto.getName()%>
                            </td>
                        </tr>
                        <tr>
                            	<td class="style1"></td>
                                <td class="style1">Address</td>
                                <td class="style1">
                                    <%=dto.getAddress()%>
                                </td>
                         </tr>
                         <tr>
                            	<td class="style1"></td>
                                <td class="style1">Address</td>
                                <td class="style1">
                                    <%=dto.getAddress()%>
                                </td>
                         </tr>
                         <tr>
                            	<td class="style1"></td>
                                <td class="style1">Telephone</td>
                                <td class="style1">
                                    <%=dto.getTelephone()%>
                                </td>
                         </tr>
                         <tr>
                            	<td class="style1"></td>
                                <td class="style1">Fax</td>
                                <td class="style1">
                                    <%=dto.getFax()%>
                                </td>
                         </tr>
                         <tr>
                            	<td class="style1"></td>
                                <td class="style1">Email</td>
                                <td class="style1">
                                    <%=dto.getEmail()%>
                                </td>
                         </tr>
                         <tr>
                            	<td class="style1"></td>
                                <td class="style1">Contact Person</td>
                                <td class="style1">
                                    <%=dto.getContactPerson()%>
                                </td>
                         </tr>
		
		    </tbody>
                    <tfoot>
                    <td colspan="2">
                        <form action="FishSupplier.htm" >
                            <input type="hidden" name="action" value="findByPrimaryKey" />
                            <input type="hidden" name="mode" value="edit" />
                            <input type="hidden" name="supplierId" value="<%= dto.getId()%>" />
                            <%-- <input type="submit" value="Edit"/>--%>
                            <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                        </form>

                    </td>
                   <td></td>
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
    <script type="text/javascript">
        $(function() {
            $('#btnBack').click(function() {
                location.href = 'FishSupplier.htm';
            });


        });
    </script>
</body>
</html>