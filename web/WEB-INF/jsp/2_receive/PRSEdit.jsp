<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Update Purchase Requisition Slip - IMS</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
        </style>
        <script language="JavaScript">
            $(document).ready(function() {

                $('#prsAddForm').validationEngine('attach');
                /*
                 * calendar request date
                 */
                $('#requestdate').datepicker({
                    dateFormat: "dd/mm/yy"
                });
                $('#productcode,#productname').bind('keyup', function() {
                    $(this).data('select', '0');
                });
                var queryArr = [],
                        idNumber = 0;
                $('#btnAddItem').click(function() {
                    if ($(this).val() === 'Add') {
                        // VALIDATE | Only type and didn't select from list
                        if ($('#productcode').data('select') === '0' || $('#productname').data('select') === '0') {
                            alert('[Error] Item typing detected! Please choose from list or create new item in Master Product.');
                            return false;
                        }

                        var jumlah = $("#jumlah").val();
                        if (jumlah <= 0) {
                            return;
                        }

                        var isiArray = $("#productcode").val() + " " + $("#tipebarang").val();
                        if ($.inArray(isiArray, queryArr) === -1) {
                            queryArr.push(isiArray);
                        } else {
                            alert('Already Have Item');
                            return false;
                        }

                        if ($('#productname').val() === '' || $('#stockonhand') === '') {
                            alert('Please to fill items data needed');
                            return false;
                        }

                        idNumber = idNumber + 1;
                        $('#main tbody').append('<tr data-id="' + ('C' + idNumber) + '" data-status="C">' +
                                '<td></td>' +
                                '<td><a href="javascript:void(0)" class="update"><img src="resources/images/edit.gif" title="Update"></a><a href="javascript:void(0)" class="delete"><img src="resources/images/delete.gif" title="Delete"></a></td>' +
                                '<td data-code="' + $("#productcode").val() + '">' + $("#productname").val() + '</td>' +
                                '<td>' + $("#stockonhand").val() + '</td>' +
                                '<td>' + $("#jumlah").val() + '</td>' +
                                '<td>' + $("#uomName").val() + '</td>' +
                                '</tr>');
                        gnvs.util.reNumbering($('#main tbody'), 1);
                    } else {
                        var $tr = $('#main tbody > tr[data-id=' + $(this).data('id') + ']');

                        if ($tr.data('status') !== 'C') {
                            $tr.data('status', 'U');
                        }
                        $tr.find('td:eq(3)').html($('#stockonhand').val());
                        $tr.find('td:eq(4)').html($('#jumlah').val());
                        $tr.find('td:eq(5)').html($('#uomName').val());
                        $(this).data('id', undefined).val('Add');
                        $('#btnClearItem').show();
                    }

                    gnvs.util.getParent($(this), 4).prev().find('input').val('');
                });
            });
            function formatCurrency(n) {
                n = isNaN(n) || n === '' || n === null ? 0.00 : n;
                return parseFloat(n).toFixed(2);
            }

            function numberWithCommas(n) {
                var parts = n.toString().split(".");
                return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
            }

            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode == 8 || charCode == 13 || charCode == 99 || charCode == 118 || charCode == 46)
                {
                    return true;
                }
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }

            function changeQtyDecimal() {
                a = $("#jumlah").val();
                b = formatCurrency(a);
                c = numberWithCommas(b);
                $("#jumlah").val(c);
                d = $("#stockonhand").val();
                e = formatCurrency(d);
                f = numberWithCommas(e);
                $("#stockonhand").val(f);
            }

        </script>                                       
    </head>
    <body>
        <%            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        %>


        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="PurchaseRequisition.htm" method="post" id="prsAddForm">
                        <input type="hidden" name="action" value="save" />
                        <input id="prsId" name="prsId" type="hidden" value="${model.h.id}">
                        <input id="prsNumber" name="prsNumber" type="hidden" value="${model.h.prsnumber}">
                        <table class="collapse tblForm row-select">
                            <caption>Purchase Requisition Add</caption>
                            <tbody class="tbl-nohover">

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Charged to Department</td>
                                    <td>
                                        <label>
                                            <select id="departmentName" name="departmentName">
                                                <c:forEach var="droplist" items="${requestScope.model.dropListDepartment}">
                                                    <option value="${droplist.departmentCode}" ${(droplist.departmentName eq requestScope.model.departmentName)? "selected": ""}>
                                                        ${droplist.departmentCode} - ${droplist.departmentName}
                                                    </option> 
                                                </c:forEach>
                                            </select>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>PRS Date</td>
                                    <td>
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="prsdate" size="25" id="prsdate" readonly value="${model.h.prsdate}">
                                        </label>
                                    </td>
                                </tr>

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Date Needed</td>
                                    <td>
                                        <label>
                                            <input type="text" id="requestdate" name="requestdate" value="${model.h.requestdate}" size="30"class="validate[required] text-input">
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td width="20%">Remarks</td>
                                    <td><label><textarea style="width: 374px; height: 51px;" id="remarks" name="remarks">${model.h.remarks}</textarea></label></td>
                                </tr>

                            </tbody>                           
                        </table>

                        <div id="tabs">
                            <ul>
                                <li><a href="#product">Items</a></li>
                            </ul>
                            <div id="product">
                                <table style="margin-bottom: 0;">
                                    <tr class="detail_genap">
                                        <td width="10%">Item Code</td>
                                        <td width="20%">
                                            <input type="text" id="productcode" name="productcode" value="" size="30" data-select="0" /> 
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Items" />
                                        </td>
                                        <td width="10%">Stock On Hand</td>
                                        <td width="20%">
                                            <input type="text" size="30" value="" name="stockonhand" onblur="changeStockOnHandDecimal()" id="stockonhand" />
                                        </td>
                                        <td width="10%">Unit</td>
                                        <td width="20%">
                                            <input type="text" name="uomName" value="" size="30" readonly="" id="uomName" />                       
                                        </td>
                                    </tr>
                                    <tr class="detail_genap">
                                        <td width="10%">Item Name</td>
                                        <td width="20%">
                                            <input type="text" id="productname" name="productname" value="" size="30" data-select="0" />
                                        </td>
                                        <td width="10%">Qty</td>
                                        <td width="20%">
                                            <input type="text" size="30" value="" onblur="changeQtyDecimal()" onkeypress="return isNumberKey(event)" name="jumlah" id="jumlah" />
                                        </td>
                                    </tr>
                                    <tfoot>
                                        <tr>
                                            <td colspan="4">
                                                <label>
                                                    <input type="button" name="btnSavePurchase" id="btnAddItem" value="Add" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                                </label>
                                                <label>
                                                    <input type="button" name="btnCancel" id="btnClearItem" value="Clear" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;"/>
                                                </label>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        <table class="collapse tblForm row-select"  id="main">
                            <caption>Items - List</caption>
                            <thead>
                                <tr>
                                    <td>No.</td>
                                    <td>Action</td>
                                    <td>Description</td>
                                    <td>Stock on Hand</td>
                                    <td>Qty</td>
                                    <td>Unit</td>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${model.d}" var="x" varStatus="vs">
                                    <tr data-id="${x.id}">
                                        <td>${vs.index + 1}</td>
                                        <td>
                                            <a class="update" href="javascript:void(0)"><img title="Update" src="resources/images/edit.gif"></a>
                                            <a class="delete" href="javascript:void(0)"><img title="Delete" src="resources/images/delete.gif"></a>
                                        </td>
                                        <td data-code="${x.productcode}">${x.productname}</td>
                                        <td>${x.prs_soh}</td>
                                        <td>${x.qty}</td>
                                        <td>${x.uom_name}</td>
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
                                                   name="btnSavePurchase" id="btnSavePurchase" value="Update" class="simpan" />
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
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'PurchaseRequisition.htm?';
                });
                $('#btnClearItem').click(function() {
                    window.location = window.location.href = 'PurchaseRequisition.htm?action=create';
                    $("#main tbody").html('');
                });
                var cookieName, $tabs, stickyTab;
                cookieName = 'stickyTab';
                $tabs = $('#tabs');
                $tabs.tabs({
                    select: function(e, ui)
                    {
                        $.cookies.set(cookieName, ui.index);
                    }
                });
                stickyTab = $.cookies.get(cookieName);
                if (!isNaN(stickyTab))
                {
                    $tabs.tabs('select', stickyTab);
                }

            });
            $('#tabs > ul').tabs({selected: 1});
            // modified by : FYA
            //  AUTOCOMPLETE | Find product by code
            $('#productcode').autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(event, ui) {
                    $("#productcode").val(ui.item.itemCode);
                    $("#productname").val(ui.item.itemName);
                    $("#uomName").val(ui.item.uom);
                    $("#stockonhand").val(ui.item.soh);
                    $('#jumlah').focus();
                    $('#productcode,#productname').data('select', '1');
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName + '</b><br /> Stock : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };
            //  AUTOCOMPLETE | Find product by name
            $('#productname').autocomplete({
                source: '?action=getProduct&mode=name',
                minLength: 2,
                select: function(event, ui) {
                    $("#productcode").val(ui.item.itemCode);
                    $("#productname").val(ui.item.itemName);
                    $("#uomName").val(ui.item.uom);
                    $("#stockonhand").val(ui.item.soh);
                    $('#jumlah').focus();
                    $('#productcode,#productname').data('select', '1');
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>' + item.itemCode + ' : ' + item.itemName + '</b><br /> Stock : ' + item.soh + '</a></li>')
                        .appendTo(ul);
            };
            $("#btnSavePurchase").click(function() {

                //if invalid do nothing
                if (!$("#prsAddForm").validationEngine('validate')) {
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
                            // 2015 Update | FYA
                            var data = '';
                            var header = $('#prsId').val() + ':s:' + $('#prsNumber').val() + ':s:' + gnvs.util.toDBDate($('#prsdate').val()) + ':s:' + gnvs.util.toDBDate($('#requestdate').val()) + ':s:' + $('#remarks').val() + ':s:' + $('#departmentName').val() + ':s:';
                            $('#main tbody').find('tr:data(status),tr[data-status]').each(function() {
                                var detail = $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(2)').data('code') + ':s:' +
                                        $(this).find('td:eq(2)').html() + ':s:' + $(this).find('td:eq(3)').html() + ':s:' + $(this).find('td:eq(4)').html() + ':s:' +
                                        $(this).find('td:eq(5)').html() + ':s:';

                                data = data + header + detail + ':se:';
                            });
                            // console.log(data);

                            // 2015 Update | by FYA
                            gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                                if (json.message === '') {
                                    $('#btnCancel').trigger('click');
                                } else {
                                    alert(json.message);
                                }
                            });

                            $(this).dialog("close");
                        }
                    },
                    zindex: 1, title: 'Confirm'});
            });

            //
            // 2015 Update | by FYA
            //
            $('#departmentName').val('${model.h.department_name}');
            $('a.update').live('click', function() {
                var $tr = gnvs.util.getParent($(this), 2);
                $('#productcode').val($tr.find('td:eq(2)').data('code'));
                $('#productname').val($tr.find('td:eq(2)').html());
                $('#stockonhand').val($tr.find('td:eq(3)').html());
                $('#jumlah').val($tr.find('td:eq(4)').html());
                $('#uomName').val($tr.find('td:eq(5)').html());

                $('#btnAddItem').data('id', $tr.data('id')).val('Update');
                $('#btnClearItem').hide();
            });
            $('a.delete').live('click', function() {
                var $tr = gnvs.util.getParent($(this), 2);
                if ($tr.data('status') !== 'C') {
                    $tr.data('status', 'D').css('display', 'none');
                } else {
                    $tr.remove();
                }

                gnvs.util.reNumbering($('#main tbody'), 1);
            });
        </script>

        <div id="dialog-confirm" title="Item Search" style="display:none;z-index:1;">Save Data?</div>
        <div id="dialog-incomplete" title="Item Search" style="display:none;z-index:1;">Please to fill all mandatory data</div>
    </body>
</html>