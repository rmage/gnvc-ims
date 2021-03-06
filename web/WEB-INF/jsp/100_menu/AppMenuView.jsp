<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - View Menu </title>
            <%@include file="../metaheader.jsp" %>
        </head>
    </head>
    <body>

        <%        com.app.wms.engine.db.dto.AppMenu dto = (com.app.wms.engine.db.dto.AppMenu) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Menu </div -->

                <div class="box">


                    <table class="collapse tblForm row-select">
                        <caption>Menu - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                                <td style="width: 175px;">Group Code</td>
                                <td>
                                    <%= dto.getGroupCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td>Menu Code</td>
                                <td>
                                    <%= dto.getMenuCode()%>
                                </td>
                            </tr>
                            <tr>
                                <td>Menu Name</td>
                                <td>
                                    <%= dto.getName()%>
                                </td>
                            </tr>
                            <tr>
                                <td>Url</td>
                                <td>
                                    <%= dto.getUrl()%>
                                </td>
                            </tr>
                            <tr>
                                <td>Sort Number</td>
                                <td>
                                    <%=dto.getSortNo()%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="AppMenu.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="menuCode" value="<%= dto.getMenuCode()%>" />
                                    <input type="submit" value="Edit"/>
                                    <input type="button" name="button" id="btnBack" value="Back" />
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