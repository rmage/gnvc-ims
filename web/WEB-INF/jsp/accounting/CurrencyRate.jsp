<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Accounting : Currency Rate</title>
        <%@include file="../metaheader.jsp" %>

        <script type="text/javascript">
            $(document).ready(function() {
                $('#btnSearch').click(function() {
                    var newerDate = $('#queryNewerThanDate').val();
                    var currCode = $('#groupCurrencyCode').val();
                    location.href = "FishType.htm?search=true&typeCode=" + typeCode;
                });

                $('#btnCleanFilter').click(function() {
                    location.href = "CurrencyRate.htm";
                });
            });
        </script>

        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            input[type="text"] {
                -moz-border-bottom-colors: none;
                -moz-border-left-colors: none;
                -moz-border-right-colors: none;
                -moz-border-top-colors: none;
                background: none repeat scroll 0 0 transparent;
                border-color: -moz-use-text-color -moz-use-text-color #A6A6A6;
                border-image: none;
                border-style: none none solid;
                border-width: 0 0 1px;
                text-align: right;
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

                    <form action="CurrencyRate.htm" method="post" id="search">
                        <table class="collapse tblForm row-select">
                            <caption>Search Filter Currency Rate History</caption>
                            <%                                java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                String queryNewerThanDate = m.get("queryNewerThanDate") == null ? "" : (String) m.get("queryNewerThanDate");
                            %>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Currency Code From 
                                    </td>
                                    <td class="style1">
                                        <select name="currency_code_from" id="dropdownCurrCode" style="width: 20%" onchange="throwValue()">
                                            <option value="%">--- All Currency ---</option>
                                            <c:if test="${model.currs!=null}">
                                                <c:forEach items="${model.currs}" var="curr">
                                                    <option value=<c:out value="${curr.currencyCode}"/>
                                                            <c:if test="${curr.currencyCode == model.selectedCurrCode}"> selected </c:if>>
                                                        <c:out value="${curr.currencyCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Currency Code To 
                                    </td>
                                    <td class="style1">
                                        <select name="currency_code_to" id="dropdownCurrCodeTo" style="width: 20%" onchange="throwValueTo()">
                                            <option value="%">--- All Currency ---</option>
                                            <c:if test="${model.currs!=null}">
                                                <c:forEach items="${model.currs}" var="curr">
                                                    <option value=<c:out value="${curr.currencyCode}"/>
                                                            <c:if test="${curr.currencyCode == model.selectedCurrCode}"> selected </c:if>>
                                                        <c:out value="${curr.currencyCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Currency Type
                                    </td>
                                    <td>
                                        <select name="groupCurrencyType" id="groupCurrencyType" style="width: 20%" onchange="throwType()">
                                            <option value="%">--- All Type ---</option>
                                            <option value="DAILY">DAILY</option>
                                            <option value="WEEKLY">WEEKLY</option>
                                            <option value="MONTHLY">MONTHLY</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Newer Than
                                    </td>
                                    <td>
                                        <label>
                                            <input type="date" id="queryNewerThanDate" name="rate_date" value="<%=queryNewerThanDate%>"/>
                                            <input type="text" id="currTypeToHidden" name="currency_code_to" value="" style="display: none"/>
                                            <input type="text" id="currCodeHidden" name="currency_code_from" value="" style="display: none"/>
                                            <input type="text" id="currTypeHidden" name="currency_type" value="" style="display: none"/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>

                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" />
                                        </label>
                                        <label>
                                            <input type="button" name="button" id="btnClear" value="Clear" />
                                        </label>					
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>

                    <table class="collapse tblForm row-select" id="list">
                        <caption>Currency Rate History Result</caption>
                        <thead>
                            <tr>
                                <td>No</td>
                                <td></td>
                                <td column="currency_type">Currency Type</td>
                                <td column="currency_code_from">Currency From</td>
                                <td>Currency To</td>
                                <td>Rate Value</td>
                                <td column="rate_date">Rate Date</td>
                                <td>Week Rate Start</td>
                                <td>Week Rate End</td>
                                <td>Month</td>
                                <td>Created By</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                        </tbody>
                        <tfoot></tfoot>
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
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'CurrencyRate.htm?action=create';
                });

                $('#btnClear').click(function() {
                    location.href = 'CurrencyRate.htm';
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            function throwValue() {
                var elem = document.getElementById("currCodeHidden");
                elem.value = $('#dropdownCurrCode').val();
            }

            function throwType() {
                var elem = document.getElementById("currTypeHidden");
                elem.value = $('#groupCurrencyType').val();
            }

            function throwValueTo() {
                var elem = document.getElementById("currTypeToHidden");
                elem.value = $('#dropdownCurrCodeTo').val();
            }



            /*global $, confirm*/
//            $('input[type="button"]').on('click', function () {
//                'use strict';
//                if (confirm('Set currency rate?')) {
//                    var $t = $(this);
//                    var $i = $(this).parent().parent().find('input[type="text"]');
//                    if ($i[0].checkValidity()) {
//                        $.ajax({
//                            type: 'POST',
//                            data: {action: 'setRate', cc: $(this).parent().prev().html(), v: $i.val()},
//                            dataType: 'json',
//                            beforeSend: function () {
//                                $t.after(' <img id="load" src="resources/ui-anim_basic_16x16.gif" style="vertical-align: middle; background-color: rgb(255, 255, 255); border-radius: 4px; padding: 2.5px;" />');
//                            },
//                            success: function (data) {
//                                if(data.status === 0) {
//                                    alert('Server error, please contact your administrator!');
//                                    $i.val($i.data('old'));
//                                } else {
//                                    $i.data('old', $i.val());
//                                    $t.parent().next().html(data.date);
//                                    $t.parent().next().next().html(data.by);
//                                }
//                            },
//                            error: function (jqXHR, textStatus, errorThrown) {
//                                alert('Server error (' + textStatus + ') : ' + errorThrown + '\n' + jq);
//                                $i.val($i.data('old'));
//                            },
//                            complete: function () {
//                                $('#load').remove();
//                            }
//                        });
//                    }
//                }
//            });
//        $(function() {
//            
//            $('#btnAdd').click(function() {
//                location.href = 'CurrencyRate.htm?action=create';
//            });
//        
//        });

            $('#queryNewerThanDate').datepicker({
                dateFormat: "dd/mm/yy"
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'z:z');
        </script>
    </body>
</html>
