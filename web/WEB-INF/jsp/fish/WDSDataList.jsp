<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS &therefore; Withdrawal</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="content" class="span-24 last">
                <div class="box">
                    <form action="FishWds.htm" id="search" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Withdrawal &therefore; Search</caption>
                            <tbody>
                                <tr>
                                    <td style="width: 200px;">WDS Number</td>
                                    <td><input type="text" name="fw.wds_no"></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <input type="submit" value="Search" name="btnSearch" />
                                        <input type="button" value="Add" name="btnAdd" onclick="location.replace('FishWds.htm?action=create');" />
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select" id="list">
                        <caption>Withdrawal &therefore; List</caption>
                        <thead>
                            <tr>
                                <td style="width: 15px">No</td>
                                <td style="width: 50px">Action</td>
                                <td>WDS No</td>
                                <td>Batch No</td>
                                <td>Supplier Name</td>
                                <td>Requested By</td>
                                <td>Approved By</td>
                                <td>Creator</td>
                            </tr>
                        </thead>
                        <tbody id="main"></tbody>
                        <tfoot></tfoot>
                    </table>
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        
        <script>
            util.initSearchForm($('#search'));
            util.initListTable($('#list'), 'R_FishWDS_Withdrawal Report (xls)');
        </script>
        
    </body>
</html>
<%--
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS &there4; Withdrawal</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript" language="javascript">
            $(document).ready(function() {

                $('#btnCleanFilter').click(function() {
                    location.href = "FishWds.htm";
                });

                $('#queryWdsDate').datepicker({
                    dateFormat: "dd/mm/yy"
                });

                $('#btnSearch').click(function() {
                    var wdsNo = $('#queryWdsNo').val();
                    var wdsDate = $('#queryWdsDate').val();

                    if (wdsDate != '') {
                        location.href = "FishWds.htm?search=true&wdsNo=" + wdsNo + "&wdsDate=" + wdsDate;
                    }
                    else {
                        location.href = "FishWds.htm?search=true&wdsNo=" + wdsNo;
                    }
                });

                $('#btnAdd').click(function() {
                    location.href = "FishWds.htm?action=create";
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();

                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });

            function showDetails(selectedRow) {
                var title = 'WDS Details: ' + selectedRow.innerHTML;
                var wsId = selectedRow.getAttribute('id');
                $('#dtl-panel').fadeIn(500, function() {
                    $.ajax({
                        url: "FishWds.htm",
                        data: "action=ajaxDocument&wdsId=" + wsId,
                        success: function(html) {
                            $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                            $('#dtl-panel').append(html);
                        },
                        error: function(jqXHR, textStatus) {
                            $('#dtl-panel').html('<b>' + title + '</b><hr style="margin-bottom: 0px" />');
                            $('#dtl-panel').append(jqXHR + " - " + textStatus);
                        }
                    });
                });
            }
        </script>
    </head>
    <body>
        <%            com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch();
            if (request.getSession().getAttribute("FishRRDataSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishRRDataSearch");
            }

            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String querySearch = m.get("querySearch") == null ? "" : (String) m.get("querySearch");
            String queryWdsNo = m.get("queryWdsNo") == null ? "" : (String) m.get("queryWdsNo");
            String queryWdsDate = m.get("queryWdsDate") == null ? "" : (String) m.get("queryWdsDate");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />
            <div id="dtl-panel" class="div-dtl" style="width: 99%; display: block;" ondblclick="csbShowDetail(0, 0)"></div>
            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishWds.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Withdrawal &there4; Search</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        WDS No
                                    </td>
                                    <td>
                                        <input type="text" id="queryWdsNo" name="wdsNo" value="<%=queryWdsNo%>"/>
                                    </td>
                                    <td>
                                        Date
                                    </td>
                                    <td>
                                        <input type="text" id="queryWdsDate" name="wdsDate" value="<%=queryWdsDate%>" />
                                    </td>
                                    <td colspan="2">
                                    </td>
                                </tr>
                                <tr></tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4">
                                        <span class="style1">
                                            <input class ="style1" type="button" value="Search" id="btnSearch" name="btnSearch" />
                                        </span>
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" />
                                        </label>
                                        <label>
                                            <input type="button" name="button" id="btnCleanFilter" value="Clean Filter" />
                                        </label>
                                    </td>
                                    <td></td>
                                </tr>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Withdrawal &there4; List</caption>
                        <thead>
                            <tr>
                                <td class="left">No</td>
                                <td class="left">Action</td>
                                
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.wdsDataList!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.wdsDataList}" var="wdsData">
                                    <tr class="ganjil">
                                        <td class="left" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>

                                        <td class="left" width="10%">
                                            <a href='GenerateReport.htm?action=index&item=FishWDS&type=xls&params=${wdsData.id}'>
                                                <img src="resources/images/printxls.jpg" width="16" height="16" alt="xls" /></a>&nbsp;&nbsp;
                                        </td>
                                        <td class="left"><a id="${wdsData.id}" onclick="showDetails(this)"><c:out value="${wdsData.wdsNo}"/></a></td>
                                        <td class="left"><c:out value="${wdsData.vessel.batchNo}"/></td>
                                        <td class="left"><c:out value="${wdsData.vessel.supplier.name}"/></td>
                                        <td class="left"><c:out value="${wdsData.requestedBy}"/></td>
                                        <td class="left"><c:out value="${wdsData.approvedBy}"/></td>
                                        <td class="left"><c:out value="${wdsData.createdDate}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="10">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishWds.htm?page=<c:out value="${model.page-1}" /><%=querySearch%>">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        <c:if test="${model.page < model.totalRows/model.paging}">
                                            <a href="FishWds.htm?page=<c:out value="${model.page+1}" /><%=querySearch%>">
                                                &gt;
                                            </a>
                                        </c:if>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>

                            <td colspan="10">
                                <span class="style1">

                                </span>
                            </td>
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
    </body>

</html>--%>
