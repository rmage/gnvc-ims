<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Booked Order Report</title>
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
                    <form action="Bor.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">BOR Number</td>
                                    <td><input type="text" name="tsCode" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Bor.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>List</caption>
                        <thead>
                            <tr>
                                <td rowspan="2" style="width: 15px">No</td>
                                <td rowspan="2" style="width: 50px">Action</td>
                                <td rowspan="2">BOR Number</td>
                                <td rowspan="2">BOR Date</td>
                                <td rowspan="2">Prepared By</td>
                                <td rowspan="2">Prepared Date</td>
                                <td rowspan="2">Reviewed By</td>
                                <td rowspan="2">Reviewed Date</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.b != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.b}" var="x" varStatus="i">
                                    <tr>
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                            <a href="GenerateReport.htm?action=index&item=FGBOR&type=xls&params=${x.borCode}:${x.borCode}:${x.borCode}"><img src="resources/images/printxls.gif" title="Print Booked Order Report (xls)" /></a>
                                            <c:if test="${x.preparedBy == null && model.user.userId == x.createdBy}">
                                                <a href="?action=approval&mode=p&key=${x.borCode}"><img src="resources/images/checkmark.gif" title="Prepared Booked Order Report" /></a>
                                            </c:if>
                                            <c:if test="${(x.preparedBy != null && x.reviewedBy == null) && model.user.roleCode == 'MD'}">
                                                <a href="?action=approval&mode=r&key=${x.borCode}"><img src="resources/images/checkmark.gif" title="Reviewed Booked Order Report" /></a>
                                            </c:if>
                                        </td>
                                        <td>${x.borCode}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.borDate}" /></td>
                                        <td>${x.preparedBy}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.preparedDate}" /></td>
                                        <td>${x.reviewedBy}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.reviewedDate}" /></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="14">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="Bor.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="Bor.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
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
