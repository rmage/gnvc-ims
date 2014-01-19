<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <head>
            <title>IMS - New PRS</title>
            <%@include file="../metaheader.jsp" %>
            <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#prsAddForm').validationEngine('attach');        
                    
                    $("#list2").jqGrid({ url:'productjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                                colNames:[ 'Item Code','Item Name','Stock on Hand','Unit','Item Category','Brand Name', 'Description'], 
                                colModel:[ {name:'productCode',index:'productCode'}, {name:'productName',index:'productName'},
                                           {name:'balance',index:'balance'}, {name:'uomName',index:'uomName'},  
                                           {name:'productCategory',index:'productCategory'}, {name:'brandName',index:'brandName'}, 
                                           {name:'productDescription',index:'productDescription'}], 
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
                                            $("#uomName").val(localRowData.uomName);    
                                            $("#stockonhand").val(localRowData.balance);                                       
                                            $("#dialog").dialog('close');
                                        } 
                                },
                                pager: '#pager2', sortname: 'id', viewrecords: true, sortorder: "desc"}); 
                            jQuery("#list2").jqGrid('navGrid','#pager2',{edit:false,add:false,del:false});
               
                    /*
                     * calendar request date
                     */
                    $('#requestdate').datepicker({                        
                        dateFormat: "dd/mm/yy"                        
                    });
                    
                    /*
                     * button add
                     */
                    var queryArr = [];
                    $('#btnAddItem').click(function(){            
                        var productname = $("#productname").val();
                        var jumlah = $("#jumlah").val();
                        var stockonhand = $("#stockonhand").val();
						
                        var isiArray = $("#productcode").val()+" "+$("#tipebarang").val();
                        if($.inArray(isiArray, queryArr) == -1){
                            queryArr.push(isiArray);
                        } else {
                        	 alert('Already Have Item');
                             return false;
                        }
						
                        if(productname == '' || jumlah == '') {
                            alert('Please to fill items data needed');
                            return false;
                        }
                        
                        var rowCount = $('#main tr').length-1;
                        $("<tr class=\"myhover\"><td class=\"style1\">"
                            +rowCount
                            +"</td><td class=\"style1\">"
                            +$("#productname").val()+" </td><td class=\"style1\">"
                            +$("#jumlah").val()+" </td><td class=\"style1\">"
                            +$("#uomName").val()+
                            "<input type=\"hidden\" name=\"productCode1\" value=\""+$("#productcode").val()+"\" />"+
                            "<input type=\"hidden\" name=\"productName1\" value=\""+$("#productname").val()+"\" />"+
                            "<input type=\"hidden\" name=\"uomName1\" value=\""+$("#uomName").val()+"\" />"+
                            "<input type=\"hidden\" name=\"qty\" value=\""+$("#jumlah").val()+"\" /> </tr>").appendTo("#main tbody")
                        
                        $("#productcode").val('');
                        $("#productname").val('');
                        $("#uomName").val('');  
                        $("#stockonhand").val('');   
                        $("input:radio").attr("checked", false);
                        $("#jumlah").val('');                            
                    });

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

		       	function changeQtyDecimal(){
			       	a = $("#jumlah").val();
			       	b = formatCurrency(a);
			       	c = numberWithCommas(b);

			       	$("#jumlah").val(c);
			       	
			       	d = $("#stockonhand").val();
			       	e = formatCurrency(d);
			       	f = numberWithCommas(e);

			       	$("#stockonhand").val(f);
			       	
		       	}
		       	
            </script>                                       
        </head>
    </head>
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
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
                    <form action="PurchaseRequisition.htm" method="post" id="prsAddForm">
                        <input type="hidden" name="action" value="save" />

                        <table class="collapse tblForm row-select">
                            <caption>Purchase Requisition Add</caption>
                            <tbody class="tbl-nohover">

                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Charged to Department</td>
                                    <td class="style1">
                                        <label>
	                                        <select name="departmentName">
	                                        	<c:forEach var="droplist" items="${requestScope.model.dropListDepartment}">
	                                        		  <option value="${droplist.departmentCode}" ${(droplist.departmentName eq requestScope.model.departmentName)? "selected": ""}>
	                                        		  	${droplist.departmentCode} - ${droplist.departmentName}
	                                        		  </option> 
	                                        	</c:forEach>
	                                        </select>
                                    	</label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                  <td>&nbsp;</td>
                                    <td>PRS Date</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="prsdate" size="25" id="prsdate" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Date Needed</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="requestdate" name="requestdate" value="" size="30"
                                                   class="validate[required] text-input" 
                                                   />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Remarks</td>
                                    <td class="style1">
                                    	<label>
                                           <textarea style="width: 374px; height: 51px;" id="remarks" name="remarks" ></textarea>
                                        </label>
                                    </td>
                                    
                               </tr>
                               
			    		</tbody>                           
                  </table>

                        <div id="tabs">
                            <ul>
                                <li><a href="#product">Items</a></li>
                            </ul>
                           
                           <div id="product">
                               
                           <table style="margin-bottom: 0;">
                               <tr class="detail_genap">
                                    
                                    <td width="10%">Item Code</td>
                                    <td class="style1" width="20%">
                                        <input type="text" name="productcode" value="" size="30"  
                                        	
                                        	id="productcode" /> 
                                        <img width="16" height="16" src="resources/images/search.png" alt="Search Items" />
                                    </td>
                                    <td width="10%">Stock On Hand</td>
                                    <td class="style1" width="20%">
                                        <label>
                                            <input type="text" size="30" value="" name="stockonhand" 
                                                  onblur="changeStockOnHandDecimal()"
                                                   id="stockonhand" />
                                        </label>
                                    </td>
                                    <td width="10%">Unit</td>
                                   	<td class="style1" width="20%">
                                        <label>
	                                        <input type="text" name="uomName" value="" size="30" readonly="" id="uomName" />
                                    	</label>                           
                                    </td>
                               </tr>
                                   
                               <tr class="detail_genap">
                                    <td width="10%">Item Name</td>
                                    <td class="style1" width="20%">
                                        <input type="text" name="productname" value="" size="30" readonly="" id="productname" />
                                    </td>
                                   <td width="10%">Qty</td>
                                    <td class="style1" width="20%">
                                        <label>
                                            <input type="text" size="30" value="" 
                                            onblur="changeQtyDecimal()"
                                            onkeypress="return isNumberKey(event)" name="jumlah" id="jumlah" />
                                        </label>
                                    </td>
                               </tr>
                                   
                            <tfoot>
                               <td colspan="4">
                                    <label>
                                        <input type="button" name="btnSavePurchase" id="btnAddItem" value="Add" 
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
                            
                        </div>

						<table class="collapse tblForm row-select"  id="main">
							<caption>Items - List</caption>
								<thead>
								    <tr>
										<td class="style1">No.</td>
	                                    <td class="style1">Description</td>
	                                    <td class="style1">Qty</td>
	                                    <td class="style1">Unit</td>
								    </tr>
								</thead>

                       
								<tbody>
									  
								</tbody>
								
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
                                                   name="btnSavePurchase" id="btnSavePurchase" value="Save" class="simpan" />
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
                    location.href = 'PurchaseRequisition.htm?';
                });

                $('#btnClearItem').click(function() {
                	window.location = window.location.href = 'PurchaseRequisition.htm?action=create';
                	$("#main tbody").html('');
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
                        $("#dialog").dialog({ width: 900, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Item' });
                });
                 
                $("#btnSavePurchase").click(function () {                         

                    //if invalid do nothing
                    if(!$("#prsAddForm").validationEngine('validate')){
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
                                $("form#prsAddForm").submit();
                            }
                        },
                        zindex: 1, title: 'Confirm' });

                });
    
        </script>
        
        
        <div id="dialog2" title="Item Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
        <div id="dialog" title="Item Search" style="display:none;z-index:1;">
            <table id="list2" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager2"></div> 
        </div>
        
        <div id="dialog-confirm" title="Item Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
        <div id="dialog-incomplete" title="Item Search" style="display:none;z-index:1;">
             Please to fill all mandatory data
        </div>
        
    </div>
        
    </body>
</html>