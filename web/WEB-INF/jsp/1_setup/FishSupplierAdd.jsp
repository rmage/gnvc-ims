<%@page import="com.app.wms.engine.db.dto.FishSupplier"%>
<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New WS Data</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSupplier.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Supplier</caption>
                            <tbody class="tbl-nohover">
                            	<tr class="detail_genap">
                                    <td></td>
                                    <td>Supplier Code</td>
                                    <td>
                                        <label>
                                           <input type="text" name="code" id="code" size="30"                                                    
                                                   value="" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                            <label>*</label>
                                        </label>
                                    </td>
                                  	<td></td>
                                    <td>Telp.</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="telp" id="code" size="30"                                                    
                                                   value="" pattern="^\S+[0-9]" required="true" />
                                            <label>*</label>
                                            </select>                                                            
                                        </label>
                                    </td>
                               	 </tr>
                            	 <tr class="detail_genap">
                                    <td></td>
                                    <td>Supplier Name</td>
                                    <td>
                                        <label>
                                            <input type="text" name="name" id="name" size="30"                                                    
                                                   value="" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Fax</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="fax" size="25" id="fax" value="" pattern="^\S+[0-9]" />                                                          
                                        </label>
                                    </td>
                               	 </tr>
                               	 <tr class="detail_genap">
                                    <td></td>
                                    <td>Address</td>
                                    <td>
                                        <label>
                                            <textarea type="text" name="address" id="address" rows="5" cols="40"                                                   
                                                      value="" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true"/></textarea>
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td>Email</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="email" size="25" id="email" value="" pattern="(\w[-._\w]*\w@\w[-._\w]*\w\.\w{2,3})" />                                                          
                                        </label>
                                    </td>  
                               	 </tr>
                                 <tr class="detail_genap">
                                    <td></td>
                                    <td>Contact Person</td>
                                    <td>
                                        <label>
                                            <input type="text" name="cp" id="cp" size="30"                                                    
                                                   value="" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                            <label>*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                               	 </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false"                                                    
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
                    location.href = 'FishSupplier.htm';
                    return false;
                });

                $('#dateShift').datepicker({                        
                    dateFormat: "dd/mm/yy"                        
                });
            });
            
           $(function() {
                $('#code').bind('blur', function() {
                  var $o = $(this);
                   if (!$o.val()) return;
                   
                   $.ajax({
                       url: 'FishSupplier.htm?term='+$o.val(),
                       method: 'post',
                       data: {
                           action: 'getUnique', term: $o.val()
                       },
                       dataType: 'json',
                       success: function(json){
                           if(json.status) {
                               alert('Not Unique code found!');
                               $o.val(null);
                               $o.focus();
                           }
                       }
                });
            });
        }); 
        </script>
    </body>
</html>
