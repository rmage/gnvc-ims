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
        <title>IMS &therefore; Receiving Report &therefore; Add</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            .ui-datepicker { display: none; }
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
                        <input type="hidden" id="rrDate" name="rrDate" value="<%=sdf.format(cDate)%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>RR Number</td>
                                    <td><input id="rrCode" name="rrCode" pattern="[0-9]{1,}" type="text" required="true" /></td>
                                    <td>RR Date</td>
                                    <td colspan="2"><input id="rrDatePicker" name="rrDatePicker" size="10" type="text" value="<%=sdfPicker.format(cDate)%>" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td style="width: 500px;"><input id="from" size="50" type="text" readonly="true" /></td>
                                    <td rowspan="2">PO</td>
                                    <td>Number</td>
                                    <td><input type="text" id="poCode" name="poCode" size="10" /></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td>PT. Sinar Pure Foods International</td>
                                    <td>Date</td>
                                    <td><input id="poDate" size="10" type="text" readonly="true" /></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="4"><input type="text" id="remarks" name="remarks" size="100" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="button" value="Save" />
                                        <input type="submit" value="Search" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('ReceiveReport.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Receiving Report &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td rowspan="2">Action</td>
                                    <td rowspan="2">Item Name</td>
                                    <td rowspan="2">Item Code</td>
                                    <td rowspan="2">Department</td>
                                    <td colspan="2">Quantity</td>
                                    <td colspan="2">Received</td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td>U/M</td>
                                    <td>Good</td>
                                    <td>Bad</td>
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
            $("#rrDatePicker").datepicker({
                dateFormat: "dd/mm/yy",
                altFormat: "yy-mm-dd",
                altField: "#rrDate",
                changeMonth: true,
                changeYear: true
            });

            $("#poCode").bind("keydown", function(e) {
                //console.log(e.keyCode);
                if (e.keyCode === 39) {
                    $.ajax({
                        url: "?", type: "post",
                        data: {action: "getPO", key: $("#poCode").val()},
                        dataType: "json",
                        success: function(json) {
                            if (json === null) {
                                    alert("Purchase Order (PO) Number #" + $("#poCode").val() + " not found or already received!");
                            } else {
                                $("#poCode").data("detail", true);
                                $('#poDate').val(json[2]);
                                $('#from').val(json[3]);
                            }
                        }
                    });
                } else {
                    $("#poCode").data("detail", false);
                }
            });

            //  BIND | Validation on rr quantity
            $(".qtyGood,.qtyBad").live("blur", function(e) {
                var $r = $(this).parent().parent();
                var cVal = $(this).val();
                var eVal = $r.find("." + ($(this).hasClass("qtyGood") ? "qtyBad" : "qtyGood")).val();

                if (parseFloat($r.data("qty")) - parseFloat(eVal) < parseFloat(cVal)) {
                    $(this).val(parseFloat($r.data("qty")) - parseFloat(eVal));
                }
            });

            $('#save').bind('click', function() {
                // VALIDATE | PO information must be triggered
                if ($("#poCode").data("detail") !== true) {
                    alert("Please press right arrow key (-->) in PO Number field or fill PO Number first!");
                    return false;
                }
                
                var data = "";
                $("#main input[type='checkbox']:checked").each(function() {
                    var $r = $(this).parent().parent();

                    // VALIDATION | quantity must be filled
                    if (parseFloat($r.find(".qtyGood").val()) <= 0 && parseFloat($r.find(".qtyBad").val()) <= 0) {
                        data = "";
                        alert("VALIDATION | selected checkbox must have quantity larger than 0 (zero)!");
                        return false;
                    }

                    data = data + $("#rrCode").val() + "," + $("#rrDate").val() + "," + $("#poCode").val() + ","
                            + $("#from").val() + "," + $("#remarks").val() + ","
                            + $r.data("prs") + "," + $r.find("td:eq(2)").html() + "," + $r.find("td:eq(3)").html() + ","
                            + $r.find(".qtyGood").val() + "," + $r.find(".qtyBad").val() + "," + $r.find("td:eq(5)").html() + ",@";
                });

                if (data !== "") {
                    //console.log(data);
                    if (confirm("Continue to save this document?")) {
                        window.location.replace("?action=save&data=" + data);
                    }
                }
            });

            $('#search').bind('submit', function(e) {
                var $o = $(this).find('input[type="submit"]');
                $o.attr('disabled', true);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                /* get detail item */
                $.ajax({
                    url: 'ReceiveReport.htm',
                    data: {action: 'getPODetail', key: $('#poCode').val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for (var i = 0; i < json.length; i++) {
                            $('#main').append('<tr data-prs="' + json[i][1] + '" data-qty="' + json[i][5].replace(/,/g, "") + '"><td><input title="Receive this item" type="checkbox" /></td><td>' +
                                    json[i][3] + '</td><td>' + json[i][2] + '</td><td>' + json[i][4] + '</td><td>' +
                                    json[i][5] + '</td><td>' + json[i][6] + '</td><td><input class="qtyGood" size="2" type="text" value="0" style="font-size: x-small;" /></td><td>' +
                                    '<input class="qtyBad" size="2" type="text" value="0" style="font-size: x-small;" /></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);

//                        if($('#main').html().trim() === '')
//                            $('#search')[0].reset();
                    }
                });

                return false;
            });

//            $('#main input').live('focus', function() {
//                var $td = $(this).parent();
//                $(this).val(null);
//                $td.next().find('input').val(0);
//                
//                $(this).unbind('keyup').bind('keyup', function() {
//                    $td.next().find('input').val(parseInt($td.prev().prev().html()) - $(this).val());
//                });
//                $(this).unbind('blur').bind('blur', function() { if($(this).val() === '') $(this).val(0); });
//            });

            $('input[type="checkbox"]').live('change', function() {
                var $tr = $(this).parent().parent();
                if ($(this)[0].checked) {
                    $tr.addClass("bold");
                    $tr.find('td:eq(6) > input').focus().trigger('keyup');
                } else {
                    $tr.removeClass("bold");
                }
            });

            function setDatePicker(s) {
                $(s).datepicker({dateFormat: "dd/mm/yy"});
            }

        </script>
    </body>
</html>
