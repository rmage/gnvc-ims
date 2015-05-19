<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Pallet Rejection &therefore; IMS</title>
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
                    <form action="#" id="fPalletRejection" name="fPalletRejection" method="post">
                        <input type="hidden" id="rejeDate" name="rejeDate">
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Rejection &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Rejection Number</td>
                                    <td><input type="text" id="rejeCode" name="rejeCode" value="${model.pds[0].reje_code}" readonly></td>
                                    <td>Rejection Date</td>
                                    <td><input type="text" id="rejeDatePicker" name="rejeDatePicker" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Rejection Issue</td>
                                    <td><input type="text" id="rejeIssue" name="rejeIssue" size="50" value="${model.pds[0].reje_issue}"></td>
                                    <td>Rejection Route</td>
                                    <td><input type="text" id="rejeRoute" name="rejeRoute" size="50" value="${model.pds[0].reje_route}"></td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td><input type="text" id="rejeCNumber" name="rejeCNumber" value="${model.pds[0].reje_cnumber}"></td>
                                    <td>Date</td>
                                    <td><input type="text" id="rejeCDate" name="rejeCDate" value="${model.pds[0].reje_cdate}"></td>
                                </tr>
                                <tr>
                                    <td>Type / Reason</td>
                                    <td>
                                        <select id="rejeCType" name="rejeCType">
                                            <option>CONDEMNATION</option>
                                            <option>RENDERING</option>
                                            <option>REPROCESS</option>
                                        </select>
                                        <input type="text" id="rejeCReason" name="rejeCReason" size="50" value="${model.pds[0].reje_ccause}">
                                    </td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="rejeCRemarks" name="rejeCRemarks" size="50" value="${model.pds[0].reje_cremarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletRejection.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>

                        <table class="collapse tblForm row-select" id="list">
                            <caption>Pallet Rejection &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <th>Pallet Number</th>
                                    <th colspan="7">
                                        <input type="text" id="ptsCode" name="ptsCode" value="${model.pds[0].pts_code}" data-id="${model.pds[0].pts_id}" readonly>
                                    </th>
                                </tr>
                                <tr>
                                    <td style="width: 100px">Pack Style</td>
                                    <td>Pack Size</td>
                                    <td>Item Code</td>
                                    <td>Production Date</td>
                                    <td>Quantity</td>
                                </tr>
                            </thead>
                            <tbody id="detail">
                                <c:forEach items="${model.pds}" var="x">
                                    <tr>
                                        <td>${x.pack_style}</td>
                                        <td>${x.pack_size}</td>
                                        <td>${x.item_name}</td>
                                        <td>${x.pts_pdate}</td>
                                        <td>${x.pts_pqty}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">Total Quantity: <span id="ptsTotalQty">${model.pds[0].pts_total_qty}</span></td>
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

            // BIND | date picker
            $("#rejeDatePicker")
                    .val(gnvs.util.toViewDate('${model.pds[0].reje_date}'))
                    .datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#rejeDate", changeYear: true, changeMonth: true });
            $('#rejeDate').val(gnvs.util.toDBDate($('#rejeDatePicker').val()));
            
            $('#rejeCType').val('${model.pds[0].reje_ctype}');

            // BIND | Save function
            $("#fPalletRejection").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                var data = $('#rejeCode').val() + ':s:' + $('#rejeDate').val() + ':s:' + $('#rejeIssue').val() + ':s:' +
                        $('#rejeRoute').val() + ':s:' + $('#rejeCNumber').val() + ':s:' + $('#rejeCDate').val() + ':s:' +
                        $('#ptsCode').data('id') + ':s:' + $('#rejeCType').val() + ':s:' + $('#rejeCReason').val() + ':s:' +
                        $('#rejeCRemarks').val() + ':s::se:';

                if (data !== '' && confirm('Update Pallet Rejection #' + $('#rejeCode').val() + ' ?')) {
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

