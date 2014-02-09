<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - New Receiving Report</title>
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
                    <form action="ReceiveReport.htm" id="poster" method="post" style="display: none;">
                        <input name="action" type="hidden" value="save" />
                    </form>
                    <form action="#" id="search" method="get">
                        <table class="collapse tblForm row-select">
                            <caption>Header</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                    <td>RR Number</td>
                                    <td><input id="rrCode" name="rrCode" pattern="[0-9]{1,}" type="text" required="true" /></td>
                                    <td>RR Date</td>
                                    <td colspan="2"><input id="rrDate" name="rrDate" size="10" type="text" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>From</td>
                                    <td style="width: 500px;"><input id="from" size="50" type="text" readonly="true" /></td>
                                    <td rowspan="2">PO</td>
                                    <td>Number</td>
                                    <td>
                                        <select id="poCode" name="poCode" required="true">
                                            <option value="">-- Pick PO --</option>
                                            <c:forEach items="${model.p}" var="x">
                                                <option value="${x.poCode}" data-supplier="${x.supplierCode}" data-date="<fmt:formatDate pattern="dd-MM-yyyy" value="${x.createdDate}" />">
                                                    ${x.poCode}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td>PT. Sinar Pure Foods International</td>
                                    <td>Date</td>
                                    <td><input id="poDate" size="10" type="text" readonly="true" /></td>
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
                            <caption>Detail</caption>
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
            $('input[name="rrDate"]').datepicker({ dateFormat: "dd/mm/yy" }).datepicker("setDate", new Date());
            
            $('#poCode').bind('change', function() {
                $('#from').val($(this).find('option:selected').data('supplier'));
                $('#poDate').val($(this).find('option:selected').data('date'));
            });

            $('#save').bind('click', function() {
                var f = 0;
                $('#main tr').each(function(i) {
                    if($(this).find('input[type="checkbox"]')[0].checked) {
                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('td:eq(2)').html() + 
                            ':' + $(this).find('td:eq(3)').html() + ':' + $(this).find('td:eq(6)>input').val() + 
                            ':' + $(this).find('td:eq(7)>input').val() + ':' + $(this).find('td:eq(5)').html() + ':' + i + '" />'); f = 1;
                    }
                });
                
                if(f === 1) {
                    $('#poster').append('<input name="master" type="hidden" value="' + $('#rrCode').val() + 
                        ':' + $('#rrDate').val() + ':' + $('#poCode').val() + ':' + $('#from').val() + '" />');
                    $('#poster').submit();
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
                        for(var i = 0; i < json.length; i++) {
                            var x = (json[i].product).split(":");
                            $('#main').append('<tr><td><input title="Receive this item" type="checkbox" /></td><td>' + 
                                x[0] + '</td><td>' + x[1] + '</td><td>' + json[i].department + '</td><td>' + 
                                x[2] + '</td><td>' + x[3] +'</td><td><input size="2" type="text" value="0" style="font-size: x-small;" /></td><td>' +
                                '<input size="2" type="text" value="0" style="font-size: x-small;" /></td></tr>');
                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                        
                        if($('#main').html().trim() === '')
                            $('#search')[0].reset();
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
                if($(this)[0].checked) {
                    $tr.addClass("bold");
                    $tr.find('td:eq(6) > input').focus().trigger('keyup');
                } else {
                    $tr.removeClass("bold");
                }
            });

            function setDatePicker(s) { $(s).datepicker({ dateFormat: "dd/mm/yy" }); }

        </script>
    </body>
</html>
