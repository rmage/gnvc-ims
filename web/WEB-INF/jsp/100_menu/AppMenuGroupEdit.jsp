<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Menu Group</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	com.app.wms.engine.db.dto.AppMenuGroup dto = (com.app.wms.engine.db.dto.AppMenuGroup) m.get("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">

                <div class="box">
                    <form action="AppMenu.htm" >
                        <input type="hidden" name="action" value="saveGroup" />
                        <input type="hidden" name="groupCode" value="<%= dto.getGroupCode()%>"/>
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
                                        <label>
                                            <input type="text" name="name" value="<%= dto.getName()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
								<tr>
                                    <td class="style1">Sort Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="sortNo" value="<%= dto.getSortNo()%>" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span class="style1">
                                    <label>
                                        <input type="submit" name="btnSave" value="Save" />
                                    </label>

                                </span>
                                <input type="button" class="style1" name="btnBack" id="btnBack" value="Back" />
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
                    location.href = 'AppMenu.htm';
                });

               
            });
        </script>
    </body>
</html>