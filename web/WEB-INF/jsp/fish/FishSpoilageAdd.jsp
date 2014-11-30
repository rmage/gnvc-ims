<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>IMS &there4; Fish Spoilage &there4; Create</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {

                $('#dateShiftPicker').datepicker({
                    dateFormat: "dd/mm/yy",
                    altFormat: "yy-mm-dd",
                    altField: "#dateShift",
                    changeYear: true,
                    changeMonth: true
                });

                $('#batchNo').click(function() {
                    $("#dialog-ajaxSearch").dialog({
                        width: 500,
                        height: 350,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Select Batch Number'});
                });

                $('#ajaxSearchBtn').click(function() {
                    query = $('#query').val();
                    var ajaxUrl = 'FishJson.htm?action=findBatchNumber&query=' + query;
                    $("#list").jqGrid('setGridParam', {url: ajaxUrl, page: 1}).trigger("reloadGrid");
                    $("#list").jqGrid({
                        url: ajaxUrl,
                        datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames: ['Vessel Id', 'Batch Number', 'Vessel Name', 'Supplier Name'],
                        colModel: [
                            {name: 'vesselId', index: 'vesselId', width: 50},
                            {name: 'batchNo', index: 'batchNo', width: 100},
                            {name: 'vesselName', index: 'vesselName', width: 200},
                            {name: 'supplierName', index: 'supplierName', width: 200}],
                        sortname: 'batchNo',
                        rowNum: 10, rowList: [10, 20, 30],
                        jsonReader: {repeatitems: false},
                        onSelectRow: function(ids) {
                            var localRowData = $(this).getRowData(ids);
                            $('#batchNo').val(localRowData.batchNo);
                            $('#supplierName').val(localRowData.supplierName);
                            $('#vesselId').val(localRowData.vesselId);
                            $('#dialog-ajaxSearch').dialog('close');
                        },
                        pager: '#pager', sortname: 'batchNo', viewrecords: true, sortorder: "desc"}
                    ).trigger("reloadGrid");

                    jQuery("#list").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
                });

                $('#addForm').validationEngine('attach');

                $('#addItem').click(function() {
                    if ($('#batchNo').val() !== '') {
                        if ($('#fishId option').length === 0) {
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
                        }

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

                $('#btnSetItem').click(function() {
                    var fishId = $('#fishId').val();
                    var fishType = $('#fishId option:selected').html();
                    var area = $('#area').val();
                    var rawWeight = $('#rawWeight').val();
                    var cookedWeight = $('#cookedWeight').val();
                    var totalProcessed = $('#totalProcessed').val();
                    var reason = $('#reason').val();

                    var rowCount = $('#main tr').length;

                    $("<tr class='ganjil'>" +
                            "<td class='center'>" + rowCount + "</td>" +
                            "<td class='center'>" + area + "</td>" +
                            "<input id='area" + rowCount + "' type='hidden' name='area" + rowCount + "' value='" + area + "' />" + "</td>" +
                            "<td id='fishType" + rowCount + "' class='style1'>" + fishType + "</td>" +
                            "<input id='fishId" + rowCount + "' type='hidden' name='fishId" + rowCount + "' value='" + fishId + "' />" + "</td>" +
                            "<td class='right'>" + addCommas(cookedWeight) + "</td>" +
                            "<input id='cookedWeight" + rowCount + "' type='hidden' name='cookedWeight" + rowCount + "' value='" + cookedWeight + "' /></td>" +
                            "<td class='right'>" + addCommas(rawWeight) + "</td>" +
                            "<input id='rawWeight" + rowCount + "' type='hidden' name='rawWeight" + rowCount + "' value='" + rawWeight + "' /></td>" +
                            "<td class='right'>" + addCommas(totalProcessed) + "</td>" +
                            "<input id='totalProcecssed" + rowCount + "' type='hidden' name='totalProcessed" + rowCount + "' value='" + totalProcessed + "' /></td>" +
                            "<td class='center'>" + reason + "</td>" +
                            "<input id='reason" + rowCount + "' type='hidden' name='reason" + rowCount + "' value='" + reason + "' /></td>" +
                            "</tr>").appendTo("#main tbody");

                    $('#totalData').val(rowCount);
                    $('#dialog-spoilage').dialog('close');
                    $(this).prev().find('input, select, textarea').val('');
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
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishSpoilageData.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" name="isActive" value="Y">
                        <input type="hidden" id="totalData" name="totalData" value="0">
                        <input type="hidden" id="dateShift" name="dateShift" value="<%=sdf.format(cDate)%>">
                        <table class="collapse tblForm row-select">
                            <caption>Fish Spoilage &there4; Header</caption>
                            <tbody>
                                <tr>
                                    <td width="30%">Date</td>
                                    <td>:</td>
                                    <td><input type="text" id="dateShiftPicker" name="dateShiftPicker" value="<%=sdfPicker.format(cDate)%>" 
                                               readonly="readonly" size="30" class="validate[required] text-input"/></td>
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
                                    <td><input type="text" id="batchNo" name="batchNo" value="" 
                                               readonly="readonly" size="30" class="validate[required] text-input"/>
                                        <input type="hidden" id="vesselId" name="vesselId" value="" 
                                               readonly="readonly" size="30" class="validate[required] text-input"/></td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                            </tfoot>
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
                                </tr>
                            </thead>
                            <tbody>
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
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>

                <div id="dialog-ajaxSearch" title="Batch Number Search" style="display:none;z-index:1;">
                    <label>Batch Number</label>
                    <label>:</label>
                    <input type="text" id="query" size="30" class="text-input"/>
                    <input type="button" id="ajaxSearchBtn" style="font-size: smaller;" aria-disabled="false"                                                    
                           role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                           name="btnBatchNumSearch" id="btnSave" value="Search" class="search" />
                    <table id="list"></table> 
                    <div id="pager"></div> 
                </div>

                <div id="dialog-spoilage" title="Add Spoilage Fish" style="display:none;z-index:1;">
                    <table class="collapse tblForm row-select">
                        <tr>
                            <td width="30%">Fish Type</td>
                            <td>:</td>
                            <td>
                                <label>
                                    <select id="fishId" name="fishId">
                                        <%--<c:forEach items="${model.fishDataList}" var="fish">--%>
                                            <!--<option value="${fish.id}">-->
                                        <%--<c:out value="${fish.code} - ${fish.fishType.description}" />--%>
                                        <!--</option>-->
                                        <%--</c:forEach>--%>
                                    </select>
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
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'FishSpoilageData.htm';
                });
            });

            $("#btnSave").click(function() {

                //if invalid do nothing
                if (!$("#addForm").validationEngine('validate')) {
                    $("#dialog-incomplete").dialog({
                        open: function() {
                            $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                            $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                        },
                        title: 'Incomplete Form',
                        resizable: false,
                        height: 120,
                        modal: true,
                        buttons: {
                            "Ok": function() {
                                $(this).dialog("close");
                            }
                        }
                    });
                    return false;
                }

                $("#dialog-confirm").dialog({width: 300, height: 150, position: "center", modal: true,
                    buttons: {
                        "Cancel": function() {
                            $(this).dialog("close");
                        },
                        "Save": function() {
                            $("form#addForm").submit();
                        }
                    },
                    zindex: 1, title: 'Confirm'});

            });
        </script>

        <script language="JavaScript">
            function cek() {
                if (form.length.value == "" || form.width.value == "" || form.height.value == "") {
                    alert("data empty");
                    exit;
                }
            }
            function kali() {
                cek();
                a = eval(form.length.value);
                b = eval(form.width.value);
                c = eval(form.height.value);
                d = a * b * c
                form.volumeMatrix.value = d;
            }
        </script>

        <script type="text/javascript">
            function formfocus() {
                document.getElementById('autofocus').focus();
            }
            window.onload = formfocus;
        </script>             

        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>

        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>

        <script>
            $('#addForm').bind('submit', function() {
                var data = '';

                var header = $('#dateShift').val() + '^' + $('#timeShift').val() + '^' + $('#vesselId').val() + '^';

                $('#main tbody tr').each(function() {
                    data = data + $(this).find('input:eq(0)').val() + '^' + header + $(this).find('input:eq(1)').val() + '^' +
                            $(this).find('input:eq(2)').val() + '^' + $(this).find('input:eq(3)').val() + '^' + $(this).find('input:eq(4)').val() + '^' + $(this).find('input:eq(5)').val() + '^~';
                });

                if (data !== '') {
                    window.location.replace("?action=save&data=" + encodeURIComponent(data));
//                    console.log(encodeURIComponent(data));
                }

                return false;
            });
        </script>
    </body>
</html>