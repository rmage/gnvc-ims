<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update &therefore; Return Cargo &therefore; IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker {
                display: none;
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
                    <form action="#" id="fReturnCargo" name="fReturnCargo" method="post">
                        <input type="hidden" id="rrDate" name="rrDate">
                        <table class="collapse tblForm row-select">
                            <caption>Return Cargo &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Return Cargo Number</td>
                                    <td><input type="text" id="rrCode" name="rrCode" value="${model.rcs[0].rr_code}" readonly></td>
                                    <td>Return Cargo Date</td>
                                    <td><input type="text" id="rrDatePicker" name="rrDatePicker" size="10" required readonly></td>
                                </tr>
                                <tr>
                                    <td>EDS Number</td>
                                    <td>
                                        <input type="text" id="edsCode" name="edsCode" size="10" value="${model.rcs[0].eds_code}" data-id="${model.rcs[0].eds_id}" readonly>
                                    </td>
                                    <td>From</td>
                                    <td><input type="text" id="rrFrom" name="rrFrom" size="50" value="${model.rcs[0].rr_from}"></td>
                                </tr>
                                <tr>
                                    <td>Replace Items?</td>
                                    <td>
                                        <select id="isReplace" name="isReplace">
                                            <option value="N">No</option>
                                            <option value="Y">Yes</option>
                                        </select>
                                    </td>
                                    <td>Remarks</td>
                                    <td><input type="text" id="rrRemarks" name="rrRemarks" size="50" value="${model.rcs[0].rr_remarks}"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Update" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGReturnCargo.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Return Cargo &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Pallet Number</td>
                                <td>Item Name</td>
                                <td>Quantity</td>
                                <td>Returned Quantity</td>
                            </tr>
                        </thead>
                        <tbody id="detail">
                            <c:forEach items="${model.rcs}" var="x" varStatus="vs">
                                <tr data-item="${x.item_code}" data-id="${x.rr_id}"> 
                                    <td>${vs.index + 1}</td> 
                                    <td>${x.pack_style}</td> 
                                    <td>${x.pack_size}</td> 
                                    <td data-id="${x.pts_id}">${x.pts_code}</td> 
                                    <td>${x.item_code}</td> 
                                    <td>${x.lmr_labeled}</td> 
                                    <td><input type="text" class="qty" value="${x.rr_qty}" size="6"></td> 
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
            $("#rrDatePicker").val(gnvs.util.toViewDate('${model.rcs[0].rr_date}'));//.datepicker({dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#rrDate", changeYear: true, changeMonth: true});
            $('#rrDate').val(gnvs.util.toDBDate($('#rrDatePicker').val()));

            // BIND | Save
            $("#fReturnCargo").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try select EDS.');
                    return false;
                }

                // VALIDATE | 0 (zero) value in quantity
                if ($('tbody#detail input[type="text"].qty')
                        .filter(function() {
                            return parseFloat($(this).val()) <= 0;
                        }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "", header = $('#rrCode').val() + ':s:' + $('#rrDate').val() + ':s:' + $('#edsCode').data('id') + ':s:' + $('#rrFrom').val() + ':s:' + $('#rrRemarks').val() + ':s:' + $('#isReplace').val() + ':s:';

                $('tbody#detail tr[data-status]').each(function() {
                    data = data + header + $(this).data('status') + ':s:' + $(this).data('id') + ':s:' + $(this).find('td:eq(3)').data('id') + ':s:' + $(this).data('item') + ':s:' + $(this).find('.qty').val() + ':s::se:';
                });

                if (confirm('Update Return Cargo #' + $('#rrCode').val() + ' ?')) {
                    if (data === '') {
                        data = header + 'X:s:-1:s::se:';
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

                return false;
            });

            // UPDATE | function
            $('#isReplace').val('${model.rcs[0].is_replace}');
            
            $('#detail input[type="text"]').bind('keyup', function() {
                $(this).parent().parent().attr('data-status', 'U');
            });

        </script>
    </body>
</html>

