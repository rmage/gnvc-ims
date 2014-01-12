<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Bundling</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
	        $(document).ready(function(){
	        	
	        
	        	 $("#list2").jqGrid({ url:'productjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                     colNames:['Product Code','Product Name', 'Category','Brand Name', 'Description', 'SKU'], 
                     colModel:[ {name:'productCode',index:'productCode'}, {name:'productName',index:'productName'}, {name:'productCategory',index:'productCategory'}, 
                         {name:'brandName',index:'brandName'},
                         {name:'productDescription',index:'productDescription'}, {name:'sku',index:'sku'}
                          ], 
                     sortname: 'productCode',
                     rowNum:10, rowList:[10,20,30], 
                     jsonReader : {
                         repeatitems: false
                     },
                     onSelectRow: function(ids) { 
                         if(ids != null) {         
                                 var localRowData = $(this).getRowData(ids); 
                                 $("#productcode").val(localRowData.productCode);
                                 $("#productname").val(localRowData.productName);
                                 $("#productdesc").val(localRowData.productDescription);
                                 $("#sku").val(localRowData.sku);
                                 $("#dialog1").dialog('close');
                             } 
                     },
                     pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                 jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
	        });
	        
        </script> 
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Bundle dto = (com.app.wms.engine.db.dto.Bundle) m.get("dto");
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
                    <form action="Bundle.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="saveBundling" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <table class="collapse tblForm row-select">
                            <caption>Bundling</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                	<td width="2%" ></td>
                                    <td width="10%" class="style1">Bundle Code</td>
                                    <td width="25%" class="style1">
                                        <label>
                                            <input type="text" name="bundleCode" value="<%=m.get("bundleCode")%>" size="30" readonly/>
                                        </label>
                                    </td>
                                    <td></td>
                                    <td width="20%" class="style1">Corporate Id</td>
                                    <td width="30%" class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=m.get("corporate")%>" size="30" readonly/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" ></td>
                                    <td width="10%" class="style1">Bundle Name</td>
                                    <td width="25%" class="style1">
                                        <label>
                                            <input type="text" name="bundleName" value="<%=m.get("bundleName") != null? m.get("bundleName") : ""%>" size="30" />
                                        </label>
                                         <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td></td>
                                    <td width="20%" class="style1">Warehouse Id</td>
                                    <td width="30%" class="style1">
                                        <label>
                                            <input type="text" name="warehouse" value="<%=m.get("warehouse")%>" size="30" readonly/>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td></td>
                                    <td class="style1">Bundle Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="bundleDate" value="<%=m.get("date")%>" size="30" readonly/>
                                        </label>
                                    </td>
                                    <td></td>
                                    
                                </tr>
                              
                            </tbody>
                            <tfoot>
                                <td colspan="20">
                                  
                                </td>
                            </tfoot>
                        </table>
                        
                              <div id="tabs">
                            <ul>
                            	<li><a href="#bundling">Bundling</a></li>
                            	 
                            </ul>
                           
                           <div id="bundling">
                           	
                               <tr>
                                        <td width="2%" class="style1">Product &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <input type="text" name="productcode" value="" size="30"  id="productcode" /> 
                                        <input type="hidden" name="productname" value="" size="30"  id="productname" /> 
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        <input type="hidden" name="productid" value="<c:out value="${model.productId}"/>"/>
                                        <input type="hidden" name="productCode" value="<c:out value="${model.productCode}"/>"/>
                                        <input type="hidden" name="productName" value="<c:out value="${model.productName}"/>"/>
                                        <input type="hidden" name="quantity" value="<c:out value="${model.quantity}"/>"/>
                                        
                                        <input type="hidden" name="sku" id="sku" value=""/>
                               </tr>
                               
                               <tr>
                               		<td width="2%" class="style1">Bundle Qty &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td width="18%" class="style1"><input type="text" class="uang" name="qtyProduct" id="qtyProduct" value="0" size="5" /></td>
                                  
                               </tr>
                               
                              
                               <tr>
                        
                           			 <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="style1" name="btnAddDtlProduct" id="btnAddDtlProduct" value="Add" /></td>
                           			
                               </tr>
                               
                           </div>

                        </div>
                        
                        <br></br>
                        
                        <table class="collapse tblForm row-select">
							<caption>Product Bundle - List</caption>
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
                                        <input type="submit" name="btnSaveBundling" id="btnSaveBundling" value="Save" />
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
                    location.href = 'Bundle.htm?action=findAll';
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
            
            	$("#productcode").click(function () {
                $("#dialog1").dialog({ width: 900, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Product' });
        		});
        </script>
        
         <div id="dialog1" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
    </body>
</html>