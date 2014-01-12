<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<head>
		<title>IMS - View Locating Area</title>
		<%@include file="../metaheader.jsp" %>
	</head>
</head>
<body>
 	<%
    	com.app.wms.engine.db.dto.WhLocatingArea dto = (com.app.wms.engine.db.dto.WhLocatingArea) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <table class="collapse tblForm row-select">
                    <caption>Locating Area - View</caption>
                    <tbody class="tbl-nohover">
                       
                        <tr>
                            <td class="style1">Locating Area</td>
                            <td class="style1">
                            <%= dto.getLocatingArea()%>
                            </td>
                        </tr>
                        <tr>
                            <td class="style1">Locating Condition</td>
                            <td class="style1">
                            <%= dto.getLocatingCondition()%>
                            </td>
                        </tr>
		    </tbody>
                 <tfoot>
                    <td colspan="2">
                        <form action="LocatingArea.htm" >
                            <input type="hidden" name="action" value="findByPrimaryKey" />
                            <input type="hidden" name="mode" value="edit" />
                            <input type="hidden" name="locatingId" value="" />
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
                location.href = 'LocatingArea.htm';
            });
        });
    </script>
</body>
</html>