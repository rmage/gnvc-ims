<%@page import="com.app.wms.engine.db.dto.CurrencyRate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting : Add Currency Rate</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function() {
                $('#addForm').validationEngine('attach');
            });

            $("#monthPicker").datepicker({
                dateFormat: 'MM yy',
                changeMonth: true,
                changeYear: true,
                showButtonPanel: true,
                onClose: function(dateText, inst) {
                    var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    $(this).val($.datepicker.formatDate('MM yy', new Date(year, month, 1)));
                }
            });

            $("#monthPicker").focus(function() {
                $(".ui-datepicker-calendar").hide();
                $("#ui-datepicker-div").position({
                    my: "center top",
                    at: "center bottom",
                    of: $(this)
                });
            });
        </script>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            CurrencyRate dto = (CurrencyRate) m.get("dto");
            String mode = (String) m.get("mode");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE, dd MM yyyy");
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="CurrencyRate.htm" method="post" name="form" id="addForm" onsubmit="return validateForm();">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Currency Rate - Add</caption>
                            <tbody class="tbl-nohover">                      
                                <tr>
                                    <td style="width: 20%">Currency Code From</td>
                                    <td class="style1" >
                                        <select name="groupCurrencyCodeFrom" id="groupCurrencyCodeFrom" style="width: 40%">
                                            <c:if test="${model.currs!=null}">
                                                <c:forEach items="${model.currs}" var="curr">
                                                    <option value=<c:out value="${curr.currencyCode}"/>
                                                            <c:if test="${curr.currencyCode==model.currencyCode}"> selected </c:if>>
                                                        <c:out value="${curr.currencyCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%">Currency Code To</td>
                                    <td class="style1" >
                                        <select name="groupCurrencyCodeTo" id="groupCurrencyCodeTo" style="width: 40%">
                                            <c:if test="${model.currs!=null}">
                                                <c:forEach items="${model.currs}" var="curr">
                                                    <option value=<c:out value="${curr.currencyCode}"/>
                                                            <c:if test="${curr.currencyCode==model.currencyCode}"> selected </c:if>>
                                                        <c:out value="${curr.currencyCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Rate Value</td>
                                    <td>
                                        <label>
                                            <input type="text" name="rateValue" value="" size="50" required="true" style="width: 40%" onkeypress="validate(event)"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%">Currency Type</td>
                                    <td class="style1" >
                                        <select name="groupCurrencyType" id="groupCurrencyType" style="width: 20%" onchange="onChangeType()">
                                            <option value="daily">DAILY</option>
                                            <option value="weekly">WEEKLY</option>
                                            <option value="monthly">MONTHLY</option>
                                        </select>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr id="rowRowDate">
                                    <td>Rate Date</td>
                                    <td>
                                        <label>
                                            <input type="text" name="rateDate" id="rateDate" required="true" style="width: 20%" readonly="true" value="<%= dateFormat.format(new Date())%>"/>
                                        </label>
                                        <label>*</label>
                                    </td>
                                </tr>
                                <tr id="rowRowPeriodWeekly">
                                    <td>Rate Period</td>
                                    <td class="style1" >
                                        <label>
                                            <input type="text" name="rateWeekStart" id="rateWeekStart" required="true" style="width: 20%" readonly="true" value="<%= dateFormat.format(new Date())%>" />
                                        </label>
                                        <label> <input type="text" name="rateWeekEnd" id="rateWeekEnd" required="true" style="width: 20%" readonly="true"/> </label>
                                    </td>
                                </tr>
                                <tr id="rowRowPeriodMonthly">
                                    <td>Rate Period</td>
                                    <td class="style1" >
                                        <label>
                                            <input type="text" name="monthPicker" id="monthPicker" required="true" style="width: 20%" readonly="true" class="monthpicker" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover"></tbody>
                            <tfoot class="ui-widget-header">
                                <tr>
                                    <td colspan="7">
                                        <label>
                                            <input type="submit" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">

            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'CurrencyRate.htm';
                });
                $('#btnSave').click(function() {
                    var currSelect = document.getElementById('groupCurrencyCode');
                    if (currSelect.selectedIndex == 0) {
                        alert("Please select Currency Code...");
                        document.getElementById("groupCurrencyCode").focus();
                        return false;
                    }
                });

                $("#rowRowPeriodWeekly").hide();
                $("#rowRowPeriodMonthly").hide();

                var date1 = $("#rateWeekStart").datepicker("getDate");
                var date2 = new Date(date1.getTime());
                date2.setDate(date2.getDate() + 7);
                $('#ui-datepicker-div').css('display', 'none');
                $("#rateWeekEnd").datepicker("setDate", date2);

                $('.monthpicker').monthpicker();

            });

            function validate(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                if (key == 37 || key == 38 || key == 39 || key == 40 ||
                        key == 8 || key == 46 || key == 9 || key == 10 || key == 36
                        || key == 13) {
                    return;
                }
                key = String.fromCharCode(key);
                var regex = /[0-9]|\./;
                if (!regex.test(key)) {
                    theEvent.returnValue = false;
                    if (theEvent.preventDefault)
                        theEvent.preventDefault();
                }
            }

            function onChangeType() {
                var idx = $("select[name='groupCurrencyType'] option:selected").index();
                if (idx == 0) {
                    $("#rowRowDate").show();
                    $("#rowRowPeriodWeekly").hide();
                    $("#rowRowPeriodMonthly").hide();
                } else if (idx == 1) {
                    $("#rowRowPeriodWeekly").show();
                    $("#rowRowDate").hide();
                    $("#rowRowPeriodMonthly").hide();
                } else {
                    $("#rowRowPeriodMonthly").show();
                    $("#rowRowPeriodWeekly").hide();
                    $("#rowRowDate").hide();
                }
            }

            $('#rateDate').datepicker({
                dateFormat: "dd/mm/yy"
            });
            $('#rateWeekStart').datepicker({
                dateFormat: "dd/mm/yy"
            });
            $('#rateWeekEnd').datepicker({
                dateFormat: "dd/mm/yy"
            });

            $("#rateWeekStart").datepicker("option", "onSelect", function(dateText, inst) {
                var date1 = $.datepicker.parseDate(inst.settings.dateFormat || $.datepicker._defaults.dateFormat, dateText, inst.settings);
                var date2 = new Date(date1.getTime());
                date2.setDate(date2.getDate() + 7);
                $("#rateWeekEnd").datepicker("setDate", date2);
            });

            function validateForm() {
                var from = document.getElementById("groupCurrencyCodeFrom");
                var strFrom = from.options[from.selectedIndex].value;
                var to = document.getElementById("groupCurrencyCodeTo");
                var strTo = to.options[to.selectedIndex].value;
                if (strFrom == strTo) {
                    alert("Please select different currency code.");
                    return false;
                } else {
                    return true;
                }
            }

//         THIS CODE BELOW IS JQUERY
            /*
             * jQuery UI Monthpicker
             *
             * @licensed MIT <see below>
             * @licensed GPL <see below>
             *
             * @author Luciano Costa
             * http://lucianocosta.info/jquery.mtz.monthpicker/
             *
             * Depends:
             *  jquery.ui.core.js
             */

            /**
             * MIT License
             * Copyright (c) 2011, Luciano Costa
             * 
             * Permission is hereby granted, free of charge, to any person obtaining a copy 
             * of this software and associated documentation files (the "Software"), to deal 
             * in the Software without restriction, including without limitation the rights 
             * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell 
             * copies of the Software, and to permit persons to whom the Software is 
             * furnished to do so, subject to the following conditions:
             * 
             * The above copyright notice and this permission notice shall be included in
             * all copies or substantial portions of the Software.
             * 
             * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
             * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
             * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
             * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
             * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
             * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
             * THE SOFTWARE.
             */
            /**
             * GPL LIcense
             * Copyright (c) 2011, Luciano Costa
             * 
             * This program is free software: you can redistribute it and/or modify it 
             * under the terms of the GNU General Public License as published by the 
             * Free Software Foundation, either version 3 of the License, or 
             * (at your option) any later version.
             * 
             * This program is distributed in the hope that it will be useful, but 
             * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
             * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
             * for more details.
             * 
             * You should have received a copy of the GNU General Public License along 
             * with this program. If not, see <http://www.gnu.org/licenses/>.
             */

            ;
            (function($) {

                var methods = {
                    init: function(options) {
                        return this.each(function() {
                            var
                                    $this = $(this),
                                    data = $this.data('monthpicker'),
                                    year = (options && options.year) ? options.year : (new Date()).getFullYear(),
                                    settings = $.extend({
                                        pattern: 'mm/yyyy',
                                        selectedMonth: null,
                                        selectedMonthName: '',
                                        selectedYear: year,
                                        startYear: year - 10,
                                        finalYear: year + 10,
                                        monthNames: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                                        id: "monthpicker_" + (Math.random() * Math.random()).toString().replace('.', ''),
                                        openOnFocus: true,
                                        disabledMonths: []
                                    }, options);

                            settings.dateSeparator = settings.pattern.replace(/(mmm|mm|m|yyyy|yy|y)/ig, '');

                            // If the plugin hasn't been initialized yet for this element
                            if (!data) {

                                $(this).data('monthpicker', {
                                    'target': $this,
                                    'settings': settings
                                });

                                if (settings.openOnFocus === true) {
                                    $this.on('focus', function() {
                                        $this.monthpicker('show');
                                    });
                                }

                                $this.monthpicker('parseInputValue', settings);

                                $this.monthpicker('mountWidget', settings);

                                $this.on('monthpicker-click-month', function(e, month, year) {
                                    $this.monthpicker('setValue', settings);
                                    $this.monthpicker('hide');
                                });

                                // hide widget when user clicks elsewhere on page
                                $this.addClass("mtz-monthpicker-widgetcontainer");
                                $(document).unbind("mousedown.mtzmonthpicker").on("mousedown.mtzmonthpicker", function(e) {
                                    if (!e.target.className || e.target.className.toString().indexOf('mtz-monthpicker') < 0) {
                                        $(this).monthpicker('hideAll');
                                    }
                                });
                            }
                        });
                    },
                    show: function() {
                        $(this).monthpicker('hideAll');
                        var widget = $('#' + this.data('monthpicker').settings.id);
                        widget.css("top", this.offset().top + this.outerHeight());
                        if ($(window).width() > (widget.width() + this.offset().left)) {
                            widget.css("left", this.offset().left);
                        } else {
                            widget.css("left", this.offset().left - widget.width());
                        }
                        widget.show();
                        widget.find('select').focus();
                        this.trigger('monthpicker-show');
                    },
                    hide: function() {
                        var widget = $('#' + this.data('monthpicker').settings.id);
                        if (widget.is(':visible')) {
                            widget.hide();
                            this.trigger('monthpicker-hide');
                        }
                    },
                    hideAll: function() {
                        $(".mtz-monthpicker-widgetcontainer").each(function() {
                            if (typeof ($(this).data("monthpicker")) != "undefined") {
                                $(this).monthpicker('hide');
                            }
                        });
                    },
                    setValue: function(settings) {
                        var
                                month = settings.selectedMonth,
                                year = settings.selectedYear;

                        if (settings.pattern.indexOf('mmm') >= 0) {
                            month = settings.selectedMonthName;
                        } else if (settings.pattern.indexOf('mm') >= 0 && settings.selectedMonth < 10) {
                            month = '0' + settings.selectedMonth;
                        }

                        if (settings.pattern.indexOf('yyyy') < 0) {
                            year = year.toString().substr(2, 2);
                        }

                        if (settings.pattern.indexOf('y') > settings.pattern.indexOf(settings.dateSeparator)) {
                            this.val(month + settings.dateSeparator + year);
                        } else {
                            this.val(year + settings.dateSeparator + month);
                        }

                        this.change();
                    },
                    disableMonths: function(months) {
                        var
                                settings = this.data('monthpicker').settings,
                                container = $('#' + settings.id);

                        settings.disabledMonths = months;

                        container.find('.mtz-monthpicker-month').each(function() {
                            var m = parseInt($(this).data('month'));
                            if ($.inArray(m, months) >= 0) {
                                $(this).addClass('ui-state-disabled');
                            } else {
                                $(this).removeClass('ui-state-disabled');
                            }
                        });
                    },
                    mountWidget: function(settings) {
                        var
                                monthpicker = this,
                                container = $('<div id="' + settings.id + '" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" />'),
                                header = $('<div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all mtz-monthpicker" />'),
                                combo = $('<select class="mtz-monthpicker mtz-monthpicker-year" />'),
                                table = $('<table class="mtz-monthpicker" />'),
                                tbody = $('<tbody class="mtz-monthpicker" />'),
                                tr = $('<tr class="mtz-monthpicker" />'),
                                td = '',
                                selectedYear = settings.selectedYear,
                                option = null,
                                attrSelectedYear = $(this).data('selected-year'),
                                attrStartYear = $(this).data('start-year'),
                                attrFinalYear = $(this).data('final-year');

                        if (attrSelectedYear) {
                            settings.selectedYear = attrSelectedYear;
                        }

                        if (attrStartYear) {
                            settings.startYear = attrStartYear;
                        }

                        if (attrFinalYear) {
                            settings.finalYear = attrFinalYear;
                        }

                        container.css({
                            position: 'absolute',
                            zIndex: 999999,
                            whiteSpace: 'nowrap',
                            width: '250px',
                            overflow: 'hidden',
                            textAlign: 'center',
                            display: 'none',
                            top: monthpicker.offset().top + monthpicker.outerHeight(),
                            left: monthpicker.offset().left
                        });

                        combo.on('change', function() {
                            var months = $(this).parent().parent().find('td[data-month]');
                            months.removeClass('ui-state-active');
                            if ($(this).val() == settings.selectedYear) {
                                months.filter('td[data-month=' + settings.selectedMonth + ']').addClass('ui-state-active');
                            }
                            monthpicker.trigger('monthpicker-change-year', $(this).val());
                        });

                        // mount years combo
                        for (var i = settings.startYear; i <= settings.finalYear; i++) {
                            var option = $('<option class="mtz-monthpicker" />').attr('value', i).append(i);
                            if (settings.selectedYear == i) {
                                option.attr('selected', 'selected');
                            }
                            combo.append(option);
                        }
                        header.append(combo).appendTo(container);

                        // mount months table
                        for (var i = 1; i <= 12; i++) {
                            td = $('<td class="ui-state-default mtz-monthpicker mtz-monthpicker-month" style="padding:5px;cursor:default;" />').attr('data-month', i);
                            if (settings.selectedMonth == i) {
                                td.addClass('ui-state-active');
                            }
                            td.append(settings.monthNames[i - 1]);
                            tr.append(td).appendTo(tbody);
                            if (i % 3 === 0) {
                                tr = $('<tr class="mtz-monthpicker" />');
                            }
                        }

                        tbody.find('.mtz-monthpicker-month').on('click', function() {
                            var m = parseInt($(this).data('month'));
                            if ($.inArray(m, settings.disabledMonths) < 0) {
                                settings.selectedYear = $(this).closest('.ui-datepicker').find('.mtz-monthpicker-year').first().val();
                                settings.selectedMonth = $(this).data('month');
                                settings.selectedMonthName = $(this).text();
                                monthpicker.trigger('monthpicker-click-month', $(this).data('month'));
                                $(this).closest('table').find('.ui-state-active').removeClass('ui-state-active');
                                $(this).addClass('ui-state-active');
                            }
                        });

                        table.append(tbody).appendTo(container);

                        container.appendTo('body');
                    },
                    destroy: function() {
                        return this.each(function() {
                            $(this).removeClass('mtz-monthpicker-widgetcontainer').unbind('focus').removeData('monthpicker');
                        });
                    },
                    getDate: function() {
                        var settings = this.data('monthpicker').settings;
                        if (settings.selectedMonth && settings.selectedYear) {
                            return new Date(settings.selectedYear, settings.selectedMonth - 1);
                        } else {
                            return null;
                        }
                    },
                    parseInputValue: function(settings) {
                        if (this.val()) {
                            if (settings.dateSeparator) {
                                var val = this.val().toString().split(settings.dateSeparator);
                                if (settings.pattern.indexOf('m') === 0) {
                                    settings.selectedMonth = val[0];
                                    settings.selectedYear = val[1];
                                } else {
                                    settings.selectedMonth = val[1];
                                    settings.selectedYear = val[0];
                                }
                            }
                        }
                    }

                };

                $.fn.monthpicker = function(method) {
                    if (methods[method]) {
                        return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
                    } else if (typeof method === 'object' || !method) {
                        return methods.init.apply(this, arguments);
                    } else {
                        $.error('Method ' + method + ' does not exist on jQuery.mtz.monthpicker');
                    }
                };

            })(jQuery);


        </script> 
    </body>
</html>
