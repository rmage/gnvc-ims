<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create &therefore; Fish &therefore; IMS</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {

                $('#addForm').validationEngine('attach');

                $('#code').focus().on("blur", function() {
                    var code = $('#code').val();
                    $.ajax({
                        url: "FishJson.htm?action=checkFishCode&query=" + code,
                        dataType: 'json',
                        success: function(data) {
                            if (data.result) {
                                $("#dialog-not-unique").dialog({
                                    open: function() {
                                        $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                        $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                                    },
                                    title: 'Warning',
                                    resizable: false,
                                    height: 120,
                                    modal: true,
                                    buttons: {
                                        "Ok": function() {
                                            $(this).dialog("close");
                                            $('#code').focus();
                                        }
                                    }
                                });
                            }
                        }
                    });
                });

            });
        </script>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Fish.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Type - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td class="style1">Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="code" name="code" value="" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Species</td>
                                    <td class="style1">
                                        <label>
                                            <select name="fishTypeId">
                                                <c:forEach items="${model.fishTypes}" var="fishType">
                                                    <option value="${fishType.id}">
                                                        <c:out value="${fishType.code} - ${fishType.description}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Weight Category</td>
                                    <td class="style1">
                                        <label>
                                            <select name="fishWeightTypeId">
                                                <c:forEach items="${model.weightTypes}" var="weightType">
                                                    <option value="${weightType.id}">
                                                        <c:out value="${weightType.code}" />
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
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
                    location.href = 'Fish.htm';
                });
            });

            $("#btnSave").click(function() {

                //if invalid do nothing
                if (!$("#addForm").validationEngine('validate')) {
                    $("#dialog-incomplete").dialog({
                        open: function() {
                            $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                            $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                        },
                        title: 'Incomplete Form',
                        resizable: false,
                        height: 120,
                        modal: true,
                        buttons: {
                            "Ok": function() {
                                $(this).dialog("close");
                            }
                        }
                    });
                    return false;
                }

                $("#dialog-confirm").dialog({width: 300, height: 150, position: "center", modal: true,
                    buttons: {
                        "Cancel": function() {
                            $(this).dialog("close");
                        },
                        "Save": function() {
                            $('#btnSave').attr('disabled', 'disabled');
                            $("form#addForm").submit();
                        }
                    },
                    zindex: 1, title: 'Confirm'});

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

        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>

        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>

        <div id="dialog-not-unique" title="warning" style="display:none;z-index:1;">
            "Fish Code" is not unique
        </div> 

    </body>
</html>