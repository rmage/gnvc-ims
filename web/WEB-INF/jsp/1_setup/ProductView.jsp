<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <head>
        <title>IMS - View Product</title>
        <%@include file="../metaheader.jsp" %>
    </head>
</head>
<body>
    <%
    	com.app.wms.engine.db.dto.Product dto = (com.app.wms.engine.db.dto.Product) request.getAttribute("dto");
    %>
    <div class="container">
        <%@include file="../header.jsp" %>
        <jsp:include page="../dynmenu.jsp" />

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <table class="collapse tblForm row-select">
                    <caption>Product - View</caption>
                    <tbody class="tbl-nohover">
                    	
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Bar Code</td>
                            <td class="style1">
                                <%= dto.getBarCode()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td width="35%" class="style1">Item Code</td>
                            <td width="62%" class="style1">
							<%= dto.getProductCode()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Item Name</td>
                            <td class="style1">
                                <%= dto.getProductName()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Brand Name</td>
                            <td class="style1">
                                <%= dto.getBrandName()%>
                            </td>
                        </tr>
                         <tr>
                        	<td class="style1"></td>
                            <td class="style1">Description</td>
                            <td class="style1">
                                <%= dto.getProductDescription()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Item Color</td>
                            <td class="style1">
                                <%= dto.getProductColor()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Category</td>
                            <td class="style1">
                                <%= dto.getProductCategory()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Unit of Measurement (UoM)</td>
                            <td class="style1">
                            	<%=dto.getUom() %>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Supplier Name</td>
                            <td class="style1">
                                <%= dto.getSupplier()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Buyer</td>
                            <td class="style1">
                                <%= dto.getBuyer()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Pack Style</td>
                            <td class="style1">
                                <%= dto.getPackstyle()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">Pack Size</td>
                            <td class="style1">
                                <%= dto.getPacksize()%>
                            </td>
                        </tr>
                        <tr>
                        	<td class="style1"></td>
                            <td class="style1">LID</td>
                            <td class="style1">
                                <%= dto.getLid()%>
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
                        <form action="Product.htm" >
                            <input type="hidden" name="action" value="findByPrimaryKey" />
                            <input type="hidden" name="mode" value="edit" />
                            <input type="hidden" name="productCode" value="<%= dto.getProductCode()%>" />
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
                location.href = 'Product.htm';
            });


        });
    </script>
</body>
</html>