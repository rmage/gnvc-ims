<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Receiving Report</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {
                $('.numeric').on('input', function() {
                    this.value = this.value.replace(/[^0-9.]/g, '');
                });

                $('#dateShift').datepicker({
                    dateFormat: "dd/mm/yy"
                });

                $('#btnSearch').click(function() {
                    var wsNo = $('#queryWsNo').val();
                    var wsDate = $('#queryWsDate').val();
                    location.href = "FishRr.htm?search=true&wsNo=" + wsNo + "&wsDate=" + wsDate;
                });

                $('#rrDate').datepicker({
                    dateFormat: "dd/mm/yy"
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

                $('#wsNo').click(function() {
                    $("#dialog-ajaxSearch2").dialog({
                        width: 500,
                        height: 350,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Select WS Number'});
                });

                $('#batchNumSearchBtn').click(function() {
                    query = $('#query').val();
                    var ajaxUrl = 'FishJson.htm?action=findBatchNumber&query=' + query;
                    $("#list").jqGrid('setGridParam', {url: ajaxUrl, page: 1}).trigger("reloadGrid");
                    $("#list").jqGrid({
                        url: ajaxUrl,
                        datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames: ['Vessel Id', 'Batch Number', 'Vessel Name', 'Supplier Name'],
                        colModel: [
                            {name: 'vesselId', index: 'vesselId', width: 150},
                            {name: 'batchNo', index: 'batchNo', width: 150},
                            {name: 'vesselName', index: 'vesselName', width: 200},
                            {name: 'supplierName', index: 'supplierName', width: 200}],
                        sortname: 'batchNo',
                        rowNum: 10, rowList: [10, 20, 30],
                        jsonReader: {repeatitems: false},
                        onSelectRow: function(ids) {
                            var localRowData = $(this).getRowData(ids);
                            $('#vesselId').val(localRowData.vesselId);
                            $('#dVesselId').val(localRowData.vesselId);
                            $('#batchNo').val(localRowData.batchNo);
                            $('#batchNo2').val(localRowData.batchNo);
                            $('#supplierName').val(localRowData.supplierName);
                            $('#dialog-ajaxSearch').dialog('close');
                        },
                        pager: '#pager', sortname: 'batchNo', viewrecords: true, sortorder: "desc"}
                    ).trigger("reloadGrid");

                    jQuery("#list").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
                });

                $('#batchNumSearchBtn2').click(function() {
                    var vesselId = $('#vesselId').val();
                    var date = $('#dateShift').datepicker({dateFormat: "dd/mm/yy"}).val();
                    var ajaxUrl = 'FishJson.htm?action=findWsData&vesselId=' + vesselId + '&dateShift=' + date;
                    $("#list2").jqGrid('setGridParam', {url: ajaxUrl, page: 1}).trigger("reloadGrid");
                    $("#list2").jqGrid({
                        url: ajaxUrl,
                        datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames: ['Id.', 'WS No.', 'WS Type', 'Supplier Name'],
                        colModel: [
                            {name: 'wsId', index: 'wsId', width: 75, key: true},
                            {name: 'wsNo', index: 'wsNo', width: 75},
                            {name: 'wsType', index: 'wsType', width: 75},
                            {name: 'supplierName', index: 'supplierName', width: 200}],
                        sortname: 'wsId',
                        multiselect: true,
                        rowNum: 10, rowList: [10, 20, 30],
                        jsonReader: {repeatitems: false},
                        pager: '#pager2', sortname: 'wsNo', viewrecords: true, sortorder: "desc"}
                    ).trigger("reloadGrid");

                    jQuery("#list2").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
                });

                $('#selectWsNoBtn').click(function() {
                    var wsIdList = $('#list2').jqGrid('getGridParam', 'selarrrow');
                    $('#dialog-ajaxSearch2').dialog('close');
                    $.ajax({
                        url: 'FishJson.htm?action=findWsDetailData&wsDetailIds=' + wsIdList,
                        dataType: 'json',
                        success: function(data) {
                            var wsNumbers = new Array();
                            $.each(data.wsDetails, function(k, v) {
                                var rowCount = $('#main tr').length;

                                $("<tr class='ganjil'>" +
                                        "<td class='style1'>" + rowCount + "</td>" +
                                        "<input id='storageId" + rowCount + "' type='hidden' name='storageId" + rowCount + "' value='" + v.storageId + "'>" + "</td>" +
                                        "<td class='style1'>" + v.wsNo +
                                        "<input id='wsId" + rowCount + "' type='hidden' name='wsId" + rowCount + "' value='" + v.wsId + "'>" + "</td>" +
                                        "<td id='fishType" + rowCount + "' class='style1'>" + v.fishType + "</td>" +
                                        "<input id='fishId" + rowCount + "' type='hidden' name='fishId" + rowCount + "' value='" + v.fishId + "'>" + "</td>" +
                                        "<input id='fishName" + rowCount + "' type='hidden' name='fishName" + rowCount + "' value='" + v.fishName + "'>" + "</td>" +
                                        "<td class='right'>" + v.totalWeight + "</td>" +
                                        "<input id='totalWeight" + rowCount + "' type='hidden' name='totalWeight" + rowCount + "' value='" + v.totalWeight.replace(/\,/g, '') + "'>" + "</td>" +
                                        "<td class='right' id='goodWeightHTML" + rowCount + "' class='center'>" + v.totalWeight + "</td>" +
                                        "<input id='goodWeight" + rowCount + "' type='hidden' name='goodWeight" + rowCount + "' value='" + v.totalWeight.replace(/\,/g, '') + "'>" + "</td>" +
                                        "<td class='right' id='spoilageWeightHTML" + rowCount + "' class='center'>0</td>" +
                                        "<input id='spoilageWeight" + rowCount + "' type='hidden' name='spoilageWeight" + rowCount + "' value='0'>" + "</td>" +
                                        "<td id='" + rowCount + "' class='center' onClick='check(this)'>Check</td>" +
                                        "</tr>").appendTo("#main tbody");

                                $('#totalData').val(rowCount);
                                if ($.inArray(v.wsNo, wsNumbers) == -1) {
                                    wsNumbers.push(v.wsNo);
                                }
                            });

                            $('#wsNo').val(wsNumbers.join(","));
                        }
                    });
                });

                $('#addForm').validationEngine('attach');

                $('#dBtnSave').click(function() {
                    var id = $('#rowId').val();
                    var spoilWeight = $('#dCookedWeight').val();
                    var totalProcessed = $('#dTotalProcessed').val();

                    $("#dialog-spoilage-confirm").dialog({
                        width: 300,
                        height: 150,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Confirm',
                        buttons: {
                            "Cancel": function() {
                                $(this).dialog("close");
                            },
                            "Ok": function() {
                                $('#spoilageWeight' + id).val(spoilWeight);
                                $('#goodWeight' + id).val(totalProcessed);
                                $('#spoilageWeightHTML' + id).html(addCommas(spoilWeight));
                                $('#goodWeightHTML' + id).html(addCommas(totalProcessed));

                                //ajax post
                                var serializedData = $('#spoilageForm').serialize();
                                var request = $.ajax({
                                    url: 'FishSpoilageData.htm?action=ajaxSave',
                                    type: 'post',
                                    data: serializedData
                                });

                                request.done(function() {
                                    $("#dialog-spoilage-success").dialog({width: 300, height: 150, position: "center",
                                        modal: true, zindex: 9999, title: 'Success'});
                                });

                                request.fail(function() {
                                    $("#dialog-spoilage-failed").dialog({width: 300, height: 150, position: "center",
                                        modal: true, zindex: 9999, title: 'Failed'});
                                });

                                $(this).dialog("close");
                                $('#dialog').dialog('close');
                            }
                        }
                    });
                });

                $('#dBtnCancel').click(function() {
                    $('#dialog').dialog('close');
                });

                $('#rrNo').focus().on("blur", function() {
                    var rrNo = $('#rrNo').val();
                    $.ajax({
                        url: "FishJson.htm?action=checkRrNo&query=" + rrNo,
                        dataType: 'json',
                        success: function(data) {
                            if (data.result) {
                                $("#dialog-not-unique").dialog({
                                    open: function() {
                                        $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                        $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                                    },
                                    title: 'Warning',
                                    resizable: false,
                                    height: 120,
                                    modal: true,
                                    buttons: {
                                        "Ok": function() {
                                            $(this).dialog("close");
                                            $('#rrNo').focus();
                                        }
                                    }
                                });
                            }
                        }
                    });
                });

                $('#dCookedWeight').keyup(function() {
                    var spoilageWeight = $('#dCookedWeight').val();
                    var rawWeight = $('#dRawWeight').val();
                    $('#dTotalProcessed').val(rawWeight - spoilageWeight);
                });
            });

            function check(selectedRow) {
                $("#dialog").dialog({
                    width: 550,
                    height: 400,
                    position: "center",
                    modal: true,
                    zindex: 9999,
                    title: 'Create Spoilage Report'
                });

                var id = selectedRow.getAttribute('id');
                var batchNo = $('#batchNo').val();
                var fishType = $('#fishType' + id).html();
                var fishId = $('#fishId' + id).val();
                var totalWeight = $('#totalWeight' + id).val();
                var spoilWeight = $('#spoilageWeight' + id).val();
                var fishName = $('#fishName' + id).val();

                $('#rowId').val(id);
                $('#dBatchNo').val(batchNo);
                $('#dFishType').val(fishType);
                $('#dFishName').val(fishName);
                $('#dFishId').val(fishId);
                $('#dRawWeight').val(totalWeight);
                $('#dCookedWeight').val(spoilWeight);
                $('#dTotalProcessed').val(totalWeight - spoilWeight);
                $('#dReason').val("");
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
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String mode = (String) m.get("mode");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="FishRr.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" id="totalData" name="totalData" value="" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" id="vesselId" name="vesselId" value="0"/>
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report - Add</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td></td>
                                    <td class="style1">RR Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="rrNo" name="rrNo" value="" size="30" class="validate[required] text-input numeric"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="rrDate" name="rrDate" value="<%=df.format(new Date())%>" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Batch Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" readonly="readonly" value="" size="30" 
                                                   class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                    </td>
                                    <td></td>
                                    <td class="style1">Recived By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="receivedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="supplierName" name="supplierName" value="" 
                                                   readonly="readonly" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Evaluated By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="evaluatedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">WS Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wsNo" name="wsNo" value="" 
                                                   readonly="readonly" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                    </td>
                                    <td></td>
                                    <td class="style1">Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="approvedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <table class="collapse tblForm row-select" id="main">
                            <thead>
                                <tr>
                                    <td class="style1">No.</td>
                                    <td class="center">WS No.</td>
                                    <td class="center">Fish Type</td>
                                    <td class="center">Total Weight</td>
                                    <td class="center">Good Weight</td>
                                    <td class="center">Spoilage Weight</td>
                                    <td class="center">Action</td>
                                </tr>
                            </thead>
                            <tbody></tbody>
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
            </div>

            <div id="dialog" title="Spoilage Report" style="display:none;z-index:1;">
                <form id="spoilageForm">
                    <input type="hidden" id="rowId" value="" />
                    <table class="collapse tblForm row-select">
                        <tbody>
                            <tr>
                                <td width="30%">Date</td>
                                <td>:</td>
                                <td><input type="text" id="dDateShift" name="dateShift" value="<%=df.format(new Date())%>" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/></td>
                            </tr>
                            <tr>
                                <td width="30%">Time Shift</td>
                                <td>:</td>
                                <td>
                                    <label>    
                                        <select name="timeShift">
                                            <option value="07-15">07:00 - 15:00</option>
                                            <option value="15-23">15:00 - 23:00</option>
                                            <option value="23-07">23:00 - 07:00</option>
                                        </select>                                                           
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td width="30%">Batch No.</td>
                                <td>:</td>
                                <td><input type="text" id="dBatchNo" name="batchNo" value="" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/>
                                    <input type="hidden" id="dVesselId" name="vesselId" value="" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/></td>
                            </tr>
                            <tr>
                                <td width="30%">Fish Type</td>
                                <td>:</td>
                                <td><input type="text" id= "dFishType" name="fishType" value="" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/>
                                    <input type="hidden" id= "dFishId" name="fishId" value="" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/></td>
                            </tr>
                            <tr>
                                <td width="30%">Fish Name</td>
                                <td>:</td>
                                <td><input type="text" id= "dFishName" value="" 
                                           readonly="readonly" size="30" class="validate[required] text-input"/>
                            </tr>
                            <tr>
                                <td width="30%">Catcher No.</td>
                                <td>:</td>
                                <td><input type="text" id="dCatcherNo" name="catcherNo" value="" 
                                           size="30" class="validate[required] text-input"/></td>
                            </tr>
                            <tr>
                                <td width="30%">Raw Weight</td>
                                <td>:</td>
                                <td><input type="text" id="dRawWeight" name="rawWeight" value="" size="30" class="validate[required] text-input numeric"/> Kg</td>
                            </tr>
                            <tr>
                                <td width="30%">Spoilage Weight</td>
                                <td>:</td>
                                <td><input type="text" id="dCookedWeight" name="cookedWeight" value="" size="30" class="validate[required] text-input numeric"/> Kg</td>
                            </tr>
                            <tr>
                                <td width="30%">Total Processed</td>
                                <td>:</td>
                                <td><input type="text" id="dTotalProcessed" name="totalProcessed" value="0" size="30" class="validate[required] text-input numeric"/> Kg</td>
                            </tr>
                            <tr>
                                <td width="30%">Reason</td>
                                <td>:</td>
                                <td><textarea id="dReason" name="reason" cols="30" rows="5"></textarea></td>
                            </tr>
                        </tbody>
                        <tfoot class="ui-widget-header">
                            <tr>
                                <td colspan="7">
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                               role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                               name="dBtnSave" id="dBtnSave" value="Save" class="simpan" />
                                    </label>
                                    <label>
                                        <input type="button" style="font-size: smaller;" aria-disabled="false" 
                                               role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                               name="dBtnCancel" id="dBtnCancel" value="Cancel" class="cancel" />
                                    </label>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </form>
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
                    location.href = 'FishRr.htm';
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

        <div id="dialog-ajaxSearch" title="Batch Number Search" style="display:none;z-index:1;">
            <label>Batch Number</label>
            <label>:</label>
            <input type="text" id="query" size="30" class="text-input"/>
            <input type="button" id="batchNumSearchBtn" style="font-size: smaller;" aria-disabled="false"                                                    
                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                   name="btnBatchNumSearch" id="btnSave" value="Search" class="search" />
            <table id="list"></table> 
            <div id="pager"></div> 
        </div>

        <div id="dialog-ajaxSearch2" title="Batch Number Search" style="display:none;z-index:1;">
            <table>
                <tr>
                    <td><label>Batch Number</label></td>
                    <td><label>:</label></td>
                    <td><input type="text" readonly="readonly" id="batchNo2"
                               size="30" class="text-input"/></td>
                </tr>
                <tr>
                    <td><label>Date Shift</label></td>
                    <td><label>:</label></td>
                    <td><input type="text" id="dateShift" size="30" class="text-input" value="<%= df.format(new Date())%>"/></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="button" id="batchNumSearchBtn2" style="font-size: smaller;" 
                               aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                               name="btnBatchNumSearch" value="Search" class="search" />
                    </td>
                </tr>
            </table>
            <table id="list2"></table> 
            <div id="pager2"></div><br />
            <input type="button" id="selectWsNoBtn" style="font-size: smaller;" 
                   aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                   name="btnBatchNumSearch" value="Select WS" class="search" />
        </div>

        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>

        <div id="dialog-spoilage-success" title="confirm" style="display:none;z-index:1;">
            Spoilage report has been saved
        </div>

        <div id="dialog-spoilage-failed" title="confirm" style="display:none;z-index:1;">
            Failed to save data
        </div>

        <div id="dialog-spoilage-confirm" title="confirm" style="display:none;z-index:1;">
            Save this spoilage report?
        </div>

        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div> 

        <div id="dialog-not-unique" title="warning" style="display:none;z-index:1;">
            "RR No." is not unique
        </div>         

    </body>
</html>