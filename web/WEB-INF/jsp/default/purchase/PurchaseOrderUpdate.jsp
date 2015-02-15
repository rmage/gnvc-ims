<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Purchase Order - IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
            .ui-datepicker { display: none; }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="search" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>PO Number</td>
                                    <td style="width: 500px;"><input id="poCode" name="poCode" type="text" pattern="[0-9]{1,}" value="${model.po[0].po_code}" readonly></td>
                                    <td>PO Date</td>
                                    <td><input id="poDate" name="poDate" size="10" type="text" required></td>
                                </tr>
                                <tr>
                                    <td>Supplier</td>
                                    <td>
                                        <input id="sSupplier" size="50" type="text" value="${model.po[0].supplier_name}" required>
                                        <input id="sSupplierCode" type="hidden" value="${model.po[0].supplier_code}" required>
                                    </td>
                                    <td>Discount</td>
                                    <td><input id="discount" name="discount" size="1" type="text" value="0" pattern="[0-9]{1,2}" value="${model.po[0].discount}" required> %</td>
                                </tr>
                                <tr>
                                    <td>PPH</td>
                                    <td><input id="pph" name="pph" size="1" type="text" value="0" pattern="[0-9]{1,2}" value="${model.po[0].pph}" required> %</td>
                                    <td>PPN</td>
                                    <td><input id="ppn" name="ppn" size="1" type="text" value="0" pattern="[0-9]{1,2}" value="${model.po[0].ppn}" required> %</td>
                                </tr>
                                <tr>
                                    <td>Currency</td>
                                    <td>
                                        <select id="currency" name="currency">
                                            <c:forEach items="${model.c}" var="x">
                                                <option>${x.currencyCode}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>Remarks</td>
                                    <td>
                                        <select id="confirmatory">
                                            <option value="0">Normal</option>
                                            <option value="1">Confirmatory</option>
                                        </select>
                                        <input id="remarks" name="remarks" size="50" type="text" value="${model.po[0].remarks}">
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input id="save" type="button" value="Update" />
                                        <input type="submit" value="Search" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('Purchase.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Pick Item to Generate Purchase Order</caption>
                            <thead>
                                <tr>
                                    <td>Action</td>
                                    <td>PRS Number</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Department</td>
                                    <td>Quantity</td>
                                    <td>Unit</td>
                                    <td>Unit/Price</td>
                                    <td>Amount</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.po}" var="x">
                                    <tr class="bold" data-id="${x.id}">
                                        <td><input title="Pick this item" type="checkbox" checked></td>
                                        <td>${x.prsnumber}</td>
                                        <td>${x.productcode}</td>
                                        <td>${x.productname}</td>
                                        <td>${x.department_code}</td>
                                        <td>${x.qty}</td>
                                        <td>${x.uom_name}</td>
                                        <td class="right">${x.unit_price}</td>
                                        <td class="right">${x.sub_total}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>

            // INIT | value
            $('#poDate').datepicker({dateFormat: "dd/mm/yy"}).val(gnvs.util.toViewDate('${model.po[0].po_date}'));
            $('#currency').val('${model.po[0].currency}');
            if ('${model.po[0].remarks}'.indexOf('CONFIRMATORY') > -1) {
                $('#confirmatory').val(1);
            }
            $('#main td:nth-child(8), #main td:nth-child(9)').each(function() {
                $(this).html(parseFloat($(this).html()).toLocaleString('en-US'));
            });

            /* BIND | element event */
            $('#save').bind('click', function() {
                var data = '',
                        header = $('#poCode').val() + ':s:' + gnvs.util.toDBDate($('#poDate').val()) + ':s:' + $('#sSupplierCode').val() + ':s:' + $('#discount').val() + ':s:' + $('#pph').val() + ':s:' + $('#ppn').val() + ':s:' + $('#currency').val() + ':s:' + $('#remarks').val() + ':s:';

                $('#main tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(1)').html() + ':s:' + $(this).find('td:eq(2)').html() + ':s:' + $(this).find('td:eq(4)').html() + ':s:' + $(this).find('td:eq(8)').html().replace(/,/g, '') + ':s::se:';
                });

                if (confirm('Update Purchase Order #' + $('#poCode').val() + ' ?')) {
                    if (data === '') {
                        data = header + 'X:s:-1:s::se:';
                    }
                    
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: data}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }
            });
            $('#search').bind('submit', function(e) {
                var $o = $(this).find('input[type="submit"]');
                $o.attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                /* get detail item */
                $.ajax({
                    url: 'Purchase.htm',
                    data: {action: 'getItems', key: $('#sSupplierCode').val()},
                    dataType: 'json',
                    success: function(json) {
                        for (var i = 0; i < json.length; i++) {
                            $('#main').append('<tr data-from="search" data-id="-1"><td><input title="Pick this item" type="checkbox"></td><td>' + json[i].prsNumber +
                                    '</td><td>' + json[i].itemCode + '</td><td>' + json[i].itemName +
                                    '</td><td>' + json[i].departmentCode + '</td><td>' + numberWithCommas(json[i].qty) +
                                    '</td><td>' + json[i].unit + '</td><td class="right">' + numberWithCommas(json[i].price) +
                                    '</td><td class="right">' + numberWithCommas(json[i].amount) + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                    }
                });
                return false;
            });
            $('input[type="checkbox"]').live('change', function() {
                var $p = $(this).parent().parent();
                if ($(this)[0].checked) {
                    $p.addClass("bold");
                    if ($p.data('id') > 0) {
                        $p.removeAttr('data-status');
                    } else {
                        $p.attr('data-status', 'C');
                    }
                } else {
                    $p.removeClass("bold");
                    if ($p.data('id') > 0) {
                        $p.attr('data-status', 'D');
                    } else {
                        $p.removeAttr('data-status');
                    }
                }
            });
            var supplierList = [
            <c:forEach items = "${model.supplier}" var = "x" >
                {
                    label: '${x.supplierName}',
                    code: '${x.supplierCode}'
                }
                ,
            </c:forEach>
            ];
            $('#sSupplier').bind('focus', function() {
                $(this).val(null);
                $('#sSupplierCode').val(null);
                $('#main tr[data-id]').attr('data-status', 'D').hide();
                $('#main tr[data-from]').remove();
            });
            $('#sSupplier').autocomplete({
                source: supplierList,
                select: function(event, ui) {
                    $('#sSupplier').val(ui.item.label);
                    $('#sSupplierCode').val(ui.item.code);
                    $('#sSupplier').blur();
                    return false;
                }
            });
            
            $('#confirmatory').bind('change', function() {
                var $r = $('#remarks');
                if ($(this).val() === '1') {
                    if ($r.val().indexOf('CONFIRMATORY') < 0)
                        $r.val('CONFIRMATORY; ' + $r.val());
                } else {
                    $r.val($r.val().replace(/CONFIRMATORY; /gi, ''));
                }
            });
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }

        </script>
    </body>
</html>
