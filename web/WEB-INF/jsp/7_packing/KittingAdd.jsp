<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Kitting</title>
        <%@include file="../metaheader.jsp" %>
        <script type="text/javascript"></script> 
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Kitting dto = (com.app.wms.engine.db.dto.Kitting) m.get("dto");
            String mode = (String) m.get("mode");
            com.app.wms.engine.db.dto.Product product = (com.app.wms.engine.db.dto.Product) request.getSession().getAttribute("product");
  		    com.app.wms.engine.db.dto.Customer customer = (com.app.wms.engine.db.dto.Customer) request.getSession().getAttribute("customer");
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    boolean isReadOnly = true;
  		    java.lang.String error = (String)m.get("msg");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Kitting.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="saveKitting" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Kitting</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                	<td width="2%" ></td>
                                    <td width="10%" class="style1">Kitting No</td>
                                    <td width="25%" class="style1">
                                        <label>
                                            <input type="text" name="kittingNo" value="<%=m.get("kittingNo")%>" size="30" />
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%" class="style1">Corporate Id</td>
                                    <td width="30%" class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=m.get("corporate")%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td></td>
                                    <td class="style1">Kitting Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="kittingDate" value="<%=m.get("date")%>" size="30" />
                                        </label>
                                    </td>
                                    <td></td>
                                    <td class="style1">Warehouse</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="warehouse" value="<%=m.get("warehouse")%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                               <tr>
                           			<td></td>
                                    <td class="style1">Sales Order &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	                                     <c:url  value="Kitting.htm" var="urlListSalesOrder">
	                                         <c:param name="action" value="listSalesOrderKitting" />
	                                     </c:url>
	                                     <input type="hidden" name="salesOrderNo" value="<c:out value="${model.salesOrderNumber}"/>"/>
	                                     <input type="hidden" name="salesOrderDate" value="<c:out value="${model.salesOrderDate}"/>"/>
                                 	<td width="30%" class="style1"><input type="text" class="disable" readonly name="salesOrder" id="salesOrder" value="<c:out value="${model.salesOrder}"/>" size="45"/>
                                    	 <a href='<c:out value="${urlListSalesOrder}"/>'><img src="resources/images/search.png" width="16" height="16" alt="Search SalesOrder" /></a>
                           			</td>
                           			<td></td>
                           			<td class="style1">Remarks Kitting</td>
                                    <td class="style1">
                                        <label>
                                            <textarea style="width: 324px; height: 41px;" id="remarks" name="remarks"><%=m.get("remarks")!=null? m.get("remarks"): ""%></textarea>
                                        </label>
                               		</td>
                               </tr>

                            </tbody>
                            <tfoot>
                                <td colspan="20">
                                  
                                </td>
                            </tfoot>
                        </table>
                        
                              <div id="tabs">
                            <ul>
                            	<li><a href="#kitting">Kitting</a></li>
                            	 
                            </ul>
                           
                           <div id="kitting">
                           	
                               <tr>
                                        <td width="2%" class="style1">Product &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <c:url  value="Kitting.htm" var="urlListProduct">
                                            <c:param name="action" value="listProductKitting" />
                                        </c:url>
                                        <input type="hidden" name="productId" value="<c:out value="${model.productId}"/>"/>
                                        <input type="hidden" name="productCode" value="<c:out value="${model.productCode}"/>"/>
                                        <input type="hidden" name="productName" value="<c:out value="${model.productName}"/>"/>
                                        <input type="hidden" name="quantity" value="<c:out value="${model.quantity}"/>"/>
                                        <input type="hidden" name="unitCode" value="<c:out value="${model.unitCode}"/>"/>
                                        <input type="hidden" name="locationCode" value="<c:out value="${model.locationCode}"/>"/>
                                        <td width="10%" class="style1"><input type="text" class="disable" readonly name="productName" id="productName" value="<c:out value="${model.productName}"/>" size="40"/></td>
                                        <td><a href='<c:out value="${urlListProduct}"/>'><img src="resources/images/search.png" width="16" height="16" alt="Search Product" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                               </tr>
                               
                               <tr>
                               		<td width="2%" class="style1">Kitting Qty &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td width="18%" class="style1"><input type="text" class="uang" name="qtyProduct" id="qtyProduct" value="0" size="5" /></td>
                                  
                               </tr>
                               
                              
                               <tr>
                        
                           			 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="style1" name="btnAddDtlProduct" id="btnAddDtlProduct" value="Add" /></td>
                           			
                               </tr>
                               
                           </div>

                        </div>
                        
                        <br></br>
                        
                        <table class="collapse tblForm row-select">
							<caption>Product Kitting - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Product</td>
	                                    <td class="style1">Quantity</td>
								    </tr>
								</thead>

                       
								<tbody id="main">
									  <c:if test="${requestScope.model.tableMap!=null}">
						                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
										<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
										   
										    <tr class="ganjil">
												<td align="right">&nbsp;${prodCounter.count}</td>
												<td align="center">&nbsp;
									      			<c:if test="${prod.value.productRequest ne null}">
									      				${prod.value.productRequest}
									      			</c:if>
									      		</td>
									      		<td align="center">&nbsp;
									      			<c:if test="${prod.value.requestQuantity ne null}">
									      				${prod.value.requestQuantity}
									      			</c:if>
									      		</td>
										    </tr>
						
										</c:forEach>
									  </c:if>
								</tbody>
								
	                             <tfoot>
                                <td colspan="7">
                                    <label>
                                        <input type="submit" name="btnSaveKitting" id="btnSaveKitting" value="Save" />
                                    </label>
                                    <label>
                                        <input type="submit" name="btnCancel" id="btnCancel" value="Cancel" />
                                    </label>
                                </td>
                            	</tfoot>
                        </table>
					
                         <% 
                       			if (error != null) {
						 %>
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
						<%
							                							            
}
						%>							
								
	                           
                    </form>
                    
                </div>
            </div>
            <div class="span-24 last border-top">
                <div class="box">
                    &copy; 2013 SPFI
                </div>
            </div>
        </div>
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'Kitting.htm?action=findAll';
                });

                var cookieName, $tabs, stickyTab;

                cookieName = 'stickyTab';
                $tabs = $( '#tabs' );

                $tabs.tabs( {
                    select: function( e, ui )
                    {
                        $.cookies.set( cookieName, ui.index );
                    }
                } );

                stickyTab = $.cookies.get( cookieName );
                if( ! isNaN( stickyTab )  )
                {
                    $tabs.tabs( 'select', stickyTab );
                }
                
                });
        </script>
    </body>
</html>