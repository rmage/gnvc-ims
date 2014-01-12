<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <head>
        <title>IMS - View Menu Group</title>
        <%@include file="../metaheader.jsp" %>
    </head>
</head>
<body>

    <%
    	com.app.wms.engine.db.dto.AppMenuGroup dto = (com.app.wms.engine.db.dto.AppMenuGroup) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-19 last">
            <!--div class="box">Search Menu Group</div -->

            <div class="box">


                <table class="collapse tblForm row-select">
                    <caption>Menu Group - Detail</caption>
                    <tbody class="tbl-nohover">
                        <tr>
                            <td class="style1">Menu Group Code</td>
                            <td class="style1">
                                <%= dto.getGroupCode()%>
                            </td>
                        </tr>
                        <tr>
                            <td class="style1">Menu Group Name</td>
                            <td class="style1">
                                <%= dto.getName()%>
                            </td>
                        </tr>
                        <tr>
                            <td class="style1">Sort Number</td>
                            <td class="style1">
                                <%=dto.getSortNo()%>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>
                    <td colspan="2">
                        <form action="AppMenu.htm" >
                            <input type="hidden" name="action" value="findByPrimaryKey" />
                            <input type="hidden" name="groupCode" value="<%= dto.getGroupCode()%>" />
                            <input type="submit" value="Edit" name="btnGroup"/>
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
                location.href = 'AppMenu.htm';
            });


        });
    </script>



</body>
</html>