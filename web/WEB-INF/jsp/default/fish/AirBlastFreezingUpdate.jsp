<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>Update &therefore; Air Blast Freezing &therefore; IMS</title>
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
                    <form id="abfForm" method="post">
                        <input type="hidden" id="abfId" value="${model.abf[0].abf_id}">
                        <table class="collapse tblForm row-select">
                            <caption>Air Blast Freezing &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">ABF Number</td>
                                    <td style="width: 40%;"><input id="abfNo" type="text"  value="${model.abf[0].abf_no}" readonly required></td>
                                    <td style="width: 10%;">ABF Date</td>
                                    <td><input id="abfDate" name="abfDate" type="text" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Batch No</td>
                                    <td>
                                        <input id="batchNo" type="text" value="${model.abf[0].batch_no}" readonly required>
                                        Supplier : <span id="info">${model.abf[0].supplier}</span>
                                    </td>
                                    <td>To Cold Storage</td>
                                    <td>
                                        <select id="coldStorage">
                                            <c:forEach items="${model.cs}" var="x">
                                                <c:if test="${x.id == model.abf[0].storage_id}">
                                                    <option value="${x.id}"><c:out value="${x.code}" /></option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Group</td>
                                    <td>
                                        <input id="regu" type="text" size="3" value="${model.abf[0].regu}" required> 
                                    </td>
                                    <td>Time Shift:</td>
                                    <td>
                                        <select id="timeShift">
                                            <option value="07-15">07:00 - 15:00</option>
                                            <option value="15-23">15:00 - 23:00</option>
                                            <option value="23-07">23:00 - 07:00</option>
                                            <option value="07-12">07:00 - 12:00</option>
                                            <option value="12-17">12:00 - 17:00</option>
                                            <option value="17-23">17:00 - 23:00</option>
                                            <option value="08-14">08:00 - 14:00</option>
                                            <option value="08-16">08:00 - 16:00</option>
                                        </select>
                                        Time Start <input id="timeStart" type="text" size="5" value="${model.abf[0].time_start}"> Time Finish <input id="timeFinish" type="text" size="5" value="${model.abf[0].time_finish}">
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" />
                                        <input id="btnCancel" type="button" value="Cancel" onclick="window.location.replace('AirBlastFreezing.htm')" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Air Blast Freezing &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td colspan="5">
                                        <select id="dFish">
                                            <option value="">-- Pick Fish --</option>
                                            <c:forEach items="${model.fs}" var="x">
                                                <option value="${x.id}" data-size="${x.fishWeightType.code}" data-type="${x.fishType.code}">${x.fishType.code} ${x.fishWeightType.code}</option>
                                            </c:forEach>
                                        </select>
                                        <input class="right" id="dQuantity" type="text" size="6" value="0">
                                        <input id="addFish" type="button" value="Add Fish" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>No</th>
                                    <th>Fish Type</th>
                                    <th>Fish Size</th>
                                    <th>Air Blast Freezing Weight</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="abfDetail">
                                <c:forEach items="${model.abf}" var="x" varStatus="vs">
                                    <tr data-fish="${x.fish_id}" data-id="${x.id}">
                                        <td>${vs.index + 1}</td>
                                        <td>${x.fish_type}</td>
                                        <td>${x.fish_weight}</td>
                                        <td>${x.total_weight}</td>
                                        <td>
                                            <a onclick="updateRow(this);" title="Update this row" href="javascript:void(0);"><img width="16" height="16" src="resources/images/edit.gif"></a>
                                            <a onclick="deleteRow(this);" title="Delete this row" href="javascript:void(0);"><img width="16" height="16" src="resources/images/delete.gif"></a>
                                        </td>
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
            $('#abfDate').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd/mm/yy"
            }).val(gnvs.util.toViewDate('${model.abf[0].abf_date}'));
            $('#timeShift').val('${model.abf[0].time_shift}');

            var id = -1;    
            $('#addFish').bind('click', function() {
                if ($("#dFish").val() !== "" && parseFloat($("#dQuantity").val()) > 0) {
                    $('#abfDetail').append('<tr data-fish="' + $("#dFish").val() + '" data-id="' + id + '" data-status="C">' +
                            '<td>' + ($('#abfDetail tr').length + 1) + '</td>' +
                            '<td>' + $('#dFish option:selected').data('type') + '</td>' +
                            '<td>' + $('#dFish option:selected').data('size') + '</td>' +
                            '<td>' + $('#dQuantity').val() + '</td>' +
                            '<td><a href="#deleteRow" title="Delete this row" onclick="if(confirm(\'Continue to delete data?\'))this.parentNode.parentNode.remove()"><img width="16" height="16" src="resources/images/delete.gif"></a></td>' +
                            '</tr>');
                }

                $('#dFish').val("");
                $('#dQuantity').val(0);
            });

            // submit form
            $('#abfForm').bind('submit', function() {
                var data = '';

                var header = $('#abfId').val() + ':s:' + $("#abfNo").val() + ':s:' + gnvs.util.toDBDate($("#abfDate").val()) + ':s:' + $("#coldStorage").val() + ':s:' + $("#batchNo").val() + ':s:' +
                        $("#regu").val() + ':s:' + $("#timeShift").val() + ':s:' + $("#timeStart").val() + ':s:' + $("#timeFinish").val() + ':s:';

                $('#abfDetail tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).data("fish") + ':s:' + $(this).find("td:eq(3)").html() + ":s::se:";
                });

                if (confirm("Continue to update this document?")) {
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
            
            // INIT | function
            function deleteRow(a) {
                var $tr = $(a).parent().parent();

                if (confirm('Continue to delete data?')) {
                    if ($tr.data('id') > 0) {
                        $tr.attr('data-status', 'D').hide();
                    } else {
                        $tr.remove();
                    }

                    gnvs.util.reNumbering($('#abfDetail'), 1);
                }
            }

            function updateRow(a) {
                var $tr = $(a).parent().parent();

                if (confirm('Continue to update data?')) {
                    var $td = $tr.find('td:eq(3)');
                    $td.html('<input class="abf-weight" typ="text" value="' + $td.html() + '">');
                    $td.find('input').focus();
                }
            }

            $('.abf-weight').live('keyup', function() {
                var $tr = $(this).parent().parent();

                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            }).live('blur', function() {
                $(this).parent().html($(this).val());
            });
        </script>

    </body>
</html>