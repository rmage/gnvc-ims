<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Product List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch(); 
            if (request.getSession().getAttribute("ProductSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("ProductSearch");
            }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Product.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Product</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Product Code
                                    </td>
                                    <td>
                                        <input type="text" name="productCode" value=""/>
                                    </td>
                                    <td>
                                        Product Name
                                    </td>
                                    <td>
                                        <input type="text" name="productName" value="" />
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr>
                                   
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                     <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Product - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Product Code</td>
                                <td class="style1">Product Name</td>
                                <td class="style1">Category</td>
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.product!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.product}" var="prod">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                       
                                        <c:url value="Product.htm" var="urlEdit">
                                            <c:param name="productId" value="${prod.productId}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                       
                                        <c:url value="Product.htm" var="urlDelete">
                                            <c:param name="productId" value="${prod.productId}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        <td class="center" width="5%">
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" /></a>
                                        </td>
                                        <td class="style1"><c:out value="${prod.productCode}"/></td>
                                        <td class="style1"><c:out value="${prod.productName}"/></td>
                                        <td class="style1"><c:out value="${prod.productCategory}"/></td>
                                        <td class="center"><c:out value="${prod.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="6">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Product.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="Product.htm?page=<c:out value="${model.page+1}" />">
											&gt;
						    				</a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                            <td colspan="6">
                                <span class="style1">
                                   
                                </span>
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
                $('#btnAdd').click(function() {
                    location.href = 'Product.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
            });
        </script>
        
    </body>

</html>