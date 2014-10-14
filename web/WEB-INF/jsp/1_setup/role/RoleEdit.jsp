<%@page import="com.app.wms.engine.db.dto.UserRole"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update User Role</title>
        <%@include file="../../metaheader.jsp" %>
    </head>

    <body>
        <% //            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
//            UserRole dto = (UserRole) m.get("dto");
//            String mode = (String) m.get("mode");
//            String roleCode = (String) dto.getRoleCode();
//            String roleName = (String) dto.getRoleName();
//            String id = Integer.toString(dto.getId());
%>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="UserRole.htm" method="post">
                        <input type="hidden" name="mode" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="${model.mode.id}"/>

                        <table class="collapse tblForm row-select">
                            <caption>User Role - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Role Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="code" value="${model.mode.roleCode}" maxlength="10" size="12" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Role Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="name"  value="${model.mode.roleName}" maxlength="25" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Department</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" class="shorttext" name="departmentCode"  value="${model.mode.departmentCode}" maxlength="25" size="30" />
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

                                    </span>
                                    <input type="button" class="style1" name="button" id="btnBack" value="Back" />
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