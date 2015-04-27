<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="com.app.wms.engine.db.dto.Ws"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Update &there4; Weight Slip &there4; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.numeric').on('input', function() {
                    this.value = this.value.replace(/[^0-9.]/g, '');
                });

                $('#addItem').click(function() {
                    $('#dialogMode').val('add');
                    $("#dialog-add-item").dialog({
                        width: 500,
                        height: 200,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Add Item'
                    });
                });

                var i = -1;
                $('#btnItemSet').click(function() {
                    var fishType = $('#fishType option:selected').html();
                    var fishId = $('#fishType').val();
                    var totalWeight = $('#totalWeight').val();
                    var dialogMode = $('#dialogMode').val();

                    if (dialogMode === 'add') {
                        var rowCount = $('#main tr').length - 2;
                        $("<tr class='ganjil' data-id='" + i + "' data-status='C'>" +
                                "<td class='style1'>" + rowCount + "</td>" +
                                "<td id='fishType" + rowCount + "' class='center'>" + fishType + "</td>" +
                                "<input id='fishId" + rowCount + "' type='hidden' name='fishId" + rowCount + "' value='" + fishId + "' /></td>" +
                                "<td id='totalWeightHTML" + rowCount + "' class='center'>" + addCommas(totalWeight) + "</td>" +
                                "<input id='totalWeight" + rowCount + "' type='hidden' name='totalWeight" + rowCount + "' value='" + totalWeight + "' /></td>" +
                                "<td id='" + rowCount + "' class='center' onClick='editItem(this)'>Edit</td>" +
                                "<td id='" + rowCount + "' class='center' onClick='deleteItem(this)'>Delete</td>" +
                                "</tr>").appendTo("#main tbody");

                        i = i - 1;
                    }
                    else if (dialogMode === 'edit') {
                        var editId = $('#editId').val();
                        $('#fishId' + editId).val(fishId);
                        $('#fishType' + editId).html(fishType);
                        $('#totalWeight' + editId).val(totalWeight);
                        $('#totalWeightHTML' + editId).html(addCommas(totalWeight));

                        var $p = $('#totalWeightHTML' + editId).parent();
                        if (parseInt($p.data('id')) > 0) {
                            $p.attr('data-status', 'U');
                        }
                    }

                    $('#totalWeight').val(0);
                    $('#dialog-add-item').dialog('close');
                    wsTotalWeight();
                });

//                $('#ajaxSearchBtn').click(function() {
//                    var query = $('#query').val();
//                    var ajaxUrl = 'FishJson.htm?action=findBatchNumber&query=' + query;
//                    $("#list").jqGrid('setGridParam', {url: ajaxUrl, page: 1}).trigger("reloadGrid");
//                    $("#list").jqGrid({
//                        url: ajaxUrl,
//                        datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
//                        colNames: ['Vessel Id', 'Batch Number', 'Vessel Name', 'Supplier Name'],
//                        colModel: [
//                            {name: 'vesselId', index: 'vesselId', width: 50},
//                            {name: 'batchNo', index: 'batchNo', width: 100},
//                            {name: 'vesselName', index: 'vesselName', width: 200},
//                            {name: 'supplierName', index: 'supplierName', width: 200}],
//                        sortname: 'batchNo',
//                        rowNum: 10, rowList: [10, 20, 30],
//                        jsonReader: {repeatitems: false},
//                        onSelectRow: function(ids) {
//                            var localRowData = $(this).getRowData(ids);
//                            $('#batchNo').val(localRowData.batchNo);
//                            $('#supplierName').val(localRowData.supplierName);
//                            $('#vesselId').val(localRowData.vesselId);
//                            $('#dialog-ajaxSearch').dialog('close');
//
//                            if (localRowData.batchNo.indexOf('F') > -1) {
//                                $('#wsTypeId').find('option:contains(WSNR), option:contains(WSNC)').attr('disabled', true);
//                                $('#wsTypeId').find('option:contains(WSHR), option:contains(WSBF), option:contains(WSABF), option:contains(WSL)').attr('disabled', false);
//                            } else {
//                                $('#wsTypeId').find('option:contains(WSNR), option:contains(WSNC)').attr('disabled', false);
//                                $('#wsTypeId').find('option:contains(WSHR), option:contains(WSBF), option:contains(WSABF), option:contains(WSL)').attr('disabled', true);
//                            }
//                            $('#wsTypeId').val(-1).trigger('change');
//                        },
//                        pager: '#pager', sortname: 'batchNo', viewrecords: true, sortorder: "desc"}
//                    ).trigger("reloadGrid");
//
//                    jQuery("#list").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
//                });
            });

            function editItem(selectedRow) {
                var id = selectedRow.getAttribute('id');
                var totalWeight = $('#totalWeight' + id).val();
                var fishId = $('#fishId' + id).val();

                $('#editId').val(id);
                $('#fishType').val(fishId);
                $('#totalWeight').val(totalWeight);
                $('#dialogMode').val('edit');
                $("#dialog-add-item").dialog({
                    width: 500,
                    height: 200,
                    position: "center",
                    modal: true,
                    zindex: 9999,
                    title: 'Edit Item'
                });
            }

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
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="FishWs.htm" method="post" id="addForm">
                        <input type="hidden" id="vesselId" name="vesselId" value="${model.ws[0].vessel_id}">
                        <input type="hidden" id="dateShift" name="dateShift">

                        <table class="collapse tblForm row-select">
                            <caption>Weight Slip &there4; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Batch No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="batchNo" id="batchNo" size="30"                                                    
                                                   readonly="readonly"  value="${model.ws[0].vessel_batch}" class="validate[required] text-input" data-id="${model.ws[0].id_ws}">
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">Fish Storage</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <select id="storageId" name="storageId" onchange="this.selectedIndex = 0;">
                                                <c:forEach items="${model.fishStorages}" var="fishStorage">
                                                    <c:if test="${model.ws[0].storage_id == fishStorage.id}">
                                                        <option value="${fishStorage.id}"><c:out value="${fishStorage.code}" /></option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>                                                            
                                        </label>
                                    </td>
                                </tr>
                                <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="supplierName" id="supplierName" size="30"                                                    
                                                   readonly="readonly" value="${model.ws[0].vessel_name}" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">Date Shift</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="dateShiftPicker" size="25" id="dateShiftPicker" readonly>                                                            
                                        </label>
                                    </td>
                                </tr>
                                <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="wsNo" id="wsNo" size="30"                                                    
                                                   value="${model.ws[0].ws_no}" class="validate[required] text-input numeric" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%">Time Shift</td>
                                    <td class="style1">
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
                                <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">WS Type</td>
                                    <td class="style1">
                                        <label>
                                            <select id="wsTypeId" name="wsTypeId">
                                                <c:forEach items="${model.wsTypes}" var="wsType">
                                                    <c:if test="${model.ws[0].wstype_id == wsType.id}">
                                                        <option value="${wsType.id}">${wsType.code}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr class="detail_genap">
                                    <td></td>
                                    <td width="20%">Group</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="regu" id="regu" size="30"                                                    
                                                   value="${model.ws[0].ws_regu}" class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                        <a href="javascript:void(0)" id="addItem">Add Item</a><br /><br />

                        <table id="main" class="collapse tblForm row-select ui-widget-content">
                            <caption>Weight Slip &there4; Detail</caption>
                            <thead>
                                <tr>
                                    <td class="center">No.</td>
                                    <td class="center">Fish Type</td>
                                    <td class="center">Total Weight</td>
                                    <td class="center" colspan="2">Action</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.ws}" var="x" varStatus="vs">
                                    <tr class="ganjil" data-id="${x.id_wsd}">
                                        <td class="style1">${vs.index + 1}</td>
                                        <td class="center" id="fishType${vs.index + 1}">${x.fish_code}</td><input type="hidden" value="${x.fish_id}" name="fishId${vs.index + 1}" id="fishId${vs.index + 1}">
                                <td class="center" id="totalWeightHTML${vs.index + 1}">${x.total_weight}</td><input type="hidden" value="${x.total_weight}" name="totalWeight${vs.index + 1}" id="totalWeight${vs.index + 1}">
                                <td onclick="editItem(this)" class="center" id="${vs.index + 1}">Edit</td>
                                <td onclick="deleteItem(this)" class="center" id="${vs.index + 1}">Delete</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <th colspan="3"><b style="float: right;">Total Weight</b></th>
                                    <th id="wsTotalWeight" style="text-align: center;"></th>
                                    <th></th>
                                </tr>
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnUpdate" value="Update" class="simpan" />
                                        </label>
                                        <label><input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnBack" id="btnBack" value="Back" class="cancel" /></label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>

            <div id="dialog-add-item" style="display:none;z-index:1;">
                <input type="hidden" id="dialogMode" value="" />
                <input type="hidden" id="editId" value="" />
                <table class="collapse tblForm row-select">
                    <tbody class="tbl-nohover">
                        <tr class="detail_genap">
                            <td width="15px"></td>
                            <td width="20%">Fish Type</td>
                            <td class="style1">
                                <label>
                                    <select id="fishType" name="fishId">
                                        <c:forEach items="${model.fishes}" var="fish">
                                            <option value="${fish.id}">
                                                <c:out value="${fish.code}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                    <label class="requiredfield" title="This Field Is Required!">*</label>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td width="15px"></td>
                            <td width="20%">Total Weight</td>
                            <td class="style1">
                                <label>                                                                                                                                                                             
                                    <input type="text" id="totalWeight" name="totalWeight" size="10" id="fishTotalWeight" value="" 
                                           class="validate[required] text-input numeric" /> Kg 
                                    <label class="requiredfield" title="This Field Is Required!">*</label>                                                           
                                </label>
                            </td> 
                        </tr>
                    </tbody>
                    <tfoot class="ui-widget-header">
                        <tr><td colspan="7">
                                <label>
                                    <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                           role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                           name="btnSave" id="btnItemSet" value="Set" class="simpan" />
                                </label>
                            </td>
                        </tr>
                    </tfoot>
                </table>
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

            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(function() {
                $('#btnBack').click(function() {
                    location.href = 'FishWs.htm';
                    return false;
                });

                $('#dateShiftPicker').datepicker({
                    dateFormat: "dd/mm/yy",
                    altFormat: "yy-mm-dd",
                    altField: "#dateShift",
                    changeYear: true,
                    changeMonth: true
                });
            });
        </script>
        <script type="text/javascript">
//            $(function() {
//                $('#btnCancel').click(function() {
//                    location.href = 'FishType.htm';
//                });
//            });

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
                } else {
                    if ($('#storageId').attr('onchange') === '' && $('#storageId').val() === '0') {
                        return false;
                    }
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

//            var wsTypes = ['WSNC', 'WSBF', 'WSABF'];
//            var wsTypes = ['WSNC'];
//            $('#wsTypeId').bind('change', function() {
//                for (var i = 0; i < wsTypes.length; i++) {
//                    if ($(this).find('option:selected').html().indexOf(wsTypes[i]) > -1) {
//                        $('#storageId').attr('onchange', '');
//                        break;
//                    } else {
//                        $('#storageId').attr('onchange', 'this.selectedIndex = 0;');
//                        $('#storageId option:eq(0)').prop('selected', true);
//                    }
//                }
//            });

            // keyboard shortcut on add item
            $(document).bind('keydown', function(e) {
                if (e.keyCode === 107 && e.altKey) {
                    $('#addItem').trigger('click');
                }
            });

            // total weight in list
            function wsTotalWeight() {
                var totalWeight = 0.0;
                $('#main tbody tr:visible').each(function() {
                    totalWeight = totalWeight + parseFloat($(this).find('td:eq(2)').html().split(',').join(''));
                });
                $('#wsTotalWeight').html('<b>' + addCommas(totalWeight.toFixed(2)) + '</b>');
            }
        </script>
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>

        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>

        <div id="dialog-not-unique" title="incomplete" style="display:none;z-index:1;">
            "WS No." is not unique
        </div>

        <script>
            $('#addForm').bind('submit', function() {
                var data = '';

                var header = $('#wsTypeId').val() + '^' + $('#vesselId').val() + '^' + $('#storageId').val() + '^' + $('#wsNo').val() + '^' +
                        $('#dateShift').val() + '^' + $('#timeShift').val() + '^' + $('#regu').val() + '^';

                $('#main tbody tr').each(function() {
                    data = data + header + $(this).find('input:eq(0)').val() + '^' + $(this).find('input:eq(1)').val() + '^@';
                });

                if (data !== '') {
                    window.location.replace("?action=save&data=" + encodeURIComponent(data));
                    //console.log(encodeURIComponent(data));
                }

                return false;
            });

            // INIT | update
            $('#vesselId').val(${model.ws[0].vessel_id});
            $('#dateShiftPicker').val(gnvs.util.toViewDate('${model.ws[0].date_shift}'));
            $('#dateShift').val(gnvs.util.toDBDate($('#dateShiftPicker').val()));
            $('#timeShift').val('${model.ws[0].time_shift}');
            if ($('#storageId').html() === '') {
                $('#storageId').html('<option value="0">-- NONE --</option>');
            }
//            $('#storageId').val(${model.ws[0].storage_id});
//            if ($('#batchNo').val().indexOf('F') > -1) {
//                $('#wsTypeId').find('option:contains(WSNR), option:contains(WSNC)').attr('disabled', true);
//                $('#wsTypeId').find('option:contains(WSHR), option:contains(WSBF), option:contains(WSABF), option:contains(WSL)').attr('disabled', false);
//            } else {
//                $('#wsTypeId').find('option:contains(WSNR), option:contains(WSNC)').attr('disabled', false);
//                $('#wsTypeId').find('option:contains(WSHR), option:contains(WSBF), option:contains(WSABF), option:contains(WSL)').attr('disabled', true);
//            }
//            $('#wsTypeId').val(${model.ws[0].wstype_id});
            $(function() {
                wsTotalWeight();
            });

            // FUNCTION | delete
            function deleteItem(td) {
                var $p = $(td).parent();

                if (parseInt($p.data('id')) > 0) {
                    $p.attr('data-status', 'D').hide();
                } else {
                    $p.remove();
                }

                wsTotalWeight();
            }

            // FUNCTION | update
            $('#btnUpdate').bind('click', function() {
                var data = '', header = '';

                header = $('#batchNo').data('id') + ':s:' + $('#wsTypeId').val() + ':s:' + $('#vesselId').val() + ':s:' + $('#storageId').val() + ':s:' + $('#wsNo').val() + ':s:' + $('#dateShift').val() + ':s:' + $('#timeShift').val() + ':s:' + $('#regu').val() + ':s:';
                $('#main tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('input:eq(0)').val() + ':s:' + $(this).find('input:eq(1)').val() + ':s::se:';
                });

                if (confirm('Update Weight Slip #' + $('#wsNo').val() + ' ?')) {
                    if (data === '') {
                        data = data + header + 'X:s:-1:s::se:';
                    }
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnBack').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }
            });
        </script>
    </body>
</html>