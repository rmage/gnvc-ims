<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>IMS &therefore; Fish Weight Slip Summary &therefore; Create</title>
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
            String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form id="fmForm" method="post" action="FishWsSummary.htm">
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" id="altDateFrom" name="altDateFrom" value="<%=cDateH%>" />
                        <input type="hidden" id="altDateTo" name="altDateFrom" value="<%=cDateH%>" />
                        <input id="fishType" type="hidden" value="" name="fishType">
                        <input id="fishTypeHTML" type="hidden" value="" name="fishTypeHTML">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Weight Slip Summary &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">WSS Number</td>
                                    <td style="width: 40%;"><input id="wssCode" name="wssCode" type="text" required="required" /></td>
                                    <td style="width: 10%;">Batch Number</td>
                                    <td><input id="batchNo" name="batchNo" type="text" size="10" required="required" /> </td>
                                </tr>
                                <tr>
                                    <td>Date From</td>
                                    <td><input id="dateFrom" name="dateFrom" size="10" type="text" required="required" value="<%=cDate%>" /></td>
                                    <td>Date To</td>
                                    <td><input id="dateTo" name="dateTo" size="10" type="text" required="required" value="<%=cDate%>" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="button" id="preview" value="Preview" />
                                        <input type="submit" id="save" value="Save" />
                                        <input type="button" value="Cancel" onclick="window.location.replace('FishWsSummary.htm')" />
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

        <script>
            // binding event to element
            $('#dateFrom').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#altDateFrom", altFormat: "yy-mm-dd"});
            $('#dateTo').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy", altField: "#altDateTo", altFormat: "yy-mm-dd"});

            $('#batchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 3,
                select: function(event, ui) {
                    $('#batchNo').val(ui.item.batchNo);

                    if (ui.item.batchNo.slice(-1) === 'F') {
                        $('#fishType').val('FishWssFresh');
                        $('#fishTypeHTML').val('FRESH');
                    } else {
                        $('#fishType').val('FishWssFrozen');
                        $('#fishTypeHTML').val('FROZEN');
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

            $('#preview').bind('click', function() {
                window.location = 'GenerateReport.htm?action=index&item=' + $('#fishType').val() + '&type=xls' +
                        '&params=' + $('#batchNo').val() + ':' + $('#altDateFrom').val() + ':' + $('#altDateTo').val() + ':' + $('#fishTypeHTML').val();
            });
        </script>

    </body>
</html>