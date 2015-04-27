<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Update &therefore; Fish Reclassification &therefore; IMS</title>
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
                    <form id="frForm" method="post">
                        <input id="frId" name="frId" type="hidden" value="${model.reccs[0].fr_id}">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Reclassification &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">FR Number</td>
                                    <td style="width: 40%;"><input id="frNo" type="text" value="${model.reccs[0].fr_code}" readonly></td>
                                    <td style="width: 10%;">FR Date</td>
                                    <td><input id="frDate" type="text" size="10" required> </td>
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
                                    <td colspan="2">From Batch No</td>
                                    <td colspan="4">
                                        <input id="fromBatchNo" type="text" required="required"> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="infoFrom"></span>
                                    </td>
                                    <td>To Batch No</td>
                                    <td colspan="4">
                                        <input id="toBatchNo" type="text" required="required"> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="infoTo"></span>
                                    </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td colspan="2">From</td>
                                    <td colspan="4">
                                        Fish: <select id="fromFish"><c:forEach items="${model.fs}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                        CS : <select id="fromCS"><c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                            Quantity: <input type="text" id="fromQty" size="5" required="required"> kg(s)
                                        </td>
                                        <td>To</td>
                                        <td colspan="4">
                                            Fish: <select id="toFish"><c:forEach items="${model.fs}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                        CS : <select id="toCS"><c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach></select>
                                            Quantity: <input type="text" id="toQty" size="5" required="required"> kg(s)
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">Remarks</td>
                                        <td colspan="4"><input type="text" id="remarks" size="50"></td>
                                        <td>Type</td>
                                        <td colspan="4">
                                            <select id="frType">
                                                <option value="R">Re-Classification</option>
                                                <option value="A" disabled="disabled">Adjustment</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="button" id="add" value="Add" data-trid="">
                                            <input type="button" id="clean" style="display: none;" value="Cancel">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th rowspan="2" style="width: 30px;">No</th>
                                        <th rowspan="2" style="width: 40px;">Action</th>
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
                                <tbody class="tbl-nohover" id="frDetail">
                                <c:forEach items="${model.reccs}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}">
                                        <td>${vs.index + 1}</td>
                                        <td>
                                            <a class="edit" href="javascript:void(0);"><img title="Update" src="resources/images/edit.gif"></a>
                                            <a class="delete" href="javascript:void(0);"><img title="Delete" src="resources/images/delete.gif"></a>
                                        </td>
                                        <td data-vesselid="${x.from_vessel_id}">${x.from_batch_no}</td>
                                        <td data-fishid="${x.from_fish_id}">${x.from_fish}</td>
                                        <td data-storageid="${x.from_storage_id}">${x.from_storage}</td>
                                        <td>${x.from_qty}</td>
                                        <td data-vesselid="${x.to_vessel_id}">${x.to_batch_no}</td>
                                        <td data-fishid="${x.to_fish_id}">${x.to_fish}</td>
                                        <td data-storageid="${x.to_storage_id}">${x.to_storage}</td>
                                        <td>${x.to_qty}</td>
                                        <td>${x.remarks}</td>
                                        <td data-frtype="${x.fr_type}">${x.type}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
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

            var trId = -1;
            $('#add').bind('click', function() {
                if ($('#frForm')[0].checkValidity()) {
                    if ($(this).data('trid') === '') {
                        $('#frDetail').append('<tr data-id="' + trId + '" data-status="C">' +
                                '<td class="row"></td>' +
                                '<td><a class="edit" href="javascript:void(0);"><img title="Update" src="resources/images/edit.gif"></a><a class="delete" href="javascript:void(0);"><img title="Delete" src="resources/images/delete.gif"></a></td>' +
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

                        trId = trId - 1;
                        gnvs.util.reNumbering($('#frDetail'), 1);
                    } else {
                        var $tr = $('#frDetail tr[data-id="' + $(this).data('trid') + '"]');
                        console.log($tr);

                        $tr.find('td:eq(5)').html($('#fromQty').val());
                        $tr.find('td:eq(9)').html($('#toQty').val());
                        $tr.find('td:eq(10)').html($('#remarks').val());
                        $tr.attr('data-status', 'U');

                        $('#fromBatchNo').data('vesselId', '').val('');
                        $('#fromFish').val('');
                        $('#fromCS').val('');
                        $('#fromQty').val('');
                        $('#toBatchNo').data('vesselId', '').val('');
                        $('#toFish').val('');
                        $('#toCS').val('');
                        $('#toQty').val('');
                        $('#remarks').val('');
                        $('#frType').val('');
                        $(this).data('trid', '').val('Add');
                        $('#clean').hide();
                    }
                }

                return false;
            });

            // submit form
            $('#frForm').bind('submit', function() {
                var data = '', header;
                header = $('#frId').val() + ':s:' + $('#frNo').val() + ':s:' + gnvs.util.toDBDate($('#frDate').val()) + ':s:';

                $('#frDetail tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(2)').data('vesselid') + ':s:' + $(this).find('td:eq(3)').data('fishid') + ':s:' + $(this).find('td:eq(4)').data('storageid') + ':s:' + $(this).find('td:eq(5)').html() + ':s:' + $(this).find('td:eq(6)').data('vesselid') + ':s:' + $(this).find('td:eq(7)').data('fishid') + ':s:' + $(this).find('td:eq(8)').data('storageid') + ':s:' + $(this).find('td:eq(9)').html() + ':s:' + $(this).find('td:eq(10)').html() + ':s:' + $(this).find('td:eq(11)').data('frtype') + ':s::se:';
                });

                if (confirm('Update Fish Reclassification #' + $('#frNo').val() + ' ?')) {
                    if (data === '') {
                        data = data + header + 'X:s:-1:s::se:';
                    }
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
            // row number assignment
            function showRowNumber() {
                $('#frDetail td.row').each(function(i) {
                    $(this).html(i + 1);
                });
            }

            // 2015 | added update function
            $('#frDate').val(gnvs.util.toViewDate('${model.reccs[0].fr_date}'));

            $('#clean').bind('click', function() {
                if (confirm('Cancel update ?')) {
                    $('#fromBatchNo').data('vesselId', '').val('');
                    $('#fromFish').val('');
                    $('#fromCS').val('');
                    $('#fromQty').val('');
                    $('#toBatchNo').data('vesselId', '').val('');
                    $('#toFish').val('');
                    $('#toCS').val('');
                    $('#toQty').val('');
                    $('#remarks').val('');
                    $('#frType').val('');
                    $('#add').data('trid', '').val('Add');
                    $(this).hide();
                }
            });

            $('.delete').live('click', function() {
                if (confirm('Delete this item ?')) {
                    $(this).parent().parent().attr('data-status', 'D').hide();
                    gnvs.util.reNumbering($('#frDetail'), 1);
                }
            });
            $('.edit').live('click', function() {
                if (confirm('Update this item ?')) {
                    var $tr = $(this).parent().parent();

                    $('#fromBatchNo').data('vesselId', $tr.find('td:eq(2)').data('vesselid')).val($tr.find('td:eq(2)').html());
                    $('#fromFish').val($tr.find('td:eq(3)').data('fishid'));
                    $('#fromCS').val($tr.find('td:eq(4)').data('storageid'));
                    $('#fromQty').val($tr.find('td:eq(5)').html());
                    $('#toBatchNo').data('vesselId', $tr.find('td:eq(6)').data('vesselid')).val($tr.find('td:eq(6)').html());
                    $('#toFish').val($tr.find('td:eq(7)').data('fishid'));
                    $('#toCS').val($tr.find('td:eq(8)').data('storageid'));
                    $('#toQty').val($tr.find('td:eq(9)').html());
                    $('#remarks').val($tr.find('td:eq(10)').html());
                    $('#frType').val($tr.find('td:eq(11)').data('frtype'));
                    $('#add').data('trid', $tr.data('id')).val('Update');
                    $('#clean').show();
                }
            });

        </script>

    </body>
</html>