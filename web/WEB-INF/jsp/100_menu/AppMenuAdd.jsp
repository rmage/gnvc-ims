<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Menu</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
	<%
		java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
			    com.app.wms.engine.db.dto.AppMenu dto = (com.app.wms.engine.db.dto.AppMenu) m.get("dto");
	%>
	<div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="AppMenu.htm" >
                        <input type="hidden" name="action" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Menu - Detail</caption>
                            <tbody class="tbl-nohover">
				<tr>
                                    <td class="style1">Group Code</td>
                                    <td class="style1">
                                        <select name="groupCode">
					    <c:if test="${model.groups!=null}">
						<c:forEach items="${model.groups}" var="group">
						    <option value=<c:out value="${group.groupCode}"/>
							    <c:if test="${group.groupCode==model.dto.groupCode}"> selected </c:if>>
							<c:out value="${group.name}" /></option>
						    </c:forEach>
						</c:if>
					</select>
                                    </td>
                                </tr>
				<tr>
                                    <td class="style1">Menu Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="menuCode" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Menu Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="name" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Url</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="url" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
				<tr>
                                    <td class="style1">Sort Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="uang" name="sortNo" value="1" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
					<input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
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
                $('#btnBack').click(function() {
                    location.href = 'AppMenu.htm';
                });

               
            });
        </script>
    </body>
</html>