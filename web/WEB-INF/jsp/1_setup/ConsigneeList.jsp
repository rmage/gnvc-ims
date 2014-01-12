<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title>IMS - Consignee List</title>
	<%@include file="../metaheader.jsp" %>
	</head>
<body>
<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Consignee.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Consignee</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Consignee Code
                                    </td>
                                    <td>
                                        <input type="text" name="consigneeCode" value=""/>
                                    </td>
                                    <td>
                                        Consignee Name
                                    </td>
                                    <td>
                                        <input type="text" name="consigneeName" value="" />
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
                        <caption>Consignee - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Consignee Code</td>
                                <td class="style1">Consignee Name</td>
                                <td class="style1">Consignee PIC</td>
                                <td class="style1">Consignee Address</td>
                                <%-- 
                                <td class="style1">Address2</td>
                                <td class="style1">Address3</td>
                                --%>
                                <td class="style1">Consignee Phone</td>
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                       <tbody id="main">
                            <c:if test="${model.consignee!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.consignee}" var="consignee">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url value="Consignee.htm" var="urlEdit">
                                                <c:param
                                                    name="consigneeCode" value="${consignee.consigneeCode}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <c:url
                                                value="Consignee.htm" 
                                                var="urlDelete">
                                                <c:param
                                                    name="consigneeCode" value="${consignee.consigneeCode}"/>
                                                <c:param name="action" value="inactivate" />
                                            </c:url>
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${consignee.consigneeCode}"/></td>
                                        <td class="style1"><c:out value="${consignee.consigneeName}"/></td> 
                                         <td class="style1"><c:out value="${consignee.consigneePIC}"/></td>     
                                        <td class="style1"><c:out value="${consignee.address1}"/></td>  
                                        <%-- 
                                        <td class="style1"><c:out value="${consignee.address2}"/></td>  
                                        <td class="style1"><c:out value="${consignee.address3}"/></td>  
                                        --%>
                                        <td class="style1"><c:out value="${consignee.consigneePhone}"/></td>  
                                        <td class="center"><c:out value="${consignee.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Consignee.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                       		&nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Consignee.htm?page=<c:out value="${model.page+1}" />">
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
                    location.href = 'Consignee.htm?action=create';
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