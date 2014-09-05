<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Non - Fish Stock Accounting</title>
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
                        <input type="hidden" name="item" value="NonFishStockCard" />
                        <input type="hidden" name="type" value="xls" />
                        <input type="hidden" name="params" value="<%=cDateH%>" />
                        <table class="collapse tblForm row-select">
                            <caption>Document Summary per Category &therefore; Non-Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Item Category</td>
                                    <td>
                                        <select name="prod_category" id="ddlProductCategory" style="width: 15%" >
                                            <c:if test="${model.prodCategory!=null}">
                                                <c:forEach items="${model.prodCategory}" var="category">
                                                    <option value=<c:out value="${category.categoryCode}"/> >
                                                        <c:out value="${category.categoryName}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">Document Type</td>
                                    <td>
                                        <select id="ddlDocType" style="width: 15%">
                                            <option value="TS" selected="selected">TS</option>
                                            <option value="RR"> RR </option>
                                            <option value="DR"> DR </option>
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
                                        <input type="button" value="Generate Report" name="btnGenerate" id="btnGenerate"/>
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
                    var e = document.getElementById("ddlProductCategory");
                    var cat = e.options[e.selectedIndex].value;
                    var doctype = document.getElementById("ddlDocType");
                    var dt = doctype.options[doctype.selectedIndex].value;
                    var asOf = document.getElementById("asOfDate").value;

                    if (dt == 'RR') {
                        location.href = 'NonFishStockDocumentSummary.htm?action=create&productcat=' + cat + '&asOf=' + asOf + '&doctype=' + dt;
                    } else if (dt == 'TS') {
                        location.href = 'NonFishStockDocumentSummary.htm?action=create&productcat=' + cat + '&asOf=' + asOf + '&doctype=' + dt;
                    } else {
                        location.href = 'NonFishStockDocumentSummary.htm?action=create&productcat=' + cat + '&asOf=' + asOf + '&doctype=' + dt;
                    }

                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            $('#asOfDate').datepicker({changeMonth: true, changeYear: true, dateFormat: "yy-mm-dd", altField: "#params", altFormat: "yy-mm-dd"});

        </script>

    </body>
</html>
