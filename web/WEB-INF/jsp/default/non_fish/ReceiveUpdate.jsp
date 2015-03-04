<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Receiving &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
                        <input type="hidden" id="rrDate" name="rrDate">
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>RR Number</td>
                                    <td><input id="rrCode" name="rrCode" type="text" value="${model.rr[0].rr_code}" readonly></td>
                                    <td>RR Date</td>
                                    <td colspan="2"><input id="rrDatePicker" name="rrDatePicker" size="10" type="text" required></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td style="width: 500px;"><input id="from" size="50" type="text" value="${model.rr[0].rr_from}" readonly></td>
                                    <td rowspan="2">PO</td>
                                    <td>Number</td>
                                    <td><input type="text" id="poCode" name="poCode" size="10" value="${model.rr[0].po_code}" readonly></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td>PT. Sinar Pure Foods International</td>
                                    <td>Date</td>
                                    <td><input id="poDate" size="10" type="text" readonly="true"></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="4"><input type="text" id="remarks" name="remarks" size="100" value="${model.rr[0].rr_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="button" value="Update">
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('ReceiveReport.htm');">
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td rowspan="2">Action</td>
                                    <td rowspan="2">Item Name</td>
                                    <td rowspan="2">Item Code</td>
                                    <td rowspan="2">Department</td>
                                    <td colspan="2">Quantity</td>
                                    <td colspan="2">Received</td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td>U/M</td>
                                    <td>Good</td>
                                    <td>Bad</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main">
                                <c:forEach items="${model.rr}" var="x">
                                    <tr data-id="${x.id}" data-prs="${x.prsnumber}" data-qty="${x.qty_prs}">
                                        <td><input title="Receive this item" type="checkbox"></td>
                                        <td>${x.product_name}</td>
                                        <td>${x.product_code}</td>
                                        <td>${x.department_name}</td>
                                        <td>${x.qty_prs}</td>
                                        <td>${x.uom_name}</td>
                                        <td><input class="qtyGood" size="10" type="text" value="${x.qty_rr_g}" style="font-size: x-small;"></td>
                                        <td><input class="qtyBad" size="10" type="text" value="${x.qty_rr_b}" style="font-size: x-small;"></td>
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
            $('#rrDatePicker').val(gnvs.util.toViewDate('${model.rr[0].rr_date}'));
            $('#rrDate').val(gnvs.util.toDBDate($('#rrDatePicker').val()));
            $('#poDate').val(gnvs.util.toViewDate('${model.rr[0].po_date}'));
            $('#main tr[data-id]').each(function() {
                if ($(this).data('id') > 0) {
                    $(this).addClass('bold').find('input[type="checkbox"]').prop('checked', true);
                }
            });

            /* BIND | element event */
            $('#rrDatePicker').datepicker({
                dateFormat: 'dd/mm/yy',
                altFormat: 'yy-mm-dd',
                altField: '#rrDate',
                changeMonth: true,
                changeYear: true
            });

            //  BIND | Validation on rr quantity
            $('.qtyGood,.qtyBad').bind('blur', function(e) {
                var $r = $(this).parent().parent();
                var cVal = $(this).val();
                var eVal = $r.find('.' + ($(this).hasClass('qtyGood') ? 'qtyBad' : 'qtyGood')).val();

                if (parseFloat($r.data('qty')) - parseFloat(eVal) < parseFloat(cVal)) {
                    $(this).val(parseFloat($r.data('qty')) - parseFloat(eVal));
                }
            }).bind('keyup', function() {
                var $tr = $(this).parent().parent();

                if ($tr.data('id') > 0) {
                    $tr.attr('data-status', 'U');
                }
            });

            $('#save').bind('click', function() {
                var data = '',
                        id = -10,
                        header = $('#rrCode').val() + ':s:' + $('#rrDate').val() + ':s:' + $('#poCode').val() + ':s:' + $('#from').val() + ':s:' + $('#remarks').val() + ':s:';

                $('#main tr[data-status]').each(function() {
                    // VALIDATION | quantity must be filled
                    if (parseFloat($(this).find('.qtyGood').val()) <= 0 && parseFloat($(this).find('.qtyBad').val()) <= 0) {
                        data = '';
                        alert('VALIDATION | selected checkbox must have quantity larger than 0 (zero)!');
                        return false;
                    }

                    data = data + header + $(this).data('status') + ':s:' + ($(this).data('id') === -1 ? id : $(this).data('id')) + ':s:'
                            + $(this).data('prs') + ':s:' + $(this).find('td:eq(2)').html() + ':s:' + $(this).find('td:eq(3)').html() + ':s:'
                            + $(this).find('.qtyGood').val() + ':s:' + $(this).find('.qtyBad').val() + ':s:' + $(this).find('td:eq(5)').html() + ':s::se:';
                    id = id - 1;
                });

                if (confirm('Update Receiving #' + $('#rrCode').val() + ' ?')) {
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
            });

            $('input[type="checkbox"]').bind('change', function() {
                var $tr = $(this).parent().parent(),
                        id = $tr.data('id');
                if ($(this)[0].checked) {
                    if (id > 0) {
                        $tr.attr('data-status', 'U');
                    } else {
                        $tr.attr('data-status', 'C');
                    }

                    $tr.addClass('bold');
                    $tr.find('td:eq(6) > input').focus().trigger('keyup');
                } else {
                    if (id > 0) {
                        $tr.attr('data-status', 'D');
                    } else {
                        $tr.removeAttr('data-status');
                    }

                    $tr.removeClass('bold');
                }
            });

            function setDatePicker(s) {
                $(s).datepicker({dateFormat: 'dd/mm/yy'});
            }

        </script>
    </body>
</html>
