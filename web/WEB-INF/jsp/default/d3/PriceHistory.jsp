<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; D3 &therefore; Data-Driven Documents &therefore; Price History</title>
        <%@include file="../../metaheader.jsp" %>
        <script src="resources/default/js/d3.min.js"></script>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-autocomplete {
                max-height: 250px;
                overflow-y: auto;
                /* prevent horizontal scrollbar */
                overflow-x: hidden;
            }
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
                    <form id="form" name="form" action="#">
                        <input type="hidden" id="dateFrom" name="dateFrom" value="" />
                        <input type="hidden" id="dateTo" name="dateTo" value="" />
                        <table class="collapse tblForm row-select">
                            <caption>D3 &therefore; Data-Driven Documents &therefore; Price History</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Item Code</td>
                                    <td><input type="text" id="itemCode" name="itemCode" required /></td>
                                    <td style="width: 200px;">Item Name</td>
                                    <td><input type="text" id="itemName" name="itemName" size="50" required /></td>
                                </tr>
                                <tr>
                                    <td>Date From</td>
                                    <td><input type="text" id="dateFromPicker" name="dateFromPicker" size="10" required /></td>
                                    <td>Date To</td>
                                    <td><input type="text" id="dateToPicker" name="dateToPicker" size="10" required /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <input type="submit" id="btnFind" name="btnFind" value="Search Price History" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select ui-widget-content" id="tblList" style="width: 49%; float: left;">
                        <caption>Price History &therefore; Price List</caption>
                        <thead>
                            <tr>
                                <td>Date</td>
                                <td>Currency</td>
                                <td>Price</td>
                            </tr>
                        </thead>
                        <tbody id="rows"></tbody>
                    </table>
                    <table class="collapse tblForm row-select ui-widget-content" id="tblSummary" style="width: 49%; float: right;">
                        <caption>Price History &therefore; Price Summary</caption>
                        <thead>
                            <tr>
                                <td>Currency</td>
                                <td>Minimum</td>
                                <td>Maximum</td>
                                <td>Average</td>
                            </tr>
                        </thead>
                        <tbody id="rows"></tbody>
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

            // BIND | Datepicker to picker and assign value to alt field
            $("#dateFromPicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#dateFrom",
                changeMonth: true,
                changeYear: true
            });

            $("#dateToPicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#dateTo",
                changeMonth: true,
                changeYear: true
            });

            // BIND | Autocompleted event
            $("#itemCode").autocomplete({
                source: '?action=getProduct&mode=code',
                minLength: 2,
                select: function(e, ui) {
                    $('#itemCode').val(ui.item[1]);
                    $('#itemName').val(ui.item[2]);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>[' + item[1] + '] ' + item[2] + '</a></li>')
                        .appendTo(ul);
            };

            $("#itemName").autocomplete({
                source: '?action=getProduct&mode=name',
                minLength: 2,
                select: function(e, ui) {
                    $('#itemCode').val(ui.item[1]);
                    $('#itemName').val(ui.item[2]);
                    return false;
                }
            }).data('autocomplete')._renderItem = function(ul, item) {
                return $('<li>')
                        .data("item", item)
                        .append('<a><b>[' + item[1] + '] ' + item[2] + '</a></li>')
                        .appendTo(ul);
            };

            // BIND | Reset data when item code or name change
            $("#itemCode,#itemName").bind("focus", function() {
                $("#form")[0].reset();
                $("#tblList > #rows,#tblSummary > #rows").html("");
            });

            // BIND | Change form submit behaviour as data trigger
            var ajaxFlag = true;
            $("#form").bind("submit", function(e) {
                e.preventDefault();

                // AJAX | Get detailed information
                if (ajaxFlag) {
                    ajaxFlag =  false;
                    $("#btnFind").after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
            
                    $.ajax({
                        url: "?action=getPriceList", type: "post",
                        data: {itemCode: $("#itemCode").val(), dateFrom: $("#dateFrom").val(), dateTo: $("#dateTo").val()},
                        dataType: "json",
                        success: function(json) {
                            var id = 1, currency = "", average = 0.00, divider = 0, value = 0;
                            var $trow;
                            for(var i = 0; i < json.length; i++) {
                                value = json[i][3].replace(",", "");
                                $("#tblList > #rows").append("<tr><td>" + json[i][1] + "</td><td>" + json[i][2] + "</td><td>" + json[i][3] + "</td></tr>");
                                
                                if (currency !== json[i][2]) {
                                    id = id + 1;
                                    average = parseFloat(value);
                                    divider = 1;
                                    currency = json[i][2];
                                    $("#tblSummary > #rows").append('<tr id="' + id + '"><td>' + currency + '</td><td>' + json[i][3] + '</td><td>' + json[i][3] + '</td><td>' + json[i][3] + '</td></tr>');
                                    $trow = $("#tblSummary > #rows tr[id='" + id + "']");
                                } else if (currency === json[i][2]) {
                                    average = average + parseFloat(value);
                                    divider = divider + 1;
                                    $trow.find("td:eq(3)").html((average / divider).kThousandFormat(2));
                                    
                                    if (parseFloat($trow.find("td:eq(1)").html().replace(",", "")) > parseFloat(value)) {
                                        $trow.find("td:eq(1)").html(json[i][3]);
                                    }
                                    if (parseFloat($trow.find("td:eq(2)").html().replace(",", "")) < parseFloat(value)) {
                                        $trow.find("td:eq(2)").html(json[i][3]);
                                    }
                                }
                                
                                
                            }
                        },
                        complete: function() {
                            ajaxFlag = true;
                            $("#load").remove();
                        }
                    });
                }
            });

        </script>
    </body>
</html>
