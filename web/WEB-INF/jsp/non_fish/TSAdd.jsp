<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Transfer Slip</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                    <form action="TransferSlip.htm" id="tsForm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>TS Number</td>
                                    <td><input name="tsCode" pattern="[0-9]{1,}" type="text" required="true" /></td>
                                    <td>TS Date</td>
                                    <td><input id="tsDate" name="tsDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><textarea name="tsInfo"></textarea></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('TransferSlip.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select SWS</td>
                                    <td colspan="6">
                                        <select id="swsCode" name="swsCode" required="true">
                                            <option value="">-- Please select SWS Number --</option>
                                            <c:forEach items="${model.s}" var="x">
                                                <option value="${x.swsCode}">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${x.swsDate}" /> :: 
                                                    ${x.departmentCode} :: ${x.swsCode}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Item Name</td>
                                    <td>Item Code</td>
                                    <td>Type</td>
                                    <td>Quantity</td>
                                    <td>Uom</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
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
            /* BIND | element event */
            $('#tsDate').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            $('#swsCode').bind('change', function() {
                if($('#swsCode').val() === '') {
                    $('#main').html(null);
                    return;
                }
                
                $(this).attr('disabled', true);
                $(this).after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                
                $.ajax({
                    url: 'TransferSlip.htm',
                    data: {action: 'getSwsDetail', key: $(this).val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for(var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + (i + 1) + '</td><td>' + json[i].itemName + '</td><td>' + json[i].itemCode + 
                                '</td><td>' + json[i].type + '</td><td>' + numberWithCommas(json[i].qty) + '</td><td>' + json[i].uom + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $('#swsCode').attr('disabled', false);  
                    }
                });
            });
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
