<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%    Date cDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Transfer &therefore; Create</title>
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
                    <form action="TransferSlip.htm" id="tsForm" method="post">
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="type"value="NORMAL" />
                        <input type="hidden" name="module" value="NF" />
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>TS Number</td>
                                    <td><input name="tsCode" pattern="[0-9]{1,}" type="text" required="true" /></td>
                                    <td>TS Date</td>
                                    <td><input id="tsDate" name="tsDate" size="10" type="text" value="<%=sdf.format(cDate)%>" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>Remarks</td>
                                    <td colspan="3"><input type="text" id="tsInfo" name="tsInfo" size="50" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input id="save" type="submit" value="Save" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('TransferSlip.htm?module=NF');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Transfer &therefore; Detail</caption>
                            <thead>
                                <tr>
                                    <td style="width: 100px;">Select SWS</td>
                                    <td colspan="7">
                                        <input type="text" id="swsCode" name="swsCode" size="10" />
                                        <input type="button" id="select" name="select" value="Select" />
                                        <%--<select id="swsCode" name="swsCode" required="true">
                                            <option value="">-- Please select SWS Number --</option>
                                            <c:forEach items="${model.s}" var="x">
                                                <option value="${x.swsCode}">
                                                    ${x.swsCode} :: <fmt:formatDate pattern="dd/MM/yyyy" value="${x.swsDate}" /> :: ${x.departmentCode}
                                                </option>
                                            </c:forEach>
                                        </select>--%>
                                    </td>
                                </tr>
                                <tr>
                                    <td>No</td>
                                    <td>Item Name</td>
                                    <td>Item Code</td>
                                    <td>Type</td>
                                    <td>Quantity Request</td>
                                    <td>Current Stocks</td>
                                    <td>Quantity Out</td>
                                    <td>Uom</td>
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
            $('#tsDate').datepicker({
                dateFormat: "dd/mm/yy",
                changeMonth: true,
                changeYear: true
            });
            
            /* BIND | Validation maximum quantity */
//            $(".detailQty").live("blur", function() {
//                if ($(this).val() > $(this).data("max")) {
//                    $(this).val($(this).data("max"));
//                }
//            });

            $('#select').bind('click', function() {
                if ($('#swsCode').val() === '') {
                    $('#main').html(null);
                    return;
                }

                $(this).attr('disabled', true);
                $(this).after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');

                $.ajax({
                    url: 'TransferSlip.htm',
                    data: {action: 'getSwsDetail', key: $("#swsCode").val()},
                    dataType: 'json',
                    success: function(json) {
                        $('#main').html(null);
                        for (var i = 0; i < json.length; i++) {
                            $('#main').append('<tr><td>' + (i + 1) + '</td><td>' + json[i][2] + '</td><td><input name="item" type="hidden" value="' + json[i][1] + '" />' + json[i][1] +
                                    '</td><td>' + json[i][3] + '</td><td>' + json[i][4] + '</td><td>' + json[i][100] + '</td><td><input class="detailQty" name="qty" pattern="[0-9]{1,}" data-max="' + json[i][4].replace(/,/g, "") + 
                                    '" type="text" /></td><td>' + json[i][5] + '</td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $('#select').attr('disabled', false);
                    }
                });
            });

            // VALIDATE | Minimum 1 (one) quantity
            $("#tsForm").bind("submit", function() {
                var b = true;
                $(".detailQty").each(function() {
                    if ($(this).val() > 0) {
                        b = false;
                    } else if ($(this).val() <= 0 && $(this).val() !== "" ) {
                        b = true;
                        return false;
                    }
                });

                if (b) {
                    return false;
                } else {
                    if (!confirm("Continue to save this document?")) {
                        return false;
                    }
                }
                    
            });

        </script>
    </body>
</html>
