<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <head>
            <title>IMS - Picking List Admin</title>
            <%@include file="../metaheader.jsp" %>
        </head>
        
    </head>
    <body>
        <%
        	String DateFormat = com.app.wms.web.util.AppConstant.DATE_FORMAT;
            pageContext.setAttribute("DateFormat", DateFormat);

            com.app.web.engine.search.PickingSearch criteria = new com.app.web.engine.search.PickingSearch(); 
            if (request.getSession().getAttribute("PickingSearch") != null) {
                criteria = (com.app.web.engine.search.PickingSearch) request.getSession().getAttribute("PickingSearch");
            }

            java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="PickingAdm.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search - Picking Admin</caption>
                            <tbody>
                                <tr>
                                	<td width="2%"></td>
                                    <td width="10%">
                                       Picking No
                                    </td>
                                    <td>
                                        <input size="30" type="text" name="pickingNo" id="pickingNo" value=""/>
                                    </td>
                         
                                    <td width="10%">
                                        Picking Date
                                    </td>
                                    <td>
                                        <input class="datetime" size="30" type="text" id="pickingDate" name="pickingDate" value="<%=com.app.wms.web.util.Utility.formatDate(criteria.getPickingDate())%>" />
                                    </td>
                                    <tr>

                                </tr>
                                </tr>
                               
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <input class ="style1" type="submit" id="btnSearch" name="action" value="Search" />
                                    <form action="PickingAdm.htm" method="post" name="frmAdd" id="frmAdd" >
                                    <input type="hidden" name="action" value="create" />
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnAdd" id="btnAdd" value="Add" />
                                        </label>
                                    </span>
                                	</form>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
                    </form>

                    <table class="collapse tblForm row-select">
                        <caption>Picking - List Admin</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">Picking No</td>
                                <td class="style1">Picking Date</td>
                                <td class="style1">Picking Status</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.picking!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.picking}" var="pick">
                                    <tr>
                                        <td width="1%" class="center" >
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td width="5%" class="style1">
                                            <c:url  value="PickingAdm.htm"  var="urlPrint">
                                                <c:param name="pickingNo" value="${pick.pickingNo}"/>
                                                <c:param name="templateName" value="rptPickingList"/>
                                                <c:param name="parametersKey" value="P_DOC_NO"/>
                                                <c:param name="action" value="doPrint" />
                                            </c:url>
                                            <a href='<c:out value="${urlPrint}"/>'>
                                                <img src="resources/images/print.jpg" width="16" height="16" alt="Print" />
                                            </a>
                                            
                                        </td>
                                            <td class="style1"><a href="#" class="no-decoration" onclick="csbShowDetail('PICK', '<c:out value="${pick.pickingNo}"></c:out>', 'Picking List Detail')"><c:out value="${pick.pickingNo}" /></a></td>
                                        <td class="style1"><c:out value="${pick.pickingDate}"/></td> 
                                        <td class="style1"><c:out value="${pick.statusApp}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="7">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="PickingAdm.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
                                        <c:if test="${model.page < model.totalRows/model.paging}">
                                            <a href="PickingAdm.htm?page=<c:out value="${model.page+1}" />">
                                                &gt;
                                            </a>
                                        </c:if>
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <td colspan="10">
                                
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
</html>