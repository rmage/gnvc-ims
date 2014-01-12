<%@page import="com.app.wms.engine.db.dto.PickingDetail"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - Update Picking</title>
            <%@include file="../metaheader.jsp" %>
            
                  <script language="JavaScript">
                    $(document).ready(function(){
                    	
                        $('.check').live("click", function() {
                        	
                        	// clear values
                            $('#productcode').val('');  
                            $('#productname').val('');  
                            $('#qty').val(''); 
                            $('#balance').val(''); 
                        	
                        	// clear table
                            $("#locationPicking tbody").html('');
                        	
                        	var jumlahRow = 1;
                        	
                        	$.ajax({
                                dataType: 'json',
                                success: function(data) {
                                     $.each(data.putawayDetail, function(k,v){
                                        var rowCount = $('#locationPicking tr').length+1;
                                        jumlahRow = jumlahRow+1;
                                        $("<tr class=\"myhover\">"+                                            
                                                "<td class=\"style1\">"+v.locationCode+"</label></td>"+
                                                "<td class=\"style1\">"+v.locationName+"</td>"+
                                                //"<td class=\"style1\">"+v.receivedDate+"</td>"+
                                                //"<td class=\"style1\">"+v.expiredDate+"</label></td>"+
                                                "<td class=\"style1\">"+v.qty+"</td>"+
                                                //"<td class=\"style1\">"+v.lotId+"</td>"+
                                                "<input type=\"hidden\" name=\"productcode\" value=\""+v.productCode+"\">"+
                                                "<input type=\"hidden\" name=\"location\" id=\"location"+rowCount+"\" value=\""+v.locationCode+"\">"+
                                                //"<input type=\"hidden\" name=\"receivedDate\" id=\"receivedDate"+rowCount+"\" value=\""+v.receivedDate+"\">"+
                                                //"<input type=\"hidden\" name=\"expiryDate\" id=\"expiryDate"+rowCount+"\" value=\""+v.expiredDate+"\">"+
                                                //"<input type=\"hidden\" name=\"lotId\" id=\"lotId"+rowCount+"\" value=\""+v.lotId+"\">"+
                                                "<td><input type=\"text\" name=\""+v.locationCode+"\" value=\"0\" id=\"value"+rowCount+"\" /></td>"+
                                            "</tr>").appendTo("#locationPicking tbody")  
                                        });
                                    },
                                    url: 'locationproductpicking2.htm?productId='+$(this).attr('productcode')+'&lotId='+$(this).attr('lotId')                             
                                  });
                        	
                        	// setup values
                        	$('#productcode').val($(this).attr('productcode')); 
                            $('#productname').val($(this).attr('productname')); 
                            $('#balance').val($(this).attr('balance'));
                            $('#qty').val($(this).attr('qty'));
                            
                            var nomer123 = ($(this).attr('nomer'));
                            
                            
                            var productCode = $(this).attr('productcode');
                            $("#dialog").dialog({ width: 800, height: 400, position: "center", modal: true, 
                                buttons: {
                                    "Cancel": function() {                                       
                                            $( this ).dialog( "close" );                                        
                                    },
                                    "Save": function() {
                                    	
                                    	var locationBr = "";
                                    	//var receivedDateBr = "";
                                    	//var expiryDateBr = "";
                                    	//var lotIdBr = "";
                                    	var valueBr = "";
                                    	
                                    	for(var i = 2; i <= jumlahRow ; i++){
                                    		
                                    		locationBr = locationBr + $('#location'+i).val()+"<br />";
                                    		//receivedDateBr = receivedDateBr + $('#receivedDate'+i).val()+"<br />";
                                    		//expiryDateBr = expiryDateBr + $('#expiryDate'+i).val()+"<br />";
                                    		//lotIdBr = lotIdBr + $('#lotId'+i).val()+"<br />";
                                    		valueBr = valueBr + $('#value'+i).val()+"<br />";
                                    		 
                                    	}
                                    	
                                    	$('#'+productCode+"-location").html(locationBr);
                                    	//$('#'+productCode+"-receivedDate").html(receivedDateBr);
                                    	//$('#'+productCode+"-expiryDate").html(expiryDateBr);
                                    	//$('#'+productCode+"-lotId").html(lotIdBr);
                                    	$('#'+productCode+"-value").html(valueBr);
                                    	
                                    	$('#'+productCode+"-location1").val(locationBr);
                                    	//$('#'+productCode+"-receivedDate1").val(receivedDateBr);
                                    	//$('#'+productCode+"-expiryDate1").val(expiryDateBr);
                                    	//$('#'+productCode+"-lotId1").val(lotIdBr);
                                    	$('#'+productCode+"-value1").val(valueBr);
                                    	
                                    	$('#'+nomer123+"value").html(valueBr);
                                    	$('#'+nomer123+"value1").val(valueBr);
                                    	
                                    	console.log("valueBr ="+valueBr);
		                                    $( this ).dialog( "close" );
                                    }
                                },
                                zindex: 1, title: 'Set Location Picking' });
                         });
                    	
                       
                       $("#list2").jqGrid({ url:'tallymanjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Tallyman Code','Name', 'Tally Name','Job Function'], 
                                colModel:[ {name:'code',index:'code'}, {name:'name',index:'name'}, {name:'tallyname',index:'tallyname'}, 
                                    {name:'jobfunction',index:'jobfunction'}                                    
                                     ], 
                                sortname: 'code',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#tallyman").val(localRowData.code+" ~ "+localRowData.name);                                                                           
                                            $("#dialog2").dialog('close');
                                        } 
                                },
                            pager: '#pager2', sortname: 'code', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                            
                       $("#list3").jqGrid({ url:'sojson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
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
                                       $("#dialog1").dialog('close');
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
		                                    "<input type=\"hidden\" name=\"unitCode\" value=\""+v.unitCode+"\">"+
		                                    "<td class=\"style1\">"+v.balance+"</td>"+
		                                    "<input type=\"hidden\" name=\"productId\" value=\""+v.productId+"\">"+
		                                    "<input type=\"hidden\" name=\"balance\" value=\""+v.balance+"\">"+
		                                    "<input type=\"hidden\" name=\"location1\" id=\""+v.productCode+"-location1\" /> "+
		                                    //"<input type=\"hidden\" name=\"receivedDate1\" id=\""+v.productCode+"-receivedDate1\" /> "+
		                                    //"<input type=\"hidden\" name=\"expiryDate1\" id=\""+v.productCode+"-expiryDate1\" /> "+
		                                    //"<input type=\"hidden\" name=\"lotId1\" id=\""+v.productCode+"-lotId1\" /> "+
		                                    "<input type=\"hidden\" name=\"value1\" id=\""+v.productCode+"-value1\" /> "+
		                                    "<td class=\"style1\"><span id=\""+v.productCode+"-location\"></span>"+"</td>"+
                                            //"<td class=\"style1\"><span id=\""+v.productCode+"-receivedDate\"></span>"+"</td>"+
                                            //"<td class=\"style1\"><span id=\""+v.productCode+"-expiryDate\"></span></td>"+
                                            //"<td class=\"style1\"><span id=\""+v.productCode+"-lotId\"></span>"+"</td>"+
                                            "<td class=\"style1\"><span id=\""+v.productCode+"-value\"></span>"+"</td>"+
                                           <%-- "<td class=\"style1\"><a productcode=\""+v.productCode+"\" productname=\""+v.productName+"\""+
		                                    "balance=\""+v.balance+"\" qty=\""+v.qty+"\" unitCode=\""+v.unitCode+"\" class=\"check\" href=\"javascript:void(0)\">Check</a></td>"+--%>
			    							+"</tr>").appendTo("#main tbody");
			    							
                                               });
                                           },
                                           url: 'sopickingjson.htm?param='+localRowData.so_number
                                       });
                                   } 
                           },
                           pager: '#pager3', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                       	jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
                            
                    });
            </script>
            
        </head>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
  		    // String msg = (String) m.get("msg");
  		    com.app.wms.engine.db.dto.Picking dto = (com.app.wms.engine.db.dto.Picking) m.get("dto");
  		    com.app.wms.engine.db.dto.PickingDetail dtod = (com.app.wms.engine.db.dto.PickingDetail) m.get("dtod");
  		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE, dd MM yyyy");
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    boolean isReadOnly = true;
  		    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
  		    java.util.List<PickingDetail> pd = (java.util.List<PickingDetail>)m.get("pd");
        %>
        

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
     		
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="PickingSpv.htm" method="post" id="pickingApproveForm">
                        <input type="hidden" name="action" value="UpdatePicking" />

                        <table class="collapse tblForm row-select">
                            <caption>Picking </caption>
                            <tbody class="tbl-nohover">

                               <tr class="detail_genap">
                                    <td width="2%"></td>
                                    <td width="10%">Picking No</td>
                                    <td width="30%" class="style1">
                                        <label>
                                            <input type="text" name="pickingNo" value="<%= dto.getPickingNo()%>" size="30" readonly/>
                                        </label>
                                    </td>
                                    
                                    <td>Corporate</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=dto.getPickingDetail().getCorp().getId() %>" size="30" readonly/>
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_ganjil">
                                  <td></td>
                                  <td>Picking Date</td>
                                  <td class="style1">
                                      <label>
                                          <input type="text" name="pickingDate" value="<%= dto.getPickingDate()%>" size="30" readonly/>
                                      </label>
                                  </td>
                                  
                                   <td>Warehouse</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="warehouse" value="<%=dto.getPickingDetail().getWh().getWhCode() %>" size="30" readonly/>
                                        </label>
                                    </td>
                               </tr>
                               <tr>
                               		<td width="2%" class="style1"></td>
                              		<td width="10%" class="style1">Sales Order No</td>
                                    <td class="style1">
                                        
                                            <input type="text" name="so_number" value="<%=dto.getSoNumber() %>" size="30" readonly/> 
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                	</td>
                                	
                                	<td>Tally Man</td>
                                     <td class="style1">
                                        <label>
                                            <input type="text" name="tallyman" value="<%=dto.getTallyman() %>" size="30" readonly/>
                                        </label>   
                                         <label class="requiredfield" title="This Field Is Required!">*</label>                                     
                                    </td>
                               </tr>
                              
			    			</tbody>
                            <tfoot>
                                <td colspan="7">
                                   
                                </td>
                            </tfoot>
                  </table>

						<table class="collapse tblForm row-select" id="main">
							<caption>Location Detail - List</caption>
								<thead> 
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Product Code</td>
	                                    <td class="style1">Product Name</td>
	                                    <td class="style1">Request Qty</td>
	                                    <td class="style1">SKU</td>
	                                    <td class="style1">Balance</td>
	                                    <td class="style1">Location</td>
	                                    <%-- <td class="style1">Received Date</td>
	                                    <td class="style1">Expiry Date</td>
	                                    <td class="style1">Lot Id</td>--%>
	                                    <td class="style1">Pick Value</td>
	                                   <%--  <td class="style1">Action</td>--%>
	                                    
								    </tr>
								</thead>

                       
								<tbody>
								<%int nomer = 1; 
									for(PickingDetail valueSearch : pd){
								%>
								<tr>
									<td class="center" width="1%">
                                        <%= nomer %>
                                    </td>
                                    <input type="hidden" value="<%= valueSearch.getProducts().getProductId() %>" name="productId" />
                                    <td class="style1"><%= valueSearch.getProducts().getProductCode() %></td>
									<input type="hidden" value="<%= valueSearch.getProducts().getProductCode() %>" name="productCode" />
									<td class="style1"><%= valueSearch.getProducts().getProductName() %></td>
									<input type="hidden" value="<%= valueSearch.getProducts().getProductName() %>" name="productName" />
									<td class="style1"><%= valueSearch.getQtyOrder() %></td>
									<input type="hidden" value="<%= valueSearch.getQtyOrder() %>" name="qty" />
									<td class="style1"><%= valueSearch.getUnitCode() %></td>
									<input type="hidden" value="<%= valueSearch.getUnitCode() %>" name="sku" />
									<td class="style1"><%= valueSearch.getBalance() %></td>
									<input type="hidden" value="<%= valueSearch.getBalance() %>" name="balance" />
									<td class="style1"><%= valueSearch.getWhlocation().getLocationCode() %></td>
									<input type="hidden" value="<%= valueSearch.getWhlocation().getLocationCode() %>" name="location" />
									<%-- 
									<td class="style1"><%= valueSearch.getReceivedDate() %></td>
									<input type="hidden" value="<%= valueSearch.getReceivedDate() %>" name="receivedDate" />
									<%if(valueSearch.getExpiredDate()!= null){%>
									<td class="style1"><%= valueSearch.getExpiredDate() %></td>
									<input type="hidden" value="<%= valueSearch.getExpiredDate() %>" name="expiryDate" />
									<%}else{ %>
									<td class="style1"></td>
									<%} %>
									<td class="style1"><%= valueSearch.getLotId() %></td>
									<input type="hidden" value="<%= valueSearch.getLotId() %>" name="lotId" />
									--%>
									<td class="style1">
										<span id="<%= nomer+"value" %>"> 
                                          		<%= valueSearch.getQtyPick() %> 
                                        </span>
									</td>
									<input type="hidden" id="<%= nomer+"value1" %>" value="<%= valueSearch.getQtyPick() %>" name="value" />
									<%-- <td class="style1"><a productcode="<%= valueSearch.getProducts().getProductCode()%>" nomer="<%= nomer %>" productname="<%= valueSearch.getProducts().getProductName()%>" balance="<%= valueSearch.getBalance()%>" qty="<%=valueSearch.getQtyOrder()%>" sku="<%=valueSearch.getUnitCode()%>" lotId="<%=valueSearch.getLotId()%>" class="check" href="javascript:void(0)">Check</a></td>--%>
									      		
								</tr>
								<%
									nomer++;
									}
								%>
								</tbody>
								
	                            <tfoot>
	                                <tr>
	                                    <td colspan="4">
		                                    <label>
                                        		<input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnApprovePicking" id="btnApprovePicking" value="Approve" />
                                   			</label>
		                                    <label>
		                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" />
		                                    </label>
	                                    </td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    <%-- 
	                                    <td></td>
	                                    <td></td>
	                                    <td></td>
	                                    --%>
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
                    location.href = 'PickingSpv.htm?action=findAll';
                });
                $("#btnApprovePicking").click(function () {
                    $("#dialog-confirm").dialog({ width: 300, height: 150, position: "center", modal: true, 
                        buttons: {
                            "Cancel": function() {                                       
                                    $( this ).dialog( "close" );                                        
                            },
                            "Save": function() {                               
                                $("form#pickingApproveForm").submit();
                            }
                        },
                        zindex: 1, title: 'Confirm' });
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
                
                $("#tallyman").click(function () {
                    $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Tally Man' });
         		});
                
                $("#so_number").click(function () {
                    $("#dialog1").dialog({ width: 800, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Sales Order No' });
            	});
                
             });
           
        </script>
        
         <div id="dialog2" title="Tallyman Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
         <div id="dialog1" title="Sales Order Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
           <div id="dialog" title="Location Search" style="display:none;z-index:1;">
             <form>
                 <table>
                     <tbody><tr>
                         <td width="20%"> <label for="name">Product Code</label></td>
                         <td><input type="text" name="productcode" id="productcode" readonly size="30"/> </td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>
                     <tr>
                         <td><label for="name">Product Name</label></td>
                         <td><input type="text" name="productname" id="productname" value="" readonly size="30"/></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>
                     <tr>
                         <td><label for="name">Balance </label></td>
                         <td> <input type="text" name="balance" id="balance" value="" readonly size="10"/></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>   
                     <tr>
                         <td><label for="name">Request Qty </label></td>
                         <td> <input type="text" name="qty" id="qty" value="" readonly size="10"/></td>
                          <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                         <td>&nbsp;</td>
                     </tr>                     
                 </tbody></table>  
                 <br />                 

                  <table class="collapse tblForm row-select" id="locationPicking">
                    <caption>Location Picking</caption>
                    <thead>
                            <tr>
                                <td> <label for="name">Location Code</label></td>
                                <td> <label for="name">Location Name</label></td>
                                <%-- 
                                <td> <label for="name">Received Date</label></td>
                                <td> <label for="name">Expiry Date</label></td> 
                                --%>
                                <td> <label for="name">Qty</label></td> 
                                <%-- <td> <label for="name">Lot Id</label></td>--%>                         
                                <td> <label for="name">Value</label></td>                         
                            </tr>
                    </thead>
                    <tbody>
                 </tbody>
              </table>  
                 
            </form>
        </div>
        
        <div id="dialog-confirm" title="Approve ?" style="display:none;z-index:1;">
            Approve Data?
        </div>
        
    </body>
</html>
