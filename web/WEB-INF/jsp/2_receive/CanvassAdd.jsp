<%@page import="java.util.Date"%>
<%@page import="com.app.wms.engine.db.dto.Supplier"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Canvassing</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#addForm').validationEngine('attach');        
                    
                });

                function formatCurrency(n) {
                	n = isNaN(n) || n === '' || n === null ? 0.00 : n;
        			return parseFloat(n).toFixed(2);
        		}
        		
        		function numberWithCommas(n) {
        			var parts=n.toString().split(".");
        			return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
        		}
            
		       	function isNumberKey(evt)
		       	{
		    	   var charCode = (evt.which) ? evt.which : evt.keyCode;

		    	   if(charCode==8 || charCode==13|| charCode==99|| charCode==118 || charCode==46)
		    	     {    return true;  }
		    	    if (charCode > 31 && (charCode < 48 || charCode > 57))
		    	    {  return false; }
		    	    return true;

		       	}

		       	function changeFormatMoney(){
			       	a = $("#unitprice").val();
			       	b = formatCurrency(a);
			       	c = numberWithCommas(b);
					
			    	$("#unitprice").val(c);
		       	}
        </script> 
        
        <script language="JavaScript">
                $(document).ready(function(){
                	$("#list3").jqGrid({ url:'prscanvasserjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames:['PRS Number','PRS Date','Date Needed', 'Department Name', 'Remarks', 'Created By'], 
                        colModel:[ {formoptions:{colpos:1,rowpos:1},name:'prsnumber',index:'prsnumber',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
       	                           {formoptions:{colpos:1,rowpos:1},name:'prsdate',index:'prsdate',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
       	                           {formoptions:{colpos:1,rowpos:1},name:'deliverydate',index:'deliverydate',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
                                   {formoptions:{colpos:1,rowpos:1},name:'departmentname',index:'departmentname',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}},
                                   {formoptions:{colpos:1,rowpos:1},name:'remarks',index:'remarks',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}},
                                   {formoptions:{colpos:1,rowpos:1},name:'createdby',index:'createdby',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}},
                                 ], 
                        sortname: 'prsnumber',
                        rowNum:10, 
                        rowList:[10,20,30], 
                        jsonReader : {
                            repeatitems: false
                        },
                        onSelectRow: function(ids) { 
                            if(ids != null) {
                            	// clear table
                                $("#main tbody").html('');
                                $("#main2 tbody").html('');
                                $("#main3 tbody").html('');
	                            //fill
                                var localRowData = $(this).getRowData(ids); 
                                $("#prsnumber").val(localRowData.prsnumber);
                                $("#prsdate").val(localRowData.prsdate);
                                $("#deliverydate").val(localRowData.deliverydate);
                                $("#poreferensi").val(localRowData.poreferensi);
                                $("#departmentname").val(localRowData.departmentname);
                                $("#remarks").val(localRowData.remarks);
                                $("#dialog2").dialog('close');
                            $.ajax({
                            dataType: 'json',
                            success: function(data) {
                            $.each(data.prsDetails, function(k,v){                                                         
                            var rowCount = $('#main tr').length-1;
                            $("<tr class=\"myhover\">"+
							"<td class=\"style1\">"+rowCount+"</td>"+
                        	"<input type=\"hidden\" name=\"productCode1\" value=\""+v.productcode+"\">"+
                        	"<input type=\"hidden\" name=\"productName1\" value=\""+v.productname+"\">"+
                        	"<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\">"+
                        	"<input type=\"hidden\" name=\"uom\" value=\""+v.uom+"\">"+
                        	"<td class=\"style1\">"+v.productname+"</td>"+
                        	"<td class=\"style1\">"+v.uom+"</td>"+	                                                                                                                                                 
                        	"<td class=\"style1\">"+v.qty+"</td>"+ 
                        	"<td class=\"style1\"><span id=\""+rowCount+"-supplier2\"></span> <input type=\"hidden\" name=\"supplier1\" id=\""+rowCount+"-supplier1\" /></td> "                             
                            +"<td class=\"style1\"><a class=\"check\" productCode=\""+v.productcode+"\" "
                            +"productName=\""+v.productname+"\" "
                            +"uom=\""+v.uom
                            +"\" rowCount=\""+rowCount+"\" qty=\""+v.qty+"\" href=\"javascript:void(0)\">Entry</a></td>"+
						    "</tr>").appendTo("#main tbody")
                            });
                            },
                              url: 'prscanvasserdetailjson.htm?param='+localRowData.prsnumber
                            });
                            } 
                        },
                        pager: '#pager3', 
                        sortname: 'id', 
                        viewrecords: true,
                        autowidth:true,
                        rownumber:false,
                        gridview:true, 
                        sortorder: "desc"}); 
                    	jQuery("#list3").jqGrid('navGrid','#pager3',{edit:false,add:false,del:false},{multipleSearch:true});
                    	jQuery("#list3").jqGrid('filterToolbar',{stringResult: false,searchOnEnter: false});
                    
                     $("#prsnumber").click(function () {
                        $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 9999, title: 'Select PRS' });
                     });
                
            });
              
        </script>            
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Canvassing.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Canvassing - Detail</caption>
                            <tbody class="tbl-nohover">
                               
                                <tr>
                                   <td class="style1">&nbsp;</td>
                                   <td class="style1">Prs No</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" name="prsnumber" id="prsnumber" value="" size="25"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td class="style1">Remarks</td>
                                	<td class="style1">
                                    	<label>
                                           <textarea style="width: 374px; height: 51px;" id="remarks" name="remarks" readonly></textarea>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                   <td class="style1">&nbsp;</td>
                                   <td class="style1">Canvasser Name</td>
                                   <td class="style1">
                                        <label>
                                            <input type="text" name="canvassername" id="canvassername" value="" size="25" />                                                   
                                        </label>
                                   </td>
                                   <td>Canvassing Date</td>
                                   <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="canvassingdate" size="25" id="canvassingdate" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                   </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <table class="collapse tblForm row-select"  id="main">
							<caption>Canvassing - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Item</td>
	                                    <td class="style1">UoM</td>
	                                    <td class="style1">Qty</td>
	                                    <td class="style1">Supplier</td>
	                                    <td class="style1">Action</td>
								    </tr>
								</thead>

                       
								<tbody>
									  
								</tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="15"></td>	                                    
	                                </tr>
	                            </tfoot>
                        </table>
                        
                        <table class="collapse tblForm row-select ui-widget-content">

                            <tbody class="tbl-nohover">
                                
                            </tbody>
                            <tfoot class="ui-widget-header">
                                <tr><td colspan="15">
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
                    location.href = 'Canvassing.htm';
                    return false;
                });
            });

            $("#btnSave").click(function () { 
            	
              var rowCount = $('#main tr').length-1;
              console.log("rowCount= "+rowCount--);
            	
          	  if($("#"+rowCount+"-supplier2").val()== ""){
			     alert("please to entry supplier");
			  }else{
               
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
	                   
	                   
            }
          	  
             

            });
            
            $('.check').live("click", function() {

  				// clear values
                $("#unitprice").val("0");

                // setup values
                $('#productCode').val($(this).attr('productCode'));                        
                $('#productName').val($(this).attr('productName')); 
                $('#UoM').val($(this).attr('uom'));      
                $('#qtypo').val($(this).attr('qty'));                     
                
                var productCode = $(this).attr('rowcount');
                var qty = $(this).attr('qty');
                $("#dialog3").dialog({ width: 440, height: 300, position: "center", modal: true, 
                       buttons: {
                           "Cancel": function() {     

				                   $("#unitprice").val("0");
                                   $( this ).dialog( "close" );                                        
                           },
                           "Save": function() { 

                        	   /*
							   if( $("#unitprice").val() == "0" ){
									alert("data empty : 'please to entry unit price' ");
									return false;
							   }
							   */				
                            	   $("#"+productCode+"-unitprice2").html($('#unitprice').val());
                            	   $("#"+productCode+"-supplier2").html($('#supplier').val());

                                   $("#"+productCode+"-unitprice2").val($('#unitprice').val());
                                   $("#"+productCode+"-supplier2").val($('#supplier').val());

                                   $("#"+productCode+"-unitprice1").val($('#unitprice').val());
                                   $("#"+productCode+"-supplier1").val($('#supplier').val());

                                   $( this ).dialog( "close" );
							  
                               
                           }
                       },
                    zindex: 9999, title: 'Set Supplier' });                     
            }); 

           
        </script>
        
        <div id="dialog2" title="PRS Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
           <div id="dialog3" title="Item Search" style="display:none;z-index:1;">
             <form>
                 <table>
                     <tr>
                         <td> <label>Item Code</label></td>
                         <td><input type="text" name="productCode" id="productCode" readonly /> </td>
                     </tr>
                     <tr>
                         <td><label>Item Name</label></td>
                         <td><input type="text" name="productName" id="productName" size="30" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Unit</label></td>
                         <td> <input type="text" name="uom" id="UoM" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Qty</label></td>
                         <td> <input type="text" name="qtypo" id="qtypo" readonly /></td>
                     </tr>
                     <tr>
                         <td> <label>Supplier</label></td>
                         <td class="style1">
                                <label>
                                 <select name="supplierName" id="supplier">
                                 	<c:forEach var="droplist" items="${requestScope.model.dropListSupplier}">
                                 		  <option value="${droplist.supplierCode}" ${(droplist.supplierCode eq requestScope.model.supplierCode)? "selected": ""}>
                                 		  	${droplist.supplierName}
                                 		  </option> 
                                 	</c:forEach>
                                 </select>
                            	</label>
                          </td>
                     </tr>
                 </table>                                 
            </form>
        	</div>
        
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
    </body>
</html>