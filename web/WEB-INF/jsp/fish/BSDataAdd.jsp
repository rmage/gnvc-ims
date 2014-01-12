<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Transfer Slip (Bad Stock)</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
            	
            	$('#bsDate').datepicker({                        
                    dateFormat: "dd/mm/yy",
                });
            	
                $('#addForm').validationEngine('attach');
                
                $('#btnRequestSet').click(function() {
                	var id = $('#dId').val();
                	var requestedQty = $('#dBadStockQty').val();
                	$('#qtyHTML'+id).html(requestedQty);
                	$('#qty'+id).val(requestedQty);
                	$('#dialog-request').dialog('close');
                });
                
                $('#batchNo').click(function() {
					$("#dialog-ajaxSearch").dialog({ 
						width: 500, 
						height: 350, 
						position: "center", 
						modal: true, 
						zindex: 9999, 
						title: 'Select Batch Number' });
                });
				
				$('#ajaxSearchBtn').click(function() {
					var query = $('#query').val();
					var ajaxUrl = 'FishJson.htm?action=findBatchNumber&query='+query
					$("#list").jqGrid('setGridParam',{url:ajaxUrl,page:1}).trigger("reloadGrid");
					$("#list").jqGrid({ 
						url:ajaxUrl, 
						datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
	                    colNames:['Vessel Id', 'Batch Number','Vessel Name', 'Supplier Name'], 
	                    colModel:[ 
								   {name:'vesselId',index:'vesselId',width:50},
	                               {name:'batchNo',index:'batchNo',width:100}, 
	                               {name:'vesselName',index:'vesselName',width:200},
	                               {name:'supplierName',index:'supplierName',width:200}], 
	                    sortname: 'batchNo',
	                    rowNum:10, rowList:[10,20,30],
	                    jsonReader:{repeatitems: false},
	                    onSelectRow: function(ids){
	                    	var localRowData = $(this).getRowData(ids);
	                    	$('#batchNo').val(localRowData.batchNo);
	                    	$('#supplierName').val(localRowData.supplierName);
	                    	$('#vesselId').val(localRowData.vesselId);
	                    	$('#dialog-ajaxSearch').dialog('close');
	                    },
	                    pager: '#pager', sortname: 'batchNo', viewrecords: true, sortorder: "desc"}
					).trigger("reloadGrid"); 
	               	
					jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
				});
				
				$('#batchNo').click(function() {
					$("#dialog-ajaxSearch").dialog({ 
						width: 500, 
						height: 350, 
						position: "center", 
						modal: true, 
						zindex: 9999, 
						title: 'Select Batch Number' });
                });
				
				$('#ajaxSearchBtn').click(function() {
					var query = $('#query').val();
					var ajaxUrl = 'FishJson.htm?action=findBatchNumber&query='+query
					$("#list").jqGrid('setGridParam',{url:ajaxUrl,page:1}).trigger("reloadGrid");
					$("#list").jqGrid({ 
						url:ajaxUrl, 
						datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
	                    colNames:['Vessel Id', 'Batch Number','Vessel Name', 'Supplier Name'], 
	                    colModel:[ 
								   {name:'vesselId',index:'vesselId',width:50},
	                               {name:'batchNo',index:'batchNo',width:100}, 
	                               {name:'vesselName',index:'vesselName',width:200},
	                               {name:'supplierName',index:'supplierName',width:200}], 
	                    sortname: 'batchNo',
	                    rowNum:10, rowList:[10,20,30],
	                    jsonReader:{repeatitems: false},
	                    onSelectRow: function(ids){
	                    	var localRowData = $(this).getRowData(ids);
	                    	$('#batchNo').val(localRowData.batchNo);
	                    	$('#supplierName').val(localRowData.supplierName);
	                    	$('#vesselId').val(localRowData.vesselId);
	                    	$('#dialog-ajaxSearch').dialog('close');
	                    },
	                    pager: '#pager', sortname: 'batchNo', viewrecords: true, sortorder: "desc"}
					).trigger("reloadGrid"); 
	               	
					jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
				});
				
				$('#addItem').click(function() {
					$("#dialog-stock").dialog({ 
						width: 500, 
						height: 350, 
						position: "center", 
						modal: true, 
						zindex: 9999, 
						title: 'Stock List' 
					});
					
					var vesselId = $('#vesselId').val();
					var ajaxUrl = 'FishJson.htm?action=findFishStockByVesselId&vesselId='+vesselId;
					$("#list").jqGrid('setGridParam',{url:ajaxUrl,page:1}).trigger("reloadGrid");
                	$("#list2").jqGrid({ 
						url:ajaxUrl, 
						datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
	                    colNames:['Fish Code','Storage Name','Balance','Fish ID', 'Storage ID', 'Fish Desc'], 
	                    colModel:[ 
								   {name:'fishCode',index:'fishCode',width:150},
	                               {name:'storageName',index:'storageName',width:150}, 
	                               {name:'balance',index:'balance',width:200},
	                               {name:'fishId',index:'fishId',width:0, hidden:true},
	                               {name:'storageId',index:'storageId',width:0, hidden:true},
	                               {name:'fishDesc',index:'fishDesc',width:0, hidden:true}], 
	                    sortname: 'fishCode',
	                    rowNum:10, rowList:[10,20,30],
	                    jsonReader:{repeatitems: false},
	                    onSelectRow: function(ids){
	                    	var localRowData = $(this).getRowData(ids);
	                    	var rowCount = $('#main tr').length;
	                    	
	                    	$("<tr class='ganjil'>" + 
	            					"<td class='style1'>"+rowCount+"</td>" +
	            					"<td id='fishType"+rowCount+"' class='style1'>"+localRowData.fishCode+"</td>" +
	            						"<input id='fishId"+rowCount+"' type='hidden' name='fishId"+rowCount+"' value='"+localRowData.fishId+"' />"+"</td>" +
	            					"<td class='style1'>"+localRowData.fishDesc+"</td>" +
	            						"<input id='description"+rowCount+"' type='hidden' name='description"+rowCount+"' value='"+localRowData.fishDesc+"' />"+"</td>" +
	            					"<td id='balance"+rowCount+"' class='center'>"+localRowData.balance+"</td>" +
	            					"<td id='qtyHTML"+rowCount+"' class='center'>0</td>" +
	            						"<input id='qty"+rowCount+"' type='hidden' name='qty"+rowCount+"' value='0' /></td>" +
	            					"<td id='uomCodeHTML"+rowCount+"' class='center'>Kg</td>" +
	            						"<input id='uomCode"+rowCount+"' type='hidden' name='uomCode"+rowCount+"' value='Kg' /></td>" +
	            					"<td id='storageHTML"+rowCount+"' class='center'>"+localRowData.storageName+"</td>" +
	            						"<input id='storageId"+rowCount+"' type='hidden' name='storageId"+rowCount+"' value='"+localRowData.storageId+"' /></td>" +
	            					"<td id='"+rowCount+"' class='center' onClick='addBadStockQuantity(this)'>Add Quantity</td>" +
	            					"</tr>").appendTo("#main tbody");
	                    	
	                    	$('#totalData').val(rowCount);
	                    	$('#dialog-stock').dialog('close');
	                    },
	                    pager: '#pager2', sortname: 'fishCode', viewrecords: true, sortorder: "desc"}
					).trigger("reloadGrid"); 
	               	
					jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                });
				
				$('#btnBadStockSet').click(function() {
                	var id = $('#dId').val();
                	var requestedQty = $('#dBadStockQty').val();
                	$('#qtyHTML'+id).html(requestedQty);
                	$('#qty'+id).val(requestedQty);
                	$('#dialog-badStock').dialog('close');
                });
            });
            
            function addBadStockQuantity(selectedRow) {
            	var id = selectedRow.getAttribute('id');
            	var balance = $('#balance'+id).html();
            	$('#dBalanceQty').html(balance);
            	$('#dId').val(id);
            	
            	$("#dialog-badStock").dialog({ 
					width: 350, 
					height: 200, 
					position: "center", 
					modal: true, 
					zindex: 9999, 
					title: 'Request Qty' 
				});
            }
        </script>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishBs.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" id="totalData" name="totalData" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" id="vesselId" name="vesselId" value="0"/>
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip (Bad Stock) - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td></td>
                                   <td class="style1">TS No.</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" id="autofocus" name="bsNo" value="" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="bsDate" name="bsDate" value="<%=df.format(new Date())%>" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td></td>
                                	<td class="style1">Batch No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" value="" 
                                            readonly="readonly" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="approvedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td></td>
                                	<td class="style1">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="supplierName" name="supplierName" value="" 
                                            size="30" class="text-input"/>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Received By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="approvedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <a href="javascript:void(0)" id="addItem">Add Item</a><br /><br />
                        
                        <table class="collapse tblForm row-select" id="main">
                        <caption>Bad Stock</caption>
                    	<thead>
	                    	<tr>
	                    		<td class="style1">No.</td>
	                    		<td class="center">Fish Code</td>
	                    		<td class="center">Description</td>
	                    		<td class="center">Balance</td>
	                    		<td class="center">Bad Stock Qty.</td>
	                    		<td class="center">UOM</td>
	                    		<td class="center">Storage</td>
	                    		<td class="center">Action</td>
	                    	</tr>
                    	</thead>
	                    	<tbody></tbody>
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
                    location.href = 'FishBs.htm';
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
            
            function addBadStockItem(selectedRow) {
            	var id = selectedRow.getAttribute('id');
            	var balance = $('#balance'+id).html();
            	$('#dBalanceQty').html(balance);
            	$('#dId').val(id);
            	
            	$("#dialog-badStock").dialog({ 
					width: 350, 
					height: 200, 
					position: "center", 
					modal: true, 
					zindex: 9999, 
					title: 'Bad Stock Qty' 
				});
            }
        </script>

        <script language="JavaScript">
			function cek(){
			if(form.length.value == "" || form.width.value == ""|| form.height.value == ""){
			alert("data empty"); 
			exit;
			}
			}
			function kali() {
			cek();
			a=eval(form.length.value);
			b=eval(form.width.value);
			c=eval(form.height.value);
			d=a*b*c
			form.volumeMatrix.value = d;
			}
		</script>
		
		<script type="text/javascript">
		   function formfocus() {
		      document.getElementById('autofocus').focus();
		   }
		   window.onload = formfocus;
    	</script>
    	             
        <div id="dialog-ajaxSearch" title="Batch Number Search" style="display:none;z-index:1;">
        	<label>Batch No</label>
        	<label>:</label>
        	<input type="text" id="query" size="30" class="text-input"/>
        	<input type="button" id=ajaxSearchBtn style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnBatchNumSearch" value="Search" class="search" />
        	<table id="list"></table> 
            <div id="pager"></div> 
        </div>
        
        <div id="dialog-badStock" title="Request Quantity" style="display:none;z-index:1;">
        	<table class="collapse tblForm row-select">
        		<tr>
        			<td>Balance</td>
        			<td>:</td>
        			<td><label id="dBalanceQty"></label></td>
        		</tr>
        		<tr>
        			<td>Bad Stock</td>
        			<td>:</td>
        			<td><input type="text" size="30" id="dBadStockQty" value="0" /></td>
        		</tr>
        	</table>
        	<input type="hidden" id="dId" value="" />
        	<input type="button" id="btnBadStockSet" style="font-size: smaller;" aria-disabled="false"                                                    
					role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
					name="btnBatchNumSearch" value="Set" class="search" />
        </div>
        
         <div id="dialog-stock" title="Stock List" style="display:none;z-index:1;">
        	<table id="list2"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-spoilage-success" title="confirm" style="display:none;z-index:1;">
            Spoilage report has been saved
        </div>
        
        <div id="dialog-spoilage-failed" title="confirm" style="display:none;z-index:1;">
            Failed to save data
        </div>
        
        <div id="dialog-spoilage-confirm" title="confirm" style="display:none;z-index:1;">
            Save this spoilage report?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>         
                                        
    </body>
</html>