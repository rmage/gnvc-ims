<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Fish Inventory</title>
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
                        <input type="hidden" name="params" value="<%=cDateH %>" />
                        <table class="collapse tblForm row-select">
                            <caption>Stock Card Accounting &therefore; Fish</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 20%;">Fish Code</td>
                                    <td>
                                        ${model.fishcode}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="width: 20%;">As Of</td>
                                    <td>
                                        ${model.asOf}
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                         <table class="collapse tblForm row-select" style="border: 1px solid black">
                            <caption>Stock Card Fish Accounting Detail</caption>
                            <tbody >
                                 <tr>
                                    <th colspan="4" style="text-align: center; border: 1px solid #0078ae">IN</th>
                                    <th colspan="6" style="text-align: center; border: 1px solid #0078ae">OUT</th>
                                    <th rowspan="3" style="text-align: center; border: 1px solid #0078ae">QTY BALANCE</th>
                                    <th colspan="4" style="text-align: center; border: 1px solid #0078ae">UNIT COST</th>
                                    <th rowspan="3" style="text-align: center; border: 1px solid #0078ae">IN (IDR)</th>
                                    <th rowspan="3" style="text-align: center; border: 1px solid #0078ae">OUT (IDR)</th>
                                    <th rowspan="3" style="text-align: center; border: 1px solid #0078ae">BALANCE (IDR)</th>
                                 </tr>
                                 <tr>
                                    <th colspan="3" style="text-align: center; border: 1px solid #0078ae">WS</td>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">QTY</td>
                                    <th colspan="3" style="text-align: center; border: 1px solid #0078ae">WS</td>
                                    <th colspan="2" style="text-align: center; border: 1px solid #0078ae">WLSC</td>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">QTY</td>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Currency</th>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Amount</th>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Rate (IDR)</th>
                                    <th rowspan="2" style="text-align: center; border: 1px solid #0078ae">Amount (IDR)</th>
                                  </tr>
                                  <tr>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS No</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS TYPE</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS Date</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS No</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS Type</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WS Date</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WLSC No</th>
                                    <th style="text-align: center; border: 1px solid #0078ae">WLSC Date</th>
                                  </tr>
                                  <c:forEach items="${model.fishWses}" var="fishWS" varStatus="i">
                                    <tr>
                                        <td>
                                            ${fishWS.wsNo}
                                        </td>
                                        <td>
                                            ${fishWS.wsType.code}
                                        </td>
                                        <td>
                                            ${fishWS.dateShift}
                                        </td>
                                        <td>
                                            qty
                                        </td>
                                        <td>
                                            ws no
                                        </td>
                                        <td>
                                            ws type
                                        </td>
                                        <td>
                                            ws date
                                        </td>
                                        <td>
                                            wlsc no
                                        </td>
                                        <td>
                                            wlsc date
                                        </td>
                                        <td>
                                            qty
                                        </td>
                                        <td>
                                            qty balance
                                        </td>
                                        <td>
                                            <select name="groupCurrencyCode-${i.index}" id="groupCurrencyCode-${i.index}" onchange="currCodeSelected('${i.index}')">
                                                <c:if test="${model.currs!=null}">
                                                    <c:forEach items="${model.currs}" var="curr" varStatus="currCodeidx">
                                                        <option value=<c:out value="${curr.currencyCode}"/> >
                                                            <c:out value="${curr.currencyCode}" /> 
                                                        </option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="number" id="amount-${i.index}" name="amount-${i}" value="0" onchange="calulateAmountIDR('${i.index}')" style="width: 80px">
                                        </td>
                                        <td>
                                            <label id="rateIDR-${i.index}">100</label>
                                        </td>
                                        <td>
                                            <label id="amountIDR-${i.index}">${i.index * 5}</label>
                                        </td>
                                        <td>
                                            IN (IDR)
                                        </td>
                                        <td>
                                            OUT (IDR)
                                        </td>
                                        <td>
                                            BALANCE (IDR)
                                        </td>
                                    </tr>
                                  </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                </tr>
                            </tfoot>
                        </table>
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
                $('#btnGenerate').click(function() {
                    var elem = document.getElementById("dropdownFishCode");
                    elem.value = $('#dropdownFishCode').val();
                    var str = elem.value;
                    str = str.replace(/_/g, ' ');
                    location.href = 'FishStockAccounting.htm?action=create&fishcode=' + str;
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
            
            function currCodeSelected(index){
                var str = $("#groupCurrencyCode-"+index+ " option:selected").index();
                document.getElementById('rateIDR-'+index).innerHTML = str;
            }
            
            function calulateAmountIDR(index){    
                
                var idrRate = $("#rateIDR-"+index).text();
                var idamount = '#amount-'+index;
                var amount = document.getElementById(idamount).value;
                
                alert(idamount);
                alert("IDRRATE " + idrRate*amount + " " + idamount);
                var total = idrRate*amount;
                document.getElementById('amountIDR-'+index).innerHTML = total;
            }
            
        </script>

    </body>
</html>
