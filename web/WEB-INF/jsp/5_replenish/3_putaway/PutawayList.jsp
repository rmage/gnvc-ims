<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Putaway List</title>
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
                    <form action="Putaway.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Putaway</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Putaway Code
                                    </td>
                                    <td>
                                        <input type="text" name="putawaycode" value="${model.putawaycode}" />
                                    </td>
                                    <td width="20%">
                                        GR Code
                                    </td>
                                    <td>
                                        <input type="text" name="grcode" value="${model.grcode}" />
                                    </td>
                                    <td width="20%">
                                        Condition
                                    </td>
                                    <td>
                                         <select name="condition" style="width: 200px">
                                            <option value="-">-</option>
                                            <option value="SUCCESSFULL">SUCCESSFULL</option>
                                            <option value="PENDING">PENDING</option>                                            
                                          </select> 
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="6">
                                    <span class="style1">
                                        <input class ="style1" type="submit" value="Search" id="btnSearch" name="btnSearch" />
                                        <label>
                                        <input type="button" name="button" id="btnAdd" value="Add" />
                                   		</label>
                                    
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Putaway - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
                                <td class="style1">Putaway Code</td>
                                <td class="style1">GR Code</td>                                
                                <td class="style1">Put Away Date</td>
                                <td class="style1">Status</td>
<!--                                <td class="style1">Is Active</td>-->
                            </tr>
                        </thead>
                        <tbody id="main">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.putaways}" var="putaway">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url  value="Putaway.htm"  var="urlPrint">
                                                    <c:param name="sonumber" value="${putaway.putawayId}"/>
                                                    <c:param name="templateName" value="rptPutAwayList"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrint" />
                                            </c:url>
                                            <a href='<c:out value="${urlPrint}"/>'>
                                                    <img src="resources/images/print.jpg" width="16" height="16" />
                                            </a> 
                                             
                                                    
                                                    
                                            <c:if test="${putaway.statusApp == 'PENDING'}">
                                                &nbsp;
                                                &nbsp;
                                                
                                                 <c:url value="Putaway.htm" var="urlEdit">
                                                    <c:param
                                                        name="putawayNo" value="${putaway.putawayId}"/>
                                                    <c:param name="mode" value="edit"/>
                                                </c:url>                                                
                                                
                                                <a href='<c:out value="${urlEdit}"/>'>
                                                        <img src="resources/images/edit.gif" width="16" height="16" />
                                                </a>
                                            </c:if>
                                        </td>
                                        <td class="style1">
                                            <a href="#" class="no-decoration" onclick="csbShowDetail('putaway', '<c:out value="${putaway.putawayId}"></c:out>', 'Putaway Detail')">
                                                ${putaway.putawayId}
                                            </a>
                                        </td>
                                        <td class="style1">${putaway.grNumber}</td>      
                                        <td class="style1"><fmt:formatDate pattern="dd-MM-yyyy" value="${putaway.putawayDate}" /></td>      
                                        <td class="style1">${putaway.statusApp}</td>      
                                        <%--<td class="style1"><c:out value="${ware.updatedDate}"/></td>--%>
                                        <!--<td class="center"><c:out value="${ware.isActive}"/></td>-->
                                    </tr>
                                </c:forEach>
                            <tr>
                                <td colspan="6">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="Putaway.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="Putaway.htm?page=<c:out value="${model.page+1}" />">
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
                                        
                                         <c:url  value="Purchase.htm"  var="urlPrintAll">
                                            <c:param name="sonumber" value=""/>
                                            <c:param name="templateName" value="rptPurchaseOrderList2"/>
                                            <c:param name="parametersKey" value="P_DOC_NO"/>
                                            <c:param name="action" value="doPrint" />
                                        </c:url>
                                        
<!--                                        <a href='<c:out value="${urlPrintAll}"/>' style="font-size: smaller; padding: 3px 20px;" 
                                           aria-disabled="false" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-hover" >
                                                        Print All
                                        </a>-->
                                                                                
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
                    location.href = 'Putaway.htm?action=create';
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