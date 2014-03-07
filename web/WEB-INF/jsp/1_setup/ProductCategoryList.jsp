<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
          <title>IMS - Product Category List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <% 
    	int row = 0; 
        java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
    %>   
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                	<form action="ProductCategory.htm" method="post">
                    <table class="collapse tblForm row-select">
                        <caption>Search Product Category</caption>
                        <tbody>
                        	<tr>
                                  <td width="20%">Category Code</td>
                                  <td><input type="text" name="categoryCode" value=""/></td>
                                  
                                  <td>Category Name</td>
                                    <td><input type="text" name="categoryName" value="" /></td>
                                  <td colspan="2">
                                  </td>
                                </tr>
                            <tr>
                               
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="10">
                                <span class="style1">
                                    <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                </span>
                                <label>
                                    <input type="button" name="button" id="btnAdd" value="Add" />
                                </label>
                            </td>
                        </tfoot>
                    </table>
				 </form>
				 
				 <table class="collapse tblForm row-select">
                        <caption>Product Category - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Category Code</td>
                                <td class="style1">Category Name</td>
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                       <tbody id="main">
                            <c:if test="${model.productcategory!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.productcategory}" var="productcategory">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url value="ProductCategory.htm" var="urlEdit">
                                                <c:param
                                                    name="id" value="${productcategory.id}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <c:url
                                                value="ProductCategory.htm" 
                                                var="urlDelete">
                                                <c:param
                                                    name="id" value="${productcategory.id}"/>
                                                <c:param name="action" value="inactivate" />
                                            </c:url>
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${productcategory.categoryCode}"/></td>
                                        <td class="style1"><c:out value="${productcategory.categoryName}"/></td>      
                                        <td class="center"><c:out value="${productcategory.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="ProductCategory.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                            &nbsp;page: <c:out value="${model.page}" />&nbsp;
						<c:if test="${model.page < model.totalRows/model.paging}">
                                                    <a href="ProductCategory.htm?page=<c:out value="${model.page+1}" />">
							&gt;
                                                    </a>
					</c:if>
				    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                            <td colspan="10">
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
                    location.href = 'ProductCategory.htm?action=create';
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