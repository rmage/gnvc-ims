<!DOCTYPE html>
<html>
    <head>
        <title>Update &there4; Fish Spoilage &there4; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {

                $('#dateShiftPicker').datepicker({
                    dateFormat: "dd/mm/yy",
                    altFormat: "yy-mm-dd",
                    altField: "#dateShift",
                    changeYear: true,
                    changeMonth: true
                });

//                $('#addForm').validationEngine('attach');
                $('#addItem').click(function() {
                    if ($('#batchNo').val() !== '') {
                        if ($('#fishId option').length === 0) {
                        }

                        $('#btnSetItem').val('Set Item');
                        $("#dialog-spoilage").dialog({
                            width: 545,
                            height: 400,
                            position: "center",
                            modal: true,
                            zindex: 9999,
                            title: 'New Spoilage Item'
                        });
                    } else {
                        alert('Please fill Batch Number');
                        return;
                    }
                });
                $.ajax({
                    url: 'FishSpoilageData.htm?term=' + $('#batchNo').val(),
                    method: 'post',
                    data: {action: 'getFishType', term: $('#batchNo').val()},
                    dataType: 'json',
                    success: function(data) {

                        var options = '';
                        for (var i = 0; i < data.length; i++) {
                            options += '<option value="' + data[i].id + '">' + data[i].code + ' ' + data[i].description + '</option>';
                        }

                        $('#fishId').html(options);
                    }
                });

                var i = -1;
                $('#btnSetItem').click(function() {
                    if ($(this).val() === 'Set Item') {
                        var fishId = $('#fishId').val();
                        var fishType = $('#fishId option:selected').html();
                        var area = $('#area').val();
                        var rawWeight = $('#rawWeight').val();
                        var cookedWeight = $('#cookedWeight').val();
                        var totalProcessed = $('#totalProcessed').val();
                        var reason = $('#reason').val();

                        $('<tr data-id="' + i + '" data-status="C">' +
                                '<td class="center"></td>' +
                                '<td class="center">' + area + '</td>' +
                                '<td data-id="' + fishId + '">' + fishType + '</td>' +
                                '<td class="right">' + addCommas(cookedWeight) + '</td>' +
                                '<td class="right">' + addCommas(rawWeight) + '</td>' +
                                '<td class="right">' + addCommas(totalProcessed) + '</td>' +
                                '<td>' + reason + '</td>' +
                                '<td class="center"><a onclick="actionEdit(' + i + ');" href="javascript:void(0);">Edit</a> <a onclick="actionDelete(' + i + ');" href="javascript:void(0);">Delete</a>' +
                                '</td>' +
                                '</tr>').appendTo("#main tbody");

                        $(this).prev().find('input, select, textarea').val('');
                        i = i - 1;
                        gnvs.util.reNumbering($('#main tbody'), '1');
                    } else {
                        var $tr = $('#main tbody tr[data-id="' + $(this).data('id') + '"]');

                        $(this).val('Set Item'); //Set Item
                        $tr.find('td:eq(2)').data('id', $('#fishId').val()).html($('#fishId option:selected').html());
                        $tr.find('td:eq(1)').html($('#area').val());
                        $tr.find('td:eq(3)').html($('#cookedWeight').val());
                        $tr.find('td:eq(4)').html($('#rawWeight').val());
                        $tr.find('td:eq(5)').html($('#totalProcessed').val());
                        $tr.find('td:eq(6)').html($('#reason').val());

                        if (parseInt($(this).data('id')) > 0) {
                            $tr.attr('data-status', 'U');
                        }
                    }

                    $('#dialog-spoilage').find('input:not(:button), select, textarea').val('');
                    $('#dialog-spoilage').dialog('close');
                });

                $('#area').bind('change', function() {
                    $('#cookedWeight').trigger('keyup');
                });

                $('#cookedWeight').keyup(function() {
                    var multiplier = {SKINNING: 2.0, LOINING: 2.75, RECEIVING: 1.0, PACKING: 2.75};
                    $('#rawWeight').val((multiplier[$('#area').val()] * $('#cookedWeight').val()).toFixed(2));
                });
            });

            function addCommas(nStr) {
                nStr += '';
                x = nStr.split('.');
                x1 = x[0];
                x2 = x.length > 1 ? '.' + x[1] : '';
                var rgx = /(\d+)(\d{3})/;
                while (rgx.test(x1)) {
                    x1 = x1.replace(rgx, '$1' + ',' + '$2');
                }
                return x1 + x2;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSpoilageData.htm" method="post" name="form" id="addForm">
                        <input type="hidden" id="dateShift" name="dateShift">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Spoilage &there4; Header</caption>
                            <tbody>
                                <tr>
                                    <td width="30%">Date</td>
                                    <td>:</td>
                                    <td><input type="text" id="dateShiftPicker" name="dateShiftPicker" readonly="readonly" size="30" class="validate[required] text-input"></td>
                                </tr>
                                <tr>
                                    <td width="30%">Time Shift</td>
                                    <td>:</td>
                                    <td>
                                        <label>    
                                            <select id="timeShift" name="timeShift">
                                                <option value="07-15">07:00 - 15:00</option>
                                                <option value="15-23">15:00 - 23:00</option>
                                                <option value="23-07">23:00 - 07:00</option>
                                                <option value="07-12">07:00 - 12:00</option>
                                                <option value="12-17">12:00 - 17:00</option>
                                                <option value="17-23">17:00 - 23:00</option>
                                                <option value="08-14">08:00 - 14:00</option>
                                                <option value="08-16">08:00 - 16:00</option>
                                            </select>                                                         
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="30%">Batch No.</td>
                                    <td>:</td>
                                    <td><input type="text" id="batchNo" name="batchNo" value="${model.spoilage[0].batch_no}" 
                                               readonly="readonly" size="30" class="validate[required] text-input"/>
                                        <input type="hidden" id="vesselId" name="vesselId" value="${model.spoilage[0].vessel_id}" 
                                               readonly="readonly" size="30" class="validate[required] text-input"/></td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header"></tfoot>
                        </table>

                        <a href="javascript:void(0)" id="addItem">Add Item</a><br /><br />

                        <table class="collapse tblForm row-select" id="main">
                            <caption>Fish Spoilage &there4; Detail</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No.</td>
                                    <td class="center">Area</td>
                                    <td class="center">Fish Type</td>
                                    <td class="center">Cooked Weight</td>
                                    <td class="center">Raw Weight</td>
                                    <td class="center">Total Processed</td>
                                    <td class="center">Reason</td>
                                    <td class="center">Action</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${model.spoilage}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}">
                                        <td class="center">${vs.index + 1}</td>
                                        <td class="center">${x.catcher_no}</td>
                                        <td data-id="${x.fish_id}">${x.fish_code}</td>
                                        <td class="right">${x.cooked_weight}</td>
                                        <td class="right">${x.raw_weight}</td>
                                        <td class="right">${x.total_processed}</td>
                                        <td>${x.reason}</td>
                                        <td class="center">
                                            <a href="javascript:void(0);" onclick="actionEdit(${x.id});">Edit</a>
                                            <a href="javascript:void(0);" onclick="actionDelete(${x.id});">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover">
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Update" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" onclick="location.href = 'FishSpoilageData.htm';">
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>

                <div id="dialog-spoilage" title="Add Spoilage Fish" style="display:none;z-index:1;">
                    <table class="collapse tblForm row-select">
                        <tr>
                            <td width="30%">Fish Type</td>
                            <td>:</td>
                            <td>
                                <label>
                                    <select id="fishId" name="fishId"></select>
                                    <label>*</label>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%">Area </td>
                            <td>:</td>
                            <td>
                                <select id="area" name="area">
                                    <option>SKINNING</option>
                                    <option>LOINING</option>
                                    <option>RECEIVING</option>
                                    <option>PACKING</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="30%">Cooked Weight</td>
                            <td>:</td>
                            <td><input type="text" id="cookedWeight" name="cookedWeight" 
                                       value="0" size="30" class="validate[required] text-input"/> Kg</td>
                        </tr>
                        <tr>
                            <td width="30%">Raw Weight</td>
                            <td>:</td>
                            <td><input type="text" id="rawWeight" name="rawWeight" 
                                       value="0" size="30" class="validate[required] text-input" readonly="readonly"/> Kg</td>
                        </tr>
                        <tr>
                            <td width="30%">Total Processed</td>
                            <td>:</td>
                            <td><input type="text" id="totalProcessed" name="totalProcessed" 
                                       value="0" size="30" class="validate[required] text-input"/> Kg</td>
                        </tr>
                        <tr>
                            <td width="30%">Reason for Rejection</td>
                            <td>:</td>
                            <td><textarea id="reason" name="reason" cols="20" rows="5"></textarea></td>
                        </tr>
                    </table>
                    <input type="button" id="btnSetItem" style="font-size: smaller;" aria-disabled="false"                                                    
                           role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                           name="btnSetItem" value="Set Item" class="search" />
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script>
            // INIT | update
            $('#dateShiftPicker').val(gnvs.util.toViewDate('${model.spoilage[0].date_shift}'));
            $('#dateShift').val(gnvs.util.toDBDate($('#dateShiftPicker').val()));
            $('#timeShift').val('${model.spoilage[0].time_shift}');

            // FUNCTION | update
            function actionDelete(id) {
                if (confirm('Delete this row?')) {
                    if (id > 0) {
                        $('#main tbody tr[data-id="' + id + '"]').attr('data-status', 'D').hide();
                    } else {
                        $('#main tbody tr[data-id="' + id + '"]').remove();
                    }

                    gnvs.util.reNumbering($('#main tbody'), '1');
                }
            }
            function actionEdit(id) {
                if (confirm('Update this row?')) {
                    var $tr = $('#main tbody tr[data-id="' + id + '"]');

                    $('#btnSetItem').val('Update Item').data('id', id); //Set Item
                    $('#fishId').val($tr.find('td:eq(2)').data('id'));
                    $('#area').val($tr.find('td:eq(1)').html());
                    $('#cookedWeight').val($tr.find('td:eq(3)').html());
                    $('#rawWeight').val($tr.find('td:eq(4)').html());
                    $('#totalProcessed').val($tr.find('td:eq(5)').html());
                    $('#reason').val($tr.find('td:eq(6)').html());

                    $("#dialog-spoilage").dialog({
                        width: 545,
                        height: 400,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Update Spoilage Item'
                    });
                }
            }
            $('#btnSave').bind('click', function() {
                var data = '', header = '';

                header = $('#dateShift').val() + ':s:' + $('#timeShift').val() + ':s:' + $('#vesselId').val() + ':s:';
                $('#main tbody tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(1)').html() + ':s:' + $(this).find('td:eq(2)').data('id') + ':s:' + $(this).find('td:eq(3)').html().replace(/,/g, '') + ':s:' + $(this).find('td:eq(4)').html().replace(/,/g, '') + ':s:' + $(this).find('td:eq(5)').html().replace(/,/g, '') + ':s:' + $(this).find('td:eq(6)').html() + ':s::se:';
                });
                
                if (confirm('Update Spoilage ?')) {
                    if (data === '') {
                        data = data + header + 'X:s:1:s::se:';
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
            });
        </script>
    </body>
</html>