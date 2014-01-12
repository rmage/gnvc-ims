<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Location</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    
                    $("#list2").jqGrid({ url:'locationproductjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:['Product Code','Product Name', 'Category','Brand Name', 'Description'], 
                                colModel:[ {name:'productcode',index:'productcode'}, {name:'productname',index:'productname'}, {name:'productcategory',index:'productcategory'}, 
                                    {name:'brandname',index:'brandname'},
                                    {name:'productdescription',index:'productdescription'}
                                     ], 
                                sortname: 'productCode',
                                rowNum:10, rowList:[10,20,30,40,50,60], 
                                jsonReader : {
                                    repeatitems: false
                                },
                                onSelectRow: function(ids) { 
                                    if(ids != null) {         
                                            var localRowData = $(this).getRowData(ids); 
                                            $("#productcode").val(localRowData.productcode);
                                            $("#productname").val(localRowData.productname);
                                            $("#productdesc").val(localRowData.productdescription);                                           
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
            com.app.wms.engine.db.dto.WhLocation dto = (com.app.wms.engine.db.dto.WhLocation) m.get("dto");
            String mode = (String) m.get("mode");
            //java.util.List<com.app.wms.engine.db.dto.Wh> dropListWarehouse = (java.util.List<com.app.wms.engine.db.dto.Wh>) m.get("dropListWarehouse");
            //java.util.List<com.app.wms.engine.db.dto.Corporate> dropListCorporate = (java.util.List<com.app.wms.engine.db.dto.Corporate>) m.get("dropListCorporate");
        %>

        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Location.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                        <input type="hidden" name="productId" value="<%= dto.getLocationId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Location - Detail</caption>
                            <tbody class="tbl-nohover">
                            <%-- 
                            	<tr>
                            		<td width="2%" class="style1"></td>
								    <td width="10%" class="style1">Corporate</td>
								    <td class="style1">
                                    	<label>
	                                        <select name="corporate">
                                                <c:forEach var="droplist" items="${requestScope.model.dropListCorporate}">
                                                    <option value="${droplist.id}" ${(droplist.id eq requestScope.model.id)? "selected": ""}>
                                                        ${droplist.id}
                                                    </option> 
                                                </c:forEach>
                                            </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                                 	</td>
								</tr>     
								
								<tr>
									<td width="2%" class="style1"></td>
								    <td width="10%" class="style1">Warehouse</td>
								    <td class="style1">
                                    	<label>
                                            <select name="warehouse">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListWarehouse}">
	                                        		  <option value="${droplist.whCode}" ${(droplist.whCode eq requestScope.model.whCode)? "selected": ""}>
	                                        		  	${droplist.whCode}
	                                        		  </option> 
	                                        	</c:forEach>
					                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                                 	</td>
								</tr>     
							--%>	
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="locationCode" value="<%=dto.getLocationCode() %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="locationName" value="<%=dto.getLocationName() %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                 <tr>
                                 	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Type</td>
                                    <td>
	                                    <select name="locationType">
    										<option value="Zone">Zone</option> 
    										<option value="Area">Area</option> 
    										<option value="Rack">Rack</option>   
    										<option value="PigeonHole">Pigeon Hole</option>  
	 									</select>
	 									<label class="requiredfield" title="This Field Is Required!">*</label>
 									</td>
                                </tr>
                                <tr class="detail_genap">
                                	<td width="2%" class="style1"></td>
                                    <td width="10%">Product Code</td>
                                    <td class="style1">
                                        
                                            <input type="text" name="productcode" value="" size="30"  id="productcode" /> 
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                	</td>
                                </tr>  
                                <tr class="detail_genap">
                                    <td width="2%" class="style1"></td>
                                    <td width="10%">Product Name</td>
                                    <td class="style1">
                                        <input type="text" name="productname" value="" size="30" readonly id="productname" />
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>      
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Minimum Product</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="min" value="" size="5" maxlength="5"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Maximum Product</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="max" value="" size="5" maxlength="5"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                
                                <tr>
                            		<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Locating Area</td>
								    <td class="style1">
                                    	<label>
	                                        <select name="locatingArea">
                                                <c:forEach var="droplist" items="${requestScope.model.dropListLocatingArea}">
                                                    <option value="${droplist.locatingArea}" ${(droplist.locatingArea eq requestScope.model.locatingArea)? "selected": ""}>
                                                        ${droplist.locatingArea}
                                                    </option> 
                                                </c:forEach>
                                            </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                                 	</td>
								</tr>     
                                 <tr>
                                 	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Locating Zone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="zone" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Bay</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="bay" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Level</td>
                                     <td class="style1">
                                        <label>
                                            <input type="text" name="level" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="10%" class="style1">Location Position</td>
                                    <td>
	                                    <select name="locatingPosition">
	                                    	<option value=""></option>
    										<option value="west">West</option> 
    										<option value="east">East</option> 
    										<option value="south">South</option>
    										<option value="north">North</option>
	 									</select>
 									</td>
                                </tr>
                                <%-- 
                                <tr>
	                                <td width="2%" class="style1"></td>
	                                <td width="10%" class="style1">Allocation Zone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="allocationZone" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                --%>
                                <tr>
                                	<td width="2%" class="style1"></td>
                                    <td width="20%" class="style1">Work Zone</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="workZone" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <td colspan="2">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
                                <td></td>
                            </tfoot>
                        </table>
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
                $('#btnBack').click(function() {
                    location.href = 'Location.htm';
                });
				
                $("#productcode").click(function () {
                	console.log("in clicked"); 
                    $("#dialog1").dialog({ width: 825, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Product' });
            	});
            
               
            });
        </script>
        
        <div id="dialog1" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
    </body>
</html>