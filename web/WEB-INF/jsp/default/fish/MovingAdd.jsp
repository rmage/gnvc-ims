<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>IMS &therefore; Fish Moving &therefore; Create</title>
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
                    <form id="poster" method="post" action="FishMoving.htm">
                        <input type="hidden" name="action" value="save" />
                    </form>
                    <form id="fmForm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Moving &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">FM Number</td>
                                    <td style="width: 40%;"><input id="fmNo" type="text" required="required" /></td>
                                    <td style="width: 10%;">FM Date</td>
                                    <td><input id="fmDate" type="text" size="10" value="<%=cDate%>" required="required" /> </td>
                                </tr>
                                <tr>
                                    <td>From Cold Storage</td>
                                    <td>
                                        <select id="fromCS" required="required">
                                            <option value="">-- select storage --</option>
                                            <c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach>
                                            </select>
                                        </td>
                                        <td>To Cold Storage</td>
                                        <td>
                                            <select id="toCS" required="required">
                                                <option value="">-- select storage --</option>
                                            <c:forEach items="${model.css}" var="x"><option value="${x.id}">${x.code}</option></c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Remarks</td>
                                        <td colspan="3">
                                            <input type="text" size="50" id="remarks" />
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="4">
                                            <input type="submit" id="save" value="Save" />
                                            <input type="button" value="Cancel" onclick="window.location.replace('FishMoving.htm')" />
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
                                            <input type="text" size="5" id="fishWeight" required="required" value="0" /> kg(s)
                                            <input type="button" value="Add Detail" id="add" />
                                        </th>
                                    </tr>
                                    <!--                                <tr>
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
                                                                    </tr>-->
                                <tr>
                                    <td>No</td>
                                    <td>Batch Number</td>
                                    <td>Supplier Name</td>
                                    <td>Fish</td>
                                    <td>Total Weight</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="fmDetail"></tbody>
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
            $('#fmDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});
            $('#fromCS').bind('change', function() {
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
            });
            $('#fromCS option').bind('click', function() {
                $('#fmDetail').html('');
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
            $('#add').bind('click', function() {
                if ($('#fmForm')[0].checkValidity()) {
                    $('#fmDetail').append('<tr>' +
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
                }
                showRowNumber();
                return false;
            });
            $('#fmDetail tr').live('dblclick', function() {
                $(this).remove();
                showRowNumber();
            });

            // submit form
            $('#fmForm').bind('submit', function() {
                if ($('#fmDetail').html().length > 0) {
                    $('#poster').append('<input type="hidden" name="header" value="' + $('#fmNo').val() + ':' + $('#fmDate').val() + ':' + $('#fromCS').val() + ':' + $('#toCS').val() + ':' + $('#remarks').val() + '" />');
                    $('#fmDetail tr').each(function() {
                        $('#poster').append('<input type="hidden" name="detail" value="' +
                                $(this).find('td:eq(1)').data('vesselid') + ':' + $(this).find('td:eq(3)').data('fishid') + ':' + $(this).find('td:eq(4)').html() +
                                '" />');
                    });

                    $('#poster').submit();
                }
                return false;
            });
            // row number assignment
            function showRowNumber() {
                $('#fmDetail td.row').each(function(i) {
                    $(this).html(i + 1);
                });
            }
        </script>

    </body>
</html>