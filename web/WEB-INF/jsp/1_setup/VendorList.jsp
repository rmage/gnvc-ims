<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Repair Vendor List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	com.app.web.engine.search.VendorSearch criteria = new com.app.web.engine.search.VendorSearch(); // dummy search, all empty
                		    if (request.getSession().getAttribute("VendorSearch") != null) {
                	criteria = (com.app.web.engine.search.VendorSearch) request.getSession().getAttribute("VendorSearch");
                		    }

                		    java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
                		    java.util.List<com.app.wms.engine.db.dto.City> listCity = (java.util.List<com.app.wms.engine.db.dto.City>) m.get("listCity");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->
                <div class="box">
                    <form action="Vendor.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Vendor</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        City
                                    </td>
                                    <td>
                                        <select name="cityCode">
                                            <%
                                            	String _strSelected = "";
                                            					if (criteria.getCityCode().equals("")) {
                                            					    _strSelected = "selected";
                                            					}
                                            %>
                                            <option value="" <%=_strSelected%>>All</option>
                                            <%
                                            	for (com.app.wms.engine.db.dto.City item : listCity) {
                                            					    _strSelected = "";
                                            					    if (criteria.getCityCode().equals(item.getCityCode())) {
                                            						_strSelected = "selected";
                                            					    }
                                            %>
                                            <option value="<%=item.getCityCode()%>" <%=_strSelected%>><%=item.getName()%></option>
                                            <%
							}
                                            %>
                                        </select>
                                    </td>
                                    <td width="20%">
                                        Vendor Code
                                    </td>
                                    <td>
                                        <input type="text" name="vendorCode" value="<%= criteria.getVendorCode()%>"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Vendor Name
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="<%= criteria.getName()%>" />
                                    </td>
                                    <td colspan="2">
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
                        <caption>Vendor - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
                                <td class="style1">Vendor Code</td>
                                <td class="style1">Vendor Name</td>
                                <!--<td class="style1">Last Update</td>-->
                                <!--<td class="style1">Is Active</td>-->
                            </tr>                                                                                                           
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.vendors!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.vendors}" var="vend">
                                    <tr class="ganjil">
                                        <td class="center">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <!--<td class="mid"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>-->
                                        <c:url value="Vendor.htm" var="urlEdit">
                                            <c:param name="vendorCode" value="${vend.vendorCode}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="VendorRepair.htm" var="urlAddProduct">
                                            <c:param name="vendorCode" value="${vend.vendorCode}"/>
					    <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <%--<c:url value="countryDelete.htm" var="urlDelete">
                                            <c:param name="vendorCode" value="${vend.vendorCode}"/>
                                        </c:url>--%>
                                        <td class="center">
					    <a href='<c:out value="${urlAddProduct}"/>'><img src="resources/images/add.png" width="16" height="16" alt="Add Product"/></a>
					    <a href='<c:out value="${urlEdit}"/>'><img src="resources/images/edit.gif" width="16" height="16"  alt="Edit"/></a>
					    <%--<a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a>--%>
					</td>
                                        <td align="style1"><c:out value="${vend.vendorCode}"/></td>
                                        <td align="style1"><c:out value="${vend.name}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="6">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Vendor.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
					<c:if test="${model.page < model.totalRows/model.paging}">
					    <a href="Vendor.htm?page=<c:out value="${model.page+1}" />">
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
                                    <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>

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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'Vendor.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
                // adjust width for tables //
                //$('.tblForm tbody').each(function() {
                //    if($(this).attr('id') != 'main') {
                //        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                //        $(this).find('.checkbox').addClass('span-2');
                //    }
                //});
            });
        </script>
        <!-- ini end if script untuk button -->

        <!-- smp sini di cut -->
    </body>
</html>