<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - Update Product</title>
        <%@include file="../metaheader.jsp" %>
    </head>
    <body>
        <%
    		    java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
    		    com.app.wms.engine.db.dto.Product dto = (com.app.wms.engine.db.dto.Product) m.get("dto");
    		    String mode = (String) m.get("mode");
        %>
       <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Product.htm" method="post">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="productId" value="<%= dto.getProductId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Product - Update</caption>
                            <tbody class="tbl-nohover">
                            	<tr>
                                 <td></td>
                                    <td class="style1">
                                       
                                    </td>
                                    <td class="style1">Bar Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="autofocus" name="barcode" value="<%=dto.getBarCode()!=null? dto.getBarCode(): "" %>" size="30" maxlength="30"/>
                                        </label>
                                    </td>
								</tr>
                            	
                                <tr>
                                   <td class="style1">Item Code</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" name="productCode" value="<%=dto.getProductCode()!=null? dto.getProductCode(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td class="style1">Item Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="productName" value="<%=dto.getProductName()!=null? dto.getProductName(): "" %>" size="30" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                	<td class="style1">Brand Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="brand" name="brand" value="<%=dto.getBrandName()!=null? dto.getBrandName(): "" %>" size="30" />
                                        </label>
                                    </td>
                                    <td class="style1">Description</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="description" value="<%=dto.getProductDescription()!=null? dto.getProductDescription(): "" %>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td class="style1">Category</td>
                                    <td class="style1">
                                    	<label>
	                                        <select id="category" name="category">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
	                                        		  <option value="<c:out value='${droplist.categoryName}'/>" 
	                                        		   <c:if test="${param.selecValue == item }"> selected</c:if> >
	                                        		  	<c:out value="${droplist.categoryName}" />
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                            		</td>
                                    <td class="style1">Item Color</td>
                                     <td class="style1">
                                        <label>
                                            <input type="text" name="color" value="<%=dto.getProductColor()!=null? dto.getProductColor(): "" %>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Unit of Measurement (UoM)</td>
                                    <td class="style1">
                                    	<label>
	                                        <select name="uom">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListUOM}">
	                                        		  <option value="<c:out value='${droplist.uomName}'/>" 
	                                        		   <c:if test="${param.selecValue == item }"> selected</c:if> >
	                                        		  	<c:out value="${droplist.uomName}" />
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                            		</td>
                            		<td class="style1"><b>Upload</b></td>
                                    <td class="style1">
                                            <a href="javascript:void(0)" id="uploadlink"> Upload Products</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Supplier Name</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="supplierName">
	                                            <option></option>
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListSupplier}">
	                                        		  <option value="<c:out value='${droplist.supplierName}' />"
	                                        		    <c:if test="${param.selecValue == item }"> selected</c:if> >
	                                        		  	 <c:out value="${droplist.supplierName}" />
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                        </label>
                                    </td>
                                    <td class="style1">Buyer</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" id="buyer" name="buyer" value="<%=dto.getProductColor()!=null? dto.getProductColor(): "" %>" size="30" />
                                        </label>
                            		</td>
                                </tr>
                                <tr>
                                	<td class="style1">Pack Style</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="packstyle" name="packstyle" value="<%=dto.getPackstyle()!=null? dto.getPackstyle(): "" %>" size="30" />
                                        </label>
                                    </td>
                                    <td class="style1">Pack Size</td>
                                    <td class="style1">
                                         <select name="packsize">
	    										<option value=""></option>  
	    										<option value="603">603</option> 
	    										<option value="307">307</option>    
	 									</select>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">LID</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="lid" name="lid" value="<%=dto.getLid()!=null? dto.getLid(): "" %>" size="30" />
                                        </label>
                                    </td>
                                    <td class="style1">NW / DW-PW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="nwdwpw" name="nwdwpw" value="<%=dto.getNwdwpw()!=null? dto.getNwdwpw(): "" %>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Is Active</td>
                                    <td class="style1">
                                        <label>
                                            <input type="radio" name="isActive" value="Y" <% if (dto.getIsActive().equalsIgnoreCase("Y")) {%> checked="checked" <% }%> /> Y
										</label>
										<label>
										    <input type="radio" name="isActive" value="N" <% if (dto.getIsActive().equalsIgnoreCase("N")) {%> checked="checked" <% }%> /> N
					                    </label>
                                   </td>
                                   <td></td>
                                   <td></td>
                               	</tr>
                            </tbody>
                            <tfoot>
                                <td colspan="5">
                                    <span class="style1">
                                        <label>
                                            <input type="submit" name="btnSave" id="btnSave" value="Save" />
                                        </label>
                                        <input type="button" class="style1" name="button" id="btnBack" value="Back" />
                                    </span>
                                </td>
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
                    location.href = 'Product.htm';
                });
            });
        </script>

        <script language="JavaScript">
			function cek(){
			if(form.length.value == "" || form.width.value == ""|| form.height.value == ""){
			alert("data empty"); 
			exit;
			}
			}
			function kali() {
			cek();
			a=eval(form.length.value);
			b=eval(form.width.value);
			c=eval(form.height.value);
			d=a*b*c
			form.volumeMatrix.value = d;
			}
		</script>
		
		<script type="text/javascript">
		   function formfocus() {
		      document.getElementById('autofocus').focus();
		   }
		   window.onload = formfocus;
		   
		   function getSelectedIndexes(select) {
			   var selected = [];
			   for (var i = 0; i  < select.options.length; i++) {
			       if(select.options[i].selected ) {
			            selected.push(i);
			       }
			   }
			   return selected;
			}

			var select = document.getElementById("options");
			var prevSelected = getSelectedIndexes(select);

			select.onchange = function(e) {
			    var currentlySelected = getSelectedIndexes(this);

			    for (var i =0; i < currentlySelected.length; i++) {
			        if (prevSelected.indexOf(currentlySelected[i]) == -1) {
			            console.log("Added to selection ", this.options[currentlySelected[i]].text);
			        }
			    }

			    for (var i =0; i < prevSelected.length; i++) {
			        if (currentlySelected.indexOf(prevSelected[i]) == -1) {
			            console.log("Removed from selection  ", this.options[prevSelected[i]].text);
			        }
			    }        
			    prevSelected = currentlySelected;
			};
    	</script>
                                        
    </body>
</html>