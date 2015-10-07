<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Export Delivery &therefore; IMS</title>
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
                    <form action="#" id="fExport" name="fExport" method="post">
                        <input id="edsId" name="edsId" type="hidden" value="${model.edss[0].eds_id}">
                        <input type="hidden" id="edsDate" name="edsDate" value="">
                        <table class="collapse tblForm row-select">
                            <caption>Export Delivery &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">EDS Number</td>
                                    <td><input type="text" id="edsCode" name="edsCode" value="${model.edss[0].eds_code}" required></td>
                                    <td style="width: 200px;">EDS Date</td>
                                    <td><input type="text" id="edsDatePicker" name="edsDatePicker" value="" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Container Number</td>
                                    <td><input type="text" id="edsVan" name="edsVan" size="50" value="${model.edss[0].eds_van}"></td>
                                    <td>Seal Number</td>
                                    <td><input type="text" id="edsPelayaranSeal" name="edsPelayaranSeal" size="50" value="${model.edss[0].eds_pelayaranseal}"></td>
                                </tr>
                                <tr>
                                    <td>Vessel</td>
                                    <td><input type="text" id="edsVessel" name="edsVessel" size="50" value="${model.edss[0].eds_vessel}"></td>
                                    <td>Plat Number</td>
                                    <td><input type="text" id="edsPlatNo" name="edsPlatNo" size="50" value="${model.edss[0].eds_platno}"></td>
                                </tr>
                                <tr>
                                    <td>Time</td>
                                    <td>
                                        In: <input type="text" id="edsTimeIn" name="edsTimeIn" size="5" value="${model.edss[0].eds_timein}">
                                        Out: <input type="text" id="edsTimeOut" name="edsTimeOut" size="5" value="${model.edss[0].eds_timeout}">
                                    </td>
                                    <td>Name of Driver</td>
                                    <td><input type="text" id="edsDriverName" name="edsDriverName" size="50" value="${model.edss[0].eds_drivername}"></td>
                                </tr>
                                <tr>
                                    <td>CI Number</td>
                                    <td><input type="text" id="edsCi" name="edsCi" value="${model.edss[0].eds_ci}"></td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="edsRemarks" name="edsRemarks" size="50" value="${model.edss[0].eds_remarks}"></td>
                                </tr>
                                <tr>
                                    <td>LM Number</td>
                                    <td colspan="3">
                                        <input type="text" id="lmrCode" name="lmrCode" class="lmr-info" value="${model.edss[0].lmr_code}" data-id="${model.edss[0].lmr_id}" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Buyer</td>
                                    <td><input type="text" class="lmr-info" size="50" value="${model.edss[0].buyer_name}" readonly></td>
                                    <td>Brand</td>
                                    <td><input type="text" class="lmr-info" size="50" value="${model.edss[0].brand_name}" readonly></td>
                                </tr>
                                <tr>
                                    <td>Reference</td>
                                    <td><input type="text" class="lmr-info" size="50" value="${model.edss[0].bor_reference}" readonly></td>
                                    <td>Destination</td>
                                    <td><input type="text" class="lmr-info" size="50" value="${model.edss[0].bor_destination}" readonly></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGExportDelivery.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Export Delivery &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td>Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Can Code</td>
                                <td>Number of Cases</td>
                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.edss}" var="x">
                                <tr>
                                    <td>${x.number}</td>
                                    <td>${x.pack_style}</td>
                                    <td>${x.pack_size}</td>
                                    <td>${x.pts_cancode}</td>
                                    <td>${x.lmr_labeled}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
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

            // BIND | Date Picker to ofal date
            $("#edsDatePicker").val(gnvs.util.toViewDate('${model.edss[0].eds_date}')).datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#edsDate", changeYear: true, changeMonth: true });
            $('#edsDate').val(gnvs.util.toDBDate($('#edsDatePicker').val()));
            
            // BIND | Save function
            $("#fExport").bind("submit", function() {
                var data = '', header = $('#edsId').val() + ':s:' + $('#edsCode').val() + ':s:' + $('#lmrCode').data('id') + ':s:' + $('#edsDate').val() + ':s:' + $('#edsVan').val() + ':s:' + $('#edsPelayaranSeal').val() + ':s:' + $('#edsVessel').val() + ':s:' + $('#edsPlatNo').val() + ':s:' + $('#edsTimeIn').val() + ':s:' + $('#edsTimeOut').val() + ':s:' + $('#edsDriverName').val() + ':s:' + $('#edsCi').val() + ':s:' + $('#edsRemarks').val() + ':s:';

                if ($('#detail tr').length > 0) {
                    data = header + ':se:';
                }

                if (confirm('Update Export Delivery #' + $('#edsCode').val() + ' ?')) {
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

        </script>
    </body>
</html>

