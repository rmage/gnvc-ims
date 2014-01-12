<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Product Category</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	com.app.wms.engine.db.dto.ProductCategory dto = (com.app.wms.engine.db.dto.ProductCategory) request.getAttribute("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Product Category - Detail</caption>
                        <tbody class="tbl-nohover">
                           
                           <tr>
                            	<td width="10%" class="style1"></td>
                                <td width="20%" class="style1">Category Code</td>
                                <td width="70%" class="style1">
                                    <%= dto.getCategoryCode()%>
                                </td>
                            </tr>
                           
                           
                            <tr>
                            	<td width="10%" class="style1"></td>
                                <td width="20%" class="style1">Category Name</td>
                                <td width="70%" class="style1">
                                    <%= dto.getCategoryName()%>
                                </td>
                            </tr>
                            
                            <tr>
                            	<td width="10%" class="style1"></td>
                                <td width="20%" class="style1">Is Active</td>
                                <td width="70%" class="style1">
                                    <%= dto.getIsActive()%>
                                </td>
                            </tr>

                        </tbody>
                        <tfoot>
                            <td colspan="10">
                                <form action="ProductCategory.htm" >
                                    <input type="hidden" name="action" value="findByPrimaryKey" />
                                    <input type="hidden" name="mode" value="edit" />
                                    <input type="hidden" name="id" value="<%= dto.getId()%>" />
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
                    location.href = 'ProductCategory.htm';
                    return false;
                });


            });
        </script>
    </body>
</html>