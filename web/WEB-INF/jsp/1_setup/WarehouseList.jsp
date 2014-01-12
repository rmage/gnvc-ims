<%@page import="com.app.wms.engine.db.dto.UserRole"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Warehouse List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
       
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Wh.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Warehouse</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Warehouse Code
                                    </td>
                                    <td>
                                        <input type="text" name="code" value="" disabled/>
                                    </td>
                                    <td width="20%">
                                       Warehouse Name
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="" disabled/>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    	</label>

                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Warehouse - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Warehouse Code</td>
                                <td class="style1">Warehouse Name</td>
                                <td class="style1">Product Category</td>
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.warehouse!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.warehouse}" var="warehouse">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <c:url value="Wh.htm" var="urlEdit">
                                        	<c:param name="id" value="${warehouse.id}"/>
                                            <c:param name="code" value="${warehouse.whCode}"/>
                                            <c:param name="name" value="${warehouse.whName}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="Wh.htm" var="urlDelete">
                                        	<c:param name="id" value="${warehouse.id}"/>
                                            <c:param name="code" value="${warehouse.whCode}"/>
                                            <c:param name="name" value="${warehouse.whName}"/>
					    				<c:param name="action" value="inactivate" />
                                        </c:url>
                                        <td class="mid" width="5%"><a href='<c:out value="${urlEdit}"/>'><img src="resources/images/edit.gif" width="16" height="16" /></a><a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a></td>
                                        <td align="left"><c:out value="${warehouse.whCode}"/></td>
                                        <td align="left"><c:out value="${warehouse.whName}"/></td>
                                        <td align="center"><c:out value="${warehouse.categoryName}"/></td>
                                        <td class="center"><c:out value="${warehouse.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Wh.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Wh.htm?page=<c:out value="${model.page+1}" />">
											&gt;
										    </a>
										</c:if>
				    				</span>
                                </td>
                                <td></td>
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
                    location.href = 'Wh.htm?action=create';
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