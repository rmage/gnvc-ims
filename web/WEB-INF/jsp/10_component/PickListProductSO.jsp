<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	
	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
	Integer paging = (Integer) m.get("paging");
	Integer totalRows = (Integer) m.get("totalRows");
    com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch();

    if (request.getSession().getAttribute("SearchProductSearch") != null) {
		criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("ProductSearch");
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>IMS - Pick List Product SO</title>
        <%@include file="../metaheader.jsp" %>
</head>
<body>
	<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="SalesOrder.htm" method="post">
                    <input type="hidden" name="action" value="doSearch" />
                    <table class="collapse tblForm row-select">
                             <caption>Product - Search</caption>
                              <tbody>
                                <tr class="detail_genap">
                                    <td width="20%">
                                        Product Code
                                    </td>
                                    <td>
                                        <input type="text" name="productCode" id="productCode"/>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                	<td>Product Name</td>
                                	<td>
										<input type="text" name="productName" id="productName"/>                                	
                                	</td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                </td>
                            </tfoot>
                     </table>
                </form>

	<table class="collapse tblForm row-select">
		<caption>Product - List</caption>
			<thead>
			    <tr>
					<td class="style1">No.</td>
					<td class="style1">Action</td>
					<td class="style1">Product Code</td>
					<td class="style1">Product Name</td>
					<td class="style1">Product Quantity</td>
					<td class="style1">SKU</td>
			    </tr>
			</thead>
			<tbody id="main">
			  <c:if test="${requestScope.model.tableMap!=null}">
                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
				<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
				   
				    <tr class="ganjil">
						<td align="right">&nbsp;${prodCounter.count}</td>
						<td class="style1" class="mid">
						    <form method="post" action="SalesOrder.htm">
								<input type="hidden" name="action" value="addSelectedProduct"/>
								<input type="hidden" name="productCode" value="<c:out value="${prod.value.productCode}"/>"/>
								<input type="hidden" name="productName" value="<c:out value="${prod.value.productName}"/>"/>
								<input type="hidden" name="quantity" value="<c:out value="${prod.value.quantity}"/>"/>
								<input type="hidden" name="unitCode" value="<c:out value="${prod.value.unitCode}"/>"/>
								<label><input type="submit" name="btnSelect" id="btnSelect" value="Select" /></label>
						    </form>
						</td>
						<td align="center">&nbsp;
			      			<c:if test="${prod.value.productCode ne null}">
			      				${prod.value.productCode}
			      			</c:if>
			      		</td>
			      		<td align="center">&nbsp;
			      			<c:if test="${prod.value.productName ne null}">
			      				${prod.value.productName}
			      			</c:if>
			      		</td>
			      		<td align="center">&nbsp;
			      			<c:if test="${prod.value.quantity ne null}">
			      				${prod.value.quantity}
			      			</c:if>
			      		</td>
			      		<td align="center">&nbsp;
			      			<c:if test="${prod.value.unitCode ne null}">
			      				${prod.value.unitCode}
			      			</c:if>
			      		</td>
				    </tr>

				</c:forEach>
			  </c:if>
			</tbody>
			<tr>
			    <td colspan="5">
                     <span class="style1">                                        
                        <c:if test="${model.page !=null && model.page > 1}">
                            <a href="Product.htm?action=listService&page=<c:out value="${model.page-1}" />">
                                &lt;
                            </a>
                        </c:if>
                        	&nbsp;page: <c:out value="${model.page}" />&nbsp;
                        	<a href="Product.htm?action=listService&page=<c:out value="${model.page+1}" />">
                            	&gt;
                        	</a>
                     </span>
			    </td>
			    <td></td>
			</tr>
			<tfoot>
			    <td colspan="5">
					<form method="get" action="SalesOrder.htm">
					    <input type="hidden" name="action" value="create"/>
					    <label><input type="submit" name="btnBack" id="btnBack" value="Back " /></label>
					</form>
			    </td>
			    <td></td>
			</tfoot>
	</table>

                </div>
            </div>
        </div>
</body>
</html>