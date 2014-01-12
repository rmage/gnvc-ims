<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
            <title>IMS - Sales Order List</title>
            <%@include file="../metaheader.jsp" %>
            	
    </head>

    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
        	com.app.web.engine.search.SalesOrderSearch criteria = new com.app.web.engine.search.SalesOrderSearch(); 
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="SalesOrder.htm" method="post" id="soForm">
                        <table class="collapse tblForm row-select">
                            <caption>Search - Sales Order</caption>
                            <tbody>
                                <tr>
                                	<%--
                                    <td width="20%">
                                       Sales Order No
                                    </td>
                                     <td><input type="text" name="salesOrderNo" id="salesOrderNo" /></td>
                                    --%>
                                </tr>
                               <tr>
                               		<td></td>
                                    <td>
                                        Sales Order Date
                                    </td>
                                    <td>
                                        From :&nbsp;<input class="datetime" size="30" type="text" id="salesOrderStartDate" name="salesOrderStartDate" value="<%=com.app.wms.web.util.Utility.formatDate(criteria.getSo_date())%>" />&nbsp;&nbsp;To :&nbsp;
                                        <input class="datetime" size="30" type="text" id="salesOrderEndDate" name="salesOrderEndDate" value="<%=com.app.wms.web.util.Utility.formatDate(criteria.getSo_date())%>" />
                                    </td>
                               </tr>
                               
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    	<input class ="style1" type="submit" id="btnSearch" name="action" value="Search" />
	                                    <form action="SalesOrder.htm" method="post" name="frmAdd" id="frmAdd" >
		                                    <input type="hidden" name="action" value="create" />
		                                    <span class="style1">
		                                        <label>
		                                            <input type="submit" id="btnAdd" name="btnAdd" value="Add" />
		                                        </label>
		                                    </span>
	                                	</form>
                                </td>
                           </tfoot>
                        </table>
                    </form>
                    
                   
                    
                    <table class="collapse tblForm row-select">
                       <caption>Sales Order - List</caption>
                        <thead>
                            <tr>
                                <td class="style1" width="1%">No</td>
                                <td class="style1" width="3%">Action</td>
                                <td class="style1">Sales Order No</td>
                                <td class="style1">Sales Order Date</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.salesorder!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.salesorder}" var="sales">
                                    <tr>
                                        <td width="1%" class="center" >
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td width="5%" class="style1">
                                        	<c:url  value="SalesOrder.htm"  var="urlPrint">
                                                <c:param name="sonumber" value="${sales.so_number}"/>
                                                <c:param name="templateName" value="rptSalesOrderList"/>
                                                <c:param name="parametersKey" value="P_DOC_NO"/>
                                                <c:param name="action" value="doPrint" />
                                            </c:url>
                                        	<a href='<c:out value="${urlPrint}"/>'>
                                                <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                            </a>
                                        </td>
                                                <td class="style1"><a href="#" class="no-decoration" onclick="csbShowDetail('SO', '<c:out value="${sales.so_number}"></c:out>', 'Sales Order Detail')"><c:out value="${sales.so_number}" /></a></td>
                                        <td class="style1"><c:out value="${sales.so_date}"/></td>      
                                    </tr>
                                </c:forEach>
                                
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="SalesOrder.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="SalesOrder.htm?page=<c:out value="${model.page+1}" />">
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