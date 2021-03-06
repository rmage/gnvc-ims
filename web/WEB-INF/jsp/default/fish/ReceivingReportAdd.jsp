<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Create &therefore; Receiving Report &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }

            ol.fish-rr {
                margin: 0;
                padding: 0;
            }
            .fish-rr span:first-child {
                border-bottom: 1px solid #c6c6c6;
                display: inline-block;
                font-weight: bold;
                padding: 5px 0;
                width: 150px;
            }
            .fish-rr span:last-child {
                background-color: #000;
                color: #fff;
                display: inline-block;
                padding: 5px 5px 5px 0;
                text-align: right;
                width: 60px;
            }
        </style>
    </head>
    <body>
        <%            String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form id="poster" method="post" action="FishReceivingReport.htm">
                        <input type="hidden" name="action" value="save" />
                    </form>
                    <form id="rrForm" method="post" onsubmit="document.getElementById('save').setAttribute('disabled', 'disabled');">
                        <input type="hidden" id="type" />
                        <input id="dateFrom" type="hidden" name="dateFrom" value="<%=cDateH%>" />
                        <input id="dateTo" type="hidden" name="dateTo" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Head</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>RR Number</td>
                                    <td><input id="rrNo" type="text" required="required" /></td>
                                    <td>RR Date</td>
                                    <td><input id="rrDate" type="text" size="10" value="<%=cDate%>" required="required" /> </td>
                                </tr>
                                <tr>
                                    <td>Batch No</td>
                                    <td><input id="batchNo" type="text" /> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number"></td>
                                    <td>Boat / Supplier</td>
                                    <td id="info"></td>
                                </tr>
                                <tr>
                                    <td>Date Shift</td>
                                    <td>From: <input id="dateShiftF" type="text" size="10" value="<%=cDate%>" required="required" /> To: <input id="dateShiftT" type="text" size="10" value="<%=cDate%>" required="required" /></td>
                                    <td>WS Number</td>
                                    <td><input id="wsNo" type="text" readonly="readonly" required="required" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" id="save" name="save" value="Save" />
                                        <input type="button" value="Cancel" onclick="window.location.replace('FishReceivingReport.htm')" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="6">
                                        <!--<select id="type">
                                            <option>FRESH</option>
                                            <option>FROZEN</option>
                                        </select>-->
                                        <input id="getWs" type="button" value="Get Weight Slip" readonly="readonly" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>No</th>
                                    <th>WS No</th>
                                    <th>WS Date</th>
                                    <th>Shift No</th>
                                    <th>Time Shift</th>
                                    <th style="width: 225px;">Description</th>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="RRDetail"></tbody>
                            <tfoot>
                                <tr>
                                    <td style="text-align: right;" colspan="5">GRAND TOTAL</td>
                                    <td id="grandTotal" style="text-align: right; padding-right: 20px;">0.00</td>
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

        <script>
            // binding event to element
            $('#rrDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#dateShiftF').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#dateFrom", altFormat: "yy-mm-dd"});
            $('#dateShiftT').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#dateTo", altFormat: "yy-mm-dd"});
            $('#batchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 2,
                select: function(event, ui) {
                    $('#batchNo').val(ui.item.batchNo);
                    $('#info').html(ui.item.boat + ' / ' + ui.item.supplier);
                    $('#dateShiftF').focus();

                    if (ui.item.batchNo.slice(-1).toUpperCase().indexOf('F') > -1) {
                        $('#type').val('FRESH');
                    } else {
                        $('#type').val('FROZEN');
                    }

                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.batchNo + ' | ' + item.boat +
                                '</b><br /> Supplier : ' + item.supplier + '</a></li>')
                        .appendTo(ul);
            };
            $('#batchNo').bind('click', function() {
                $(this).val('');
                $('#info').html('');
                $('#RRDetail').html('');
                $('#getWs').val('Get Weight Slip');
                $('#getWs').attr('disabled', false);
                $('#wsNo').val('');
            });
            $('#getWs').bind('click', function() {
                $(this).val('Fetching data from server....');
                $(this).attr('disabled', true);
                $(this).css('cursor', 'progress');
                $.ajax({
                    url: '?',
                    data: {
                        action: 'getWeightSlip',
                        batchNo: $('#batchNo').val(),
                        dateFrom: $('#dateFrom').val(),
                        dateTo: $('#dateTo').val(),
                        type: $('#type').val()
                    },
                    dataType: 'json',
                    success: function(json) {
                        var wsNo = '';
                        for (var i = 0; i < json.rows.length; i++) {
                            wsNo = wsNo + json['rows'][i].wsNo + ',';
                            $('#RRDetail').append('<tr><td>' + (i + 1) + '</td><td>' + json['rows'][i].wsNo + '</td><td>' + json['rows'][i].dateShift +
                                    '</td><td>' + json['rows'][i].regu + '</td><td>' + json['rows'][i].timeShift + '</td><td>' + json['rows'][i].description + '</td></tr>');
                        }
                        $('#grandTotal').html(json['grandTotal']);
                        $('#wsNo').val(wsNo.substr(0, wsNo.length - 1));
                    },
                    complete: function() {
                        $('#getWs').data('type', $('#type').val());
                        $('#getWs').val('Completed fetch data from server');
                        $('#getWs').css('cursor', '');
                    }
                });
            });
            $('#rrForm').bind('submit', function() {
                if ($('#wsNo').val() !== '') {
                    $('#poster').append('<input type="hidden" name="data" value="' +
                            $('#rrNo').val() + ':' + $('#rrDate').val() + ':' + $('#batchNo').val() + ':' + $('#dateFrom').val() + ':' +
                            $('#dateTo').val() + ':' + $('#getWs').data('type') + ':' + $('#wsNo').val() +
                            '" />');
                    $('#poster').submit();
                }
                return false;
            });
        </script>

    </body>
</html>