<%@page import="com.app.wms.engine.db.dto.Department"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - New Department</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%  
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Department.htm" method="post" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Department - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Department Code</td>
                                    <td>
                                        <label>
                                            <input type="text" name="departmentCode" id="departmentCode" maxlength="10" size="12" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Department Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="departmentName" id="departmentName" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true"/>
                                        </label>
                                        <label>*</label> 
                                    </td>
                                </tr>
                            </tbody>                
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
                                    </td>
                                </tr>
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
                $('#btnCancel').click(function() {
                    location.href = 'Department.htm';
                    return false;
                });
            });
            
            $('#departmentCode').bind('blur', function() {
                    var $o = $(this);
                    if (!$o.val())
                        return;
                    $.ajax({
                        url: 'Department.htm?term=' + $o.val(),
                        method: 'post',
                        data: {
                            action: 'getUnique', term: $o.val()
                        },
                        dataType: 'json',
                        success: function(json) {
                            if (json.status) {
                                alert('The code is already exist!');
                                $o.val(null);
                                $o.focus();
                            }
                        }
                    });
                });

        </script>
    </body>
</html>
