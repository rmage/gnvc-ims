<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>IMS &therefore; Brine Freezing &therefore; Create</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <%            String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form id="poster" method="post" action="BrineFreezing.htm">
                        <input type="hidden" name="action" value="save" />
                    </form>
                    <form id="bfForm" method="post">
                        <input type="hidden" id="wsId" />
                        <table class="collapse tblForm row-select">
                            <caption>Brine Freezing &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">BF Number</td>
                                    <td style="width: 40%;"><input id="bfNo" type="text" required="required" /></td>
                                    <td style="width: 10%;">BF Date</td>
                                    <td><input id="bfDate" type="text" size="10" value="<%=cDate%>" required="required" /> </td>
                                </tr>
                                <tr>
                                    <td>Batch No</td>
                                    <td>
                                        <input id="batchNo" type="text" required="required" /> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="info"></span>
                                    </td>
                                    <td>WS Number</td>
                                    <td>
                                        <input id="wsNo" size="8" type="text" required="required" /> (WSBF)
                                        To Cold Storage
                                        <select id="coldStorage">
                                            <c:forEach items="${model.cs}" var="x">
                                                <option value="${x.id}"><c:out value="${x.code}" /></option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Regu</td>
                                    <td>
                                        <input id="regu" type="text" size="3" required="required" /> 
                                    </td>
                                    <td>Time Shift:</td>
                                    <td>
                                        <select id="timeShift">
                                            <option value="07-15">07:00 - 15:00</option>
                                            <option value="15-23">15:00 - 23:00</option>
                                            <option value="23-07">23:00 - 07:00</option>
                                        </select>
                                        Time Start <input id="timeStart" type="text" size="5" /> Time Finish <input id="timeFinish" type="text" size="5" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" />
                                        <input type="button" value="Cancel" onclick="window.location.replace('BrineFreezing.htm')" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Brine Freezing &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="4">
                                        <input id="getFish" type="button" value="Get Fish" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>No</th>
                                    <th>Fish Type</th>
                                    <th>WS Total Weight</th>
                                    <th>Brine Freezing Weight</th>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="bfDetail"></tbody>
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

        <script>
            // binding event to element
            $('#bfDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#batchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 3,
                select: function(event, ui) {
                    $('#batchNo').val(ui.item.batchNo);
                    $('#info').html(ui.item.supplier);
                    $('#wsNo').focus();
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>Batch No: ' + item.batchNo + ' | Boat : ' + item.boat +
                                '</b><br /> Supplier : ' + item.supplier + '</a></li>')
                        .appendTo(ul);
            };
            $('#batchNo').bind('click', function() {
                $(this).val('');
                $('#info').html('');
                $('#bfDetail').html('');
                $('#getFish').val('Get Fish');
                $('#getFish').attr('disabled', false);
                $('#wsNo').val('');
            });
            $('#getFish').bind('click', function() {
                $(this).val('Fetching data from server....');
                $(this).attr('disabled', true);
                $(this).css('cursor', 'progress');
                $.ajax({
                    url: '?',
                    data: {
                        action: 'getFish',
                        batchNo: $('#batchNo').val(),
                        wsNo: $('#wsNo').val()
                    },
                    dataType: 'json',
                    success: function(json) {
                        for (var i = 0; i < json.length; i++) {
                            $('#bfDetail').append('<tr><td>' + (i + 1) + '</td><td>' + json[i].fish + '</td><td>' + json[i].weight + '</td><td><input type="text" size="6" value="0.00" required="required" pattern="^\\d+(\\.\\d{2})?$" title="###0.00" /></td></tr>');
                            $('#bfDetail tr:last-child').data('fishid', json[i].fishid);
                        }
                        if(json.length > 0) {
                            $('#wsId').val(json[0].wsid);
                        } else {
                            $('#batchNo').trigger('click');
                        }
                    },
                    complete: function() {
                        $('#getFish').data('type', $('#type').val());
                        $('#getFish').val('Completed fetch data from server');
                        $('#getFish').css('cursor', '');
                    }
                });
            });

            // submit form
            $('#bfForm').bind('submit', function() {
                if ($('#bfDetail').html().length > 0) {
                    $('#poster').append('<input type="hidden" name="header" value="' +
                            $('#bfNo').val() + ':' + $('#bfDate').val() + ':' + $('#wsId').val() + ':' + $('#coldStorage').val() + ':' + $('#batchNo').val() + ':' +
                            $('#regu').val() + ':' + $('#timeShift').val() + ':' + $('#timeStart').val() + ':' + $('#timeFinish').val() + '" />');
                    $('#bfDetail tr').each(function() {
                        $('#poster').append('<input type="hidden" name="detail" value="' + $(this).data('fishid') + ':' + $(this).find('input').val() + '" />');
                    });
                    
                    $('#poster').submit();
                }
                return false;
            });
        </script>

    </body>
</html>