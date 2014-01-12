<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - New Stores Withdrawal Slip</title>
            <%@include file="../metaheader.jsp" %>
            <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#swsAddForm').validationEngine('attach');        
                    
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
                                            $("#dialog").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                    
                    /*
                     * calendar po estimated delivery
                     */
                    $('#estimation').datepicker({                        
                        dateFormat: "dd/mm/yy"                        
                    });
                    
                    
                    /*
                     * calendar expirydate
                     */
                    $('#expirydate').datepicker({                        
                        dateFormat: "dd/mm/yy"                        
                    });
                    
                    /*
                     * calendar po created
                     */
                    $('#pocreated').datepicker({                        
                        dateFormat: "dd/mm/yy"                        
                    });
                    
                     $("#poreferensi").click(function () {
                        $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 9999, title: 'Select PO' });
                    });
                    
                    /*
                     * button add
                     */
                    var queryArr = [];
                    $('#btnAddItem').click(function(){            
                        var productname = $("#productname").val();
                        var jumlah = $("#jumlah").val();
                        var isiArray = $("#productcode").val()+" "+$("#tipebarang").val();
                        if($.inArray(isiArray, queryArr) == -1){
                            queryArr.push(isiArray);
                        } else {
                            alert('Already Have Item');
                            return false;
                        }                        
                        
                        if(productname == '' || jumlah == '') {
                            alert('Tolong lengkapi semua data');
                            return false;
                        }
                        var rowCount = $('#main tr').length-1;
                        $("<tr class=\"myhover\"><td class=\"style1\">"+rowCount+"</td><td class=\"style1\">"+$("#productname").val()+" </td><td class=\"style1\">"
                            +$("#jumlah").val()+"</td><td class=\"style1\">"+$("#tipebarang").val()+"</td> "+
                            "<input type=\"hidden\" name=\"productcode1\" value=\""+$("#productcode").val()+"\" />"+
                            "<input type=\"hidden\" name=\"producttype\" value=\""+$("#tipebarang").val()+"\" />"+
                            "<input type=\"hidden\" name=\"qty\" value=\""+$("#jumlah").val()+"\" /> </tr>").appendTo("#main tbody")
                        
                        $("#productcode").val('');
                        $("#productname").val('');
                        $("#productdesc").val('');    
                        $("input:radio").attr("checked", false);
                        $("#jumlah").val('');                            
                    });
                    
                });
                
            </script>                                       
            
            <script language="javascript">
                var req;
                
                function ajaxFunction(){
                    var url = "uploadServlet";
                    
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
                            var hash = xml.getElementsByTagName("hash")[0];
                            var hashDetail = "";
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
                                    
                                    hashDetail = hash.firstChild.data;
                                                    
                                    // clear table
                                    $("#main tbody").html('');
                                                    
                                    // ajax call
                                    $.ajax({
                                        dataType: 'json',
                                        success: function(data) {
                                             $.each(data.pocsv, function(k,v){                                                         
                                                var rowCount = $('#main tr').length-1;
                                                
                                                $("<tr class=\"myhover\"><td class=\"style1\">"+rowCount+"</td><td class=\"style1\">"+v.productName+" </td><td class=\"style1\">"
                                                    +v.qty+"</td><td class=\"style1\">"+
                                                    
                                                    "<select name=\"producttype\" id=\"tipebarang\" >"+
                                                        "<option value=\"General\">General</option>"+
                                                        "<option value=\"Promo\">Promo</option>"+
                                                        "<option value=\"Tester\">Tester</option>"+                                            
                                                    "</select>" 
                                                    
                                                    +"</td> "+
                                                    "<input type=\"hidden\" name=\"productcode1\" value=\""+v.productCode+"\" />"+
//                                                    "<input type=\"hidden\" name=\"producttype\" value=\""+$("#tipebarang").val()+"\" />"+
                                                    "<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\" /> </tr>").appendTo("#main tbody")                                                             
                                            });
                                        },
                                        url: 'poCSVJson.htm?param='+hashDetail
                                    });
                                    
                                    // close
                                    $("#dialog-upload").dialog('close');
                                }
                            }
                        } else {
                            alert(req.statusText);
                        }
                    }
                }
            </script>
            
        </head>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");    
            
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE, dd MM yyyy");
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    boolean isReadOnly = true;
  		    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        %>


        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="Sws.htm" method="post" id="swsAddForm">
                        <input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />

                        <table class="collapse tblForm row-select">
                            <caption>Stores Withdrawal Slip Add</caption>
                            <tbody class="tbl-nohover">

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">WS No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="swsNo" id="swsNo" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                  <td>&nbsp;</td>
                                    <td>WS Date</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="swsDate" size="25" id="swsDate" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Department Name</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="departmentName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListDepartment}">
	                                        		  <option value="${droplist.departmentName}" ${(droplist.departmentName eq requestScope.model.departmentName)? "selected": ""}>
	                                        		  	${droplist.departmentName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td>Remarks</td>
                                    <td class="style1">                                                                                    
                                        <textarea style="width: 374px; height: 71px;" id="remarks" name="remarks"></textarea>
                                   </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Requested By</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="requestedBy" id="requestedBy" value="" size="30"                                                    
                                             />
                                        </label>
                                    </td>
                                  	<td>&nbsp;</td>
                                    <td>Approved By</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="appBy" size="30" id="appBy"  
                                                   value=""  />                                                            
                                        </label>
                                    </td>
                               </tr>
			    		</tbody>                           
                  </table>

                        <div id="tabs">
                            <ul>
                                <li><a href="#product">Products</a></li>
                                <li><a href="#upload">Upload Products</a></li>
                            </ul>
                           
                           <div id="product">
                               
                               <table style="margin-bottom: 0;">
                                   
                              
                               <tr class="detail_genap">
                                    
                                    <td width="20%">Product Code : </td>
                                    <td class="style1" width="30%">
                                        
                                            <input type="text" name="productcode" value="" size="30"  id="productcode" /> 
                                            <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        
                                    </td>
                                    
                                    <td width="20%">Qty : </td>
                                    <td class="style1" width="30%">
                                        <label>
                                            <input type="text" size="30" value="" name="jumlah" 
                                                   class="intValidate"
                                                   id="jumlah" />
                                        </label>
                                    </td>
                               </tr>
                                   
                               <tr class="detail_genap">
                                    <td width="20%">Product Name : </td>
                                    <td class="style1" width="30%">
                                        <input type="text" name="productname" value="" size="30" readonly="" id="productname" />
                                    </td>
                               </tr>
                                   
                               <tr class="detail_genap">
                                   <td width="20%" valign="top">Product Description : </td>
                                   <td class="style1" width="30%">                                                                                    
                                        <textarea style="width: 374px; height: 71px;" id="productdesc" name="productdesc" readonly=""></textarea>
                                   </td>
                                   <td width="20%">Type : </td>
                                    <td align="center">
                                        <select name="tipebarang" id="tipebarang" style="width: 200px">
                                            <option value="General">General</option>
                                            <option value="Promo">Promo</option>
                                            <option value="Tester">Tester</option>                                            
                                          </select>                                        
                                    </td>
                               </tr>
                                   
                               <tfoot>
                               <td colspan="4">
                                    <label>
                                        <input type="button" name="btnAddItem" id="btnAddItem" value="Add" 
                                               class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;" />
                                    </label>
                                    <label>
                                        <input type="button" name="btnCancel" id="btnClearItem" value="Clear"
                                               class="ui-button ui-widget ui-state-default ui-corner-all" role="button" aria-disabled="false" style="font-size: smaller;"/>
                                    </label>
                                </td>
                            </tfoot>
                          </table>
                          </div>
                            
                          <div id="upload">
                              <!-- upload form -->
                              <a href="javascript:void(0)" id="uploadlink"> Upload </a>
                          </div>
                        </div>

						<table class="collapse tblForm row-select"  id="main">
							<caption>Product - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Product </td>
	                                    <td class="style1">Product Qty</td>
	                                    <td class="style1">Type</td>
								    </tr>
								</thead>
								<tbody></tbody>
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="4"></td>	                                    
	                                </tr>
	                            </tfoot>
                        </table>
                        <table class="collapse tblForm row-select ui-widget-content">
                            <tbody class="tbl-nohover">
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="7">
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false"                                                    
                                                   role="button" class="ui-button ui-widget ui-state-default ui-corner-all" 
                                                   name="btnSaveSws" id="btnSaveSws" value="Save" class="simpan" />
                                        </label>
                                        <label>
                                            <input type="button" style="font-size: smaller;" aria-disabled="false" role="button" class="ui-button ui-widget ui-state-default ui-corner-all" name="btnCancel" id="btnCancel" value="Cancel" class="cancel" />
                                        </label>
                                    </td>
                                </tr></tfoot>
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
                    location.href = 'Sws.htm?';
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
                
                $('#tabs > ul').tabs({ selected: 1 });
                
                
                
                $("#productcode").click(function () {
                        $("#dialog").dialog({ width: 900, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Product' });
                });
                
                $("#uploadlink").click(function () {
                        $("#dialog-upload").dialog({ width: 350, height: 150, position: "center", modal: true, zindex: 1, title: 'Upload' });
                });
                
                 
                $("#btnSaveSws").click(function () {                         

                    //if invalid do nothing
                    if(!$("#swsAddForm").validationEngine('validate')){
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
                                $("form#swsAddForm").submit();
                            }
                        },
                        zindex: 1, title: 'Confirm' });

                });
    
        </script>
        
        
        <div id="dialog2" title="Product Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
        <div id="dialog" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
        <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Tolong Lengkapi Semua Data
        </div>
        
        <div id="dialog-upload" title="Product Search" style="display:none;z-index:1;">
            <iframe id="uploadFrameID" name="uploadFrame" height="0" width="0" frameborder="0" scrolling="yes"></iframe>  
            <form id="myForm" enctype="multipart/form-data" method="post" 
                  target="uploadFrame" 
                  action="uploadServlet" onsubmit="ajaxFunction()">
                <input type="hidden" name="hash" id="hash" value="<%= m.get("hash")  %>" />
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
        
    </body>
</html>