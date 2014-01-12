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
//        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
//            com.app.wms.engine.db.dto.Wh dto = (com.app.wms.engine.db.dto.Wh) m.get("dto");
//            String mode = (String) m.get("mode");
        %>

        
        
        <script>
            $(document).ready(function(){
                
                $('#pickingAddForm').validationEngine('attach');     
                
                 $('#receivedDate').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });

                $('#maximumDate').datepicker({                        
                   dateFormat: "dd/mm/yy"                        
                });
                
                
                $("#list2").jqGrid({ url:'purchaseorderjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['PO Number','Delivery Date', 'Created By','Corpo Id'], 
                                colModel:[ {name:'ponumber',index:'ponumber'}, {name:'deliverydate',index:'deliverydate'}, {name:'createdby',index:'createdby'}, 
                                    {name:'corpid',index:'corpid'}
                                     ], 
                                sortname: 'ponumber',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) { 
                                            // clear table
                                            $("#main tbody").html('');
                                            
                                            // fill
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#purchaseNo").val(localRowData.ponumber);                                                                           
                                            $("#dialog2").dialog('close');                                            
                                            $.ajax({
                                                dataType: 'json',
                                                success: function(data) {
                                                     $.each(data.poDetails, function(k,v){                                                         
                                                        var rowCount = $('#main tr').length-1;
                                                        
                                                        $("<tr>"+
										"<td class=\"style1\">"+rowCount+"</td>"+
	                                    "<td class=\"style1\">"+v.productCode+" </td>"+
	                                    "<input type=\"hidden\" name=\"productCode1\" value=\""+v.productCode+"\">"+
	                                    "<td class=\"style1\">"+v.productName+"</td>"+
	                                    "<td class=\"style1\">"+v.qty+"</td>"+	
                                            "<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\">"+
//                                             "<td class=\"style1\"><span id=\""+v.productCode+"-status\"></span> <input type=\"hidden\" name=\"status\" id=\""+v.productCode+"-status1\" /> "+
//                                             "<input type=\"hidden\" name=\"lotid\" id=\""+v.productCode+"-lotid\" />"+
//                                             "</td>"+
//                                             "<td class=\"style1\"><span id=\""+v.productCode+"-qtyreal\"></span> <input type=\"hidden\" name=\"qtyreal\" id=\""+v.productCode+"-qtyreal1\" /></td>"+
//                                             "<td class=\"style1\"><span id=\""+v.productCode+"-qtyplus\"></span> <input type=\"hidden\" name=\"remark\" id=\""+v.productCode+"-remark1\" /> </td>"+
//                                             "<td class=\"style1\"><span id=\""+v.productCode+"-qtyminus\"></span> <input type=\"hidden\" name=\"expirydate\" id=\""+v.productCode+"-expirydate1\" /> </td>"+                                              
								    "</tr>").appendTo("#main tbody")
                                                    });
                                                },
                                                url: 'purchaseorderdetailjson.htm?param='+localRowData.ponumber
                                            });
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                
            });
        </script>
        
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="CrossDockPOSave.htm" method="post" id="pickingAddForm">
                        <input type="hidden" name="mode" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>CrossDock - Add</caption>
                            <tbody class="tbl-nohover">
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td>PO Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="ponumber" value="" size="30" id="purchaseNo" 
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                             <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
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
                        
                        
                        <table class="collapse tblForm row-select" id="main">
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
                                                <td class="style1">Mendarat Dengan Selamat</td>
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
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSavePurchase" id="btnSavePurchase" value="Save" />
                                    </label>
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" />
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
            
            
        <div id="dialog2" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
            
            
        <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Tolong Lengkapi Semua Data
        </div>
            
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'CrossDock.htm';
                });

                $("#purchaseNo").click(function () {
                        $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select PO' });
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
            });
        </script>
    </body>
</html>