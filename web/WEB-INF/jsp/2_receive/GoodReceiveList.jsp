<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Receiving Report (RR) List</title>
        <%@include file="../metaheader.jsp" %>
    </head>

    <body>
        <%
        	//java.util.HashMap map = (java.util.HashMap) request.getAttribute("model");
        	com.app.web.engine.search.WarehouseSearch criteria = new com.app.web.engine.search.WarehouseSearch(); 
        
        		    if (request.getSession().getAttribute("WarehouseSearch") != null) {
        	criteria = (com.app.web.engine.search.WarehouseSearch) request.getSession().getAttribute("WarehouseSearch");
        		    }
                
                com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
        
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <%@include file="../dynmenu.jsp" %>

            <div id="content" style="display: none" class="span-24 last">
                <!--div class="box">Search Warehouse</div -->
                <div class="box">
                    <form action="GoodReceive.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Receiving Report</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        RR Code
                                    </td>
                                    <td>
                                        <input type="text" name="grcode" value="${model.grcode}"/>
                                    </td>
                                    <td width="20%">
                                        RR Date
                                    </td>
                                    <td>
                                        <input type="text" id="grdate" name="grdate" value="${model.grdate}" />
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="6">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                    </span>
                                     <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                    </label>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>RR - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <td class="style1">Action</td>
                                <td class="style1">RR Code</td>
                                <td class="style1">RR Date</td>                                
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.grs!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.grs}" var="gr">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url  value="GoodReceive.htm"  var="urlPrint">
                                                    <c:param name="sonumber" value="${gr.grnumber}"/>
                                                    <c:param name="templateName" value="rptGoodReceiveList"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrint" />
                                                </c:url>
                                                <a href='<c:out value="${urlPrint}"/>'>
                                                        <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                                </a>
                                        </td>
                                        <td class="style1">
                                            <a href="#" class="no-decoration" onclick="csbShowDetail('GR', '<c:out value="${gr.grnumber}"></c:out>', 'RR Detail')">
                                                ${gr.grnumber}
                                             </a>
                                        </td>
                                        <td class="style1">
                                            <fmt:formatDate pattern="dd-MM-yyyy hh:mm" value="${gr.receiveddate}" />
                                        </td>      
                                        <%--<td class="style1"><c:out value="${ware.updatedDate}"/></td>
                                        <td class="center">${gr.corpid}</td>
                                        --%>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="5">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="GoodReceive.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="GoodReceive.htm?page=<c:out value="${model.page+1}" />">
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
                                <%-- 
                                    <label>
                                        
                                         <c:url  value="Purchase.htm"  var="urlPrintAll">
                                            <c:param name="sonumber" value="<%= l.getWhCode() %>"/>
                                            <c:param name="templateName" value="rptPurchaseOrderList2"/>
                                            <c:param name="parametersKey" value="P_DOC_NO"/>
                                            <c:param name="action" value="doPrint" />
                                        </c:url>
                                        
                                        <a href='<c:out value="${urlPrintAll}"/>' style="font-size: smaller; padding: 3px 20px;" 
                                           aria-disabled="false" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-hover" >
                                                        Print All
                                        </a>
                                                                                
                                    </label>
                                  --%>  
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
                    location.href = 'GoodReceive.htm?action=create';
                });

                $('#btnEdit').click(function() {
                    location.href = '';
                });
                
                $('#grdate').datepicker({                        
                        dateFormat: "dd/mm/yy"                        
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