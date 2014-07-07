<%@page import="com.app.wms.engine.db.dto.Currency"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - New Currency</title>
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
                    <form action="Currency.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Currency - Detail</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td class="style1">Currency Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="code" name="code" maxlength="10" size="12" required="true" pattern="^\S+[A-Za-z0-9 ]{1,}" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Currency Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="name" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Currency Symbol</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="symbol" maxlength="25" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true" />
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>		
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false"                                                    
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
                $('#code').bind('blur', function() {
                    var $o = $(this);
                    if (!$o.val())
                        return;

                    $.ajax({
                        url: 'Currency.htm?term=' + $o.val(),
                        method: 'post',
                        data: {
                            action: 'getUnique', term: $o.val()
                        },
                        dataType: 'json',
                        success: function(json) {
                            if (json.status) {
                                alert('The code is already exist!');
                                $o.val(null);
                                $o.focus();
                            }
                        }
                    });
                });
                
                $('#btnCancel').click(function() {
                    location.href = 'Currency.htm';
                    return false;
                });
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