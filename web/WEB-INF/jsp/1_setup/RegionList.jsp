<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Region List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <% int row = 0;%>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-19 last">
                <!--div class="box">Search Branch</div -->
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Region - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
                                <td class="style1">Region Code</td>
                                <td class="style1">Region Name</td>
                                <!--<td class="style1">Created Date</td>-->
                                <td class="style1">Is Active</td>
                                <!--<td class="style1">Is Active</td>-->
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.cities!=null}">
                                <c:forEach items="${model.cities}" var="cit">
                                    <% row = row + 1;%>
                                    <tr class="ganjil">
                                        <td class="center"><%= row%></td>
                                        <!--<td class="mid"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>-->
                                        <c:url value="Region.htm" var="urlEdit">
                                            <c:param name="regionCode" value="${cit.regionCode}"/>
                                            <c:param name="mode" value="edit"/>
                                        </c:url>
                                        <c:url value="Region.htm" var="urlDelete">
                                            <c:param name="action" value="inactivate" />
                                            <c:param name="regionCode" value="${cit.regionCode}"/>

                                        </c:url>
                                        <td class="center"><a href='<c:out value="${urlEdit}"/>'><img src="resources/images/edit.gif" width="16" height="16" /></a><a href='<c:out value="${urlDelete}"/>'><img src="resources/images/delete.gif" width="16" height="16" /></a></td>
                                        <td class="style1"><c:out value="${cit.regionCode}"/></td>
                                        <td class="style1"><c:out value="${cit.name}"/></td>
                                        <%--<td class="style1"><c:out value="${cit.createdDate}"/></td>--%>
                                        <td class="center"><c:out value="${cit.isActive}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
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
                    location.href = 'Region.htm?action=create';
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
                $('.tblForm tbody').each(function() {
                    if($(this).attr('id') != 'main') {
                        $(this).find('td:eq(0)').css('width', '1%').next().addClass('span-5');
                        $(this).find('.checkbox').addClass('span-2');
                    }
                });
            });
        </script>
        <!-- ini end if script untuk button -->

        <!-- smp sini di cut -->
    </body>

</html>