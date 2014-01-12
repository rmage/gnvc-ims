<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - New Sales Order</title>
            <%@include file="../metaheader.jsp" %>
            
            <script language="JavaScript">
                $(document).ready(function(){
                    
                    $("#list2").jqGrid({ url:'soproductjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Product Code','Product Name', 'Balance','SKU', 'Location Code'], 
                                colModel:[ {name:'productcode',index:'productcode'}, 
                                           {name:'productname',index:'productname'}, 
                                           {name:'balance',index:'balance'}, 
                                    	   {name:'sku',index:'sku'}, 
                                    	   {name:'locationCode',index:'locationCode'}
                                     ], 
                                sortname: 'productName',
                                rowNum:10, rowList:[10,20,30,40,50,60], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#productcode").val(localRowData.productcode);
                                            $("#productname").val(localRowData.productname);
                                            $("#balance").val(localRowData.balance);
                                            $("#sku").val(localRowData.sku); 
                                            $("#locationCode").val(localRowData.locationCode); 
                                            $("#dialog1").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                                jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                            
                            
                            $("#list3").jqGrid({ url:'soconsigneejson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Consignee Code','Consignee Name', 'Consignee Address','Consignee Phone'], 
                                colModel:[ {name:'consigneecode',index:'consigneecode'}, 
                                           {name:'consigneename',index:'consigneename'}, 
                                           {name:'consigneeaddress',index:'consigneeaddress'}, 
                                    	   {name:'consigneephone',index:'consigneephone'}
                                     ], 
                                sortname: 'consigneename',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#consigneecode").val(localRowData.consigneecode);
                                            $("#deliveryname").val(localRowData.consigneename);
                                            $("#deliveryaddress").val(localRowData.consigneeaddress);
                                            $("#deliveryphone").val(localRowData.consigneephone); 
                                            $("#dialog2").dialog('close');
                                        } 
                                },
                                pager: '#pager3', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false});
                            
                            
                            $("#list4").jqGrid({ url:'sobundlejson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Bundle Code','Bundle Name'], 
                                colModel:[ {name:'bundlecode',index:'bundlecode'}, 
                                           {name:'bundlename',index:'bundlename'}, 
                                     ], 
                                sortname: 'bundlename',
                                rowNum:10, rowList:[10,20,30], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#bundlecode").val(localRowData.bundlecode);
                                            $("#bundlename").val(localRowData.bundlename);
                                            $("#dialog3").dialog('close');
                                        } 
                                },
                                pager: '#pager4', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list4").jqGrid('navGrid','#pager4',{edit:false,add:false,del:false});
                }); 
    		</script>  
    		
    		<script language="javascript">
                var req;
                
                function ajaxFunction(){
                    var url = "uploadServlet";
                    
                    if (window.XMLHttpRequest){ 
                        req = new XMLHttpRequest();
                        req.onreadystatechange = processStateChange;
                        
                        try{
                            req.open("GET", url, true);
                        } catch (e) {
                            alert(e);
                        }
                        req.send(null);
                    } else if (window.ActiveXObject) { 
                        req = new ActiveXObject("Microsoft.XMLHTTP");
                        
                        if (req) {
                            req.onreadystatechange = processStateChange;
                            req.open("GET", url, true);
                            req.send();
                        }
                    }
                }
                
                function processStateChange(){
                    if (req.readyState == 4){
                        if (req.status == 200){
                            var xml = req.responseXML;
                            var isNotFinished = xml.getElementsByTagName
                            ("finished")[0];
                            var myBytesRead = xml.getElementsByTagName
                            ("bytes_read")[0];
                            var myContentLength = xml.getElementsByTagName
                            ("content_length")[0];
                            var myPercent = xml.getElementsByTagName
                            ("percent_complete")[0];
                            var hash = xml.getElementsByTagName("hash")[0];
                            var hashDetail = "";
                            if ((isNotFinished == null) && (myPercent == null)){
                                document.getElementById("initializing").style.
                                    visibility = "visible";
                                window.setTimeout("ajaxFunction();", 100);
                            } else {
                                document.getElementById("initializing").style.
                                    visibility = "hidden";
                                document.getElementById("progressBarTable").style.
                                    visibility = "visible";
                                document.getElementById("percentCompleteTable").style.
                                    visibility = "visible";
                                document.getElementById("bytesRead").style.
                                    visibility = "visible";
                                
                                myBytesRead = myBytesRead.firstChild.data;
                                myContentLength = myContentLength.firstChild.data;
                                
                                if (myPercent != null) {
                                    myPercent = myPercent.firstChild.data;
                                    
                                    document.getElementById("progressBar").style.width 
                                        = myPercent + "%";
                                    document.getElementById("bytesRead").innerHTML 
                                        = myBytesRead + " of " + 
                                        myContentLength + " bytes read";
                                    document.getElementById("percentComplete").innerHTML 
                                        = myPercent + "100%";
                                    window.setTimeout("ajaxFunction();", 100);
                                } else { // finished
                                    document.getElementById("bytesRead").style.visibility 
                                        = "hidden";
                                    document.getElementById("progressBar").style.width 
                                        = "100%";
                                    document.getElementById("percentComplete").innerHTML 
                                        = "File Uploading Done!";
                                    document.getElementById("txtFile").value="";
                                    
                                    hashDetail = hash.firstChild.data;
                                                    
                                    // clear table
                                    $("#main tbody").html('');
                                                    
                                    // ajax call
                                    $.ajax({
                                        dataType: 'json',
                                        success: function(data) {
                                             $.each(data.pocsv, function(k,v){                                                         
                                                var rowCount = $('#main tr').length-1;
                                                
                                                $("<tr class=\"myhover\"><td class=\"style1\">"+rowCount+"</td><td class=\"style1\">"+v.productName+" </td><td class=\"style1\">"
                                                    +v.qty+"</td><td class=\"style1\">"+
                                                    
                                                    "<select name=\"producttype\" id=\"tipebarang\" >"+
                                                        "<option value=\"General\">General</option>"+
                                                        "<option value=\"Promo\">Promo</option>"+
                                                        "<option value=\"Tester\">Tester</option>"+                                            
                                                    "</select>" 
                                                    
                                                    +"</td> "+
                                                    "<input type=\"hidden\" name=\"productcode1\" value=\""+v.productCode+"\" />"+
//                                                    "<input type=\"hidden\" name=\"producttype\" value=\""+$("#tipebarang").val()+"\" />"+
                                                    "<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\" /> </tr>").appendTo("#main tbody")                                                             
                                            });
                                        },
                                        url: 'poCSVJson.htm?param='+hashDetail
                                    });
                                    
                                    // close
                                    $("#dialog-upload").dialog('close');
                                }
                            }
                        } else {
                            alert(req.statusText);
                        }
                    }
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
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    boolean isReadOnly = true;
  		    String error = (String)m.get("msg");
  		    
  		  	String DateFormat = com.app.wms.web.util.AppConstant.DATE_FORMAT;
          	pageContext.setAttribute("DateFormat", DateFormat);

          	com.app.web.engine.search.SalesOrderSearch criteria = new com.app.web.engine.search.SalesOrderSearch(); 
          	if (request.getSession().getAttribute("SalesOrderSearch") != null) {
              criteria = (com.app.web.engine.search.SalesOrderSearch) request.getSession().getAttribute("SalesOrderSearch");
          	}
  		   
        %>


        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="SalesOrder.htm" method="post" id="salesOrderAddForm">
                        <input type="hidden" name="action" value="saveSalesOrder" />
                        <table class="collapse tblForm row-select">
                            <caption>Sales Order</caption>
                            <tbody class="tbl-nohover">

                               <tr class="detail_genap">
                                    <td width="1%"></td>
                                    <td width="19%">Sales Order No</td>
                                    
                                    <td class="style1">
                                        <label>
                                  		    <input type="text" id="salesOrderNo" name="salesOrderNo" value="<%=m.get("salesOrderNo")!=null? m.get("salesOrderNo"): ""%>" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    
                                    <td>Corporate Id</td>
                                   
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=m.get("corporate")%>" size="30" readonly/>
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_ganjil">
	                                  <td></td>
	                                  <td>Sales Order Date</td>
	                                 
	                                  <td class="style1">
	                                      <label>
	                                          <input type="text" name="salesOrderDate" value="<%=m.get("date")%>" size="30" readonly/>
	                                      </label>
	                                  </td>
	                                  
	                                  <td width="15%">Warehouse Id</td>
	                                    
	                                  <td width="34%" class="style1">
	                                      <label>
	                                          <input type="text" name="warehouse" value="<%=m.get("warehouse")%>" size="30" readonly/>
	                                      </label>
	                                  </td>
                               </tr>
                               
                               
                               <tr class="detail_genap">
                                    <td></td>
                                    <td>Delivery</td>
                                    <td class="style1">                                                                
		                                <input type="radio" name="devname" class="devname" value="DEVNAME" checked="">Delivery Name&nbsp; &nbsp;</input>
		                                <input type="radio" name="devname" class="devname" value="CONSNAME" >Consignee Name</input>                                                              
				                    </td>
				                    <td>
                                        Delivery Date
                                    </td>
                                    <td class="style1">
                                        <input size="30" type="text" id="deliverydate" name="deliverydate" value="<%=m.get("deliveryDate")!=null? m.get("deliveryDate"): ""%>" class="validate[required] text-input"/>
                                   		<label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                               </tr>
                               
                               <tr class="hideable">
                                    <td></td>
                                    <td class="hideable">Delivery Name</td>
                                    <td class="hideable"><input type="text" id="deliveryname1" name="deliveryname1" value="<%=m.get("deliveryName")!=null? m.get("deliveryName"): ""%>" size="52" maxlength="25"/>&nbsp;&nbsp;<label class="requiredfield" title="This Field Is Required!">*</label></td>
                                   <td></td><td></td>
                               </tr>
                               
                                <tr class="hideable1">
                                    <td></td>
                                    <td class="hideable1">Consignee Name</td>
                                    <td class="hideable1">
                                        <label>
                                            <input type="text" id="deliveryname" name="deliveryname" value="<%=m.get("deliveryName")!=null? m.get("deliveryName"): ""%>" size="52" maxlength="25" class="validate[required] text-input"/>
                                       		<img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td><td></td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td></td>
                                    <td class="style1">Delivery Address</td>
                                    <td class="style1">
                                        <label>
                                            <%-- <textarea style="width: 324px; height: 41px;" id="deliveryaddress" name="deliveryaddress"><%=m.get("deliveryAddress")!=null? m.get("deliveryAddress"): ""%></textarea>--%>
                                        	 <textarea style="width: 324px; height: 41px;" id="deliveryaddress" name="deliveryaddress"><%=m.get("deliveryAddress")!=null? m.get("deliveryAddress"): ""%></textarea>
                                        </label>
                               		</td>
                               		  <td class="style1">Remarks Kitting</td>
                                    <td class="style1">
                                        <label>
                                            <textarea style="width: 324px; height: 41px;" id="remarks" name="remarks"><%=m.get("remarks")!=null? m.get("remarks"): ""%></textarea>
                                        </label>
                               		</td>
                               </tr>
                               
                               
			    		</tbody>
                            <tfoot>
                                <td colspan="7">
                                  
                                </td>
                            </tfoot>
                  </table>

                        <div id="tabs">
                            <ul>
                            	<li><a href="#product">Product</a></li>
                            	<li><a href="#bundle">Bundle</a></li>
                                <li><a href="#upload">Upload</a></li>
                            </ul>
                           
                           <div id="product">
                           		<tr class="detail_genap">
                                    <td width="30%">Product Code&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1" width="30%">
                                        
                                       <input type="text" name="productcode" value="" size="30"  id="productcode" /> 
                                       <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                   
                                    </td>
                                    
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>	
                                    <td width="30%">Product Name</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="30%" class="style1"><input type="text" class="disable" readonly name="productname" id="productname" value="" size="30"/></td>
                                    
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>	
                               		<td width="20%" class="style1">SKU &nbsp;</td>
                                    <td width="20%" class="style1"><input type="text" class="disable" readonly name="sku" id="sku" value="" size="10"/></td>
                               		
                                </tr>
                           		<tr class="detail_genap">
                           			<td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="18%" class="style1">Request Qty &nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="18%" class="style1"><input type="text" class="disable" name="qtyProduct" id="qtyProduct" value="0" size="10" maxlength="5"/></td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>	
                                    <td><input type="submit" class="style1" name="btnAddDtlProduct" id="btnAddDtlProduct" value="Add" /></td>
                               </tr>
                           </div>
							
						  <div id="bundle">
						  		<tr class="detail_genap">
                                    <td width="30%">Bundle Code&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1" width="30%">
                                        
                                       <input type="text" name="bundlecode" value="" size="30"  id="bundlecode" /> 
                                       <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                   
                                    </td>
                                    
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>	
                                    <td width="30%">Bundle Name</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="30%" class="style1"><input type="text" class="disable" readonly name="bundlename" id="bundlename" value="" size="30"/></td>
                                    
                                </tr>
                           		<tr class="detail_genap">
                           			<td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="18%" class="style1">Request Qty &nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td width="18%" class="style1"><input type="text" class="disable" name="qtyBundle" id="qtyBundle" value="0" size="10" maxlength="5"/></td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>
                                    <td class="style1">&nbsp;</td>	
                                    <td><input type="submit" class="style1" name="btnAddDtlBundle" id="btnAddDtlBundle" value="Add" /></td>
                               </tr>
						  
						  </div>
							
                           <div id="upload">
                              
                                <a href="javascript:void(0)" id="uploadlink"> Upload </a>
                               
                           </div>

                        </div>

						<br></br>
					
						<table class="collapse tblForm row-select">
							<caption>Product Request - List</caption>
								<thead>
								    <tr>
										<td width="5%" class="style1">No.</td>
	                                    <td width="25%" class="style1">Product Request</td>
	                                    <td width="15%" class="style1">SKU</td>
	                                    <td width="45%" class="style1">Request Qty</td>
	                                   
								    </tr>
								</thead>

                       
								<tbody id="main">
									  <c:if test="${requestScope.model.tableMap!=null}">
						                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
										<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
										   
										    <tr class="ganjil">
												<td width="5%" align="right">&nbsp;${prodCounter.count}</td>
												<td width="25%" align="center">&nbsp;
									      			<c:if test="${prod.value.productRequest ne null}">
									      				${prod.value.productRequest}
									      			</c:if>
									      		</td>
									      		<td width="15%" align="center">&nbsp;
									      			<c:if test="${prod.value.unitCode ne null}">
									      				${prod.value.unitCode}
									      			</c:if>
									      		</td>
									      		<td width="45%" align="center">&nbsp;
									      			<c:if test="${prod.value.requestQuantity ne null}">
									      				${prod.value.requestQuantity}
									      			</c:if>
									      		</td>
										    </tr>
						
										</c:forEach>
									  </c:if>
								</tbody>
								
	                             <tfoot>
                                <td colspan="7">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSaveSalesOrder" id="btnSaveSalesOrder" value="Save" />
                                   	</label>
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" />
                                    </label>
                                </td>
                            	</tfoot>
                        </table>
                        
                        
	                         <% 
	                       	 if (error != null) {
							 %>
								<div id="dialog" title="Info" style="display:none">
								    <ul>
								        <%
								        	String[] listErrorMsg = error.split(com.app.wms.web.util.AppConstant.EOL);
		                                    
								        	for (int i = 0; i < listErrorMsg.length; i++) {
		                                    String msg = listErrorMsg[i];
								        %>
								        <li><%=msg%></li>
								        <%
								            }
								        %>
								    </ul>
								    <script type="text/javascript">
								        $(document).ready(function() {
								            $("#dialog").dialog();
								        });
								    </script>
								</div>
							<%
							}
							%>
                        
                        
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
	                    location.href = 'SalesOrder.htm?action=findAll';
	            });
                $('#btnAddDtlProduct').click(function() {
                	var salesOrderNo1 = $("#salesOrderNo").val();
                	var deliveryname1 = $("#deliveryname").val();
                	var deliverydate1 = $("#deliverydate").val();
                	var deliveryaddress1 = $("#deliveryaddress").val();
                	console.log(salesOrderNo1);
                	console.log(deliveryname1);
                	console.log(deliverydate1);
                	console.log(deliveryaddress1);
                	
                });
                $('#btnAddDtlBundle').click(function() {
                	var bundlecode = $("#bundlecode").val();
                	var bundlename = $("#bundlename").val();
                	
                	console.log(bundlecode);
                	console.log(bundlename);
                	
                });
                $("#btnSaveSalesOrder").click(function () {
                	
                	if(!$("#salesOrderAddForm").validationEngine('validate')){
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
                                $("form#salesOrderAddForm").submit();
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
                
                $("#productcode").click(function () {
                    $("#dialog1").dialog({ width: 825, height: 300, position: "center", modal: true, zindex: 1, title: 'Select Product' });
            	});
                
                $("#bundlecode").click(function () {
                    $("#dialog3").dialog({ width: 335, height: 255, position: "center", modal: true, zindex: 1, title: 'Select Bundle' });
            	});
                
                $("#deliveryname").click(function () {
                    $("#dialog2").dialog({ width: 665, height: 255, position: "center", modal: true, zindex: 1, title: 'Select Consignee' });
            	});
                
                $("#uploadlink").click(function () {
                    $("#dialog-upload").dialog({ width: 350, height: 150, position: "center", modal: true, zindex: 1, title: 'Upload' });
            	});
                
                //$('#deliverydate').datetimepicker();
                $("#deliverydate").datetimepicker({dateFormat: "dd.mm.yy", onSelect: function() 
                    { 
                    console.log($("#deliverydate").datetimepicker("getDate"));
                }});
                
                });
        </script>
        <script>
            $(".devname").click(function(){
            	
	           	if($(this).val()==="DEVNAME") {
	                     $(".hideable").show();
	                     $(".hideable1").hide();
	             }
            	            	
	           	else if($(this).val()==="CONSNAME") {
                    $(".hideable").hide();
                    $(".hideable1").show();
                }
                
            });
            window.onload = $(".devname").click();
           
        </script>
        <div id="dialog1" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog2" title="Consignee Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
        <div id="dialog3" title="Bundle Search" style="display:none;z-index:1;">
            <table id="list4" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager4"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
        <div id="dialog-upload" title="Product Search" style="display:none;z-index:1;">
            <iframe id="uploadFrameID" name="uploadFrame" height="0" width="0" frameborder="0" scrolling="yes"></iframe>  
            <form id="myForm" enctype="multipart/form-data" method="post" 
                  target="uploadFrame" 
                  action="uploadServlet" onsubmit="ajaxFunction()">
                <input type="hidden" name="hash" id="hash" value="<%= m.get("hash")  %>" />
                <input type="file" name="txtFile" id="txtFile" /><br />
                <input type="submit" id="submitID" name="submit" value="Upload" />
            </form>

            <div id="initializing" style="visibility: hidden; position:    absolute; top: 100px;">
                <table width="100%" style="border: 1px; background-color: black;">
                    <tr>
                        <td>
                            <table width="100%" style="border: 1px; background-color:    black; color: white;">
                                <tr>
                                    <td align="center">
                                        <b>Initializing Upload...</b>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="progressBarTable" style="visibility: hidden; position:   absolute; top: 100px;">
                <table width="100%" style="border: 1px; color: white;">
                    <tr>
                        <td>
                            <table id="progressBar" width="100%" >
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <table width="100%" style="background-color: white; color: black;">
                    <tr>
                        <td align="center" nowrap="nowrap">
                            <span id="bytesRead" style="font-weight: bold;">&nbsp;</span>
                        </td>
                    </tr>
                </table>  
            </div>

            <div id="percentCompleteTable" align="center"
                 style="visibility: hidden; position: absolute; top: 100px;">
                <table width="100%" style="border: 1px;">
                    <tr>
                        <td>
                            <table width="100%" style="border: 1px;">
                                <tr>
                                    <td align="center"  width="100%">
                                        <span id="percentComplete" style="color: blue; font-weight: bold; width:100%">&nbsp;</span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
            </div>
    </body>
</html>
