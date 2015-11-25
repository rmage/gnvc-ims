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
        <title>Accounting &therefore; Non-Fish Receiving Report &therefore; IMS</title>
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
                                <th>PO Number</th>
                                <th>Supplier</th>
                                <th>Remarks</th>
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
                                <th>PO Number</th>
                                <th>Supplier</th>
                                <th>Remarks</th>
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
                                <td class="bold">Purchase Order Number</td>
                                <td id="poCode"></td>
                            </tr>
                            <tr>
                                <td class="bold">To</td>
                                <td>PT. Sinar Pure Foods International</td>
                                <td class="bold">Purchase Order Date</td>
                                <td id="poDate"></td>
                            </tr>
                            <tr>
                                <td class="bold">Remarks</td>
                                <td colspan="3" id="rrRemarks"></td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td colspan="4">
                                    <br>
                                    <table class="collapse tblForm row-select ui-widget-content">
                                        <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Item List</caption>
                                        <thead>
                                            <tr>
                                                <th class="center" rowspan="2">#</th>
                                                <th class="center" rowspan="2">Item Name</th>
                                                <th class="center" rowspan="2">Item Code</th>
                                                <th class="center" rowspan="2">Department</th>
                                                <th class="center" colspan="2">Quantity</th>
                                                <th class="center" rowspan="2">Unit of Measurement</th>
                                                <th class="center" rowspan="2">Unit Cost</th>
                                                <th class="center" rowspan="2">Amount</th>
                                            </tr>
                                            <tr>
                                                <th class="center">Good</th>
                                                <th class="center">Bad</th>
                                            </tr>
                                        </thead>
                                        <tbody id="rrDetailList"><tr><td colspan="9" class="center"><i>-- tidak ada data --</i></td></tr></tbody>
                                    </table>
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
                            htmlTRow = '<tr><td>' + json.rows[i].rrCode + '</td><td>' + json.rows[i].rrDate + '</td><td>' + json.rows[i].poCode + '</td><td>' + json.rows[i].rrFrom + '</td><td>' + json.rows[i].rrRemarks + '</td><td>' + json.rows[i].createdBy + '</td><td>' + json.rows[i].createdDate + '</td><td><a class="ACTION_CLASS" href="javascript:void(0);"><img src="ACTION_IMAGE" width="16px"></a></td></tr>';

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

            $('#btnCancel').on('click', function() {
                $('.popup-box').fadeOut('fast');
                $('#btnDelete').hide();
                $('#isRevise').val(0);
            });

            $('#btnSubmit').on('click', function() {
                var isError = false, data = '';

                $('#rrDetailList tr').each(function() {
                    data = data + $(this).data('id') + ':s:' + $(this).find('td:eq(2)').text() + ':s:' + $(this).find('td:eq(7) > input').data('value') + ':s:' + $(this).find('td:eq(8)').text().replace(/,/g, '') + ':s::se:';
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
                            $('#rrDetailList').html('<tr><td colspan="9" class="center"><i>-- tidak ada data --</i></td></tr>');
                        },
                        success: function(json) {
                            if (json.rows.length > 0) {
                                $('*[id="rrCode"]').text(json.rows[0].rrCode);
                                $('#rrDate').text(json.rows[0].rrDate);
                                $('#poCode').text(json.rows[0].poCode);
                                $('#poDate').text(json.rows[0].poDate);
                                $('#rrFrom').text(json.rows[0].rrFrom);
                                $('#rrRemarks').text(json.rows[0].rrRemarks);

                                $('#rrDetailList').html('');
                                for (var i = 0; i < json.rows.length; i++) {
                                    $('#rrDetailList').append('<tr data-id="' + json.rows[i].rrdId + '" data-quantity="' + (json.rows[i].rrQuantityGood + json.rows[i].rrQUantityBad) + '">' +
                                            '<td class="center" style="width: 50px">' + (i + 1) + '</td>' +
                                            '<td>' + json.rows[i].productName + '</td>' +
                                            '<td class="center">' + json.rows[i].productCode + '</td>' +
                                            '<td class="center">' + json.rows[i].departmentCode + '</td>' +
                                            '<td class="right">' + json.rows[i].rrQuantityGood + '</td>' +
                                            '<td class="right">' + json.rows[i].rrQUantityBad + '</td>' +
                                            '<td class="center">' + json.rows[i].rrUom + '</td>' +
                                            '<td class="center"><input id="rrUnitCost" class="right format-separator-thousand" type="text" value="' + parseFloat(json.rows[i].accUnitCost).kThousandFormat2(2) + '" data-value="' + json.rows[i].accUnitCost + '"></td>' +
                                            '<td class="right" id="rrAmount">' + parseFloat(json.rows[i].accAmount).kThousandFormat2(2) + '</td>' +
                                            '</tr>');
                                }

                                $('.popup-box').fadeIn('slow');
                            } else {
                                alert("Please check your configuration below:\n- Product Category has been correctly mapped to DAILY, WEEKLY, or MONTHLY Currency Type\n- Currency Rate has been set correctly");
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
                            $('#rrDetailList').html('<tr><td colspan="9" class="center"><i>-- tidak ada data --</i></td></tr>');
                        },
                        success: function(json) {
                            if (json.rows.length > 0) {
                                $('*[id="rrCode"]').text(json.rows[0].rrCode);
                                $('#rrDate').text(json.rows[0].rrDate);
                                $('#poCode').text(json.rows[0].poCode);
                                $('#poDate').text(json.rows[0].poDate);
                                $('#rrFrom').text(json.rows[0].rrFrom);
                                $('#rrRemarks').text(json.rows[0].rrRemarks);

                                $('#rrDetailList').html('');
                                for (var i = 0; i < json.rows.length; i++) {
                                    $('#rrDetailList').append('<tr data-id="' + json.rows[i].rrdId + '" data-quantity="' + (json.rows[i].rrQuantityGood + json.rows[i].rrQUantityBad) + '">' +
                                            '<td class="center" style="width: 50px">' + (i + 1) + '</td>' +
                                            '<td>' + json.rows[i].productName + '</td>' +
                                            '<td class="center">' + json.rows[i].productCode + '</td>' +
                                            '<td class="center">' + json.rows[i].departmentCode + '</td>' +
                                            '<td class="right">' + json.rows[i].rrQuantityGood + '</td>' +
                                            '<td class="right">' + json.rows[i].rrQUantityBad + '</td>' +
                                            '<td class="center">' + json.rows[i].rrUom + '</td>' +
                                            '<td class="center"><input id="rrUnitCost" class="right format-separator-thousand" type="text" value="' + parseFloat(json.rows[i].accUnitCost).kThousandFormat2(2) + '" data-value="' + json.rows[i].accUnitCost + '"></td>' +
                                            '<td class="right" id="rrAmount">' + parseFloat(json.rows[i].accAmount).kThousandFormat2(2) + '</td>' +
                                            '</tr>');
                                }

                                $('.popup-box').fadeIn('slow');
                            } else {
                                alert("Please check your configuration below:\n- Product Category has been correctly mapped to DAILY, WEEKLY, or MONTHLY Currency Type\n- Currency Rate has been set correctly");
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
            });
        </script>
    </body>
</html>
