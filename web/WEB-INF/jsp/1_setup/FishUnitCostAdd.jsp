<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Fish Unit Cost List</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishUnitCostList.htm" method="post" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Add Fish Cost</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Contract Number
                                    </td>
                                    <td >
                                        <input type="text" id="contractNumber" name="contractNumber" required="true" value="${model.contractNumber}"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Contract Period
                                    </td>
                                    <td>
                                        <input type="date" id="contractBeginDate" name="contractBeginDate" required="true" value="${model.startDate}" readonly="true"/> Start
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">

                                    </td>
                                    <td >
                                        <input type="date" id="contractEndDate" name="contractEndDate" required="true" value="${model.endDate}" readonly="true"/> End
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        Supplier Name
                                    </td>
                                    <td>
                                        <select name="groupSupplierId" id="groupSupplierId" style="width: 30%">
                                            <c:if test="${model.suppliers!=null}">
                                                <c:forEach items="${model.suppliers}" var="supplier">
                                                    <option value=<c:out value="${supplier.id}"/> >
                                                        <c:out value="${supplier.name}" />
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
                                        <select name="groupCurrencyCode" id="groupCurrencyCode" style="width: 30%">
                                            <c:if test="${model.currs!=null}">
                                                <c:forEach items="${model.currs}" var="curr">
                                                    <option value=<c:out value="${curr.currencyCode}"/>
                                                            <c:if test="${curr.currencyCode == model.currencyCode}"> selected </c:if>>
                                                        <c:out value="${curr.currencyCode}" />
                                                    </option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">
                                        <b> Fish Unit Cost </b>
                                    </td>
                                </tr>
                                <c:forEach items="${model.fishes}" var="fish" varStatus="i">
                                    <tr>
                                        <td width="20%">
                                            ${fish.code}
                                        </td>
                                        <td>
                                            <input type="text" id="unitCost-${i.index}" name="unitCost-${i.index}" onkeypress="validate(event)" value="0"/>
                                        </td>
                                    </tr>
                                </c:forEach>
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
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'FishUnitCostList.htm?action=create';
                });

                $('#btnCancel').click(function() {
                    window.history.back();
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('#contractBeginDate').datepicker({
                    dateFormat: "dd/mm/yy",
                    onSelect: function(dateText) {
                        $("#contractEndDate").datepicker("option", "minDate", dateText);
                    }
                });

                $('#contractEndDate').datepicker({
                    dateFormat: "dd/mm/yy"
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'u:d');


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