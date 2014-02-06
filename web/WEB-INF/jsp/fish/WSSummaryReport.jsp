<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishWSType"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Generate Report</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
                
                $('#addForm').validationEngine('attach');   
                
                $('#dateShift').datepicker({                        
                    dateFormat: "yy-mm-dd"
                });
                
                $('#btnGenerate').click(function() {
                	var type = $('#type').val();
                	var vesselId = $('#vesselId').val();
                    var dateShift = $('#dateShift').val();
                    var params = vesselId + ":" + dateShift;
                	var url = "GenerateReport.htm?action=index&type="+type+"&item=FSummaryWSSlip"+"&params="+params;
                	
                	location.href = url;
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
	                    	$('#vesselId').val(localRowData.vesselId);
                            $('#batchNo').val(localRowData.batchNo);
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
            SimpleDateFormat df = new SimpleDateFormat("yy-mm-dd");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
			
            <div id="content" style="display: none" class="span-24 last">
	
                <div class="box">
                    <form action="FishReport.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="vesselId" id="vesselId" value="0" />
                        <table class="collapse tblForm row-select">
                            <caption>Generate WSSummary Report</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                   <td class="style1">Batch No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" 
                                                   value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                   <td class="style1">Date Shift</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="dateShift" name="dateShift" 
                                                   value="<%=df.format(new Date())%>" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">Format</td>
                                    <td class="style1">
                                        <select id="type" name="type">
                                        	<option value="pdf">PDF</option>
                                        	<option value="xls">XLS</option>
                                            <option value="csv">CSV</option>
                                        </select>
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
                                            	name="btnGenerate" id="btnGenerate" value="Generate Report" class="save" />
                                        </label>
                                    </td>
                                </tr>
                           </tfoot>
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
    </body>
</html>