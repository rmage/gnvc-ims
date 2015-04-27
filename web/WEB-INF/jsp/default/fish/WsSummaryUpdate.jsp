<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Update &therefore; Fish Weight Slip Summary &therefore; IMS</title>
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
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form id="wssForm" method="post" action="FishWsSummary.htm">
                        <input id="wssId" type="hidden" value="${model.wss[0].id}">
                        <input id="fishType" type="hidden" value="" name="fishType">
                        <input id="fishTypeHTML" type="hidden" value="" name="fishTypeHTML">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Weight Slip Summary &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">WSS Number</td>
                                    <td style="width: 40%;"><input id="wssCode" name="wssCode" type="text" size="13" value="${model.wss[0].code}" required></td>
                                    <td style="width: 10%;">Batch Number</td>
                                    <td><input id="batchNo" name="batchNo" type="text" size="13" required value="${model.wss[0].batch_no}"></td>
                                </tr>
                                <tr>
                                    <td>Date From</td>
                                    <td><input id="dateFrom" name="dateFrom" size="10" type="text" required></td>
                                    <td>Date To</td>
                                    <td><input id="dateTo" name="dateTo" size="10" type="text" required></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="button" id="preview" value="Preview" />
                                        <input type="submit" id="save" value="Update" />
                                        <input id="btnCancel" type="button" value="Cancel" onclick="window.location.replace('FishWsSummary.htm')" />
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
            $('#dateFrom').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"}).val();
            $('#dateTo').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"}).val();

            $('#batchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 2,
                select: function(event, ui) {
                    $('#batchNo').val(ui.item.batchNo);
                    detectFishType(ui.item.batchNo);
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
                        '&params=' + $('#batchNo').val() + ':' + gnvs.util.toDBDate($('#dateFrom').val()) + ':' + gnvs.util.toDBDate($('#dateTo').val()) + ':' + $('#fishTypeHTML').val();
            });

            function detectFishType(t) {
                if (t.indexOf('F') > -1) {
                    $('#fishType').val('FishWssFresh');
                    $('#fishTypeHTML').val('FRESH');
                } else {
                    $('#fishType').val('FishWssFrozen');
                    $('#fishTypeHTML').val('FROZEN');
                }
            }

            // INIT | update
            $('#dateFrom').val(gnvs.util.toViewDate('${model.wss[0].date_from}'));
            $('#dateTo').val(gnvs.util.toViewDate('${model.wss[0].date_to}'));
            detectFishType($('#batchNo').val());
            
            $('#wssForm').bind('submit', function() {
                if (confirm('Update Spoilage ?')) {
                    var data = $('#wssId').val() + ':s:' + $('#wssCode').val() + ':s:' + $('#batchNo').val() + ':s:' + gnvs.util.toDBDate($('#dateFrom').val()) + ':s:' + gnvs.util.toDBDate($('#dateTo').val()) + ':s::se:';
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }
                
                return false;
            });
        </script>

    </body>
</html>