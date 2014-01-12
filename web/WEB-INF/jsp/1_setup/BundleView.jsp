<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
	<head>
	    <title>IMS - View Bundle</title>
	    <%@include file="../metaheader.jsp" %>
	</head>
    </head>
    <body>

	<%
		java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
		    com.app.wms.engine.db.dto.Bundle dto = (com.app.wms.engine.db.dto.Bundle) m.get("dtoB");
		    com.app.wms.engine.db.dto.BundleDtl dtoDtl = (com.app.wms.engine.db.dto.BundleDtl) m.get("dtoDtl");

		    Integer paging = (Integer) m.get("paging");
		    Integer totalRows = (Integer) m.get("totalRows");

		    com.app.web.engine.search.BundleDtlSearch criteria = new com.app.web.engine.search.BundleDtlSearch(); // dummy search, all empty
		    if (request.getSession().getAttribute("BundleDtlSearch") != null) {
			criteria = (com.app.web.engine.search.BundleDtlSearch) request.getSession().getAttribute("BundleDtlSearch");
		    }
	%>
	<div class="container">
	    <%@include file="../header.jsp" %>
	    <jsp:include page="../dynmenu.jsp" />

	    <div id="content" style="display: none" class="span-24 last">
		<!--div class="box">Search Bundle</div -->

		<div class="box">


		    <table class="collapse tblForm row-select">
			<caption>Bundle - Detail</caption>
			<tbody class="tbl-nohover">
			    <tr>
				<td class="style1">Bundle Code</td>
				<td class="style1" colspan="3">
				    <%= dto.getBundleCode()%>
				</td>
			    </tr>
			    <tr>
				<td class="style1">Bundle Name</td>
				<td class="style1" colspan="3">
				    <%= dto.getBundleName()%>
				</td>
			    </tr>
			    <tr>
				<td class="style1">Is Active</td>
				<td class="style1" colspan="3">
				</td>
			    </tr>
			</tbody>
			<tfoot>
			    <td colspan="4">
				<form action="Bundle.htm" >
				    <input type="hidden" name="action" value="findByPrimaryKey" />
				    <input type="hidden" name="mode" value="edit" />
				    <input type="hidden" name="bundleCode" value="<%= dto.getBundleCode()%>" />
				    <input type="submit" value="Edit"/>
				    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
				</form>

			    </td>
			</tfoot>
		    </table>
		    <table class="collapse tblForm row-select">
			<caption>Bundle Detail - List</caption>
			<thead>
			    <tr>
				<td class="style1">No</td>
				<!-- <td><input type="checkbox" class="checkAll" /></td> -->
				<td class="style1">Product Code</td>
				<td class="style1">Product Name</td>
				<td class="style1">Quantity</td>
			    </tr>
			</thead>
			<tbody id="main">
			    <c:if test="${model.details!=null}">
				<c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
				<c:forEach items="${model.details}" var="detail">
				    <tr class="ganjil">
					<td class="center">
					    <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
					</td>
					<!--<td class="mid"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>-->
					<td class="style1"><c:out value="${detail.itemCode}"/></td>
					<td class="style1"><c:out value="${detail.productName}"/></td>
					<td class="style1"><c:out value="${detail.quantity}"/></td>
				    </tr>
				</c:forEach>
			    </c:if>
			    <tr>
                                <td colspan="4">
                                    <span class="style1">
					<%
						    int halaman = 1;
						    int baris = (halaman - 1) * paging;
						    while (baris < totalRows) {
					%>
					<!--a href="Bundle.htm?page=<%=halaman%>"><%=halaman%>&nbsp;</a-->
					<%
							halaman++;
							baris = (halaman - 1) * paging;
						    }
					%>
					<c:url value="Bundle.htm" var="urlPrev">
					    <c:param name="page2" value="${model.page2}"/>
					    <c:param name="page" value="${model.page-1}"/>
					    <c:param name="mode" value="edit"/>
					    <c:param name="bundleCode" value="${model.dtoB.bundleCode}"/>
					</c:url>
					<c:url value="Bundle.htm" var="urlNext">
					    <c:param name="page2" value="${model.page2}"/>
					    <c:param name="page" value="${model.page+1}"/>
					    <c:param name="mode" value="edit"/>
						    <c:param name="bundleCode" value="${model.dtoB.bundleCode}"/>
				</c:url>
					<c:if test="${model.page !=null && model.page > 1}">
                                            <a href="<c:out value="${urlPrev}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
					<c:if test="${model.page < model.totalRows/model.paging}">
					    <a href="<c:out value="${urlNext}" />">
						&gt;
					    </a>
					</c:if>
					
				    </span>
                                </td>
                            </tr>
			</tbody>
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
		    location.href = 'Bundle.htm';
		});


	    });
	</script>



    </body>
</html>