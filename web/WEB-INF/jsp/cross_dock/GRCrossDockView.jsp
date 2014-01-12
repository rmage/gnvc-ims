<%-- created by FYA --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Good Receive Cross Dock Update</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form id="GRForm" method="post" action="GRCrossDock.htm" autocomplete="off">
                        <input type="hidden" name="action" value="edit" >
                        <input type="hidden" name="grCode" value="${model.list.GRCode}" />
                        <input type="hidden" id="detail" name="detail" value="" />
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Good Receive Cross Dock</caption>
                            <tbody class="tbl-nohover">
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Good Receive No</td>
                                    <td class="style1"><label>${model.list.GRCode}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Purchase Order No</td>
                                    <td class="style1"><label>${model.list.updatedBy}</label></td>
                                </tr>
                                <tr style="background: none repeat scroll 0% 0% rgb(250, 250, 250);">
                                    <td class="style1">Good Receive Date</td>
                                    <td class="style1"><fmt:formatDate pattern="dd/MM/yyyy" value="${model.list.GRDate}" /></td>
                                </tr>
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="button" value="Print" id="btnSave" name="btnSave" class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                        </label>
                                        <input type="button" value="Back" id="btnBack" name="button" class="style1 ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </span>
                                </td>
                            </tr></tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <caption class="ui-widget-header left span-7 ui-corner-tr ui-corner-tl" style="margin-bottom: -1px; position: relative;">Good Receive Cross Dock - Detail</caption>
                            <thead>
                                <tr>
                                    <td>Product Code</td>
                                    <td>Product Name</td>
                                    <td>Quantity</td>
                                    <td>Type</td>
                                    <td>Status</td>
                                </tr>
                            </thead>
                            <tbody id="list" class="tbl-nohover">
                                <c:forEach items="${model.listDetail}" var="list" varStatus="status">
                                    <c:set var="listGR" value="${model.listGR[status.index]}" />
                                    <tr class="detail-item" code="${listGR.GRCodeDtl}" status="${listGR.GRStatus}" data="C">
                                        <td>${list.productCode}</td>  
                                        <td>${model.listProduct[status.index].productName}</td>  
                                        <td>${list.POQty}</td>  
                                        <td>${list.POType}</td>
                                        <td>
                                            <c:if test="${listGR.GRStatus == 'Y'}">
                                                <input type="checkbox" class="list-status" checked disabled />
                                            </c:if>
                                            <c:if test="${listGR.GRStatus == 'N'}">
                                                <input type="checkbox" class="list-status" disabled />
                                            </c:if>
                                        </td>  
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">&copy; 2013 SPFI</div>
            </div>
        </div>
        <script type="text/javascript">
            $(function(){
                $('#btnBack').unbind('click').click(function(){
                    window.location.href = "?action=list";
                });
            });
        </script>
    </body>
</html>