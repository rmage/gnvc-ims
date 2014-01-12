<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - User List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	com.app.web.engine.search.UserSearch criteria = new com.app.web.engine.search.UserSearch(); 
        	if (request.getSession().getAttribute("UserSearch") != null) {
        		criteria = (com.app.web.engine.search.UserSearch) request.getSession().getAttribute("UserSearch");
        	}
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="User.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search User</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        User Id
                                    </td>
                                    <td>
                                        <input type="text" name="code" value=""/>
                                    </td>
                                    <td width="20%">
                                        User Name
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <%-- <input class ="style1" type="submit" value="View All" id="btnViewAll" name="btnViewAll" />--%>
                                    	 <label>
                                        	<input type="button" name="button" id="btnAdd" value="Add" />
                                    	</label>
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>User - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">User Id</td>
                                <%-- <td class="style1">Code</td>--%>
                                <td class="style1">User Name</td>
                                <td class="style1">Name</td>
                                <td class="style1">Role Code</td>
                                <td class="style1">Is Active</td>
                                <%-- 
                                <td class="style1">Warehouse Id</td>
                                <td class="style1">Corporate Id</td>
                                
                                
                                --%>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.users!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.users}" var="user">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <c:url value="User.htm" var="urlEdit">
                                            <c:param name="userId" value="${user.userId}"/>
                                            <c:param name="password" value="${user.password}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="User.htm" var="urlDelete">
                                            <c:param name="userId" value="${user.userId}"/>
                                            <c:param name="password" value="${user.password}"/>
					    					<c:param name="action" value="inactivate" />
                                        </c:url>
                                        <td class="mid" width="5%">
	                                        <a href='<c:out value="${urlEdit}"/>' > 
	                                       		 <img src="resources/images/edit.gif" width="16" height="16" />
	                                        </a>
	                                        <a href='<c:out value="${urlDelete}"/>'>
	                                        	<img src="resources/images/delete.gif" width="16" height="16" />
	                                        </a>
                                        </td>
                                        <td align="left"><c:out value="${user.userId}"/></td>
                                        <%-- <td align="left"><c:out value="${user.code}"/></td> --%>
                                        <td align="left"><c:out value="${user.username}"/></td>
                                        <td align="left"><c:out value="${user.name}"/></td>
                                        <td align="left"><c:out value="${user.roleCode}"/></td>
                                        <td align="center"><c:out value="${user.isActive}"/></td>
                                        <%-- 
                                        <td align="left"><c:out value="${user.whCode}"/></td>
                                        <td align="left"><c:out value="${user.corpId}"/></td>
                                        
                                        
                                         --%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="User.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
					<c:if test="${model.page < model.totalRows/model.paging}">
					    <a href="User.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'User.htm?action=create';
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