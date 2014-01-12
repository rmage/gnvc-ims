<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Location List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	com.app.web.engine.search.WarehouseLocationSearch criteria = new com.app.web.engine.search.WarehouseLocationSearch(); 
        	if (request.getSession().getAttribute("WarehouseLocationSearch") != null) {
        		criteria = (com.app.web.engine.search.WarehouseLocationSearch) request.getSession().getAttribute("WarehouseLocationSearch");
        	}
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Integer paging = (Integer) m.get("paging");
            Integer totalRows = (Integer) m.get("totalRows");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Location.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Location</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Location Code
                                    </td>
                                    <td>
                                        <input type="text" name="locationCode" value="<%= criteria.getWarehouseLocationCode()%>"/>
                                    </td>
                                    <td width="20%">
                                        Location Name
                                    </td>
                                    <td>
                                        <input type="text" name="locationName" value="<%= criteria.getWarehouseLocationName()%>" />
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
                        <caption>Location - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Location Id</td>
                                <td class="style1">Location Code</td>
                                <td class="style1">Location Name</td>
                                <td class="style1">Product Code</td>
                                <td class="style1">Product Name</td>  
                                <td class="style1">SKU</td>                               
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.location!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.location}" var="loc">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url value="Location.htm" var="urlEdit">
                                                <c:param
                                                    name="locationId" value="${loc.locationId}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <c:url
                                                value="Location.htm" 
                                                var="urlDelete">
                                                <c:param
                                                    name="locationId" value="${loc.locationId}"/>
                                                <c:param name="action" value="inactivate" />
                                            </c:url>
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${loc.locationId}"/></td>
                                        <td class="style1"><c:out value="${loc.locationCode}"/></td>
                                        <td class="style1"><c:out value="${loc.locationName}"/></td>
                                        <td class="style1"><c:out value="${loc.productCode}"/></td> 
                                        <td class="style1"><c:out value="${loc.productName}"/></td>
                                        <td class="style1"><c:out value="${loc.unitCode}"/></td>
                                        <td class="center"><c:out value="${loc.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Location.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Location.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'Location.htm?action=create';
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