<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Supplier Assignment - IMS</title>
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
                        <caption>Assignment</caption>
                        <thead>
                            <tr>
                                <td colspan="6" style="text-align: right;">
                                    <input type="button" data-pron="${model.ass[0].product_name}" data-proc="${model.ass[0].product_code}" data-prsc="${model.ass[0].prsnumber}" value="Add" class="add" style="font-size: smaller;">
                                    <input id="btnUpdate" type="button" value="Update">
                                    <input id="btnCancel" style="background-color: #FFFFFF;" type="button" value="Cancel" onclick="window.location.replace('SupplierAssignment.htm');">
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 87px">PRS Number</td>
                                <td style="width: 61px">Item Code</td>
                                <td>Item Name</td>
                                <td style="width: 48px">Quantity</td>
                                <td style="width: 330px">Supplier</td>
                            </tr>
                        </thead>
                        <tbody id="main" class="tbl-nohover">
                            <tr>
                                <td>
                                    ${model.ass[0].prsnumber}
                                    <input type="hidden" required="true" value="${model.ass[0].prsnumber}" name="prsnumber">
                                </td>
                                <td>
                                    ${model.ass[0].product_code}
                                    <input type="hidden" required="true" value="${model.ass[0].product_code}" name="productcode">
                                </td>
                                <td>${model.ass[0].product_name}</td>
                                <td>${model.ass[0].qty}</td>
                                <td class="row">
                                    <c:forEach items="${model.ass}" var="x" varStatus="v">
                                        <input type="text" required="true" size="40" name="${v.index + 1}supplier" class="supplier" value="${x.supplier_name}" validity="true">
                                        <input type="hidden" required="true" name="${v.index + 1}supplierCode" value="${x.supplier_code}" data-id="${x.id}">
                                        <c:if test="${v.index > 0}">
                                            <input type="button" value="Delete" style="font-size: smaller;" name="${v.index + 1}delete" class="delete ui-button ui-widget ui-state-default ui-corner-all">
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
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
            //  ACTION | Add action on click
            $('.add').live('click', function() {
                if (confirm('Add new supplier for this? PRS Number : ' + $(this).data('prsc') + ' | Item : [' + $(this).data('proc') + '] ' + $(this).data('pron'))) {
                    var l = $('#main tr').find('.supplier').size();
                    var $p = $('#main tr').find('.row');
                    $('<input class="supplier" name="' + (l + 1) + 'supplier" size="40" type="text" required="true" > '
                            + '<input name="' + (l + 1) + 'supplierCode" type="hidden" required="true" data-status="C" data-id="-1"> '
                            + '<input class="delete ui-button ui-widget ui-state-default ui-corner-all" name="' + (l + 1) + 'delete" style="font-size: smaller;" type="button" value="Delete">')
                            .appendTo($p);
                    $p.find('input[name="' + (l + 1) + 'supplier"]').autocomplete({
                        source: supplierList,
                        select: function(event, ui) {
                            if (isDuplicate($(this), ui.item.label)) {
                                $(this).val("");
                                $(this).attr("validity", false);
                                $('input[name="' + ($(this).attr('name') + 'Code') + '"]').val("");
                            } else {
                                $(this).val(ui.item.label);
                                $(this).attr("validity", true);
                                $('input[name="' + ($(this).attr('name') + 'Code') + '"]').val(ui.item.code);
                            }
                            return false;
                        }
                    });
                }
            });

            $('.delete').live('click', function() {
                var i = $(this).attr('name').slice(0, 1);
                $(this).parent().find('input[name="' + i + 'supplier"]').remove();
                $(this).parent().find('input[name="' + i + 'supplierCode"]').data('status', 'D');
                $(this).remove();
            });

            var supplierList = [
            <c:forEach items="${model.supplier}" var="x">
                {
                    label: '${x.supplierName}',
                    code: '${x.supplierCode}'
                },
            </c:forEach>
            ];

            $('.supplier').autocomplete({
                source: supplierList,
                select: function(event, ui) {
                    $(this).val(ui.item.label);
                    $(this).attr("validity", true);
                    $('input[name="' + ($(this).attr('name') + 'Code') + '"]').val(ui.item.code);
                    return false;
                }
            }).live("keyup", function() {
                $(this).attr("validity", false);
            });

            $('#btnUpdate').bind('click', function() {
                //  VALIDATATION | Check supplier isn't typo but selected in the list
                var b = false;
                $('#main tr').find(".supplier").each(function() {
                    if ($(this).attr("validity") !== "true") {
                        b = true;
                        return false;
                    }
                });

                //  FLAG | Stop execution when typo
                if (b)
                    return;

                // 2015 Update | by FYA
                var header = '',
                        data = '';
                $('#main tr').find('input[type="hidden"]').each(function(i) {
                    if (i < 2) {
                        header = header + $(this).val() + ':s:';
                    } else if ($(this).data('status') !== undefined) {
                        data = data + header + $(this).data('id') + ':s:' + $(this).data('status') + ':s:' + $(this).val() + ':s::se:';
                    }
                });

                if (data !== '') {
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

            //  VALIDATION | Prevent duplicate value on selected supplier
            function isDuplicate($o, value) {
                var b = false;
                $o.parent().parent().find(".supplier").each(function() {
                    if ($(this).val() === value) {
                        b = true;
                        return false;
                    }
                });

                return b;
            }
        </script>
    </body>
</html>
