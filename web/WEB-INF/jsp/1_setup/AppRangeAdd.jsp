<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>IMS - New Approval Range</title>
        <%@include file="../metaheader.jsp" %>
        <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#addForm').validationEngine('attach');   

                    $("#list2").jqGrid({ url:'userrolejson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
                        colNames:['Role Code','User Name'], 
                        colModel:[{name:'rolecode',index:'rolecode'}, {name:'username',index:'username'}], 
                        sortname: 'role_code',
                        rowNum:10, rowList:[10,20,30], 
                        jsonReader : {
                            repeatitems: false
                        },
                        onSelectRow: function(ids) { 
                            if(ids != null) {     
                            	// clear table
                                $("#main tbody").html('');
                            	
                                    var localRowData = $(this).getRowData(ids); 
                                    $("#rolecode").val(localRowData.rolecode);
                                    $("#username").val(localRowData.username);
                                    $("#dialog1").dialog('close');
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
        %>
        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">

                <div class="box">
                    <form action="ApprovalRange.htm" method="post" id="addForm">
                    	<input type="hidden" name="mode" value="<%=mode%>" />
                        <input type="hidden" name="action" value="save" />
                        <input type="hidden" name="isActive" value="Y"/>

                        <table class="collapse tblForm row-select">
                            <caption>Approval Range - Detail</caption>
                            <tbody class="tbl-nohover">
                                
                                <tr>
                                    <td class="style1">Role Code</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="rolecode" name="rolecode" maxlength="20" size="25" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="style1">User Name</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="username" name="username" maxlength="25" size="30" class="validate[required] text-input"/>
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="style1">From Amount</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="fromamount" name="fromamount" maxlength="25" size="30" value="0" class="validate[required] text-input"
                                                   onblur="changeFormatFromAmount()"
                                     			   onkeypress="return isNumberKey(event)" 
                                            />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td class="style1">To Amount</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="toamount" name="toamount" maxlength="25" size="30" value="0" class="validate[required] text-input"
                                                   onblur="changeFormatToAmount()"
                                     			   onkeypress="return isNumberKey(event)" 
                                            />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
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
                    location.href = 'ApprovalRange.htm';
                    return false;
                });

            });

            $("#rolecode").click(function () {
                $("#dialog1").dialog({ width: 340, height: 275, position: "center", modal: true, zindex: 1, title: 'Select Role Code' });
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
        
        <div id="dialog1" title="Role Code Search" style="display:none;z-index:1;">
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