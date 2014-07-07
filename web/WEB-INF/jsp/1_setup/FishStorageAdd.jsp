<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="com.app.wms.engine.db.dto.FishStorage"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS - New Fish Storage</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {

                $('#code').bind('blur', function() {
                    var $o = $(this);
                    if (!$o.val())
                        return;
                    $.ajax({
                        url: "FishJson.htm?action=checkStorageCode&query=" + $o.val(),
                        dataType: 'json',
                        success: function(data) {
                            if (data.result) {
                                alert('The code is already exist!');
                                $o.val(null);
                                $o.focus();
                            }
                        }
                    });
                });
            });
        </script>
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
                    <form action="FishStorage.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="csId" value=""/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Storage - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td>Code</td>
                                    <td>
                                        <label>
                                            <input type="text" id="code" name="code" value="" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td>
                                        <label>
                                            <input type="text" name="description" value="" size="30" pattern="^\S+[A-Za-z0-9 ]{1,}" required="true"/>
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
                $('#btnCancel').click(function() {
                    location.href = 'FishStorage.htm';
                });
            });
        </script>

        <script language="JavaScript">
            function cek() {
                if (form.length.value == "" || form.width.value == "" || form.height.value == "") {
                    alert("data empty");
                    exit;
                }
            }
            function kali() {
                cek();
                a = eval(form.length.value);
                b = eval(form.width.value);
                c = eval(form.height.value);
                d = a * b * c
                form.volumeMatrix.value = d;
            }
        </script>

        <script type="text/javascript">
            function formfocus() {
                document.getElementById('autofocus').focus();
            }
            window.onload = formfocus;
        </script>

        <div id="dialog-not-unique" title="warning" style="display:none;z-index:1;">
            "Storage Code" is not unique
        </div>
    </body>
</html>