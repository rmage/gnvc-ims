<%@page import="com.app.wms.engine.db.dto.PriceCatalog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Price Catalog </title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            PriceCatalog  dto = (PriceCatalog) request.getAttribute("dto");    
            String catalogCode = (String)dto.getCatalogCode();
            String catalogName = (String)dto.getCatalogName();
     		String id = Integer.toString(dto.getId());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Price Catalog  - Detail</caption>
                        <tbody class="tbl-nohover">
                            <tr>
                                <td class="style1">Catalog Code</td>
                                <td class="style1">
                                    <%=catalogCode%>
                                </td>
                            </tr>
                            <tr>
                                <td class="style1">Catalog Name</td>
                                <td class="style1">
                                    <%=catalogName%>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="2">
                                <form action="PriceCatalog.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="id" value="<%= dto.getId()%>" />
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
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'PriceCatalog.htm';
                });


            });
        </script>
    </body>
</html>