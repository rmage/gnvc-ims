<%@page import="com.app.wms.engine.db.dto.Warehouse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Warehouse</title>
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
        	Warehouse dto = (Warehouse)m.get("dto");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Wh.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Warehouse - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Warehouse Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="code" maxlength="10" size="12" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Warehouse Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="name" maxlength="25" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
								<tr>
                                    <td class="style1">Product Category</td>
                                     <td class="style1">
                                    	<label>
	                                        <select name="categoryName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
	                                        		  <option value="${droplist.categoryName}" ${(droplist.categoryName eq requestScope.model.categoryName)? "selected": ""}>
	                                        		  	${droplist.categoryName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                            		</td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">
                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
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
                    location.href = 'Wh.htm';
                    return false;
                });
            });

            $("#btnSave").click(function () {                         

                //if invalid do nothing
                if(!$("#addForm").validationEngine('validate')){
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

            });
        </script>
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
    </body>
</html>