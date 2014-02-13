<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Stores Withdrawal Slip</title>
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
                    <form action="ReceiveReport.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">SWS Number</td>
                                    <td><input type="text" name="prsnumber" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Sws.htm?action=create');" />
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
                                <td>SWS Number</td>
                                <td>SWS Date</td>
                                <td>Department</td>
                                <td>Info</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.s != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.s}" var="x" varStatus="i">
                                    <tr>
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                             <a href="GenerateReport.htm?action=index&item=IMSWS&type=xls&params=${x.swsCode}"><img src="resources/images/printxls.gif" title="Print Stores Withdrawal Slip (xls)" /></a>
                                        </td>
                                        <td><a class="d" href="#${x.swsCode}">${x.swsCode}</a></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.swsDate}" /></td>
                                        <td>${x.departmentCode}</td>
                                        <td>${x.swsInfo}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="6">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="Sws.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="Sws.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
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
            /* jQuery | Binding event */
            $('.d').live('click', function(){
                fyaQPanel($(this).html());
            });

            function fyaQPanel(p) {
                if(p === 0) {
                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
                } else {
                    $('#fyaQPanel').fadeIn(300, function(){
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'Sws.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>SWS Number : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>Item Code</th><th>Item Name</th><th>Type</th><th>Stock on Hand</th>'
                                    + '<th>Quantity</th><th>Uom</th></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].itemCode + '</td><td>' + json[i].itemName +
                                        '</td><td>' + json[i].type + '</td><td>' + numberWithCommas(json[i].soh) + 
                                        '</td><td>' + numberWithCommas(json[i].qty) + '</td><td>' + json[i].uom + '</td></tr>');
                                }
                            },
                            complete: function() {
                                if($('#fyaQPanel').html() === '')
                                    fyaQPanel(0);
                            }
                        });
                    }); 
                }
            }
            
            function numberWithCommas(x) {
                var parts = x.toString().split(".");
                parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                return parts.join(".");
            }
        </script>
    </body>
</html>
