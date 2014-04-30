<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Return Cargo (DR)</title>
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
                    <form action="ReturnCargoDr.htm" id="form" method="get">
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
                                    <td style="width: 500px;"><input id="drFrom" size="50" type="text" required="true" /></td>
                                    <td rowspan="2">DR</td>
                                    <td>Number</td>
                                    <td><input id="drCode" size="6" type="text" pattern="[0-9]{1,}" required="true" /></td>
                                </tr>
                                <tr>
                                    <td>To</td>
                                    <td>PT. Sinar Pure Foods International</td>
                                    <td>Date</td>
                                    <td><input id="drDate" size="10" type="text" readonly="true" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <input type="submit" value="Save" />
                                        <input id="search" type="button" value="Search" />
                                        <input type="reset" value="Cancel" onclick="window.location.replace('ReturnCargoDr.htm');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select">
                            <caption>Detail</caption>
                            <thead>
                                <tr>
                                    <td rowspan="2">Item Code</td>
                                    <td rowspan="2">Item Name</td>
                                    <td colspan="2">Quantity</td>
                                    <td rowspan="2">Received</td>
                                </tr>
                                <tr>
                                    <td>Number</td>
                                    <td>U/M</td>
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
            
            $('#search').bind('click', function () {
                if($('#drCode')[0].checkValidity()) {
                    var $this = $(this);
                    $this.attr('disabled', true);
                    $this.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                    
                    $.ajax({
                        url: 'ReturnCargoDr.htm',
                        data: {action: 'getDrDetail', key: $('#drCode').val()},
                        dataType: 'json',
                        success: function (data) {
                            if (data.status) {
                                $('#main').html(null);
                                $('#drDate').val(data.date);
                                $('#drFrom').val(data.from);
                                for (var i = 0; i < data.data.length; i++) {
                                    $('#main').append('<tr><td>' + data.data[i].code + '</td><td>' + data.data[i].name + '</td><td>' + data.data[i].qty + '</td><td>' + data.data[i].uom + '</td><td><input title="Received this item" type="checkbox" /></td></tr>');
                                }
                            } else {
                                alert(data.message);
                            }
                        },
                        complete: function() {
                            $('#load').remove();
                            $this.attr('disabled', false);

                            if($('#main').html().trim() === '') $('#form')[0].reset();
                        }
                    });
                }
            });
            
//            $('#poCode').bind('change', function() {
//                $('#from').val($(this).find('option:selected').data('supplier'));
//                $('#poDate').val($(this).find('option:selected').data('date'));
//            });
//
//            $('#save').bind('click', function() {
//                var f = 0;
//                $('#main tr').each(function(i) {
//                    if($(this).find('input[type="checkbox"]')[0].checked) {
//                        $('#poster').append('<input name="detail" type="hidden" value="' + $(this).find('td:eq(2)').html() + 
//                            ':' + $(this).find('td:eq(3)').html() + ':' + $(this).find('td:eq(6)>input').val() + 
//                            ':' + $(this).find('td:eq(7)>input').val() + ':' + $(this).find('td:eq(5)').html() + ':' + i + '" />'); f = 1;
//                    }
//                });
//                
//                if(f === 1) {
//                    $('#poster').append('<input name="master" type="hidden" value="' + $('#rrCode').val() + 
//                        ':' + $('#rrDate').val() + ':' + $('#poCode').val() + ':' + $('#from').val() + '" />');
//                    $('#poster').submit();
//                }
//            });
//            
//            $('#search').bind('submit', function(e) {
//                var $o = $(this).find('input[type="submit"]');
//                $o.attr('disabled', true);
//                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
//                
//                /* get detail item */
//                $.ajax({
//                    url: 'ReceiveReport.htm',
//                    data: {action: 'getPODetail', key: $('#poCode').val()},
//                    dataType: 'json',
//                    success: function(json) {
//                        $('#main').html(null);
//                        for(var i = 0; i < json.length; i++) {
//                            var x = (json[i].product).split(":");
//                            $('#main').append('<tr><td><input title="Receive this item" type="checkbox" /></td><td>' + 
//                                x[0] + '</td><td>' + x[1] + '</td><td>' + json[i].department + '</td><td>' + 
//                                x[2] + '</td><td>' + x[3] +'</td><td><input size="2" type="text" value="0" style="font-size: x-small;" /></td><td>' +
//                                '<input size="2" type="text" value="0" style="font-size: x-small;" /></td></tr>');
//                        }
//                    },
//                    complete: function() {
//                        $('#load').remove();
//                        $o.attr('disabled', false);
//                        
//                        if($('#main').html().trim() === '')
//                            $('#search')[0].reset();
//                    }
//                });
//                
//                return false;
//            });
            
            $('#main input[type="checkbox"]').live('change', function() {
                var $tr = $(this).parent().parent();
                if($(this)[0].checked) {
                    $tr.addClass("bold");
                    $tr.find('td:eq(6) > input').focus().trigger('keyup');
                } else {
                    $tr.removeClass("bold");
                }
            });

//            function setDatePicker(s) { $(s).datepicker({ dateFormat: "dd/mm/yy" }); }

        </script>
    </body>
</html>
