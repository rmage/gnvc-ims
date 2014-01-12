<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Consolidate</title>
        <%@include file="../metaheader.jsp" %>
        
        <script type="text/javascript">  
        
    	</script> 
        
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            com.app.wms.engine.db.dto.Consolidate dto = (com.app.wms.engine.db.dto.Consolidate) m.get("dto");
            String mode = (String) m.get("mode");
            java.lang.String error = (String)m.get("msg");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Consolidate.htm" method="post" id="consolidateAddForm">
                        <input type="hidden" name="action" value="saveConsolidate" />
                        
                        <table class="collapse tblForm row-select">
                            <caption>Consolidate</caption>
                            <tbody class="tbl-nohover">
                                <tr>
                                	 <td>&nbsp;</td>
                                    <td class="style1">Consolidate No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consolidateNo" value="<%=m.get("consolidateNo") %>" size="30" />
                                        </label>
                                    </td>
                                    
                                     <td>&nbsp;</td>
                                    <td class="style1">Corporate</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="corporate" value="<%=m.get("corporate") %>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td>&nbsp;</td>
                                    <td class="style1">Consolidate Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="consolidateDate" value="<%=m.get("date") %>" size="30" />
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
                            	<li><a href="#consolidate">Consolidate</a></li>
                                
                            </ul>
                           
                           <div id="consolidate">
                           
                               <tr>
                                        <td width="2%" class="style1">Packing No &nbsp;&nbsp;&nbsp;&nbsp;</td>
                                        <c:url  value="Consolidate.htm" var="urlListProduct">
                                            <c:param name="action" value="listPacking" />
                                        </c:url>
                                        <input type="hidden" name="packingNo" value="<c:out value="${model.packingNo}"/>"/>
                                        <input type="hidden" name="packingDate" value="<c:out value="${model.packingDate}"/>"/>
                                        
                                        <td width="10%" class="style1"><input type="text" class="disable" readonly name="packingNo" id="packingNo" value="<c:out value="${model.packingNo}"/>" size="40"/></td>
                                        <td><a href='<c:out value="${urlListProduct}"/>'><img src="resources/images/search.png" width="16" height="16" alt="Search Packing" /></a></td>
                               			   
                               </tr>
                              
                              <br></br>
                               		 
                                   
                           			 <td><input type="submit" class="style1" name="btnAddDtlConsolidate" id="btnAddDtlConsolidate" value="Add" /></td>
                           		
                        </div>
                        
                        </div>
                        
							<br></br>                        
                        	<table class="collapse tblForm row-select">
							
							<caption>Consolidate - List</caption>
								<thead>
								    <tr>
										<td width="2%" class="style1">No.</td>
	                                    <td width="25%" class="style1">Packing No</td>
	                                    <td width="35%" class="style1">Packing Date</td>
								    </tr>
								</thead>

                       
								<tbody id="main">
									  <c:if test="${requestScope.model.tableMap!=null}">
						                <c:set scope="page" value="${((model.page-1)*model.paging)+1}" var="nomor"/>
										<c:forEach var="prod" items="${requestScope.model.tableMap}" varStatus="prodCounter">
										   
										    <tr class="ganjil">
												<td align="right">&nbsp;${prodCounter.count}</td>
                                                                                                <td class="style1"><a href="#" class="no-decoration" onclick="csbShowDetail('PACK', '<c:out value="${prod.value.packingNo}"></c:out>', 'Packing Detail')"><c:out value="${prod.value.packingNo}" /></a></td>
									      		<td align="center">&nbsp;
									      			<c:if test="${prod.value.packingDate ne null}">
									      				${prod.value.packingDate}
									      			</c:if>
									      		</td>
										    </tr>
						
										</c:forEach>
									  </c:if>
								</tbody>
								
	                             <tfoot>
                                <td colspan="7">
                                    <label>
                                        <input type="submit" name="btnSaveConsolidate" id="btnSaveConsolidate" value="Save" />
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
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'Consolidate.htm?action=findAll';
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