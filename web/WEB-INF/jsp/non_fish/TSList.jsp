<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Transfer Slip</title>
        <%@include file="../metaheader.jsp" %>
        <style>
            :-moz-ui-invalid:not(output) { box-shadow: none; }
            #fyaQPanelBody tr:nth-child(2n+1) {
                background-color: #EEEEEE;
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
                    <form action="TransferSlip.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">TS Number</td>
                                    <td><input type="text" name="tsCode" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <select id="type"><option value="NORMAL">Normal</option><option value="OTHERS">Others</option><!--<option value="BAD_STOCKS">Bad Stocks</option>--></select>
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('TransferSlip.htm?action=create&module=<%= request.getParameter("module")%>&type=' + document.getElementById('type').value);" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td>TS Number</td>
                                <td>TS Date</td>
                                <td>TS Type</td>
                                <td>Sws Number</td>
                                <td>Info</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.t != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.t}" var="x" varStatus="i">
                                    <tr>
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                            <a href="GenerateReport.htm?action=index&item=IMTS&type=xls&params=${x.tsCode}"><img src="resources/images/printxls.gif" title="Print Transfer Slip (xls)" /></a>
                                        </td>
                                        <td>${x.tsCode}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.tsDate}" /></td>
                                        <td>${x.tsType}</td>
                                        <td>${x.swsCode == 0 ? '-' : x.swsCode}</td>
                                        <td>${x.tsInfo}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="7">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="TransferSlip.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="TransferSlip.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
                                    </c:if>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </div>

            <!-- footer HERE -->
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>

        <!-- javascript block HERE -->
        <div id="fyaQPanel" class="div-dtl" style="width: 100%; display: none;" ondblclick="fyaQPanel(0)"></div>
        <script>

        </script>
    </body>
</html>
