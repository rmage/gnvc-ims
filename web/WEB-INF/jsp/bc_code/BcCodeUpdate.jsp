<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; BC Code &therefore; IMS</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- include file header HERE -->
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="BcCode.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="bcForm" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Transaction &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Module</td>
                                    <td colspan="4">
                                        <select id="moduleCategory" name="moduleCategory">                                       
                                            <c:if test="${model.bc_code[0].module == 'nfRR'}"><option value="nfRR">Non Fish - Receiving Report</option></c:if>
                                            <c:if test="${model.bc_code[0].module == 'fishRR'}"><option value="fishRR">Fish - Receiving Report</option></c:if>
                                            <c:if test="${model.bc_code[0].module == 'fgEDS'}"><option value="fgEDS">Finish Goods - Export Delivery Slip</option></c:if>
    <!--                                        <c:if test="${model.bc_code[0].module == 'fgDR'}"><option value="fgDR">Finish Goods - Delivery Report</option></c:if>                                        -->
                                            <c:if test="${model.bc_code[0].module == 'fgPTS'}"><option value="fgPTS">Finish Goods - Pallet Transfer Slip</option></c:if>
                                            <c:if test="${model.bc_code[0].module == 'rmDR'}"><option value="rmDR">Rendering Module - Delivery Report</option></c:if>                                        
                                            <c:if test="${model.bc_code[0].module == 'rawTsMU'}"><option value="rawTsMU">TS - Raw Material Used</option></c:if>
                                            <c:if test="${model.bc_code[0].module == 'rawWdsMU'}"><option value="rawWdsMU">WDS - Raw Material Used</option></c:if>
                                         </select>
                                            <label> Trx No </label>
                                            <input id="trxNo" name="trxNo" size="20" type="text" value="${model.bc_code[0].trx_no}" />
                                        <input id="btnClear" type="button" value="Back" name="btnClear" />
                                    </td>
                                <tr>
                                    <td><label> Trx Date </label></td>
                                    <td colspan="4">
                                        <input id="trxDate" name="trxDate" size="20" type="text" disabled="true" />
                                    </td>
                                </tr>
                                </tr>
                                <tr>
                                    <td rowspan="2">Item Code</td>
                                    <td rowspan="2">Item Name</td> 
                                    <td rowspan="2">Qty</td>                                
                                    <td rowspan="2">Hs Code</td>
                                    <td rowspan="2">Harga per Satuan</td>                                
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
                        </table>
                        <table class="collapse tblForm">
                            <caption>Bc Code &therefore;</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 137px">No Pesanan Penjualan</td>
                                    <td>
                                        <input id="pesanNo" name="pesanNo" type="text" size="30" value="${model.bc_code[0].pesanan_no}" />
                                    </td>
                                    <td style="width: 137px">Tanggal Pesanan Penjualan</td>
                                    <td>
                                        <input id="pesanTgl" name="pesanTgl" type="text" size="30" />
                                    </td>
                                </tr>   
                                <tr>
                                    <td style="width: 137px">Pajak</td>
                                    <td>
                                        <input id="pajak" name="pajak" type="text" size="30" value="${model.bc_code[0].pajak}" />
                                    </td>
                                    <td style="width: 137px">No BC</td>
                                    <td>
                                        <select id="bcNo" name="bcNo" required>
                                            <option value="${model.bc_code[0].bc_no}">${model.bc_code[0].bc_no}</option>
                                        </select>
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Presentase Pajak</td>
                                    <td>
                                        <input id="presenPajak" name="presenPajak" type="text" size="30" value="${model.bc_code[0].presentase_pajak}" />
                                    </td>
                                    <td style="width: 137px">Bc Date</td>
                                    <td>
                                        <input id="bcDate" name="bcDate" type="text" size="30" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">No Seri</td>
                                    <td>
                                        <input id="seriNo" name="seriNo" type="text" size="30" value="${model.bc_code[0].seri_no}" />
                                    </td>
                                    <td style="width: 137px">No Registration</td>
                                    <td>
                                        <input id="regNo" name="regNo" type="text" size="30" required="true" value="${model.bc_code[0].reg_no}" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">BEA</td>
                                    <td>
                                        <input id="bea" name="bea" type="text" size="30" value="${model.bc_code[0].bea}" />
                                    </td>
                                    <td style="width: 137px">Presentase BEA</td>
                                    <td>
                                        <input id="presenBea" name="presenBea" type="text" size="30" value="${model.bc_code[0].presentase_bea}" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Valuta</td>
                                    <td>
                                        <input id="valuta" name="valuta" type="text" size="30" required="true" value="${model.bc_code[0].valuta}" />
                                    </td>
                                    <td style="width: 137px">Currency</td>
                                    <td>
                                        <select id="currency" name="currency">
                                            <option value="${model.bc_code[0].currency}">${model.bc_code[0].currency}</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Keterangan</td>
                                    <td>
                                        <input id="ketrgn" name="ketrgn" type="text" size="30" value="${model.bc_code[0].descrpt}" />
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>                        
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="button" value="Update" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('BcCode.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
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
            $('#bcDate').val(gnvs.util.toViewDate('${model.bc_code[0].bc_date}'));
            $('#pesanTgl').val('${model.bc_code[0].tgl_pesanan}' === '' ? '' : gnvs.util.toViewDate('${model.bc_code[0].tgl_pesanan}'));

            /* BIND | element event */
            $('#bcDate').datepicker({
                dateFormat: 'dd/mm/yy',
                altFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true
            });

            $('#pesanTgl').datepicker({
                dateFormat: 'dd/mm/yy',
                altFormat: 'yy-mm-dd',
                changeMonth: true,
                changeYear: true
            });

            $('#save').bind('click', function() {
                var data = $('#moduleCategory').val() + ':s:' + $('#pesanNo').val() + ':s:' + gnvs.util.toDBDate($('#pesanTgl').val()) + ':s:' + $('#srjalanNo').val() + ':s:' +
                        $('#trxNo').val() + ':s:' + $('#bcNo').val() + ':s:' + gnvs.util.toDBDate($('#bcDate').val()) + ':s:' + $('#regNo').val() + ':s:' + $('#pajak').val() + ':s:' +
                        $('#presenPajak').val() + ':s:' + $('#seriNo').val() + ':s:' + $('#bea').val() + ':s:' + $('#presenBea').val() + ':s:' + $('#priceSat').val() + ':s:' +
                        $('#valuta').val() + ':s:' + $('#currency').val() + ':s:' + $('#ketrgn').val() + ':s::se:';

                console.log(data);
                gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                    if (json.message === '') {
                        $('#btnCancel').trigger('click');
                    } else {
                        alert(json.message);
                    }
                });

                return false;
            });

            $('#btnClear').click(function() {
                location.href = "BcCode.htm";
            });

            function getListItem() {
                if (!$('#trxNo').val())
                    return;

                var $o = $(this);
                $o.attr('disabled', true);
                $('#trxNo').attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                $('#main').html(null);
                $.ajax({
                    url: 'BcCode.htm',
                    data: {action: 'getTrxNo', key: $('#trxNo').val(), cat: $('#moduleCategory').val(), mode: 0},
                    dataType: 'json',
                    success: function(json) {
                        if (json.rows.length > 0) {
                            $('#trxDate').val(gnvs.util.toViewDate(json.rows[0].tanggal));
                        }
                        for (var i = 0; i < json.rows.length; i++) {
                            $('#main').append('<tr><td>' + json.rows[i].prodCode + '</td><td>' + json.rows[i].prodName + '</td><td>'
                                    + json.rows[i].qty + '</td><td>' + json.rows[i].hs_code + '</td><td>' + json.rows[i].harga_per_satuan + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        $('#trxNo').attr('disabled', false);
                    }
                });
            }
            getListItem();

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>
