<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS &there4; Menu Access &there4; Role Access</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String roleCode = (String) m.get("roleCode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search Menu Role /div -->
                <div class="box">

                    <table class="collapse tblForm row-select">
                        <caption>Role Access &there4; Role</caption>
                        <form action="AppMenuRole.htm" >
                            <input type="hidden" name="mode" value="list" />
                            <tbody class="tbl-nohover" id="main">
                                <tr>
                                    <td>Role Name:</td>
                                    <td>
                                        <select name="roleCode">
                                            <c:if test="${model.userRoles!=null}">
                                                <c:forEach items="${model.userRoles}" var="userRole">
                                                    <option value=<c:out value="${userRole.roleCode}"/>
                                                            <c:if test="${userRole.roleCode==model.roleCode}"> selected </c:if>>
                                                        <c:out value="${userRole.roleName}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <span>
                                            <label>
                                                <input type="submit" name="btnSearch" value="Search" />
                                            </label>
                                        </span>
                                    </td>
                                </tr>
                            </tfoot>
                        </form>
                    </table>
                    <table class="collapse tblForm row-select">
                        <caption>Role Access &there4; Assigned Menu</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 15px"><input type="checkbox" class="checkAll" /></td>
                                <td>Menu Code</td>
                                <td>Menu Name</td>
                            </tr>
                        </thead>
                        <form action="AppMenuRole.htm" >
                            <input type="hidden" name="action" value="save" />
                            <input type="hidden" name="roleCode" value="<%=roleCode%>"/>
                            <tbody id="main">
                                <c:if test="${model.menuRoles!=null}">
                                    <% int rowNumber = 1;%>
                                    <c:forEach items="${model.menuRoles}" var="menuRole">
                                        <tr>
                                            <td class="center"><%=rowNumber++%></td>
                                            <td align="center"><input type="checkbox" class="cbo"  name="menuCode" value="${menuRole.menuCode}"/></td>
                                            <td>${menuRole.menuCode}</td>
                                            <td>${menuRole.createdBy}</td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                            <tfoot>
                                <td colspan="10">
                                    <span>
                                        <input type="submit" name="btnAction" value="Delete" />
                                    </span>
                                </td>
                            </tfoot>
                        </form>
                    </table>

                    <table class="collapse tblForm row-select">
                        <caption>Role Access &there4; Unassigned Menu</caption>
                        <thead>
                            <form action="AppMenuRole.htm" >
                                <input type="hidden" name="mode" value="list" />
                                <input type="hidden" name="roleCode" value="<%=roleCode%>"/>
                                <tr>
                                    <td colspan="2" >Group Code</td>
                                    <td colspan="3">
                                        <select name="groupCode">
                                            <!--option value="ALL">--- All Group ---</option-->
                                            <c:if test="${model.menuGroups!=null}">
                                                <c:forEach items="${model.menuGroups}" var="menuGroup">
                                                    <option value=<c:out value="${menuGroup.groupCode}"/>
                                                            <c:if test="${menuGroup.groupCode==model.groupCode}"> selected </c:if>>
                                                        <c:out value="${menuGroup.name}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="5">
                                        <span>
                                            <label>
                                                <input type="submit" name="btnSearch" value="Search" />
                                            </label>
                                        </span>
                                    </td>
                                </tr>
                            </form>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 15px"><input type="checkbox" class="checkAll2" /></td>
                                <td>Group Code</td>
                                <td>Menu Code</td>
                                <td>Name</td>
                            </tr>
                        </thead>
                        <form action="AppMenuRole.htm">
                            <input type="hidden" name="action" value="save" />
                            <input type="hidden" name="roleCode" value="<%=roleCode%>"/>
                            <tbody id="main">
                                <c:if test="${model.menus!=null}">
                                    <% int rowP = 1;%>
                                    <c:forEach items="${model.menus}" var="menu">
                                        <tr class="ganjil">
                                            <td class="center"><%= rowP++%></td>
                                            <td class="mid"><input type="checkbox" class="cbo2"  name="menuCode" value="<c:out value="${menu.menuCode}"/>"/></td>
                                            <td><c:out value="${menu.groupCode}"/></td>
                                            <td><c:out value="${menu.menuCode}"/></td>
                                            <td><c:out value="${menu.name}"/></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                            <tfoot>
                                <td colspan="10">
                                    <span>
                                        <label>
                                            <input type="submit" name="btnAction" value="Add" />
                                        </label>
                                    </span>
                                </td>
                            </tfoot>
                        </form>
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
                    //location.href = 'AppMenuRole.htm?action=create';
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
        <!-- ini end if script untuk button -->

        <!-- smp sini di cut -->
    </body>
</html>