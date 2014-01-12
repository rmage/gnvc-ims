<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	
	java.util.HashMap map = (java.util.HashMap) request.getAttribute("model");
	Integer paging = (Integer) map.get("paging");
	Integer totalRows = (Integer) map.get("totalRows");
    com.app.web.engine.search.SalesOrderSearch criteria = new com.app.web.engine.search.SalesOrderSearch();

    if (request.getSession().getAttribute("SalesOrderSearch") != null) {
		criteria = (com.app.web.engine.search.SalesOrderSearch) request.getSession().getAttribute("SalesOrderSearch");
    }
    
    java.util.List<com.app.wms.engine.db.dto.Picking>listSalesOrder = new java.util.ArrayList<com.app.wms.engine.db.dto.Picking>();
    if(map.get("listSalesOrder")!=null){
    	listSalesOrder = (java.util.List<com.app.wms.engine.db.dto.Picking>)map.get("listSalesOrder");
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>IMS - Pick List Sales Order Packing</title>
        <%@include file="../metaheader.jsp" %>
</head>
<body>
	<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

	<div id="content" style="display: none" class="span-24 last">
	    <div class="box">
	        <form action="Packing.htm" method="post">
	        <input type="hidden" name="action" value="doSearch" />
                    <table class="collapse tblForm row-select">
                             <caption>Sales Order - Search</caption>
                              <tbody>
                                <tr class="detail_genap">
                                    <td width="20%">
                                        Sales Order No
                                    </td>
                                    <td>
                                        <input type="text" name="salesOrderNo" id="salesOrderNo"/>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                	<td>Sales Order Date</td>
                                	<td>
										<input type="text" name="salesOrderDate" id="salesOrderDate"/>                                	
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
		<caption>Sales Order - List</caption>
			<thead>
			    <tr>
					<td class="style1" width="1%">No.</td>
					<td class="style1" width="2%">Action</td>
					<td class="style1" width="5%">Sales Order No</td>
					<td class="style1" width="25%">Sales Order Date</td>
			    </tr>
			</thead>
			<tbody id="main">
			  <c:if test="${requestScope.model!=null}">
                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
				<c:forEach var="so" items="${requestScope.model.listSalesOrder}" varStatus="soCounter">
				   
				    <tr class="ganjil">
						<td align="right">&nbsp;${soCounter.count}</td>
						<td class="style1" class="mid">
						    <form method="post" action="Packing.htm">
								<input type="hidden" name="action" value="addSelectedSalesOrder"/>
								<input type="hidden" name="salesOrderNumber" value="<c:out value="${so.soNumber}"/>"/>
								<input type="hidden" name="salesOrderDate" value="<c:out value="${so.soDate}"/>"/>
								<label><input type="submit" name="btnSelect" id="btnSelect" value="Select" /></label>
						    </form>
						</td>
						<td align="center">&nbsp;
			      			<c:if test="${so.soNumber ne null}">
			      				${so.soNumber}
			      			</c:if>
			      		</td>
			      		<td align="center">&nbsp;
			      			<c:if test="${so.soDate ne null}">
			      				${so.soDate}
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
			</tr>
			<tfoot>
			    <td colspan="5">
					<form method="get" action="Packing.htm">
					    <input type="hidden" name="action" value="create"/>
					    <label><input type="submit" name="btnBack" id="btnBack" value="Back " /></label>
					</form>
			    </td>
			</tfoot>
	</table>

                </div>
            </div>
        </div>
</body>
</html>