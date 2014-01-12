<%@page import="com.app.wms.engine.db.dto.Packing"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%
	
	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
	Integer paging = (Integer) m.get("paging");
	Integer totalRows = (Integer) m.get("totalRows");
    com.app.web.engine.search.PackingSearch criteria = new com.app.web.engine.search.PackingSearch();

    if (request.getSession().getAttribute("SearchPackingSearch") != null) {
		criteria = (com.app.web.engine.search.PackingSearch) request.getSession().getAttribute("PackingSearch");
    }
    
    java.util.List<Packing>packingSearch = new java.util.ArrayList<Packing>();
    if(m.get("packingSearch")!=null){
    	packingSearch = (java.util.List<Packing>)m.get("packingSearch");
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>IMS - Pick List Consolidate</title>
        <%@include file="../metaheader.jsp" %>
</head>
<body>
	<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Consolidate.htm" method="post">
                    <input type="hidden" name="action" value="doSearch" />
                    <table class="collapse tblForm row-select">
                             <caption>Packing - Search</caption>
                              <tbody>
                                <tr class="detail_genap">
                                    <td width="20%">
                                        Packing No
                                    </td>
                                    <td>
                                        <input type="text" name="productCode" id="productCode"/>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                	<td>Packing Date</td>
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
		<caption>Packing - List</caption>
			<thead>
			    <tr>
					<td class="style1">No.</td>
					<td class="style1">Action</td>
					<td class="style1">Packing No</td>
					<td class="style1">Packing Date</td>
					
			    </tr>
			</thead>
			<tbody id="main">
			  <c:if test="${requestScope.model!=null}">
                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
				<c:forEach var="prod" items="${requestScope.model.packingSearch}" varStatus="prodCounter">
				   
				    <tr class="ganjil">
						<td align="right">&nbsp;${prodCounter.count}</td>
						<td class="style1" class="mid">
						    <form method="post" action="Consolidate.htm">
								<input type="hidden" name="action" value="addSelectedPacking"/>
								<input type="hidden" name="packingNo" value="<c:out value="${prod.packingNo}"/>"/>
								<input type="hidden" name="packingDate" value="<c:out value="${prod.packingDate}"/>"/>
								<label><input type="submit" name="btnSelect" id="btnSelect" value="Select" /></label>
						    </form>
						</td>
						<td align="center">&nbsp;
			      			<c:if test="${prod.packingNo ne null}">
			      				${prod.packingNo}
			      			</c:if>
			      		</td>
			      		<td align="center">&nbsp;
			      			<c:if test="${prod.packingDate ne null}">
			      				${prod.packingDate}
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
					<form method="get" action="Consolidate.htm">
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