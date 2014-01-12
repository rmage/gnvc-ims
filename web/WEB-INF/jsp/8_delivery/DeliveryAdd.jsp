<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Delivery Receipt</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    
                    $("#list2").jqGrid({ url:'sojson1.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Sales Order No','Sales Order Date','Delivery Date','Delivery Name','Delivery Address'], 
                                colModel:[ {name:'so_number',index:'so_number'}, {name:'so_date',index:'so_date'},{name:'delivery_date',index:'delivery_date'},
                                           {name:'delivery_name',index:'delivery_name'}, {name:'delivery_address',index:'delivery_address'}], 
                                sortname: 'so_number',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {     
                                    	// clear table
                                        $("#main tbody").html('');
                                    	
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#so_number").val(localRowData.so_number);
                                            $("#so_date").val(localRowData.so_date);
                                            $("#delivery_date").val(localRowData.delivery_date);
                                            $("#delivery_name").val(localRowData.delivery_name);
                                            $("#delivery_address").val(localRowData.delivery_address);
                                            $("#dialog").dialog('close');
                                            $.ajax({
                                                dataType: 'json',
                                                success: function(data) {
                                                     $.each(data.rows, function(k,v){                                                         
                                                        var rowCount = $('#main tr').length-1;
                                                        
                                                        $("<tr class=\"myhover\">"+
																"<td class=\"style1\">"+rowCount+"</td>"+
							                                    "<td class=\"style1\">"+v.productCode+" </td>"+
							                                    "<input type=\"hidden\" name=\"productCode\" value=\""+v.productCode+"\">"+
							                                    "<td class=\"style1\">"+v.productName+"</td>"+
							                                    "<input type=\"hidden\" name=\"productName\" value=\""+v.productName+"\">"+
							                                    "<td class=\"style1\">"+v.qty+"</td>"+
							                                    "<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\">"+
							                                    "<td class=\"style1\">"+v.unitCode+"</td>"+
							                                    "<input type=\"hidden\" name=\"sku\" value=\""+v.unitCode+"\">"+
							                                    "<input type=\"hidden\" name=\"pickingId\" value=\""+v.pickingId+"\">"+
							                                    "<input type=\"hidden\" name=\"productId\" value=\""+v.productId+"\">"+
								    					"</tr>").appendTo("#main tbody");
								    					console.log("product code="+v);
								    					
                                                    });
                                                },
                                                url: 'sodetailjson.htm?param='+localRowData.so_number
                                            });
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            	jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                });    
                
                
       </script>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Delivery dto = (com.app.wms.engine.db.dto.Delivery) m.get("dto");
            String mode = (String) m.get("mode");
            boolean isReadOnly = true;
  		    java.lang.String error = (String)m.get("msg");
        %>
    <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="purchaseOrderAdd.htm" method="post" id="pickingAddForm">
                        <input type="hidden" name="action" value="savePicking" />

                        <table class="collapse tblForm row-select">
                            <caption>Delivery Receipt - DR</caption>
                            <tbody class="tbl-nohover">

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">From</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="froms" id="froms" value="IM - PT. SPFI" size="30" readonly/>                                                    
                                        </label>
                                    </td>
                                  <td>&nbsp;</td>
                                    <td>DR Date</td>
                                    <td class="style1">
                                         <label>
                                            <input type="text" id="drdate" name="drdate" 
                                                   
                                                   value="<%= new SimpleDateFormat("MM/yyyy").format(new Date()) %>"
                                                   readonly
                                                   size="30"/>
                                            
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>To</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="to" name="to" value="" size="45" />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Location</td>
                                    <td class="style1">
                                         <label>
                                        	<textarea style="width: 374px; height: 71px;" id="location" name="location"></textarea>
                                        </label>
                                    </td>
                                    
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>OR No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="orNo" name="orNo" value="" size="30" />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">DM No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="dmNo" name="dmNo" value="" size="30" />
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Unit Cost</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="unitCost" name="unitCost" value="" size="30" />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Amount</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="amount" name="amount" value="" size="30" />
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Issued By / Delivery By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="unitCost" name="unitCost" value="" size="30" />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Accounting Entry</td>
                                    <td class="style1">
                                        <label>
                                        	<textarea style="width: 374px; height: 71px;" id="accEntry" name="accEntry"></textarea>
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="approvedBy" name="approvedBy" value="" size="30" />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Received By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="receivedBy" name="receivedBy" value="" size="30" />
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Remarks</td>
                                    <td class="style1">
                                        <label>
                                        	<textarea style="width: 374px; height: 71px;" id="remarks" name="remarks"></textarea>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td width="20%"></td>
                                    <td class="style1">
                                    </td>
                               </tr>
                               
			    		</tbody>                           
                  </table>

                        <div id="tabs">
                            <ul>
                                <li><a href="#product">Products</a></li>
                            </ul>
                           
                           <div id="product">
                               
                               <table style="margin-bottom: 0;">
                                   
                              
                               <tr class="detail_genap">
                                    
                                    <td width="20%">Product Code : </td>
                                    <td class="style1" width="30%">
                                        
                                            <input type="text" name="productcode" value="" size="30"  id="productcode" /> 
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        
                                    </td>
                                    
                                    <td width="20%">Qty : </td>
                                    <td class="style1" width="30%">
                                        <label>
                                            <input type="text" size="30" value="" name="jumlah" 
                                                   class="intValidate"
                                                   id="jumlah" />
                                        </label>
                                    </td>
                               </tr>
                                   
                               <tr class="detail_genap">
                                    
                                    <td width="20%">Product Name : </td>
                                    <td class="style1" width="30%">
                                        
                                        <input type="text" name="productname" value="" size="30" readonly="" id="productname" />
                                        
                                    </td>
                                    
<!--                                <td width="20%">Expiry Date : </td>
                                    <td class="style1" width="30%">
                                        <label>
                                            <input type="text" readonly="" size="30" value="" name="expirydate" id="expirydate" />
                                        </label>
                                    </td>-->
                               </tr>
                                   
                               <tr class="detail_genap">
                                    
                                   <td width="20%" valign="top">Product Description : </td>
                                    <td class="style1" width="30%">                                                                                    
                                        <textarea style="width: 374px; height: 71px;" id="productdesc" name="productdesc" readonly=""></textarea>
                                    </td>
                                    
                                   <td width="20%">Type : </td>
                                   
                                    <td align="center">
                                        
                                        <select name="tipebarang" id="tipebarang" style="width: 200px">
                                            <option value="General">General</option>
                                            <option value="Promo">Promo</option>
                                            <option value="Tester">Tester</option>                                            
                                          </select>                                        
                                    </td>
                               </tr>
                                   
                                    <tfoot>
                                <td colspan="4">
                                    <label>
                                        <input type="button" name="btnSavePurchase" id="btnAddItem" value="Add" 
                                               class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </label>
                                    <label>
                                        <input type="button" name="btnCancel" id="btnClearItem" value="Clear"
                                               class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;"/>
                                    </label>
                                </td>
                            </tfoot>
                               
                               
                               
                                </table>
                           </div>
                            
                        </div>

						<table class="collapse tblForm row-select"  id="main">
							<caption>Product - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Product </td>
	                                    <td class="style1">Product Qty</td>
	                                    <td class="style1">Type</td>
								    </tr>
								</thead>

                       
								<tbody>
									  
								</tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="4"></td>	                                    
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
                                                   name="btnSavePurchase" id="btnSavePurchase" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
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
       
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'Delivery.htm?action=findAll';
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
                
                $("#so_number").click(function () {
                	console.log("in clicked"); 
                    $("#dialog").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Sales Order No' });
            	});
                
                });
            
        </script>
        <script>
            $(".trans").click(function(){
                if($(this).val()==="EXTERNAL") {
                   // console.log("in clicked"); 
                    $(".hideable").show();
                }
                else { 
                   // console.log("ex clicked");
                    $(".hideable").hide();
                }
            });
        </script>
         <div id="dialog" title="Sales Order Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
    </body>
</html>