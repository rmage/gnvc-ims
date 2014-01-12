<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Replenish List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
            java.util.HashMap model = (java.util.HashMap) request.getAttribute("model");
            java.lang.String error = (String)model.get("msg");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search Replenish</div -->
                <div class="box">
                    <form action="Replenish.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search - Replenish</caption>
                            <tbody>
                                <tr>
                                    <td width="2%"></td>
                                    <td width="10%">
                                        Replenish No
                                    </td>
                                    <td>
                                        <input size="30" type="text" name="replenishNo" value=""/>
                                    </td>
                                    <td width="10%">
                                        Replenish Date
                                    </td>
                                     <td width="50%" >
                                        <input size="30" type="text" name="replenishDate" id="replenishDate" value="" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                    <span class="style1">
                                        Scope : 
                                        <select id="type">
                                            <option>Warehouse</option>
                                            <option>Corporate</option>
                                        </select>
                                        <label>
                                            <input type="button" name="button" id="btnAdd" value="Add" />
                                        </label>
                                    </span>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Replenish - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Replenish Code</td>
                                <td class="style1">Replenish Date</td>
                                <td class="style1">Replenish Type</td>
                                <td class="style1">Status</td>
                                <td class="style1">Created By</td>
                                <td class="style1">Created Date</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.replenish!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)}" var="nomor" />
                                <c:forEach items="${model.replenish}" var="r" varStatus="i" >
                                <c:set var="nomor" value="${nomor + 1}" />
                                <tr>
                                    <td class="style1">${nomor}</td>
                                    <td class="style1">
                                        <c:choose>
                                        <c:when test="${fn:contains(r.statusApp, '-')}">
                                            <a href="Replenish.htm?action=view&replenishNo=${r.replenishNo}" title="View Replenish">
                                                <img src="resources/images/detail.png" width="16" />
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="Replenish.htm?action=doPrint&replenishNo=${r.replenishNo}&templateName=rptReplenishList&parametersKey=P_DOC_NO" title="View Replenish">
                                                <img src="resources/images/print.jpg" width="16" />
                                            </a>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:if test="${r.statusApp == '-1' || r.statusApp == '-2'}">
                                            <a href="Replenish.htm?action=cancel&replenishNo=${r.replenishNo}" title="Cancel Replenish">
                                                <img src="resources/images/icon-pencil-x.gif" width="16" />
                                            </a>
                                        </c:if>
                                    </td>
                                    <td class="style1">${r.replenishNo}</td>
                                    <td class="style1"><fmt:formatDate pattern="dd-MM-yyyy" value="${r.replenishDate}" /></td>
                                    <td class="style1">
                                        <c:if test="${fn:contains(r.statusApp, '1')}">Warehouse</c:if>
                                        <c:if test="${fn:contains(r.statusApp, '2')}">Corporate</c:if>
                                    </td>
                                    <td class="style1">
                                        <c:if test="${r.statusApp == '-1' || r.statusApp == '-2'}">WAITING APPROVAL</c:if>
                                        <c:if test="${fn:contains(r.statusApp, '0')}">CANCELED</c:if>
                                        <c:if test="${r.statusApp == '1' || r.statusApp == '2'}">SUCCESSFUL</c:if>
                                    </td>
                                    <td class="style1">${r.createdBy}</td>
                                    <td class="style1"><fmt:formatDate pattern="dd-MM-yyyy" value="${r.createdDate}" /></td>
                                </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="8">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Replenish.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        <c:if test="${model.page < model.totalRows/model.paging}">
                                            <a href="Replenish.htm?page=<c:out value="${model.page+1}" />">
                                                &gt;
                                            </a>
                                        </c:if>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <% if (error != null) { %>
        <div id="dialog" title="Info" style="display:none">
            <ul>
            <%
            String[] listErrorMsg = error.split(com.app.wms.web.util.AppConstant.EOL);
            for (int i = 0; i < listErrorMsg.length; i++) {
                String msg = listErrorMsg[i];
            %>
                <li><%=msg%></li>
            <%
            }
            %>
            </ul>
            <script type="text/javascript">
            $(document).ready(function() {
                $("#dialog").dialog();
            });
            </script>
        </div>
        <% } %>
        <script type="text/javascript">
            $(function() {
                $('#replenishDate').datepicker({                        
                    dateFormat: "dd/mm/yy"
                });
                
                $('#btnAdd').click(function() {
                    location.href = 'Replenish.htm?action=create&type=' + $('#type').val();
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
              
            });
        </script>
      
    </body>
</html>