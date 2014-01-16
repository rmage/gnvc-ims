<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Berita Acara Pemeriksaan</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#addForm').validationEngine('attach');   

                    $("#list2").jqGrid({ url:'purchaseorderjson1.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames:['PO Number','PO Date','Supplier','Created By'], 
                        colModel:[ {name:'ponumber',index:'ponumber'}, {name:'podate',index:'podate'}, {name:'supplierName',index:'supplierName'},{name:'createdby',index:'createdby'}
                             ], 
                        sortname: 'ponumber',
                        rowNum:10, rowList:[10,20,30], 
                        jsonReader : {
                            repeatitems: false
                        },
                        onSelectRow: function(ids) { 
                            if(ids != null) { 
                                    // clear table
                                    $("#main tbody").html('');
                                    
                                    // fill
                                    var localRowData = $(this).getRowData(ids); 
                                    $("#purchaseNo").val(localRowData.ponumber);  
                                    $("#podate").val(localRowData.podate);
                                    $("#total").val( $("#total1").val());
                                    
                                    $("#dialog2").dialog('close');                                            
                                    $.ajax({
                                      dataType: 'json',
                                      success: function(data) {
                                      $.each(data.poDetails, function(k,v){                                                         
                                      var rowCount = $('#main tr').length-1;
                                        $("<tr class=\"myhover\">"+
										"<td class=\"style1\">"+rowCount+"</td>"+
	                                    "<td class=\"style1\">"+v.productCode+" </td>"+
	                                    "<input type=\"hidden\" name=\"productCode1\" value=\""+v.productCode+"\">"+
	                                    "<td class=\"style1\">"+v.productName+"</td>"+
	                                    "<input type=\"hidden\" name=\"productName1\" value=\""+v.productName+"\">"+
	                                    "<td class=\"style1\">"+v.qty+"</td>"+	 
	                                    "<input type=\"hidden\" name=\"qty1\" value=\""+v.qty+"\">"+
							    		"</tr>").appendTo("#main tbody")});
                                    	},
                                        	url: 'purchaseorderdetailjson.htm?param='+localRowData.ponumber
                                    	});
                                	} 
                        		},
                        		pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                    			jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
                
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

		       	function changeFormatFromAmount(){
			       	a = $("#fromamount").val();
			       	b = formatCurrency(a);
			       	c = numberWithCommas(b);
					
			    	$("#fromamount").val(c);
			    	
		       	}
		       	
		       	function changeFormatToAmount(){
			    	d = $("#toamount").val();
			       	e = formatCurrency(d);
			       	f = numberWithCommas(e);
					
			    	$("#toamount").val(f);
			    	
		       	}
		       	
		       	
		       
		       	
        </script> 
               
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
            String mode = (String) m.get("mode");
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="Bap.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Berita Acara Pemeriksaan Pekerjaan - Add</caption>
                            <tbody class="tbl-nohover">
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Berita Acara No.</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="beritano" id="beritano" value="" size="30" class="validate[required] text-input" />     
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                    <td>Berita Acara Date</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="beritadate" name="beritadate" 
                                                   
                                                   value="<%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>"
                                                   readonly
                                                   size="30"/>
                                        </label>
                                    </td>
                               </tr>                           
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="purchaseNo" id="purchaseNo" size="30" readonly class="validate[required] text-input datepicker" value=""
                                                   />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                             <img width="16" height="16" src="resources/images/search.png" alt="Search Product" />
                                        </label>
                                    </td>
                                    <td>Purchase Order Date</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="podate" id="podate" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Total</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="total" id="total" size="30" class="validate[required] text-input datepicker" value=""
                                                   />
                                            <label class="requiredfield" title="This Field Is Required!">*</label>
                                        </label>
                                    </td>
                                    <td>Contractor Name</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="contractor" id="contractor" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Result Work</td>
                                    <td class="style1">
                                        <input type="checkbox" name="resultwork" value="100" checked>&nbsp;100%</input>
                                    </td>
                                    <td>End User</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="enduser" id="enduser" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Pertial Completion</td>
                                    <td class="style1">
                                        <input type="checkbox" name="pertialcompletion" value="80/50" checked>&nbsp;(80%)50%</input>
                                    </td>
                                    <td>Plant Enginerr</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="plantengineer" id="plantengineer" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                     <td width="20%">Retention</td>
                                    <td class="style1">
                                         <input type="checkbox" name="retention" value="10" checked>&nbsp;10%</input>
                                    </td>
                                    <td>Operation Enginner</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="operationengineer" id="operationengineer" value="" size="30" />     
                                        </label>
                                    </td>
                                </tr>
                                
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Description of Work</td>
                                    <td class="style1">
                                       <label>
                                           <textarea style="width: 374px; height: 51px;" id="descriptionwork" name="descriptionwork" ></textarea>
                                        </label>
                                    </td>
                                    <td>Total Progress %</td>
                                    <td class="style1">
                                    	<label>
                                            <input type="text" name="totalprogress" id="totalprogress" value="" size="10" />     
                                        </label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        
                        <table class="collapse tblForm row-select"  id="main">
							<caption>Item - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Item Code </td>
	                                    <td class="style1">Item Name</td>
	                                    <td class="style1">Qty</td>
								    </tr>
								</thead>
                                <tbody>									                                                                                                                                                     
                                                                                                
                                </tbody>
								
	                            <tfoot><br/>
	                                <tr>
	                                    <td colspan="5"></td>
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
                    location.href = 'Bap.htm';
                    return false;
                });

            });

            $("#purchaseNo").click(function () {
                $("#dialog2").dialog({ width: 450, height: 275, position: "center", modal: true, zindex: 9999, title: 'Select PO' });
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
        
        <div id="dialog2" title="Product Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="confirm" style="display:none;z-index:1;">
            Save data?
        </div>
        
        <div id="dialog-incomplete" title="incomplete" style="display:none;z-index:1;">
            Please to fill mandatory data
        </div>
        
    </body>
</html>