<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - View Replenish</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            java.lang.String error = (String)m.get("msg");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <table class="collapse tblForm row-select">
                        <caption>Replenish</caption>
                        <tbody class="tbl-nohover">
                            <tr class="detail_genap">
                                <td width="1%"></td>
                                <td width="19%">Replenish No</td>
                                <td class="style1"><label>${model.header.replenishNo}</label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Corporate</td>
                                <td class="style1">
                                <label>${model.user.corpId}</label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Replenish Date</td>
                                <td class="style1"><label><fmt:formatDate pattern="dd-MM-yyyy" value="${model.header.replenishDate}" /></label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>Warehouse</td>
                                <td class="style1"><label>${model.user.whCode}</label></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>Replenish Type</td>
                                <td class="style1">
                                    <c:if test="${model.header.statusApp == '-1' || model.header.statusApp == '1'}">Warehouse</c:if>
                                    <c:if test="${model.header.statusApp == '-2' || model.header.statusApp == '2'}">Corporate</c:if>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="14">
                                    <c:if test="${(model.header.statusApp == '-1' && model.login.roleCode == 'SPV') || 
                                        (model.header.statusApp == '-2' && model.login.roleCode == 'MGR')}">
                                        <input type="button" name="btnApprove" id="btnApprove" value="Approve" />
                                    </c:if>
                                    <input type="button" name="btnBack" id="btnBack" value="Back" />
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                    <br />
                    <table class="collapse tblForm row-select">
                        <caption>Product Replenish - List</caption>
                        <thead>
                            <tr>
                                <td>No.</td>
                                <td>Product</td>
                                <td>From Location</td>
                                <td>To Location</td>
                                <td>Quantity</td>
                                <td>Warehouse</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:forEach var="d" items="${model.detail}" varStatus="i" >
                            <tr>
                                <td>${i.count}</td>
                                <td>${model.product[i.index].productName}</td>
                                <td>${d.fromLocation}</td>
                                <td>${d.toLocation}</td>
                                <td>${d.qtyReplenish}</td>
                                <td>${d.whCode}</td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <% if (error != null) { %>
                    <div id="dialog" title="Info" style="display:none">
                        <ul>
                        <%
                        String[] listErrorMsg = error.split(com.app.wms.web.util.AppConstant.EOL);
                        for (int i = 0; i < listErrorMsg.length; i++) {
                            String msg = listErrorMsg[i];
                        %>
                            <li><%=msg%></li>
                        <%
                        }
                        %>
                        </ul>
                        <script type="text/javascript">
                        $(document).ready(function() {
                            $("#dialog").dialog();
                        });
                        </script>
                    </div>
                    <% } %>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &COPY; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnApprove').bind('click', function(){
                    window.location.replace("Replenish.htm?action=approve&replenishNo=${model.header.replenishNo}&type=${model.header.statusApp}");
                });
                $('#btnBack').bind('click', function(){
                    window.location.replace("Replenish.htm");
                })
            });
        </script>
    </body>
</html>
