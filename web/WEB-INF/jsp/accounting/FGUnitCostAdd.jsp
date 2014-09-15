<%-- 
    Document   : FGUnitCostAdd
    Created on : Sep 14, 2014, 12:09:50 AM
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
                    <input type="hidden" name="action" value="save" />
                    <table class="collapse tblForm row-select">
                        <caption>Create FG Unit Cost</caption>
                        <tbody>
                            <tr>
                                <td width="20%">
                                    FG Item
                                </td>
                                <td>
                                    <select name="fg_item_id" id="fg_item_id" style="width: 40%">
                                        <c:if test="${model.fgItems!=null}">
                                            <c:forEach items="${model.fgItems}" var="fgi">
                                                <option value=<c:out value="${fgi.itemId}"/>
                                                        <c:if test="${fgi.itemCode == model.fgiCode}"> selected </c:if>>
                                                    <c:out value="${fgi.itemCode} - ${fgi.itemName}" />
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">
                                    Currency Code
                                </td>
                                <td>
                                    <select name="currency_code" id="currency_code" style="width: 40%">
                                        <c:if test="${model.currencies!=null}">
                                            <c:forEach items="${model.currencies}" var="currency">
                                                <option value=<c:out value="${currency.currencyCode}"/>
                                                        <c:if test="${currency.currencyCode == model.currencyCode}"> selected </c:if>>
                                                    <c:out value="${currency.currencyCode}" />
                                                </option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">
                                    FG Unit Cost Amount
                                </td>
                                <td>
                                    <input type="text" id="amount" name="amount" onkeypress="validate(event)" value="0"/>
                                </td>
                            </tr>
                            <tr>
                                <td width="20%">
                                    FG Unit Cost Date
                                </td>
                                <td>
                                    <input type="date" id="queryDate" name="queryDate" />
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                        <td colspan="4">
                            <span>
                                <input class="simpan" type="submit" value="Save" id="btnSave" name="btnSave" />
                                <label>
                                    <input type="button" name="button" id="btnCancel" value="Cancel" />
                                </label>
                            </span>
                        </td>
                        </tfoot>
                    </table>
                </form>
            </div>
        </div>

        <div class="span-24 last border-top">
            <div class="box">&copy; 2013 SPFI</div>
        </div>

        <script>


            $('#queryDate').datepicker({
                dateFormat: "dd/mm/yy"
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
        </script>
    </body>
</html>
