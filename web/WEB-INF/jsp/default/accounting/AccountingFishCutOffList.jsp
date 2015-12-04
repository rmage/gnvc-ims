<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounting &therefore; Fish Cut Off &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #cutOffDate, #currencyRate {
                font-size: 300%;
                font-weight: 600;
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
                <input id="storage" name="storage" type="hidden" value="%%">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Fish Cut Off &therefore; Header</caption>
                        <tbody>
                            <tr>
                                <td width="100px">Cut Off Date</td>
                                <td id="cutOffDate" data-value="${model.header.date_value}">${model.header.date_text ne null ? model.header.date_text : '<i>-- No Cut Off Date --</i>'}</td>
                                <td width="150px">Currency Rate (USD to IDR)</td>
                                <td id="currencyRate" data-value="${model.header.currency_rate}">${model.header.currency_rate gt 0 ? model.header.currency_rate : '<i>-- Not Set --</i>'}</td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="4">
                                    <input id="btnProcess" name="btnProcess" type="button" value="Check" disabled>
                                </td>
                            </tr>
                        </tfoot>
                    </table>

                    <table class="collapse tblForm row-select">
                        <caption>Fish Cut Off &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td class="bold center" rowspan="2">DESCRIPTION</td>
                                <td class="bold center" colspan="3">BEGINNING INVENTORY</td>
                                <td class="bold center" colspan="3">RECEIPT (WSNC)</td>
                                <td class="bold center" colspan="3">WITHDRAWAL (WLSC)</td>
                                <td class="bold center" colspan="3">ENDING INVENTORY</td>
                            </tr>
                            <tr>
                                <td>Quantity</td>
                                <td>Price</td>
                                <td>Amount</td>
                                <td>Quantity</td>
                                <td>Price</td>
                                <td>Amount</td>
                                <td>Quantity</td>
                                <td>Price</td>
                                <td>Amount</td>
                                <td>Quantity</td>
                                <td>Price</td>
                                <td>Amount</td>
                            </tr>
                        </thead>
                        <tbody id="dataDetail">
                            <tr><td class="center" colspan="13"><i>-- no data --</i></td></tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td>G R A N D  -  T O T A L</td>
                                <td class="bold right" id="bi_quantity">-</td>
                                <td class="bold right" id="bi_price">-</td>
                                <td class="bold right" id="bi_amount">-</td>
                                <td class="bold right" id="r_quantity">-</td>
                                <td class="bold right" id="r_price">-</td>
                                <td class="bold right" id="r_amount">-</td>
                                <td class="bold right" id="w_quantity">-</td>
                                <td class="bold right" id="w_price">-</td>
                                <td class="bold right" id="w_amount">-</td>
                                <td class="bold right" id="ei_quantity">-</td>
                                <td class="bold right" id="ei_price">-</td>
                                <td class="bold right" id="ei_amount">-</td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            if ($('#cutOffDate').data('value') !== '' && parseFloat($('#currencyRate').data('value')) > 0) {
                $('#btnProcess').removeAttr('disabled');
            } else {
                $('#btnProcess').attr('disabled', 'disabled');
            }

            if (parseFloat($('#currencyRate').text()) > 0) {
                $('#currencyRate').text(parseFloat($('#currencyRate').text()).kThousandFormat2(2));
            }

            $('#btnProcess').on('click', function() {
                var $this = $(this);

                if ($this.val() === 'Check') {
                    $.ajax({
                        url: '', type: 'post',
                        data: {action: 'getDetailData', dateFrom: $('#cutOffDate').data('value').substr(0, 8) + '01', dateTo: $('#cutOffDate').data('value'), storage: $('#storage').val(), currencyRate: $('#currencyRate').data('value')},
                        dataType: 'json',
                        beforeSend: function() {
                            $this.attr('disabled', 'disabled').val('Checking....');
                            $('#dataDetail').html('<tr><td class="center" colspan="13"><i>-- no data --</i></td></tr>');
                        },
                        success: function(json) {
                            if (json.rows !== undefined && json.rows.length > 0) {
                                var biQuantity = 0.00, biAmount = 0.00,
                                        rQuantity = 0.00, rAmount = 0.00,
                                        wQuantity = 0.00, wAmount = 0.00,
                                        eiQuantity = 0.00, eiAmount = 0.00;

                                $('#dataDetail').text('');
                                for (var i = 0; i < json.rows.length; i++) {
                                    $('#dataDetail').append('<tr>' +
                                            '<td>' + json.rows[i].fish_name + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].bi_quantity).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].bi_price).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + (parseFloat(json.rows[i].bi_quantity) * parseFloat(json.rows[i].bi_price)).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].r_quantity).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].r_price).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + (parseFloat(json.rows[i].r_quantity) * parseFloat(json.rows[i].r_price)).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].w_quantity).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].w_price).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + (parseFloat(json.rows[i].w_quantity) * parseFloat(json.rows[i].w_price)).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].ei_quantity).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + parseFloat(json.rows[i].ei_price).kThousandFormat2(2) + '</td>' +
                                            '<td class="right">' + (parseFloat(json.rows[i].ei_quantity) * parseFloat(json.rows[i].ei_price)).kThousandFormat2(2) + '</td>' +
                                            '</tr>');

                                    biQuantity = biQuantity + parseFloat(json.rows[i].bi_quantity);
                                    rQuantity = rQuantity + parseFloat(json.rows[i].r_quantity);
                                    wQuantity = wQuantity + parseFloat(json.rows[i].w_quantity);
                                    eiQuantity = eiQuantity + parseFloat(json.rows[i].ei_quantity);

                                    biAmount = biAmount + parseFloat(json.rows[i].bi_quantity) * parseFloat(json.rows[i].bi_price);
                                    rAmount = rAmount + parseFloat(json.rows[i].r_quantity) * parseFloat(json.rows[i].r_price);
                                    wAmount = wAmount + parseFloat(json.rows[i].w_quantity) * parseFloat(json.rows[i].w_price);
                                    eiAmount = eiAmount + parseFloat(json.rows[i].ei_quantity) * parseFloat(json.rows[i].ei_price);
                                }

                                $('#bi_quantity').text(biQuantity.kThousandFormat2(2));
                                $('#r_quantity').text(rQuantity.kThousandFormat2(2));
                                $('#w_quantity').text(wQuantity.kThousandFormat2(2));
                                $('#ei_quantity').text(eiQuantity.kThousandFormat2(2));

                                $('#bi_price').text((biQuantity > 0 ? (biAmount / biQuantity) : 0).kThousandFormat2(2));
                                $('#r_price').text((rQuantity > 0 ? (rAmount / rQuantity) : 0).kThousandFormat2(2));
                                $('#w_price').text((wQuantity > 0 ? (wAmount / wQuantity) : 0).kThousandFormat2(2));
                                $('#ei_price').text((eiQuantity > 0 ? (eiAmount / eiQuantity) : 0).kThousandFormat2(2));

                                $('#bi_amount').text(biAmount.kThousandFormat2(2));
                                $('#r_amount').text(rAmount.kThousandFormat2(2));
                                $('#w_amount').text(wAmount.kThousandFormat2(2));
                                $('#ei_amount').text(eiAmount.kThousandFormat2(2));

                                $this.val('Cut Off!');
                            } else {
                                $this.val('Check');
                            }
                        },
                        complete: function() {
                            $this.removeAttr('disabled');
                        }
                    });
                } else if ($this.val() === 'Cut Off!') {
                    if (confirm('***ATTENTION*** CONTINUINING CUT OFF PROCESS MEANS YOU AGREE THE FOLLOWING DATA ARE CORRECT AND CAN NOT BE MODIFIED ON THE FUTURE!!!')) {
                        gnvs.ajaxCall({action: 'saveDetailData', dateFrom: $('#cutOffDate').data('value').substr(0, 8) + '01', dateTo: $('#cutOffDate').data('value'), storage: $('#storage').val(), currencyRate: $('#currencyRate').data('value')}, function(json) {
                            if (json.message === '') {
                                location.href = 'AccFishCutOff.htm';
                            } else {
                                alert(json.message);
                            }
                        });
                    }
                }
            });
        </script>
    </body>
</html>
