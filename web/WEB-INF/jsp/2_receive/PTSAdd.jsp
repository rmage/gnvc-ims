<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.Supplier"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New PTS</title>
        <%@include file="../metaheader.jsp" %>
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
                    <form action="Pts.htm" method="post">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                       
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Transfer Slip - PTS</caption>
                            <tbody class="tbl-nohover">
                            	<tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">PTS Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="ptsCode" id="ptsCode" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td>PTS Date</td>
                                    <td class="style1">
                                       <label>
                                            <input type="text" id="ptsdate" name="ptsdate" 
                                                   
                                                   value="<%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>"
                                                   readonly
                                                   size="30"/>
                                            
                                        </label>
                                    </td>
                               	 </tr>
                               	 
                               	 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Pack Style/Size</td>
                                    <td class="style1">
                                        <label>
	                                        <input type="text" id="packStyleSize" name="packStyleSize" value="" size="30" />
                                    	</label>
                                    </td>
                                    <td>For Brand</td>
                                    <td class="style1">
                                    	<label>
	                                        <input type="text" id="forBrand" name="forBrand" value="" size="30" />
                                    	</label>
                                    </td>
                                </tr>
                                 
                              	<tr class="detail_genap">
                                   <td>&nbsp;</td>
                                   <td width="20%">Can Code</td>
                                   <td class="style1">
                                       <label>
                                           <input type="text" name="canCode" id="canCode" value="" size="30"                                                    
                                                  class="validate[required] text-input" />
                                       </label>
                                   </td>
                                 	<td>Reff</td>
                                   <td class="style1">
                                       <label>
                                           <input type="text" name="reff" id="reff" value="" size="30"                                                    
                                                  class="validate[required] text-input" />
                                       </label>
                                   </td>
                              	</tr>
                       
                       			<tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%"><b>PRODUCT</b></td>
                                    <td class="style1">
                                    </td>
                                    <td></td>
                                    <td class="style1">
                                    </td>
                               	</tr>
                       
                            	<tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">NS / DS</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="nsds" id="nsds" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                    <td>Prod. Batch</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="prodBatch" id="prodBatch" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Basket</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="basket" id="basket" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                  	<td>Quantity</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="canCode" id="canCode" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                               	 </tr>
                               	 
                               	 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%"><b>CUT OUT EVALUATION</b></td>
                                    <td class="style1">
                                    </td>
                                    <td></td>
                                    <td class="style1">
                                    </td>
                               	</tr>
                               	 
                            	 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">% Flk</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="percentFlk" id="percentFlk" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                  <td>NW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="nw" id="nw" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">DW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="dw" id="dw" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                  	<td>PW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="pw" id="pw" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                               	 </tr>
                           
                           		 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%"><b>QA DISPOSITION</b></td>
                                    <td class="style1">
                                    </td>
                                    <td></td>
                                    <td class="style1">
                                    </td>
                               	</tr>
                           
                            	 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Release to</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="ptsCode" id="ptsCode" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                    <td>Remarks</td>
                                    <td class="style1">
                                         <label>
                                        	<textarea style="width: 374px; height: 71px;" id="remarks" name="remarks"></textarea>
                                        </label>
                                    </td>
                               	 </tr>
                           
                           		 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Issued By / CASE UP</td>
                                    <td class="style1">
                                        <label>
	                                        <input type="text" id="accounting" name="accounting" value="" size="30" />
                                    	</label>
                                    </td>
                                    <td>CS</td>
                                    <td class="style1">
                                    	<label>
	                                        <input type="text" id="cs" name="cs" value="" size="30" />
                                    	</label>
                                    </td>
                               	</tr>
                           
                                 <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Pallet Checker / FGW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="checker" id="checker" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                    </td>
                                    <td>Received By / IM Supervisor</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="receivedBy" id="receivedBy" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
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
                                                   name="btnSavePTS" id="btnSavePTS" value="Save" class="simpan" />
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
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'Pts.htm';
                    return false;
                });

                $('#ptsDate').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
            });
        </script>
    </body>
</html>