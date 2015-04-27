<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Create &therefore; Fish Reclassification &therefore; IMS</title>
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
                    <form id="frForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Reclassification &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">FR Number</td>
                                    <td style="width: 40%;"><input id="frNo" type="text" required="required" /></td>
                                    <td style="width: 10%;">FR Date</td>
                                    <td><input id="frDate" type="text" size="10" value="<%=cDate%>" required="required" /> </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" id="save" value="Save" />
                                        <input id="btnCancel" type="button" value="Cancel" onclick="window.location.replace('FishReclassification.htm')" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Reclassification &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td>From Batch No</td>
                                    <td colspan="4">
                                        <input id="fromBatchNo" type="text" required="required" /> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="infoFrom"></span>
                                    </td>
                                    <td>To Batch No</td>
                                    <td colspan="4">
                                        <input id="toBatchNo" type="text" required="required" /> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="infoTo"></span>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td colspan="4">
                                        Fish: <select id="fromFish"><c:forEach items="${model.fs}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                        CS : <select id="fromCS"><c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                            Quantity: <input type="text" id="fromQty" size="5" required="required" /> kg(s)
                                        </td>
                                        <td>To</td>
                                        <td colspan="4">
                                            Fish: <select id="toFish"><c:forEach items="${model.fs}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                        CS : <select id="toCS"><c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                        Quantity: <input type="text" id="toQty" size="5" required="required" /> kg(s)
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="4"><input type="text" id="remarks" size="50" /></td>
                                    <td>Type</td>
                                    <td colspan="4">
                                        <select id="frType">
                                            <option value="R">Re-Classification</option>
                                            <option value="A" disabled="disabled">Adjustment</option>
                                        </select>
                                    </td>
                                    <td><input type="button" id="add" value="Add" /></td>
                                </tr>
                                <tr>
                                    <th rowspan="2">No</th>
                                    <th colspan="4">From</th>
                                    <th colspan="4">To</th>
                                    <th rowspan="2">Remarks</th>
                                    <th rowspan="2">Type</th>
                                </tr>
                                <tr>
                                    <th>Batch</th>
                                    <th>Fish</th>
                                    <th>Cold Storage</th>
                                    <th>Quantity</th>
                                    <th>Batch</th>
                                    <th>Fish</th>
                                    <th>Cold Storage</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="frDetail"></tbody>
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
            $('#frDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#fromBatchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 2,
                select: function(event, ui) {
                    $('#fromBatchNo').data('vesselId', ui.item.id);
                    $('#fromBatchNo').val(ui.item.batchNo);
                    $('#infoFrom').html(ui.item.supplier);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>Batch No: ' + item.batchNo + ' | Boat : ' + item.boat +
                                '</b><br /> Supplier : ' + item.supplier + '</a></li>')
                        .appendTo(ul);
            };
            $('#toBatchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 2,
                select: function(event, ui) {
                    $('#toBatchNo').data('vesselId', ui.item.id);
                    $('#toBatchNo').val(ui.item.batchNo);
                    $('#infoTo').html(ui.item.supplier);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>Batch No: ' + item.batchNo + ' | Boat : ' + item.boat +
                                '</b><br /> Supplier : ' + item.supplier + '</a></li>')
                        .appendTo(ul);
            };
            $('#save').bind('mouseenter', function() {
                if ($('#frDetail').html().length > 0) {
                    $('#fromBatchNo').val(' ');
                    $('#toBatchNo').val(' ');
                    $('#fromQty').val(' ');
                    $('#toQty').val(' ');
                }
            }).bind('mouseleave', function() {
                if ($('#frDetail').html().length > 0) {
                    $('#fromBatchNo').val('');
                    $('#toBatchNo').val('');
                    $('#fromQty').val('');
                    $('#toQty').val('');
                }
            });
            $('#add').bind('click', function() {
                if ($('#frForm')[0].checkValidity()) {
                    $('#frDetail').append('<tr>' +
                            '<td class="row"></td>' +
                            '<td data-vesselid="' + $('#fromBatchNo').data('vesselId') + '">' + $('#fromBatchNo').val() + '</td>' +
                            '<td data-fishid="' + $('#fromFish').val() + '">' + $('#fromFish option:selected').html() + '</td>' +
                            '<td data-storageid="' + $('#fromCS').val() + '">' + $('#fromCS option:selected').html() + '</td>' +
                            '<td>' + money_desimal_to_value($('#fromQty').val()) + '</td>' +
                            '<td data-vesselid="' + $('#toBatchNo').data('vesselId') + '">' + $('#toBatchNo').val() + '</td>' +
                            '<td data-fishid="' + $('#toFish').val() + '">' + $('#toFish option:selected').html() + '</td>' +
                            '<td data-storageid="' + $('#toCS').val() + '">' + $('#toCS option:selected').html() + '</td>' +
                            '<td>' + $('#toQty').val() + '</td>' +
                            '<td>' + $('#remarks').val() + '</td>' +
                            '<td data-frtype="' + $('#frType').val() + '">' + $('#frType option:selected').html() + '</td>' +
                            '</tr>');
                    var x = [
                        $('#frNo').val(), $('#frDate').val()
                    ];
                    $('#frForm')[0].reset();
                    $('#frNo').val(x[0]);
                    $('#frDate').val(x[1]);
                    $('#infoFrom').html('');
                    $('#infoTo').html('');
                }
                showRowNumber();
                return false;
            });
            $('#frDetail tr').live('dblclick', function() {
                $(this).remove();
                showRowNumber();
            });

            // submit form
            $('#frForm').bind('submit', function() {
                var data = '', header;
                header = $('#frNo').val() + ':s:' + gnvs.util.toDBDate($('#frDate').val()) +':s:';

                $('#frDetail tr').each(function(){
                  data = data + header + $(this).find('td:eq(1)').data('vesselid') + ':s:' + $(this).find('td:eq(2)').data('fishid') + ':s:' + $(this).find('td:eq(3)').data('storageid') + ':s:' + $(this).find('td:eq(4)').html() + ':s:' + $(this).find('td:eq(5)').data('vesselid') + ':s:' + $(this).find('td:eq(6)').data('fishid') + ':s:' + $(this).find('td:eq(7)').data('storageid') + ':s:' + $(this).find('td:eq(8)').html() + ':s:' + $(this).find('td:eq(9)').html() + ':s:' + $(this).find('td:eq(10)').data('frtype') + ':s::se:';
                });
                
                if (data !== '' && confirm('Save Fish Reclassification #' + $('#frNo').val() + ' ?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });
            // row number assignment
            function showRowNumber() {
                $('#frDetail td.row').each(function(i) {
                    $(this).html(i + 1);
                });
            }
        </script>

    </body>
</html>