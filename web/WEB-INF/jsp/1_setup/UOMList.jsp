<%@page import="com.app.wms.engine.db.dto.Uom"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Unit of Measurement List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select"> 
                        <caption>Unit of Measurement - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">UoM Code</td>
                                <td class="style1">UoM Name</td>
                                <td class="style1">Remarks</td>
                                <td class="style1">Is Active</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.uoms!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.uoms}" var="uom">
                                    <tr class="ganjil">
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <c:url value="Uom.htm" var="urlEdit">
                                            <c:param name="id" value="${uom.id}"/>
                                            <c:param name="uomCode" value="${uom.uomCode}"/>
                                            <c:param name="uomName" value="${uom.uomName}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="Uom.htm" var="urlDelete">
                                            <c:param name="id" value="${uom.id}"/>
                                            <c:param name="uomCode" value="${uom.uomCode}"/>
                                            <c:param name="uomName" value="${uom.uomName}"/>
                                            <c:param name="action" value="inactivate" />
                                        </c:url>
                                        <td class="center" width="5%"><a href='<c:out value="${urlEdit}"/>'><img src="resources/images/edit.gif" width="16" height="16" /></a><a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a></td>
                                        <td class="style1"><c:out value="${uom.uomCode}"/></td>
                                        <td class="style1"><c:out value="${uom.uomName}"/></td>
                                        <td class="style1"><c:out value="${uom.remarks}"/></td>
                                        <td class="center"><c:out value="${uom.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Uom.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        <c:if test="${model.page < model.totalRows/model.paging}">
                                            <a href="Uom.htm?page=<c:out value="${model.page+1}" />">
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
                                <input type="button" name="button" id="btnAdd" value="Add" />
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
                    location.href = 'Uom.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
                // adjust width for tables //
                $('.tblForm tbody').each(function() {
                    if ($(this).attr('id') != 'main') {
                        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                        $(this).find('.checkbox').addClass('span-2');
                    }
                });
            });
        </script>
    </body>
</html>