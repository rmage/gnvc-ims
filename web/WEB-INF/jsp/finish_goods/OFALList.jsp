<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Order Fill / Authority to Label (OFAL)</title>
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
                    <form action="Ofal.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">Bor Number</td>
                                    <td><input type="text" name="tsCode" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Ofal.htm?action=create');" />
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
                                <td>Created Date</td>
                                <td>Bor Code</td>
                                <td>Label Net Weight</td>
                                <td>Label Drained Weight</td>
                                <td>Shipment Date</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.o != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.o}" var="x" varStatus="i">
                                    <c:set var="xx" value="${fn:split(x.borCode, '*')}" />
                                    <tr>
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                             <a href="GenerateReport.htm?action=index&item=FGOFAL&type=xls&params=${x.ofalId}"><img src="resources/images/printxls.gif" title="Print OFAL (xls)" /></a>
                                        </td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.createdDate}" /></td>
                                        <td><a class="d" href="#${x.ofalId}" data-id="${x.ofalId}">${xx[0]}</a></td>
                                        <td>${x.ofalLabelNw} g</td>
                                        <td>${x.ofalLabelDw} g</td>
                                        <td>${x.ofalShipment}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="14">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="Ofal.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="Ofal.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
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
                fyaQPanel($(this).data('id'));
            });

            function fyaQPanel(p) {
                if(p === 0) {
                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
                } else {
                    $('#fyaQPanel').fadeIn(300, function(){
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'Ofal.htm',
                            data: {action: 'ajaxDocument', term: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>Ofal ID : ' + p + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>PTS Number</th><th>PTS Date</th><th>Can Code</th><th>Quantity</th><th>' +
                                    'Location</th><th>Remarks</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].ptsCode + '</td><td>' + json[i].ptsDate 
                                        + '</td><td>' + json[i].canCode + '</td><td>' + json[i].qty 
                                        + '</td><td>' + json[i].location + '</td><td>' + json[i].remarks + '</td></tr>');
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
