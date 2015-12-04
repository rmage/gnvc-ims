<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPicker = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting &therefore; Fish Receiving Report &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .tblForm tbody tr:not(:last-child) td {
                border-bottom: 1px solid #cecece;
            }

            .row-select tr:hover {
                background-color: rgba(0, 0, 0, 0.07);
            }

            .popup-box {
                background-color: rgba(0, 0, 0, 0.13);
                bottom: 0;
                left: 0;
                position: fixed;
                right: 0;
                top: 0;
                z-index: 10000;
            }
            .popup-box-form {
                background-color: #ffffff;
                border-radius: 5px;
                box-shadow: 0 0 7px 0 rgba(0, 0, 0, 0.23);
                margin: 0 auto;
                padding: 20px;
                position: relative;
                top: 50%;
                transform: translateY(-50%);
                width: 1000px;
            }
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
                    <input id="dateFrom" name="dateFrom" type="hidden" value="<%=sdf.format(cDate)%>">
                    <input id="dateTo" name="dateTo" type="hidden" value="<%=sdf.format(cDate)%>">

                    <table class="collapse tblForm row-select">
                        <caption>Receiving Report &therefore; Un-Processed</caption>
                        <thead>
                            <tr>
                                <td>Date</td>
                                <td colspan="7">
                                    From <input id="dateFromPicker" name="dateFromPicker" type="text" value="<%=sdfPicker.format(cDate)%>">
                                    To <input id="dateToPicker" name="dateToPicker" type="text" value="<%=sdfPicker.format(cDate)%>">
                                    <input id="btnSearch" name="btnSearch" type="button" value="Search">
                                </td>
                            </tr>
                            <tr>
                                <th>RR Number</th>
                                <th>RR Date</th>
                                <th>From</th>
                                <th>WS Number</th>
                                <th>Batch Number</th>
                                <th>Creator</th>
                                <th>Created Date</th>
                                <th class="center" style="width: 50px;">Action</th>
                            </tr>
                        </thead>
                        <tbody id="rrUp"><tr><td colspan="8" class="center"><i>-- tidak ada data --</i></td></tr></tbody>
                    </table>

                    <table class="collapse tblForm row-select">
                        <caption>Receiving Report &therefore; Processed</caption>
                        <thead>
                            <tr>
                                <th>RR Number</th>
                                <th>RR Date</th>
                                <th>From</th>
                                <th>WS Number</th>
                                <th>Batch Number</th>
                                <th>Creator</th>
                                <th>Created Date</th>
                                <th class="center" style="width: 50px;">Action</th>
                            </tr>
                        </thead>
                        <tbody id="rrDown"><tr><td colspan="8" class="center"><i>-- tidak ada data --</i></td></tr></tbody>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <div class="popup-box" style="display: none;">
            <input id="isRevise" name="isRevise" type="hidden" value="0">
            <div class="popup-box-form">
                <div class="poup-box-form-header">
                    <h3 style="float: left;">Receiving Report #<b id="rrCode">00000</b></h3>
                    <input id="btnCancel" name="btnCancel" type="button" value="Cancel" style="float: right;"><hr>
                </div>
                <div class="popup-box-form-body">
                    <table>
                        <thead>
                            <tr>
                                <td class="bold" style="width: 120px;">Document Number</td>
                                <td id="rrCode"></td>
                                <td class="bold" style="width: 140px;">Document Date</td>
                                <td id="rrDate"></td>
                            </tr>
                            <tr>
                                <td class="bold">From</td>
                                <td id="rrFrom"></td>
                                <td class="bold">WS Number</td>
                                <td id="wsCodes"></td>
                            </tr>
                            <tr>
                                <td class="bold">To</td>
                                <td>PT. Sinar Pure Foods International</td>
                                <td class="bold">Batch Number</td>
                                <td id="batchNumber"></td>
                            </tr>
                            <tr>
                                <td class="bold">Tax Paid By</td>
                                <td>
                                    <select id="receivedBy" name="receivedBy">
                                        <option value="Supplier">Supplier</option>
                                        <option value="SPFI">PT. Sinar Pure Foods International</option>
                                    </select>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="4">
                                    <br>
                                    <div style="overflow-y: auto; height: 350px;">
                                        <table class="collapse tblForm row-select ui-widget-content">
                                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Item List</caption>
                                            <thead>
                                                <tr>
                                                    <th class="center" rowspan="2">#</th>
                                                    <th class="center" rowspan="2">Description</th>
                                                    <th class="center" colspan="2">Quantity</th>
                                                    <th class="center" >Contract</th>
                                                    <th class="center" colspan="2">iCore</th>
                                                    <th class="center" rowspan="2">Tax Rate</th>
                                                    <th class="center" colspan="2">Accounting</th>
                                                </tr>
                                                <tr>
                                                    <th class="center">Number</th>
                                                    <th class="center">U/M</th>
                                                    <th class="center">Price</th>
                                                    <th class="center">Unit Price</th>
                                                    <th class="center">Amount</th>
                                                    <th class="center">Unit Price</th>
                                                    <th class="center">Amount</th>
                                                </tr>
                                            </thead>
                                            <tbody id="rrDetailList"><tr><td colspan="10" class="center"><i>-- tidak ada data --</i></td></tr></tbody>
                                            <tfoot>
                                                <tr>
                                                    <td class="right" colspan="9"><b>G R A N D - T O T A L</b></td>
                                                    <td id="rrGrandTotal" class="right">0.00</td>
                                                </tr>
                                            </tfoot>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="4">
                                    <input id="btnSubmit" name="btnSubmit" type="button" value="Process Document">
                                    <input id="btnDelete" name="btnDelete" type="button" value="Remove Data" style="background: #d90000 none repeat scroll 0 0; border-color: #d90000; color: #fff; display: none;">
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            $('#dateFromPicker').datepicker({dateFormat: 'dd/mm/yy', altFormat: 'yy-mm-dd', altField: '#dateFrom', changeMonth: true, changeYear: true});
            $('#dateToPicker').datepicker({dateFormat: 'dd/mm/yy', altFormat: 'yy-mm-dd', altField: '#dateTo', changeMonth: true, changeYear: true});

            $('#btnSearch').on('click', function() {
                var $button = $(this);
                $button.attr('disabled', 'disabled').val('Searching....');

                $.ajax({
                    url: '', type: 'post',
                    data: {action: 'getRR', dateFrom: $('#dateFrom').val(), dateTo: $('#dateTo').val()},
                    dataType: 'json',
                    beforeSend: function() {
                        $('#rrUp').html('<tr><td colspan="8" class="center"><i>-- tidak ada data --</i></td></tr>');
                        $('#rrDown').html('<tr><td colspan="8" class="center"><i>-- tidak ada data --</i></td></tr>');
                    },
                    success: function(json) {
                        var htmlTRow = '', rrUpFlag = true, rrDownFlag = true;
                        for (var i = 0; i < json.rows.length; i++) {
                            htmlTRow = '<tr><td>' + json.rows[i].rrCode + '</td><td>' + json.rows[i].rrDate + '</td><td>' + json.rows[i].rrFrom + '</td><td>' + json.rows[i].wsCodes + '</td><td>' + json.rows[i].batchNumber + '</td><td>' + json.rows[i].createdBy + '</td><td>' + json.rows[i].createdDate + '</td><td><a class="ACTION_CLASS" href="javascript:void(0);"><img src="ACTION_IMAGE" width="16px"></a></td></tr>';

                            if (json.rows[i].approvedBy === undefined || json.rows[i].approvedBy === '') {
                                if (rrUpFlag) {
                                    rrUpFlag = false;
                                    $('#rrUp').html('');
                                }

                                htmlTRow = htmlTRow.replace('ACTION_CLASS', 'process');
                                htmlTRow = htmlTRow.replace('ACTION_IMAGE', 'resources/img/inv.png');
                                $('#rrUp').append(htmlTRow);
                            } else {
                                if (rrDownFlag) {
                                    rrDownFlag = false;
                                    $('#rrDown').html('');
                                }

                                htmlTRow = htmlTRow.replace('ACTION_CLASS', 'revision');
                                htmlTRow = htmlTRow.replace('ACTION_IMAGE', 'resources/images/edit.gif');
                                $('#rrDown').append(htmlTRow);
                            }
                        }
                    },
                    complete: function() {
                        $button.removeAttr('disabled').val('Search');
                    }
                });
            });
            
            $('#receivedBy').on('change', function() {
                $('input[id="rrContractPrice"]').each(function() { rrRecalculateDetail(this); });
                rrDetailGrandTotal();
            });

            $('#btnCancel').on('click', function() {
                $('.popup-box').fadeOut('fast');
                $('#btnDelete').hide();
                $('#isRevise').val(0);
            });

            $('#btnSubmit').on('click', function() {
                var isError = false, data = '';

                $('#rrDetailList tr').each(function() {
                    data = data + $('#receivedBy').val() + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(1)').data('id') + ':s:' + $(this).find('td:eq(4) > input').data('value') + ':s:' + $(this).find('td:eq(5)').data('value') + ':s:' + $(this).find('td:eq(6)').data('value') + ':s:' + $(this).find('td:eq(7) > input').val() + ':s:' + $(this).find('td:eq(8)').data('value') + ':s:' + $(this).find('td:eq(9)').data('value') + ':s::se:';
                });

                $(this).attr('disabled', 'disabled').css('cursor', 'wait');
                $.ajax({
                    url: '', type: 'post',
                    data: {action: 'doRRProcess', data: data, isRevise: $('#isRevise').val()},
                    dataType: 'json',
                    success: function(json) {
                        if (json.message !== undefined) {
                            isError = true;
                            alert("Server Error! Please contact IMS administrator.\nInformation: " + json.message);
                        }
                    },
                    error: function() {
                        isError = true;
                        alert('Server Error! Please contact IMS administrator.');
                    },
                    complete: function() {
                        if (!isError) {
                            $('#isRevise').val(0);

                            $('.popup-box').fadeOut('slow');
                            $('#btnSearch').trigger('click');
                        }
                        $('#btnSubmit').removeAttr('disabled').css('cursor', '');
                    }
                });
            });

            $('#btnDelete').on('click', function() {
                var isError = false;

                $(this).attr('disabled', 'disabled').css('cursor', 'wait');
                $.ajax({
                    url: '', type: 'post',
                    data: {action: 'doRRRemove', rrCode: $('#rrCode').text()},
                    dataType: 'json',
                    success: function(json) {
                        if (json.message !== undefined) {
                            isError = true;
                            alert("Server Error! Please contact IMS administrator.\nInformation: " + json.message);
                        }
                    },
                    error: function() {
                        isError = true;
                        alert('Server Error! Please contact IMS administrator.');
                    },
                    complete: function() {
                        if (!isError) {
                            $('#isRevise').val(0);

                            $('.popup-box').fadeOut('slow');
                            $('#btnSearch').trigger('click');
                        }
                        $('#btnDelete').removeAttr('disabled').css('cursor', '').hide();
                    }
                });
            });

            $('body').on('focus', '.format-separator-thousand', function() {
                $(this).val($(this).val().replace(/,/g, ''));
            });
            $('body').on('blur', '.format-separator-thousand', function() {
                $(this).data('value', $(this).val());
                $(this).val(parseFloat($(this).val()).kThousandFormat2(2));
            });

            $('body').on('click', '#rrUp .process', function() {
                var htmlTRow = $(this).parent().parent().find('td:eq(0)');
                if (confirm('Continue to process RR#' + htmlTRow.text())) {
                    $.ajax({
                        url: '', type: 'post',
                        data: {action: 'getRRDetail', rrCode: htmlTRow.text()},
                        dataType: 'json',
                        beforeSend: function() {
                            $('#rrDetailList').html('<tr><td colspan="10" class="center"><i>-- tidak ada data --</i></td></tr>');
                        },
                        success: function(json) {

                            if (json.rows.length > 0) {
                                $('*[id="rrCode"]').text(json.rows[0].rrCode);
                                $('#rrDate').text(json.rows[0].rrDate);
                                $('#rrFrom').text(json.rows[0].rrFrom);
                                $('#wsCodes').text(json.rows[0].wsCodes);
                                $('#batchNumber').text(json.rows[0].batchNumber);
                                $('#receivedBy').val(json.rows[0].receivedBy);

                                $('#rrDetailList').html('');
                                for (var i = 0; i < json.rows.length; i++) {
                                    $('#rrDetailList').append('<tr data-id="' + json.rows[i].rrId + '" data-quantity="' + json.rows[i].rrQuantity + '">' +
                                            '<td class="center" style="width: 50px">' + (i + 1) + '</td>' +
                                            '<td data-id="' + json.rows[i].fishId + '">' + json.rows[i].fishName + '</td>' +
                                            '<td class="right">' + json.rows[i].rrQuantity + '</td>' +
                                            '<td class="center">' + json.rows[i].rrUom + '</td>' +
                                            '<td class="center"><input id="rrContractPrice" class="right format-separator-thousand" type="text" value="' + parseFloat(json.rows[i].contractPrice).kThousandFormat2(2) + '" data-value="' + json.rows[i].contractPrice + '" size="7"></td>' +
                                            '<td class="right" id="rrUnitCostICore" data-value="' + json.rows[i].iCoreUnitCost + '">' + parseFloat(json.rows[i].iCoreUnitCost).kThousandFormat2(2) + '</td>' +
                                            '<td class="right" id="rrAmountICore">' + parseFloat(json.rows[i].iCoreAmount).kThousandFormat2(2) + '</td>' +
                                            '<td class="center"><input id="rrTaxRate" class="right" type="text" value="' + parseFloat(json.rows[i].taxRate).kThousandFormat2(4) + '" data-value="' + json.rows[i].taxRate + '" size="5"></td>' +
                                            '<td class="right" id="rrUnitCost" data-value="' + json.rows[i].accUnitCost + '">' + parseFloat(json.rows[i].accUnitCost).kThousandFormat2(2) + '</td>' +
                                            '<td class="right" id="rrAmount">' + parseFloat(json.rows[i].accAmount).kThousandFormat2(2) + '</td>' +
                                            '</tr>');
                                }
                                $('input[id="rrContractPrice"]').each(function() { rrRecalculateDetail(this); });
                                rrDetailGrandTotal();

                                $('.popup-box').fadeIn('slow');
                            } else {
                                alert("Please check your configuration below:\n- Fish Contract per supplier already encoded\n- Currency Rate has been set correctly for USD to IDR and OTHER_CURRENCY to USD\n- Make sure Currency Rate on DAILY BASE");
                            }
                        },
                        error: function() {
                            alert('Server Error! Please contact IMS administrator.');
                        }
                    });
                }
            });
            $('body').on('click', '#rrDown .revision', function() {
                var htmlTRow = $(this).parent().parent().find('td:eq(0)');
                if (confirm('Continue to revise RR#' + htmlTRow.text())) {
                    $.ajax({
                        url: '', type: 'post',
                        data: {action: 'getRRDetail', isRevise: 1, rrCode: htmlTRow.text()},
                        dataType: 'json',
                        beforeSend: function() {
                            $('#btnDelete').show();
                            $('#isRevise').val(1);
                            $('#rrDetailList').html('<tr><td colspan="10" class="center"><i>-- tidak ada data --</i></td></tr>');
                        },
                        success: function(json) {
                            if (json.rows.length > 0) {
                                $('*[id="rrCode"]').text(json.rows[0].rrCode);
                                $('#rrDate').text(json.rows[0].rrDate);
                                $('#rrFrom').text(json.rows[0].rrFrom);
                                $('#wsCodes').text(json.rows[0].wsCodes);
                                $('#batchNumber').text(json.rows[0].batchNumber);
                                $('#receivedBy').val(json.rows[0].receivedBy);

                                $('#rrDetailList').html('');
                                for (var i = 0; i < json.rows.length; i++) {
                                    $('#rrDetailList').append('<tr data-id="' + json.rows[i].rrId + '" data-quantity="' + json.rows[i].rrQuantity + '">' +
                                            '<td class="center" style="width: 50px">' + (i + 1) + '</td>' +
                                            '<td data-id="' + json.rows[i].fishId + '">' + json.rows[i].fishName + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].rrQuantity).kThousandFormat2(2) + '</td>' +
                                            '<td class="center">' + json.rows[i].rrUom + '</td>' +
                                            '<td class="center"><input id="rrContractPrice" class="right format-separator-thousand" type="text" value="' + parseFloat(json.rows[i].contractPrice).kThousandFormat2(2) + '" data-value="' + json.rows[i].contractPrice + '" size="7"></td>' +
                                            '<td class="right" id="rrUnitCostICore" data-value="' + json.rows[i].iCoreUnitCost + '">' + parseFloat(json.rows[i].iCoreUnitCost).kThousandFormat2(2) + '</td>' +
                                            '<td class="right" id="rrAmountICore">' + parseFloat(json.rows[i].iCoreAmount).kThousandFormat2(2) + '</td>' +
                                            '<td class="center"><input id="rrTaxRate" class="right" type="text" value="' + parseFloat(json.rows[i].taxRate).kThousandFormat2(4) + '" data-value="' + json.rows[i].taxRate + '" size="5"></td>' +
                                            '<td class="right" id="rrUnitCost" data-value="' + json.rows[i].accUnitCost + '">' + parseFloat(json.rows[i].accUnitCost).kThousandFormat2(2) + '</td>' +
                                            '<td class="right" id="rrAmount">' + parseFloat(json.rows[i].accAmount).kThousandFormat2(2) + '</td>' +
                                            '</tr>');
                                }
                                $('input[id="rrContractPrice"]').each(function() { rrRecalculateDetail(this); });
                                rrDetailGrandTotal();

                                $('.popup-box').fadeIn('slow');
                            } else {
                                alert("Please check your configuration below:\n- Fish Contract per supplier already encoded\n- Currency Rate has been set correctly for USD to IDR and OTHER_CURRENCY to USD\n- Make sure Currency Rate on DAILY BASE");
                            }
                        },
                        error: function() {
                            alert('Server Error! Please contact IMS administrator.');
                        }
                    });
                }
            });
            $('body').on('blur', 'input[id="rrUnitCost"]', function() {
                var htmlTRow = $(this).parent().parent();
                htmlTRow.find('#rrAmount').text(((parseFloat($(this).data('value'))) * (parseFloat(htmlTRow.data('quantity')))).kThousandFormat2(2));
                rrDetailGrandTotal();
            });
            $('body').on('blur', 'input[id="rrContractPrice"]', function() {
                $(this).data('value', $(this).val().replace(/,/, ''));
                rrRecalculateDetail(this);
                rrDetailGrandTotal();
            });
            $('body').on('blur', 'input[id="rrTaxRate"]', function() {
                rrRecalculateDetail(this);
                rrDetailGrandTotal();
            });

            function rrDetailGrandTotal() {
                var grandTotal = 0.00;
                $('#rrDetailList tr td:nth-child(10)').each(function() {
                    grandTotal = grandTotal + parseFloat($(this).text().replace(/,/g, ''));
                });
                $('#rrGrandTotal').text(grandTotal.kThousandFormat2(2));
            }

            function rrRecalculateDetail(_this) {
                var $row = (_this.nodeName === 'INPUT' ? $(_this).parent().parent() : $(_this).parent());
                
                var receivedBy = $('#receivedBy').val(),
                        quantity = $row.find('td:eq(2)').text().replace(/,/g, ''),
                        contractPrice = $row.find('td:eq(4) > input').data('value'),
                        $icUnitPrice = $row.find('td:eq(5)'),
                        $icAmount = $row.find('td:eq(6)'),
                        taxRate = $row.find('td:eq(7) > input').val(),
                        $accUnitPrice = $row.find('td:eq(8)'),
                        $accAmount = $row.find('td:eq(9)');

                if (receivedBy === 'Supplier') {
                    //iCore
                    $icUnitPrice.data('value', contractPrice * (1.0000 - taxRate)).text(parseFloat(contractPrice * (1.0000 - taxRate)).kThousandFormat(2));
                    $icAmount.data('value', $icUnitPrice.data('value') * quantity).text(parseFloat($icUnitPrice.data('value') * quantity).kThousandFormat(2));

                    // Accounting
                    $accUnitPrice.data('value', contractPrice).text(parseFloat(contractPrice).kThousandFormat(2));
                    $accAmount.data('value', contractPrice * quantity).text(parseFloat(contractPrice * quantity).kThousandFormat(2));
                } else {
                    //iCore
                    $icUnitPrice.data('value', contractPrice).text(parseFloat(contractPrice).kThousandFormat(2));
                    $icAmount.data('value', $icUnitPrice.data('value') * quantity).text(parseFloat($icUnitPrice.data('value') * quantity).kThousandFormat(2));

                    // Accounting
                    $accUnitPrice.data('value', contractPrice / (1.0000 - taxRate)).text(parseFloat(contractPrice / (1.0000 - taxRate)).kThousandFormat(2));
                    $accAmount.data('value', $accUnitPrice.data('value') * quantity).text(parseFloat($accUnitPrice.data('value') * quantity).kThousandFormat(2));
                }
            }
        </script>
    </body>
</html>
