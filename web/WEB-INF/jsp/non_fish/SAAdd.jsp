<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Supplier Assignment</title>
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
                    <form action="SupplierAssignment.htm" autocomplete="off" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Assignment</caption>
                        <thead>
                            <tr>
                                <td colspan="6" style="text-align: right;">
                                    <input style="background-color: #FFFFFF;" type="button" value="Back" onclick="window.location.replace('SupplierAssignment.htm');" />
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 113px">Action</td>
                                <td style="width: 87px">PRS Number</td>
                                <td style="width: 61px">Item Code</td>
                                <td>Item Name</td>
                                <td style="width: 48px">Quantity</td>
                                <td style="width: 330px">Supplier</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input type="text" size="10" onkeyup="filterList(2, this.value)" /></td>
                                <td><input type="text" size="3" onkeyup="filterList(3, this.value)"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody id="main" class="tbl-nohover">
                            <c:forEach items="${model.list}" var="x">
                                <tr class="form">
                                    <td>
                                        <input type="submit" value="Assign" onclick="if (confirm('Assign Button clicked, want to continue?')) {
                                                    submitForm(this);
                                                } else {
                                                    return false;
                                                }" />
                                        <input class="add" type="button" value="Add" data-prsc="${x.prsnumber}" data-proc="${x.productcode}" data-pron="${x.productname}" />
                                    </td>
                                    <td>
                                        ${x.prsnumber}
                                        <input type="hidden" name="prsnumber" value="${x.prsnumber}" required="true" />
                                    </td>
                                    <td>
                                        ${x.productcode}
                                        <input type="hidden" name="productcode" value="${x.productcode}" required="true" />
                                    </td>
                                    <td>${x.productname}</td>
                                    <td>${x.qty}</td>
                                    <td class="row">
                                        <input class="supplier" name="1supplier" size="40" type="text" required="true" />
                                        <input type="hidden" name="1supplierCode" required="true" />
                                    </td>
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
            //  ACTION | Add action on click
            $('.add').live('click', function() {
                if (confirm('Add new supplier for this? PRS Number : ' + $(this).data('prsc') + ' | Item : [' + $(this).data('proc') + '] ' + $(this).data('pron'))) {
                    var l = $(this).parent().parent().find('.supplier').size();
                    var $p = $(this).parent().parent().find('.row');
                    $('<input class="supplier" name="' + (l + 1) + 'supplier" size="40" type="text" required="true" /> '
                            + '<input name="' + (l + 1) + 'supplierCode" type="hidden" required="true" /> '
                            + '<input class="delete ui-button ui-widget ui-state-default ui-corner-all" name="' + (l + 1)
                            + 'delete" style="font-size: smaller;" type="button" value="Delete" />')
                            .appendTo($p);
                    $p.find('input[name="' + (l + 1) + 'supplier"]')
                            .autocomplete({
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
                $(this).parent().find('input[name="' + i + 'supplierCode"]').remove();
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

            function submitForm(element) {
                //  VALIDATATION | Check supplier isn't typo but selected in the list
                var b = false;
                $(element).parent().parent().find(".supplier").each(function() {
                    //console.log($(this).val() + "/" + $(this).attr("validity"));
                    if ($(this).attr("validity") !== "true") {
                        b = true;
                        return false;
                    }
                });

                //  FLAG | Stop execution when typo
                if (b)
                    return;

                element.type = 'hidden';

                while (element.className !== 'form')
                    element = element.parentNode;

                var form = document.getElementById('poster');
                var inputs = element.getElementsByTagName('input');

                while (inputs.length > 0) {
                    form.appendChild(inputs[0]);
                }

                var selects = element.getElementsByTagName('select');

                while (selects.length > 0)
                    form.appendChild(selects[0]);

                var textareas = element.getElementsByTagName('textarea');

                while (textareas.length > 0)
                    form.appendChild(textareas[0]);

                if (form.checkValidity()) {
                    form.submit();
                } else {
                    window.location.replace(window.location.href);
                }
            }

            //  FILTER | By PRS Number
            var $trObj = $('#main tr.form');
            function filterList(i, keyword) {
                if (keyword === '') {
                    $trObj.filter(':hidden').show();
                } else {
                    $trObj.filter(':visible').hide();
                    $trObj.find('td:nth-child(' + i + '):contains("' + keyword + '")').parent().show();
                }
            }

            //  VALIDATIOn | Prevent duplicate value on selected supplier
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
