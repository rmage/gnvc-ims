<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Price Assignment - IMS</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
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
            <%@include file="../../header.jsp" %>
            <jsp:include page="../../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Purchase Requisition Slip</caption>
                        <tbody>
                            <tr>
                                <td>PRS Number</td>
                                <td colspan="3"><input type="text" value="${model.ap.prsnumber}" readonly></td>
                            </tr>
                            <tr>
                                <td>Product Code</td>
                                <td><input type="text" value="${model.ap.product_code}" readonly></td>
                                <td>Product Name</td>
                                <td><input size="50" type="text" value="${model.ap.product_name}" readonly></td>
                            </tr>
                            <tr>
                                <td>Supplier Code</td>
                                <td><input id="supplierCode" name="supplierCode" type="text" value="${model.ap.supplier_code}" readonly></td>
                                <td>Supplier Name</td>
                                <td><input size="50" type="text" value="${model.ap.supplier_name}" readonly></td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="collapse tblForm row-select">
                        <caption>Price Assignment</caption>
                        <tbody>
                            <tr>
                                <td>Selected Price ?</td>
                                <td><select id="col6" name="col6"><option value="N">No</option><option value="Y">Yes</option></select></td>
                                <td>Unit Price</td>
                                <td><input id="col1" maxlength="18" name="col1" type="text" value="${model.ap.unit_price}"></td>
                            </tr>
                            <tr>
                                <td>Term of Payment</td>
                                <td>
                                    <select id="col2" name="col2"><option>Cash</option><option>Credit</option></select>
                                    <input id="col3" maxlength="250" name="col3" type="text" size="50" value="${model.ap.top_desc}">
                                </td>
                                <td>Term of Delivery</td>
                                <td><input id="col4" maxlength="250" name="col4" type="text" size="50" value="${model.ap.tod}"></td>
                            </tr>
                            <tr>
                                <td>Warranty Period</td>
                                <td colspan="3"><input id="col5" name="col5" size="10"></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="4">
                                    <input id="btnUpdate" name="btnUpdate" type="button" value="Update" data-id="${model.ap.id}">
                                    <input id="btnCancel" name="btnCancel" type="button" value="Cancel" onclick="window.location.replace('PriceAssignment.htm');">
                                </td>
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
            $('#col2').val('${model.ap.top}');
            if ('${model.ap.wp}' !== '') {
                $('#col5').val(gnvs.util.toViewDate('${model.ap.wp}'));
            }
            $('#col6').val('${model.ap.is_selected}');

            // EVENT | unit price
            $('#col1').bind('blur', function() {
                if (parseFloat($(this).val()) > 0) {
                    $(this).val(parseFloat($(this).val()).toLocaleString('en-US'));
                }
            }).trigger('blur').bind('focus', function() {
                $(this).val($(this).val().replace(/,/g, ''));
            });

            $('#col5').datepicker({changeMonth: true, changeYear: true, dateFormat: "dd/mm/yy"});

            // EVENT | save data
            $('#btnUpdate').bind('click', function() {
                var data = $(this).data('id') + ':s:' + $('#col1').val().replace(/,/g, '') + ':s:' + $('#col2').val() + ':s:' + $('#col3').val() + ':s:' + $('#col4').val() + ':s:' + gnvs.util.toDBDate($('#col5').val()) + ':s:' + $('#col6').val() + ':s:' + $('#supplierCode').val() + ':s::se:';
                if (confirm('Update price assignment?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNUpdate', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }
            });
        </script>
    </body>
</html>
