<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Purchase Order</title>
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
                    <form action="Purchase.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PO Number</td>
                                    <td><input type="text" name="ponumber" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Purchase.htm?action=create');" />
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
                                <td>PO Number</td>
                                <td>Po Date</td>
                                <td>Supplier Code</td>
                                <td>Supplier Name</td>
                                <td>Supplier Phone</td>
                                <td>Grand Total</td>
                                <td>Approval</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.p != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.p}" var="x" varStatus="i">
                                    <c:set var="xx" value="${fn:split(x.supplierCode, ':')}" />
                                    <tr class="ganjil">
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                            <c:if test="${x.isApproved == 'N'}">
                                                <a href="?action=approval&po=${x.poCode}"><img src="resources/images/checkmark.gif" title="Approve this Purchase Order" /></a>
                                            </c:if>
                                            <c:if test="${x.approvedDate != null}">
                                                <a href="GenerateReport.htm?action=index&item=PPoForm&type=xls&params=${xx[0]}:${x.poCode}"><img src="resources/images/printxls.gif" title="Print PO Form (xls)" /></a>
                                            </c:if>
                                        </td>
                                        <td>${x.poCode}</td>
                                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${x.poDate}" /></a></td>
                                        <td>${xx[0]}</td>
                                        <td>${xx[1]}</td>
                                        <td>${xx[2]}</td>
                                        <td>${model.bd[i.index]}</td>
                                        <td>
                                            <c:if test="${x.approvedDate == null}">Waiting List ${x.approvedBy}</c:if>
                                            <c:if test="${x.approvedDate != null}">By <b>${x.approvedBy}</b> at <fmt:formatDate pattern="dd-MM-yyyy" value="${x.approvedDate}" /></c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="9">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="Purchase.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="Purchase.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
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
            $('.box > table > tbody tr').each(function() {
                var $o = $(this).find('td:eq(7)');
                $o.html( numberWithCommas($o.html()) );
            });
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>
