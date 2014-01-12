<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Crossdock</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Wh dto = (com.app.wms.engine.db.dto.Wh) m.get("dto");
            String mode = (String) m.get("mode");
        %>

        
        <script>
            $(document).ready(function(){
                    
                    
                    
                    $('#pickingAddForm').validationEngine('attach');     
                    
                    
                    
                    $("#list2").jqGrid({ url:'productjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Product Code','Product Name', 'Category','Brand Name', 'Description'], 
                                colModel:[ {name:'productCode',index:'productCode'}, {name:'productName',index:'productName'}, {name:'productCategory',index:'productCategory'}, 
                                    {name:'brandName',index:'brandName'},
                                    {name:'productDescription',index:'productDescription'}
                                     ], 
                                sortname: 'productCode',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#productcode").val(localRowData.productCode);
                                            $("#productname").val(localRowData.productName);                                                                               
                                            $("#dialog").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                            
                            
                             $('#receivedDate').datepicker({                        
                                dateFormat: "dd/mm/yy"                        
                             });
                            
                             $('#maximumDate').datepicker({                        
                                dateFormat: "dd/mm/yy"                        
                             });
                             
                             
                             /*
                     * button add
                     */
                    $('#btnAddItem').click(function(){            
                        var productname = $("#productname").val();
                        var jumlah = $("#jumlah").val();
                        if(productname == '' || jumlah == '') {
                            alert('Tolong lengkapi semua data');
                            return false;
                        }
                        var rowCount = $('#main tr').length-1;
                        $("<tr><td class=\"style1\">"+rowCount+"</td><td class=\"style1\">"+$("#productcode").val()+" </td><td class=\"style1\">"+$("#productname").val()+" </td><td class=\"style1\">"
                            +$("#jumlah").val()+"</td> "+
                            "<input type=\"hidden\" name=\"productcode1\" value=\""+$("#productcode").val()+"\" />"+                            
                            "<input type=\"hidden\" name=\"qty\" value=\""+$("#jumlah").val()+"\" /> </tr>").appendTo("#main tbody")
                        
                        $("#productcode").val('');
                        $("#productname").val('');
                        $("#jumlah").val('');                            
                    });
                             
                            
            });
                            
        </script>
        
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="CrossDockSave.htm" method="post" id="pickingAddForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>CrossDock - Add</caption>
                            <tbody class="tbl-nohover">
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Consignee Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consignee" value="" size="30" id="consignee" 
                                                   class="validate[required] text-input" /> 
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>Delivery To</td>
                                    <td class="style1">
                                        <label>
                                           <input type="text" name="deliveryTo" value="" size="30" id="deliveryTo" 
                                                  class="validate[required] text-input" />    
                                           <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                               </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Received Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="receivedDate" value="" size="30" id="receivedDate"
                                                   class="validate[required] text-input" />     
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>Delivery Date</td>
                                    <td class="style1">
                                       <label>
                                            <input type="text" name="maximumDate" value="" size="30" id="maximumDate"
                                                   class="validate[required] text-input" />       
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                               </tr>
                           

                            </tbody>                            
                        </table>
                        
                         <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                            <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all">
                                <li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active"><a href="#product">Product</a></li>
                            </ul>
                           
                           <div id="product" class="ui-tabs-panel ui-widget-content ui-corner-bottom">
                               
                               <table style="margin-bottom: 0;">
                                   
                              
                               <tbody><tr class="detail_genap">
                                    
                                    <td width="20%">Product Code : </td>
                                    <td width="30%" class="style1">
                                        
                                            <input type="text" id="productcode" size="30" value="" name="productcode"> 
                                            <img width="16" height="16" alt="Search Product" src="resources/images/search.png">
                                        
                                    </td>
                                    
                                    <td width="20%">Qty : </td>
                                    <td width="30%" class="style1">
                                        <label>
                                            <input type="text" id="jumlah" name="jumlah" value="" size="30">
                                        </label>
                                    </td>
                               </tr>
                                   
                               <tr class="detail_genap">
                                    
                                    <td width="20%">Product Name : </td>
                                    <td width="30%" class="style1">
                                        
                                        <input type="text" id="productname" readonly="" size="30" value="" name="productname" class="readonly">
                                        
                                    </td>

                               </tr>
                                   
                               
                                   
                                    </tbody><tfoot>
                                <tr><td colspan="4">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Add" id="btnAddItem" name="btnSavePurchase">
                                    </label>
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" value="Clear" id="btnClearItem" name="btnCancel">
                                    </label>
                                </td>
                            </tr></tfoot>
                               
                               
                               
                                </table>
                           </div>

                        </div>
                        
                        
                        <table class="collapse tblForm row-select"  id="main">
							<caption>Product - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Product Code </td>
	                                    <td class="style1">Product Name </td>	                                    
	                                    <td class="style1">Qty</td>	                                    	                                                                
								    </tr>
								</thead>

                       
								<tbody>
									 
                                                                          
                                                                          
<!--                                            <tr>
                                            <td class="style1">1.</td>
	                                    <td class="style1">001</td>
	                                    <td class="style1">Lipstik Merah Delima</td>	                                    
	                                    <td class="style1">20</td>
	                                    <td class="style1">Lokasi 1</td>
                                            </tr>-->
                                                                          
								</tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="5"></td>	                                   
	                                </tr>
	                            </tfoot>
                        </table>
                        
                        <table class="collapse tblForm row-select ui-widget-content">
                           
                            <tbody class="tbl-nohover">

                                
			    		</tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSavePurchase" id="btnSavePurchase" value="Save">
                                    </label>
                                    <label>
                                        <input type="submit" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel">
                                    </label>
                                </td>
                            </tr></tfoot>
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
                    location.href = 'CrossDock.htm';
                });
            });
            
            
             $("#productcode").click(function () {
                        $("#dialog").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Product' });
                });
                
                 
                
               $("#btnSavePurchase").click(function () {
                   
                    //if invalid do nothing
                    if(!$("#pickingAddForm").validationEngine('validate')){
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
                                $("form#pickingAddForm").submit();
                            }
                        },
                        zindex: 1, title: 'Confirm' });
                });
            
        </script>
        
        
        <div id="dialog" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
         <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Tolong Lengkapi Semua Data
        </div>
        
    </body>
</html>