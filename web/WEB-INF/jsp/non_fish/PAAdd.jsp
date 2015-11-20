<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Price Assignment - IMS</title>
        <%@include file="../metaheader.jsp" %>
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
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <!-- transaction form HERE -->
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="#" id="search" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>Supplier</td>
                                    <td>
                                        <input id="sSupplier" size="50" type="text" required="true" />
                                        <input id="sSupplierCode" type="hidden" required="true" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input id="save" type="button" value="Save" />
                                        <input type="submit" value="Search" />
                                        <input id="btnCancel" type="reset" value="Cancel" onclick="window.location.replace('PriceAssignment.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Assignment</caption>
                            <thead>
                                <tr>
                                    <td>PRS Number</td>
                                    <td>Assign Date</td>
                                    <td>Item Code</td>
                                    <td>Item Name</td>
                                    <td>Quantity</td>
                                    <td>Supplier Code</td>
                                    <td>Supplier Name</td>
                                    <td>Unit Price</td>
                                    <td>Term of Payments</td>
                                    <td>Term of Delivery</td>
                                    <td>Warranty Period</td>
                                </tr>
                            </thead>
                            <tbody class="tbl-nohover" id="main"></tbody>
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

            /* BIND | element event */
            $('#save').bind('click', function() {
                var data = '';
                $('input[name="price"]').each(function() {
                    if ($(this).val() !== '') {
                        var $p = $(this).parent().parent();
                        data = data + $p.data('id') + ':s:' + $p.find('input:eq(1)').val().replace(/,/g, '') + ':s:' + $p.find('select').val() + ':s:' + $p.find('input:eq(2)').val() + ':s:' + $p.find('input:eq(3)').val() + ':s:' + gnvs.util.toDBDate($p.find('input:eq(4)').val()) + ':s:' + ($p.find('input:eq(0)').prop('checked') ? 'Y' : 'N') + ':s::se:';
                    }
                });

                if (data !== '' && confirm('Save price assignment?')) {
                    gnvs.ajaxCall({action: 'ajaxNSave', data: encodeURIComponent(data)}, function(json) {
                        if (json.message === '') {
                            $('#btnCancel').trigger('click');
                        } else {
                            alert(json.message);
                        }
                    });
                }
            });

            $('#search').bind('submit', function(e) {

                var $o = $(this).find('input[type="submit"]');
                $o.attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                /* get detail item */
                $.ajax({
                    url: 'PriceAssignment.htm',
                    data: {action: 'getSupplier', key: $('#sSupplierCode').val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for (var i = 0; i < json.rows.length; i++) {
                            $('#main').append('<tr data-id="' + json.rows[i][0] + '"><td>' + json.rows[i][1] + '</td><td>' + json.rows[i][2] + '</td><td>' + json.rows[i][3] + '</td><td>' + json.rows[i][4] + '</td><td>' + json.rows[i][5] + '</td><td>' + json.rows[i][6] +
                                    '</td><td>' + json.rows[i][7] + '</td><td><input name="selected" title="Selected Supplier" type="checkbox"> <input class="price" name="price" size="10" type="text"></td><td><select name="top"><option>Cash</option><option>Credit</option></select>' +
                                    '<input name="topDesc" type="text"></td><td><input name="tod" type="text"></td><td><input class="wp" name="wp" size="8" type="text"></td></tr>');
                        }

                        // prevent multiple event assignment
                        setEvent();
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);

                        if ($('#main').html().trim() === '')
                            $('#search')[0].reset();
                    }
                });

                return false;
            });

            var supplierList = [
            <c:forEach items="${model.supplier}" var="x">
                {
                    label: '${x.supplierName}',
                    code: '${x.supplierCode}'
                },
            </c:forEach>
            ];
            $('#sSupplier').bind('focus', function() {
                $(this).val(null);
                $('#sSupplierCode').val(null);
                $('#main').html(null);
            });
            $('#sSupplier').autocomplete({
                source: supplierList,
                select: function(event, ui) {
                    $('#sSupplier').val(ui.item.label);
                    $('#sSupplierCode').val(ui.item.code);
                    $('#sSupplier').blur();
                    return false;
                }
            });

            function setEvent() {
                $('.wp').datepicker({dateFormat: "dd/mm/yy"});
                
                // event: focus
                $(document).on('focus', '.price', function() {
                    $(this).val($(this).val().replace(/,/g, ''));
                });
                
                // event: blur
                $(document).on('blur', '.price', function() {
                    if (parseFloat($(this).val()) > 0) {
                        $(this).val(parseFloat($(this).val()).kThousandFormat2(5));
                    } else {
                        $(this).val(0);
                    }
                });
            }

        </script>
    </body>
</html>
