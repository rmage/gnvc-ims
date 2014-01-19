<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
            <title>IMS - New Purchase Order</title>
            <%@include file="../metaheader.jsp" %>
            
            <script type="text/javascript">
            
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

		       	var t, a, d, hsd,p1, p2, i;//t=total,a=amount,d=discount,hsd=hargasetelahdiscount,hsdp1=hargasetelahdiscountdanppn,p1=ppn,p2=pph
		       	function calSetTotal(){
					if(document.getElementById("currency").value != ""){
						
						   i = 0;
						   //format unitprice and amount to decimal point eg: 2,000,000.00
						   
						   aa = formatCurrency($("#unitprice").val());
						   bb = numberWithCommas (aa);
						  // $("#unitprices").val(bb);

						   cc = formatCurrency($("#amount").val());
						   dd = numberWithCommas (cc);
						  // $("#amounts").val(dd);
						   ee = dd;
						   //
						   
						   if($("#discount").val() > i){
							   
							   adisc = eval ( $("#amounts").val() );
							   bdisc = eval ( $("#discount").val() );
							   cdisc = adisc * bdisc/100 ;
							   ddisc = adisc - cdisc ;
	
							   edisc = formatCurrency(ddisc);
		                       fdisc = numberWithCommas (edisc);

							   d = cdisc;
							   a = fdisc;

							   console.log("potongan discount = "+d);
							   console.log("harga setelah discount = "+a);

							   $("#adisc").val(a);
							   t = a;
							   hsd = a;
							   
						   }else{

							   gnodisc = eval ( $("#amounts").val() );
							   hnodisc = formatCurrency(gnodisc);
		                       inodisc = numberWithCommas (hnodisc);

							   $("#disc").val(i);
							   a = inodisc;
							   console.log("harga tanpa discount = "+a);
							   t = a;
						   }

						
						   if($("#ppn").val() > i && $("#discount").val() > i){

							   x = a.replace(",", "");
							   y = x.replace(",","");
							   appn = eval(y);
							   bppn = eval( $("#ppn").val() );
							   cppn = eval ( $("#amounts").val() );

							   dppn = cppn * bppn/100 ;
							   eppn = appn + dppn;

							   fppn = formatCurrency(eppn);
							   gppn = numberWithCommas(fppn);

							   $("#pn").val(gppn);

							   p1 = dppn;
							   a = gppn;

							   console.log("dikenakan ppn = "+p1);
							   console.log("harga setelah discount + ppn = "+a);

							   $("#appn1").val(a);
							   
							   t = a;
							  
						   }else if ($("#ppn").val() > i && $("#discount").val() == i){
							   
							   x = a.replace(",", "");
							   y = x.replace(",","");
							   appn = eval(y);
							   bppn = eval( $("#ppn").val() );
							   cppn = eval ( $("#amounts").val() );

							   dppn = cppn * bppn/100 ;
							   eppn = cppn + dppn;
							   
							   fppn = formatCurrency(eppn);
							   gppn = numberWithCommas(fppn);

							   $("#pn").val(gppn);
							   
							   p1 = dppn;
							   w  = cppn + p1;
							   a  = gppn;
							   
							   console.log("dikenakan ppn = "+p1);
                               console.log("harga setelah ppn = "+a);

							   $("#appn1").val(a);
							   
							   t = a;
							   
						   }

						   if($("#pph").val() > i && $("#ppn").val() > i && $("#discount").val() > i){

							   x = hsd.replace(",", "");
							   y = x.replace(",","");
							   apph = eval(y);
							   bpph = eval( $("#pph").val() );
							   cpph = eval ( $("#amounts").val() );
							   
							   z1 = $("#appn1").val();
							   x2 = z1.replace(",", "");
							   y2 = x2.replace(",","");
							   z2 = eval (y2);//z2 = harga setelah discount dan ppn
							   
							   console.log("z2 ="+z2);
							   
							   dpph = apph * bpph/100 ;
							   epph = z2 - dpph;
							   
							   fpph = formatCurrency(epph);
							   gpph = numberWithCommas(fpph);

							   $("#ph").val(gpph);

							   p2 = dpph;
							   a = gpph;

							   console.log("dikenakan pph = "+p2);
							   console.log("harga setelah discount + ppn + pph = "+a);

							   $("#apph1").val(a);
							   
							   t = a;

						   }else if ($("#pph").val() > i && $("#ppn").val() > i && $("#discount").val() == i){
							
							   am = eval ( $("#amounts").val() ); 
							   x = a.replace(",", "");
							   y = x.replace(",","");
							   appn = eval(y);
							   bppn = eval( $("#ppn").val() );
							   cppn = eval ( $("#amounts").val() );

							   dppn = cppn * bppn/100 ;
							   eppn = cppn + dppn;
							   
							   fppn = formatCurrency(eppn);
							   gppn = numberWithCommas(fppn);

							   $("#pn").val(gppn);
							   
							   p1 = dppn;
							   w  = cppn + p1;
							   a  = gppn;
							   
							   console.log("dikenakan ppn = "+p1);
                               console.log("harga setelah ppn = "+a);

							   $("#appn1").val(a);
							   
							   x = a.replace(",", "");
							   y = x.replace(",","");
							   appn = eval(y);
							   bppn = eval( $("#pph").val() );
							   cppn = eval ( $("#amounts").val() );

							   dppn = cppn * bppn/100 ;
							   eppn = appn - dppn;

							   fppn = formatCurrency(eppn);
							   gppn = numberWithCommas(fppn);

							   $("#ph").val(gppn);

							   p2 = dppn;
							   a = gppn;

							   console.log("dikenakan pph = "+p2);
							   console.log("harga setelah ppn + pph = "+a);

							   $("#apph1").val(a);
							   
							   t = a;
							   
						   }else if ($("#pph").val() > i && $("#ppn").val() == i && $("#discount").val() == i){
							   
							   amount = eval ( $("#amounts").val() );
							   apph = eval( $("#pph").val() );
							   bpph = amount * apph/100;
							   
							   p2 = bpph;
							   afterpph = amount - p2;
							   
							   fppn = formatCurrency(afterpph);
							   gppn = numberWithCommas(fppn);
							   
							   a = gppn;
							   
							   console.log("dikenakan pph = "+p2);
							   console.log("harga setelah pph = "+a);
							   
							   $("#apph1").val(a);
							   
							   t = a ;
							   
						   }
							else if ($("#pph").val() > i && $("#ppn").val() == i && $("#discount").val() > i){
								
							   fc = formatCurrency(d);
							   nwc= numberWithCommas(fc);
						   	   $("#disc").val(nwc);
							  
						   	   adisc = $("#adisc").val();
							   xdd = adisc.replace(",", "");
							   ydd = xdd.replace(",","");
							   add = ydd;
							   
							   console.log("harga setelah discount sebelum pph = "+ydd);

							   apph = eval( $("#pph").val() );
							   bpph = ydd * apph/100;
							   
							   p2 = bpph;
							   afterpph = add - p2;
							   
							   fppn = formatCurrency(afterpph);
							   gppn = numberWithCommas(fppn);
							   
							   a = gppn;
							   
							   console.log("dikenakan pph = "+p2);
							   console.log("harga setelah discount + pph = "+a);
							   
							   $("#apph1").val(a);
							   
							   t = a ;
							   
						   }
						    
						   	console.log("discount ="+d);
						   	//format discount to decimal point
							fc = formatCurrency(d);
							nwc= numberWithCommas(fc);
						   	$("#disc").val(nwc);
							//
						   	
						   	console.log("ppn ="+p1);
						   	//format ppn to decimal point
						   	fc2 = formatCurrency(p1);
						    nwc2= numberWithCommas(fc2);
						    $("#ppn1").val(nwc2);

						    console.log("pph ="+p2);
						   	//format pph to decimal point
						   	fc3 = formatCurrency(p2);
						    nwc3= numberWithCommas(fc3);
						    $("#pph1").val(nwc3);
							
						   	$("#totals").val(t);
						   	
						    console.log("!!! total (t)="+t);
						    
						    /*
						     already testing for this condition
							    if(discount >0), ok
								if(discount >0 && ppn>0),ok
								if(discount == 0 && ppn>0),ok
								if(discount >0 && ppn == 0),ok
								if(discount >0 && ppn>0 && pph>0),ok
								if(discount == 0 && ppn>0 && pph>0),ok
						    */
						    
					}else{
						//this is just copying fuction above, else if no currency selected
						
						
					}		
			    }   	

				
    		</script>
    		
            <script language="JavaScript">
                $(document).ready(function(){
                    
                    $('#purchaseAddForm').validationEngine('attach');        
                    
	                    $("#list3").jqGrid({ url:'prsjson.htm', datatype: "json", hidegrid: false, shrinkToFit: true, autowidth: true,
	                        colNames:['PRS Number','PRS Date', 'Date Needed', 'Department','Supplier Name', 'Remarks', 'Created By'], 
	                        colModel:[ {formoptions:{colpos:1,rowpos:1},name:'prsnumber',index:'prsnumber',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
	       	                           {formoptions:{colpos:1,rowpos:1},name:'prsdate',index:'prsdate',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
	       	                           {formoptions:{colpos:1,rowpos:1},name:'requestdate',index:'requestdate',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}}, 
	                                   {formoptions:{colpos:1,rowpos:1},name:'departmentname',index:'departmentname',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}},
	                                   {formoptions:{colpos:1,rowpos:1},name:'suppliername',index:'suppliername',width:100, editable:true, editoptions:{readonly:false, size:30}, editrules:{required:true}, searchoptions:{sopt:['eq','ne','bw','ew','bn','en','cn','nc','in','ni']}},
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
		                            //fill
	                                var localRowData = $(this).getRowData(ids); 
	                                $("#prsnumber").val(localRowData.prsnumber);
	                                $("#prsdate").val(localRowData.prsdate);
	                                $("#requestdate").val(localRowData.requestdate);
	                                $("#departmentname").val(localRowData.departmentname);
	                                $("#suppliername").val(localRowData.suppliername);
	                                $("#remarks").val(localRowData.remarks);
	                                $("#dialog2").dialog('close');
	                            $.ajax({
                                dataType: 'json',
                                success: function(data) {
                                $.each(data.prsDetails, function(k,v){                                                         
                                var rowCount = $('#main tr').length-1;
                                var vunitprice, vamount;
                                
                                function formatCurrency(n) {
                                	n = isNaN(n) || n === '' || n === null ? 0.00 : n;
                        			return parseFloat(n).toFixed(2);
                        		}
                        		
                        		function numberWithCommas(n) {
                        			var parts=n.toString().split(".");
                        			return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
                        		}
                                
                        		x = formatCurrency(v.unitprice);
                        		y = numberWithCommas (x);
                        		vunitprice = y;
                        		
                        		xx = formatCurrency(v.amount);
                        		yy = numberWithCommas (xx);	
                        		vamount = yy;
                                
                                $("<tr class=\"myhover\">"+
								"<td class=\"style1\">"+rowCount+"</td>"+
                            	"<input type=\"hidden\" name=\"productCode1\" value=\""+v.productcode+"\">"+
                            	"<input type=\"hidden\" name=\"qty\" value=\""+v.qty+"\">"+
                            	"<input type=\"hidden\" name=\"uom\" value=\""+v.uom+"\">"+
                            	"<td class=\"style1\">"+v.productname+"</td>"+
                            	"<td class=\"style1\">"+v.uom+"</td>"+	                                                                                                                                                 
                            	"<td class=\"style1\">"+v.qty+"</td>"+ 
                            	"<td class=\"style1\">"+v.warranty+"<input type=\"hidden\" name=\"warranty\" value=\""+v.warranty+"\" id=\"warranty\"></td> "+
                            	"<td class=\"style1\">"+v.termpayment+"<input type=\"hidden\" name=\"termpayment\" value=\""+v.termpayment+"\" id=\"termpayment\"></td> "+
                            	"<td class=\"style1\">"+v.termdelivery+"<input type=\"hidden\" name=\"termdelivery\" value=\""+v.termdelivery+"\" id=\"termdelivery\"></td> "+
                            	"<td class=\"style1\"><span id=\""+rowCount+"-currency2\"></span> <input type=\"hidden\" name=\"currency1\" id=\""+rowCount+"-currency1\" /></td> "+
                            	
                            	"<td class=\"style1\">"+vunitprice+"<input type=\"hidden\" name=\"unitprice\" value=\""+v.unitprice+"\" id=\"unitprice\"></td> "+
                            	//"<td class=\"style1\">"+vunitprice+"<span id=\""+rowCount+"-unitprice2\"></span> <input type=\"hidden\" name=\"unitprice\" value=\""+v.unitprice+"\" id=\""+rowCount+"-unitprice\" /></td> "+
                            	
                            	"<td class=\"style1\">"+vamount+"<input type=\"hidden\" name=\"amount\" value=\""+v.amount+"\" id=\"amount\"></td> "+
                            	//"<td class=\"style1\">"+vamount+"<span id=\""+rowCount+"-amount2\"></span> <input type=\"hidden\" name=\"amount\" value=\""+v.amount+"\" id=\""+rowCount+"-amount\" /></td> "+
                            	
                            	
                            	"<td class=\"style1\">"+v.discount+"<input type=\"hidden\" name=\"discount\" value=\""+v.discount+"\" id=\"discount\"></td> "+
                            	"<td class=\"style1\">"+v.pph+"<input type=\"hidden\" name=\"pph\" value=\""+v.pph+"\" id=\"pph\"></td> "+
                            	"<td class=\"style1\">"+v.ppn+"<input type=\"hidden\" name=\"ppn\" value=\""+v.ppn+"\" id=\"ppn\"></td> "+
                            	"<td class=\"style1\"><span id=\""+rowCount+"-total2\"></span><input type=\"hidden\" name=\"total1\" id=\""+rowCount+"-total1\" /></td> "+
                            	" <input type=\"hidden\" name=\"remarks21\" id=\""+rowCount+"-remarks21\" /></td> "
                                +"<td class=\"style1\"><a class=\"check\" productCode=\""+v.productcode+"\" "
                                +"productName=\""+v.productname+"\" "
                                +"uom=\""+v.uom
                                +"\" rowCount=\""+rowCount+"\" qty=\""+v.qty+"\" unitprice=\""+v.unitprice+"\" amount=\""+v.amount+"\" href=\"javascript:void(0)\">Entry</a></td>"+
							    "</tr>").appendTo("#main tbody")
                                });
                                },
                                  url: 'prsdetailjson.htm?param='+localRowData.prsnumber+'&supplier='+localRowData.suppliername
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
	                    	 console.log("prsnumber click ???");
	                        $("#dialog2").dialog({ width: 650, height: 275, position: "center", modal: true, zindex: 9999, title: 'Select PRS' });
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
    <body>
        <%
        	java.util.HashMap m = (java.util.HashMap) request.getAttribute("model");
  		    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE, dd MM yyyy");
  		    com.app.wms.engine.db.dto.map.LoginUser l = (com.app.wms.engine.db.dto.map.LoginUser) request.getSession().getAttribute("user");
  		    boolean isReadOnly = true;
  		    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        %>


        <div class="container">
            <%@include file="../header.jsp" %>
            <jsp:include page="../dynmenu.jsp" />

            <div id="content" style="display: none" class="span-24 last">
                <div class="box">
                    <form action="purchaseOrderAdd.htm" method="post" id="purchaseAddForm">
                        <input type="hidden" name="action" value="savePicking" />

                        <table class="collapse tblForm row-select">
                            <caption>Purchase Order Add</caption>
                            <tbody class="tbl-nohover">

                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">Purchase Order No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="purchaseNo" id="purchaseNo" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                         <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                  <td>&nbsp;</td>
                                    <td>Purchase Order Date</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="pocreated1" size="25" id="pocreated1" readonly 
                                                   value="<%= dateFormat.format(new Date()) %>" />                                                            
                                        </label>
                                    </td>
                               </tr>
                               
                               <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td width="20%">PRS No</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" name="prsnumber" id="prsnumber" value="" size="30"                                                    
                                                   class="validate[required] text-input" />
                                        </label>
                                        <label class="requiredfield" title="This Field Is Required!">*</label>
                                    </td>
                                  <td>&nbsp;</td>
                                    <td>PRS Date</td>
                                    <td class="style1">
                                        <label>                                                                                                                                                                             
                                            <input type="text" name="prsdate" size="25" id="prsdate" readonly 
                                             />                                                            
                                        </label>
                                    </td>
                               </tr>
                               
                                <tr class="detail_genap">
                                    <td>&nbsp;</td>
                                    <td>Department</td>
                                    <td class="style1">
                                        <label>
                                            <input type="text" id="departmentname" name="departmentname" value="" size="30"
                                              readonly />
                                        </label>
                                    </td>
                                     <td>&nbsp;</td>
                                    <td width="20%">Date Needed</td>
                                     <td class="style1">
                                        <label>
                                            <input type="text" id="requestdate" name="requestdate" value="" size="30"
                                              readonly />
                                              <input type="hidden" id="suppliername" name="suppliername" value="" size="30"/>
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
	                                    <td class="style1">Item</td>
	                                    <td class="style1">Unit</td>
	                                    <td class="style1">Qty</td>
	                                    <td class="style1">Warranty</td>
	                                    <td class="style1">Term of Payment</td>
	                                    <td class="style1">Term of Delivery</td>
	                                    <td class="style1">Currency</td>
	                                    <td class="style1">Unit Price</td>
	                                    <td class="style1">Amount</td>
	                                    <td class="style1">Disc %</td>
	                                    <td class="style1">PPH %</td>
	                                    <td class="style1">PPN %</td>
	                                    <td class="style1">Total</td>
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
        <!-- script untuk button -->
        <script type="text/javascript">
            $(function() {
                $('#btnCancel').click(function() {
                    location.href = 'Purchase.htm?';
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

      			$('.check').live("click", function() {

      				// clear values
      				$("#unitprices").val("0.00");
      				$("#amounts").val("0.00");
      				
                    $("#disc").val("0.00");
                    $("#adisc").val("0.00");
                    
                    $("#ppn1").val("0.00");
                    $("#appn1").val("0.00");
                    
                    $("#pph1").val("0.00");
                    $("#apph1").val("0.00");
	                $("#totals").val("0.00");
                    $('#remarks2').val(''); 

                    // setup values
                    $('#productCode').val($(this).attr('productCode'));                        
                    $('#productName').val($(this).attr('productName')); 
                    $('#UoM').val($(this).attr('uom'));      
                    $('#qtypo').val($(this).attr('qty'));   
                    $('#warrantys').val($('#warranty').val());  
                    $('#termpayments').val($('#termpayment').val());  
                    $('#termdeliverys').val($('#termdelivery').val());  
                    $('#unitprices').val($(this).attr('unitprice')); 
                    $('#amounts').val($(this).attr('amount'));  
                    $('#discounts').val($('#discount').val());  
                    $('#pphs').val($('#pph').val());  
                    $('#ppns').val($('#ppn').val());  
                    
                    var productCode = $(this).attr('rowcount');
                    var qty = $(this).attr('qty');
                    $("#dialog3").dialog({ width: 440, height: 650, position: "center", modal: true, 
                           buttons: {
                               "Cancel": function() {     

					                  // $("#unitprice").val("0");
					                   //$("#amount").val("0");    			                              
                                       $( this ).dialog( "close" );                                        
                               },
                               "Save": function() { 
								   /*
								   if( $("#unitprice").val() == "0" ||  $("#amount").val() == "0" ){
										alert("data empty : 'please to entry unit price' ");
										return false;
								   }
									*/
									
                            	   if( $("#currency").val() == ""){
										alert("data mandatory : 'please to entry the currency");
										return false;
								   }

									   $("#"+productCode+"-total2").html($('#totals').val());
								   	   $("#"+productCode+"-currency2").html($('#currency').val());
	                                   $("#"+productCode+"-remarks21").html($('#remarks2').val());

	                                   $("#"+productCode+"-total2").val($('#totals').val());
	                                   $("#"+productCode+"-currency2").val($('#currency').val());

	                            	   $("#"+productCode+"-total1").val($('#totals').val());
	                            	   $("#"+productCode+"-currency1").val($('#currency').val());
	                                   $("#"+productCode+"-remarks21").val($('#remarks2').val());

	                                   $( this ).dialog( "close" );
								  
                                   
                               }
                           },
                        zindex: 9999, title: 'Set Item Price' });                     
                }); 
                
                 
                $("#btnSavePurchase").click(function () {  
                	
                	var rowCount = $('#main tr').length-1;
                    console.log("rowCount= "+rowCount--);
					
					if($("#"+rowCount+"-currency2").val()== "" && $("#"+rowCount+"-total2").val()== ""){
						alert("please to entry currency and total");
					}else{
		                    //if invalid do nothing
		                    if(!$("#purchaseAddForm").validationEngine('validate')){
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
		                                $("form#purchaseAddForm").submit();
		                            }
		                        },
		                        zindex: 1, title: 'Confirm' });
					
					}
                });
    
        </script>
        
        
        <div id="dialog2" title="PRS Search" style="display:none;z-index:1;">
            <table id="list3" cellpadding="0" cellspacing="0" width="100%"></table> 
            <div id="pager3"></div> 
        </div>
        
        <div id="dialog-confirm" title="Product Search" style="display:none;z-index:1;">
            Save Data?
        </div>
        
        <div id="dialog-incomplete" title="Product Search" style="display:none;z-index:1;">
            Please to fill mandatory data
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
    		
    	    <div id="dialog3" title="Item Entry" style="display:none;z-index:1;">
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
                         <td><label>UoM</label></td>
                         <td> <input type="text" name="uom" id="UoM" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Item Qty</label></td>
                         <td> <input type="text" name="qtypo" id="qtypo" readonly /></td>
                         <td> <input type="hidden" name="warrantys" id="warrantys"/></td>
                         <td> <input type="hidden" name="termpayments" id="termpayments" /></td>
                         <td> <input type="hidden" name="termdeliverys" id="termdeliverys" /></td>
                     </tr>
                     <tr>
                     	  <td>Currency</td>
                          <td class="style1">
                              <label>
                               <select name="currencyCode" id="currency" onchange="calSetTotal(this.value)" >
									<option value=""></option>
									<c:if test="${requestScope.model.dropListCurrency!=null}">
	                               	<c:forEach items="${requestScope.model.dropListCurrency}" var="droplist" >
	                               		  <option value="${droplist.currencyCode}" ${(droplist.currencyCode eq requestScope.model.currencyCode)? "selected": ""}>
	                               		  	 ${droplist.currencyCode} - ${droplist.currencySymbol}
	                               		  </option> 
	                               	</c:forEach>
	                               	</c:if>
                               </select>
                          	 </label>
                          	 <label class="requiredfield" title="This Field Is Required!">*</label>
                          </td>
                     </tr>      
                     <tr>
                         <td><label>Unit Price</label></td>
                         <td>= <input type="text" name="unitprices" id="unitprices" value="0"
                                    
                                     />
                         </td>
                     </tr>
                     <tr>
                         <td></td>
                         <td><label> ------------------------------------ </label></td>
                     </tr>
                     <tr>
                         <td><label>Amount</label></td>
                         <td>= <input type="text" name="amounts" id="amounts" value="0" 
                         		
                         		/>
                         </td>
                     </tr>
                     <tr>
                         <td>Discount</td>
                         <td> 
	                         	<input type="hidden" name="discounts" id="discounts" value="0"/>
	                            = <input type="text" name="disc" id="disc" value="0" 
	                           
	                            />
                         </td>
                     </tr>
                     <tr>
                         <td></td>
                         <td><label> ------------------------------------ (-)</label></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td> 
	                            = <input type="text" name="adisc" id="adisc" value="0" readonly/>
                         </td>
                     </tr>
                     <tr>
                         <td>PPN</td>
                         <td> 
	                         	<input type="hidden" name="ppns" id="ppns" value="0"/>
	                            = <input type="text" name="ppn1" id="ppn1" value="0" 
	                           
	                            />
                         </td>
                     </tr>
                     <tr>
                         <td></td>
                         <td><label> ------------------------------------ (+)</label></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td> 
	                            = <input type="text" name="appn1" id="appn1" value="0" readonly/>
                         </td>
                     </tr>
                     
                     <tr>
                         <td>PPH</td>
                         <td> 
	                         	<input type="hidden" name="pphs" id="pphs" value="0"/>
	                            = <input type="text" name="pph1" id="pph1" value="0" 
	                           
	                            />
                         </td>
                     </tr>
                     <tr>
                         <td></td>
                         <td><label> ------------------------------------ (-)</label></td>
                     </tr>
                     <tr>
                         <td></td>
                         <td> 
	                            = <input type="text" name="apph1" id="apph1" value="0" readonly/>
                         </td>
                     </tr>
                     
                     <tr>
                         <td></td>
                         <td><label> ------------------------------------ </label></td>
                     </tr>
                     <tr>
                         <td><label>Total</label></td>
                         <td>= <input type="text" name="totals" id="totals" value="0" readonly /></td>
                     </tr>
                     <tr>
                         <td><label>Remarks</label></td>
                         <td> <textarea type="text" name="remarks2" id="remarks2" value="" style="width: 178px; height: 80px;"></textarea></td>
                     </tr>    
                 </table>                                 
            </form>
        	</div>
        
    </body>
</html>
