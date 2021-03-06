<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Bundle List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	com.app.web.engine.search.BundleSearch criteria = new com.app.web.engine.search.BundleSearch();      		    
            if (request.getSession().getAttribute("BundleSearch") != null) {
        	    criteria = (com.app.web.engine.search.BundleSearch) request.getSession().getAttribute("BundleSearch");
            }
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Bundle.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Bundle</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Bundle Code
                                    </td>
                                    <td>
                                        <input type="text" name="bundleCode" value=""/>
                                    </td>
                                    <td width="20%">
                                        Bundle Name
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
                        <caption>Bundle - List</caption>
                        <thead>
                            <tr>
                                <td width="1%" class="style1">No</td>
                                <td width="5%" class="style1"></td>
                                <td width="30%" class="style1">Bundle Code</td>
                                <td class="style1">Bundle Name</td>                                
                                <%-- <td class="style1">Is Active</td>--%>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.bundle!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.bundle}" var="bran">
                                    <tr>
                                        <td class="center">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center">
                                            
                                        </td>
                                            <td class="style1"><a href="#" class="no-decoration" onclick="csbShowDetail('BU', '<c:out value="${bran.bundleCode}" />', 'Bundle Detail')"><c:out value="${bran.bundleCode}"></c:out></a></td>
                                        
                                        <td class="style1"><c:out value="${bran.bundleName}"/></td>      
                                        <%-- <td class="center"><c:out value="${bran.isActive}"/></td>--%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Bundle.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>&nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Bundle.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'Bundle.htm?action=create';
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