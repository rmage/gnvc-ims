<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.app.wms.engine.db.dto.FishWs"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.app.wms.engine.db.dto.FishType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create &there4; Withdrawal &there4; IMS</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {

                $('.numeric').on('input', function() {
                    this.value = this.value.replace(/[^0-9.]/g, '');
                });

                $('#wdsDatePicker').datepicker({
                    dateFormat: "dd/mm/yy",
                    altFormat: "yy-mm-dd",
                    altField: "#wdsDate",
                    changeYear: true,
                    changeMonth: true
                });

                $('#batchNo').click(function() {
                    $("#dialog-ajaxSearch").dialog({
                        width: 550,
                        height: 500,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Select Batch Number'});
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
                        height: 400,
                        rowNum: 100, rowList: [100],
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

                $('#addRequest').click(function() {
                    $("#dialog-stock").dialog({
                        width: 550,
                        height: 500,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Stock List'
                    });

                    var vesselId = $('#vesselId').val();
                    var ajaxUrl = 'FishJson.htm?action=getBalanceForWithdrawal&vesselId=' + vesselId + '&storageId=' + $('#storageId').val();
                    $("#list2").jqGrid('setGridParam', {url: ajaxUrl, page: 1}).trigger("reloadGrid");
                    $("#list2").jqGrid({
                        url: ajaxUrl,
                        datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames: ['Fish Code', 'Storage Name', 'Balance', 'Fish ID', 'Storage ID', 'Fish Desc'],
                        colModel: [
                            {name: 'fishCode', index: 'fishCode', width: 150},
                            {name: 'storageName', index: 'storageName', width: 150},
                            {name: 'balance', index: 'balance', width: 200, align: 'right'},
                            {name: 'fishId', index: 'fishId', width: 0, hidden: true},
                            {name: 'storageId', index: 'storageId', width: 0, hidden: true},
                            {name: 'fishDesc', index: 'fishDesc', width: 0, hidden: true}],
                        sortname: 'fishCode',
                        rowNum: 100,
                        height: 400,
                        width: 500,
                        jsonReader: {repeatitems: false},
                        onSelectRow: function(ids) {
                            var localRowData = $(this).getRowData(ids);
                            var rowCount = $('#main tr').length;

                            $("<tr class='ganjil'>" +
                                    "<td class='style1'>" + rowCount + "</td>" +
                                    "<td id='fishType" + rowCount + "' class='style1'>" + localRowData.fishCode + "</td>" +
                                    "<input id='fishId" + rowCount + "' type='hidden' name='fishId" + rowCount + "' value='" + localRowData.fishId + "' />" + "</td>" +
                                    "<td class='style1'>" + localRowData.fishDesc + "</td>" +
                                    "<input id='description" + rowCount + "' type='hidden' name='description" + rowCount + "' value='" + localRowData.fishDesc + "' />" + "</td>" +
                                    "<td class='right' id='balance" + rowCount + "' class='center'>" + localRowData.balance + "</td>" +
                                    "<td class='right' id='qtyHTML" + rowCount + "' class='center'>0</td>" +
                                    "<input id='qty" + rowCount + "' type='hidden' name='qty" + rowCount + "' value='0' /></td>" +
                                    "<td id='uomCodeHTML" + rowCount + "' class='center'>Kg</td>" +
                                    "<input id='uomCode" + rowCount + "' type='hidden' name='uomCode" + rowCount + "' value='Kg' /></td>" +
                                    "<td id='storageHTML" + rowCount + "' class='center'>" + localRowData.storageName + "</td>" +
                                    "<input id='storageId" + rowCount + "' type='hidden' name='storageId" + rowCount + "' value='" + localRowData.storageId + "' /></td>" +
                                    "<td id='" + rowCount + "' style='width: 160px; text-align: center;'><button type='button' onclick='addRequestQuantity(this.parentNode);'>Add Quantity</button><button type='button' onclick='this.parentNode.parentNode.remove();'>Remove</button></td>" +
                                    "</tr>").appendTo("#main tbody");

                            $('#totalData').val(rowCount);
                            $('#dialog-stock').dialog('close');
                        }/*,
                         pager: '#pager2', viewrecords: true, sortorder: "desc"*/}
                    ).trigger("reloadGrid");

                    jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: false, add: false, del: false});
                });

                $('#addForm').validationEngine('attach');

                $('#btnRequestSet').click(function() {
                    var id = $('#dId').val();
                    var balance = $('#dBalanceQty').html().replace(/\,/g, '');
                    var requestedQty = $('#dRequestedQty').val().replace(/\,/g, '');

                    balance = Math.round(balance * 100) / 100;
                    requestedQty = Math.round(requestedQty * 100) / 100;

//                    if (requestedQty <= balance) {
                    $('#qtyHTML' + id).html(addCommas(requestedQty));
                    $('#qty' + id).val(requestedQty);
                    $('#dialog-request').dialog('close');
//                    }
//                    else {
//                        $("#dialog-insufficient").dialog({
//                            open: function() {
//                                $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
//                                $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
//                            },
//                            title: 'Warning',
//                            resizable: false,
//                            height: 120,
//                            modal: true,
//                            buttons: {
//                                "Ok": function() {
//                                    $(this).dialog("close");
//                                    $('#dRequestedQty').focus();
//                                }
//                            }
//                        });
//                    }
                });

                $('#wdsNo').focus().on("blur", function() {
                    var wdsNo = $('#wdsNo').val();
                    $.ajax({
                        url: "FishJson.htm?action=checkWdsNo&query=" + wdsNo,
                        dataType: 'json',
                        success: function(data) {
                            if (data.result) {
                                $("#dialog-not-unique").dialog({
                                    open: function() {
                                        $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                        $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                                    },
                                    title: 'Insufficient Amount',
                                    resizable: false,
                                    height: 150,
                                    modal: true,
                                    buttons: {
                                        "Ok": function() {
                                            $(this).dialog("close");
                                            $('#wdsNo').focus();
                                        }
                                    }
                                });
                            }
                        }
                    });
                });
            });

            function addRequestQuantity(selectedRow) {
                var id = selectedRow.getAttribute('id');
                var balance = $('#balance' + id).html();
                $('#dBalanceQty').html(balance);
                $('#dRequestedQty').val('');
                $('#dId').val(id);

                $("#dialog-request").dialog({
                    width: 350,
                    height: 200,
                    position: "center",
                    modal: true,
                    zindex: 9999,
                    title: 'Request Qty'
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
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishWds.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>">
                        <input type="hidden" id="totalData" name="totalData" value="">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" id="vesselId" name="vesselId" value="0">
                        <input type="hidden" id="wdsDate" name="wdsDate" value="<%=sdf.format(cDate)%>">
                        <table class="collapse tblForm row-select">
                            <caption>Withdrawal &there4; Header</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td></td>
                                    <td class="style1">WDS No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsNo" name="wdsNo" value="" size="30" class="validate[required] text-input numeric"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsDatePicker" name="wdsDatePicker" value="<%=sdfPicker.format(cDate)%>" size="30" class="text-input"/>
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
                                    <td class="style1">Requested By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="requestedBy" name="requestedBy" value="" size="30" class="text-input"/>
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
                                    <td class="style1">Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="approvedBy" name="approvedBy" value="" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <select id="storageId" name="storageId">
                            <c:forEach items="${model.fses}" var="x">
                                <option value="${x.id}">${x.code} - ${x.description}</option>
                            </c:forEach>
                        </select>
                        <a href="javascript:void(0)" id="addRequest">Add Request</a><br /><br />

                        <table class="collapse tblForm row-select" id="main">
                            <caption>Withdrawal &there4; Detail</caption>
                            <thead>
                                <tr>
                                    <td class="style1">No.</td>
                                    <td class="center">Fish Code</td>
                                    <td class="center">Description</td>
                                    <td class="center">Stock</td>
                                    <td class="center">Request Qty.</td>
                                    <td class="center">UOM</td>
                                    <td class="center">Storage</td>
                                    <td class="center">Action</td>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>

                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover">
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" onclick="location.href = 'FishWds.htm'">
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
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
        <script type="text/javascript">
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

        <div id="dialog-ajaxSearch" title="Batch Number Search" style="display:none;z-index:1;">
            <label>Batch Number</label>
            <label>:</label>
            <input type="text" id="query" size="30" class="text-input"/>
            <input type="button" id="batchNumSearchBtn" style="font-size: smaller;" aria-disabled="false"                                                    
                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                   name="btnBatchNumSearch" value="Search" class="search" />
            <table id="list"></table> 
            <div id="pager"></div> 
        </div>

        <div id="dialog-stock" title="Stock List" style="display:none;z-index:1;">
            <table id="list2"></table> 
            <div id="pager2"></div> 
        </div>

        <div id="dialog-request" title="Request Quantity" style="display:none;z-index:1;">
            <table class="collapse tblForm row-select">
                <tr>
                    <td>Balance</td>
                    <td>:</td>
                    <td><label id="dBalanceQty"></label></td>
                </tr>
                <tr>
                    <td>Request</td>
                    <td>:</td>
                    <td><input type="text" size="30" id="dRequestedQty" class="numeric" value="0" /></td>
                </tr>
            </table>
            <input type="hidden" id="dId" value="" />
            <input type="button" id="btnRequestSet" style="font-size: smaller;" aria-disabled="false"                                                    
                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                   name="btnBatchNumSearch" value="Set" class="search" />
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
            "WDS No." is not unique
        </div>

        <script>
            $('#addForm').bind('submit', function() {
                var data = '';

                var header = $('#wdsNo').val() + '^' + $('#wdsDate').val() + '^' + $('#vesselId').val() + '^' + $('#requestedBy').val() + '^' + $('#approvedBy').val() + '^';

                $('#main tbody tr').each(function() {
                    data = data + header + $(this).find('input:eq(0)').val() + '^' + $(this).find('input:eq(1)').val() + '^' + $(this).find('input:eq(4)').val() + '^' +
                            $(this).find('input:eq(2)').val() + '^' + $(this).find('input:eq(3)').val() + '^@';
                });

                if (data !== '') {
                    window.location.replace("?action=save&data=" + encodeURIComponent(data));
//                    console.log(encodeURIComponent(data));
                }

                return false;
            });

            // keyboard shortcut on add item
            $(document).bind('keydown', function(e) {
                if (e.keyCode === 107 && e.altKey) {
                    $('#addRequest').trigger('click');
                }
            });
        </script>
    </body>
</html>