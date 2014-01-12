<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Packing</title>
        <%@include file="../metaheader.jsp" %>
        
        <script type="text/javascript">  
        
    	</script> 
        
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Packing dto = (com.app.wms.engine.db.dto.Packing) m.get("dto");
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
                    <form action="Packing.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="savePacking" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Packing</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                	 <td>&nbsp;</td>
                                    <td class="style1">Packing No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="packingNo" value="<%=m.get("packingNo")%>" size="30" />
                                        </label>
                                    </td>
                                    
                                     <td>&nbsp;</td>
                                    <td class="style1">Corporate</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=m.get("corporate")%>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Packing Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="packingDate" value="<%=m.get("date") %>" size="30" />
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td class="style1">Warehouse</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="warehouse" value="<%=m.get("warehouse")%>" size="30" />
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
                            	<li><a href="#product">Product Packing</a></li>
                            	 
                            </ul>
                           
                           <div id="product">
                           	   <tr>
                                        <td width="2%" class="style1">Sales Order &nbsp;</td>
	                                        <c:url  value="Packing.htm" var="urlListSalesOrder">
	                                            <c:param name="action" value="listSalesOrderPacking" />
	                                        </c:url>
	                                        <input type="hidden" name="salesOrderNo" value="<c:out value="${model.salesOrderNumber}"/>"/>
	                                        <input type="hidden" name="salesOrderDate" value="<c:out value="${model.salesOrderDate}"/>"/>
	                                    <td width="8%" class="style1"><input type="text" class="disable" readonly name="salesOrder" id="salesOrder" value="<c:out value="${model.salesOrder}"/>" size="45"/></td>
                                        <td><a href='<c:out value="${urlListSalesOrder}"/>'><img src="resources/images/search.png" width="16" height="16" alt="Search SalesOrder" /></a></td>
                               </tr>
                              <br></br>
                               <tr>
                           			 <td><input type="submit" class="style1" name="btnViewDtlProduct" id="btnViewDtlProduct" value="View Product" /></td>
                           			
                               </tr>
                               
                           </div>
                        
                        </div>
                        
                        <br></br>
                        
                    <table class="collapse tblForm row-select">
							<caption>Product Picking - List</caption>
								<thead>
								    <tr>
										<td width="5%" class="style1">No.</td>
										<td width="15%" class="style1">Picking No</td>
	                                    <td width="45%" class="style1">Product</td>
	                                    <td class="style1">Quantity</td>
								    </tr>
								</thead>

                       
								<tbody id="main">
									  <c:if test="${requestScope.model.tableMap1!=null}">
						                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
										<c:forEach var="prod" items="${requestScope.model.tableMap1}" varStatus="prodCounter">
										   
										    <tr class="ganjil">
												<td align="right">&nbsp;${prodCounter.count}</td>
												<td align="center">&nbsp;
									      			<c:if test="${prod.value.pickingNo ne null}">
									      				${prod.value.pickingNo}
									      			</c:if>
									      		</td>
												<td align="center">&nbsp;
									      			<c:if test="${prod.value.productRequest ne null}">
									      				${prod.value.productRequest}
									      			</c:if>
									      		</td>
									      		<td align="center">&nbsp;
									      			<c:if test="${prod.value.qtyPicking ne null}">
									      				${prod.value.qtyPicking}
									      			</c:if>
									      		</td>
										    </tr>
						
										</c:forEach>
									  </c:if>
								</tbody>
								
	                             <tfoot>
                                <td colspan="7">
                                   
                                </td>
                            	</tfoot>
                     </table>
                     
                     <table class="collapse tblForm row-select">
							<caption>Product Kitting - List</caption>
								<thead>
								    <tr>
										<td width="5%" class="style1">No.</td>
										<td width="15%" class="style1">Kitting No</td>
	                                    <td width="45%" class="style1">Product</td>
	                                    <td class="style1">Quantity</td>
								    </tr>
								</thead>

                       
								<tbody id="main">
									  <c:if test="${requestScope.model.tableMap2!=null}">
						                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
										<c:forEach var="prod" items="${requestScope.model.tableMap2}" varStatus="prodCounter">
										   
										    <tr class="ganjil">
												<td align="right">&nbsp;${prodCounter.count}</td>
												<td align="center">&nbsp;
									      			<c:if test="${prod.value.kittingNo ne null}">
									      				${prod.value.kittingNo}
									      			</c:if>
									      		</td>
												<td align="center">&nbsp;
									      			<c:if test="${prod.value.productRequest ne null}">
									      				${prod.value.productRequest}
									      			</c:if>
									      		</td>
									      		<td align="center">&nbsp;
									      			<c:if test="${prod.value.qtyKitting ne null}">
									      				${prod.value.qtyKitting}
									      			</c:if>
									      		</td>
										    </tr>
						
										</c:forEach>
									  </c:if>
								</tbody>
								
	                             <tfoot>
                                <td colspan="7">
                                    <label>
                                        <input type="submit" name="btnSavePacking" id="btnSavePacking" value="Save" />
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
                    location.href = 'Packing.htm?action=findAll';
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