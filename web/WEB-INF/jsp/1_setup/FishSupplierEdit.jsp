<%@page import="com.app.wms.engine.db.dto.FishSupplier"%>
<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<!DOCTYPE>
<html>
    <head>
        <title>IMS - New WS Data</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
            FishSupplier dto = (FishSupplier) m.get("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSupplier.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="id" value="<%=dto.getId()%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Supplier</caption>
                            <tbody class="tbl-nohover">
                            	<tr class="detail_genap">
                                    <td></td>
                                    <td>Supplier Code</td>
                                    <td>
                                        <label>
                                           <input type="text" name="code" id="code" size="30"                                                    
                                                   value="<%=dto.getCode()%>" readonly pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" /> 
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Telp.</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="telp" id="telp" size="30"                                                    
                                                   value="<%=dto.getTelephone()%>" pattern="^\S+[0-9]" required="true" />
                                            <label>*</label>
                                            </select>                                                            
                                        </label>
                                    </td>
                               	 </tr>
                            	 <tr class="detail_genap">
                                    <td></td>
                                    <td>Supplier Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="name" id="name" size="30"                                                    
                                                   value="<%=dto.getName()%>" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Fax</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="fax" size="25" id="fax" value="<%=dto.getFax()%>" />                                                           
                                        </label>
                                    </td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td>Address</td>
                                    <td>
                                        <label>
                                            <textarea type="text" name="address" id="address" rows="5" cols="40"                                                   
                                                   value="" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" ><%=dto.getAddress()%></textarea>
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Email</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="email" size="25" id="email" value="<%=dto.getEmail()%>" />                                                            
                                        </label>
                                    </td>  
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td></td>
                                    <td>Contact Person</td>
                                    <td>
                                        <label>
                                            <input type="text" name="cp" id="cp" size="30"                                                    
                                                   value="<%=dto.getContactPerson()%>" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
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
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnBack" id="btnBack" value="Back" class="cancel" />
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
                $('#btnBack').click(function() {
                    location.href = 'FishSupplier.htm';
                    return false;
                });

                $('#dateShift').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
            });
        </script>
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'FishType.htm';
                });
            });

//             $("#btnSave").click(function () {                         

                //if invalid do nothing
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
