<%@page import="com.app.wms.engine.db.dto.UserRole"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New User Role</title>
        <%@include file="../../metaheader.jsp" %>
    </head>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="UserRole.htm" method="post">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>User Role - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Role Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="code" maxlength="10" size="12" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Role Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name" maxlength="25" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Department</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="departmentCode">
                                                    <option value="">No Department</option>
                                                    <c:forEach var="droplist" items="${requestScope.model.dropListDepartment}">
                                                        <option value="${droplist.departmentCode}" ${(droplist.departmentCode eq requestScope.model.departmentCode)? "selected": ""}>
                                                            ${droplist.departmentCode} - ${droplist.departmentName}
                                                        </option> 
                                                    </c:forEach>
	                                        </select>
                                    	</label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
								<%-- 
                                <tr>
                                    <td class="style1">Role Level</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="roleLevel" maxlength="2" size="2" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                               --%>
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
                    location.href = 'UserRole.htm';
                    return false;
                });
            });
        </script>
    </body>
</html>