<%-- 
    Document   : AppMenuList
    Created on : Jul 11, 2010, 8:57:05 PM
    Author     : tri
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - List of Menu</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
	<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
	    <div id="content" style="display: none" class="span-24 last">

                <div class="box">
		    <form action="AppMenu.htm" >
			<input type="hidden" name="mode" value="list" />
			<table class="collapse tblForm row-select">
			    <caption>Menu Group</caption>
			    <tbody class="tbl-nohover" id="main">
				<tr>
				    <td class="style1">Menu Group Name:</td>
				    <td class="style1">
					<select name="groupCode">
					    <option value="ALL">--- All Group ---</option>
					    <c:if test="${model.groups!=null}">
						<c:forEach items="${model.groups}" var="group">
						    <option value=<c:out value="${group.groupCode}"/>
							    <c:if test="${group.groupCode==model.groupCode}"> selected </c:if>>
							<c:out value="${group.name}" /></option>
						    </c:forEach>
						</c:if>
					</select>
				    </td>
				</tr>
			    </tbody>
			    <tfoot>
				<tr>
				    <td colspan="2">
					<input type="submit" name="btnGroup" value="Search" />
					<input type="submit" name="btnGroup" value="Add" />
					<input type="submit" name="btnGroup" value="Edit" />
					<input type="submit" name="btnGroup" value="Delete" />
					
				    </td>
				</tr>
			    </tfoot>
			</table>
		    </form>
		    <table class="collapse tblForm row-select">
                        <caption>Menu - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Group Code</td>
                                <td class="style1">Menu Code</td>
                                <td class="style1">Menu Title</td>
                                <td class="style1">Sort No</td>
                                <!--td class="style1">URL</td-->
                                <!--td class="style1">Last Updated</td-->
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.menus!=null}">
                                <% int rowNumber = 1;%>
                                <c:forEach items="${model.menus}" var="menu">
                                    <tr>
                                        <td align="right"><%=rowNumber++%></td>
                                        <!-- <td align="center"><input type="checkbox" class="cbo" /></td> -->
                                        <td class="style1">
                                            <c:url value="AppMenu.htm" var="urlEdit">
                                                <c:param name="menuCode" value="${menu.menuCode}"/>
                                                <c:param name="mode" value="edit"/>
                                            </c:url>
                                            <c:url value="AppMenu.htm" var="urlDelete">
                                                <c:param name="menuCode" value="${menu.menuCode}"/>
                                                <c:param name="action" value="delete" />
                                            </c:url>
                                            <!-- munculkan link nya -->
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" alt="icon edit"/>
                                            </a>
                                            <a href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" alt="icon delete" />
                                            </a>
                                        </td>
                                        <td class="style1"><c:out value="${menu.groupCode}"/></td>
                                        <td class="style1"><c:out value="${menu.menuCode}"/></td>
                                        <td class="style1"><c:out value="${menu.name}"/></td>
                                        <!--td class="style1"><c:out value="${menu.url}"/></td-->
                                        <td class="style1"><c:out value="${menu.sortNo}"/></td>
                                        <!--td class="center"><c:out value="${menu.updatedDate}"/></td-->
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <td colspan="7">
				<input type="button" name="button" id="btnAdd" value="Add" />
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
                    location.href = 'AppMenu.htm?action=create&groupCode=<c:out value="${model.groupCode}"/>';
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
