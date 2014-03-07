<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Fish Type</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
                $('#addForm').validationEngine('attach'); 
            });
        </script>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            FishType dto = (FishType) m.get("dto");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishType.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                         <input type="hidden" name="fishTypeId" value="<%= mode.equals("create") ? "" : dto.getId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Type - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td>Code</td>
                                   <td>
                                        <label>
                                            <input type="text" name="code" id="code" value="" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true"  />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td>
                                        <label>
                                            <input type="text" name="description" value="" size="50" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
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
                    location.href = 'FishType.htm';
                });
            });       
            $(function() {
                $('#code').bind('blur', function() {
                  var $o = $(this);
                   if (!$o.val()) return;
                   
                   $.ajax({
                       url: 'FishType.htm?term='+$o.val(),
                       method: 'post',
                       data: {
                           action: 'getUnique', term: $o.val()
                       },
                       dataType: 'json',
                       success: function(json){
                           if(json.status) {
                               alert('Not Unique code found!');
                               $o.val(null);
                               $o.focus();
                           }
                       }
                });
            });
        });
        </script>                                
    </body>
</html>
