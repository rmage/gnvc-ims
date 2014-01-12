<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Vendor Repair List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
	<%
		java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");

			    Integer paging = (Integer) m.get("paging");
			    Integer totalRows = (Integer) m.get("totalRows");
			    Integer paging2 = (Integer) m.get("paging2");
			    Integer totalRows2 = (Integer) m.get("totalRows2");

			    com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch();
			    if (request.getSession().getAttribute("ProductSearch") != null) {
		criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("ProductSearch");
			    }
	%>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <div class="box">
		    <table class="collapse tblForm row-select">
                        <caption>Product - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td><input type="checkbox" class="checkAll" /></td>
                                <!--td class="style1">Action</td-->
				<td class="style1">Product Code</td>
                                <td class="style1">Product Name</td>
			    </tr>
                        </thead>
                        <form action="VendorRepair.htm" >
			    <input type="hidden" name="action" value="save" />
			    <input type="hidden" name="vendorCode" value="<c:out value="${model.vendorCode}"/>"/>
			    <tbody id="main">
				<c:if test="${model.vIns!=null}">
				    <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
				    <c:forEach items="${model.vIns}" var="vIn">
					<tr>
					    <td class="center">
						<c:out value="${nomor}" />
						<c:set scope="page" value="${nomor+1}" var="nomor"/>
					    </td>
					    <td align="center"><input type="checkbox" class="cbo"  name="itemCode" value="<c:out value="${vIn.itemCode}"/>"/></td>
					    <td class="style1"><c:out value="${vIn.itemCode}"/></td>
					    <td class="style1"><c:out value="${vIn.name}"/></td>
					</tr>
				    </c:forEach>
				</c:if>
				<tr>
				    <td colspan="6">
					<span class="style1">
					    <%
							int halaman = 1;
							int baris = (halaman - 1) * paging;
							while (baris < totalRows) {
					    %>
					    <!--a href="VendorRepair.htm?page=<%=halaman%>"><%=halaman%>&nbsp;</a-->
					    <%
							    halaman++;
							    baris = (halaman - 1) * paging;
							}
					    %>
					    <c:url value="VendorRepair.htm" var="urlPrev">
						<c:param name="page2" value="${model.page2}"/>
						<c:param name="page" value="${model.page-1}"/>
						<c:param name="vendorCode" value="${model.vendorCode}"/>
						<c:param name="action" value="save"/>
						<c:param name="btnAction" value="Select"/>
					    </c:url>
					    <c:url value="VendorRepair.htm" var="urlNext">
						<c:param name="page2" value="${model.page2}"/>
						<c:param name="page" value="${model.page+1}"/>
						<c:param name="vendorCode" value="${model.vendorCode}"/>
						<c:param name="action" value="save"/>
						<c:param name="btnAction" value="Select"/>
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
			    <tfoot>
				<td colspan="10">
				    <span class="style1">
					<input type="submit" name="btnAction" value="Delete" />
					<input type="button" class="style1" name="btnBack" id="btnBack" value="Back" />
				    </span>
				</td>
			    </tfoot>
			</form>
		    </table>

		    <form action="VendorRepair.htm" method="post">
			<input type="hidden" name="vendorCode" value="<c:out value="${model.vendorCode}"/>"/>
			<input type="hidden" name="action" value="editVendorRepair" />
			<table class="collapse tblForm row-select">
                            <caption>Search Product</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Item Code
                                    </td>
                                    <td>
                                    </td>
				    <td>
                                        Product Name
                                    </td>
                                    <td>
                                    </td>
				</tr>
			    </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <input class ="style1" type="submit" value="View All" id="btnViewAll" name="btnViewAll" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>

		    <table class="collapse tblForm row-select">
                        <caption>Product - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td><input type="checkbox" class="checkAll2" /></td>
                                <!--td class="style1">Action</td-->
				<td class="style1">Product Code</td>
                                <td class="style1">Product Name</td>
			    </tr>
                        </thead>
			<form action="VendorRepair.htm">
			    <input type="hidden" name="action" value="save" />
			    <input type="hidden" name="vendorCode" value="<c:out value="${model.vendorCode}"/>"/>
			    <tbody id="main">
				<c:if test="${model.vOuts!=null}">
				    <c:set scope="page" value="${((model.page2-1)*model.paging2)+1}" var="nomor2"/>
				    <c:forEach items="${model.vOuts}" var="vOut">
					<tr class="ganjil">
					    <td class="center">
						<c:out value="${nomor2}" />
						<c:set scope="page" value="${nomor2+1}" var="nomor2"/>
					    </td>
					    <td class="mid"><input type="checkbox" class="cbo2"  name="itemCode" value="<c:out value="${vOut.itemCode}"/>"/></td>
					    <td class="style1"><c:out value="${vOut.itemCode}"/></td>
					    <td class="style1"><c:out value="${vOut.name}"/></td>
					</tr>
				    </c:forEach>
				</c:if>
				<tr>
				    <td colspan="4">
					<span class="style1">
					    <%
							int halaman2 = 1;
							int baris2 = (halaman2 - 1) * paging2; // ini paging = 10
							while (baris2 < totalRows2) {


					    %>
					    <!--a href="VendorRepair.htm?page=<%=halaman2%>"><%=halaman2%>&nbsp;</a-->
					    <%
							    halaman2++;
							    baris2 = (halaman2 - 1) * paging2;
							}
					    %>
					    <c:url value="VendorRepair.htm" var="urlPrev">
						<c:param name="page" value="${model.page}"/>
						<c:param name="page2" value="${model.page2-1}"/>
						<c:param name="vendorCode" value="${model.vendorCode}"/>
						<c:param name="action" value="save"/>
						<c:param name="btnAction" value="Select"/>
					    </c:url>
					    <c:url value="VendorRepair.htm" var="urlNext">
						<c:param name="page" value="${model.page}"/>
						<c:param name="page2" value="${model.page2+1}"/>
						<c:param name="vendorCode" value="${model.vendorCode}"/>
						<c:param name="action" value="save"/>
						<c:param name="btnAction" value="Select"/>
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
			    <tfoot>
				<td colspan="10">
				    <span class="style1">
					<input type="submit" name="btnAction" value="Add" />
				    </span>
				</td>
			    </tfoot>
			</form>
		    </table>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Vendor.htm';
                });
                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
                // adjust width for tables //
                $('.tblForm tbody').each(function() {
                    if($(this).attr('id') != 'main') {
                        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                        $(this).find('.checkbox').addClass('span-2');
                    }
                });
            });
        </script>
        <!-- ini end if script untuk button -->

        <!-- smp sini di cut -->
    </body>
</html>