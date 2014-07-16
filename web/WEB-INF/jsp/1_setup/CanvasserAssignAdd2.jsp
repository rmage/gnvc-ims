<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Canvassing Assignment</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            
            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="CanvassingAssignment.htm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Assignment</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 200px;">PRS No</td>
                                    <td>
                                        <select id="prsNo" name="prsNo" required="true">
                                            <option value="">-- Pilih PRS --</option>
                                            <c:forEach var="droplist" items="${requestScope.model.dropListPrs}">
                                                <option value="${droplist.prsnumber}">
                                                    ${droplist.departmentName} :: <fmt:formatDate pattern="yyyy-MM-dd" value="${droplist.prsdate}" /> :: ${droplist.prsnumber}
                                                </option> 
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('CanvassingAssignment.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Canvasser</caption>
                            <thead>
                                <tr>
                                    <td>PRS Number</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Quantity</td>
                                    <td>Canvasser</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="canvasser">
                                
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            
            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            /* jQuery | Binding event */            
            $('#prsNo').bind('change', function() {
                if($(this).val() !== '') {
                    $(this).attr('disabled', true);
                    $(this).parent().append('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    
                    $.ajax({
                        url: 'CanvassingAssignment.htm',
                        data: {action: 'getPrs', key: $(this).val()},
                        dataType: 'json',
                        success: function(json) {
                            $('#canvasser').html('');
                            for(var i = 0; i < json.length; i++) {
                                $('#canvasser').append('<tr><td>' + json[i].prsNo + '</td><td>' + json[i].itemCode 
                                    + '</td><td>' + json[i].itemName + '</td><td>' + json[i].quantity 
                                    + '</td><td>' + json[i].canvasser + '</td></tr>');
                            }
                        },
                        complete: function() {
                            $('#prsNo').attr('disabled', false);
                            $('#load').remove();
                        }
                    });
                } else {
                    $('#canvasser').html(null);
                }
            });
        </script>
    </body>
</html>
