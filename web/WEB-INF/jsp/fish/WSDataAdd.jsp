<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New WS Data</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript">
        	$(document).ready(function() {
        		$('#batchNo').click(function() {
					$("#dialog-ajaxSearch").dialog({ 
						width: 500, 
						height: 350, 
						position: "center", 
						modal: true, 
						zindex: 9999, 
						title: 'Select Batch Number' });
                });
				
        		$('#wsNo').focus().on("blur",function() {
        			var wsNo = $('#wsNo').val();
        			$.ajax({
        				url:"FishJson.htm?action=checkWsNo&query="+wsNo,
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
        	});
        </script>
    </head>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="FishWs.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" id="vesselId" name="vesselId" value="" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip - WS</caption>
                            <tbody class="tbl-nohover">
                            	<tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS Type</td>
                                    <td class="style1">
                                        <label>
                                            <select name="wsTypeId">
                                            	<c:forEach items="${model.wsTypes}" var="wsType">
                                            		<option value="${wsType.id}">
                                            			<c:out value="${wsType.code}" />
                                            		</option>
                                            	</c:forEach>
                                            </select>
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td width="20%">Fish Storage</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <select name="storageId">
                                            	<option value="0">-- NONE --</option>
                                            	<c:forEach items="${model.fishStorages}" var="fishStorage">
                                            		<option value="${fishStorage.id}">
                                            			<c:out value="${fishStorage.code}" />
                                            		</option>
                                            	</c:forEach>
                                            </select>                                                            
                                        </label>
                                    </td>
                               	 </tr>
                            	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="wsNo" id="wsNo" size="30"                                                    
                                                   value="" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">Date Shift</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="dateShift" size="25" id="dateShift" readonly 
                                                   value="<%=dateFormat.format(new Date())%>" />                                                            
                                        </label>
                                    </td>
                                   
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Batch No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="batchNo" id="batchNo" size="30"                                                    
                                                   readonly="readonly"  value="" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td width="20%">Time Shift</td>
                                    <td class="style1">
                                        <label>    
                                        	<select name="timeShift">
                                        		<option value="07:00-15:00">07:00 - 15:00</option>
                                        		<option value="15:00-23:00">15:00 - 23:00</option>
                                        		<option value="23:00-07:00">23:00 - 07:00</option>
                                        	</select>                                                           
                                        </label>
                                    </td>
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="supplierName" id="supplierName" size="30"                                                    
                                                   readonly="readonly" value="" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Regu</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="regu" id="regu" size="30"                                                    
                                                   value="" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                            </tbody>
                        </table>
                        
                        <table class="collapse tblForm row-select">
                            <caption>WS Data</caption>
                            <tbody class="tbl-nohover">
                            	 <tr class="detail_genap">
                            	 	<td width="15px"></td>
                                    <td width="20%">Fish Type</td>
                                    <td class="style1">
                                        <label>
                                            <select name="fishId">
                                            	<c:forEach items="${model.fishes}" var="fish">
                                            		<option value="${fish.id}">
                                            			<c:out value="${fish.code}" />
                                            		</option>
                                            	</c:forEach>
                                            </select>
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                               	 </tr>
                               	 <tr>
                               	 <td width="15px"></td>
                               	 <td width="20%">Total Weight</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="totalWeight" size="10" id="fishTotalWeight" value="" 
                                            class="validate[required] text-input" /> Kg 
                                            <label class="requiredfield" title="This Field Is Required!">*</label>                                                           
                                        </label>
                                    </td> 
                               	 </tr>
                            </tbody>
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
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnBack" id="btnBack" value="Back" class="cancel" />
                                        </label>
                                    </td>
                                </tr></tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div id="dialog-ajaxSearch" title="Batch Number Search" style="display:none;z-index:1;">
	        	<label>Batch Number</label>
	        	<label>:</label>
	        	<input type="text" id="query" size="30" class="text-input"/>
	        	<input type="button" id="ajaxSearchBtn" style="font-size: smaller;" aria-disabled="false"                                                    
	                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
	                                                   name="btnBatchNumSearch" id="btnSave" value="Search" class="search" />
	        	<table id="list"></table> 
	            <div id="pager"></div> 
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
                    location.href = 'FishWs.htm';
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
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
        <div id="dialog-not-unique" title="incomplete" style="display:none;z-index:1;">
            "WS No." is not unique
        </div>      
    </body>
</html>