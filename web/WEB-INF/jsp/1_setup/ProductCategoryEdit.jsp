<!DOCTYPE html>
<html>
    <head>
        <title>IMS - Update Product Category</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.ProductCategory dto = (com.app.wms.engine.db.dto.ProductCategory) m.get("dto");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="ProductCategory.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="id" value="<%= dto.getId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Product Category - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Category Code</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="categoryCode" value="<%= dto.getCategoryCode()%>" size="30" readonly pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>  
                                <tr>
                                    <td>Category Name</td>
                                    <td>
                                        <label>
                                            <input type="text" class="shorttext" name="categoryName" value="<%= dto.getCategoryName()%>" size="30" pattern="^\S+[A-Za-z0-9 ]+\S" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>  
                                <tr>
                                    <td>Is Active</td>
                                    <td>
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
					</label>
                                         <label>
					    <input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
                                        </label>
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
                    location.href = 'ProductCategory.htm';
                    return false;
                  });
               });
               
//                 $("#btnSave").click(function () {                         
//
//                //if invalid do nothing
//                if($("#addForm")[0].checkValidity()){
//                    $("#dialog-incomplete").dialog({
//                            open: function () {
//                                $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
//                                $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
//                            },
//                            title: 'Incomplete Form',
//                            resizable: false,
//                            height: 120,
//                            modal: true,
//                            buttons: {
//                                "Ok" : function () {
//                                    $(this).dialog("close");
//                                }
//                            }
//                        });
//                    return false;
//                 }
//                
//                $("#dialog-confirm").dialog({ width: 300, height: 150, position: "center", modal: true, 
//                    buttons: {
//                        "Cancel": function() {                                       
//                            $( this ).dialog( "close" );                                        
//                        },
//                        "Save": function() {
//                            $("form#addForm").submit();
//                        }
//                    },
//                    zindex: 1, title: 'Confirm' });
//
//            });
               
        </script>
        
         <script language="JavaScript">
//			function cek(){
//			if(form.length.value == "" || form.width.value == ""|| form.height.value == ""){
//			alert("data empty"); 
//			exit;
//			}
//			}
//			function kali() {
//			cek();
//			a=eval(form.length.value);
//			b=eval(form.width.value);
//			c=eval(form.height.value);
//			d=a*b*c
//			form.volumeMatrix.value = d;
//			}
        </script>
 
    </body>
</html>
