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
        <title>IMS - New Fish Transfer Slip</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
            	
            	$('#tsDate').datepicker({                        
                    dateFormat: "dd/mm/yy",
                });
            	
            	$('#wdsNo').click(function() {
					$("#dialog-ajaxSearch").dialog({ 
						width: 500, 
						height: 350, 
						position: "center", 
						modal: true, 
						zindex: 9999, 
						title: 'Select WDS Number' });
                });
            	
            	$('#btnAjaxSearch').click(function() {
					query = $('#query').val();
					var ajaxUrl = 'FishJson.htm?action=findWdsData&query='+query;
					$("#list").jqGrid('setGridParam',{url:ajaxUrl,page:1}).trigger("reloadGrid");
					$("#list").jqGrid({ 
						url:ajaxUrl, 
						datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
	                    colNames:['Vessel Id', 'WDS Id.','WDS No.','Batch No.','Requested By'], 
	                    colModel:[ 
								   {name:'vesselId',index:'vesselId',width:0, hidden:true},
								   {name:'wdsId',index:'wdsId',width:150, key:true},
	                               {name:'wdsNo',index:'wdsNo',width:150}, 
	                               {name:'batchNo',index:'batchNo',width:200},
	                               {name:'requestedBy',index:'requestedBy',width:200}], 
	                    sortname: 'wdsId',
	                    rowNum:10, rowList:[10,20,30],
	                    jsonReader:{repeatitems: false},
	                    onSelectRow: function(ids){
	                    	var localRowData = $(this).getRowData(ids);
	                    	var wdsId = localRowData.wdsId;
	                    	
	                    	$('#wdsId').val(wdsId);
	                    	$('#vesselId').val(localRowData.vesselId);
	                    	$('#wdsNo').val(localRowData.wdsNo);
	                    	$('#batchNo').val(localRowData.batchNo);
	                    	$('#dialog-ajaxSearch').dialog('close');
	                    	
	                    	$.ajax({
	                			url: 'FishJson.htm?action=findWdsDetailData&wdsId='+wdsId,
	                			dataType: 'json',
	                			success: function(data) {
	                				$.each(data.wdsDetails, function(k,v){
	                					var rowCount = $('#main tr').length;
	                					
	                					$("<tr class='ganjil'>" + 
	        	            					"<td class='style1'>"+rowCount+"</td>" +
	        	            					"<td id='fishType"+rowCount+"' class='style1'>"+v.fishCode+"</td>" +
	        	            						"<input id='fishId"+rowCount+"' type='hidden' name='fishId"+rowCount+"' value='"+v.fishId+"' />"+"</td>" +
	        	            					"<td class='style1'>"+v.description+"</td>" +
	        	            						"<input id='description"+rowCount+"' type='hidden' name='description"+rowCount+"' value='"+v.description+"' />"+"</td>" +
	        	            					"<td id='qtyHTML"+rowCount+"' class='center'>"+v.qty+"</td>" +
	        	            						"<input id='qty"+rowCount+"' type='hidden' name='qty"+rowCount+"' value='"+v.qty+"' /></td>" +
	        	            					"<td id='uomCodeHTML"+rowCount+"' class='center'>"+v.uomCode+"</td>" +
	        	            						"<input id='uomCode"+rowCount+"' type='hidden' name='uomCode"+rowCount+"' value='"+v.uomCode+"' /></td>" +
	        	            					"<td id='storageHTML"+rowCount+"' class='center'>"+v.storageName+"</td>" +
	        	            						"<input id='storageId"+rowCount+"' type='hidden' name='storageId"+rowCount+"' value='"+v.storageId+"' /></td>" +
	        	            					"</tr>").appendTo("#main tbody");
	                					
	                					$('#totalData').val(rowCount);
	                				});
	                			}
	                		});
	                    },
	                    pager: '#pager', sortname: 'wdsId', viewrecords: true, sortorder: "desc"}
					).trigger("reloadGrid"); 
	               	
					jQuery("#list").jqGrid('navGrid','#pager',{edit:false,add:false,del:false});
				});
            	
                $('#addForm').validationEngine('attach');
                
                $('#btnRequestSet').click(function() {
                	var id = $('#dId').val();
                	var requestedQty = $('#dRequestedQty').val();
                	$('#qtyHTML'+id).html(requestedQty);
                	$('#qty'+id).val(requestedQty);
                	$('#dialog-request').dialog('close');
                });
                
                $('#tsNo').focus().on("blur",function() {
        			var tsNo = $('#tsNo').val();
        			$.ajax({
        				url:"FishJson.htm?action=checkTsNo&query="+tsNo,
        				dataType: 'json',
        				success: function(data) {
        					if(data.result) {
        						$("#dialog-not-unique").dialog({
                                    open: function () {
                                        $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                        $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                                    },
                                    title: 'Warning',
                                    resizable: false,
                                    height: 120,
                                    modal: true,
                                    buttons: {
                                        "Ok" : function () {
                                            $(this).dialog("close");
                                        }
                                    }
                                });
        					}
        				}
        			});
        		});
            });
            
            function addRequestQuantity(selectedRow) {
            	var id = selectedRow.getAttribute('id');
            	var balance = $('#balance'+id).html();
            	$('#dBalanceQty').html(balance);
            	$('#dId').val(id);
            	
            	$("#dialog-request").dialog({ 
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
                    <form action="FishTs.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" id="totalData" name="totalData" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" id="vesselId" name="vesselId" value="0"/>
                        <table class="collapse tblForm row-select">
                            <caption>Transfer Slip - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td></td>
                                   <td class="style1">TS No.</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" id="tsNo" name="tsNo" value="" size="30" class="validate[numeric] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="tsDate" name="tsDate" value="<%=df.format(new Date())%>" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td></td>
                                	<td class="style1">WDS No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsNo" name="wdsNo" readonly="readonly" value="" size="30" 
                                            	class="validate[required] text-input"/>
                                            <input type="hidden" id="wdsId" name="wdsId" value="" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                    </td>
                                    <td></td>
                                    <td class="style1">Issued By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="issuedBy" value="" size="30" class="text-input"/>
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
                                	<td class="style1">Noted By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="notedBy" name="notedBy" value="" 
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
                        
                        <table class="collapse tblForm row-select" id="main">
                        <caption>Request</caption>
                    	<thead>
	                    	<tr>
	                    		<td class="style1">No.</td>
	                    		<td class="center">Fish Code</td>
	                    		<td class="center">Description</td>
	                    		<td class="center">Request Qty.</td>
	                    		<td class="center">UOM</td>
	                    		<td class="center">Storage</td>
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
                    location.href = 'FishTs.htm';
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
        	<label>WDS Number</label>
        	<label>:</label>
        	<input type="text" id="query" size="30" class="text-input"/>
        	<input type="button" id="btnAjaxSearch" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnBatchNumSearch" value="Search" class="search" />
        	<table id="list"></table> 
            <div id="pager"></div> 
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
        
        <div id="dialog-not-unique" title="incomplete" style="display:none;z-index:1;">
            "TS No." is not unique
        </div>          
                                        
    </body>
</html>