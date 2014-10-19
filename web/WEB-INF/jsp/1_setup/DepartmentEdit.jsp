<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Department</title>
        <%@include file="../metaheader.jsp" %>
    </head>    
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Department.htm" method="post">
                        <input type="hidden" name="mode" value="<%//=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="${model.mode.id}"/>

                        <table class="collapse tblForm row-select">
                            <caption>Department - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Department Code</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="departmentCode" value="${model.mode.departmentCode}" maxlength="10" size="12" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" readonly />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Department Name</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="departmentName"  value="${model.mode.departmentName}" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            <td colspan="2">
                                <span>
                                    <label>
                                        <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                    </label>
                                </span>
                                <input type="button" name="button" id="btnBack" value="Back" />
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
                    location.href = 'Department.htm';
                    return false;
                });
            });
        </script>
    </body>
</html>
