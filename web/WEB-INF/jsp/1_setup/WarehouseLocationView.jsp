<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Corporate</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>

        <%
	        com.app.wms.engine.db.dto.WhLocation dto = (com.app.wms.engine.db.dto.WhLocation) request.getAttribute("dto");
        
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Location - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                            	<td class="style1"></td>
                                <td width="35%" class="style1">Location Code</td>
                                <td width="62%" class="style1">
                                    <%= dto.getLocationCode()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Location Name</td>
                                <td class="style1">
                                    <%= dto.getLocationName()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Location Type</td>
                                <td class="style1">
                                    <%= dto.getLocationType()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Product Code</td>
                                <td class="style1">
                                	<%= dto.getProductCode()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Product Name</td>
                                <td class="style1">
                                	<%= dto.getProductName()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Minimum Product</td>
                                <td class="style1">
                                    <%= dto.getMinProduct()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Maximum Product</td>
                                <td class="style1">
                                    <%= dto.getMaxProduct()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Locating Area</td>
                                <td class="style1">
                                    <%= dto.getLocatingArea()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Locating Zone</td>
                                <td class="style1">
                                    <%= dto.getLocatingZone()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Location Bay</td>
                                <td class="style1">
                                    <%= dto.getLocationBay()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Location Level</td>
                                <td class="style1">
                                    <%= dto.getLocationLevel()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Location Position</td>
                                <td class="style1">
                                    <%= dto.getLocationPosition()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Work Zone</td>
                                <td class="style1">
                                    <%= dto.getWorkZone()%>
                                </td>
                            </tr>
                            <tr>
                            	<td class="style1"></td>
                                <td class="style1">Is Active</td>
                                <td class="style1">
                                    <%=dto.getIsActive()%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="20">
                                <form action="Location.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="locationCode" value="<%= dto.getLocationCode()%>" />
                                    <%-- <input type="submit" value="Edit"/>--%>
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
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Location.htm';
                });


            });
        </script>
    </body>
</html>