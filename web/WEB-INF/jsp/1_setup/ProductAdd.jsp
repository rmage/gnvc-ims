<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Product</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
            $(document).ready(function(){
                
                $('#addForm').validationEngine('attach');        
                
            });
        </script>
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
                    <form action="Product.htm" method="post" name="form" id="addForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>
                         <input type="hidden" name="productId" value="<%= dto.getProductId()%>"/>
                        <table class="collapse tblForm row-select">
                            <caption>Product - Add</caption>
                            <tbody class="tbl-nohover">
                            	<tr>
                                 <td></td>
                                    <td class="style1">
                                        
                                    </td>
                                    <td class="style1"><b>Bar Code</b></td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="autofocus" name="barcode" value="<%=dto.getBarCode() %>" size="30" maxlength="30"/>
                                        </label>
                                    </td>
								</tr>
                            
                                <tr>
                                   <td class="style1">Item Code</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" name="productCode" value="<%=dto.getProductCode() %>" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td class="style1">Item Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="productName" value="<%=dto.getProductName() %>" size="50" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                <tr>
                                	<td class="style1">Brand Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="brand" name="brand" value="<%=dto.getBrandName() %>" size="30" />
                                        </label>
                                    </td>
                                    <td class="style1">Description</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="description" value="<%=dto.getProductDescription() %>" size="50" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Category</td>
                                    <td class="style1">
                                    	<label>
	                                        <select id="category" name="category">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListCategory}">
	                                        		  <option value="${droplist.categoryName}" ${(droplist.categoryName eq requestScope.model.categoryName)? "selected": ""}>
	                                        		  	${droplist.categoryName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	<label class="requiredfield" title="This Field Is Required!">*</label>
                            		</td>
                                    <td class="style1">Item Color</td>
                                     <td class="style1">
                                        <label>
                                            <input type="text" name="color" value="<%=dto.getProductColor() %>" size="30" />
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="style1">Unit of Measurement (UoM)</td>
                                    <td class="style1">
                                    	<label>
	                                        <select name="uom">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListUOM}">
	                                        		  <option value="${droplist.uomName}" ${(droplist.uomName eq requestScope.model.uomName)? "selected": ""}>
	                                        		  	${droplist.uomName}
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
                                    <td class="style1"></td>
                                    <td class="style1">
                                    	<%-- 
                                        <label>
	                                        <select name="supplierName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListSupplier}">
	                                        		  <option></option>
	                                        		  <option value="${droplist.supplierName}" ${(droplist.supplierName eq requestScope.model.supplierName)? "selected": ""}>
	                                        		  	${droplist.supplierName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                    	--%>
                                    </td>
                                    <td class="style1">Buyer FG</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" id="buyer" name="buyer" value="" size="30" />
                                        </label>
                            		</td>
                                </tr>
                                <tr>
                                	<td class="style1">Pack Style</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="packstyle" name="packstyle" value="" size="30" />
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
                                            <input type="text" id="lid" name="lid" value="" size="30" />
                                        </label>
                                    </td>
                                    <td class="style1">NW / DW-PW</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="nwdwpw" name="nwdwpw" value="" size="30" />
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">
                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSave" id="btnSave" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
                                    </td>
                                </tr>
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
                $('#btnCancel').click(function() {
                    location.href = 'Product.htm';
                });
            });

            $("#btnSave").click(function () {                         

                //if invalid do nothing
                if(!$("#addForm").validationEngine('validate')){
                    $("#dialog-incomplete").dialog({
                            open: function () {
                                $(this).parents(".ui-dialog:first").find(".ui-dialog-titlebar").addClass("ui-state-error");
                                $(this).parents(".ui-dialog:first").find(".ui-button").addClass("ui-state-error");
                            },
                            title: 'Incomplete Form',
                            resizable: false,
                            height: 120,
                            modal: true,
                            buttons: {
                                "Ok" : function () {
                                    $(this).dialog("close");
                                }
                            }
                        });
                    return false;
                 }
                
                $("#dialog-confirm").dialog({ width: 300, height: 150, position: "center", modal: true, 
                    buttons: {
                        "Cancel": function() {                                       
                            $( this ).dialog( "close" );                                        
                        },
                        "Save": function() {
                            $("form#addForm").submit();
                        }
                    },
                    zindex: 1, title: 'Confirm' });

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
    	</script>
                                        
                                  
        <!-- added by edw for uploading products csv -->
        
        <div id="dialog-upload" title="Product Upload" style="display:none;z-index:1;">
            <iframe id="uploadFrameID" name="uploadFrame" height="0" width="0" frameborder="0" scrolling="yes"></iframe>  
            <form id="myForm" enctype="multipart/form-data" method="post" 
                  target="uploadFrame" 
                  action="uploadProductServlet" onsubmit="ajaxFunction()">
                <input type="file" name="txtFile" id="txtFile" /><br />
                <input type="submit" id="submitID" name="submit" value="Upload" />
            </form>

            <div id="initializing" style="visibility: hidden; position:    absolute; top: 100px;">
                <table width="100%" style="border: 1px; background-color: black;">
                    <tr>
                        <td>
                            <table width="100%" style="border: 1px; background-color:    black; color: white;">
                                <tr>
                                    <td align="center">
                                        <b>Initializing Upload...</b>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>

            <div id="progressBarTable" style="visibility: hidden; position:   absolute; top: 100px;">
                <table width="100%" style="border: 1px; color: white;">
                    <tr>
                        <td>
                            <table id="progressBar" width="100%" >
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <table width="100%" style="background-color: white; color: black;">
                    <tr>
                        <td align="center" nowrap="nowrap">
                            <span id="bytesRead" style="font-weight: bold;">&nbsp;</span>
                        </td>
                    </tr>
                </table>  
            </div>

            <div id="percentCompleteTable" align="center"
                 style="visibility: hidden; position: absolute; top: 100px;">
                <table width="100%" style="border: 1px;">
                    <tr>
                        <td>
                            <table width="100%" style="border: 1px;">
                                <tr>
                                    <td align="center"  width="100%">
                                        <span id="percentComplete" style="color: blue; font-weight: bold; width:100%">&nbsp;</span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        
        <script language="javascript">
                var req;
                
                function ajaxFunction(){
                    var url = "uploadProductServlet";
                    
                    if (window.XMLHttpRequest){ 
                        req = new XMLHttpRequest();
                        req.onreadystatechange = processStateChange;
                        
                        try{
                            req.open("GET", url, true);
                        } catch (e) {
                            alert(e);
                        }
                        req.send(null);
                    } else if (window.ActiveXObject) { 
                        req = new ActiveXObject("Microsoft.XMLHTTP");
                        
                        if (req) {
                            req.onreadystatechange = processStateChange;
                            req.open("GET", url, true);
                            req.send();
                        }
                    }
                }
                
                function processStateChange(){
                    if (req.readyState == 4){
                        if (req.status == 200){
                            var xml = req.responseXML;
                            var isNotFinished = xml.getElementsByTagName
                            ("finished")[0];
                            var myBytesRead = xml.getElementsByTagName
                            ("bytes_read")[0];
                            var myContentLength = xml.getElementsByTagName
                            ("content_length")[0];
                            var myPercent = xml.getElementsByTagName
                            ("percent_complete")[0];
                            if ((isNotFinished == null) && (myPercent == null)){
                                document.getElementById("initializing").style.
                                    visibility = "visible";
                                window.setTimeout("ajaxFunction();", 100);
                            } else {
                                document.getElementById("initializing").style.
                                    visibility = "hidden";
                                document.getElementById("progressBarTable").style.
                                    visibility = "visible";
                                document.getElementById("percentCompleteTable").style.
                                    visibility = "visible";
                                document.getElementById("bytesRead").style.
                                    visibility = "visible";
                                
                                myBytesRead = myBytesRead.firstChild.data;
                                myContentLength = myContentLength.firstChild.data;
                                
                                if (myPercent != null) {
                                    myPercent = myPercent.firstChild.data;
                                    
                                    document.getElementById("progressBar").style.width 
                                        = myPercent + "%";
                                    document.getElementById("bytesRead").innerHTML 
                                        = myBytesRead + " of " + 
                                        myContentLength + " bytes read";
                                    document.getElementById("percentComplete").innerHTML 
                                        = myPercent + "100%";
                                    window.setTimeout("ajaxFunction();", 100);
                                } else { // finished
                                    document.getElementById("bytesRead").style.visibility 
                                        = "hidden";
                                    document.getElementById("progressBar").style.width 
                                        = "100%";
                                    document.getElementById("percentComplete").innerHTML 
                                        = "File Uploading Done!";
                                    document.getElementById("txtFile").value="";                                                                       
                                    
                                    alert('success uploading....');
                                    
                                    // close
                                    $("#dialog-upload").dialog('close');
                                }
                            }
                        } else {
                            alert(req.statusText);
                        }
                    }
                }
                
                
                $("#uploadlink").click(function () {
                        $("#dialog-upload").dialog({ width: 350, height: 150, position: "center", modal: true, zindex: 1, title: 'Upload' });
                });
            </script>
                
        <!--    end of uploading    -->             
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>            
                                        
    </body>
</html>