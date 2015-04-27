<!DOCTYPE html>
<html>
    <head>
        <title>Update &there4; Withdrawal &there4; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <script>
            var i = -1, status;
            $(document).ready(function() {
                $('.numeric').on('input', function() {
                    this.value = this.value.replace(/[^0-9.]/g, '');
                });

                $('#wdsDate').datepicker({dateFormat: "dd/mm/yy", changeYear: true, changeMonth: true});
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

                            $("<tr class='ganjil' data-id='" + i + "' data-status='C'>" +
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
                                    "<td id='" + rowCount + "' class='center' onClick='addRequestQuantity(this)'>Add Quantity</td>" +
                                    "<td onclick='removeItem(this)' class='center' id='" + rowCount + "'>Remove Item</td>" +
                                    "</tr>").appendTo("#main tbody");

                            $('#dialog-stock').dialog('close');
                            i = i - 1;
                        }
                    }).trigger("reloadGrid");

                    jQuery("#list2").jqGrid('navGrid', '#pager2', {edit: false, add: false, del: false});
                });

                $('#addForm').validationEngine('attach');

                $('#btnRequestSet').click(function() {
                    var id = $('#dId').val();
                    var balance = $('#dBalanceQty').html().replace(/\,/g, '');
                    var requestedQty = $('#dRequestedQty').val().replace(/\,/g, '');

                    balance = Math.round(balance * 100) / 100;
                    requestedQty = Math.round(requestedQty * 100) / 100;

                    if (status === 'U') {
                        $('#qtyHTML' + id).html(addCommas(requestedQty)).parent().attr('data-status', 'U');
                        status = '';
                    } else {
                        $('#qtyHTML' + id).html(addCommas(requestedQty));
                    }
                    $('#qty' + id).val(requestedQty);
                    $('#dialog-request').dialog('close');
                });
            });

            function addRequestQuantity(selectedRow) {
                var id = selectedRow.getAttribute('id');
                var balance = $('#balance' + id).html();
                $('#dBalanceQty').html(balance);
                $('#dRequestedQty').val(balance);
                $('#dId').val(id);

                if ($(selectedRow).parent().data('id') > 0) {
                    status = 'U';
                }

                $("#dialog-request").dialog({
                    width: 350,
                    height: 200,
                    position: "center",
                    modal: true,
                    zindex: 9999,
                    title: 'Request Qty'
                });
            }

            function removeItem(obj) {
                if (confirm('Continue to remove this item?')) {
                    var $p = $(obj).parent();
                    if ($p.data('id') > 0) {
                        $p.attr('data-status', 'D').hide();
                    } else {
                        $p.remove();
                    }

                    gnvs.util.reNumbering($('#main tbody'), 1);
                }
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
        <div class="container">
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishWds.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="action" value="save">
                        <input type="hidden" id="wdsId" name="wdsId" value="${model.wds[0].wds_id}">
                        <input type="hidden" id="vesselId" name="vesselId" value="${model.wds[0].vessel_id}">
                        <table class="collapse tblForm row-select">
                            <caption>Withdrawal &there4; Header</caption>
                            <tbody class="tbl-nohover">                          
                                <tr>
                                    <td></td>
                                    <td class="style1">WDS No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsNo" name="wdsNo" value="${model.wds[0].wds_no}" size="30" readonly>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="wdsDate" name="wdsDate" size="30" class="text-input"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Batch Number</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="batchNo" name="batchNo" value="${model.wds[0].batch_no}" size="30" class="validate[required] text-input" readonly>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                    </td>
                                    <td></td>
                                    <td class="style1">Requested By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="requestedBy" name="requestedBy" value="${model.wds[0].requested_by}" size="30" class="text-input">
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td class="style1">Supplier Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="supplierName" name="supplierName" value="${model.wds[0].name}" readonly size="30" class="validate[required] text-input">
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Approved By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="approvedBy" name="approvedBy" value="${model.wds[0].approved_by}" size="30" class="text-input"/>
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
                                    <td class="center" colspan="2">Action</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${model.wds}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}">
                                        <td>${vs.index + 1}</td>
                                        <td id="fishType${vs.index + 1}">${x.fish_code}</td>
                                <input type="hidden" value="${x.fish_id}" name="fishId${vs.index + 1}" id="fishId${vs.index + 1}">
                                <td>${x.fish_description}</td><input type="hidden" value="${x.fish_description}" name="description${vs.index + 1}" id="description${vs.index + 1}">
                                <td id="balance${vs.index + 1}" class="right">${x.fish_balance}</td>
                                <td id="qtyHTML${vs.index + 1}" class="right">${x.fish_qty}</td><input type="hidden" value="${x.fish_qty}" name="qty${vs.index + 1}" id="qty${vs.index + 1}">
                                <td class="center" id="uomCodeHTML${vs.index + 1}">${x.fish_uom}</td><input type="hidden" value="${x.fish_uom}" name="uomCode${vs.index + 1}" id="uomCode${vs.index + 1}">
                                <td class="center" id="storageHTML${vs.index + 1}">${x.storage_code}</td><input type="hidden" value="${x.storage_id}" name="storageId${vs.index + 1}" id="storageId${vs.index + 1}">
                                <td onclick="addRequestQuantity(this)" class="center" id="${vs.index + 1}">Change Quantity</td>
                                <td onclick="removeItem(this)" class="center" id="${vs.index + 1}">Remove Item</td>
                                </tr>
                            </c:forEach>
                            </tbody>
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
                                                   name="btnSave" id="btnSave" value="Update" class="simpan" />
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
<!--        <script type="text/javascript">
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
        </script>-->

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

        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">Save data?</div>
        <div id="dialog-spoilage-success" title="confirm" style="display:none;z-index:1;">Spoilage report has been saved</div>
        <div id="dialog-spoilage-failed" title="confirm" style="display:none;z-index:1;">Failed to save data</div>
        <div id="dialog-spoilage-confirm" title="confirm" style="display:none;z-index:1;">Save this spoilage report?</div>
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">Please to fill mandatory data</div>     

        <script>
            $('#btnSave').bind('click', function() {
                var data = '', header = '';

                header = $('#wdsId').val() + ':s:' + $('#wdsNo').val() + ':s:' + gnvs.util.toDBDate($('#wdsDate').val()) + ':s:' + $('#vesselId').val() + ':s:' + $('#requestedBy').val() + ':s:' + $('#approvedBy').val() + ':s:';
                $('#main tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('input:eq(0)').val() + ':s:' + $(this).find('input:eq(1)').val() + ':s:' + $(this).find('input:eq(4)').val() + ':s:' + $(this).find('input:eq(2)').val() + ':s:' + $(this).find('input:eq(3)').val() + ':s::se:';
                });

                if (confirm('Update Withdrawal Slip #' + $('#wdsNo').val() + ' ?')) {
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

            // keyboard shortcut on add item
            $(document).bind('keydown', function(e) {
                if (e.keyCode === 107 && e.altKey) {
                    $('#addRequest').trigger('click');
                }
            });

            // INIT | update
            $('#wdsDate').val(gnvs.util.toViewDate('${model.wds[0].wds_date}'));
        </script>
    </body>
</html>