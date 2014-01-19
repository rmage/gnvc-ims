<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - New Receiving Report (RR)</title>
            <%@include file="../metaheader.jsp" %>
            <script language="JavaScript">
                    $(document).ready(function(){
                                           
                      $('#grAddForm').validationEngine('attach');     
                      
                        $('#expirydate').datepicker({                        
                            dateFormat: "dd/mm/yy"                        
                        });
                        
                        
                        $("#list2").jqGrid({ url:'purchaseorderjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['PO Number','Delivery Date','Supplier','Created By'], 
                                colModel:[ {name:'ponumber',index:'ponumber'}, {name:'deliverydate',index:'deliverydate'}, {name:'supplier',index:'supplier'},{name:'createdby',index:'createdby'}
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
                                            $("#supplier").val(localRowData.supplier);
                                            
                                            $("#dialog2").dialog('close');                                            
                                            $.ajax({
                                              dataType: 'json',
                                              success: function(data) {
                                              $.each(data.poDetails, function(k,v){                                                         
                                              var rowCount = $('#main tr').length-1;
		                                        $("<tr class=\"myhover\">"+
												"<td class=\"style1\">"+rowCount+"</td>"+
			                                    "<td class=\"style1\">"+v.productCode+" </td>"+
			                                    "<input type=\"hidden\" name=\"productCode1\" value=\""+v.productCode+"\">"+
			                                    "<td class=\"style1\">"+v.productName+"</td>"+
			                                    "<td class=\"style1\">"+v.qty+"</td>"+	                                   
	                                            "<td class=\"style1\"><span id=\""+rowCount+"-qtyreal\"></span> <input type=\"hidden\" name=\"status\" id=\""+rowCount+"-status1\" /> "+
	//                                             "<input type=\"hidden\" name=\"lotid\" id=\""+v.productCode+"-lotid\" />"+
	                                            "</td>"+
	                                            "<td class=\"style1\"><span id=\""+rowCount+"-qtygood\"></span> <input type=\"hidden\" name=\"qtyreal\" id=\""+rowCount+"-qtyreal1\" /></td>"+
	                                            "<td class=\"style1\"><span id=\""+rowCount+"-qtydmg\"></span> <input type=\"hidden\" name=\"remark\" id=\""+rowCount+"-remark1\" /> </td>"+
	                                            "<td class=\"style1\"> <span id=\""+rowCount+"-qtyplus\"></span> <input type=\"hidden\" name=\"expirydate\" id=\""+rowCount+"-expirydate1\" /> </td>"+
	                                            "<td class=\"style1\"><span id=\""+rowCount+"-qtyminus\"></span></td>"+
	                                            "<input type=\"hidden\" value=\"0\" name=\"qtygood\" id=\""+rowCount+"-qtygood1\" />"+
	                                            "<input type=\"hidden\" value=\"0\" name=\"qtydmg\" id=\""+rowCount+"-qtydmg1\" />"+
	                                            "<td class=\"style1\"><a class=\"check\" productCode=\""+v.productCode+"\" "+
	                                            "productName=\""+v.productName+"\" rowCount=\""+rowCount+"\" qty=\""+v.qty+"\" href=\"javascript:void(0)\">Check</a></td>"+
									    		"</tr>").appendTo("#main tbody")});
                                            	},
                                                	url: 'purchaseorderdetailjson.htm?param='+localRowData.ponumber
                                            	});
                                        	} 
                                		},
                                		pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            			jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                        
                    					});
            </script>
            
            
            <script>
            function formatCurrency(n) {
            	n = isNaN(n) || n === '' || n === null ? 0.00 : n;
    			return parseFloat(n).toFixed(2);
    		}
    		
    		function numberWithCommas(n) {
    			var parts=n.toString().split(".");
    			return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
    		}
        
	       	function isNumberKey(evt)
	       	{
	    	   var charCode = (evt.which) ? evt.which : evt.keyCode;

	    	   if(charCode==8 || charCode==13|| charCode==99|| charCode==118 || charCode==46)
	    	     {    return true;  }
	    	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	    	    {  return false; }
	    	    return true;

	       	}

	       	
	       	function changeQtyRealDecimal(){
		       	a = $("#qtyreal").val();
		       	b = formatCurrency(a);
		       	c = numberWithCommas(b);

		       	$("#qtyreal").val(c);
		       	
	       	}
	       	
	       	function changeQtyGoodDecimal(){
		       	a = $("#qtygood").val();
		       	b = formatCurrency(a);
		       	c = numberWithCommas(b);

		       	$("#qtygood").val(c);
		       	$("#qtyreal").val($("#qtygood").val());
	       	}
	       	
	       	function changeQtyDamageDecimal(){
		       	a = $("#qtydmg").val();
		       	b = formatCurrency(a);
		       	c = numberWithCommas(b);

		       	$("#qtydmg").val(c);
		       	d = eval ($("#qtygood").val());
		       	e = eval ($("#qtydmg").val());
		       	f = d + e;
		       	
		       	$("#qtyreal").val(f);
		       	
		       	g = $("#qtyreal").val();
		       	h = formatCurrency(g);
		       	i = numberWithCommas(h);

		       	$("#qtyreal").val(i);
	       	}
            
            </script>
        </head>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
  		    // String msg = (String) m.get("msg");
  		    com.app.wms.engine.db.dto.Product product = (com.app.wms.engine.db.dto.Product) request.getSession().getAttribute("product");
  		    com.app.wms.engine.db.dto.Customer customer = (com.app.wms.engine.db.dto.Customer) request.getSession().getAttribute("customer");
  		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE, dd MM yyyy");
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    
  		    boolean isReadOnly = true;
  		    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        
        %>


        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="goodReceiveAdd.htm" method="post" id="grAddForm">
                        <input type="hidden" name="action" value="savePicking" />

                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report - RR</caption>
                            <tbody class="tbl-nohover">    
                            
                              <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">RR No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="rrNo" id="rrNo" value="" size="30" class="validate[required] text-input" />     
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>RR Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="grreceivedate" name="grreceivedate" 
                                                   
                                                   value="<%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>"
                                                   readonly
                                                   size="30"/>
                                        </label>
                                    </td>
                               </tr>                           
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="purchaseNo" id="purchaseNo" size="30" readonly class="validate[required] text-input datepicker" value=""
                                                   />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                             <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>
                                    </td>
                                    <td>Supplier</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="supplier" id="supplier" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                    
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Department</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="departmentName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListDepartment}">
	                                        		  <option value="${droplist.departmentCode}" ${(droplist.departmentCode eq requestScope.model.departmentCode)? "selected": ""}>
	                                        		  	${droplist.departmentCode} - ${droplist.departmentName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>Warehouse</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="warehouse">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListWarehouse}">
	                                        		  <option value="${droplist.whCode}" ${(droplist.whCode eq requestScope.model.whCode)? "selected": ""}>
	                                        		  	${droplist.whCode} - ${droplist.whName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                               </tr>
                                
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Received By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="receivedBy" id="receivedBy" value="" size="30" />     
                                        </label>
                                    </td>
                                    <td>Checked By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="checkedBy" id="checkedBy" value="" size="30" />     
                                        </label>
                                    </td>
                               </tr>
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Evaluated By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="evaluatedBy" id="evaluatedBy" value="" size="30" />     
                                        </label>
                                    </td>
                                    <td>Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="approvedBy" id="approvedBy" value="" size="30" />     
                                        </label>
                                    </td>
                               </tr>
                               
			    		</tbody>
                  </table>

						<table class="collapse tblForm row-select"  id="main">
							<caption>Item - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Item Code </td>
	                                    <td class="style1">Item Name</td>
	                                    <td class="style1">Qty</td>
                                        <td class="style1">Qty Received</td>
                                        <td class="style1">Qty Good</td>
                                        <td class="style1">Qty Damaged</td>
                                        <td class="style1">+</td>
                                        <td class="style1">-</td>
                                        <td class="style1">Action</td>
								    </tr>
								</thead>
                                <tbody>									                                                                                                                                                     
                                                                                                
                                </tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="9"></td>
	                                    <td></td>
	                                </tr>
	                            </tfoot>
                        </table>
                        
                        
                        
                        <table class="collapse tblForm row-select ui-widget-content">
                           
                            <tbody class="tbl-nohover">

                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" 
                                               role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                               name="btnSavePurchase" id="btnSavePurchase" value="Save" />
                                    </label>
                                    <label>
                                        <input type="submit" style="font-size: smaller;" aria-disabled="false" 
                                               role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                               name="btnCancel" id="btnCancel" value="Cancel" />
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
                $('#btnCancel').click(function() {
                    location.href = 'GoodReceive.htm';
                });

                var cookieName, $tabs, stickyTab;

                cookieName = 'stickyTab';
                $tabs = $( '#tabs' );

                $tabs.tabs( {
                    select: function( e, ui )
                    {
                        $.cookies.set( cookieName, ui.index );
                    }
                } );

                stickyTab = $.cookies.get( cookieName );
                if( ! isNaN( stickyTab )  )
                {
                    $tabs.tabs( 'select', stickyTab );
                }
                
                });
                
                
                $("#purchaseNo").click(function () {
                        $("#dialog2").dialog({ width: 450, height: 275, position: "center", modal: true, zindex: 9999, title: 'Select PO' });
                });
                
                $('.check').live("click", function() {
                    
                    // clear values
                    $('#qtyreal').val('0');  
                    $('#remark').val('');  
//                    $('#lotid').val('');  
                    $('#expirydate').val('');  
                    $('#qtygood').val('0');  
                    $('#qtydmg').val('0');  
                    
                    // setup values
                    $('#productCode').val($(this).attr('productCode'));                        
                    $('#productName').val($(this).attr('productName'));                        
                    $('#qtypo').val($(this).attr('qty'));                     
                    
                    var productCode = $(this).attr('rowcount');
                    var qty = $(this).attr('qty');
                    $("#dialog").dialog({ width: 440, height: 525, position: "center", modal: true, 
                           buttons: {
                               "Cancel": function() {                                       
                                       $( this ).dialog( "close" );                                        
                               },
                               "Save": function() { 
                                   
                                   if($('#qtygood').val() == "" || $('#qtydmg').val() == "" || $('#qtyreal').val() == "" ) 
                                       return false;
                                                   
                                   if(parseInt($('#qtygood').val()) + parseInt($('#qtydmg').val())  != parseInt($('#qtyreal').val()) ) {
                                       alert('Quantity Total is not the same');
                                       return false;
                                   }
                                       
                                   
                                   $("#"+productCode+"-status").html($('input:radio[name=status]:checked').val());
                                   $("#"+productCode+"-qtyreal").html($('#qtyreal').val());
                                   $("#"+productCode+"-status1").val($('input:radio[name=status]:checked').val());
                                   $("#"+productCode+"-qtyreal1").val($('#qtyreal').val());
                                   $("#"+productCode+"-remark1").val($('#remark').val());
                                   $("#"+productCode+"-qtygood1").val($('#qtygood').val());
                                   $("#"+productCode+"-qtydmg1").val($('#qtydmg').val());
                                   
                                   $("#"+productCode+"-qtygood").html("0");
                                   $("#"+productCode+"-qtydmg").html("0");
                                   
                                   if($('#qtygood').val() != "")
                                        $("#"+productCode+"-qtygood").html($('#qtygood').val());
                                   if($('#qtydmg').val() != "") 
                                        $("#"+productCode+"-qtydmg").html($('#qtydmg').val());
                                                                     
                                   $("#"+productCode+"-expirydate1").val($('#expirydate').val());
//                                   $("#"+productCode+"-lotid").val($('#lotid').val());
                                   
                                   $("#"+productCode+"-qtyplus").html('0');
                                   $("#"+productCode+"-qtyminus").html('0');
                                   
                                   var qtyreal = $('#qtyreal').val();                                   
                                   if(qtyreal - qty > 0)
                                       $("#"+productCode+"-qtyplus").html(qtyreal - qty);                                   
                                   if(qtyreal - qty < 0)
                                       $("#"+productCode+"-qtyminus").html(qty - qtyreal );
                                   
                                   $( this ).dialog( "close" );
                               }
                           },
                        zindex: 9999, title: 'Set Item Status' });                     
                });        
                
                $("#btnSavePurchase").click(function () {
                
                    //if invalid do nothing
                    if(!$("#grAddForm").validationEngine('validate')){
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
                                $("form#grAddForm").submit();
                    }           
                        },
                        zindex: 9999, title: 'Confirm' });
                });
        </script>
        
         <div id="dialog" title="Product Search" style="display:none;z-index:1;">
             <form>
                 <table>
                     <tr>
                         <td> <label>Product Code</label></td>
                         <td><input type="text" name="productCode" id="productCode" readonly /> </td>
                     </tr>
                     <tr>
                         <td><label>Product Name</label></td>
                         <td><input type="text" name="productName" id="productName" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Qty PO </label></td>
                         <td> <input type="text" name="qtypo" id="qtypo" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Qty Received</label></td>
                         <td> <input type="text" name="qtyreal2" id="qtyreal" value="0.00"
                                     onblur="changeQtyRealDecimal()"
                                     onkeypress="return isNumberKey(event)"
                                     readonly
                                     /></td>
                     </tr>
                     <tr><td></td><td>----------------------------------</td></tr>
                     <tr>
                         <td><label>Qty Good</label></td>
                         <td> <input type="text" name="qtygood2" id="qtygood" value="0.00" 
                                     onblur="changeQtyGoodDecimal()"
                                     onkeypress="return isNumberKey(event)" 
                                     /></td>
                     </tr>
                     <tr>
                         <td><label>Qty Damaged</label></td>
                         <td> <input type="text" name="qtydmg2" id="qtydmg" value="0.00" 
                                     onblur="changeQtyDamageDecimal()"
                                     onkeypress="return isNumberKey(event)"
                                     /></td>
                     </tr>
                     <tr>
                         <td><label>Expiry Date</label></td>
                         <td> <input type="text" name="expirydate" id="expirydate" value="" /></td>
                     </tr>
                     <tr>
                         <td><label>Remark</label></td>
                         <td> <textarea type="text" name="remark" id="remark" value="" style="width: 178px; height: 80px;"></textarea></td>
                     </tr>    
                 </table>                                 
            </form>
        </div>
        
        
        
        <div id="dialog2" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
    </body>
</html>
