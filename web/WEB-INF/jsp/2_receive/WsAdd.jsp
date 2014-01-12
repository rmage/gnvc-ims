<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Ws</title>
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
                    <form action="Ws.htm" method="post">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip - WS</caption>
                            <tbody class="tbl-nohover">
                            	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="wsCode" id="wsCode" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">WS Date</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="wsDate" size="25" id="wsDate" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                    </td>
                                   
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="supplierName" id="supplierName" value="" size="55"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Species</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="species" id="species" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Weight</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="weight" id="weight" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>                                     
                                    </td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Quantity</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="canCode" id="canCode" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td></td>
                                    <td></td>                                     
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
                                                   name="btnWs" id="btnWs" value="Save" class="simpan" />
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
                    location.href = 'Ws.htm';
                    return false;
                });

                $('#wsDate').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
            });
        </script>
    </body>
</html>