<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Corporate List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	com.app.web.engine.search.CorporateSearch criteria = new com.app.web.engine.search.CorporateSearch(); 
        	if (request.getSession().getAttribute("CorporateSearch") != null) {
        		criteria = (com.app.web.engine.search.CorporateSearch) request.getSession().getAttribute("CorporateSearch");
        		
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
                    <form action="Corporate.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Corporate</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Corporate Id
                                    </td>
                                    <td>
                                        <input type="text" name="corpId" value=""/>
                                    </td>
                                    <td width="20%">
                                        Corporate Name
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
                                    </span>
                                     <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
                                    
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Corporate - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Corporate Id</td>
                                <td class="style1">Corporate Name</td>                                
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.corp!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.corp}" var="corp">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url value="Corporate.htm" var="urlEdit">
                                                <c:param
                                                    name="corpId" value="${corp.corpId}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <c:url
                                                value="Corporate.htm" 
                                                var="urlDelete">
                                                <c:param
                                                    name="corpId" value="${corp.corpId}"/>
                                                <c:param name="action" value="inactivate" />
                                            </c:url>
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${corp.corpId}"/></td>
                                        <td class="style1"><c:out value="${corp.corpName}"/></td>      
                                        <td class="center"><c:out value="${corp.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Corporate.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                       		&nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Corporate.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'Corporate.htm?action=create';
                });

            });
        </script>
      
    </body>
</html>