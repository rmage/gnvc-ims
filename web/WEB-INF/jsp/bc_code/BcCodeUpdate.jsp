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
                    <table class="collapse tblForm row-select">
                        <caption>Transaction &therefore; Search</caption>
                        <thead>
                            <tr>
                                <td style="width: 100px;">Transaction</td>
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
                            </tr>
                            <tr>
                                <td style="background: none;">Information</td>
                                <td colspan="3" id="sumPlaceholder" style="background: none;"></td>
                            </tr>
                            <tr>
                                <td>Item Code</td>
                                <td>Item Name</td> 
                                <td>Qty</td>                                
                                <td>iCore Code</td>
                                <!--<td rowspan="2">Harga per Satuan</td>-->                                
                            </tr>
                        </thead>
                        <tbody class="tbl-nohover" id="main"></tbody>
                    </table>

                    <c:if test="${model.bc_code[0].bc_no == 'BC 2.3'}">
                        <div id="bcDetail">
                            <ul>
                                <li><a href="#tabs-bc23" id="nav-bc23">BC 2.3</a></li>
                            </ul>
                            <div id="tabs-bc23">
                                <form action="#" class="bcForm" id="bcForm23" name="bcForm23" method="get" autocomplete="off">
                                    <input type="hidden" id="bcNo" name="bcNo" value="BC 2.3">
                                    <input type="hidden" id="srjalanNo" name="srjalanNo">
                                    <input type="hidden" id="valuta" name="valuta">

                                    <input type="hidden" id="pajak" name="pajak">
                                    <input type="hidden" id="presenPajak" name="presenPajak">
                                    <input type="hidden" id="seriNo" name="seriNo">
                                    <input type="hidden" id="currency" name="currency">
                                    <input type="hidden" id="ketrgn" name="ketrgn">

                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>NOMOR DOKUMEN PABEAN</td>
                                                <td>:
                                                    <input id="regNo" name="regNo" type="text" size="30" value="${model.bc_code[0].reg_no}" required>
                                                    <span class="requiredfield">*</span>
                                                </td>

                                                <td>BEA</td>
                                                <td>: <input id="bea" name="bea" type="text" size="30" value="${model.bc_code[0].bea}"></td>

                                                <td>NOMOR PESANAN PENJUALAN</td>
                                                <td>: <input id="pesanNo" name="pesanNo" type="text" size="30" value="${model.bc_code[0].pesanan_no}"></td>
                                            </tr>
                                            <tr>
                                                <td>TGL DOKUMEN PABEAN</td>
                                                <td>:
                                                    <input id="bcDate" name="bcDate" type="text" size="30" required> 
                                                    <span class="requiredfield">*</span>
                                                </td>

                                                <td>PERSENTASE BEA</td>
                                                <td>: <input id="presenBea" name="presenBea" type="text" size="30" value="${model.bc_code[0].presentase_bea}"></td>

                                                <td>TANGGAL PESANAN PENJUALAN</td>
                                                <td>: <input id="pesanTgl" name="pesanTgl" type="text" size="30"></td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <td colspan="6">
                                                    <input id="save" type="submit" value="Update" />
                                                    <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('BcCode.htm');" />
                                                </td>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <script>
            $('#bcDetail').tabs();
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
                var data = $('#moduleCategory').val() + ':s:' + 
                        $('#pesanNo').val() + ':s:' + 
                        gnvs.util.toDBDate($('#pesanTgl').val()) + ':s:' + 
                        $('#srjalanNo').val() + ':s:' +
                        $('#trxNo').val() + ':s:' + 
                        $('#bcNo').val() + ':s:' + 
                        gnvs.util.toDBDate($('#bcDate').val()) + ':s:' + 
                        $('#regNo').val() + ':s:' + 
                        $('#pajak').val() + ':s:' +
                        $('#presenPajak').val() + ':s:' + 
                        $('#seriNo').val() + ':s:' + 
                        $('#bea').val() + ':s:' + 
                        $('#presenBea').val() + ':s:' + 
//                        $('#priceSat').val() + ':s:' +
                        $('#valuta').val() + ':s:' + 
                        $('#currency').val() + ':s:' + 
                        $('#ketrgn').val() + ':s:${param.key}:s::se:';

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
                            $('#sumDocumentNumber').text($('#trxNo').val());
                            $('#sumDocumentDate').text(gnvs.util.toViewDate(json.rows[0].tanggal));
                            $('#sumDocumentSupplier').text(json.rows[0].supplier);
                            $('#sumDocumentValuta').text(json.rows[0].valuta);

                            // BC Form
            <c:if test="${model.bc_code[0].bc_no == 'BC 2.3'}">
                            $('#tabs-bc23').find('#srjalanNo').val($('#trxNo').val());
                            $('#tabs-bc23').find('#valuta').val(json.rows[0].valuta);
            </c:if>
                        }

                        for (var i = 0; i < json.rows.length; i++) {
                            $('#main').append('<tr><td>' + json.rows[i].prodCode + '</td><td>' + json.rows[i].prodName + '</td><td>'
                                    + json.rows[i].qty + '</td><td>' + json.rows[i].hs_code + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        $('#trxNo').attr('disabled', false);
                    }
                });
            }

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/B(?=(d{3})+(?!d))/g, ",");
                return parts.join(".");
            }

            // added by : FYA
            setTimeout(function() {
            <c:if test="${model.bc_code[0].module == 'nfRR'}">$('#sumPlaceholder').html('<table style="margin-bottom: 0px;"><tbody><tr><td style="width: 125px;">NOMOR SURAT JALAN</td><td style="">: <span id="sumDocumentNumber"></span></td><td style="width: 125px;">SUPPLIER</td><td>: <span id="sumDocumentSupplier"></span></td></tr><tr><td>TANGGAL PEMASUKAN</td><td>: <span id="sumDocumentDate"></span></td><td>VALUTA</td><td>: <span id="sumDocumentValuta"></span></td></tr></tbody></table>');</c:if>

                    getListItem();
                }, 600);
        </script>
    </body>
</html>
