<%-- 
    Document   : FishRRAccountingAdd
    Created on : Sep 17, 2014, 1:58:55 PM
    Author     : Faridzi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish RR Update Price</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body onload="onload();">
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishRRAccounting.htm" method="post" id="addForm">
                        <!--<input type="hidden" name="action" value="save" />-->
                        <table class="collapse tblForm row-select">
                            <caption>Fish RR Detail</caption>
                            <tbody>
                                <tr>
                                    <td>
                                        RR Number : <b> ${model.rrNo} </b>   
                                    </td>
                                    <td>
                                        RR Date :  <b>  ${model.rrDate}  </b>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Batch Number : <b>${model.batchNo}</b>   
                                    </td>
                                    <td>
                                        Supplier Name : <b>${model.supplierName}</b>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                            </tfoot>
                        </table>
                    </form>
                    <form action="FishRRAccounting.htm" method="post" id="addForm">
                        <input type="hidden" name="action" value="save" />
                        <table class="collapse tblForm row-select">
                            <caption>Update Fish RR Price</caption>
                            <thead>
                            <th>
                                Fish Type
                            </th>
                            <th>
                                Quantity
                            </th>
                            <th>
                                Unit Cost
                            </th>
                            <th>
                                Currency Code
                            </th>
                            <th>
                                Adjusted Cost 
                            </th>
                            <th>
                                Amount
                            </th>
                            </thead>
                            <tbody>
                                </tr>
                                <c:forEach items="${model.frrDetailList}" var="frrDetail" varStatus="i">
                                    <tr>
                                        <td style="width: 10%">
                                            ${frrDetail.fish.code}
                                        </td>
                                        <td style="width: 10%">
                                            <b> <fmt:formatNumber pattern="#,##0" value="${frrDetail.totalWeight}" /> </b> 
                                        </td>
                                        <td style="width: 10%">
                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${frrDetail.contractPrice}" /> 
                                        </td>
                                        <td style="width: 10%">
                                            ${frrDetail.currencyCode}
                                        </td>
                                        <td style="width: 10%">
                                            <input type="text" id="amount-${i.index}" name="amount-${i.index}" onkeypress="validate(event)" value="${frrDetail.amount}" onchange="calAmount(this.value, ${frrDetail.totalWeight}, ${i.index})"/>
                                        </td>
                                        <td style="width: 10%">
                                            <b> <label id="lblAmount-${i.index}" > <fmt:formatNumber type="number" maxFractionDigits="2" value="${frrDetail.totalWeight * frrDetail.amount}" /> </label> </b>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                            <td colspan="6">
                                <span>
                                    <input class="simpan" type="submit" value="Save" id="btnSave" name="btnSave" onclick="return confirm('Are you sure you want to continue ?')" />
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
        </div>

        <script type="text/javascript">
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

            function calAmount(val, qty, idx) {
                var amount = val * qty;
                document.getElementById('lblAmount-' + idx + '').innerHTML = amount.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');;
            }

            $(function() {

                $('#btnCancel').click(function() {
                    window.history.back();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
        </script>
    </body>
</html>
