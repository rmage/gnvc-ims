<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Fish Vessel &therefore; IMS</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript" language="javascript">
            $(document).ready(function() {
                $('#btnSearch').click(function() {
                    var fishCode = $('#queryBatchNo').val();
                    location.href = "FishVessel.htm?search=true&batchNo=" + fishCode;
                });

                $('#btnCleanFilter').click(function() {
                    location.href = "FishVessel.htm";
                });

                $('#btnAdd').click(function() {
                    $("#dialog").dialog({
                        width: 450,
                        height: 350,
                        position: "center",
                        modal: true,
                        zindex: 9999,
                        title: 'Select Supplier'});
                });

                $('#ajaxSearchBtn').click(function() {
                    if ($('#list')[0].grid) {
                        $("#list").jqGrid().setGridParam({
                            url : 'FishJson.htm?action=findFishSupplier&query=' + $('#query').val()
                        }).trigger("reloadGrid");
                    } else {
                        $("#list").jqGrid({
                            url: 'FishJson.htm?action=findFishSupplier&query=' + $('#query').val(),
                            datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                            colNames: ['id', 'Code', 'Supplier Name'],
                            colModel: [
                                {name: 'id', index: 'id', width: 100},
                                {name: 'code', index: 'code', width: 100},
                                {name: 'name', index: 'name', width: 350}],
                            sortname: 'code',
                            rowNum: 10, rowList: [10, 20, 30],
                            jsonReader: {repeatitems: false},
                            onSelectRow: function(ids) {
                                var localRowData = $(this).getRowData(ids);
                                location.href = 'FishVessel.htm?action=create&supplierId=' + localRowData.id;
                            },
                            pager: '#pager', viewrecords: true, sortorder: "desc"}
                        );

                        $("#list").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false});
                    }
                });
            });
        </script>
    </head>
    <body>
        <%            com.app.web.engine.search.ProductSearch criteria = new com.app.web.engine.search.ProductSearch();
            if (request.getSession().getAttribute("FishVesselSearch") != null) {
                criteria = (com.app.web.engine.search.ProductSearch) request.getSession().getAttribute("FishVesselSearch");
            }

            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String querySearch = m.get("querySearch") == null ? "" : (String) m.get("querySearch");
            String queryBatchNo = m.get("queryBatchNo") == null ? "" : (String) m.get("queryBatchNo");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="FishVessel.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Fish Vessel</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Batch No.
                                    </td>
                                    <td>
                                        <input type="text" id="queryBatchNo" name="batchNo" value="<%=queryBatchNo%>"/>
                                    </td>
                                </tr>
                                <tr>
                                </tr>
                            </tbody>
                            <tfoot>
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
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Fish Vessel - Search Result</caption>
                        <thead>
                            <tr>
                                <td class="left">No</td>
                                <td class="left">Action</td>
                                <td class="left">Vessel Code</td>
                                <td class="left">Vessel Name</td>
                                <td class="left">Batch No.</td>
                                <td class="left">Supplier Name</td>
                                <td>Created Date</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.fishVessels!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.fishVessels}" var="vessel">
                                    <tr class="ganjil">
                                        <td class="left" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>

                                        <c:url value="FishVessel.htm" var="urlEdit">
                                            <c:param name="id" value="${vessel.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="edit"/>
                                        </c:url>

                                        <c:url value="FishVessel.htm" var="urlDelete">
                                            <c:param name="id" value="${vessel.id}"/>
                                            <c:param name="page" value="${model.page}" />
                                            <c:param name="action" value="inactivate"/>
                                        </c:url>
                                        <td class="left" width="5%">
                                            <a href='<c:out value="${urlEdit}"/>'>
                                                <img src="resources/images/edit.gif" width="16" height="16" /></a> 
                                            <a class="urlDelete" href='<c:out value="${urlDelete}"/>'>
                                                <img src="resources/images/delete.gif" width="16" height="16" /></a>
                                        </td>
                                        <td class="left"><c:out value="${vessel.code}"/></td>
                                        <td class="left"><c:out value="${vessel.name}"/></td>
                                        <td class="left"><c:out value="${vessel.batchNo}"/></td>
                                        <td class="left"><c:out value="${vessel.supplier.name}"/></td>
                                        <td><fmt:formatDate type="date" value="${vessel.createdDate}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="7">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="FishVessel.htm?page=<c:out value="${model.page-1}" /><%=querySearch%>">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        <c:if test="${model.page < model.totalRows/model.paging}">
                                            <a href="FishVessel.htm?page=<c:out value="${model.page+1}" /><%=querySearch%>">
                                                &gt;
                                            </a>
                                        </c:if>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr><td colspan="7"></td></tr>
                        </tfoot>
                    </table>
                </div>
            </div>
            <div id="dialog" title="Batch Number Search" style="display:none;z-index:1;">
                <label>Supplier Name</label>
                <label>:</label>
                <input type="text" id="query" size="30" class="text-input"/>
                <input type="button" id="ajaxSearchBtn" style="font-size: smaller;" aria-disabled="false"                                                    
                       role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                       name="ajaxSearchBtn" id="btnSave" value="Search" class="search" />
                <table id="list"></table> 
                <div id="pager"></div> 
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function() {
                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();

                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom', '-1px').css('position', 'relative');
            });
        </script>

    </body>

</html>