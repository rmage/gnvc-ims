<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Pallet Transfer Slip</title>
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
                    <form action="Pts.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">PTS Number</td>
                                    <td><input type="text" name="tsCode" /></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="window.location.replace('Pts.htm?action=create');" />
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
                                <td rowspan="2">PTS Number</td>
                                <td rowspan="2">PTS Date</td>
                                <td rowspan="2">For Brand</td>
                                <td rowspan="2">Pack Style/Size</td>
                                <td rowspan="2">Can Code</td>
                                <td rowspan="2">Reff</td>
                                <td colspan="4">Cut Out Evaluation</td>
                                <td colspan="2">QA Disposition</td>
                            </tr>
                            <tr>
                                <td>% Flk</td>
                                <td>NW</td>
                                <td>DW</td>
                                <td>PW</td>
                                <td>Release to</td>
                                <td>Remarks</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${model.p != null}">
                                <c:set scope="page" value="${((model.page-1) * model.paging) + 1}" var="no" />
                                <c:forEach items="${model.p}" var="x" varStatus="i">
                                    <c:set var="xx" value="${fn:split(x.productCode, ':')}" />
                                    <tr data-cs="${x.ptsCs}" data-location="${x.ptsLocation}">
                                        <td>
                                            ${no}
                                            <c:set scope="page" value="${no + 1}" var="no"/>
                                        </td>
                                        <td>
                                             <a href="GenerateReport.htm?action=index&item=FGPTS&type=xls&params=${x.ptsCode}"><img src="resources/images/printxls.gif" title="Print Pallet Transfer Slip (xls)" /></a>
                                        </td>
                                        <td><a class="d" href="#${x.ptsCode}">${x.ptsCode}</a></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${x.ptsDate}" /></td>
                                        <td>${xx[0]}</td>
                                        <td>${xx[1]}</td>
                                        <td>${x.ptsCanCode}</td>
                                        <td>${x.borCode}</td>
                                        <td>${x.coeFlk}</td>
                                        <td>${x.coeNw}</td>
                                        <td>${x.coeDw}</td>
                                        <td>${x.coePw}</td>
                                        <td>${x.qadReleaseTo}</td>
                                        <td>${x.qadRemarks}</td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="14">
                                    <c:if test="${model.page !=null && model.page > 1}">
                                        <a href="Pts.htm?page=<c:out value="${model.page-1}" />">&lt</a>
                                    </c:if>
                                    &nbsp;page: ${model.page}&nbsp;
                                    <c:if test="${model.page < model.totalRows/model.paging}">
                                        <a href="Pts.htm?page=<c:out value="${model.page+1}" />">&gt;</a>
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
            
            var cs = 0;
            var loc = '';
            
            $('.d').live('click', function(){
                var $p = $(this).parent().parent();
                cs = $p.data('cs');
                loc = $p.data('location');
                fyaQPanel($(this).html());
            });
            
            function fyaQPanel(p) {
                if(p === 0) {
                    $('#fyaQPanel').fadeOut(300, function(){ $('#fyaQPanel').html(null); });
                } else {
                    $('#fyaQPanel').fadeIn(300, function(){
                        $('#fyaQPanel').html('<center><img src="resources/img/load-spin.gif" /></center>');
                        $.ajax({
                            url: 'Pts.htm',
                            data: {action: 'ajaxDocument', key: p},
                            dataType: 'json',
                            success: function(json) {
                                $('#fyaQPanel').html(null);
                                $('#fyaQPanel').append('<h6>PTS Number : ' + p + ' | Cs : ' + cs + ' | Location : ' + loc + '</h6><hr />');
                                $('#fyaQPanel').append('<table><thead><tr><th>NS/DS</th><th>Date</th>' +
                                    '<th>Prod. Batch</th><th>Basket</th><th>Quantity</th></tr></thead><tbody id="fyaQPanelBody"></tbody></table>');
                                for(var i = 0; i < json.length; i++) {
                                    $('#fyaQPanelBody').append('<tr><td>' + json[i].shift + '</td><td>' + json[i].date +
                                        '</td><td>' + json[i].prodbatch + '</td><td>' + json[i].basket + 
                                        '</td><td>' + numberWithCommas(json[i].qty) + '</td></tr>');
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
