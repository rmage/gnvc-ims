<%@page import="java.util.Date"%>
<%@page import="com.app.wms.engine.db.dto.Canvassing"%>
<%@page import="com.app.wms.engine.db.dto.CanvassingDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Canvassing</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#addForm').validationEngine('attach');        
                    
                });

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

		       	function changeFormatMoney(){
			       	a = $("#unitprice").val();
			       	b = formatCurrency(a);
			       	c = numberWithCommas(b);
			    	$("#unitprice").val(c);
		       	}

		       	function changeFormatDiscount(){
		       		d = $("#discount").val();
					e = formatCurrency(d);
					f = numberWithCommas(e);
					$("#discount").val(f);
		       	} 

		       	function changeFormatPph(){
		       		g = $("#pph").val();
					h = formatCurrency(g);
					i = numberWithCommas(h);
					$("#pph").val(i);
		       	} 

		       	function changeFormatPpn(){
		       		j = $("#ppn").val();
					k = formatCurrency(j);
					l = numberWithCommas(k);
					$("#ppn").val(l);
		       	}  
		       	 	
        </script> 
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            Canvassing dto = (Canvassing) m.get("dto");
            CanvassingDetail dtod = (CanvassingDetail) m.get("dtod");
            String mode = (String) m.get("mode");
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.text.SimpleDateFormat warrantyFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
            Integer id = dtod.getId();
            String prsnumber = dtod.getPrsnumber();
            String canvassername = dto.getCanvassername();
            String remarks = dto.getRemarks();
            java.util.List<CanvassingDetail> cd = (java.util.List<CanvassingDetail>)m.get("cd");
        
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Canvassing.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                    	<input type="hidden" name="id" value="<%=id%>" />
                    	<input type="hidden" name="prsnumber" value="<%=prsnumber%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Canvassing - Detail</caption>
                            <tbody class="tbl-nohover">
                               
                                <tr>
                                   <td class="style1">&nbsp;</td>
                                   <td class="style1">Prs No</td>
                                   <td class="style1">
                                   		<label>
                                            <input type="text" class="shorttext" name="prsnumber"  value="<%=prsnumber%>" maxlength="25" size="30" readonly/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td class="style1">Remarks</td>
                                	<td class="style1">
                                    	<label>
                                           <textarea style="width: 374px; height: 51px;" id="remarks" name="remarks" value="<%=remarks%>" readonly></textarea>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                   <td class="style1">&nbsp;</td>
                                   <td class="style1">Canvasser Name</td>
                                   <td class="style1">
                                   		<label>
                                            <input type="text" class="shorttext" name="canvassername"  value="<%=canvassername%>" maxlength="25" size="30" readonly/>
                                        </label>
                                   </td>
                                   <td>Canvassing Updated Date</td>
                                   <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="canvassingdate" size="25" id="canvassingdate" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                   </td>
                                </tr>
                                <tr>
                                	<td class="style1">&nbsp;</td>
                                    <td class="style1">Selected Canvassing</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="selected" value="Y" <% if (dtod.getIsSelected().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
										</label>
										<label>
										    <input type="radio" name="selected" value="N" <% if (dtod.getIsSelected().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
					                    </label>
					                    <label class="requiredfield" title="This Field Is Required!">required for PO</label>
                                   </td>
                                   <td></td>
                                   <td></td>
                               	</tr>
                            </tbody>
                        </table>
                        
                        <table class="collapse tblForm row-select"  id="main">
							<caption>Canvassing - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Item</td>
	                                    <td class="style1">UoM</td>
	                                    <td class="style1">Qty</td>
	                                    <td class="style1">Unit Price</td>
	                                    <td class="style1">Warranty</td>
										<td class="style1">Term of Payment</td>
										<td class="style1">Term of Delivery</td>
										<td class="style1">Discount</td>
										<td class="style1">PPH</td>
										<td class="style1">PPN</td>
	                                    <td class="style1">Supplier</td>
	                                    <td class="style1">Action</td>
								    </tr>
								</thead>

                    
								<tbody>
									<%int nomer = 1; 
									for(CanvassingDetail valueSearch : cd){
								    %>
								<tr>
									<td class="center" width="1%">
                                        <%= nomer %>
                                    </td>
                                    <input type="hidden" value="<%= valueSearch.getProductcode() %>" name="productCode1" />
									<input type="hidden" value="<%= valueSearch.getProductname() %>" name="productName1" />
									<input type="hidden" value="<%= valueSearch.getPrsDetail().getUomName() %>" name="uom" />
									<input type="hidden" value="<%= valueSearch.getPrsDetail().getQty() %>" name="qty" />
									<input type="hidden" value="<%= valueSearch.getSupplierCode() %>" name="supplier1" />
									<td class="style1"><%= valueSearch.getProductname() %></td>
									<td class="style1"><%= valueSearch.getPrsDetail().getUomName() %></td>
									<td class="style1"><%= valueSearch.getPrsDetail().getQty() %></td>
									<td class="style1" id="unitprices"><%=valueSearch.getPriceunit() %></td>
									<input type="hidden" value="<%=valueSearch.getPriceunit() %>" name="unitprice1" id="unitprice1"/>
									<%if(valueSearch.getWarranty() != null ){ %>
										<td class="style1" id="warrantys"><%= warrantyFormat.format(valueSearch.getWarranty()) %></td>
										<input type="hidden" value="<%= warrantyFormat.format(valueSearch.getWarranty()) %>" name="warranty1" id="warranty1"/>
									<%}else{%>
										<td class="style1" id="warrantys"></td>
										<input type="hidden" value="" name="warranty1" id="warranty1"/>
									<%}%>
									
									<%if(valueSearch.getTermpayment() != null ){ %>
										<td class="style1" id="termpayments"><%=valueSearch.getTermpayment() %></td>
										<input type="hidden" value="<%=valueSearch.getTermpayment() %>" name="termpayment1" id="termpayment1"/>
									<%}else{%>
										<td class="style1" id="termpayments"></td>
										<input type="hidden" value="" name="termpayment1" id="termpayment1"/>
									<%}%>	
									
									<%if(valueSearch.getTermdelivery() != null ){ %>
										<td class="style1" id="termdeliverys"><%=valueSearch.getTermdelivery() %></td>
										<input type="hidden" value="<%=valueSearch.getTermdelivery() %>" name="termdelivery1" id="termdelivery1"/>
									<%}else{%>
										<td class="style1" id="termdeliverys"></td>
										<input type="hidden" value="" name="termdelivery1" id="termdelivery1"/>
									<%}%>
									
									<%if(valueSearch.getDiscount() != null ){ %>
										<td class="style1" id="discounts"><%=valueSearch.getDiscount() %></td>
										<input type="hidden" value="<%=valueSearch.getDiscount() %>" name="discount1" id="discount1"/>
									<%}else{%>
										<td class="style1" id="discounts"></td>
										<input type="hidden" value="" name="discount1" id="discount1"/>
									<%}%>	
										
									<%if(valueSearch.getPph() != null ){ %>
										<td class="style1" id="pphs"><%=valueSearch.getPph() %></td>
										<input type="hidden" value="<%=valueSearch.getPph() %>" name="pph1" id="pph1"/>
									<%}else{%>
										<td class="style1" id="pphs"></td>
										<input type="hidden" value="" name="pph1" id="pph1"/>
									<%}%>		
										
									<%if(valueSearch.getPpn() != null ){ %>
										<td class="style1" id="ppns"><%=valueSearch.getPpn() %></td>
										<input type="hidden" value="<%=valueSearch.getPpn() %>" name="ppn1" id="ppn1"/>
									<%}else{%>
										<td class="style1" id="ppns"></td>
										<input type="hidden" value="" name="ppn1" id="ppn1"/>
									<%}%>	
										
									<td class="style1"><%= valueSearch.getSupplierCode() %></td>
									<td class="style1">
										<a productcode="<%= valueSearch.getProductcode() %>"  
	                                       productname="<%= valueSearch.getProductname() %>"
	                                       uom="<%= valueSearch.getPrsDetail().getUomName() %>"
	                                       qty="<%= valueSearch.getPrsDetail().getQty() %>" 
	                                       unitprice1="<%= valueSearch.getPriceunit() %>"
	                                       supplier="<%= valueSearch.getSupplierCode() %>"
	                                       class="check" href="javascript:void(0)" nomer="<%= nomer %>">Update
	                                    </a>
	                                   
                                    </td>
								</tr>
								<%
									nomer++;
									}
								%>	  
								</tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="15"></td>	                                    
	                                </tr>
	                            </tfoot>
                        </table>
                        
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">
                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="15">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Update" class="simpan" />
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
                    location.href = 'Canvassing.htm';
                    return false;
                });

                $("#warranty").datepicker({                        
                    dateFormat: "dd/mm/yy"                        
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
	                        "Update": function() {
	                            $("form#addForm").submit();
	                        }
	                    },
	                    zindex: 1, title: 'Confirm' 
	                });

            	});

            $('.check').live("click", function() {

  				// clear values
                $("#unitprice").val("0");

                // setup values
                $('#supplier').val($(this).attr('supplier'));
                $('#productCode').val($(this).attr('productcode'));                        
                $('#productName').val($(this).attr('productname')); 
                $('#UoM').val($(this).attr('uom'));      
                $('#qty1').val($(this).attr('qty'));                     
                
                var productCode = $(this).attr('rowcount');
                var qty = $(this).attr('qty');
                $("#dialog3").dialog({ width: 440, height: 600, position: "center", modal: true, 
                       buttons: {
                           "Cancel": function() {     

				                   $("#unitprice").val("0");
                                   $( this ).dialog( "close" );                                        
                           },
                           "Update": function() { 

                        	   /*
							   if( $("#unitprice").val() == "0" ){
									alert("data empty : 'please to entry unit price' ");
									return false;
							   }
							   */				
                            	   $("#"+productCode+"-unitprice2").html($('#unitprice').val());
                            	   $("#"+productCode+"-supplier2").html($('#supplier').val());

                                   $("#"+productCode+"-unitprice2").val($('#unitprice').val());
                                   $("#"+productCode+"-supplier2").val($('#supplier').val());

                                   $("#"+productCode+"-unitprice1").val($('#unitprice').val());
                                   $("#"+productCode+"-supplier1").val($('#supplier').val());

                                   $('#unitprice1').val($('#unitprice').val());
                                   $('#unitprices').html($('#unitprice1').val());
                                   $('#unitprices').val($('#unitprice1').val());

                                   $('#warranty1').val($('#warranty').val());
                                   $('#warrantys').html($('#warranty1').val());
                                   $('#warrantys').val($('#warranty1').val());

                                   $('#termpayment1').val($('#termpayment').val());
                                   $('#termpayments').html($('#termpayment1').val());
                                   $('#termpayments').val($('#termpayment1').val());

                                   $('#termdelivery1').val($('#termdelivery').val());
                                   $('#termdeliverys').html($('#termdelivery1').val());
                                   $('#termdeliverys').val($('#termdelivery1').val());

                                   $('#discount1').val($('#discount').val());
                                   $('#discounts').html($('#discount1').val());
                                   $('#discounts').val($('#discount1').val());

                                   $('#pph1').val($('#pph').val());
                                   $('#pphs').html($('#pph1').val());
                                   $('#pphs').val($('#pph1').val());

                                   $('#ppn1').val($('#ppn').val());
                                   $('#ppns').html($('#ppn1').val());
                                   $('#ppns').val($('#ppn1').val());

                                   $( this ).dialog( "close" );
							  
                               
                           }
                       },
                    zindex: 9999, title: 'Update Item Price' });                     
            }); 

        </script>
        
        <div id="dialog2" title="PRS Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
           <div id="dialog3" title="Item Search" style="display:none;z-index:1;">
             <form>
                 <table>
                 	  <tr>
                         <td> <label>Supplier</label></td>
                          <td><input type="text" name="supplier" id="supplier" size="45" readonly /> </td>
                         <%-- 
                         <td class="style1">
                                <label>
                                 <select name="supplierName" id="supplier">
                                 	<c:forEach var="droplist" items="${requestScope.model.dropListSupplier}">
                                 		  <option value="${droplist.supplierName}" ${(droplist.supplierName eq requestScope.model.supplierName)? "selected": ""}>
                                 		  	${droplist.supplierName}
                                 		  </option> 
                                 	</c:forEach>
                                 </select>
                            	</label>
                          </td>
                        --%>
                     </tr>
                     <tr>
                         <td> <label>Item Code</label></td>
                         <td><input type="text" name="productCode" id="productCode" readonly /> </td>
                     </tr>
                     <tr>
                         <td><label>Item Name</label></td>
                         <td><input type="text" name="productName" id="productName" size="45" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Unit</label></td>
                         <td> <input type="text" name="uom" id="UoM" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Qty</label></td>
                         <td> <input type="text" name="qty1" id="qty1" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Unit/Price</label></td>
                         <td> <input type="text" name="unitprice" id="unitprice" value="0"
                                     onblur="changeFormatMoney()"
                                     onkeypress="return isNumberKey(event)"  
                                     />
                               <label class="requiredfield" title="This Field Is Required!">*</label>
                         </td>
                     </tr>
                     <tr>
                         <td><label>Warranty Period</label></td>
                         <td> <input type="text" name="warranty" id="warranty" /></td>
                     </tr>
                     <tr>
                         <td><label>Term of Payment</label></td>
                         <td> <input type="text" name="termpayment" id="termpayment" size="45" /></td>
                     </tr>
                     <tr>
                         <td><label>Term of Delivery</label></td>
                         <td> <input type="text" name="termdelivery" id="termdelivery" size="45" /></td>
                     </tr>
                     <tr>
                         <td><label>Discount</label></td>
                          <td> <input type="text" name="discount" id="discount" value="0"
                                     onblur="changeFormatDiscount()"
                                     onkeypress="return isNumberKey(event)"  
                                     />
                         </td>
                     </tr>
                     <tr>
                         <td><label>PPH</label></td>
                          <td> <input type="text" name="pph" id="pph" value="0"
                                     onblur="changeFormatPph()"
                                     onkeypress="return isNumberKey(event)"  
                                     />
                         </td>
                     </tr>
                     <tr>
                         <td><label>PPN</label></td>
                          <td> <input type="text" name="ppn" id="ppn" value="0"
                                     onblur="changeFormatPpn()"
                                     onkeypress="return isNumberKey(event)"  
                                     />
                         </td>
                     </tr>
                 </table>                                 
            </form>
        	</div>
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Update data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
    </body>
</html>