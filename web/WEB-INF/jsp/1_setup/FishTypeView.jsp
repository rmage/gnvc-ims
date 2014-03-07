<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - View Fish Type</title>
        <%@include file="../metaheader.jsp" %>
    </head>
</head>
<body>
    <%
    	FishType dto = (FishType) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <table class="collapse tblForm row-select">
                    <caption>Fish Type - View</caption>
                    <tbody class="tbl-nohover">
                    	
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Code</td>
                            <td class="style1">
                                <%= dto.getCode()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td width="35%" class="style1">Description</td>
                            <td width="62%" class="style1">
							<%= dto.getDescription()%>
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
                    <td colspan="2">
                        <form action="FishType.htm" >
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
                location.href = 'FishType.htm';
            });


        });
    </script>
</body>
</html>