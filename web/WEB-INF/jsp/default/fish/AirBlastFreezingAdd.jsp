<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="windows-1252">
        <title>IMS &therefore; Air Blast Freezing &therefore; Create</title>
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
                        <input type="hidden" id="wsId">
                        <input type="hidden" id="abfDate" name="abfDate" value="<%=sdf.format(cDate)%>">
                        <table class="collapse tblForm row-select">
                            <caption>Air Blast Freezing &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td style="width: 10%;">ABF Number</td>
                                    <td style="width: 40%;"><input id="abfNo" type="text" required="required" /></td>
                                    <td style="width: 10%;">ABF Date</td>
                                    <td><input id="abfDatePicker" name="abfDatePicker" type="text" size="10" value="<%=sdfPicker.format(cDate)%>" required="required"> </td>
                                </tr>
                                <tr>
                                    <td>Batch No</td>
                                    <td>
                                        <input id="batchNo" type="text" required="required" /> <img width="16" height="16" alt="Search" src="resources/images/search.png" title="Need 8-digit batch number">
                                        Supplier : <span id="info"></span>
                                    </td>
                                    <td>To Cold Storage</td>
                                    <td>
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
                                            <option value="07-12">07:00 - 12:00</option>
                                            <option value="12-17">12:00 - 17:00</option>
                                            <option value="17-23">17:00 - 23:00</option>
                                            <option value="08-14">08:00 - 14:00</option>
                                            <option value="08-16">08:00 - 16:00</option>
                                        </select>
                                        Time Start <input id="timeStart" type="text" size="5" /> Time Finish <input id="timeFinish" type="text" size="5" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" />
                                        <input type="button" value="Cancel" onclick="window.location.replace('AirBlastFreezing.htm')" />
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
                                            <c:forEach items="${model.f}" var="x">
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
                            <tbody class="tbl-nohover" id="abfDetail"></tbody>
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
            $('#abfDatePicker').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd/mm/yy",
                altField: "#abfDate",
                altFormat: "yy-mm-dd"
            });
            $('#batchNo').autocomplete({
                source: '?action=getBatchInfo',
                minLength: 2,
                select: function(event, ui) {
                    $('#batchNo').val(ui.item.batchNo);
                    $('#info').html(ui.item.supplier);
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
                $('#abfDetail').html('');
                $('#addFish').val('Get Fish');
                $('#addFish').attr('disabled', false);
            });
            $('#addFish').bind('click', function() {
                if ($("#dFish").val() !== "" && parseFloat($("#dQuantity").val()) > 0) {
                    $('#abfDetail').append('<tr data-fish="' + $("#dFish").val() + '">' +
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
//            $('#addFish').bind('click', function() {
//                $(this).val('Fetching data from server....');
//                $(this).attr('disabled', true);
//                $(this).css('cursor', 'progress');
//                $.ajax({
//                    url: '?',
//                    data: {
//                        action: 'addFish',
//                        batchNo: $('#batchNo').val(),
//                        wsNo: $('#wsNo').val()
//                    },
//                    dataType: 'json',
//                    success: function(json) {
//                        for (var i = 0; i < json.length; i++) {
//                            $('#abfDetail').append('<tr><td>' + (i + 1) + '</td><td>' + json[i].fish + '</td><td>' + json[i].weight + '</td><td><input type="text" size="6" value="0.00" required="required" pattern="^\\d+(\\.\\d{2})?$" title="###0.00" /></td></tr>');
//                            $('#abfDetail tr:last-child').data('fishid', json[i].fishid);
//                        }
//                        if (json.length > 0) {
//                            $('#wsId').val(json[0].wsid);
//                        } else {
//                            $('#batchNo').trigger('click');
//                        }
//                    },
//                    complete: function() {
//                        $('#addFish').data('type', $('#type').val());
//                        $('#addFish').val('Completed fetch data from server');
//                        $('#addFish').css('cursor', '');
//                    }
//                });
//            });

            // submit form
            $('#abfForm').bind('submit', function() {
//                if ($('#abfDetail').html().length > 0) {
//                    $('#poster').append('<input type="hidden" name="header" value="' +
//                            $('#abfNo').val() + '^' + $('#abfDate').val() + '^' + $('#wsId').val() + '^' + $('#coldStorage').val() + '^' + $('#batchNo').val() + '^' +
//                            $('#regu').val() + '^' + $('#timeShift').val() + '^' + $('#timeStart').val() + '^' + $('#timeFinish').val() + '" />');
//                    $('#abfDetail tr').each(function() {
//                        $('#poster').append('<input type="hidden" name="detail" value="' + $(this).data('fishid') + '^' + $(this).find('input').val() + '" />');
//                    });
//
//                    $('#poster').submit();
//                }

                var data = '';

                var header = $("#abfNo").val() + "^" + $("#abfDate").val() + "^" + $("#coldStorage").val() + "^" + $("#batchNo").val() + "^" +
                        $("#regu").val() + "^" + $("#timeShift").val() + "^" + $("#timeStart").val() + "^" + $("#timeFinish").val() + "^";

                $('#abfDetail tr').each(function() {
                    data = data + header + $(this).data("fish") + "^" + $(this).find("td:eq(3)").html() + "^@";
                });

                if (data !== '') {
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + encodeURIComponent(data));
//                        console.log(encodeURIComponent(data));
                    }
                }

                return false;
            });
        </script>

    </body>
</html>