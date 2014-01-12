<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Bundle</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
          		    com.app.wms.engine.db.dto.Bundle dto = (com.app.wms.engine.db.dto.Bundle) m.get("dtoB");
          		    com.app.wms.engine.db.dto.BundleDtl dtoDtl = (com.app.wms.engine.db.dto.BundleDtl) m.get("dtoDtl");

          		    Integer paging = (Integer) m.get("paging");
          		    Integer totalRows = (Integer) m.get("totalRows");
          		    Integer paging2 = (Integer) m.get("paging2");
          		    Integer totalRows2 = (Integer) m.get("totalRows2");

          		    com.app.web.engine.search.BundleDtlSearch criteria = new com.app.web.engine.search.BundleDtlSearch(); // dummy search, all empty
          		    if (request.getSession().getAttribute("BundleDtlSearch") != null) {
          	criteria = (com.app.web.engine.search.BundleDtlSearch) request.getSession().getAttribute("BundleDtlSearch");
          		    }

          		    com.app.web.engine.search.ProductSearch criteria2 = new com.app.web.engine.search.ProductSearch(); // dummy search, all empty
          		    if (request.getSession().getAttribute("ProductSearch") != null) {
          	criteria2 = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("ProductSearch");
          		    }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Bundle.htm" >
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="bundleCode" value="<%= dto.getBundleCode()%>"/>
			<input type="hidden" name="mode" value="edit" />

                        <table class="collapse tblForm row-select">
                            <caption>Bundle - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Bundle Code</td>
                                    <td class="style1">
                                        <%= dto.getBundleCode()%>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Bundle Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="name" value="<%= dto.toString()%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Is Active</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.toString().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
					</label>
					<label>
					    <input type="radio" name="isActive" value="N" <% if (dto.toString().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
				<td colspan="2">
				    <span class="style1">
					<label>
					    <input type="submit" name="btnSave" value="Save" />
					</label>
				    </span>
				    <input type="button" class="style1" name="btnBack" id="btnBack" value="Back" />
				</td>
                            </tfoot>
                        </table>
                    </form>
		    <table class="collapse tblForm row-select">
                        <caption>Bundle Detail - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
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
					<c:url value="BundleDtl.htm" var="urlDelete">
					    <c:param name="bundleCode" value="${detail.bundleCode}"/>
					    <c:param name="itemCode" value="${detail.itemCode}"/>
					    <c:param name="action" value="delete"/>
					</c:url>
					<c:url value="Bundle.htm" var="urlEdit">
					    <c:param name="bundleCode" value="${detail.bundleCode}"/>
					    <c:param name="itemCode" value="${detail.itemCode}"/>
					    <c:param name="mode" value="edit"/>
					</c:url>
					<td class="center">
					    <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
					    <a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a>
					</td>
					<td class="style1"><c:out value="${detail.itemCode}"/></td>
					<td class="style1"><c:out value="${detail.productName}"/></td>
					<td class="style1"><c:out value="${detail.quantity}"/></td>
				    </tr>
				</c:forEach>
			    </c:if>
			    <tr>
                                <td colspan="5">
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
		    <table class="collapse tblForm row-select">
                        <caption>Product - List</caption>
                        <thead>
			    <form action="Bundle.htm" >
				<input type="hidden" name="mode" value="edit" />
				<input type="hidden" name="bundleCode" value="<%= dto.getBundleCode()%>"/>
				<tr>
				    <td class="style1" colspan="2">Product Code</td>
				    <td class="style1" colspan="3">
				    </td>
				</tr>
				<tr>
				    <td class="style1" colspan="2">Product Name</td>
				    <td class="style1" colspan="3">
				    </td>
				</tr>
				<tr>
				    <td colspan="5">
					<span class="style1">
					    <label>
						<input class ="style1" type="submit" value="Search" id="btnSearch2" name="btnSearch2" />
					    </label>
					    <label>
						<input class ="style1" type="submit" value="View All" id="btnViewAll2" name="btnViewAll2" />
					    </label>
					</span>
				    </td>
				</tr>
			    </form>
			    <tr>
				<td class="style1">No</td>
				<!-- <td><input type="checkbox" class="checkAll" /></td> -->
				<td class="style1">Product Code</td>
				<td class="style1">Product Name</td>
				<td class="style1">Quantity</td>
				<td class="style1">Action</td>
			    </tr>
                        </thead>
                        <tbody id="main">
			    <c:if test="${model.products!=null}">
				<c:set scope="page" value="${((model.page2-1)*model.paging2)+1}" var="nomor2"/>
				<c:forEach items="${model.products}" var="prod">
				    <form action="BundleDtl.htm" >
					<input type="hidden" name="action" value="save" />
					<input type="hidden" name="bundleCode" value="<%= dto.getBundleCode()%>"/>
					<input type="hidden" name="itemCode" value="<c:out value="${prod.itemCode}"/>"/>
					<tr class="ganjil">
					    <td class="center">
						<c:out value="${nomor2}" />
						<c:set scope="page" value="${nomor2+1}" var="nomor2"/>
					    </td>
					    <!--<td class="mid"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>-->
					    <c:url value="BundleDtl.htm" var="urlAdd">
						<c:param name="itemCode" value="${prod.itemCode}"/>
						<c:param name="bundleCode" value="${model.dtoB.bundleCode}"/>
						<c:param name="action" value="save"/>
					    </c:url>
					    <td class="style1"><c:out value="${prod.itemCode}"/></td>
					    <td class="style1"><c:out value="${prod.name}"/></td>
					    <td class="style1">
						<input type="text" class="uang" name="quantity" value="<c:out value="${model.dtoDtl.quantity}"/>" size="30" />
					    </td>
					    <td>
						<span class="style1">
						    <label>
							<input type="submit" name="btnAdd" value="Add" />
						    </label>
						</span>
					    </td>
					</tr>
				    </form>
				</c:forEach>
			    </c:if>
			    <tr>
                                <td colspan="5">
                                    <span class="style1">
					<%
						    int halaman2 = 1;
						    int baris2 = (halaman2 - 1) * paging2; // ini paging = 10
						    while (baris2 < totalRows2) {


					%>
					<!--a href="Bundle.htm?page=<%=halaman2%>"><%=halaman2%>&nbsp;</a-->
					<%
							halaman2++;
							baris2 = (halaman2 - 1) * paging2;
						    }
					%>
					<c:url value="Bundle.htm" var="urlPrev">
					    <c:param name="page" value="${model.page}"/>
					    <c:param name="page2" value="${model.page2-1}"/>
					    <c:param name="mode" value="edit"/>
					    <c:param name="bundleCode" value="${model.dtoB.bundleCode}"/>
					</c:url>
					<c:url value="Bundle.htm" var="urlNext">
					    <c:param name="page" value="${model.page}"/>
					    <c:param name="page2" value="${model.page2+1}"/>
					    <c:param name="mode" value="edit"/>
					    <c:param name="bundleCode" value="${model.dtoB.bundleCode}"/>
					</c:url>
					<c:if test="${model.page2 !=null && model.page2 > 1}">
					    <a href='<c:out value="${urlPrev}"/>'>
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page2}" />&nbsp;
					<c:if test="${model.page2 < model.totalRows2/model.paging2}">
					    <a href='<c:out value="${urlNext}"/>'>
						&gt;
					    </a>
					</c:if>
                                        
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
		    <form action="BundleDtl.htm" >
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="bundleCode" value="<%= dto.getBundleCode()%>"/>
			<table  style="display:none" class="collapse tblForm row-select">
                            <caption>Bundle Detail - Add</caption>
			    <tbody class="tbl-nohover">

				<tr>
				    <td class="style1">Product:</td>
				    <td class="style1">
					<select name="itemCode">
					    <c:if test="${model.products!=null}">
						<c:forEach items="${model.products}" var="prod">
						    <option value=<c:out value="${prod.itemCode}"/>
							    <c:if test="${prod.itemCode==model.dtoDtl.itemCode}"> selected </c:if>>
							<c:out value="${prod.name}" /></option>
						    </c:forEach>
						</c:if>
					</select>
				    </td>
				</tr>
				<tr>
				    <td class="style1">Quantity:</td>
				    <td class="style1">
					<label>
					    <input type="text" class="uang" name="quantity" value="<c:out value="${model.dtoDtl.quantity}"></c:out>" size="30" />
					</label>
				    </td>
				</tr>
			    </tbody>
			    <tfoot>
				<td colspan="2">
				    <span class="style1">
					<label>
					    <%
							String strMode = "Add";
							if (dtoDtl != null) {
							    strMode = "Save";
							}
					    %>
					    <input type="submit" name="btnAdd" value="<%=strMode%>" />

					</label>
				    </span>
				</td>
                            </tfoot>
                        </table>
		    </form>
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