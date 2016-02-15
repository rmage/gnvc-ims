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
        <title>Create &therefore; Return Cargo &therefore; IMS</title>
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
                        <input type="hidden" id="rrDate" name="rrDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Return Cargo &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Return Cargo Number</td>
                                    <td><input type="text" id="rrCode" name="rrCode" required="required"></td>
                                    <td>Return Cargo Date</td>
                                    <td><input type="text" id="rrDatePicker" name="rrDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>EDS Number</td>
                                    <td>
                                        <input type="text" id="edsCode" name="edsCode" size="10">
                                        <input type="button" id="btnAdd" name="btnAdd" value="Select EDS">
                                    </td>
                                    <td>From</td>
                                    <td><input type="text" id="rrFrom" name="rrFrom" size="50"></td>
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
                                    <td><input type="text" id="rrRemarks" name="rrRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
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
                                <td>Action</td>
                            </tr>
                        </thead>
                        <tbody id="detail"></tbody>
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
            $("#rrDatePicker").datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#rrDate", changeYear: true, changeMonth: true, onSelect: function(dateText, inst) { gnvs.validator.backDate($(this), dateText, inst); } });

            // BIND | Search EDS button
            $("#btnAdd").bind("click", function() {
                var edsCode = $('#edsCode').val();

                // VALIDATE | export delivery slip is empty
                if (edsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: edsCode},
                        dataType: "json",
                        success: function(json) {
                            $('tbody#detail').html('');
                            if (json.length > 0) {
                                // HEADER | value
                                $('#edsCode').val(json[0][1]).data('id', json[0][103]);
                                $('#rrFrom').val(json[0][2]);

                                for (var i = 0; i < json.length; i++) {
                                    $('tbody#detail').append('<tr data-item="' + json[i][101] + '">' +
                                            '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                            '<td>' + json[i][3] + '</td>' +
                                            '<td>' + json[i][4] + '</td>' +
                                            '<td data-id="' + json[i][102] + '">' + json[i][5] + '</td>' +
                                            '<td>' + json[i][6] + '</td>' +
                                            '<td>' + json[i][7] + '</td>' +
                                            '<td><input type="text" class="qty" value="0" size="6"></td>' +
                                            '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="actionDelete(this);"></td>' +
                                            '</tr>');
                                }
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                        }
                    });
                }
            });

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

                $('tbody#detail tr').each(function() {
                    data = data + header + $(this).find('td:eq(3)').data('id') + ':s:' + $(this).data('item') + ':s:' + $(this).find('.qty').val() + ':s::se:';
                });

                if (data !== '' && confirm('Save Return Cargo #' + $('#rrCode').val() + ' ?')) {
                    console.log(data);
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }

                return false;
            });

//            // LIVE | validate maximum quantity
//            $('tbody#detail tr input').live('blur', function() {
//                var ppc = $(this).parent().parent().data('ppc');
//                
//                if ($(this).val() < 0 || parseInt($(this).val()) >= parseInt($(this).data('max'))) {
//                    if ($(this).hasClass('tin')) {
//                        if (parseInt($(this).prev().val()) === $(this).prev().data('max')) {
//                            $(this).val($(this).data('max'));
//                        } else if (parseInt($(this).val()) > (ppc - 1)) {
//                            $(this).val(ppc - 1);
//                        }
//                    } else {
//                        $(this).val($(this).data('max'));
//                        $(this).next().trigger('blur');
//                        return false;
//                    }
//                }
//            });

            function actionDelete(el) {
                el.parentNode.parentNode.remove();
                gnvs.util.reNumbering($('#detail'), 1);
            }

        </script>
    </body>
</html>

