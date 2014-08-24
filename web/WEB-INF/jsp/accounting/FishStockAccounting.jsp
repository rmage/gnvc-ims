<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Stock Accounting</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            String cDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            String cDateH = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="" id="search" method="post">
                        <input type="hidden" name="action" value="index" />
                        <input type="hidden" name="item" value="FishStockCard" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" name="params" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card Accounting &therefore; Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Fish Code</td>
                                    <td>
                                        <select name="dropdownFishCode" id="dropdownFishCode" onchange="throwValue()">
                                            <c:if test="${model.fishes!=null}">
                                                <c:forEach items="${model.fishes}" var="fish">
                                                    <option value=<c:out value="${fish.code}"/>
                                                            <c:if test="${fish.code == model.selectedfishes}"> selected </c:if>>
                                                        <c:out value="${fish.code}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td><input id="asOfDate" type="date" name="date" value="<%=cDateH%>" readonly="true"/></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="button" value="Generate Stock Card Accounting Fish" name="btnGenerate" id="btnGenerate"/>
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

        <script>
            $(function() {
                $('#btnGenerate').click(function() {
                    var elem = document.getElementById("dropdownFishCode");
                    var asOf = document.getElementById("asOfDate").value;
                    elem.value = $('#dropdownFishCode').val();
                    var str = elem.value;
                    str = str.replace(/_/g, ' ');
                    location.href = 'FishStockAccounting.htm?action=create&fishcode=' + str + '&asOf=' + asOf;
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            function throwValue() {
                var elem = document.getElementById("dropdownFishCode");
                elem.value = $('#dropdownFishCode').val();
                var str = elem.value;
                str = str.replace(/_/g, ' ');
            }

            $('#asOfDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "yy-mm-dd", altField: "#params", altFormat: "yy-mm-dd"});
        </script>

    </body>
</html>
