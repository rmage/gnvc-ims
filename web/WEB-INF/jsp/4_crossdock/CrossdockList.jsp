<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Cross Dock List</title>
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
                    <form action="CrossDock.htm" method="post">
                        <table class="collapse tblForm row-select">
                            <caption>Search Cross Dock</caption>
                            <tbody>
                                <tr>
                                    <td width="20%">
                                        Customer Code
                                    </td>
                                    <td>
                                        <input type="text" name="whCode" value="<%= criteria.getWhCode()%>"/>
                                    </td>
                                    <td width="20%">
                                        Tanggal Diterima
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
                                    </span>
                                </td>
                            </tfoot>
                        </table>
                    </form>
                    <table class="collapse tblForm row-select">
                        <caption>Cross Dock - List</caption>
                        <thead>
                            <tr>
                                <td class="style1">No</td>
                                <!-- <td><input type="checkbox" class="checkAll" /></td> -->
                                <td class="style1">Action</td>
                                <td class="style1">Consignee Number</td>
                                <td class="style1">Nomer PO</td>                                
                                <!--<td class="style1">Last Update</td>-->
                                <td class="style1">Customer Code</td>
                                <td class="style1">Receive Date</td>
                                <td class="style1">Delivery To</td>
                            </tr>
                        </thead>
                        <tbody id="main">
                            <c:if test="${model.wh!=null}">
                                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
                                <c:forEach items="${model.crossdocks}" var="crossdock">
                                    <tr>
                                        <td class="center" width="1%">
                                            <c:out value="${nomor}" />
                                            <c:set scope="page" value="${nomor+1}" var="nomor"/>
                                        </td>
                                        <td class="center" width="5%">
                                            <c:url  value="CrossDock.htm"  var="urlPrint">
                                                    <c:param name="sonumber" value="${crossdock.ponumber}"/>
                                                    <c:param name="templateName" value="rptPurchaseOrderList"/>
                                                    <c:param name="parametersKey" value="P_DOC_NO"/>
                                                    <c:param name="action" value="doPrint" />
                                                </c:url>
                                                <a href='<c:out value="${urlPrint}"/>'>
                                                        <img src="resources/images/print.jpg" width="16" height="16" alt="View" />
                                                </a>
                                        </td>
                                        <td class="style1">${crossdock.consigneenumber}</td>
                                        <td class="style1">${crossdock.ponumber}</td>      
                                        <%--<td class="style1"><c:out value="${ware.updatedDate}"/></td>--%>
                                        <td class="style1">${crossdock.corpId}</td>
                                        <td class="style1">
                                            <fmt:formatDate pattern="dd-MM-yyyy" value="${crossdock.receivedate}" />                                            
                                        </td>
                                        <td class="style1"></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr>
                                <td colspan="7">
                                    <span class="style1">
                                        <c:if test="${model.page !=null && model.page > 1}">
                                            <a href="CrossDock.htm?page=<c:out value="${model.page-1}" />">
                                                &lt;
                                            </a>
                                        </c:if>
                                        &nbsp;page: <c:out value="${model.page}" />&nbsp;
										<c:if test="${model.page < model.totalRows/model.paging}">
										    <a href="CrossDock.htm?page=<c:out value="${model.page+1}" />">
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
                                        <input type="button" name="button" id="btnAddx1" value="Add New Crossdock Consignee" />
                                    </label>
                                    <label>
                                        <input type="button" name="button" id="btnAdd002" value="Add New Crossdock PO" />
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
                

                $('#btnEdit').click(function() {
                    location.href = '';
                });

                $('.tab').hide();
                $('#btnEdit').click(function() {
                    $('.tab').show();
                });

                $('.tblForm caption').addClass('span-7 ui-corner-tr ui-corner-tl').css('margin-bottom','-1px').css('position', 'relative');
              
            });


            $(document).ready(function(){
                
                
                $('#btnAdd002').click(function(){ 
                    location.href = 'CrossDockPO.htm';                    
                });
                
                $('#btnAddx1').click(function(){            
                    location.href = 'CrossDock.htm?action=create';                    
                });
                 
            });                                      
    </script>
                                                                                
    </body>
</html>