<%@page import="com.app.wms.engine.db.dto.FishVessel"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <head>
        <title>IMS - View Fish Vessel</title>
        <%@include file="../metaheader.jsp" %>
    </head>
</head>
<body>
    <%
    	FishVessel dto = (FishVessel) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <table class="collapse tblForm row-select">
                    <caption>Fish Vessel - View</caption>
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
                            <td class="style1">Vessel Code</td>
                            <td class="style1">
                                <%= dto.getCode()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td width="style1" class="style1">Vessel Name</td>
                            <td width="style1" class="style1">
								<%= dto.getName()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Batch No.</td>
                            <td class="style1">
                                <%=dto.getBatchNo()%>
                            </td>
                         </tr>
		
		    </tbody>
                    <tfoot>
                    <td colspan="2">
                        <form action="FishVessel.htm" >
                            <input type="hidden" name="action" value="findByPrimaryKey" />
                            <input type="hidden" name="mode" value="edit" />
                            <input type="hidden" name="FishTypeId" value="<%= dto.getId()%>" />
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
                location.href = 'FishVessel.htm';
            });


        });
    </script>
</body>
</html>