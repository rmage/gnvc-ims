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
        <title>IMS &therefore; Booked Order &therefore; Create</title>
        <%@include file="../../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
            .item-detail span {
                border-bottom: 1px solid;
                display: inline-block;
                text-align: center;
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
                    <form action="#" id="borHeader" name="borHeader" method="post">
                        <input type="hidden" id="borDate" name="borDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Booked Order &therefore; Header</caption>
                            <tbody>
                                <tr>
                                    <td class="bold" style="width: 175px;">BOR Number</td>
                                    <td><input id="borNumber" maxlength="50" name="borNumber" tabindex="1" type="text" required></td>
                                    <td style="width: 175px;"></td>
                                    <td></td>
                                    <td class="bold" style="width: 175px;">BOR Date</td>
                                    <td><input id="borDatePicker" maxlength="10" name="borDatePicker" size="10" tabindex="2" type="text" value="<%=sdfPicker.format(cDate)%>" required></td>
                                </tr>
                                <tr>
                                    <td class="bold">Contract Number</td>
                                    <td><input id="borContract" maxlength="50" name="borContract" tabindex="3" type="text"></td>
                                    <td class="bold">Freight Term</td>
                                    <td>
                                        <select id="borFreight" name="borFreight" tabindex="6">
                                            <option value="">-- Select Freight Term --</option>
                                            <c:forEach items="${ims.freights}" var="x">
                                                <option value="${x.freight_id}">${x.freight_code} | ${x.freight_description}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="bold">Carton</td>
                                    <td><input id="borCarton" maxlength="50" name="borCaron" tabindex="9" type="text"></td>
                                </tr>
                                <tr>
                                    <td class="bold">Buyer</td>
                                    <td>
                                        <select id="borBuyer" name="borBuyer" tabindex="4" required>
                                            <option value="">-- Select Buyer --</option>
                                            <c:forEach items="${ims.buyers}" var="x">
                                                <option value="${x.buyer_id}">${x.buyer_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="bold">Payment Term</td>
                                    <td>
                                        <select id="borPayment" name="borPayment" tabindex="7">
                                            <option value="">-- Select Payment Term --</option>
                                            <c:forEach items="${ims.tops}" var="x">
                                                <option value="${x.top_id}">${x.top_code} | ${x.top_description}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="bold">Shrink Wrap</td>
                                    <td>
                                        <select id="borShrink" name="borShrink" tabindex="10">
                                            <option>No</option>
                                            <option>Yes</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="bold">Can Code Max per FCL</td>
                                    <td><input id="borMaxCode" maxlength="50" name="borMaxCode" size="7" tabindex="5" type="text"></td>
                                    <td class="bold">GSP Form</td>
                                    <td><input id="borGsp" maxlength="50" name="borGsp" tabindex="8" type="text"></td>
                                    <td class="bold">Extra Carton per FCL</td>
                                    <td><input id="borExtraCarton" maxlength="50" name="borExtraCarton" size="7" tabindex="11" type="text"></td>
                                </tr>
                                <tr>
                                    <td class="bold">Additional Info</td>
                                    <td colspan="3">
                                        <input id="borInfo1" maxlength="250" name="borInfo1" size="100" tabindex="13" type="text">
                                        <input id="borInfo2" maxlength="250" name="borInfo2" size="100" tabindex="14" type="text">
                                        <input id="borInfo3" maxlength="250" name="borInfo3" size="100" tabindex="15" type="text">
                                    </td>
                                    <td class="bold">Extra Label per FCL</td>
                                    <td><input id="borExtraLabel" maxlength="50" name="borExtraLabel" size="7" tabindex="12" type="text"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6">
                                        <input type="submit" value="Save" name="btnSave" />
                                        <input type="reset" value="Cancel" name="btnCancel" onclick="window.location.replace('FGBookedOrder15.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <form action="#" id="borDetail" method="post" name="borDetail">
                        <table class="collapse tblForm">
                            <caption>Booked Order &therefore; Detail</caption>
                            <tbody>
                                <tr>
                                    <td class="bold">Reference Number</td>
                                    <td><input id="borReference" maxlength="50" name="borReference" tabindex="16" type="text" required></td>
                                    <td class="bold">Percent Flakes</td>
                                    <td><input id="borFlakes" maxlength="50" name="borFlakes" tabindex="20" type="text"></td>
                                    <td class="bold">Commission</td>
                                    <td><input id="borCommission" maxlength="50" name="borCommission" size="2" tabindex="23" type="text"></td>
                                    <td class="bold">GMO</td>
                                    <td><input id="borGmo" maxlength="50" name="borGmo" tabindex="26" type="text"></td>
                                </tr>
                                <tr>
                                    <td class="bold">PO / IMP Number</td>
                                    <td><input id="borPo" maxlength="50" name="borPo" tabindex="17" type="text"></td>
                                    <td class="bold">Case per FCL</td>
                                    <td><input id="borCase" max="10000" min="1" name="borCase" step="1" tabindex="21" type="number" required></td>
                                    <td class="bold">Oil Water Ratio</td>
                                    <td><input id="borOilWater" maxlength="50" name="borOilWater" size="5" tabindex="24" type="text"></td>
                                    <td class="bold">Shipping Date</td>
                                    <td><input id="borShippingDate" maxlength="100" name="borShippingDate" size="50" tabindex="27" type="text"></td>
                                </tr>
                                <tr>
                                    <td class="bold">Brand</td>
                                    <td>
                                        <select id="borBrand" name="borBrand" tabindex="18" required>
                                            <option value="">-- Select Brand --</option>
                                        </select>
                                    </td>
                                    <td class="bold">Price</td>
                                    <td><input id="borPrice" min="0.01" name="borPrice" size="4" step="0.01" tabindex="22" type="number" required></td>
                                    <td class="bold">Number of Loins</td>
                                    <td><input id="borLoins" maxlength="50" name="borLoins" tabindex="25" type="text"></td>
                                    <td class="bold">Destination</td>
                                    <td>
                                        <input id="iPort" type="checkbox" name="iPort">
                                        <input id="borDestination" maxlength="100" name="borDestination" size="45" tabindex="28" type="text">
                                    </td>
                                </tr>
                                <tr>
                                    <td class="bold">Item Code</td>
                                    <td><input id="borItem" name="borItem" size="10" tabindex="19" type="text"></td>
                                    <td class="item-detail" colspan="6">
                                        <b>Pack Style: </b><span id="itemPs" style="width: 50px;"></span>
                                        <b>Can Size: </b><span id="itemCs" style="width: 60px;"></span>
                                        <b>Net Weight: </b><span id="itemNw" style="width: 50px;"></span>
                                        <b>Drain Weight: </b><span id="itemDw" style="width: 50px;"></span>
                                        <input id="addDetail" name="addDetail" value="Add Detail" tabindex="29" type="submit">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                    <table class="collapse tblForm">
                        <caption>Booked Order &therefore; Specification per FCL</caption>
                        <thead>
                            <tr>
                                <th>Action</th>
                                <th>Reference #</th>
                                <th>PO / IMP</th>
                                <th>Brand</th>
                                <th>Item Code</th>
                                <th>Percent Flakes</th>
                                <th>Case per FCL</th>
                                <th>Price</th>
                                <th>Commission</th>
                                <th>Oil Water Ratio</th>
                                <th>Number of Loins</th>
                                <th>GMO</th>
                                <th>Shipping Date</th>
                                <th>Destination</th>
                            </tr>
                        </thead>
                        <tbody id="borDetailSpecification"></tbody>
                    </table>
                    <table class="collapse tblForm">
                        <caption>Booked Order &therefore; Item Specification</caption>
                        <thead>
                            <tr>
                                <th>Item Code</th>
                                <th>Oil Medium</th>
                                <th>Pack Style</th>
                                <th>Catch Method</th>
                                <th>Can Size</th>
                                <th>Net Weight</th>
                                <th>Drain Weight</th>
                                <th>Lid Type</th>
                                <th>Can Type</th>
                            </tr>
                        </thead>
                        <tbody id="borDetailItem"></tbody>
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
            //  BIND | Date Picker to bor date
            $('#borDatePicker').datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#borDate",
                changeYear: true,
                changeMonth: true
            });

            // BUYER | when pick the buyer get the brand for the following buyer
            $('#borBuyer').bind('change', function() {
                var $i = $('#borBrand');
                $i.html('<option value="">-- Select  Brand --</option>');
                if ($(this).val() !== '') {
                    $i.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif">');

                    $.ajax({
                        url: '?', type: 'post',
                        data: {action: 'getBrand', buyer: $(this).val()},
                        dataType: 'json',
                        success: function(json) {
                            for (var i = 0; i < json.length; i++) {
                                $i.append('<option value="' + json[i][1] + '">' + json[i][2] + '</option>');
                            }
                        },
                        complete: function() {
                            $('img#load').remove();
                        }
                    });
                }
            });

            // ITEM | autocomplete based on item code
            $('#borItem').autocomplete({
                source: function(request, response) {
                    $.ajax({
                        url: '?action=getItem', type: 'post',
                        data: {term: request.term},
                        dataType: 'json',
                        success: function(json) {
                            response(json.rows);
                        }
                    });
                },
                minLength: 3,
                delay: 600,
                select: function(event, ui) {
                    $('#borFlakes').focus();
                    var ps = ui.item.PackStyle.split(' - ');
                    var ndw = ps[4].split('/');
                    setItemInformation(ui.item.ProductCode, ps[1], '-', ndw[0], ndw[1]);

                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>Item Code</b>: ' + item.ProductCode + ' | <b>Pack Style</b>: ' + item.PackStyle + '</a></li>')
                        .appendTo(ul);
            };

            // ITEM | remove info on focus to prevent accidental deletion
            $('#borItem').bind('focus', function() {
                setItemInformation('', '', '', '', '');
            });

            // DETAIL | form submit and check validation
            var iSpec = 1;
            $('#borDetail').bind('submit', function() {
                if ($('#addDetail').val() === 'Add Detail') {
                    if ($('#borDetailSpecification td:nth-child(2):contains("' + $('#borReference').val() + '")').length < 1) {
                        $('#borDetailSpecification').append('<tr><td id="' + iSpec + '"><img class="edit" title="Edit" src="resources/images/edit.gif" style="cursor: pointer;"><img class="delete" title="Delete" src="resources/images/delete.gif" style="cursor: pointer;"></td>' +
                                '<td>' + $('#borReference').val() + '</td>' +
                                '<td>' + $('#borPo').val() + '</td>' +
                                '<td data-brand="' + $('#borBrand').val() + '">' + $('#borBrand option:selected').html() + '</td>' +
                                '<td>' + $('#borItem').val() + '</td>' +
                                '<td>' + $('#borFlakes').val() + '</td>' +
                                '<td>' + $('#borCase').val() + '</td>' +
                                '<td>' + $('#borPrice').val() + '</td>' +
                                '<td>' + $('#borCommission').val() + '</td>' +
                                '<td>' + $('#borOilWater').val() + '</td>' +
                                '<td>' + $('#borLoins').val() + '</td>' +
                                '<td>' + $('#borGmo').val() + '</td>' +
                                '<td>' + $('#borShippingDate').val() + '</td>' +
                                '<td>' + $('#borDestination').val() + '</td>' +
                                '</tr>');

                        setItemSpecification($('#borItem').val(), '', $('#itemPs').html(), '', $('#itemCs').html(), $('#itemNw').html(), $('#itemDw').html(), '', '');
                        iSpec = iSpec + 1;
                    } else {
                        alert('Duplicate reference number? Please check!');
                    }
                } else {
                    $('#borDetailSpecification td:nth-child(2):contains("' + $('#borReference').val() + '")').parent().html('<td><img class="edit" title="Edit" src="resources/images/edit.gif" style="cursor: pointer;"><img class="delete" title="Delete" src="resources/images/delete.gif" style="cursor: pointer;"></td>' +
                            '<td>' + $('#borReference').val() + '</td>' +
                            '<td>' + $('#borPo').val() + '</td>' +
                            '<td data-brand="' + $('#borBrand').val() + '">' + $('#borBrand option:selected').html() + '</td>' +
                            '<td>' + $('#borItem').val() + '</td>' +
                            '<td>' + $('#borFlakes').val() + '</td>' +
                            '<td>' + $('#borCase').val() + '</td>' +
                            '<td>' + $('#borPrice').val() + '</td>' +
                            '<td>' + $('#borCommission').val() + '</td>' +
                            '<td>' + $('#borOilWater').val() + '</td>' +
                            '<td>' + $('#borLoins').val() + '</td>' +
                            '<td>' + $('#borGmo').val() + '</td>' +
                            '<td>' + $('#borShippingDate').val() + '</td>' +
                            '<td>' + $('#borDestination').val() + '</td>');

                    setItemSpecification($('#borItem').val(), '', $('#itemPs').html(), '', $('#itemCs').html(), $('#itemNw').html(), $('#itemDw').html(), '', '');
                    $('#addDetail').val('Add Detail');
                    $('#borReference').attr('readonly', false);
                }

                $('#borReference').focus();
                return false;
            });

            // HEADER | form submit to save
            $('#borHeader').bind('submit', function() {
                if ($('#borDetailSpecification tr').length > 0) {
                    var data = "";

                    // GET | header data
                    var header = $('#borNumber').val() + '^' + $('#borDate').val() + '^' + $('#borContract').val() + '^' + $('#borBuyer').val() + '^' +
                            $('#borMaxCode').val() + '^' + $('#borFreight').val() + '^' + $('#borPayment').val() + '^' + $('#borGsp').val() + '^' +
                            $('#borCarton').val() + '^' + $('#borShrink').val() + '^' + $('#borExtraCarton').val() + '^' + $('#borExtraLabel').val() + '^' +
                            $('#borInfo1').val() + '^' + $('#borInfo2').val() + '^' + $('#borInfo3').val() + '^';

                    $('#borDetailSpecification tr').each(function() {
                        // GET | detail data
                        var detail = "";
                        $(this).find('td').each(function(i) {
                            if (i > 0) {
                                switch (i) {
                                    case 3:
                                        detail = detail + $(this).data('brand') + '^';
                                        break;
                                    default:
                                        detail = detail + $(this).html() + '^';
                                }
                            }
                        });

                        data = data + header + detail + '~';
                    });

                    data = data
                            .replace(/#/g, ":numberSign:")
                            .replace(/%/g, ":percentageSign:");

                    if (data !== "") {
                        if (confirm("Continue to save this document?")) {
                            window.location.replace("?action=save&data=" + data);
                        }
                    }
                } else {
                    alert('Please add specification information!');
                }

                return false;
            });

            function setItemInformation(bi, ps, cs, nw, dw) {
                $('#borItem').val(bi);
                $('#itemPs').html(ps);
                $('#itemCs').html(cs);
                $('#itemNw').html(nw);
                $('#itemDw').html(dw);
            }

            function setItemSpecification(ic, om, ps, cm, cs, nw, dw, lt, ct) {
                if ($('#borDetailItem td:nth-child(1):contains("' + ic + '")').length < 1 && ic !== '') {
                    $('#borDetailItem').append('<tr><td>' + ic + '</td>' +
                            '<td>' + om + '</td>' +
                            '<td>' + ps + '</td>' +
                            '<td>' + cm + '</td>' +
                            '<td>' + cs + '</td>' +
                            '<td>' + nw + '</td>' +
                            '<td>' + dw + '</td>' +
                            '<td>' + lt + '</td>' +
                            '<td>' + ct + '</td>' +
                            '</tr>');
                }
            }

            // BIND | Checkbox change
            $('input#iPort').bind('change', function() {
                var $dstI = $('<input id="borDestination" maxlength="100" name="borDestination" size="45" tabindex="28" type="text">');
                var $dstS = $('<select id="borDestination" name="borDestination" tabindex="28"></select>');

                $(this).next().remove();
                if ($(this).is(':checked')) {
                    var $t = $(this);
                    
                    $(this).after(' <img id="load" src="resources/ui-anim_basic_16x16.gif">');
                    $.ajax({
                        url: '?', type: 'post',
                        data: {action: 'getDestination'},
                        dataType: 'json',
                        success: function(json) {
                            $dstS.append('<option value="">-- select destination --</option>');
                            for (var i = 0; i < json.length; i++) {
                                $dstS.append('<option>' + json[i][2] + '</option>');
                            }
                        },
                        complete: function() {
                            $('img#load').remove();
                            $t.after($dstS).after(' ');
                        }
                    });
                } else {
                    $(this).after($dstI).after(' ');
                }
            });

            $('img.delete').live('click', function() {
                if (confirm('Delete this row?')) {
                    $(this).parent().parent().remove();
                }
            });

            $('img.edit').live('click', function() {
                if (confirm('Update this row?')) {
                    var $td = $(this).parent();
                    var itemCode = $td.parent().find('td:eq(4)').html();
                    jqSort('#borDetail input:not([type="submit"]):not(:checkbox),#borDetail select', 'tabindex').each(function(i) {
                        $td = $td.next();
                        if (i === 2) {
                            $(this).val($td.data('brand'));
                        } else {
                            $(this).val($td.html());
                        }
                    });

                    $('#addDetail').val('Update Detail');
                    $('#borReference').attr('readonly', true);
                    
                    var $iTr = $('#borDetailItem td:nth-child(1):contains("' + itemCode + '")').parent();
                    setItemInformation($iTr.find('td:eq(0)').html(), $iTr.find('td:eq(2)').html(), $iTr.find('td:eq(4)').html(), $iTr.find('td:eq(5)').html(), $iTr.find('td:eq(6)').html());
                }
            });

            // FUNCTION | static function
            function jqSort(selector, attrName) {
                return $($(selector).toArray().sort(function(a, b) {
                    var aVal = parseInt(a.getAttribute(attrName)),
                            bVal = parseInt(b.getAttribute(attrName));
                    return aVal - bVal;
                }));
            }
        </script>
    </body>
</html>
