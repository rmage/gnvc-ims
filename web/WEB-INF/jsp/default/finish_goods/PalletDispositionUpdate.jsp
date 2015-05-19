<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Pallet Disposition &therefore; IMS</title>
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
                    <form action="#" id="fPalletDisposition" name="fPalletDisposition" method="post">
                        <input type="hidden" id="qaId" name="qaId" value="${model.pds[0].qa_id}">
                        <input type="hidden" id="qaDate" name="qaDate">
                        <input type="hidden" id="evaDate" name="evaDate">
                        <input type="hidden" id="disDate" name="disDate">
                        <table class="collapse tblForm row-select">
                            <caption>Pallet Disposition &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">QA Number</td>
                                    <td><input type="text" id="qaCode" name="qaCode" value="${model.pds[0].qa_code}" required></td>
                                    <td>QA Date</td>
                                    <td><input type="text" id="qaDatePicker" name="qaDatePicker" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Evaluated Date</td>
                                    <td><input type="text" id="evaDatePicker" name="evaDatePicker" size="10"></td>
                                    <td>Disposition Date</td>
                                    <td><input type="text" id="disDatePicker" name="disDatePicker" size="10"></td>
                                </tr>
                                <tr>
                                    <td>Reason</td>
                                    <td><input type="text" id="qaReason" name="qaReason" size="50" value="${model.pds[0].qa_reason}"></td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="qaRemarks" name="qaRemarks" size="50" value="${model.pds[0].qa_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGPalletDisposition.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Pallet Disposition &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="7">
                                    <input type="text" id="ptsCode" name="ptsCode" value="${model.pds[0].pts_code}" data-id="${model.pds[0].pts_id}" readonly>
                                </th>
                            </tr>
                            <tr>
                                <td>Can Code</td>
                                <td>Production Date</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Item Name</td>
                                <td>Quantity</td>
                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.pds}" var="x">
                                <tr>
                                    <td>${x.pts_can_code}</td>
                                    <td>${x.pts_pdate}</td>
                                    <td>${x.pack_style}</td>
                                    <td>${x.pack_size}</td>
                                    <td>${x.item_name}</td>
                                    <td>${x.pts_pqty}</td>
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

            // BIND | date picker
            $("#qaDatePicker").val(gnvs.util.toViewDate('${model.pds[0].qa_date}'))
                    .datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#qaDate", changeYear: true, changeMonth: true });
            $("#qaDate").val(gnvs.util.toDBDate($("#qaDatePicker").val()));
            
            $("#evaDatePicker").val(gnvs.util.toViewDate('${model.pds[0].evaluated_date}'))
                    .datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#evaDate", changeYear: true, changeMonth: true });
            $("#evaDate").val(gnvs.util.toDBDate($("#evaDatePicker").val()));
            
            $("#disDatePicker").val(gnvs.util.toViewDate('${model.pds[0].disposition_date}'))
                    .datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#disDate", changeYear: true, changeMonth: true });
            $("#disDate").val(gnvs.util.toDBDate($("#disDatePicker").val()));

            // BIND | Save function
            $("#fPalletDisposition").bind("submit", function() {
                var data = '', header = $('#qaId').val() + ':s:' + $('#qaCode').val() + ':s:' + $('#qaDate').val() + ':s:' + $('#ptsCode').data('id') + ':s:' + $('#evaDate').val() + ':s:' + $('#disDate').val() + ':s:' + $('#qaReason').val() + ':s:' + $('#qaRemarks').val() + ':s:';

                if ($('#detail tr').length > 0) {
                    data = header + ':se:';
                }

                if (data !== '' && confirm('Update Pallet Disposition #' + $('#qaCode').val() + ' ?')) {
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

