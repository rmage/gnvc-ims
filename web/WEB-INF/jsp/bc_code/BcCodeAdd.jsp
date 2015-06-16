<%@page import="java.util.HashMap"%>
<%@page import="com.app.wms.engine.db.dto.map.LoginUser"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create &therefore; BC Code &therefore; IMS</title>
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
                                            <option value="empty">Select Module</option>                                            
                                            <option value="nfRR">Non Fish - Receiving Report</option>
                                            <option value="fishRR">Fish - Receiving Report</option>
                                            <option value="fgEDS">Finish Goods - Export Delivery Slip</option>
                                            <!--                                        <option value="fgDR">Finish Goods - Delivery Report</option>                                        -->
                                            <!--                                        <option value="fgPTS">Finish Goods - Pallet Transfer Slip</option>-->
                                            <option value="rmDR">Rendering Module - Delivery Report</option>
                                            <!--                                        <option value="rawTsMU">TS/WDS - Raw Material Used</option>-->
                                            <!--                                        <option value="rawWdsMU">WDS - Raw Material Used</option>-->
                                        </select>
                                        <label> Trx No </label>
                                        <input id="trxNo" name="trxNo" size="20" type="text" />
                                        <input id = "btnSearchTrx" type="submit" value="Search" name="btnSearchTrx" />
                                        <input id="btnClear" type="button" value="Cancel" name="btnClear" />
                                    </td>
                                <tr>
                                    <td><label> Trx Date </label></td>
                                    <td colspan="4">
                                        <input id="trxDate" name="trxDate" size="10" type="text" disabled="true" />
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
                        <table id="bcDetail" class="collapse tblForm" hidden="">
                            <caption>Bc Code &therefore;</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 137px">No Pesanan Penjualan</td>
                                    <td>
                                        <input id="pesanNo" name="pesanNo" type="text" size="30" />
                                    </td>
                                    <td style="width: 137px">Tanggal Pesanan Penjualan</td>
                                    <td>
                                        <input id="pesanTgl" name="pesanTgl" type="text" size="30" />
                                    </td>
                                </tr>   
                                <tr>
                                    <td style="width: 137px">Pajak</td>
                                    <td>
                                        <input id="pajak" name="pajak" type="text" size="30" />
                                    </td>
                                    <td style="width: 137px">No BC</td>
                                    <td>
                                        <select id="bcNo" name="bcNo" required="true">
                                            <option value="0">Select No BC</option>
                                            <c:forEach items="${model.pc}" var="x">
                                                <option value="${x.code_bc}">${x.name_bc}</option>
                                            </c:forEach>
                                        </select>
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Presentase Pajak</td>
                                    <td>
                                        <input id="presenPajak" name="presenPajak" type="text" size="30" />
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
                                        <input id="seriNo" name="seriNo" type="text" size="30" />
                                    </td>
                                    <td style="width: 137px">No Registration</td>
                                    <td>
                                        <input id="regNo" name="regNo" type="text" size="30" required="true" />
                                        <span class="requiredfield">*</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">BEA</td>
                                    <td>
                                        <input id="bea" name="bea" type="text" size="30" value="Bebas Bea" />
                                    </td>
                                    <td style="width: 137px">Presentase BEA</td>
                                    <td>
                                        <input id="presenBea" name="presenBea" type="text" size="30" />
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Valuta</td>
                                    <td>
                                        <input id="valuta" name="valuta" type="text" size="30" value="0" />
                                    </td>
                                    <td style="width: 137px">Currency</td>
                                    <td>
                                        <select id="currency" name="currency">                                            
                                            <option value="USD">USD</option>                                            
                                            <option value="IDR">IDR</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 137px">Keterangan</td>
                                    <td>
                                        <input id="ketrgn" name="ketrgn" type="text" size="30" />
                                    </td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
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
            $('#bcDate').datepicker({dateFormat: "dd/mm/yy"}).datepicker("setDate", new Date());
            $('#pesanTgl').datepicker({dateFormat: "dd/mm/yy"}).datepicker();

            $('#bcForm').bind('submit', function() {
                var data = $('#moduleCategory').val() + ':s:' + $('#pesanNo').val() + ':s:' + ($('#pesanTgl').val() === '' ? '' : gnvs.util.toDBDate($('#pesanTgl').val())) + ':s:' + $('#srjalanNo').val() + ':s:' +
                        $('#trxNo').val() + ':s:' + $('#bcNo').val() + ':s:' + gnvs.util.toDBDate($('#bcDate').val()) + ':s:' + $('#regNo').val() + ':s:' + $('#pajak').val() + ':s:' +
                        $('#presenPajak').val() + ':s:' + $('#seriNo').val() + ':s:' + $('#bea').val() + ':s:' + $('#presenBea').val() + ':s:' + $('#valuta').val() + ':s:' + $('#currency').val() + ':s:' +
                        $('#ketrgn').val() + ':s::se:';

                console.log(data);
                gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
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

            $('#btnSearchTrx').bind('click', function() {
                if (!$('#trxNo').val())
                    return;

                var $o = $(this);
                $o.attr('disabled', true);
                $('#trxNo').attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                $('#main').html(null);
                $.ajax({
                    url: 'BcCode.htm',
                    data: {action: 'getTrxNo', key: $('#trxNo').val(), cat: $('#moduleCategory').val(), mode: 1}, //mode adalah untuk menampilkan list item code.
                    dataType: 'json',
                    success: function(json) {
                        if (json.rows.length > 0) {
                            $('#trxDate').val(gnvs.util.toViewDate(json.rows[0].tanggal));
                        } else if (json.rows.length === 0) {
                            alert('Nomor Trx Ini Sudah Teregistrasi');
                        }
                        for (var i = 0; i < json.rows.length; i++) {
                            $('#main').append('<tr><td>' + json.rows[i].prodCode + '</td><td>' + json.rows[i].prodName + '</td><td>'
                                    + json.rows[i].qty + '</td><td>' + json.rows[i].hs_code + '</td><td>' + json.rows[i].harga_per_satuan + '</td></tr>');
                            $('#bcDetail').show();
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        $('#trxNo').attr('disabled', false);
                    }
                });

                return false;
            });

            $('#trxNo').focus(function() {
                $('#main').html(null);
                $('#trxDate').val('');
                $('#bcDetail').hide();
                $('#pesanNo').val('');
                $('#pesanTgl').val('');
                $('#srjalanNo').val('');
                $('#bcNo').val('');
                $('#pajak').val('');
                $('#presenPajak').val('');
                $('#regNo').val('');
                $('#seriNo').val('');
                $('#presenBea').val('0%');
                $('#bea').val('Bebas Bea');
                $('#currency').val('');
                $('#valuta').val('0');
                $('#ketrgn').val('');
            });

            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>


<!--CATATAN :
        + trx_no tidak boleh sama
        + no bc diganti jenis dokumen
        - valuta = insert valuta manual * harga po
        - jumlah -> qty di rr_detail
        - search di halaman depan by nomer transaction-->