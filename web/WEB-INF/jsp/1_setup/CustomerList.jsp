<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Customer List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	com.app.web.engine.search.CustomerSearch criteria = new com.app.web.engine.search.CustomerSearch(); // dummy search, all empty
        		    if (request.getSession().getAttribute("CustomerSearch") != null) {
        	criteria = (com.app.web.engine.search.CustomerSearch) request.getSession().getAttribute("CustomerSearch");
        		    }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->
                <div class="box">
                    <form action="Customer.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Customer</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Customer Code
                                    </td>
                                    <td>
                                        <input type="text" name="code" value="<%= criteria.getCode()%>"/>
                                    </td>
                                    <td width="20%">
                                        Customer Name
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="<%= criteria.getName()%>" />
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
                        <caption>Customer - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
                                <td class="style1">Customer Code</td>
                                <td class="style1">Customer Name</td>
                                <td class="style1">Is Active</td>
                                <%--<td class="style1">Created Date</td>--%>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.customers!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.customers}" var="cust">
                                    <tr class="ganjil">
                                        <td class="center">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <!--<td class="mid"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>-->
                                        <c:url value="Customer.htm" var="urlEdit">
                                            <c:param name="customerId" value="${cust.customerId}"/>
                                            <c:param name="customerCode" value="${cust.code}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="Customer.htm" var="urlDelete">
                                            <c:param name="customerId" value="${cust.customerId}"/>
                                            <c:param name="customerCode" value="${cust.code}"/>
                                            <c:param name="action" value="inactivate" />
                                        </c:url>
                                        <td class="center"><a href='<c:out value="${urlEdit}"/>'><img src="resources/images/edit.gif" width="16" height="16" /></a><a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a></td>
                                        <td class="style1"><c:out value="${cust.code}"/></td>
                                        <td class="style1"><c:out value="${cust.name}"/></td>
                                        <td class="center"><c:out value="${cust.isActive}"/></td>
                                        <%--<td class="style1"><c:out value="${cust.createdDate}"/></td>--%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Customer.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
					<c:if test="${model.page < model.totalRows/model.paging}">
					    <a href="Customer.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'Customer.htm?action=create';
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
