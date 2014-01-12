<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>IMS - Locating Area List</title>
	<%@include file="../metaheader.jsp" %>
	</head>
<body>
<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="LocatingArea.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Locating Area</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Locating Area
                                    </td>
                                    <td>
                                        <input type="text" name="locatingArea" value=""/>
                                    </td>
                                    <td>
                                        Locating Condition 
                                    </td>
                                    <td>
                                        <input type="text" name="locatingCondition" value="" />
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr>
                                   
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
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Locating Area - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Locating Area</td>
                                <td class="style1">Locating Condition</td>
                            </tr>
                        </thead>
                       <tbody id="main">
                            <c:if test="${model.locatingarea!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.locatingarea}" var="locatingarea">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url value="LocatingArea.htm" var="urlEdit">
                                                <c:param
                                                    name="locatingId" value="${locatingarea.locatingId}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${locatingarea.locatingArea}"/></td>
                                        <td class="style1"><c:out value="${locatingarea.locatingCondition}"/></td>      
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="LocatingArea.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                       		&nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="LocatingArea.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'LocatingArea.htm?action=create';
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

</body>
</html>