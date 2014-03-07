<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                         <input type="hidden" name="id" value="<%=dto.getId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Type - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td>Code</td>
                                   <td>
                                        <label>
                                            <input type="text" name="code" value="<%=dto.getCode()%>" size="30" readonly pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td>
                                        <label>
                                            <input type="text" name="description" value="<%=dto.getDescription()%>" size="50" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
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

 /*           $("#btnSave").click(function () {                         

                //if invalid do nothing
                if($("#addForm")[0].checkValidity()){
                    $("#dialog-incomplete").dialog({
                            open: function () {
                                $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                            },
                            title: 'Incomplete Form',
                            resizable: false,
                            height: 120,
                            modal: true,
                            buttons: {
                                "Ok" : function () {
                                    $(this).dialog("close");
                                }
                            }
                        });
                    return false;
                 }
                
                $("#dialog-confirm").dialog({ width: 300, height: 150, position: "center", modal: true, 
                    buttons: {
                        "Cancel": function() {                                       
                            $( this ).dialog( "close" );                                        
                        },
                        "Save": function() {
                            $("form#addForm").submit();
                        }
                    },
                    zindex: 1, title: 'Confirm' });

            });*/
        </script>

        <script language="JavaScript">
/*			function cek(){
			if(form.length.value == "" || form.width.value == ""|| form.height.value == ""){
			alert("data empty"); 
			exit;
			}
			}
			function kali() {
			cek();
			a=eval(form.length.value);
			b=eval(form.width.value);
			c=eval(form.height.value);
			d=a*b*c
			form.volumeMatrix.value = d;
			}*/
        </script>
		
            <script type="text/javascript">
               function formfocus() {
                  document.getElementById('autofocus').focus();
               }
               window.onload = formfocus;
            </script>
    </body>
</html>
