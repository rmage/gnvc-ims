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
        <title>Create &therefore; Reclassification &therefore; IMS</title>
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
                    <form action="#" id="fReclassification" name="fReclassification" method="post">
                        <input type="hidden" id="reccDate" name="reccDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Reclassification &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Reclassification Number</td>
                                    <td><input type="text" id="reccCode" name="reccCode" required="required"></td>
                                    <td>Reclassification Date</td>
                                    <td><input type="text" id="reccDatePicker" name="reccDatePicker" value="<%=sdfPicker.format(cDate)%>" size="10" required></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="reccRemarks" name="reccRemarks" size="50" pattern="[^#\^@]+" title="accept all, except: [#][^][@]" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input id="btnCancel" type="button" value="Cancel" name="btnCancel" onclick="window.location.replace('FGReclassification.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Reclassification &therefore; Detail</caption>
                        <thead>
                            <tr>
                                <th>Pallet Number</th>
                                <th colspan="8">
                                    <input type="text" id="ptsCode" name="ptsCode">
                                    <input type="button" id="btnAdd" name="btnAdd" value="Add Item">
                                </th>
                            </tr>
                            <tr>
                                <td style="width: 77px;">Number</td>
                                <td>Pack Style</td>
                                <td>Pack Size</td>
                                <td>Pallet Number</td>
                                <td>From Item</td>
                                <td>To Item</td>
                                <td>Quantity</td>
                                <td>Reclassification Quantity</td>
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
            $("#reccDatePicker").datepicker({ dateFormat: "dd/mm/yy", altFormat: "yy-mm-dd", altField: "#reccDate", changeYear: true, changeMonth: true, onSelect: function(dateText, inst) { gnvs.validator.backDate($(this), dateText, inst); } });

            // BIND | Search PTS button
            $("#btnAdd").bind("click", function() {
                // VALIDATE | No same pts number in list
                var ptsCode = $('#ptsCode').val();
                if ($('tbody#detail tr td:nth-child(4):containsCI("' + ptsCode + '")').length > 0) {
                    alert('Pallet Reclassification Number #' + ptsCode + ' is in detail! Duplicate detected.');
                    return false;
                }

                // VALIDATE | pallet transfer number is empty
                if (ptsCode !== '') {
                    $(this).after('<img id="load" src="resources/ui-anim_basic_16x16.gif" />');
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPalletTransfer", key: ptsCode},
                        dataType: "json",
                        success: function(json) {
                            for (var i = 0; i < json.length; i++) {
                                $('tbody#detail').append('<tr>' +
                                        '<td>' + ($('tbody#detail tr').length + 1) + '</td>' +
                                        '<td>' + json[i][1] + '</td>' +
                                        '<td>' + json[i][2] + '</td>' +
                                        '<td data-id="' + json[i][101] + '">' + json[i][100] + '</td>' +
                                        '<td><select>' + json[i][3] + '</select></td>' +
                                        '<td><select>' + json[i][5] + '</select>' + '</td>' +
                                        '<td></td>' +
                                        '<td><input type="text" class="qty" value="0"></td>' +
                                        '<td><input class="ui-button ui-widget ui-state-default ui-corner-all" type="button" value="Remove" style="font-size: smaller;" onclick="this.parentNode.parentNode.remove()"></td>' +
                                        '</tr>');
                            }

                            if (json.length > 0) {
                                $('#ptsCode').val("");
                                $('tbody#detail tr:last select:first option:selected').each(function() { $(this).parent().parent().next().next().html($(this).data('qty')); });
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                            $('#ptsCode').focus();
                        }
                    });
                }
            });

            // BIND | Save function
            $("#fReclassification").bind("submit", function() {
                // VALIDATE | Pallet has been added
                if ($("tbody#detail tr").length <= 0) {
                    alert('[Error] No pallet number added? Try adding one.');
                    return false;
                }

                // VALIDATE | 0 (zero) value in bad quantity
                if ($('tbody#detail input.qty')
                        .filter(function() {
                            return parseFloat($(this).val()) <= 0;
                        }).length > 0) {
                    alert('[Error] 0 (zero) value detected! Please remove or fill quantity greater than 0 (zero).');
                    return false;
                }

                var data = "", header = $('#reccCode').val() + ':s:' + $('#reccDate').val() + ':s:' + $('#reccRemarks').val() + ':s:';

                $('tbody#detail tr').each(function() {
                    data = data + header + $(this).find('td:eq(3)').data('id') + ':s:' + $(this).find('select:eq(0)').val() + ':s:' + $(this).find('select:eq(1)').val() + ':s:' + $(this).find('.qty').val() + ':s::se:';
                });
                
                if (data !== '' && confirm('Save Reclassification #' + $('#reccCode').val() + ' ?')) {
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
            
            $('#detail td:nth-child(5) select').live('change', function() {
                $(this).parent().next().next().html($(this).find('option:selected').data('qty'));
            });

//            // LIVE | validate maximum quantity
//            $('tbody#detail .cs,tbody#detail .tin').live('blur', function() {
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

        </script>
    </body>
</html>

