<%@page import="java.util.List"%>
<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - WS Data Detail</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String mode = (String) m.get("mode");
                    
			FishWs dto = (FishWs) m.get("dto");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip - WS</caption>
                            <tbody class="tbl-nohover">
                            	<tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS Type</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="wsType" id="wsType" size="30" readonly="readonly"                                                    
                                                   value="<%=dto.getWsType().getCode()%>" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                               	 </tr>
                            	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="wsNo" id="wsNo" size="30" readonly="readonly"                                                   
                                                   value="<%=dto.getWsNo()%>" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">Date Shift</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="dateShift" size="25" id="dateShift" readonly="readonly" 
                                                   value="<%= dateFormat.format(dto.getDateShift()) %>" />                                                            
                                        </label>
                                    </td>
                                   
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Batch No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="batchNo" id="batchNo" size="30" readonly="readonly"                                                   
                                                   value="<%= dto.getVessel().getBatchNo() %>" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td width="20%">Time Shift</td>
                                    <td class="style1">
                                        <label>    
                                        	<input type="text" name="timeShift" id="timeShift" size="30" readonly="readonly"                                                   
                                                   value="<%= dto.getTimeShift() %>" class="validate[required] text-input" />                                                         
                                        </label>
                                    </td>
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="supplierName" id="supplierName" size="30" readonly="readonly"                                                  
                                                   value="<%= dto.getVessel().getSupplier().getName() %>" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Group</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="regu" id="regu" size="30" readonly="readonly"                                                    
                                                   value="<%=dto.getRegu()%>" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                            </tbody>
                        </table>
                        
                        <form action="FishWs.htm" method="post" id="addForm">
                        	<input type="hidden" name="action" value="saveDetail" />
                        	<input type="hidden" name="wsId" value="<%=dto.getId()%>" />
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
                        </form>
                    <table class="collapse tblForm row-select">
                    	<thead>
	                    	<tr>
	                    		<td class="style1">No.</td>
	                    		<td class="center">Fish Type</td>
	                    		<td class="center">Total Weight</td>
	                    	</tr>
                    	</thead>
                    	<tbody>
	                    	<c:set var="counter" value="1" scope="page"/>
	                    	<c:forEach items="${model.wsDetails}" var="wsDetail">
	                    		<tr class="ganjil">
	                    			<td class="style1">${counter}</td>
	                    			<td class="center">${wsDetail.fish.code}</td>
	                    			<td class="center">${wsDetail.totalWeight}</td>
	                    		</tr>
	                    	<c:set var="counter" value="${counter + 1}" scope="page"/>
	                    	</c:forEach>
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
		                       </tr>
	                       </tfoot>
                     </table>
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
    </body>
</html>