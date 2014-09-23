<%-- 
    Document   : FGUnitCostList
    Created on : Sep 14, 2014, 10:09:50 AM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Finished Goods Unit Cost</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
        </div>

        <div id="content" style="display: none" class="span-24 last">
            <div class="box">
                <form action="FGUnitCost.htm" method="post" id="search">
                    <table class="collapse tblForm row-select">
                        <caption>Search FG Unit Cost</caption>
                        <tbody>
                            <tr>
                                <td width="20%">
                                    FG Item
                                </td>
                                <td>
                                    <select name="ddFgItem" id="ddFgItem" style="width: 40%" onchange="throwValueFG()">
                                        <option value="">--- All FG Item ---</option>
                                        <c:if test="${model.fgItems!=null}">
                                            <c:forEach items="${model.fgItems}" var="fgi">
                                                <option value=<c:out value="${fgi.itemId}"/>
                                                        <c:if test="${fgi.itemCode == model.fgiCode}"> selected </c:if>>
                                                    <c:out value="${fgi.itemCode} - ${fgi.itemName}" />
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                    <input type="text" id="fgIdHidden" name="fg_item_id" value="" style="display: none"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">
                                    Currency Code
                                </td>
                                <td>
                                    <select name="ddCurrCode" id="ddCurrCode" style="width: 40%" onchange="throwValue()">
                                        <option value="">--- All Currency ---</option>
                                        <c:if test="${model.currencies!=null}">
                                            <c:forEach items="${model.currencies}" var="currency">
                                                <option value=<c:out value="${currency.currencyCode}"/>
                                                        <c:if test="${currency.currencyCode == model.currencyCode}"> selected </c:if>>
                                                    <c:out value="${currency.currencyCode}" />
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                    <input type="text" id="currCodeHidden" name="currency_code" value="" style="display: none"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">
                                    FG Unit Cost Date
                                </td>
                                <td>
                                    <input type="date" id="queryDate" name="unit_cost_date" />
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                        <td colspan="4">
                            <span>
                                <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                <label>
                                    <input type="button" name="button" id="btnAdd" value="Add" />
                                </label>
                            </span>
                        </td>
                        </tfoot>
                    </table>
                </form>
                <table class="collapse tblForm row-select" id="list">
                    <caption>FG Unit Cost - List</caption>
                    <thead>
                        <tr>
                            <td>No</td>
                            <td> </td>
                            <td column="contract_number">FG Item Code</td>
                            <td >FG Item Name</td>
                            <td column="currency_code">Currency Code</td>
                            <td>Fixed Cost </td>
                            <td>Variable Cost </td>
                            <td>Date</td>
                        </tr>
                    </thead>
                    <tbody id="main"></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
        </div>

        <div class="span-24 last border-top">
            <div class="box">&copy; 2013 SPFI</div>
        </div>

        <script>

            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'FGUnitCost.htm?action=create';
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            $('#queryDate').datepicker({
                dateFormat: "dd/mm/yy"
            });

            function throwValue() {
                var elem = document.getElementById("currCodeHidden");
                elem.value = $('#ddCurrCode').val();
            }

            function throwValueFG() {
                var elem = document.getElementById("fgIdHidden");
                if ($('#ddFgItem').val() == 'ALL') {

                } else {
                    elem.value = $('#ddFgItem').val();
                }
            }

            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'x:l');
        </script>
    </body>
</html>
