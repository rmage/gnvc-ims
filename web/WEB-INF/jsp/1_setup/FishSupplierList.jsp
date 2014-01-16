<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Fish Cold Storage List</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#btnSearch').click(function() {
        			var name = $('#queryName').val();
        			location.href = "FishSupplier.htm?search=true&name="+name;
        		});
                
                $('#btnCleanFilter').click(function() {
                   location.href = "FishSupplier.htm"; 
                });
            });
        </script>
    </head>
    <body>
        <%
        	com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch(); 
            if (request.getSession().getAttribute("FishSupplierSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishSupplierSearch");
            }
            
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String querySearch = m.get("querySearch") == null ? "" : (String) m.get("querySearch");
            String queryName = m.get("queryName") == null ? "" : (String) m.get("queryName");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSupplier.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Fish Supplier</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Fish Supplier Name
                                    </td>
                                    <td>
                                        <input type="text" id="queryName" name="FishSupplierName" 
                                               size="30" value="<%=queryName%>" />
                                    </td>
                                </tr>
                                <tr>
                                   
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="button" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                     <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
                                    <label>
	                                    <input type="button" name="button" id="btnCleanFilter" value="Clean Filter" />
	                                </label>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Fish Supplier - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="center">No</td>
                                <td class="center">Action</td>
                                <td class="center">Code</td>
                                <td class="center">Name</td>
                                <td class="center">Telp.</td>
                                <td class="center">Fax</td>
                                <td class="center">Email</td>
                                <td class="center">CP</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.fishSuppliers!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.fishSuppliers}" var="fishSupplier">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                       
                                        <c:url value="FishSupplier.htm" var="urlEdit">
                                            <c:param name="id" value="${fishSupplier.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="edit"/>
                                        </c:url>
                                       
                                        <c:url value="FishSupplier.htm" var="urlDelete">
                                            <c:param name="id" value="${fishSupplier.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        <td class="center" width="5%">
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a> 
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" /></a>
                                        </td>
                                        <td class="style1"><c:out value="${fishSupplier.code}"/></td>
                                        <td class="style1"><c:out value="${fishSupplier.name}"/></td>
                                        <td class="center"><c:out value="${fishSupplier.telephone}"/></td>
                                        <td class="center"><c:out value="${fishSupplier.fax}"/></td>
                                        <td class="center"><c:out value="${fishSupplier.email}"/></td>
                                        <td class="center"><c:out value="${fishSupplier.contactPerson}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                          <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishSupplier.htm?page=<c:out value="${model.page-1}" /><%=querySearch%>">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
						    				<a href="FishSupplier.htm?page=<c:out value="${model.page+1}" /><%=querySearch%>">
											&gt;
						    				</a>
										</c:if>
				    				</span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                            <td colspan="6">
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
                    location.href = 'FishSupplier.htm?action=create';
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