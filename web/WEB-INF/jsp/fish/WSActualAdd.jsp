<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Fish WS Actual</title>
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
                    <form action="FishWsActual.htm" id="wsForm" method="post">
                        <input name="action" type="hidden" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>WS Type</td>
                                    <td><input id="wsType" size="50" type="text" readonly="true" /></td>
                                    <td>Fish Storage</td>
                                    <td><input id="fishStorage" size="50" type="text" readonly="true" /></td>
                                </tr>
                                <tr>
                                    <td>WS No</td>
                                    <td style="width: 500px;">
                                        <select id="wsId" name="wsId" required="true">
                                            <option value="">-- Select WS Number --</option>
                                            
                                            <c:forEach items="${model.fw}" var="x">
                                            <option value="${x.id}" data-batch="${x.vessel.batchNo}" data-dshift="${x.dateShift}" data-regu="${x.regu}" data-storage="${x.storage.description}" data-supplier="${x.vessel.name}" data-tshift="${x.timeShift}" data-type="${x.wsType.code}">${x.wsNo}</option>
                                            </c:forEach>
                                        </select> <input id="search" type="button" value="Search" />
                                    </td>
                                    <td>Date Shift</td>
                                    <td><input id="dateShift" readonly="true" type="text" /></td>
                                </tr>
                                <tr>
                                    <td>Batch No</td>
                                    <td><input id="batchNo" readonly="true" type="text" /></td>
                                    <td>Time Shift</td>
                                    <td><input id="timeShift" readonly="true" type="text" /></td>
                                </tr>
                                <tr>
                                    <td>Supplier Name</td>
                                    <td><input id="supplierName" readonly="true" type="text" /></td>
                                    <td>Regu</td>
                                    <td><input id="regu" readonly="true" type="text" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('FishWsActual.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <td>No</td>
                                    <td>Fish Type</td>
                                    <td>Total Weight</td>
                                    <td>Actual Weight</td>
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
            $('#wsForm').bind('submit', function() {
                if(!$('#main').html())
                    return false;
            });
            
            $('#search').bind('click', function() {
                if(!$('#wsId').val())
                    return false;
                
                $(this).attr('disabled', true);
                $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                $.ajax({
                    url: 'FishWsActual.htm',
                    data: {action: 'getFishWsDetail', term: $('#wsId').val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for(var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + (i + 1) + '<input name="fishId" type="hidden" value="' + json[i].fishId + '" /></td><td>' + json[i].fishCode + 
                                '</td><td>' + json[i].weight + '</td><td><input name="weight" type="text" required="true" /></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $('#search').attr('disabled', false);  
                    }
                });
            });
            
            $('#wsId').bind('change', function() {
                $('#wsType').val( $(this).find(':selected').data('type') );
                $('#fishStorage').val( $(this).find(':selected').data('storage') );
                $('#dateShift').val( $(this).find(':selected').data('dshift') );
                $('#batchNo').val( $(this).find(':selected').data('batch') );
                $('#timeShift').val( $(this).find(':selected').data('tshift') );
                $('#supplierName').val( $(this).find(':selected').data('supplier') );
                $('#regu').val( $(this).find(':selected').data('regu') );
            });
        </script>
    </body>
</html>
