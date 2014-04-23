<%@page import="java.util.Date"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Fish Oil</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
            }
            #firstUse {
                background-color: #DFEFFC;
                border-radius: 3px 3px 3px 3px;
                left: 50%;
                margin-left: -165px;
                margin-top: -95px;
                padding: 10px;
                position: fixed;
                top: 50%;
                width: 310px;
            }
            #main input {
                background: none repeat scroll 0 0 transparent;
                border: medium none;
                font-size: 9px;
                width: 35px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="foForm" action="GenerateReport.htm" method="get">
                        <input name="action" type="hidden" value="index"/>
                        <input name="item" type="hidden" value="RPDailyProduction"/>
                        <input id="params" name="params" type="hidden"/>
                        <table class="collapse tblForm row-select">
                            <caption>General Report</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">FO Date</td>
                                    <td>
                                        <select id="year" required="true">
                                            <c:forEach items="${model.year}" var="x">
                                                <option>${x}</option>
                                            </c:forEach>
                                        </select>
                                        <select id="month" required="true">
                                            <c:forEach items="${model.month}" var="x">
                                                <option value="${x[0]}" <c:if test="${x[2] == '1'}">selected="true"</c:if>>${x[1]}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Report Type</td>
                                    <td>
                                        <select name="type" required="true">
                                            <option value="xls">XLS</option>
                                            <option value="pdf">PDF</option>
                                            <option value="csv">CSV</option>
                                        </select>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input id="btnGenerate" name="btnGenerate" type="submit" value="Generate" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td colspan="21">
                                    <select id="yearL">
                                        <c:forEach items="${model.year}" var="x">
                                            <option>${x}</option>
                                        </c:forEach>
                                    </select>
                                    <select id="monthL">    
                                        <c:forEach items="${model.month}" var="x">
                                            <option value="${x[0]}" <c:if test="${x[2] == '1'}">selected="true"</c:if>>${x[1]}</option>
                                        </c:forEach>
                                    </select>
                                    <input id="view" type="button" value="View" />
                                </td>
                            </tr>
                            <tr>
                                <td rowspan="2">DATE</td>
                                <td colspan="2" rowspan="2">BEG INVENTORY</td>
                                <td colspan="9">RECEIPTS</td>
                                <td colspan="2" rowspan="2">TOTAL TO DAY</td>
                                <td colspan="3" rowspan="2">ISSUANCES</td>
                                <td colspan="2" rowspan="2">END INVENTORY</td>
                            </tr>
                            <tr>
                                <td colspan="3">SHIFT I</td>
                                <td colspan="3">SHIFT II</td>
                                <td colspan="3">SHIFT III</td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>SCRAPS</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>SCRAPS</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>SCRAPS</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                                <td>QUANTITY</td>
                                <td>DRUMS</td>
                                <td>KILOS</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                    </table>
                    <form action="FishOil.htm" id="adjForm" method="post">
                        <input name="action" type="hidden" value="adjustStock" />
                        <input id="date" name="date" type="hidden" value="" />
                        <span id="adjust" style="display: none; float: right;">
                            Adjust this Month : <input name="drums" size="6" type="text" style="font-size: smaller; text-align: right;" value="0" /> <b>DRUMS</b>
                            <input name="kilos" size="6" type="text" style="font-size: smaller; text-align: right;" value="0" readonly="true" /> <b>KILOS</b>
                            <input type="submit" value="Adjust Stock" />
                        </span>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <div id="firstUse" style="display: none">
            <table>
                <thead>
                    <tr>
                        <td colspan="3"><h5>Please fill your last end inventory (<b>31 March 2014</b>)</h5></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Drums</td>
                        <td><input id="fuDrums" type="text" value="0" /></td>
                    </tr>
                    <tr>
                        <td>Kilos</td>
                        <td><input id="fuKilos" type="text" value="0" readonly="true" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input id="fuConfirm" type="button" value="Confirm" /></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <script>
            var m = 200;
            $('#year > option:last-child').attr('selected', true);
            $('#view').bind('click', function() {
                var $o = $(this);
                $o.attr('disabled', true);
                $('#adjust').hide();
                $('#adjust').val(0);
                $o.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
                $('#main').html('');
                $('#main tr input').val('');
                $.ajax({
                    url: 'FishOil.htm',
                    data: {action: 'getContent', year: $('#yearL').val(), month: $('#monthL').val()},
                    dataType: 'json',
                    success: function(json) {
                        if (json.message) {
                            alert(json.message);
                            return false;
                        } else {
                            if (json.request) {
                                $('#firstUse').show();
                                $('#fuDrums').focus();
                                return false;
                            }
                        }
                        generateSlot();
                        if (json.data) {
                            // already have data
                            for (var i = 0; i < json.data.length; i++) {
                                var $tr = $('#main tr:eq(' + i + ')');
                                $tr.data('id', json.data[i].id);
                                $tr.find('input:eq(0)').val(json.data[i].biDrums);
                                $tr.find('input:eq(1)').val(json.data[i].biKilos);
                                $tr.find('input:eq(2)').val(json.data[i].rS1Rm);
                                $tr.find('input:eq(3)').val(json.data[i].rS1Drums);
                                $tr.find('input:eq(4)').val(json.data[i].rS1Kilos);
                                $tr.find('input:eq(5)').val(json.data[i].rS2Rm);
                                $tr.find('input:eq(6)').val(json.data[i].rS2Drums);
                                $tr.find('input:eq(7)').val(json.data[i].rS2Kilos);
                                $tr.find('input:eq(8)').val(json.data[i].rS3Rm);
                                $tr.find('input:eq(9)').val(json.data[i].rS3Drums);
                                $tr.find('input:eq(10)').val(json.data[i].rS3Kilos);
                                $tr.find('input:eq(11)').val(json.data[i].ttdDrums);
                                $tr.find('input:eq(12)').val(json.data[i].ttdKilos);
                                $tr.find('input:eq(13)').val(json.data[i].iDrums);
                                $tr.find('input:eq(14)').val(json.data[i].iKilos);
                                $tr.find('input:eq(15)').val(json.data[i].iPrice);
                                $tr.find('input:eq(16)').val(json.data[i].eiDrums);
                                $tr.find('input:eq(17)').val(json.data[i].eiKilos);
                            }
                            setXVal();
                            $('#main').data('id', json.foId);
                            checkFullSheetForAdjust();
                            var adjust = json.adjust.split(':');
                            $('#adjust input:eq(0)').val(adjust[0]).next().next().val(adjust[1]);
                        } else {
                            // first use
                            var $tr = $('#main tr:first-child');
                            $tr.find('td:eq(1) input').data('xval', json.drums);
                            $tr.find('td:eq(2) input').data('xval', json.kilos);
                            // $tr.find('input').attr('disabled', false); 
                            getXVal(); // ajax funtion on success
                            var firstMonth = function(json) {
                                if (json.message) {
                                    $('#main').data('id', json.foId);
                                    $tr.data('id', json.id);
                                    setXVal(0);
                                    $tr.next().find('input').attr('disabled', false);
                                    getXVal();
                                } else {
                                    getXVal(0);
                                }
                            };
                            // send ajax request
                            var _detail = $tr.data('date');
                            $('#main tr:first-child input').each(function() {
                                _detail += (':' + $(this).val());
                            });
                            callAjaxUpdate({
                                action: 'saveContent',
                                master: $('#monthL').val() + ':' + $('#yearL').val(),
                                detail: _detail,
                                status: 0}, firstMonth);
                        }
//                        $('#main').html(null);
//                        for(var i = 0; i < json.length; i++) {
//                            $('#main').append('');
//                        }
                    },
                    complete: function() {
                        $('#load').remove();
                        $o.attr('disabled', false);
                    }
                });
            });
            $('#adjust input:eq(0)').bind('blur', function() {
                $(this).next().next().val($(this).val() * m);
            });

            /*_TEST_GLOBAL_AJAX_FUNCTION_____*/
            function callAjaxUpdate(_data, _fnc) {
                $.ajax({
                    url: 'FishOil.htm',
                    data: _data,
                    dataType: 'json',
                    success: _fnc
                });
            }

            function setXVal(tr) {
                if (tr) {
                    $('#main tr:eq(' + tr + ') input').each(function() {
                        $(this).data('xval', $(this).val());
                    });
                } else {
                    $('#main input').each(function() {
                        $(this).data('xval', $(this).val());
                    });
                }
            }

            function getXVal(tr) {
                if (tr) {
                    $('#main tr:eq(' + tr + ') input').each(function() {
                        $(this).val($(this).data('xval'));
                    });
                } else {
                    $('#main input').each(function(i) {
                        if (!$(this).attr('disabled')) {
                            if (i % 20 === 16)
                                if ($(this).data('xval'))
                                    $(this).val($(this).data('xval'));
                                else
                                    $(this).val($(this).parent().parent().find('input:eq(0)').val());
                            else if (i % 20 === 17)
                                if ($(this).data('xval'))
                                    $(this).val($(this).data('xval'));
                                else
                                    $(this).val($(this).parent().parent().find('input:eq(1)').val());
                            else if (i % 20 === 18 || i % 20 === 19) {
                            }
                            else
                                $(this).val($(this).data('xval') ? $(this).data('xval') : 0);
                        } else {
                            return false;
                        }
                    });
                }
            }

            $('#main .update').live('click', function() {
                var $this = $(this);
                if (!$this.data('process')) {
                    $this.data('process', true);

                    // ajax funtion on success
                    var currentRow = function(json) {
                        if (json.message) {
                            $('#main').data('id', json.foId);
                            $tr.data('id', json.id);
                            setXVal($('#main tr').index($tr));
                            alert('Success update data!');
                        } else {
                            getXVal($('#main tr').index($tr));
                        }

                        $this.data('process', false);
                        checkFullSheetForAdjust();
                    };

                    // send ajax request
                    var $tr = $(this).parent().parent();
                    var _detail = $tr.data('date');
                    $('#main tr:eq(' + $('#main tr').index($tr) + ') input').each(function() {
                        if ($(this).val() === '')
                            $(this).val(0).trigger('blur');
                        _detail += (':' + $(this).val());
                    });
                    callAjaxUpdate({
                        action: 'saveContent',
                        foId: $('#main').data('id'),
                        id: ($tr.data('id') ? $tr.data('id') : 0),
                        detail: _detail,
                        status: ($tr.data('id') ? 1 : 0)}, currentRow);
                }
            });

            var reNumber = new RegExp('^[0-9]+$');
            $('#main input').live('blur', function() {
                var $tr = $(this).parent().parent();
                if ($('#main tr').index($tr) !== 0) {
                    $tr.find('input:eq(0)').val($tr.prev().find('input:eq(16)').val());
                    $tr.find('input:eq(1)').val($tr.prev().find('input:eq(17)').val());
                }

                var i = 0;
                if (reNumber.test($tr.find('input:eq(3)').val())) {
                    i += parseInt($tr.find('input:eq(3)').val());
                }
                if (reNumber.test($tr.find('input:eq(6)').val())) {
                    i += parseInt($tr.find('input:eq(6)').val());
                }
                if (reNumber.test($tr.find('input:eq(9)').val())) {
                    i += parseInt($tr.find('input:eq(9)').val());
                }
                $tr.find('input:eq(11)').val(i);
                if (reNumber.test($tr.find('input:eq(13)').val())) {
                    i -= parseInt($tr.find('input:eq(13)').val());
                }
                if (reNumber.test($tr.find('input:eq(0)').val())) {
                    i += parseInt($tr.find('input:eq(0)').val());
                }
                $tr.find('input:eq(16)').val(i);
                i = 0;

                if (reNumber.test($tr.find('input:eq(4)').val())) {
                    i += parseInt($tr.find('input:eq(4)').val());
                }
                if (reNumber.test($tr.find('input:eq(7)').val())) {
                    i += parseInt($tr.find('input:eq(7)').val());
                }
                if (reNumber.test($tr.find('input:eq(10)').val())) {
                    i += parseInt($tr.find('input:eq(10)').val());
                }
                $tr.find('input:eq(12)').val(i);
                if (reNumber.test($tr.find('input:eq(14)').val())) {
                    i -= parseInt($tr.find('input:eq(14)').val());
                }
                if (reNumber.test($tr.find('input:eq(1)').val())) {
                    i += parseInt($tr.find('input:eq(1)').val());
                }
                $tr.find('input:eq(17)').val(i);
                i = 0;
            });

            $('#fuConfirm').bind('click', function() {
                $('#firstUse').hide();
                $.ajax({
                    url: 'FishOil.htm',
                    data: {action: 'confirmFirstUse', drums: $('#fuDrums').val(), kilos: $('#fuKilos').val()},
                    dataType: 'json',
                    success: function(json) {
                        if (json.message)
                            $('#view').trigger('click');
                        else
                            $('#firstUse').show();
                    }
                });
            });
            $('#fuDrums').bind('blur', function() {
                $('#fuKilos').val($(this).val() * m);
            });
            setTimeout(function() {
                $('#view').trigger('click');
            }, 1500);

            /*
             *  Generate Button
             */
            $('#foForm').bind('submit', function() {
                $('#params').val($('#year').val() + ':' + $('#month').val() + ':' + 'FISH OIL INVENTORY:FISH_OIL');
            });
            $('#adjForm').bind('submit', function() {
                $('#date').val($('#yearL').val() + '-' + $('#monthL').val() + '-01');
            });

            /*
             *  Function Group
             */
            function generateSlot() {
                var limit = new Date($('#yearL').val(), (parseInt($('#monthL').val())), 0);
                for (var i = 0; i < limit.getDate(); i++) {
                    $('#main').append('<tr><td><img class="update" src="resources/images/copy.png" style="float: right;" title="Click to update!" /><span>' + (i + 1) + ' ' + $('#monthL > option:selected').html() + ' ' + $('#yearL').val() + '</span></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" /></td>' +
                            '<td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" readonly="true" /></td>' +
                            '<td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" /></td><td><input type="text" disabled="true" readonly="true" /></td><td><input type="text" disabled="true" readonly="true" /></td></tr>');
                    $('#main tr:last-child').data('date', (i + 1) + '/' + $('#monthL').val() + '/' + $('#yearL').val());
                }
                var date = new Date();
                var day = date.getDate();
                if (date.getFullYear() >= $('#yearL').val() && date.getMonth() + 1 > $('#monthL').val())
                    day = 31;
                for (var i = 0; i < day; i++) {
                    $('#main tr:eq(' + i + ')').find('input').attr('disabled', false);
                }

                $('#main tr').each(function() {
                    $(this).find('input:eq(3),input:eq(6),input:eq(9),input:eq(13)').live('blur', function() {
                        $(this).parent().next().find('input').val($(this).val() * m);
                    });
                });
            }

            function checkFullSheetForAdjust() {
                var c = true;
                $('#main tr').each(function() {
                    if ($(this).find('input:eq(17)').val() === '') {
                        c = false;
                        return false;
                    }
                });

                if (c) {
                    $('#adjust').show();
                }
            }
        </script>
    </body>
</html>
