<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Update &therefore; Fish Moving &therefore; IMS</title>
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
                    <form id="fmForm" method="post">
                        <input id="fmId" name="fmId" type="hidden" value="${model.movs[0].fm_id}">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Moving &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">FM Number</td>
                                    <td style="width: 40%;"><input id="fmNo" type="text" value="${model.movs[0].fm_code}" readonly></td>
                                    <td style="width: 10%;">FM Date</td>
                                    <td><input id="fmDate" type="text" size="10" required="required"> </td>
                                </tr>
                                <tr>
                                    <td>From Cold Storage</td>
                                    <td>
                                        <select id="fromCS" required>
                                            <option value="">-- select storage --</option>
                                            <c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach>
                                            </select>
                                        </td>
                                        <td>To Cold Storage</td>
                                        <td>
                                            <select id="toCS" required>
                                                <option value="">-- select storage --</option>
                                            <c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Remarks</td>
                                        <td colspan="3">
                                            <input type="text" size="50" id="remarks" value="${model.movs[0].remarks}">
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" id="save" value="Save" />
                                        <input id="btnCancel" type="button" value="Cancel" onclick="window.location.replace('FishMoving.htm')" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Fish Moving &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <th style="width: 10px;"></th>
                                    <th>
                                        Select Batch Number:
                                        <select id="batchNo" required="required">
                                            <option value="" data-supplier="">-- select batch --</option>
                                        </select>
                                    </th>
                                    <th>
                                        Supplier:
                                        <span id="supplier"></span>
                                    </th>
                                    <th>
                                        Select Fish:
                                        <select id="fishId" required="required">
                                            <option value="" data-balance="0">-- select fish --</option>
                                        </select>
                                    </th>
                                    <th>
                                        Total Weight to Move:
                                        <input type="text" size="10" id="fishWeight" required="required" value="0"> kg(s)
                                        <input type="button" value="Add Detail" id="add">
                                    </th>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Batch Number</td>
                                    <td>Supplier Name</td>
                                    <td>Fish</td>
                                    <td>Total Weight</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="fmDetail">
                                <c:forEach items="${model.movs}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}"><td class="row">${vs.index + 1}</td><td data-vesselid="${x.v_id}">${x.batch_no}</td><td>${x.supplier}</td><td data-fishid="${x.f_id}">${x.fish}</td><td>${x.total_weight}</td></tr>
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
            $('#fmDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"}).val(gnvs.util.toViewDate('${model.movs[0].fm_date}'));
            $('#fromCS option[value!="${model.movs[0].from_storage_id}"]').remove();
            $('#toCS option[value!="${model.movs[0].to_storage_id}"]').remove();
            if (!$('#imgLoadingB').length)
                $('#batchNo').after('<img id="imgLoadingB" src="resources/ui-anim_basic_16x16.gif" />');
            $.ajax({
                url: "?",
                data: {action: "getBatchInCs", storageId: $('#fromCS').val()},
                dataType: "json",
                success: function(json) {
                    $('#batchNo').html('<option value="" data-supplier="">-- select batch --</option>');
                    for (var i = 0; i < json.length; i++) {
                        $('#batchNo').append('<option data-supplier="' + json[i]['3'] + '" value="' + json[i]['1'] + '">' + json[i]['2'] + '</option>');
                    }
                },
                complete: function() {
                    $('#imgLoadingB').remove();
                    $('#batchNo').trigger('change');
                }
            });
            $('#batchNo').bind('change', function() {
                $('#supplier').html($(this).find('option:selected').data('supplier'));
                if (!$('#imgLoadingF').length)
                    $('#fishId').after('<img id="imgLoadingF" src="resources/ui-anim_basic_16x16.gif" />');
                $.ajax({
                    url: "?",
                    data: {action: "getFishInCsBatch", storageId: $('#fromCS').val(), vesselId: $('#batchNo').val()},
                    dataType: "json",
                    success: function(json) {
                        $('#fishId').html('<option data-balance="0" value="">-- select fish --</option>');
                        for (var i = 0; i < json.length; i++) {
                            $('#fishId').append('<option data-balance="' + json[i]['3'] + '" value="' + json[i]['1'] + '">' + json[i]['2'] + '</option>');
                        }
                    },
                    complete: function() {
                        $('#imgLoadingF').remove();
                        $('#fishId').trigger('change');
                    }
                });
            });
            $('#fishId').bind('change', function() {
                $('#fishWeight').val($(this).find('option:selected').data('balance'));
            });
            $('#save').bind('mouseenter', function() {
                if ($('#fmDetail').html().length > 0) {
                    $('#batchNo').attr('required', false);
                    $('#fishId').attr('required', false);
                }
            }).bind('mouseleave', function() {
                if ($('#fmDetail').html().length > 0) {
                    $('#batchNo').attr('required', true);
                    $('#fishId').attr('required', true);
                }
            });

            var i = -1;
            $('#add').bind('click', function() {
                if ($('#fmForm')[0].checkValidity()) {
                    $('#fmDetail').append('<tr data-id="' + i + '" data-status="C">' +
                            '<td class="row"></td>' +
                            '<td data-vesselid="' + $('#batchNo').val() + '">' + $('#batchNo option:selected').html() + '</td>' +
                            '<td>' + $('#batchNo option:selected').data('supplier') + '</td>' +
                            '<td data-fishid="' + $('#fishId').val() + '">' + $('#fishId option:selected').html() + '</td>' +
                            '<td>' + $('#fishWeight').val() + '</td>' +
                            '</tr>');
                    var x = [
                        $('#fmNo').val(), $('#fmDate').val(),
                        $('#fromCS').val(), $('#toCS').val(),
                        $('#remarks').val()
                    ];
                    $('#fmForm')[0].reset();
                    $('#fmNo').val(x[0]);
                    $('#fmDate').val(x[1]);
                    $('#fromCS').val(x[2]);
                    $('#toCS').val(x[3]);
                    $('#remarks').val(x[4]);
                    $('#fromCS').trigger('change');

                    i = i - 1;
                }
                showRowNumber();
                return false;
            });

            $('#fmDetail tr').live('dblclick', function() {
                $(this).attr('data-status', 'D').hide();
                showRowNumber();
            });
            $('#fmDetail td:nth-child(5)').live('click', function() {
                var newWeight = window.prompt('New Total Weight', $(this).html());

                if (parseInt(newWeight) > 0) {
                    $(this).html(newWeight);

                    if ($(this).parent().data('id') > 0) {
                        $(this).parent().attr('data-status', 'U');
                    }
                }
            });

            // submit form
            $('#fmForm').bind('submit', function() {
                if ($('#fmDetail').html().length > 0) {
                    var data = '', header;

                    header = $('#fmId').val() + ':s:' + $('#fmNo').val() + ':s:' + gnvs.util.toDBDate($('#fmDate').val()) + ':s:' + $('#fromCS').val() + ':s:' + $('#toCS').val() + ':s:' + $('#remarks').val() + ':s:';
                    $('#fmDetail tr[data-status]').each(function() {
                        data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(1)').data('vesselid') + ':s:' + $(this).find('td:eq(3)').data('fishid') + ':s:' + $(this).find('td:eq(4)').html() + ':s:KGs:s::se:';
                    });

                    if (confirm('Update Fish Moving #' + $('#fmNo').val() + ' ?')) {
                        if (data === '') {
                            data = data + header + 'X:s:-1:s::se:';
                        }
                        console.log(data);
//                        gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
//                            if (json.message === '') {
//                                $('#btnCancel').trigger('click');
//                            } else {
//                                alert(json.message);
//                            }
//                        });
                    }
                }
                return false;
            });

            // row number assignment
            function showRowNumber() {
                $('#fmDetail td.row:visible').each(function(i) {
                    $(this).html(i + 1);
                });
            }
        </script>

    </body>
</html>