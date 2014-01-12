<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Stock Take List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	//java.util.HashMap map = (java.util.HashMap) request.getAttribute("model");
        	com.app.web.engine.search.WarehouseSearch criteria = new com.app.web.engine.search.WarehouseSearch(); 
        
        		    if (request.getSession().getAttribute("WarehouseSearch") != null) {
        	criteria = (com.app.web.engine.search.WarehouseSearch) request.getSession().getAttribute("WarehouseSearch");
        		    }
        
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search Warehouse</div -->
                <div class="box">
                    <form action="stocktake.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Stock Take</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Stock Take
                                    </td>
                                    <td>
                                        <input type="text" name="whCode" value="<%= criteria.getWhCode()%>"/>
                                    </td>
                                    <td width="20%">
                                        Stock Take Date
                                    </td>
                                    <td>
                                        <input type="text" name="name" value="<%= criteria.getName()%>" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="4">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                       <%--  <input class ="style1" type="submit" value="View All" id="btnViewAll" name="btnViewAll" />--%>
                                    	<input type="button" name="button" id="btnAdd" value="Add" />
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Product - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>                                                                
                                <td class="style1">Action</td>
                                <td class="style1">Tanggal StockTake</td>
                                <td class="style1">Product Code</td>
                                <td class="style1">Location</td>
                                <td class="style1">Actual Qty</td>
                                <td class="style1">Corp Id</td>
                                <td class="style1">Wh Id</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.stockTakes!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.stockTakes}" var="stockTake">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        
                                        <td class="style1">
                                             <c:url  value="stocktake.htm"  var="urlPrint">
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${stockTake.stockdate}" var="newdatevar" />
                                                    <c:param name="sonumber" value="${newdatevar}"/>
                                                    <c:param name="templateName" value="rptStockTakeList"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrint" />
                                            </c:url>
                                            <a href='<c:out value="${urlPrint}"/>'>
                                                    <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                            </a>
                                        </td>
                                        <td class="style1">
                                            <fmt:formatDate pattern="dd-MMMM-yyyy hh:mm" value="${stockTake.stockdate}" />
                                        </td>
                                        <td class="style1"><c:out value="${stockTake.productcode}"/></td>
                                        <td class="style1"><c:out value="${stockTake.locationcode}"/></td>
                                        <td class="style1"><c:out value="${stockTake.actualqty}"/></td>
                                        <td class="style1"><c:out value="${stockTake.corpId}"/></td>
                                        <td class="style1"><c:out value="${stockTake.whCode}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="8">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="stocktake.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="stocktake.htm?page=<c:out value="${model.page+1}" />">
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
                                    <label>
                                        
                                    </label>

                                     <label>
                                        
                                         <c:url  value="report.htm"  var="urlPrintAll">
                                            <c:param name="action" value="print"/>
                                            <c:param name="templateName" value="rptAllStockCheck"/>                                           
                                        </c:url>
                                        
                                        <a href='<c:out value="${urlPrintAll}"/>' style="font-size: smaller; padding: 3px 20px;" 
                                           aria-disabled="false" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-hover" >
                                                        Print List Stock
                                        </a>
                                                                                
                                    </label>
                                    
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

        <script type="text/javascript">
            $(function() {
                $('#btnAdd').click(function() {
                    location.href = 'StockTakeAdd.htm';
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